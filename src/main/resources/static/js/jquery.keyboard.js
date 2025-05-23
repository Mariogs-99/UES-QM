/*! jQuery UI Virtual Keyboard v1.25.3 *//*

Author: Jeremy Satterfield
Modified: Rob Garrison (Mottie on github)
-----------------------------------------
Licensed under the MIT License

Caret code modified from jquery.caret.1.02.js
Licensed under the MIT License:
http://www.opensource.org/licenses/mit-license.php
-----------------------------------------

An on-screen virtual keyboard embedded within the browser window which
will popup when a specified entry field is focused. The user can then
type and preview their input before Accepting or Canceling.

As a plugin to jQuery UI styling and theme will automatically
match that used by jQuery UI with the exception of the required CSS.

Requires: jQuery v1.4.3+
Optional:
 jQuery UI (position utility only) & CSS theme
 jQuery mousewheel

Setup/Usage:
	Please refer to https://github.com/Mottie/Keyboard/wiki
*/
/*jshint browser:true, jquery:true, unused:false */
/*global require:false, define:false, module:false */
;(function(factory) {
	if (typeof define === 'function' && define.amd) {
		define(['jquery'], factory);
	} else if (typeof module === 'object' && typeof module.exports === 'object') {
		module.exports = factory(require('jquery'));
	} else {
		factory(jQuery);
	}
}(function($) {
'use strict';
var $keyboard = $.keyboard = function(el, options){
	var base = this, o;

	base.version = '1.25.3';

	// Access to jQuery and DOM versions of element
	base.$el = $(el);
	base.el = el;

	// Add a reverse reference to the DOM object
	base.$el.data('keyboard', base);

	base.init = function(){
		var position,
			kbcss = $keyboard.css;
		base.settings = options || {};
		// shallow copy position to prevent performance issues; see #357
		if ( options && options.position ) {
			position = $.extend( {}, options.position );
			options.position = null;
		}
		base.options = o = $.extend( true, {}, $keyboard.defaultOptions, options );
		if ( position ) {
			o.position = position;
			options.position = position;
		}

		// keyboard is active (not destroyed);
		base.el.active = true;
		// unique keyboard namespace
		base.namespace = '.keyboard' + Math.random().toString(16).slice(2);
		// extension namespaces added here (to unbind listeners on base.$el upon destroy)
		base.extensionNamespace = [];
		// Shift and Alt key toggles, sets is true if a layout has more than one keyset
		// used for mousewheel message
		base.shiftActive = base.altActive = base.metaActive = base.sets = base.capsLock = false;
		// Class names of the basic key set - meta keysets are handled by the keyname
		base.rows = [ '', '-shift', '-alt', '-alt-shift' ];

		base.inPlaceholder = base.$el.attr('placeholder') || '';
		// html 5 placeholder/watermark
		base.watermark = $keyboard.watermark && base.inPlaceholder !== '';
		// convert mouse repeater rate (characters per second) into a time in milliseconds.
		base.repeatTime = 1000/(o.repeatRate || 20);
		// delay in ms to prevent mousedown & touchstart from both firing events at the same time
		o.preventDoubleEventTime = o.preventDoubleEventTime || 100;
		// flag indication that a keyboard is open
		base.isOpen = false;
		// is mousewheel plugin loaded?
		base.wheel = $.isFunction( $.fn.mousewheel );
		// keyCode of keys always allowed to be typed - caps lock, page up & down, end, home, arrow, insert &
		// delete keys
		base.alwaysAllowed = [20,33,34,35,36,37,38,39,40,45,46];
		base.$keyboard = [];
		// keyboard enabled
		base.enabled = true;
		// make a copy of the original keyboard position
		if (!$.isEmptyObject(o.position)) {
			o.position.orig_at = o.position.at;
		}

		base.checkCaret = ( o.lockInput || $keyboard.checkCaretSupport() );

		// [shift, alt, meta]
		base.last = {
			start: 0,
			end: 0,
			key: '',
			val: '',
			layout: '',
			virtual: true,
			keyset: [ false, false, false ],
			wheel_$Keys : null,
			wheelIndex : 0,
			wheelLayers: []
		};
		base.temp = [ '', 0, 0 ]; // used when building the keyboard - [keyset element, row, index]

		// Bind events
		$.each('initialized beforeVisible visible hidden canceled accepted beforeClose'.split(' '), function( i, f ) {
			if ($.isFunction(o[f])){
				base.$el.bind(f + base.namespace, o[f]);
			}
		});

		// Close with esc key & clicking outside
		if (o.alwaysOpen) { o.stayOpen = true; }
		$(document).bind('mousedown keyup touchstart checkkeyboard '.split(' ').join(base.namespace + ' '), function( e ) {
			if (base.opening) { return; }
			base.escClose(e);
			var $target = $(e.target);
			// needed for IE to allow switching between keyboards smoothly
			if ( $target.hasClass( kbcss.input ) ) {
				var kb = $target.data('keyboard');
				// only trigger on self
				if ( kb === base && !kb.$el.hasClass( kbcss.isCurrent ) && e.type === kb.options.openOn ) {
					kb.focusOn();
				}
			}
		});

		// Display keyboard on focus
		base.$el
			.addClass( kbcss.input + ' ' + o.css.input )
			.attr({ 'aria-haspopup' : 'true', 'role' : 'textbox' });

		// add disabled/readonly class - dynamically updated on reveal
		if (base.$el.is(':disabled') || (base.$el.attr('readonly') &&
			!base.$el.hasClass(kbcss.locked))) {
			base.$el.addClass(kbcss.noKeyboard);
		}
		if (o.openOn) {
			base.$el.bind(o.openOn + base.namespace, function(){
				base.focusOn();
			});
		}

		// Add placeholder if not supported by the browser
		if (!base.watermark && base.$el.val() === '' && base.inPlaceholder !== '' &&
			base.$el.attr('placeholder') !== '') {
			base.$el
				.addClass(kbcss.placeholder) // css watermark style (darker text)
				.val( base.inPlaceholder );
		}

		base.$el.trigger( $keyboard.events.kbInit, [ base, base.el ] );

		// initialized with keyboard open
		if (o.alwaysOpen) {
			base.reveal();
		}

	};

	base.toggle = function(){
		var $toggle = base.$keyboard.find( '.' + $keyboard.css.keyToggle ),
			locked = !base.enabled;
		// prevent physical keyboard from working
		base.$preview.prop( 'readonly', locked || base.options.lockInput );
		// disable all buttons
		base.$keyboard
			.toggleClass( $keyboard.css.keyDisabled, locked )
			.find( '.' + $keyboard.css.keyButton )
			.not( $toggle )
			.prop( 'disabled', locked )
			.attr( 'aria-disabled', locked );
		$toggle.toggleClass( $keyboard.css.keyDisabled, locked );
		// stop auto typing
		if ( locked && base.typing_options ) {
			base.typing_options.text = '';
		}
	};

	base.setCurrent = function(){
		var kbcss = $keyboard.css;
		// ui-keyboard-has-focus is applied in case multiple keyboards have alwaysOpen = true and are stacked
		$('.' + kbcss.hasFocus).removeClass(kbcss.hasFocus);
		$('.' + kbcss.isCurrent).removeClass(kbcss.isCurrent);

		base.$el.addClass(kbcss.isCurrent);
		base.$keyboard.addClass(kbcss.hasFocus);
		base.isCurrent(true);
		base.isOpen = true;
	};

	base.isCurrent = function(set){
		var cur = $keyboard.currentKeyboard || false;
		if (set) {
			cur = $keyboard.currentKeyboard = base.el;
		} else if (set === false && cur === base.el) {
			cur = $keyboard.currentKeyboard = '';
		}
		return cur === base.el;
	};

	base.isVisible = function() {
		return base.$keyboard && base.$keyboard.length ? base.$keyboard.is(':visible') : false;
	};

	base.focusOn = function(){
		if ( !base && base.el.active ) {
			// keyboard was destroyed
			return;
		}
		if (base.$el.is(':visible')) {
			// caret position is always 0,0 in webkit; and nothing is focused at this point... odd
			// save caret position in the input to transfer it to the preview
			// add delay to get correct caret position
			base.timer2 = setTimeout(function(){
				var undef;
				// Number inputs don't support selectionStart and selectionEnd
				// Number/email inputs don't support selectionStart and selectionEnd
				if ( !/(number|email)/i.test(base.el.type) && !o.caretToEnd ) {
					base.saveCaret( undef, undef, base.$el );
				}
			}, 20);
		}
		if (!base.isVisible()) {
			clearTimeout(base.timer);
			base.reveal();
		}
		if (o.alwaysOpen) {
			base.setCurrent();
		}
	};

	base.reveal = function(refresh){
		if (base.isOpen) {
			refresh = true;
		}
		var kbcss = $keyboard.css;
		base.opening = true;
		// remove all 'extra' keyboards
		$('.' + kbcss.keyboard).not('.' + kbcss.alwaysOpen).remove();

		// update keyboard after a layout change
		if (refresh) {
			base.isOpen = false;
			base.last.val = base.$preview && base.$preview.val() || '';
			if (base.$keyboard.length) {
				base.$keyboard.remove();
				base.$keyboard = [];
				base.shiftActive = base.altActive = base.metaActive = false;
			}
		}

		// Don't open if disabled
		if (base.$el.is(':disabled') || (base.$el.attr('readonly') &&
			!base.$el.hasClass(kbcss.locked))) {
			base.$el.addClass(kbcss.noKeyboard);
			return;
		} else {
			base.$el.removeClass(kbcss.noKeyboard);
		}

		// Unbind focus to prevent recursion - openOn may be empty if keyboard is opened externally
		if (o.openOn) {
			base.$el.unbind( o.openOn + base.namespace );
		}

		// build keyboard if it doesn't exist; or attach keyboard if it was removed, but not cleared
		if ( !base.$keyboard || base.$keyboard && ( !base.$keyboard.length || $.contains(document.body, base.$keyboard[0]) ) ) {
			base.startup();
		}

		// clear watermark
		if (!base.watermark && base.el.value === base.inPlaceholder) {
			base.$el
				.removeClass(kbcss.placeholder)
				.val('');
		}
		// save starting content, in case we cancel
		base.originalContent = base.$el.val();
		base.$preview.val( refresh ? base.last.val : base.originalContent );

		// disable/enable accept button
		if (o.acceptValid) { base.checkValid(); }

		if (o.resetDefault) {
			base.shiftActive = base.altActive = base.metaActive = false;
			base.showSet();
		}

		// beforeVisible event
		base.$el.trigger( $keyboard.events.kbBeforeVisible, [ base, base.el ] );

		base.setCurrent();
		// update keyboard - enabled or disabled?
		base.toggle();

		// show keyboard
		base.$keyboard.show();

		// adjust keyboard preview window width - save width so IE won't keep expanding (fix issue #6)
		if (o.usePreview && $keyboard.msie) {
			if (typeof base.width === 'undefined') {
				base.$preview.hide(); // preview is 100% browser width in IE7, so hide the damn thing
				base.width = Math.ceil(base.$keyboard.width()); // set input width to match the widest keyboard row
				base.$preview.show();
			}
			base.$preview.width(base.width);
		}

		base.position = $.isEmptyObject(o.position) ? false : o.position;

		// position after keyboard is visible (required for UI position utility) and appropriately sized
		if ($.ui && $.ui.position && base.position) {
			// get single target position || target stored in element data (multiple targets) || default @ element
			base.position.of = base.position.of || base.$el.data('keyboardPosition') || base.$el;
			base.position.collision = base.position.collision || 'flipfit flipfit';
			o.position.at = o.usePreview ? o.position.orig_at : o.position.at2;
			base.$keyboard.position(base.position);
		}

		base.checkDecimal();

		// get preview area line height
		// add roughly 4px to get line height from font height, works well for font-sizes from 14-36px
		// needed for textareas
		base.lineHeight = parseInt( base.$preview.css('lineHeight'), 10) ||
			parseInt(base.$preview.css('font-size') ,10) + 4;

		if (o.caretToEnd) {
			base.saveCaret( base.originalContent.length, base.originalContent.length );
		}

		// IE caret haxx0rs
		if ($keyboard.allie){
			// sometimes end = 0 while start is > 0
			if (base.last.end === 0 && base.last.start > 0) {
				base.last.end = base.last.start;
			}
			// IE will have start -1, end of 0 when not focused (see demo: http://jsfiddle.net/Mottie/fgryQ/3/)
			if (base.last.start < 0) {
				// ensure caret is at the end of the text (needed for IE)
				base.last.start = base.last.end = base.originalContent.length;
			}
		}

		// opening keyboard flag; delay allows switching between keyboards without immediately closing
		// the keyboard
		base.timer2 = setTimeout(function(){
			base.opening = false;
			if (o.initialFocus) {
				$keyboard.caret( base.$preview, base.last );
			}
			// save event time for keyboards with stayOpen: true
			base.last.eventTime = new Date().getTime();
			base.$el.trigger( $keyboard.events.kbVisible, [ base, base.el ] );
			base.timer = setTimeout(function(){
				// get updated caret information after visible event - fixes #331
				if (base) { // Check if base exists, this is a case when destroy is called, before timers have fired
					base.saveCaret();
				}
			}, 200);
		}, 10);
		// return base to allow chaining in typing extension
		return base;
	};

	base.updateLanguage = function(){
		// change language if layout is named something like 'french-azerty-1'
		var layouts =  $keyboard.layouts,
			lang = o.language || layouts[ o.layout ] && layouts[ o.layout ].lang && layouts[ o.layout ].lang || [ o.language || 'en' ],
			kblang = $keyboard.language;

		// some languages include a dash, e.g. 'en-gb' or 'fr-ca'
		// allow o.language to be a string or array...
		// array is for future expansion where a layout can be set for multiple languages
		lang = ( $.isArray(lang) ? lang[0] : lang ).split('-')[0];

		// set keyboard language
		o.display = $.extend( true, {}, kblang.en.display, kblang[ lang ] && kblang[ lang ].display || {}, base.settings.display );

		o.combos = $.extend( true, {}, kblang.en.combos, kblang[ lang ] && kblang[ lang ].combos || {}, base.settings.combos );
		o.wheelMessage = kblang[ lang ] && kblang[ lang ].wheelMessage || kblang.en.wheelMessage;
		// rtl can be in the layout or in the language definition; defaults to false
		o.rtl = layouts[ o.layout ] && layouts[ o.layout ].rtl || kblang[ lang ] && kblang[ lang ].rtl  || false;

		// save default regex (in case loading another layout changes it)
		base.regex = kblang[ lang ] && kblang[ lang ].comboRegex || $keyboard.comboRegex;
		// determine if US '.' or European ',' system being used
		base.decimal = /^\./.test(o.display.dec);
		base.$el
			.toggleClass('rtl', o.rtl)
			.css('direction', o.rtl ? 'rtl' : '');
	};

	base.startup = function(){
		var kbcss = $keyboard.css;
		// ensure base.$preview is defined
		base.$preview = base.$el;

		if ( !(base.$keyboard && base.$keyboard.length) ) {
			// custom layout - create a unique layout name based on the hash
			if (o.layout === 'custom') { o.layoutHash = 'custom' + base.customHash(); }
			base.layout = o.layout === 'custom' ? o.layoutHash : o.layout;
			base.last.layout = base.layout;

			base.updateLanguage();
			if (typeof $keyboard.builtLayouts[base.layout] === 'undefined') {
				if ($.isFunction(o.create)) {
					// create must call buildKeyboard() function; or create it's own keyboard
					base.$keyboard = o.create(base);
				} else if (!base.$keyboard.length) {
					base.buildKeyboard(base.layout, true);
				}
			}
			base.$keyboard = $keyboard.builtLayouts[base.layout].$keyboard.clone();
			base.$keyboard.data( 'keyboard', base );
			if ( ( base.el.id || '' ) !== '' ) {
				// add ID to keyboard for styling purposes
				base.$keyboard.attr( 'id', base.el.id + $keyboard.css.idSuffix );
			}

			// build preview display
			if (o.usePreview) {
				// restore original positioning (in case usePreview option is altered)
				if (!$.isEmptyObject(o.position)) {
					o.position.at = o.position.orig_at;
				}
				base.$preview = base.$el.clone(false)
					.removeAttr('id')
					.data( 'keyboard', base )
					.removeClass(kbcss.placeholder + ' ' + kbcss.input)
					.addClass(kbcss.preview + ' ' + o.css.input)
					.removeAttr('aria-haspopup')
					.attr('tabindex', '-1')
					.show(); // for hidden inputs
				// Switch the number input fields to text so the caret positioning will work again
				if (base.$preview.attr('type') == 'number') {
					base.$preview.attr('type', 'text');
				}
				// build preview container and append preview display
				$('<div />')
					.addClass(kbcss.wrapper)
					.append(base.$preview)
					.prependTo(base.$keyboard);
			} else {
				// No preview display, use element and reposition the keyboard under it.
				if (!$.isEmptyObject(o.position)) {
					o.position.at = o.position.at2;
				}
			}

		}

		base.preview = base.$preview[0];
		base.$decBtn = base.$keyboard.find('.' + kbcss.keyPrefix + 'dec');
		// add enter to allowed keys; fixes #190
		if (o.enterNavigation || base.el.nodeName === 'TEXTAREA') { base.alwaysAllowed.push(13); }
		if (o.lockInput) {
			base.$preview.addClass(kbcss.locked).attr({ 'readonly': 'readonly'});
		}

		base.bindKeyboard();

		base.$keyboard.appendTo( o.appendLocally ? base.$el.parent() : o.appendTo || 'body' );

		base.bindKeys();

		// adjust with window resize; don't check base.position
		// here in case it is changed dynamically
		if (o.reposition && $.ui && $.ui.position && o.appendTo == 'body') {
			$(window).bind('resize' + base.namespace, function(){
				if (base.position && base.isVisible()) {
					base.$keyboard.position(base.position);
				}
			});
		}

	};

	base.saveCaret = function(start, end, $el){
		var p = $keyboard.caret( $el || base.$preview, start, end );
		base.last.start = typeof start === 'undefined' ? p.start : start;
		base.last.end = typeof end === 'undefined' ? p.end : end;
	};

	base.setScroll = function(){
		// Set scroll so caret & current text is in view
		// needed for virtual keyboard typing, NOT manual typing - fixes #23
		if ( base.last.virtual ) {

			var scrollWidth, clientWidth, adjustment, direction,
				isTextarea = base.preview.nodeName === 'TEXTAREA',
				value = base.last.val.substring( 0, Math.max( base.last.start, base.last.end ) );

			if ( !base.$previewCopy ) {
				// clone preview
				base.$previewCopy = base.$preview.clone()
					.removeAttr('id') // fixes #334
					.css({
						position : 'absolute',
						left: 0,
						zIndex : -10,
						visibility : 'hidden'
					})
					.addClass('ui-keyboard-preview-clone');
				if ( !isTextarea ) {
					// make input zero-width because we need an accurate scrollWidth
					base.$previewCopy.css({ 'white-space' : 'pre', 'width' : 0 });
				}
				if (o.usePreview) {
					// add clone inside of preview wrapper
					base.$preview.after( base.$previewCopy );
				} else {
					// just slap that thing in there somewhere
					base.$keyboard.prepend( base.$previewCopy );
				}
			}

			if ( isTextarea ) {
				// need the textarea scrollHeight, so set the clone textarea height to be the line height
				base.$previewCopy
					.height( base.lineHeight )
					.val( value );
				// set scrollTop for Textarea
				base.preview.scrollTop = base.lineHeight * ( Math.floor( base.$previewCopy[0].scrollHeight / base.lineHeight ) - 1 );
			} else {
				// add non-breaking spaces
				base.$previewCopy.val( value.replace(/\s/g, '\xa0') );

				// if scrollAdjustment option is set to "c" or "center" then center the caret
				adjustment = /c/i.test( o.scrollAdjustment ) ? base.preview.clientWidth / 2 : o.scrollAdjustment;
				scrollWidth = base.$previewCopy[0].scrollWidth - 1;

				// set initial state as moving right
				if ( typeof base.last.scrollWidth === 'undefined' ) {
					base.last.scrollWidth = scrollWidth;
					base.last.direction = true;
				}
				// if direction = true; we're scrolling to the right
				direction = base.last.scrollWidth === scrollWidth ? base.last.direction : base.last.scrollWidth < scrollWidth;
				clientWidth = base.preview.clientWidth - adjustment;

				// set scrollLeft for inputs; try to mimic the inherit caret positioning + scrolling:
				// hug right while scrolling right...
				if ( direction ) {
					if ( scrollWidth < clientWidth ) {
						base.preview.scrollLeft = 0;
					} else {
						base.preview.scrollLeft = scrollWidth - clientWidth;
					}
				} else {
					// hug left while scrolling left...
					if ( scrollWidth >= base.preview.scrollWidth - clientWidth ) {
						base.preview.scrollLeft = base.preview.scrollWidth - adjustment;
					} else if ( scrollWidth - adjustment > 0 ) {
						base.preview.scrollLeft = scrollWidth - adjustment;
					} else {
						base.preview.scrollLeft = 0;
					}
				}

				base.last.scrollWidth = scrollWidth;
				base.last.direction = direction;
			}
		}
	};

	base.bindKeyboard = function(){
		var evt, layout = $keyboard.builtLayouts[base.layout];
		base.$preview
			.unbind('keypress keyup keydown mouseup touchend '.split(' ').join(base.namespace + ' '))
			.bind('click' + base.namespace, function(){
				// update last caret position after user click, use at least 150ms or it doesn't work in IE
				base.timer2 = setTimeout(function(){
					base.saveCaret();
				}, 150);
			})
			.bind('keypress' + base.namespace, function(e){
				if (o.lockInput) { return false; }
				var k = e.charCode || e.which,
					str = base.last.key = String.fromCharCode(k);
				base.last.virtual = false;
				base.last.event = e;
				base.last.$key = []; // not a virtual keyboard key
				if (base.checkCaret) {
					base.saveCaret();
				}
				// update caps lock - can only do this while typing =(
				base.capsLock = (((k >= 65 && k <= 90) && !e.shiftKey) ||
					((k >= 97 && k <= 122) && e.shiftKey)) ? true : false;

				// restrict input - keyCode in keypress special keys:
				// see http://www.asquare.net/javascript/tests/KeyCode.html
				if (o.restrictInput) {
					// allow navigation keys to work - Chrome doesn't fire a keypress event (8 = bksp)
					if ( (e.which === 8 || e.which === 0) && $.inArray( e.keyCode, base.alwaysAllowed ) ) { return; }
					// quick key check
					if ($.inArray(k, layout.acceptedKeys) === -1) {
						e.preventDefault();
						// copy event object in case e.preventDefault() breaks when changing the type
						evt = $.extend({}, e);
						evt.type = $keyboard.events.inputRestricted;
						base.$el.trigger( evt, [ base, base.el ] );
						if ( $.isFunction(o.restricted) ) {
							o.restricted( evt, base, base.el );
						}
					}
				} else if ( (e.ctrlKey || e.metaKey) && (e.which === 97 || e.which === 99 || e.which === 118 ||
						(e.which >= 120 && e.which <=122)) ) {
					// Allow select all (ctrl-a:97), copy (ctrl-c:99), paste (ctrl-v:118) & cut (ctrl-x:120) &
					// redo (ctrl-y:121)& undo (ctrl-z:122); meta key for mac
					return;
				}
				// Mapped Keys - allows typing on a regular keyboard and the mapped key is entered
				// Set up a key in the layout as follows: 'm(a):label'; m = key to map, (a) = actual keyboard key
				// to map to (optional), ':label' = title/tooltip (optional)
				// example: \u0391 or \u0391(A) or \u0391:alpha or \u0391(A):alpha
				if (layout.hasMappedKeys && layout.mappedKeys.hasOwnProperty(str)){
					base.last.key = layout.mappedKeys[str];
					base.insertText( base.last.key );
					e.preventDefault();
				}
				base.checkMaxLength();

			})
			.bind('keyup' + base.namespace, function(e){
				base.last.virtual = false;
				switch (e.which) {
					// Insert tab key
					case 9 :
						// Added a flag to prevent from tabbing into an input, keyboard opening, then adding the tab to the keyboard preview
						// area on keyup. Sadly it still happens if you don't release the tab key immediately because keydown event auto-repeats
						if (base.tab && o.tabNavigation && !o.lockInput) {
							base.shiftActive = e.shiftKey;
							// when switching inputs, the tab keyaction returns false
							var notSwitching = $keyboard.keyaction.tab(base);
							base.tab = false;
							if (!notSwitching) { return false; }
						} else {
							e.preventDefault();
						}
						break;

					// Escape will hide the keyboard
					case 27:
						if (!o.ignoreEsc) {
							base.close( o.autoAccept && o.autoAcceptOnEsc ? 'true' : false );
						}
						return false;
				}

				// throttle the check combo function because fast typers will have an incorrectly positioned caret
				clearTimeout(base.throttled);
				base.throttled = setTimeout(function(){
					// fix error in OSX? see issue #102
					if (base.isVisible()) {
						base.checkCombos();
					}
				}, 100);

				base.checkMaxLength();
				// change callback is no longer bound to the input element as the callback could be
				// called during an external change event with all the necessary parameters (issue #157)
				base.$el.trigger( $keyboard.events.kbChange, [ base, base.el ] );
				base.last.val = base.$preview.val();

				if ($.isFunction(o.change)){
					o.change( $.Event( $keyboard.events.inputChange ), base, base.el );
					return false;
				}
			})
			.bind('keydown' + base.namespace, function(e){
				// prevent tab key from leaving the preview window
				if ( e.which === 9 ) {
					// allow tab to pass through - tab to next input/shift-tab for prev
					base.tab = true;
					return false;
				}

				if ( o.lockInput ) { return false; }
				base.last.virtual = false;
				switch (e.which) {

					case 8 :
						$keyboard.keyaction.bksp(base, null, e);
						e.preventDefault();
						break;

					case 13:
						$keyboard.keyaction.enter(base, null, e);
						break;

					// Show capsLock
					case 20:
						base.shiftActive = base.capsLock = !base.capsLock;
						base.showSet();
						break;

					case 86:
						// prevent ctrl-v/cmd-v
						if (e.ctrlKey || e.metaKey) {
							if (o.preventPaste) { e.preventDefault(); return; }
							base.checkCombos(); // check pasted content
						}
						break;
				}
			})
			.bind('mouseup touchend '.split(' ').join(base.namespace + ' '), function(){
				base.last.virtual = true;
				if (base.checkCaret) {
					base.saveCaret();
				}
			});

		// prevent keyboard event bubbling
		base.$keyboard.bind('mousedown click touchstart '.split(' ').join(base.namespace + ' '), function(e){
			e.stopPropagation();
			if (!base.isCurrent()) {
				base.reveal();
				$(document).trigger('checkkeyboard' + base.namespace);
			}
			if (!o.noFocus) { base.$preview.focus(); }
		});

		// If preventing paste, block context menu (right click)
		if (o.preventPaste){
			base.$preview.bind('contextmenu' + base.namespace, function(e){ e.preventDefault(); });
			base.$el.bind('contextmenu' + base.namespace, function(e){ e.preventDefault(); });
		}

	};

	base.bindKeys = function(){
		var kbcss = $keyboard.css;
		base.$allKeys = base.$keyboard.find('button.' + kbcss.keyButton)
			.unbind(base.namespace + ' ' + base.namespace + 'kb')
			.bind(o.keyBinding.split(' ').join(base.namespace + ' ') + base.namespace + ' ' + $keyboard.events.kbRepeater, function(e){
				e.preventDefault();
				// prevent errors when external triggers attempt to 'type' - see issue #158
				if (!base.$keyboard.is(':visible')){ return false; }
				var action, $keys,
					last = base.last,
					key = this,
					$key = $(key),
					// prevent mousedown & touchstart from both firing events at the same time - see #184
					timer = new Date().getTime();

				if ( o.useWheel && base.wheel ) {
					// get keys from other layers/keysets (shift, alt, meta, etc) that line up by data-position
					$keys = last.wheel_$Keys;
					// target mousewheel selected key
					$key = $keys ? $keys.eq( last.wheelIndex ) : $key;
				}
				action = $key.attr( 'data-action' );
				// don't split colon key. Fixes #264
				action = action === ':' ? ':' : (action || '').split(':')[0];
				if ( timer - ( last.eventTime || 0 ) < o.preventDoubleEventTime ) { return; }
				last.eventTime = timer;
				last.event = e;
				last.virtual = true;
				if (!o.noFocus) { base.$preview.focus(); }
				last.$key = $key;
				last.key = $key.attr('data-value');
				// Start caret in IE when not focused (happens with each virtual keyboard button click
				if (base.checkCaret) {
					$keyboard.caret( base.$preview, last );
				}
				if (action.match('meta')) { action = 'meta'; }
				// keyaction is added as a string, override original action & text
				if (action === last.key && typeof $keyboard.keyaction[ action ] === 'string' ) {
					last.key = action = $keyboard.keyaction[ action ];
				} else if (action in $keyboard.keyaction && $.isFunction($keyboard.keyaction[action])) {
					// stop processing if action returns false (close & cancel)
					if ( $keyboard.keyaction[ action ]( base, this, e ) === false ) { return false; }
					action = null; // prevent inserting action name
				}
				if (typeof action !== 'undefined' && action !== null) {
					last.key = $(this).hasClass(kbcss.keyAction) ? action : last.key;
					base.insertText( last.key );
					if (!base.capsLock && !o.stickyShift && !e.shiftKey) {
						base.shiftActive = false;
						base.showSet( $key.attr('data-name') );
					}
				}
				// set caret if caret moved by action function; also, attempt to fix issue #131
				$keyboard.caret( base.$preview, last );
				base.checkCombos();
				base.$el.trigger( $keyboard.events.kbChange, [ base, base.el ] );
				last.val = base.$preview.val();

				if ($.isFunction(o.change)){
					o.change( $.Event( $keyboard.events.inputChange ), base, base.el );
					// return false to prevent reopening keyboard if base.accept() was called
					return false;
				}

			})
			// Change hover class and tooltip
			.bind('mouseenter mouseleave touchstart '.split(' ').join(base.namespace + ' '), function( e ) {
				if (!base.isCurrent()) { return; }
				var $keys, txt,
					last = base.last,
					$this = $(this),
					type = e.type;

				if ( o.useWheel && base.wheel ) {
					$keys = base.getLayers( $this );
					txt = ( $keys.length ? $keys.map( function() {
						return $( this ).attr( 'data-value' ) || '';
					}).get() : '' ) || [ $this.text() ];
					last.wheel_$Keys = $keys;
					last.wheelLayers = txt;
					last.wheelIndex = $.inArray( $this.attr( 'data-value' ), txt );
				}

				if ((type === 'mouseenter' || type === 'touchstart') && base.el.type !== 'password' &&
					!$this.hasClass(o.css.buttonDisabled) ) {
					$this.addClass(o.css.buttonHover);
					if ( o.useWheel && base.wheel ) {
						$this.attr( 'title', function( i, t ) {
							// show mouse wheel message
							return ( base.wheel && t === '' && base.sets && txt.length > 1 && type !== 'touchstart' ) ?
								o.wheelMessage : t;
						});
					}
				}
				if ( type === 'mouseleave' ) {
					// needed or IE flickers really bad
					$this.removeClass( (base.el.type === 'password') ? '' : o.css.buttonHover);
					if ( o.useWheel && base.wheel ) {
						last.wheelIndex = 0;
						last.wheelLayers = [];
						last.wheel_$Keys = null;
						$this
							.attr( 'title', function( i, t ){ return ( t === o.wheelMessage ) ? '' : t; })
							.html( $this.attr('data-html') ); // restore original button text
					}
				}
			})
			// using 'kb' namespace for mouse repeat functionality to keep it separate
			// I need to trigger a 'repeater.keyboard' to make it work
			.bind('mouseup' + base.namespace + ' ' + 'mouseleave touchend touchmove touchcancel '.split(' ').join(base.namespace + 'kb '), function(e){
				base.last.virtual = true;
				var offset,
					$this = $(this);
				if (e.type === 'touchmove') {
					// if moving within the same key, don't stop repeating
					offset = $this.offset();
					offset.right = offset.left + $this.outerWidth();
					offset.bottom = offset.top + $this.outerHeight();
					if (e.originalEvent.touches[0].pageX >= offset.left &&
						e.originalEvent.touches[0].pageX < offset.right &&
						e.originalEvent.touches[0].pageY >= offset.top &&
						e.originalEvent.touches[0].pageY < offset.bottom) {
						return true;
					}
				} else if (/(mouseleave|touchend|touchcancel)/i.test(e.type)) {
					$this.removeClass(o.css.buttonHover); // needed for touch devices
				} else {
					if (!o.noFocus && base.isVisible() && base.isCurrent()) { base.$preview.focus(); }
					if (base.checkCaret) {
						$keyboard.caret( base.$preview, base.last );
					}
				}
				base.mouseRepeat = [false,''];
				clearTimeout(base.repeater); // make sure key repeat stops!
				return false;
			})
			// prevent form submits when keyboard is bound locally - issue #64
			.bind('click' + base.namespace, function(){
				return false;
			})
			// no mouse repeat for action keys (shift, ctrl, alt, meta, etc)
			.not( '.' + kbcss.keyAction )
			// Allow mousewheel to scroll through other keysets of the same (non-action) key
			.bind( 'mousewheel' + base.namespace, function( e, delta ) {
				if ( o.useWheel && base.wheel ) {
					// deltaY used by newer versions of mousewheel plugin
					delta = delta || e.deltaY;
					var n,
						txt = base.last.wheelLayers || [];
					if ( txt.length > 1 ) {
						n = base.last.wheelIndex + ( delta > 0 ? -1 : 1 );
						if ( n > txt.length-1) { n = 0; }
						if ( n < 0 ) { n = txt.length-1; }
					} else {
						n = 0;
					}
					base.last.wheelIndex = n;
					$( this ).html( txt[ n ] );
					return false;
				}
			})
			// mouse repeated action key exceptions
			.add('.' + kbcss.keyPrefix + ('tab bksp space enter'.split(' ').join(',.' + kbcss.keyPrefix)), base.$keyboard)
			.bind('mousedown touchstart '.split(' ').join(base.namespace + 'kb '), function(){
				if (o.repeatRate !== 0) {
					var key = $(this);
					base.mouseRepeat = [true, key]; // save the key, make sure we are repeating the right one (fast typers)
					setTimeout(function() {
						if (base.mouseRepeat[0] && base.mouseRepeat[1] === key) { base.repeatKey(key); }
					}, o.repeatDelay);
				}
				return false;
			});
	};

	// Insert text at caret/selection - thanks to Derek Wickwire for fixing this up!
	base.insertText = function(txt){
		if ( typeof txt === 'undefined' ) { return; }
		var bksp, t,
			isBksp = txt === '\b',
			// use base.$preview.val() instead of base.preview.value (val.length includes carriage returns in IE).
			val = base.$preview.val(),
			pos = $keyboard.caret( base.$preview ),
			len = val.length; // save original content length

		// silly IE caret hacks... it should work correctly, but navigating using arrow keys in a textarea
		// is still difficult
		// in IE, pos.end can be zero after input loses focus
		if (pos.end < pos.start) { pos.end = pos.start; }
		if (pos.start > len) { pos.end = pos.start = len; }

		if (base.preview.nodeName === 'TEXTAREA') {
			// This makes sure the caret moves to the next line after clicking on enter (manual typing works fine)
			if ($keyboard.msie && val.substr(pos.start, 1) === '\n') { pos.start += 1; pos.end += 1; }
		}

		if (txt === '{d}') {
			txt = '';
			t = pos.start;
			pos.end += 1;
		}

		bksp = isBksp && pos.start === pos.end;
		txt = isBksp ? '' : txt;
		val = val.substr(0, pos.start - (bksp ? 1 : 0)) + txt + val.substr(pos.end);
		t = pos.start + (bksp ? -1 : txt.length);

		base.$preview.val( val );
		base.saveCaret( t, t ); // save caret in case of bksp
		base.setScroll();
	};

	// check max length
	base.checkMaxLength = function(){
		var start, caret,
			val = base.$preview.val();
		if (o.maxLength !== false && val.length > o.maxLength) {
			start = $keyboard.caret( base.$preview ).start;
			caret = Math.min(start, o.maxLength);

			// prevent inserting new characters when maxed #289
			if (!o.maxInsert) {
				val = base.last.val;
				caret = start - 1; // move caret back one
			}

			base.$preview.val( val.substring(0, o.maxLength) );
			// restore caret on change, otherwise it ends up at the end.
			base.saveCaret( caret, caret );
		}
		if (base.$decBtn.length) {
			base.checkDecimal();
		}
	};

	// mousedown repeater
	base.repeatKey = function(key){
		key.trigger( $keyboard.events.kbRepeater );
		if (base.mouseRepeat[0]) {
			base.repeater = setTimeout(function() {
				base.repeatKey(key);
			}, base.repeatTime);
		}
	};

	// make it easier to switch keysets via API
	// showKeySet('shift+alt+meta1')
	base.showKeySet = function(str) {
		if (typeof str === 'string') {
			base.last.keyset = [ base.shiftActive, base.altActive, base.metaActive ];
			base.shiftActive = /shift/i.test(str);
			base.altActive = /alt/i.test(str);
			if (/meta/.test(str)) {
				base.metaActive = true;
				base.showSet( str.match(/meta\d+/i)[0] );
			} else {
				base.metaActive = false;
				base.showSet();
			}
		} else {
			base.showSet( str );
		}
	};

	base.showSet = function( name ) {
		o = base.options; // refresh options
		var kbcss = $keyboard.css,
			key = '',
			toShow = (base.shiftActive ? 1 : 0) + (base.altActive ? 2 : 0);
		if (!base.shiftActive) { base.capsLock = false; }
		// check meta key set
		if (base.metaActive) {
			// the name attribute contains the meta set # 'meta99'
			key = (/meta/i.test(name)) ? name : '';
			// save active meta keyset name
			if (key === '') {
				key = (base.metaActive === true) ? '' : base.metaActive;
			} else {
				base.metaActive = key;
			}
			// if meta keyset doesn't have a shift or alt keyset, then show just the meta key set
			if ( (!o.stickyShift && base.last.keyset[2] !== base.metaActive) ||
				( (base.shiftActive || base.altActive) && !base.$keyboard.find('.' + kbcss.keySet + '-' + key +
					base.rows[toShow]).length) ) {
				base.shiftActive = base.altActive = false;
			}
		} else if (!o.stickyShift && base.last.keyset[2] !== base.metaActive && base.shiftActive) {
			// switching from meta key set back to default, reset shift & alt if using stickyShift
			base.shiftActive = base.altActive = false;
		}
		toShow = (base.shiftActive ? 1 : 0) + (base.altActive ? 2 : 0);
		key = (toShow === 0 && !base.metaActive) ? '-normal' : (key === '') ? '' : '-' + key;
		if (!base.$keyboard.find('.' + kbcss.keySet + key + base.rows[toShow]).length) {
			// keyset doesn't exist, so restore last keyset settings
			base.shiftActive = base.last.keyset[0];
			base.altActive = base.last.keyset[1];
			base.metaActive = base.last.keyset[2];
			return;
		}
		base.$keyboard
			.find('.' + kbcss.keyPrefix + 'alt,.' + kbcss.keyPrefix + 'shift,.' + kbcss.keyAction + '[class*=meta]')
				.removeClass(o.css.buttonActive).end()
			.find('.' + kbcss.keyPrefix + 'alt').toggleClass( o.css.buttonActive, base.altActive ).end()
			.find('.' + kbcss.keyPrefix + 'shift').toggleClass( o.css.buttonActive, base.shiftActive ).end()
			.find('.' + kbcss.keyPrefix + 'lock').toggleClass( o.css.buttonActive, base.capsLock ).end()
			.find('.' + kbcss.keySet).hide().end()
			.find('.' + kbcss.keySet + key + base.rows[toShow]).show().end()
			.find('.' + kbcss.keyAction + '.' + kbcss.keyPrefix + key).addClass(o.css.buttonActive);
		base.last.keyset = [ base.shiftActive, base.altActive, base.metaActive ];
		base.$el.trigger( $keyboard.events.kbKeysetChange, [ base, base.el ] );
	};

	// check for key combos (dead keys)
	base.checkCombos = function(){
		if (!base.isVisible()) { return base.$preview.val(); }
		var i, r, t, t2,
			// use base.$preview.val() instead of base.preview.value (val.length includes carriage returns in IE).
			val = base.$preview.val(),
			pos = $keyboard.caret( base.$preview ),
			layout = $keyboard.builtLayouts[base.layout],
			len = val.length; // save original content length
		// return if val is empty; fixes #352
		if (val === '') { return val; }

		// silly IE caret hacks... it should work correctly, but navigating using arrow keys in a textarea
		// is still difficult
		// in IE, pos.end can be zero after input loses focus
		if (pos.end < pos.start) { pos.end = pos.start; }
		if (pos.start > len) { pos.end = pos.start = len; }
		// This makes sure the caret moves to the next line after clicking on enter (manual typing works fine)
		if ($keyboard.msie && val.substr(pos.start, 1) === '\n') { pos.start += 1; pos.end += 1; }

		if (o.useCombos) {
			// keep 'a' and 'o' in the regex for ae and oe ligature (æ,œ)
			// thanks to KennyTM: http://stackoverflow.com/q/4275077
			// original regex /([`\'~\^\"ao])([a-z])/mig moved to $.keyboard.comboRegex
			if ($keyboard.msie) {
				// old IE may not have the caret positioned correctly, so just check the whole thing
				val = val.replace(base.regex, function(s, accent, letter){
					return (o.combos.hasOwnProperty(accent)) ? o.combos[accent][letter] || s : s;
				});
			// prevent combo replace error, in case the keyboard closes - see issue #116
			} else if (base.$preview.length) {
				// Modern browsers - check for combos from last two characters left of the caret
				t = pos.start - (pos.start - 2 >= 0 ? 2 : 0);
				// target last two characters
				$keyboard.caret( base.$preview, t, pos.end );
				// do combo replace
				t2 = ($keyboard.caret( base.$preview ).text || '').replace(base.regex, function(s, accent, letter){
					return (o.combos.hasOwnProperty(accent)) ? o.combos[accent][letter] || s : s;
				});
				// add combo back
				base.$preview.val( $keyboard.caret( base.$preview ).replaceStr(t2) );
				val = base.$preview.val();
			}
		}

		// check input restrictions - in case content was pasted
		if (o.restrictInput && val !== '') {
			t = val;
			r = layout.acceptedKeys.length;
			for (i=0; i < r; i++){
				if (t === '') { continue; }
				t2 = layout.acceptedKeys[i];
				if (val.indexOf(t2) >= 0) {
					// escape out all special characters
					if (/[\[|\]|\\|\^|\$|\.|\||\?|\*|\+|\(|\)|\{|\}]/g.test(t2)) { t2 = '\\' + t2; }
					t = t.replace( (new RegExp(t2, 'g')), '');
				}
			}
			// what's left over are keys that aren't in the acceptedKeys array
			if (t !== '') { val = val.replace(t, ''); }
		}

		// save changes, then reposition caret
		pos.start += val.length - len;
		pos.end += val.length - len;
		base.$preview.val(val);
		base.saveCaret( pos.start, pos.end );
		// set scroll to keep caret in view
		base.setScroll();

		base.checkMaxLength();

		if (o.acceptValid) { base.checkValid(); }

		return val; // return text, used for keyboard closing section
	};

	// Toggle accept button classes, if validating
	base.checkValid = function(){
		var kbcss = $keyboard.css,
			valid = true;
		if ($.isFunction(o.validate)) {
			valid = o.validate(base, base.$preview.val(), false);
		}
		// toggle accept button classes; defined in the css
		base.$keyboard.find('.' + kbcss.keyPrefix + 'accept')
			.toggleClass( kbcss.inputInvalid, !valid )
			.toggleClass( kbcss.inputValid, valid );

	};

	// Decimal button for num pad - only allow one (not used by default)
	base.checkDecimal = function(){
		// Check US '.' or European ',' format
		if ( ( base.decimal && /\./g.test(base.preview.value) ) ||
			( !base.decimal && /\,/g.test(base.preview.value) ) ) {
			base.$decBtn
				.attr({ 'disabled': 'disabled', 'aria-disabled': 'true' })
				.removeClass(o.css.buttonHover)
				.addClass(o.css.buttonDisabled);
		} else {
			base.$decBtn
				.removeAttr('disabled')
				.attr({ 'aria-disabled': 'false' })
				.addClass(o.css.buttonDefault)
				.removeClass(o.css.buttonDisabled);
		}
	};

	// get other layer values for a specific key
	base.getLayers = function($el){
		var kbcss = $keyboard.css,
			key = $el.attr('data-pos'),
			$keys = $el.closest('.' + kbcss.keyboard).find('button[data-pos="' + key + '"]');
		return $keys.filter(function(){ return $(this).find('.' + kbcss.keyText).text() !== ''; }).add($el);
	};

	// Go to next or prev inputs
	// goToNext = true, then go to next input; if false go to prev
	// isAccepted is from autoAccept option or true if user presses shift+enter
	base.switchInput = function(goToNext, isAccepted){
		if ($.isFunction(o.switchInput)) {
			o.switchInput(base, goToNext, isAccepted);
		} else {
			// base.$keyboard may be an empty array - see #275 (apod42)
			if (base.$keyboard.length) {
				base.$keyboard.hide();
			}
			var kb, stopped = false,
				all = $('button, input, textarea, a').filter(':visible').not(':disabled'),
				indx = all.index(base.$el) + (goToNext ? 1 : -1);
			if (base.$keyboard.length) {
				base.$keyboard.show();
			}
			if (indx > all.length - 1) {
				stopped = o.stopAtEnd;
				indx = 0; // go to first input
			}
			if (indx < 0) {
				stopped = o.stopAtEnd;
				indx = all.length - 1; // stop or go to last
			}
			if (!stopped) {
				isAccepted = base.close(isAccepted);
				if (!isAccepted) { return; }
				kb = all.eq(indx).data('keyboard');
				if (kb && kb.options.openOn.length) {
					kb.focusOn();
				} else {
					all.eq(indx).focus();
				}
			}
		}
		return false;
	};

	// Close the keyboard, if visible. Pass a status of true, if the content was accepted
	// (for the event trigger).
	base.close = function(accepted){
		if (base.isOpen) {
			clearTimeout(base.throttled);
			var kbcss = $keyboard.css,
				kbevents = $keyboard.events,
				val = (accepted) ?  base.checkCombos() : base.originalContent;
			// validate input if accepted
			if (accepted && $.isFunction(o.validate) && !o.validate(base, val, true)) {
				val = base.originalContent;
				accepted = false;
				if (o.cancelClose) { return; }
			}
			base.isCurrent(false);
			base.isOpen = false;
			// update value for always open keyboards
			base.$preview.val(val);

			base.$el
				.removeClass(kbcss.isCurrent + ' ' + kbcss.inputAutoAccepted)
				// add 'ui-keyboard-autoaccepted' to inputs - see issue #66
				.addClass( (accepted || false) ? accepted === true ? '' : kbcss.inputAutoAccepted : '' )
				.val( val )
				// trigger default change event - see issue #146
				.trigger(kbevents.inputChange)
				// don't trigger beforeClose if keyboard is always open
				.trigger( (o.alwaysOpen) ? '' : kbevents.kbBeforeClose, [ base, base.el, (accepted || false) ] )

				.trigger( ((accepted || false) ? kbevents.inputAccepted : kbevents.inputCanceled), [ base, base.el ] )
				.trigger( (o.alwaysOpen) ? kbevents.kbInactive : kbevents.kbHidden, [ base, base.el ] )
				.blur();

			// base is undefined if keyboard was destroyed - fixes #358
			if ( base ) {
				// add close event time
				base.last.eventTime = new Date().getTime();

				if (o.openOn) {
					// rebind input focus - delayed to fix IE issue #72
					base.timer = setTimeout(function(){
						// make sure keyboard isn't destroyed
						// Check if base exists, this is a case when destroy is called, before timers have fired
						if ( base && base.el.active ) {
							base.$el.bind( o.openOn + base.namespace, function(){ base.focusOn(); });
							// remove focus from element (needed for IE since blur doesn't seem to work)
							if ($(':focus')[0] === base.el) { base.$el.blur(); }
						}
					}, 500);
				}
				if (!o.alwaysOpen && base.$keyboard) {
					// free up memory
					base.$keyboard.remove();
					base.$keyboard = [];
				}
				if (!base.watermark && base.el.value === '' && base.inPlaceholder !== '') {
					base.$el
						.addClass(kbcss.placeholder)
						.val(base.inPlaceholder);
				}
			}
		}
		return !!accepted;
	};

	base.accept = function(){
		return base.close(true);
	};

	base.escClose = function(e){
		if ( e && e.type === 'keyup' ) {
			return ( e.which === 27 && !o.ignoreEsc ) ? base.close( o.autoAccept && o.autoAcceptOnEsc ? 'true' : false ) : '';
		}
		// keep keyboard open if alwaysOpen or stayOpen is true - fixes mutliple always open keyboards or
		// single stay open keyboard
		if ( !base.isOpen ) { return; }
		// ignore autoaccept if using escape - good idea?
		if ( !base.isCurrent() && base.isOpen || base.isOpen && e.target !== base.el ) {
			// don't close if stayOpen is set; but close if a different keyboard is being opened
			if (o.stayOpen && !$(e.target).hasClass('ui-keyboard-input')) {
				return;
			}
			// stop propogation in IE - an input getting focus doesn't open a keyboard if one is already open
			if ( $keyboard.allie ) {
				e.preventDefault();
			}
			// send 'true' instead of a true (boolean), the input won't get a 'ui-keyboard-autoaccepted'
			// class name - see issue #66
			base.close( o.autoAccept ? 'true' : false );
		}
	};

	// Build default button
	base.keyBtn = $('<button />')
		.attr({ 'role': 'button', 'type': 'button', 'aria-disabled': 'false', 'tabindex' : '-1' })
		.addClass( $keyboard.css.keyButton );

	base.processName = function( name ) {
		var index, n,
			process = ( name || '' ).replace( /[^a-z0-9-_]/gi, '' ),
			len = process.length,
			newName = [];
		if ( len > 1 && name === process ) {
			// return name if basic text
			return name;
		}
		// return character code sequence
		len = name.length;
		if ( len ) {
			for ( index = 0; index < len; index++ ) {
				n = name[ index ];
				// keep '-' as a dash, but don't add it or we get two dashes in a row
				newName.push( /[a-z0-9-_]/i.test( n ) ? ( /[-_]/.test(n) ? '' : n ) :
					( index === 0 ? '' : '-' ) + n.charCodeAt( 0 ) );
			}
			return newName.join( '' );
		} else {
			return name;
		}
	};

	// Add key function
	// keyName = the name of the function called in $.keyboard.keyaction when the button is clicked
	// name = name added to key, or cross-referenced in the display options
	// base.temp[0] = keyset to attach the new button
	// regKey = true when it is not an action key
	base.addKey = function( keyName, name, regKey ) {
		var keyClass, m, map, nm,
			kbcss = $keyboard.css,
			txt = name.split(':'),
			len = txt.length - 1,
			n = (regKey === true) ? keyName : o.display[txt[0]] || keyName,
			data = {
				isAction : !regKey,
				action   : keyName,
				name     : base.processName( keyName.split(/[(:]/)[0] )
			};
		// map defined keys - format 'key(A):Label_for_key'
		// 'key' = key that is seen (can any character; but it might need to be escaped using '\'
		//  or entered as unicode '\u####'
		// '(A)' = the actual key on the real keyboard to remap, ':Label_for_key' ends up in the title/tooltip
		if (/\(.+\)/.test(n)) { // n = '\u0391(A):alpha'
			map = n.replace(/\(([^()]+)\)/, ''); // remove '(A)', left with '\u0391:alpha'
			m = n.match(/\(([^()]+)\)/)[1]; // extract 'A' from '(A)'
			n = map;
			nm = map.split(':');
			map = (nm[0] !== '' && nm.length > 1) ? nm[0] : map; // get '\u0391' from '\u0391:alpha'
			$keyboard.builtLayouts[base.layout].mappedKeys[m] = map;
		}

		// find key label
		nm = n.split(':');
		// corner case of ':(:):;' reduced to '::;', split as ['', '', ';']
		if (nm[0] === '' && nm[1] === '') { n = ':'; }
		n = (nm[0] !== '' && nm.length > 1) ? nm[0] : n;
		// allow alt naming of action keys
		data.value = $.trim( regKey ? n : txt[1] || n );
		// added to title
		data.title = (nm.length > 1) ? $.trim(nm[1]).replace(/_/g, ' ') || '' : len > 0 ? txt[len] || '' : '';
                
                data.code = data.value.charCodeAt(0);

		// Action keys will have the 'ui-keyboard-actionkey' class
		// '\u2190'.length = 1 because the unicode is converted, so if more than one character,
		// add the wide class
		keyClass = ( data.value.length > 2 ) ? ' ' + kbcss.keyWide : '';
		keyClass += ( regKey ) ? '' : ' ' + kbcss.keyAction;

		data.html = '<span class="' + kbcss.keyText + '">' +
			// this prevents HTML from being added to the key
			data.value.replace(/[\u00A0-\u9999<>\&]/gim, function(i) {
				return '&#'+i.charCodeAt(0)+';';
			}) +
			'</span>';

		data.$key = base.keyBtn
			.clone()
			.attr({
				'data-value'  : data.value, // value
				'data-name'   : data.name,
				'data-pos'    : base.temp[1] + ',' + base.temp[2],
				'title'       : data.title,
				'data-action' : data.action,
				'data-html'   : data.html,
                                'data-code'   : data.code
			})
			// add 'ui-keyboard-' + data.name for all keys
			//  (e.g. 'Bksp' will have 'ui-keyboard-bskp' class)
			// any non-alphanumeric characters will be replaced with
			//  their decimal unicode value
			//  (e.g. '~' is a regular key, class = 'ui-keyboard-126'
			//  (126 is the unicode decimal value - same as &#126;)
			//  See https://en.wikipedia.org/wiki/List_of_Unicode_characters#Control_codes
			.addClass( ( data.name === '' ? '' : kbcss.keyPrefix + data.name + keyClass + ' ' ) + o.css.buttonDefault )
			.html( data.html )
			.appendTo( base.temp[0] );

		if ( typeof o.buildKey === 'function' ) {
			data = o.buildKey( base, data );
			// copy html back to attributes
			txt = data.$key.html();
			data.$key.attr( 'data-html', txt );
		}
		return data.$key;
	};

	base.customHash = function( layout ) {
		/*jshint bitwise:false */
		var i, array, hash, character, len,
			arrays = [], merged = [];
		// pass layout to allow for testing
		layout = typeof layout === 'undefined' ? o.customLayout : layout;
		// get all layout arrays
		for ( array in layout ) {
			if ( layout.hasOwnProperty( array ) ) {
				arrays.push( layout[ array ] );
			}
		}
		// flatten array
		merged = merged.concat.apply( merged, arrays ).join( ' ' );
		// produce hash name - http://stackoverflow.com/a/7616484/145346
		hash = 0;
		len = merged.length;
		if ( len === 0 ) { return hash; }
		for ( i = 0; i < len; i++ ) {
			character = merged.charCodeAt( i );
			hash = ( ( hash<<5 ) - hash ) + character;
			hash = hash & hash; // Convert to 32bit integer
		}
		return hash;
	};

	base.buildKeyboard = function(name, internal) {
		// o.display is empty when this is called from the scramble extension (when alwaysOpen:true)
		if ( $.isEmptyObject(o.display) ) {
			// set keyboard language
			base.updateLanguage();
		}
		var row, $row, currentSet,
			kbcss = $keyboard.css,
			sets = 0,
			layout = $keyboard.builtLayouts[name || base.layout || o.layout] = {
				mappedKeys   : {},
				acceptedKeys : []
			},
			acceptedKeys = layout.acceptedKeys = [],
			// using $layout temporarily to hold keyboard popup classnames
			$layout = kbcss.keyboard + ' ' + o.css.popup + ' ' + o.css.container +
				( o.alwaysOpen ? ' ' + kbcss.alwaysOpen : '' ),

		container = $('<div />')
			.addClass( $layout )
			.attr({ 'role': 'textbox' })
			.hide();
		// verify layout or setup custom keyboard
		if ( ( internal && o.layout === 'custom' ) || !$keyboard.layouts.hasOwnProperty(o.layout) ) {
			o.layout = 'custom';
			$layout = $keyboard.layouts.custom = o.customLayout || { 'normal' : ['{cancel}'] };
		} else {
			$layout = $keyboard.layouts[ internal ? o.layout : name || base.layout || o.layout ];
		}

		// Main keyboard building loop
		$.each($layout, function(set, keySet) {
			// skip layout name & lang settings
			if (set !== '' && !/^(name|lang|rtl)$/i.test(set)) {
				// keep backwards compatibility for change from default to normal naming
				if (set === 'default') { set = 'normal'; }
				sets++;
				$row = $('<div />')
					.attr('name', set) // added for typing extension
					.addClass( kbcss.keySet + ' ' + kbcss.keySet + '-' + set)
					.appendTo(container)
					.toggle( set === 'normal' );

				for ( row = 0; row < keySet.length; row++ ) {
					// remove extra spaces before spliting (regex probably could be improved)
					currentSet = $.trim(keySet[row]).replace(/\{(\.?)[\s+]?:[\s+]?(\.?)\}/g,'{$1:$2}');
					base.buildRow( $row, row, currentSet.split(/\s+/), acceptedKeys );
					$row.find('.' + kbcss.keyButton + ':last').after('<br class="' + kbcss.endRow + '"/>');
				}
			}
		});

		if (sets > 1) { base.sets = true; }
		layout.hasMappedKeys = !( $.isEmptyObject(layout.mappedKeys) ); // $.isEmptyObject() requires jQuery 1.4+
		layout.$keyboard = container;
		return container;
	};

	base.buildRow = function( $row, row, keys, acceptedKeys ) {
		var t, txt, key, isAction, action, margin,
			kbcss = $keyboard.css;
		for ( key = 0; key < keys.length; key++ ) {
			// used by addKey function
			base.temp = [ $row, row, key ];
			isAction = false;

			// ignore empty keys
			if (keys[key].length === 0) { continue; }

			// process here if it's an action key
			if (/^\{\S+\}$/.test(keys[key])) {
				action = keys[key].match(/^\{(\S+)\}$/)[1];
				// add active class if there are double exclamation points in the name
				if (/\!\!/.test(action)) {
					action = action.replace('!!', '');
					isAction = true;
				}

				// add empty space
				if (/^sp:((\d+)?([\.|,]\d+)?)(em|px)?$/i.test(action)) {
					// not perfect globalization, but allows you to use {sp:1,1em}, {sp:1.2em} or {sp:15px}
					margin = parseFloat( action
						.replace(/,/, '.')
						.match(/^sp:((\d+)?([\.|,]\d+)?)(em|px)?$/i)[1] || 0
					);
					$('<span class="' + kbcss.keyText + '"></span>')
						// previously {sp:1} would add 1em margin to each side of a 0 width span
						// now Firefox doesn't seem to render 0px dimensions, so now we set the
						// 1em margin x 2 for the width
						.width( (action.match(/px/i) ? margin + 'px' : (margin * 2) + 'em') )
						.addClass( kbcss.keySpacer )
						.appendTo($row);
				}

				// add empty button
				if (/^empty(:((\d+)?([\.|,]\d+)?)(em|px)?)?$/i.test(action)) {
					margin = (/:/.test(action)) ? parseFloat( action
						.replace(/,/,'.')
						.match(/^empty:((\d+)?([\.|,]\d+)?)(em|px)?$/i)[1] || 0
					) : '';
					base
						.addKey('', ' ')
						.addClass(o.css.buttonDisabled + ' ' + o.css.buttonEmpty)
						.attr('aria-disabled', true)
						.width( margin ? (action.match('px') ? margin + 'px' : (margin * 2) + 'em') : '' );
				}

				// meta keys
				if (/^meta\d+\:?(\w+)?/i.test(action)) {
					base
						.addKey(action.split(':')[0], action)
						.addClass( kbcss.keyHasActive );
					continue;
				}

				// switch needed for action keys with multiple names/shortcuts or
				// default will catch all others
				txt = action.split(':');
				switch(txt[0].toLowerCase()) {

					case 'a':
					case 'accept':
						base
							.addKey('accept', action)
							.addClass(o.css.buttonAction + ' ' + kbcss.keyAction);
						break;

					case 'alt':
					case 'altgr':
						base
							.addKey('alt', action)
							.addClass( kbcss.keyHasActive );
						break;

					case 'b':
					case 'bksp':
						base.addKey('bksp', action);
						break;

					case 'c':
					case 'cancel':
						base
							.addKey('cancel', action)
							.addClass(o.css.buttonAction + ' ' + kbcss.keyAction);
						break;

					// toggle combo/diacritic key
					case 'combo':
						base
							.addKey('combo', action)
							.addClass( kbcss.keyHasActive )
							.toggleClass(o.css.buttonActive, o.useCombos);
						break;

					// Decimal - unique decimal point (num pad layout)
					case 'dec':
						acceptedKeys.push((base.decimal) ? '.' : ',');
						base.addKey('dec', action);
						break;

					case 'e':
					case 'enter':
						base
							.addKey('enter', action)
							.addClass(o.css.buttonAction + ' ' + kbcss.keyAction);
						break;

					case 'lock':
						base
							.addKey('lock', action)
							.addClass( kbcss.keyHasActive );
						break;

					case 's':
					case 'shift':
						base
							.addKey('shift', action)
							.addClass( kbcss.keyHasActive );
						break;

					// Change sign (for num pad layout)
					case 'sign':
						acceptedKeys.push('-');
						base.addKey('sign', action);
						break;

					case 'space':
						acceptedKeys.push(' ');
						base.addKey('space', action);
						break;

					case 't':
					case 'tab':
						base.addKey('tab', action);
						break;

					default:
						if ($keyboard.keyaction.hasOwnProperty(txt[0])){
							// acceptedKeys.push(action);
							base
								.addKey(txt[0], action)
								.toggleClass( o.css.buttonAction + ' ' + kbcss.keyAction, isAction );
						}

				}

			} else {

				// regular button (not an action key)
				t = keys[key];
				acceptedKeys.push( t === ':' ? t : t.split(':')[0] );
				base.addKey(t, t, true);
			}
		}
	};

	base.removeBindings = function( namespace ) {
		$(document).unbind( namespace );
		$(window).unbind( namespace );
		base.$el.unbind( namespace );
	};

	base.destroy = function( callback ) {
		var index,
			kbcss = $keyboard.css,
			len = base.extensionNamespace.length,
			tmp = [ kbcss.input, kbcss.locked, kbcss.placeholder, kbcss.noKeyboard,
				kbcss.alwaysOpen, o.css.input ].join(' ');
		clearTimeout(base.timer);
		clearTimeout(base.timer2);
		base.removeBindings( base.namespace );
		for ( index = 0; index < len; index++ ) {
			base.removeBindings( base.extensionNamespace[ index ] );
		}
		base.el.active = false;
		if (base.$keyboard.length) {
			base.$keyboard.remove();
		}

		base.$el
			.removeClass( tmp )
			.removeAttr('aria-haspopup')
			.removeAttr('role')
			.removeData('keyboard');
		base = null;

		if ( typeof callback === 'function' ) {
			callback();
		}
	};

		// Run initializer
		base.init();
	};
	$keyboard.css = {
		// keyboard id suffix
		idSuffix: '_keyboard',
		// element class names
		input: 'ui-keyboard-input',
		wrapper: 'ui-keyboard-preview-wrapper',
		preview: 'ui-keyboard-preview',
		keyboard: 'ui-keyboard',
		keySet: 'ui-keyboard-keyset',
		keyButton: 'ui-keyboard-button',
		keyWide: 'ui-keyboard-widekey',
		keyPrefix: 'ui-keyboard-',
		keyText: 'ui-keyboard-text', // span with button text
		keyHasActive: 'ui-keyboard-hasactivestate',
		keyAction: 'ui-keyboard-actionkey',
		keySpacer: 'ui-keyboard-spacer', // empty keys
		keyToggle: 'ui-keyboard-toggle',
		keyDisabled: 'ui-keyboard-disabled',
		// states
		locked: 'ui-keyboard-lockedinput',
		alwaysOpen: 'ui-keyboard-always-open',
		noKeyboard: 'ui-keyboard-nokeyboard',
		placeholder: 'ui-keyboard-placeholder',
		hasFocus: 'ui-keyboard-has-focus',
		isCurrent: 'ui-keyboard-input-current',
		// validation & autoaccept
		inputValid: 'ui-keyboard-valid-input',
		inputInvalid: 'ui-keyboard-invalid-input',
		inputAutoAccepted: 'ui-keyboard-autoaccepted',
		endRow: 'ui-keyboard-button-endrow' // class added to <br>
	};

	$keyboard.events = {
		// keyboard events
		kbChange: 'keyboardChange',
		kbBeforeClose: 'beforeClose',
		kbBeforeVisible: 'beforeVisible',
		kbVisible: 'visible',
		kbInit: 'initialized',
		kbInactive: 'inactive',
		kbHidden: 'hidden',
		kbRepeater: 'repeater',
		kbKeysetChange: 'keysetChange',
		// input events
		inputAccepted: 'accepted',
		inputCanceled: 'canceled',
		inputChange: 'change',
		inputRestricted: 'restricted'
	};

	// Action key function list
	$keyboard.keyaction = {
		accept : function(base) {
			base.close(true); // same as base.accept();
			return false;     // return false prevents further processing
		},
		alt : function(base) {
			base.altActive = !base.altActive;
			base.showSet();
		},
		bksp : function(base) {
			// the script looks for the '\b' string and initiates a backspace
			base.insertText('\b');
		},
		cancel : function(base) {
			base.close();
			return false; // return false prevents further processing
		},
		clear : function(base) {
			base.$preview.val('');
			if (base.$decBtn.length) {
				base.checkDecimal();
			}
		},
		combo : function(base) {
			var c = !base.options.useCombos;
			base.options.useCombos = c;
			base.$keyboard.find('.' + $keyboard.css.keyPrefix + 'combo').toggleClass(base.options.css.buttonActive, c);
			if (c) { base.checkCombos(); }
			return false;
		},
		dec : function(base) {
			base.insertText((base.decimal) ? '.' : ',');
		},
		del : function(base) {
			// the script looks for the '{d}' string and initiates a delete
			base.insertText('{d}');
		},
		// resets to base keyset (deprecated because "default" is a reserved word)
		'default' : function(base) {
			base.shiftActive = base.altActive = base.metaActive = false;
			base.showSet();
		},
		// el is the pressed key (button) object; it is null when the real keyboard enter is pressed
		enter : function(base, el, e) {
			var tag = base.el.nodeName, o = base.options;
			// shift+enter in textareas
			if (e.shiftKey) {
				// textarea & input - enterMod + shift + enter = accept, then go to prev;
				//  base.switchInput(goToNext, autoAccept)
				// textarea & input - shift + enter = accept (no navigation)
				return (o.enterNavigation) ? base.switchInput(!e[o.enterMod], true) : base.close(true);
			}
			// input only - enterMod + enter to navigate
			if (o.enterNavigation && (tag !== 'TEXTAREA' || e[o.enterMod])) {
				return base.switchInput(!e[o.enterMod], o.autoAccept ? 'true' : false);
			}
			// pressing virtual enter button inside of a textarea - add a carriage return
			// e.target is span when clicking on text and button at other times
			if (tag === 'TEXTAREA' && $(e.target).closest('button').length) {
				base.insertText(' \n'); // IE8 fix (space + \n) - fixes #71 thanks Blookie!
			}
		},
		// caps lock key
		lock : function(base) {
			base.last.keyset[0] = base.shiftActive = base.capsLock = !base.capsLock;
			base.showSet();
		},
		left : function(base) {
			var p = $keyboard.caret( base.$preview );
			if (p.start - 1 >= 0) {
				// move both start and end of caret (prevents text selection) & save caret position
				base.last.start = base.last.end = p.start - 1;
				$keyboard.caret( base.$preview, base.last );
				base.setScroll();
			}
		},
		meta : function(base, el) {
			var $el = $(el);
			base.metaActive = !$el.hasClass( base.options.css.buttonActive );
			base.showSet( $el.attr( 'data-name' ) );
		},
		next : function(base) {
			base.switchInput(true, base.options.autoAccept);
			return false;
		},
		// same as 'default' - resets to base keyset
		normal : function(base) {
			base.shiftActive = base.altActive = base.metaActive = false;
			base.showSet();
		},
		prev : function(base) {
			base.switchInput(false, base.options.autoAccept);
			return false;
		},
		right : function(base) {
			var p = $keyboard.caret( base.$preview );
			if (p.start + 1 <= base.$preview.val().length) {
				// move both start and end of caret (prevents text selection) && save caret position
				base.last.start = base.last.end = p.start + 1;
				$keyboard.caret( base.$preview, base.last );
				base.setScroll();
			}
		},
		shift : function(base) {
			base.last.keyset[0] = base.shiftActive = !base.shiftActive;
			base.showSet();
		},
		sign : function(base) {
			if(/^\-?\d*\.?\d*$/.test( base.$preview.val() )) {
				base.$preview.val( (base.$preview.val() * -1) );
			}
		},
		space : function(base) {
			base.insertText(' ');
		},
		tab : function(base) {
			var tag = base.el.nodeName,
				o = base.options;
			if (tag === 'INPUT') {
				if (o.tabNavigation) {
					return base.switchInput(!base.shiftActive, true);
				} else {
					// ignore tab key in input
					return false;
				}
			}
			base.insertText('\t');
		},
		toggle : function(base) {
			base.enabled = !base.enabled;
			base.toggle();
		},
		// *** Special action keys: NBSP & zero-width characters ***
		// Non-breaking space
		NBSP : '\u00a0',
		// zero width space
		ZWSP : '\u200b',
		// Zero width non-joiner
		ZWNJ : '\u200c',
		// Zero width joiner
		ZWJ : '\u200d',
		// Left-to-right Mark
		LRM : '\u200e',
		// Right-to-left Mark
		RLM : '\u200f'
	};

	// Default keyboard layouts
	$keyboard.builtLayouts = {};
	$keyboard.layouts = {
		'alpha' : {
			'normal': [
				'` 1 2 3 4 5 6 7 8 9 0 - = {bksp}',
				'{tab} a b c d e f g h i j [ ] \\',
				'k l m n o p q r s ; \' {enter}',
				'{shift} t u v w x y z , . / {shift}',
				'{accept} {space} {cancel}'
			],
			'shift': [
				'~ ! @ # $ % ^ & * ( ) _ + {bksp}',
				'{tab} A B C D E F G H I J { } |',
				'K L M N O P Q R S : " {enter}',
				'{shift} T U V W X Y Z < > ? {shift}',
				'{accept} {space} {cancel}'
			]
		},
		'qwerty' : {
			'normal': [
				'` 1 2 3 4 5 6 7 8 9 0 - = {bksp}',
				'{tab} q w e r t y u i o p [ ] \\',
				'a s d f g h j k l ; \' {enter}',
				'{shift} z x c v b n m , . / {shift}',
				'{accept} {space} {cancel}'
			],
			'shift': [
				'~ ! @ # $ % ^ & * ( ) _ + {bksp}',
				'{tab} Q W E R T Y U I O P { } |',
				'A S D F G H J K L : " {enter}',
				'{shift} Z X C V B N M < > ? {shift}',
				'{accept} {space} {cancel}'
			]
		},
		'international' : {
			'normal': [
				'` 1 2 3 4 5 6 7 8 9 0 - = {bksp}',
				'{tab} q w e r t y u i o p [ ] \\',
				'a s d f g h j k l ; \' {enter}',
				'{shift} z x c v b n m , . / {shift}',
				'{accept} {alt} {space} {alt} {cancel}'
			],
			'shift': [
				'~ ! @ # $ % ^ & * ( ) _ + {bksp}',
				'{tab} Q W E R T Y U I O P { } |',
				'A S D F G H J K L : " {enter}',
				'{shift} Z X C V B N M < > ? {shift}',
				'{accept} {alt} {space} {alt} {cancel}'
			],
			'alt': [
				'~ \u00a1 \u00b2 \u00b3 \u00a4 \u20ac \u00bc \u00bd \u00be \u2018 \u2019 \u00a5 \u00d7 {bksp}',
				'{tab} \u00e4 \u00e5 \u00e9 \u00ae \u00fe \u00fc \u00fa \u00ed \u00f3 \u00f6 \u00ab \u00bb \u00ac',
				'\u00e1 \u00df \u00f0 f g h j k \u00f8 \u00b6 \u00b4 {enter}',
				'{shift} \u00e6 x \u00a9 v b \u00f1 \u00b5 \u00e7 > \u00bf {shift}',
				'{accept} {alt} {space} {alt} {cancel}'
			],
			'alt-shift': [
				'~ \u00b9 \u00b2 \u00b3 \u00a3 \u20ac \u00bc \u00bd \u00be \u2018 \u2019 \u00a5 \u00f7 {bksp}',
				'{tab} \u00c4 \u00c5 \u00c9 \u00ae \u00de \u00dc \u00da \u00cd \u00d3 \u00d6 \u00ab \u00bb \u00a6',
				'\u00c4 \u00a7 \u00d0 F G H J K \u00d8 \u00b0 \u00a8 {enter}',
				'{shift} \u00c6 X \u00a2 V B \u00d1 \u00b5 \u00c7 . \u00bf {shift}',
				'{accept} {alt} {space} {alt} {cancel}'
			]
		},
		'colemak' : {
			'normal': [
				'` 1 2 3 4 5 6 7 8 9 0 - = {bksp}',
				'{tab} q w f p g j l u y ; [ ] \\',
				'{bksp} a r s t d h n e i o \' {enter}',
				'{shift} z x c v b k m , . / {shift}',
				'{accept} {space} {cancel}'
			],
			'shift' : [
				'~ ! @ # $ % ^ & * ( ) _ + {bksp}',
				'{tab} Q W F P G J L U Y : { } |',
				'{bksp} A R S T D H N E I O " {enter}',
				'{shift} Z X C V B K M < > ? {shift}',
				'{accept} {space} {cancel}'
			]
		},
		'dvorak' : {
			'normal': [
				'` 1 2 3 4 5 6 7 8 9 0 [ ] {bksp}',
				'{tab} \' , . p y f g c r l / = \\',
				'a o e u i d h t n s - {enter}',
				'{shift} ; q j k x b m w v z {shift}',
				'{accept} {space} {cancel}'
			],
			'shift' : [
				'~ ! @ # $ % ^ & * ( ) { } {bksp}',
				'{tab} " < > P Y F G C R L ? + |',
				'A O E U I D H T N S _ {enter}',
				'{shift} : Q J K X B M W V Z {shift}',
				'{accept} {space} {cancel}'
			]
		},
		'num' : {
			'normal' : [
				'= ( ) {b}',
				'{clear} / * -',
				'7 8 9 +',
				'4 5 6 {sign}',
				'1 2 3 %',
				'0 {dec} {a} {c}'
			]
		}
	};

	$keyboard.language = $.extend({}, $keyboard.language, {
		en : {
			display : {
				// check mark - same action as accept
				'a'      : '\u2714:Accept (Shift+Enter)',
				'accept' : 'Aceptar:Accept (Shift+Enter)',
				// other alternatives \u2311
				'alt'    : 'Alt:\u2325 AltGr',
				// Left arrow (same as &larr;)
				'b'      : '\u232b:Backspace',
				'bksp'   : 'Borrar:Backspace',
				// big X, close - same action as cancel
				'c'      : '\u2716:Cancel (Esc)',
				'cancel' : 'Cancelar:Cancel (Esc)',
				// clear num pad
				'clear'  : 'C:Clear',
				'combo'  : '\u00f6:Toggle Combo Keys',
				// decimal point for num pad (optional), change '.' to ',' for European format
				'dec'    : '.:Decimal',
				// down, then left arrow - enter symbol
				'e'      : '\u23ce:Enter',
				'empty'  : '\u00a0',
				'enter'  : 'Intro:Enter \u23ce',
				// left arrow (move caret)
				'left'   : '\u2190',
				// caps lock
				'lock'   : 'Lock:\u21ea Caps Lock',
				'next'   : 'Siguiente \u21e8',
				'prev'   : '\u21e6 Anterior',
				// right arrow (move caret)
				'right'  : '\u2192',
				// thick hollow up arrow
				's'      : '\u21e7:Shift',
				'shift'  : 'Shift:Shift',
				// +/- sign for num pad
				'sign'   : '\u00b1:Change Sign',
				'space'  : '\u00a0:Space',
				// right arrow to bar (used since this virtual keyboard works with one directional tabs)
				't'      : '\u21e5:Tab',
				// \u21b9 is the true tab symbol (left & right arrows)
				'tab'    : '\u21e5 Tab:Tab',
				// replaced by an image
				'toggle' : ' '
			},

			// Message added to the key title while hovering, if the mousewheel plugin exists
			wheelMessage : 'Use mousewheel to see other keys',

			comboRegex : /([`\'~\^\"ao])([a-z])/mig,
			combos    : {
				// grave
				'`' : { a:'\u00e0', A:'\u00c0', e:'\u00e8', E:'\u00c8', i:'\u00ec', I:'\u00cc', o:'\u00f2', O:'\u00d2',
						u:'\u00f9', U:'\u00d9', y:'\u1ef3', Y:'\u1ef2' },
				// acute & cedilla
				"'" : { a:'\u00e1', A:'\u00c1', e:'\u00e9', E:'\u00c9', i:'\u00ed', I:'\u00cd', o:'\u00f3', O:'\u00d3',
						u:'\u00fa', U:'\u00da', y:'\u00fd', Y:'\u00dd' },
				// umlaut/trema
				'"' : { a:'\u00e4', A:'\u00c4', e:'\u00eb', E:'\u00cb', i:'\u00ef', I:'\u00cf', o:'\u00f6', O:'\u00d6',
						u:'\u00fc', U:'\u00dc', y:'\u00ff', Y:'\u0178' },
				// circumflex
				'^' : { a:'\u00e2', A:'\u00c2', e:'\u00ea', E:'\u00ca', i:'\u00ee', I:'\u00ce', o:'\u00f4', O:'\u00d4',
						u:'\u00fb', U:'\u00db', y:'\u0177', Y:'\u0176' },
				// tilde
				'~' : { a:'\u00e3', A:'\u00c3', e:'\u1ebd', E:'\u1ebc', i:'\u0129', I:'\u0128', o:'\u00f5', O:'\u00d5',
						u:'\u0169', U:'\u0168', y:'\u1ef9', Y:'\u1ef8', n:'\u00f1', N:'\u00d1' }
			}
		}
	});

	$keyboard.defaultOptions = {
		// set this to ISO 639-1 language code to override language set by the layout
		// http://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
		// language defaults to 'en' if not found
		language     : null,
		rtl          : false,

		// *** choose layout & positioning ***
		layout       : 'qwerty',
		customLayout : null,

		position     : {
			// optional - null (attach to input/textarea) or a jQuery object (attach elsewhere)
			of : null,
			my : 'center top',
			at : 'center top',
			// used when 'usePreview' is false (centers the keyboard at the bottom of the input/textarea)
			at2: 'center bottom'
		},

		// allow jQuery position utility to reposition the keyboard on window resize
		reposition   : true,

		// preview added above keyboard if true, original input/textarea used if false
		usePreview   : true,

		// if true, the keyboard will always be visible
		alwaysOpen   : false,

		// give the preview initial focus when the keyboard becomes visible
		initialFocus : true,

		// avoid changing the focus (hardware keyboard probably won't work)
		noFocus      : false,

		// if true, keyboard will remain open even if the input loses focus, but closes on escape
		// or when another keyboard opens.
		stayOpen     : false,

		// if true, keyboard will not close if you press escape.
		ignoreEsc    : false,

		css : {
			// input & preview
			input          : 'ui-widget-content ui-corner-all',
			// keyboard container
			container      : 'ui-widget-content ui-widget ui-corner-all ui-helper-clearfix',
			// keyboard container extra class (same as container, but separate)
			popup          : '',
			// default state
			buttonDefault  : 'ui-state-default ui-corner-all',
			// hovered button
			buttonHover    : 'ui-state-hover',
			// Action keys (e.g. Accept, Cancel, Tab, etc); this replaces 'actionClass' option
			buttonAction   : 'ui-state-active',
			// Active keys (e.g. shift down, meta keyset active, combo keys active)
			buttonActive   : 'ui-state-active',
			// used when disabling the decimal button {dec} when a decimal exists in the input area
			buttonDisabled : 'ui-state-disabled',
			buttonEmpty    : 'ui-keyboard-empty'
		},

		// *** Useability ***
		// Auto-accept content when clicking outside the keyboard (popup will close)
		autoAccept      : false,
		// Auto-accept content even if the user presses escape (only works if `autoAccept` is `true`)
		autoAcceptOnEsc : false,

		// Prevents direct input in the preview window when true
		lockInput    : false,

		// Prevent keys not in the displayed keyboard from being typed in
		restrictInput: false,

		// Check input against validate function, if valid the accept button gets a class name of
		// 'ui-keyboard-valid-input'. If invalid, the accept button gets a class name of
		// 'ui-keyboard-invalid-input'
		acceptValid  : false,

		// if acceptValid is true & the validate function returns a false, this option will cancel
		// a keyboard close only after the accept button is pressed
		cancelClose  : true,

		// tab to go to next, shift-tab for previous (default behavior)
		tabNavigation: false,

		// enter for next input; shift+enter accepts content & goes to next
		// shift + 'enterMod' + enter ('enterMod' is the alt as set below) will accept content and go
		// to previous in a textarea
		enterNavigation : false,
		// mod key options: 'ctrlKey', 'shiftKey', 'altKey', 'metaKey' (MAC only)
		enterMod : 'altKey', // alt-enter to go to previous; shift-alt-enter to accept & go to previous

		// if true, the next button will stop on the last keyboard input/textarea; prev button stops at first
		// if false, the next button will wrap to target the first input/textarea; prev will go to the last
		stopAtEnd : true,

		// Set this to append the keyboard after the input/textarea (appended to the input/textarea parent).
		// This option works best when the input container doesn't have a set width & when the 'tabNavigation'
		// option is true.
		appendLocally: false,
		// When appendLocally is false, the keyboard will be appended to this object
		appendTo     : 'body',

		// If false, the shift key will remain active until the next key is (mouse) clicked on; if true it will
		// stay active until pressed again
		stickyShift  : true,

		// Prevent pasting content into the area
		preventPaste : false,

		// caret placed at the end of any text when keyboard becomes visible
		caretToEnd   : false,

		// caret stays this many pixels from the edge of the input while scrolling left/right;
		// use "c" or "center" to center the caret while scrolling
		scrollAdjustment : 10,

		// Set the max number of characters allowed in the input, setting it to false disables this option
		maxLength    : false,
		// allow inserting characters @ caret when maxLength is set
		maxInsert    : true,

		// Mouse repeat delay - when clicking/touching a virtual keyboard key, after this delay the key will
		// start repeating
		repeatDelay  : 500,

		// Mouse repeat rate - after the repeatDelay, this is the rate (characters per second) at which the
		// key is repeated Added to simulate holding down a real keyboard key and having it repeat. I haven't
		// calculated the upper limit of this rate, but it is limited to how fast the javascript can process
		// the keys. And for me, in Firefox, it's around 20.
		repeatRate   : 20,

		// resets the keyboard to the default keyset when visible
		resetDefault : false,

		// Event (namespaced) on the input to reveal the keyboard. To disable it, just set it to ''.
		openOn       : 'focus',

		// Event (namepaced) for when the character is added to the input (clicking on the keyboard)
		keyBinding   : 'mousedown touchstart',

		// enable/disable mousewheel functionality
		// enabling still depends on the mousewheel plugin
		useWheel : true,

		// combos (emulate dead keys : http://en.wikipedia.org/wiki/Keyboard_layout#US-International)
		// if user inputs `a the script converts it to à, ^o becomes ô, etc.
		useCombos : true,

/*
		// *** Methods ***
		// commenting these out to reduce the size of the minified version
		// Callbacks - attach a function to any of these callbacks as desired
		initialized   : function(e, keyboard, el) {},
		beforeVisible : function(e, keyboard, el) {},
		visible       : function(e, keyboard, el) {},
		change        : function(e, keyboard, el) {},
		beforeClose   : function(e, keyboard, el, accepted) {},
		accepted      : function(e, keyboard, el) {},
		canceled      : function(e, keyboard, el) {},
		restricted    : function(e, keyboard, el) {},
		hidden        : function(e, keyboard, el) {},
		// called instead of base.switchInput
		switchInput   : function(keyboard, goToNext, isAccepted) {},
		// used if you want to create a custom layout or modify the built-in keyboard
		create        : function(keyboard) { return keyboard.buildKeyboard(); },

		// build key callback
		buildKey : function( keyboard, data ) {
			/ *
			data = {
				// READ ONLY
				isAction : [boolean] true if key is an action key
				name     : [string]  key class name suffix ( prefix = 'ui-keyboard-' ); may include decimal ascii value of character
				value    : [string]  text inserted (non-action keys)
				title    : [string]  title attribute of key
				action   : [string]  keyaction name
				html     : [string]  HTML of the key; it includes a <span> wrapping the text
				// use to modify key HTML
				$key     : [object]  jQuery selector of key which is already appended to keyboard
			}
			* /
			return data;
		},
*/

		// this callback is called just before the 'beforeClose' to check the value
		// if the value is valid, return true and the keyboard will continue as it should
		// (close if not always open, etc). If the value is not value, return false and the clear the keyboard
		// value ( like this "keyboard.$preview.val('');" ), if desired. The validate function is called after
		// each input, the 'isClosing' value will be false; when the accept button is clicked,
		// 'isClosing' is true
		validate : function( keyboard, value, isClosing ) { return true; }

	};

	// for checking combos
	$keyboard.comboRegex = /([`\'~\^\"ao])([a-z])/mig;

	// store current keyboard element; used by base.isCurrent()
	$keyboard.currentKeyboard = '';

	$('<!--[if lte IE 8]><script>jQuery("body").addClass("oldie");</script><![endif]--><!--[if IE]>' +
		'<script>jQuery("body").addClass("ie");</script><![endif]-->').appendTo('body').remove();
	$keyboard.msie = $('body').hasClass('oldie'); // Old IE flag, used for caret positioning
	$keyboard.allie = $('body').hasClass('ie');

	$keyboard.watermark = (typeof(document.createElement('input').placeholder) !== 'undefined');

	$keyboard.checkCaretSupport = function() {
		if ( typeof $keyboard.checkCaret !== 'boolean' ) {
			// Check if caret position is saved when input is hidden or loses focus
			// (*cough* all versions of IE and I think Opera has/had an issue as well
			var $temp = $('<div style="height:0px;width:0px;overflow:hidden;"><input type="text" value="testing"/></div>')
				.prependTo( 'body' ); // stop page scrolling
			$keyboard.caret( $temp.find('input'), 3, 3 );
			// Also save caret position of the input if it is locked
			$keyboard.checkCaret = $keyboard.caret( $temp.find('input').hide().show() ).start !== 3;
			$temp.remove();
		}
		return $keyboard.checkCaret;
	};

	$keyboard.caret = function($el, param1, param2) {
		if ( !$el.length || $el.is(':hidden') || $el.css('visibility') === 'hidden' ) {
			return {};
		}
		var start, end, txt, pos,
			noFocus = $el.getkeyboard() && $el.getkeyboard().options.noFocus;
		if (!noFocus) { $el.focus(); }
		// set caret position
		if (typeof param1 !== 'undefined') {
			// allow setting caret using ( $el, { start: x, end: y } )
			if (typeof param1 === 'object' && 'start' in param1 && 'end' in param1) {
				start = param1.start;
				end = param1.end;
			} else if (typeof param2 === 'undefined') {
				param2 = param1; // set caret using start position
			}
			// set caret using ( $el, start, end );
			if (typeof param1 === 'number' && typeof param2 === 'number') {
				start = param1;
				end = param2;
			} else if ( param1 === 'start' ) {
				start = end = 0;
			} else if ( typeof param1 === 'string' ) {
				// unknown string setting, move caret to end
				start = end = $el.val().length;
			}

			// *** SET CARET POSITION ***
			// modify the line below to adapt to other caret plugins
			return $el.caret( start, end, noFocus );
		}
		// *** GET CARET POSITION ***
		// modify the line below to adapt to other caret plugins
		pos = $el.caret();
		start = pos.start;
		end = pos.end;

		// *** utilities ***
		txt = ($el[0].value || $el.text() || '');
		return {
			start : start,
			end : end,
			// return selected text
			text : txt.substring( start, end ),
			// return a replace selected string method
			replaceStr : function( str ) {
				return txt.substring( 0, start ) + str + txt.substring( end, txt.length );
			}
		};
	};

	$.fn.keyboard = function(options){
		return this.each(function(){
			if (!$(this).data('keyboard')) {
				/*jshint nonew:false */
				(new $.keyboard(this, options));
			}
		});
	};

	$.fn.getkeyboard = function(){
		return this.data('keyboard');
	};

/* Copyright (c) 2010 C. F., Wong (<a href="http://cloudgen.w0ng.hk">Cloudgen Examplet Store</a>)
 * Licensed under the MIT License:
 * http://www.opensource.org/licenses/mit-license.php
 * Highly modified from the original
 */

$.fn.caret = function( start, end, noFocus ) {
	if ( typeof this[0] === 'undefined' || this.is(':hidden') || this.css('visibility') === 'hidden' ) {
		return this;
	}
	var selRange, range, stored_range, txt, val,
		selection = document.selection,
		$el = this,
		el = $el[0],
		sTop = el.scrollTop,
		ss = false,
		supportCaret = true;
	try {
		ss = 'selectionStart' in el;
	} catch(err){
		supportCaret = false;
	}
	if (supportCaret && typeof start !== 'undefined') {
		if (!/(email|number)/i.test(el.type)) {
			if (ss){
				el.selectionStart = start;
				el.selectionEnd = end;
			} else {
				selRange = el.createTextRange();
				selRange.collapse(true);
				selRange.moveStart('character', start);
				selRange.moveEnd('character', end - start);
				selRange.select();
			}
		}
		// must be visible or IE8 crashes; IE9 in compatibility mode works fine - issue #56
		if ( !noFocus && ($el.is(':visible') || $el.css('visibility') !== 'hidden') ) { el.focus(); }
		el.scrollTop = sTop;
		return this;
	} else {
		if (/(email|number)/i.test(el.type)) {
			// fix suggested by raduanastase (https://github.com/Mottie/Keyboard/issues/105#issuecomment-40456535)
			start = end = $el.val().length;
		} else if (ss) {
			start = el.selectionStart;
			end = el.selectionEnd;
		} else if (selection) {
			if (el.nodeName === 'TEXTAREA') {
				val = $el.val();
				range = selection.createRange();
				stored_range = range.duplicate();
				stored_range.moveToElementText(el);
				stored_range.setEndPoint('EndToEnd', range);
				// thanks to the awesome comments in the rangy plugin
				start = stored_range.text.replace(/\r/g, '\n').length;
				end = start + range.text.replace(/\r/g, '\n').length;
			} else {
				val = $el.val().replace(/\r/g, '\n');
				range = selection.createRange().duplicate();
				range.moveEnd('character', val.length);
				start = (range.text === '' ? val.length : val.lastIndexOf(range.text));
				range = selection.createRange().duplicate();
				range.moveStart('character', -val.length);
				end = range.text.length;
			}
		} else {
			// caret positioning not supported
			start = end = (el.value || '').length;
		}
		txt = (el.value || '');
		return {
			start : start,
			end : end,
			text : txt.substring( start, end ),
			replace : function(str) {
				return txt.substring( 0, start ) + str + txt.substring( end, txt.length );
			}
		};
	}
};

return $keyboard;

}));
