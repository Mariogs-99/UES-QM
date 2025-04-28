var TMessages;

var configureBefore = function( ) {
    TMessages = {
        intervalText: -1,
        tDiv: null,
        LONG_TIME: 6000,
        SHORT_TIME: 3000,
        messageClearClasses: function() {
            this.tDiv.removeClass("notice");
            this.tDiv.removeClass("warning");
            this.tDiv.removeClass("failed");
        },
        showMessage: function(args) {
            this.tDiv.show();
            this.tDiv.find("li").remove();
            this.tDiv.find("ul").append(jQuery("<li>").html("<p>" + args[0] + "</p>"));
            this.tDiv.stop();
            if (this.intervalText > 0) {
                clearInterval(this.intervalText);
            }
            var time = this.LONG_TIME;
            this.tDiv.animate({right: 0}, 200);
            if (args[1] !== null) {
                if (!isNaN(parseInt(args[1]))) {
                    time = parseInt(args[1]);
                }
            }
            TMessages.intervalText = setTimeout(function() {
                TMessages.tDiv.animate({right: -(TMessages.tDiv.innerWidth() - 50)});
            }, time);
        },
        showErrorMessage: function() {
            var args = arguments;
            this.messageClearClasses();
            this.tDiv.addClass("failed").addClass("r-shadow");
            this.showMessage(args);
        },
        showWarningMessage: function() {
            var args = arguments;
            this.messageClearClasses();
            this.tDiv.addClass("warning");
            this.showMessage(args);
        },
        showNoticeMessage: function() {
            var args = arguments;
            this.messageClearClasses();
            this.tDiv.addClass("notice");
            this.showMessage(args);
        },
        setMessageContainer: function(jQueryObject) {
            this.tDiv = jQueryObject;
        },
        clearMessages: function() {
            jQuery("#tMessage ul:first  li").remove();
        },
        extractErrorMessages: function() {
            var counter = 0;
            this.messageClearClasses();
            this.tDiv.addClass("failed");
            var seed = 'span[class*=error], span[class^=error], span[id*=error], span[id^=error], ul[class*=error], div[id*=errors]';

            if (jQuery(seed).length > 0) {
                jQuery(seed).hide();
                jQuery(seed).each(
                        function(index, obj) {
                            var o = jQuery(obj);
                            var li = jQuery("<li>").html("<p>" + o.html() + "</p>");
                            var exists = false;
                            try {
                                exists = (jQuery("#tMessage ul:first").html().indexOf(li.html()) !== -1);
                            } catch (e) {
                            }
                            if (!exists) {
                                counter++;
                                jQuery("#tMessage ul:first").append(li);
                            }
                            jQuery(this).remove();
                        }
                );
                TMessages.tDiv.show();
                TMessages.tDiv.css({right: 0});
                TMessages.intervalText = setTimeout(function() {
                    TMessages.tDiv.animate({right: -(TMessages.tDiv.innerWidth() - 50)});
                }, 10000);
            }
            return counter;
        }
    };
};
var expand = function(o) {
    var a = jQuery(o);
    a.stop();
    a.animate({right: 0}, 300);
    jQuery(o).attr('status', 'e');
};
var collapse = function(o) {
    var a = jQuery(o);
    a.stop();
    a.animate({right: -350}, 300);
    jQuery(o).attr('status', 'c');
};
var check = function(o) {
    var status = jQuery(o).attr('status');
    if (!status || status === undefined) {
        status = 'c';
    }
    if (status === 'c') {
        expand(o);
    } else {
        collapse(o);
    }
};
var refreshErrors = function() {
//    console.log('refreshing errors...');
    TMessages.tDiv = jQuery("div#tMessage");
    jQuery("#tMessage").hide();
    TMessages.clearMessages();
//    console.log('END refreshing errors...');
    return TMessages.extractErrorMessages();
};

var configureTMessages = function() {
    configureBefore();
    if (!isNullOrUndefined(TMessages)) {
        TMessages.tDiv = jQuery("div#tMessage");
        var divM = jQuery("<div>", {"class": "message-list r b-shadow"});
        divM.append(jQuery("<ul>"));
        TMessages.tDiv.append(divM);
        refreshErrors();

        jQuery("#tMessage").mouseenter(
                function() {
                    expand(this);
                }
        ).dblclick(function() {
            jQuery(this).hide();
        }).mouseleave(
                function() {
                    collapse(this);
                }
        ).click(
                function() {
                    jQuery(this).hide();
//                    check(this);
                }
        );
        jQuery("a.alert").click(
                function(e) {
                    e.preventDefault();
                }
        );
        jQuery("a#alert1").click(
                function(e) {
                    TMessages.showWarningMessage("Text warning message", 1000);
                }
        );
        jQuery("a#alert2").click(
                function(e) {
                    TMessages.showNoticeMessage("Text notice message", 3000);
                }
        );
    }
};