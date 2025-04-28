$(document).ready(function(){

			// Keyboard Layouts
			$('.keyboard-normal').keyboard({
			    layout: 'custom',
			    autoAccept: 'true',
                            customLayout: {
                                'normal': [
                                        '1 2 3 4 5 6 7 8 9 0 {bksp}',
                                        '{tab} q w e r t y u i o p',
                                        'a s d f g h j k l {enter}',
                                        '{shift} z x c v b n m {shift}',
                                        '{accept} {space} {cancel}'
                                ],
                                'shift': [
                                        '1 2 3 4 5 6 7 8 9 0 {bksp}',
                                        '{tab} Q W E R T Y U I O P',
                                        'A S D F G H J K L {enter}',
                                        '{shift} Z X C V B N M {shift}',
                                        '{accept} {space} {cancel}'
                                ]
			    },
			    usePreview: false,
			    visible: function(e, keyboard, el) {
			       
			    },
			    beforeClose: function(e, keyboard, el, accepted) {
			        $('input.current').removeClass('current');
			        $("body").css('padding-bottom', '0px');
			    }
			});

			$('.keyboard-zip').keyboard({
			    layout: 'custom',
			    autoAccept: 'true',
			    maxLength: 5,
			    customLayout: {
			        'default': [
			            '7 8 9',
			            '4 5 6',
			            '1 2 3',
			            '0 {bksp}',
			            '{accept}'
			            ]
			    },
			    usePreview: false,
			    visible: function(e, keyboard, el) {
			        
			    },
			    beforeClose: function(e, keyboard, el, accepted) {
			        $('input.current').removeClass('current');
			        $("body").css('padding-bottom', '0px');
			    }
			});

			$('.keyboard-num').keyboard({
			    layout: 'custom',
			    autoAccept: 'true',
			    customLayout: {
			        'default': [
			            '1 2 3',
			            '4 5 6',
			            '7 8 9',
			            '{bksp} 0 {accept}'
			            ]
			    },
			    usePreview: false,
			    visible: function(e, keyboard, el) {
			        
			    },
			    beforeClose: function(e, keyboard, el, accepted) {
			        $('input.current').removeClass('current');
			        $("body").css('padding-bottom', '0px');
                                
			    }
			}).on('keyboardChange', function(e, keyboard, el){
                            $(".ui-keyboard-button").each(function(){
                                if(parseInt(keyboard.last.key)===parseInt($(this).data('value'))){
                                    var code = $(this).data('code');
                                    $('#txtNIT').trigger(
                                        jQuery.Event( 'keypress', { keyCode: code, which: code } )
                                    );
                                }
                            });
                        });

			$('.keyboard-states').keyboard({
			    layout: 'custom',
			    customLayout: {
			        'default': [
			            'AL AK AZ AR CA CO CT DE FL GA',
			            'HI ID IL IN IA KS KY LA ME MD',
			            'MA MI MN MS MO MT NE NV NH NJ',
			            'NM NY NC ND OH OK OR PA RI SC',
			            'SD TN TX UT VT VA WA WV WI WY',
			            '{accept}{clear}'
			            ]
			    },
			    usePreview: false,
			    visible: function(e, keyboard, el) {
			       
			    },
			    // prevent entering more than one state
			    change: function(e, keyboard, el) {
			        var v = keyboard.$el.val();
			        if (v.length > 2) {
			            keyboard.$el.val(v.slice(-2));
			        }
			    },
			    beforeClose: function(e, keyboard, el, accepted) {
			        $('input.current').removeClass('current');
			        $("button.ui-keyboard-widekey").removeClass('state-button');
			        $("body").css('padding-bottom', '0px');
			    }
			});
});

