var isUndefinedOrNull = function (o) {
    return (!o || o === null || o === undefined);
};

var wait = function (element) {
    if (!isUndefinedOrNull(element) && !isUndefinedOrNull(jQuery(element).position())) {
        var width = 350;
        var height = 60;
        var offset = 30; // 22
        var outset = 6; // 22
        var message = 'Espere por favor, actualizando...';
        var top = jQuery(element).height() / 2 - height / 2;
        var left = jQuery(element).width() / 2 - width / 2;
        var divname = jQuery(element).attr('id');
        var divname = isUndefinedOrNull(divname) ? 'waitDiv' : divname + 'waitDiv';
//    console.log('element:'+element);
        var newDiv = '<div id="' + divname + '" class="ui-overlay ui-corner-all"  style="margin-top: ' + jQuery(element).css('marginTop') + '; margin-left:' + jQuery(element).css('marginLeft') + '; margin-right:' + jQuery(element).css('marginRight') + '; margin-bottom:' + jQuery(element).css('marginBottom') + '; width: ' + jQuery(element).width() + 'px; height: ' + jQuery(element).height() + 'px; position: absolute; left: ' + jQuery(element).position().left + 'px; top: ' + jQuery(element).position().top + 'px;">'
                + '<div class="ui-widget-overlay ui-corner-all"></div>'
                + '<div class="ui-widget-shadow ui-corner-all"            style="position: absolute; width: ' + (width + outset) + 'px; height: ' + (height + outset) + 'px; left: ' + left + 'px; top: ' + top + 'px;"></div>'
                + '<div class="ui-widget ui-widget-content ui-corner-all" style="vertical-align:middle; position: absolute; width: ' + (width - offset) + 'px; height: ' + (height - offset) + 'px; left: ' + (left + offset / 5) + 'px; top: ' + (top + offset / 5) + 'px; padding: 10px;"><img style=" float:left; paddin:0 0; margin: 0 0;" id="loadingIcon" src="/colas/images/loading.gif" title="' + message + '" /><div class="waitmessagetext">' + message + '</div></div></div>';
        jQuery(element).append(newDiv);
//        console.log(newDiv);
    }
};

var rand = function () {
    var now = new Date();
    var num = now * Math.floor(Math.random() * 1024);
    var num = num + 1;
    return num;
};

addTab = function (tabs) {
    var label = tabTitle.val() || "Tab " + tabCounter,
            id = "tabs-" + tabCounter,
            li = $(tabTemplate.replace(/#\{href\}/g, "#" + id).replace(/#\{label\}/g, label)),
            tabContentHtml = tabContent.val() || "Tab " + tabCounter + " content.";
    tabs.find(".ui-tabs-nav").append(li);
    tabs.append("<div id='" + id + "'><p>" + tabContentHtml + "</p></div>");
    tabs.tabs("refresh");
    tabCounter++;
};

var minWidth = 400;
var minHeight = 400;
var defaultTitle = 'Dialog';

var resizeDialog = function (dialogId, height) {
    var newheight = 0;
    var offset = 100;
    jQuery("#" + dialogId).find('div.form, div.toolBar').each(function () {
        var isvisible = jQuery(this).is(':visible');
//        console.log('isVisible(' + jQuery(this).attr('id') + ')=' + jQuery(this).is(':visible'));
        if (!isUndefinedOrNull(isvisible) && isvisible === true) {
            newheight += jQuery(this).height();
        }
    });
    newheight += offset;
//    if (newheight < height) {
//        newheight *= 1.93;
//    }
    jQuery("#" + dialogId).dialog("option", "height", newheight).hide().show('fast');
    return newheight;
};

var openDialog = function (dialogId, modal, icon, title, width, height, defaultField, resizeToFit) {
//    console.log('opening dialog ('+dialogId+')');
    if (!isUndefinedOrNull(modal) && modal === true || modal === 'true' || modal === 'si' || modal === 'yes') {
        jQuery("#" + dialogId).dialog("option", "modal", modal);
    }
    jQuery("#" + dialogId).dialog("option", "title", '<span class="ui-icon ' + icon + '" style="display: inline-table; vertical-align:middle; "></span><span  style="display: inline-table; vertical-align:middle;">' + title + '</span>');
    jQuery("#" + dialogId).dialog("option", "width", width);
    jQuery("#" + dialogId).dialog("open");

    if (!isUndefinedOrNull(defaultField)) {
        try {
            jQuery('#' + replaceAll(defaultField, '#', '')).focus();
        } catch (e) {
            console.log(e);
        }
    }
    if (!isUndefinedOrNull(resizeToFit) && (resizeToFit === true || resizeToFit === 'yes' || resizeToFit === 'si' || resizeToFit === 'true')) {
        var newheight = resizeDialog(dialogId, height);
    } else {
        jQuery("#" + dialogId).dialog("option", "height", height).hide().show('fast');
    }
};

var configureSelectableCheckBox = function (checkbox) {
    if (!isUndefinedOrNull(checkbox)) {

        jQuery(checkbox).click(function (event) {
            updatecheckboxtr(this);
            var func = jQuery(checkbox).attr('functionName');
            try {
                eval(func);
            } catch (e) {
            }

        });
    }
};

var configureSelectableCheckBoxes = function () {
    jQuery('.selectableCheckBox').each(function () {
        configureSelectableCheckBox(this);
    });

    jQuery('.selectableButton').each(function () {
        jQuery(this).button({
            icons: {
                primary: "ui-icon-circle-zoomout"
            }
        });
    });

    jQuery('.selectAllSelectableCheckBoxes').each(function () {
        var target = jQuery(this).data('target');
        if (!isUndefinedOrNull(target)) {
            jQuery(this).click(function (event) {
                var chked = this.checked;
                jQuery(target).each(function () {
                    jQuery(this).prop('checked', chked);
                    updatecheckboxtr(this);
                });
            });
        }
    });
};

var updatecheckboxtr = function (checkbox) {
    var chked = checkbox.checked;
    var tr = jQuery(checkbox).closest('tr');
    if (!isUndefinedOrNull(tr)) {
        if (chked) {
            jQuery(tr).addClass('selected');
        } else {
            jQuery(tr).removeClass('selected');
        }
    }
};
var unlockButton = function (btn) {
    try {
//        console.log('unlockButton(' + jQuery(btn).attr('id') + ')');
        jQuery(btn).removeClass('disabled');
    } catch (e) {
    }
    try {
        jQuery(btn).button('option', 'disabled', false);
    } catch (e) {
    }
};

var openWindow = function (url, sheight, swidth, sleft, stop) {
    return window.open(url, '', 'height=' + sheight + ',width=' + swidth + ',left=' + sleft + ',top=' + stop + ',scrollbars=1,resizable=1,toolbar=yes');

};
var openWindowCenter = function (url, sheight, swidth) {
    var lpos = (screen.width) ? (screen.width - swidth) / 2 : 0;
    var tpos = (screen.height) ? (screen.height - sheight) / 2 : 0;
    var options = 'height=' + sheight + ',width=' + swidth + ',top=' + tpos + ',left=' + lpos + ',scrollbars=1,resizable=1';
    return window.open(url, "", options);
};
var openWindowFull = function (url) {
    var swidth = screen.width * .96;
    var sheight = screen.height * .85;
    var lpos = (screen.width) ? (screen.width - swidth) / 2 : 0;
    var tpos = (screen.height) ? (screen.height - sheight) / 2 : 0;
    var options = 'height=' + sheight + ',width=' + swidth + ',top=' + tpos + ',left=' + lpos + ',scrollbars=1,resizable=1,';
    return window.open(url, "", options);
};


var evaluatePostFunction = function (btn, responseTxt, statusTxt, xhr) {
    var func = jQuery(btn).attr('functionName');
    var f = func + '(responseTxt, statusTxt, xhr)';
    var hide = jQuery(btn).attr('hide');
    unlockButton(btn);

    if (!isUndefinedOrNull(hide)) {
        if (hide === 'true') {
            try {
                jQuery(btn).hide();
            } catch (e) {
            }
        }
    }
    try {
        eval(f);
    } catch (e) {
    }
};

var configureIOpenerButtons = function () {
    jQuery('.dialogOpener').each(function () {
        configureIOpenerButton(this);
    });
    jQuery('.dialogOpener2').each(function () {
        configureIOpenerButton2(this);
    });
};

var configureIOpenerButton = function (btn) {
    var icon = jQuery(btn).attr('icon');
    if (!isUndefinedOrNull(icon)) {
        try {
            jQuery(btn).button({
                icons: {
                    primary: icon
                }
            });
        } catch (e) {
        }
    }

    jQuery(btn).click(function () {
        if (!jQuery(btn).hasClass('disabled')) {
            jQuery(btn).addClass('disabled');
            var icon = jQuery(btn).attr('icon');
            icon = isUndefinedOrNull(icon) ? 'ui-icon-signal-diag' : icon;
            try {
                jQuery(btn).button('option', 'disabled', true);
            } catch (e) {
            }
            var dialogId = jQuery(btn).attr('dialogId');
            var width = jQuery(btn).attr('width');
            width = isUndefinedOrNull(width) ? minWidth : width;
            var title = jQuery(btn).attr('title');
            title = isUndefinedOrNull(title) ? defaultTitle + rand() : title;
            var height = jQuery(btn).attr('height');
            height = isUndefinedOrNull(height) ? minHeight : height;
            var modal = jQuery(btn).attr('modal');
            var resizeToFit = jQuery(btn).attr('resizeToFit');
            var itemid = jQuery(btn).attr('itemid');
            var args = isUndefinedOrNull(itemid) ? jQuery(btn).attr('args') : 'recid=' + itemid;
//            console.log('ARGS=' + args);
            var url = jQuery(btn).attr('url');
            if (!isUndefinedOrNull(url)) {
                wait2(".cpanel");
//                console.log('URL is NOT undefined....(' + url + ')');
                var div = jQuery(btn).attr('dialogId');
                if (!isUndefinedOrNull(div)) {
                    jQuery('#' + div).load(url + ' #' + div, args, function (responseTxt, statusTxt, xhr) {
                        modal = isUndefinedOrNull(modal) ? false : modal === "true" || modal === "si" || modal === "yes";
                        if (dialogId && dialogId !== null && dialogId !== undefined) {
                            var defaultField = jQuery(btn).attr('defaultField');
                            openDialog(dialogId, modal, icon, title, width, height, defaultField, resizeToFit);
                            evaluatePostFunction(btn, responseTxt, statusTxt, xhr);
                        }
                        jQuery("#waitDiv").remove();
                    });
                }
            } else {
//                console.log('URL IS undefined....');
                modal = isUndefinedOrNull(modal) ? false : modal === "true" || modal === "si" || modal === "yes";
                if (dialogId && dialogId !== null && dialogId !== undefined) {
                    var defaultField = jQuery(btn).attr('defaultField');
                    openDialog(dialogId, modal, icon, title, width, height, defaultField, resizeToFit);
                    evaluatePostFunction(btn);
                }
                jQuery("#waitDiv").remove();
            }
        }
        jQuery("#waitDiv").remove();
    });
};
var configureIOpenerButton2 = function (btn) {
    var icon = jQuery(btn).attr('icon');
    if (!isUndefinedOrNull(icon)) {
        try {
            jQuery(btn).button({
                icons: {
                    primary: icon
                }
            });
        } catch (e) {
        }
    }

    jQuery(btn).click(function () {
        if (!jQuery(btn).hasClass('disabled')) {
            jQuery(btn).addClass('disabled');
            var icon = jQuery(btn).attr('icon');
            icon = isUndefinedOrNull(icon) ? 'ui-icon-signal-diag' : icon;
            try {
                jQuery(btn).button('option', 'disabled', true);
            } catch (e) {
            }
            var dialogId = jQuery(btn).attr('dialogId');
            var width = jQuery(btn).attr('width');
            width = isUndefinedOrNull(width) ? minWidth : width;
            var title = jQuery(btn).attr('title');
            title = isUndefinedOrNull(title) ? defaultTitle + rand() : title;
            var height = jQuery(btn).attr('height');
            height = isUndefinedOrNull(height) ? minHeight : height;
            var modal = jQuery(btn).attr('modal');
            var resizeToFit = jQuery(btn).attr('resizeToFit');
            var refreshDiv = jQuery(btn).attr('refreshDiv');
            var args = jQuery(btn).attr('args');
//            console.log('ARGS=' + args);
            wait2(".cpanel");
            var url = jQuery(btn).attr('url');
            if (!isUndefinedOrNull(url)) {
//                console.log('URL is NOT undefined....(' + url + ')');
                var div = jQuery(btn).attr('dialogId');
                if (!isUndefinedOrNull(div)) {
                    jQuery('#' + div).load(url + ' #' + refreshDiv, args, function (responseTxt, statusTxt, xhr) {
                        modal = isUndefinedOrNull(modal) ? false : modal === "true" || modal === "si" || modal === "yes";
                        if (dialogId && dialogId !== null && dialogId !== undefined) {
                            var defaultField = jQuery(btn).attr('defaultField');
                            openDialog(dialogId, modal, icon, title, width, height, defaultField, resizeToFit);
                            evaluatePostFunction(btn, responseTxt, statusTxt, xhr);
                        }
                        jQuery("#waitDiv").remove();
                    });
                }
            } else {
//                console.log('URL IS undefined....');
                modal = isUndefinedOrNull(modal) ? false : modal === "true" || modal === "si" || modal === "yes";
                if (dialogId && dialogId !== null && dialogId !== undefined) {
                    var defaultField = jQuery(btn).attr('defaultField');
                    openDialog(dialogId, modal, icon, title, width, height, defaultField, resizeToFit);
                    evaluatePostFunction(btn);
                }
                jQuery("#waitDiv").remove();
            }

        }
    });
};

var closedialog = function (dialogId) {
    try {
        jQuery(dialogId).dialog('close');
    } catch (e) {
        console.log(e);
    }
};

var clearDivImputs = function (div) {
    var inputs = jQuery(div).find(':input:not([type=button])');
    if (!isUndefinedOrNull(inputs)) {
        for (var i = 0; i < inputs.length; i++) {
            try {
                jQuery(inputs[i]).val('');
            } catch (e) {
            }
        }
    }
};

var clearInputs = function (inputs) {
    if (!isUndefinedOrNull(inputs)) {
        var array = inputs.split(',');
        for (var i = 0; i < array.length; i++) {
            try {
                var vname = array[i];
                jQuery('#' + replaceAll(vname, '#', '')).val('');
            } catch (e) {
            }
        }
    }
};

var configureIWindow = function (window) {
    jQuery(window).dialog({
        autoOpen: false,
        closeOnEscape: false
    }).on('keydown', function (evt) {
        if (evt.keyCode === jQuery.ui.keyCode.ESCAPE) {
            evt.preventDefault();
//            console.log('CLOSE!');
        }
        evt.stopPropagation();
    });

};

var configureIWindows = function () {
    jQuery('.iwindow').each(function () {
        configureIWindow(this);
    });

    jQuery(".dialogOpener").each(function () {
        configureIOpenerButton(this);
    });

    jQuery('.drag').draggable();
//    jQuery('.messages').draggable();
    jQuery('.sort').sortable({handle: 'a.move230', placeholder: 'ui-state-highlight'});
};

var configureUpdatingButtons = function () {
    jQuery('.updatingDivButton').each(function () {
        configureUpdatingButton(this);
    });
};

var configureUpdatingButton = function (btn) {
    var icon = jQuery(btn).attr('icon');
    icon = isNullOrUndefined(icon) ? 'ui-icon-radio-off' : icon;

    jQuery(btn).button({
        icons: {
            primary: icon
        }
    });

    configureSimpleUpdatingButton(btn);
};

var configureSimpleUpdatingButtons = function () {
    jQuery('.simpleUpdatingDivButton').each(function () {
        configureSimpleUpdatingButton(this);
    });
};

var configureSimpleUpdatingButton = function (btn) {
    var configured = jQuery(btn).attr('configured');
    if (isUndefinedOrNull(configured)) {
        var icon = jQuery(btn).attr('icon');
        if (!isNullOrUndefined(icon)) {
            jQuery(btn).button({
                icons: {
                    primary: icon
                }
            });
        }

        jQuery(btn).click(function (event) {
            event.preventDefault();
            clickUpdatingDivButton(this, event);
        });

        var hide = jQuery(btn).attr('hide');
        if (!isUndefinedOrNull(hide)) {
            if (hide === 'true') {
                try {
                    jQuery(btn).hide();
                } catch (e) {
                }
            }
        }
        jQuery(btn).attr('configured', true);
    }
};

var configureUpdatingLinks = function () {
    jQuery('.updatingDivLink').each(function () {
        configureUpdatingLink(this);
    });
};

var configureUpdatingLink = function (link) {
    configureSimpleUpdatingButton(link);
};

var configureAjaxHeader = function (header) {
    jQuery(header).click(function (event) {
        clickAjaxHeader(this, event);
    });
};

var configureAjaxHeaders = function (table) {
    if (!isNullOrUndefined(jQuery(table))) {
        jQuery(table).find('.ajaxcall').each(function () {
            configureAjaxHeader(this);
        });
    } else {
        jQuery('.ajaxcall').each(function () {
            configureAjaxHeader(this);
        });
    }
};

var clickAjaxHeader = function (header, event) {
    event.preventDefault();
    var table = jQuery(header).closest('table');
    var href = jQuery(header).attr("href");

    tableGo(table, href);
};

var continuefunctionupdatingdivbutton = function (btn, event) {

    var beforeFunctionName = jQuery(btn).attr('beforeFunctionName');
    beforeFunctionName = isUndefinedOrNull(beforeFunctionName) ? '' : beforeFunctionName;
    try {
        if (!isUndefinedOrNull(beforeFunctionName) && beforeFunctionName !== '') {

            eval(beforeFunctionName);
        }
    } catch (e) {
        alert(e);
        console.log(e);
    }
    //...continue with ajax call
    var div = jQuery(btn).attr('div');
    div = isUndefinedOrNull(div) ? 'body' : div;
    var functionName = jQuery(btn).attr('functionName');
    functionName = isUndefinedOrNull(functionName) ? '' : functionName;
    var noform = jQuery(btn).data('noform') || jQuery(btn).attr('hide');

    var params = isUndefinedOrNull(noform) ? jQuery(btn).attr('params') : undefined;
    var form = isUndefinedOrNull(noform) ? jQuery(btn).closest('form') : undefined;
    var datas = isUndefinedOrNull(noform) ? jQuery(btn).attr('datas') : undefined;
//    console.log('dat a s(' + datas + ')');
//      console.log(' F OrM(' + form.attr('id') + ')');
    params = isUndefinedOrNull(params) ? !isUndefinedOrNull(form) ? form.serialize() : '' : params;
    if (!isUndefinedOrNull(datas)) {

        var array = datas.split(',');
        for (var i = 0; i < array.length; i++) {
            var val;
            var vname = array[i];
            //            console.log('validating(' + vname + ')');
            try {
                if (jQuery('#' + replaceAll(vname, '#', '')).is('[type="checkbox"]')) {
                    val = jQuery(inputs[i]).is(':checked');
                } else {
                    val = jQuery('#' + replaceAll(vname, '#', '')).val();
                }
            } catch (e) {
            }
            if (!isUndefinedOrNull(val)) {

                params += '&' + vname + '=' + escape(val);
//                console.log(params);
            }
        }
    } else {
        //            console.log('datas is undefined!...');
        var dataDiv = jQuery(btn).attr('dataDiv');
        var inputs = jQuery('#' + dataDiv).find(':input:not([type=button])');
        if (!isUndefinedOrNull(inputs)) {
            for (var i = 0; i < inputs.length; i++) {
                var val;
                var vname = jQuery(inputs[i]).attr('id');
                try {
                    if (jQuery(inputs[i]).is('[type="checkbox"]')) {
//                        val = jQuery(inputs[i]).is(':checked');
                        if (jQuery(inputs[i]).is(':checked'))
                            val = jQuery(inputs[i]).attr("value");
                        else
                            val = '';
                    } else {
                        val = jQuery(inputs[i]).val();
                    }
                } catch (e) {
                }
                if (!isUndefinedOrNull(val)) {
                    params = params.concat('&' + vname + '=' + escape(val));
                    //   console.log(params);
                }
            }
        }
    }
//        console.log('PARAMS(' + params + ')');
    var url = jQuery(btn).attr('url');
//            jQuery(btn).attr('params', params);
    //+
//    alert("params :"+params)
    url = isUndefinedOrNull(url) ? '' : (url + (isUndefinedOrNull(params) ? '' : ('?' + params)));
    try {
        jQuery(btn).button('option', 'disabled', true);
    } catch (e) {
    }



    doLoad(btn, url, div, functionName);
};

var clickUpdatingDivButton = function (btn, event) {
    if (!jQuery(btn).hasClass('disabled')) {
        jQuery(btn).addClass('disabled');

        var vconfirm = jQuery(btn).attr('confirm');
        if (!isNullOrUndefined(vconfirm)) {
            uiconfirm(vconfirm, null, function () {
                continuefunctionupdatingdivbutton(btn, event);
            }, function () {
                unlockButton(btn);
            });
        } else {
            continuefunctionupdatingdivbutton(btn, event);
        }
    }
};

var uialert = function (msg, title) {
    title = isUndefinedOrNull(title) ? '&#161;ALERTA&#33;' : title;
    var div = jQuery('<div id="uialert" title="' + title + '">'
            + '<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>' + msg + '</p>'
            + '</div>');
    jQuery(div).dialog({
        resizable: false,
        height: 140,
        modal: true,
        buttons: {
            "Ok": {text: 'Ok', class: 'yeahbutton', click: function () {
                    jQuery(this).dialog("close");
                }}
        }
    }).dialog("open");
};
var uiconfirm = function (msg, yesfunction) {
    uiconfirm(msg, 'CONFIRME', yesfunction, null);
};
var uiconfirm = function (msg, yesfunction, nofunction) {
    uiconfirm(msg, 'CONFIRME', yesfunction, nofunction);
};
var uiconfirm = function (msg, title, yesfunction, nofunction) {
    title = isUndefinedOrNull(title) ? 'CONFIRME' : title;
    var div = jQuery('<div id="uialert" title="' + title + '">'
            + '<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>' + msg + '</p>'
            + '</div>');
    jQuery(div).dialog({
        resizable: false,
        height: 150,
        width: 400,
        modal: true,
        buttons: {
            "Si": {text: 'Si', class: 'yeahbutton', click: function () {
                    jQuery(this).dialog("close");
                    try {
                        yesfunction();
                    } catch (e) {
                        console.log('yes error:' + e);
                        return true;
                    }
                    jQuery(this).remove();
                }},
            "No": {text: 'No', class: 'noobutton', click: function () {
                    jQuery(this).dialog("close");
                    try {
                        nofunction();
                    } catch (e) {
                        return false;
                    }
                    jQuery(this).remove();
                }}
        }
    }).dialog("open");
};

var doLoad = function (btn, url, div, func) {
    wait('#' + div);
    var ismultipart = jQuery(btn).hasClass('multipart');
    if (isUndefinedOrNull(ismultipart) || (ismultipart !== 'true' && ismultipart !== true && ismultipart !== 1 && ismultipart !== '1' && ismultipart !== 'yes' && ismultipart !== 'si' && ismultipart !== 'on')) {
        url = encodeURI(url);
        jQuery('#' + div).load(url + ' #' + div, function (responseTxt, statusTxt, xhr) {
            jQuery("#numsol").val("");
            if (statusTxt === 'success') {

                unlockButton(btn);
                var f = func + '(responseTxt, statusTxt, xhr)';
                var hide = jQuery(btn).attr('hide');
                if (!isUndefinedOrNull(hide)) {
                    if (hide === 'true') {
                        try {
                            jQuery(btn).hide();
                        } catch (e) {
                        }
                    }
                } else {
                    try {
                        jQuery(btn).button('option', 'disabled', false);
                    } catch (e) {
                    }
                }
                try {
                    eval(f);
                } catch (e) {
                }
            } else {
                var divname = isUndefinedOrNull(div) ? 'waitDiv' : div + 'waitDiv';
                var msg = "Ha ocurrido un error inesperado: ";
                jQuery('#' + divname).hide();
                jQuery('#' + divname).remove();
                uialert('ERROR: ' + msg + xhr.status + " " + xhr.statusText, '¡Error Inesperado!');
            }
        });
    } else {
        var data = new FormData();
        jQuery("input[type=file]").each(function () {
            var files = jQuery(this)[0].files;
            if (!isUndefinedOrNull(files)) {
                var filenameseparator = jQuery(files).length > 1 ? '_' : '';
                jQuery.each(jQuery(this)[0].files, function (i, file) {
                    var filename = jQuery(this).attr('id');
                    filename = (isUndefinedOrNull(filename) ? 'archivo' : filename)
                            + ((!isUndefinedOrNull(filenameseparator) && filenameseparator !== '') ? (filenameseparator + i) : '');
                    data.append(filename, file);
                    console.log('search for(' + filename + ')');
                });
            }
        });

        jQuery.ajax({
            url: url,
            data: data,
            cache: false,
            contentType: false,
            processData: false,
            sync: false,
            type: 'POST'
        }).always(
                function (responseTxt, statusTxt, xhr) {
                    if (statusTxt === 'success') {
//                        jQuery('#waitDiv').hide();
                        var divname = isUndefinedOrNull(div) ? 'waitDiv' : div + 'waitDiv';
                        jQuery('#' + divname).hide();
                        jQuery('#' + div).html(jQuery(responseTxt).find('#' + div).html() + '<div id="#errorMessages">' + jQuery(responseTxt).find('#errorMessages').html() + '</div>');
                        jQuery('#errorMessages').hide();
                        unlockButton(btn);
                        var f = func + '(responseTxt, statusTxt, xhr)';
                        var hide = jQuery(btn).attr('hide');
                        if (!isUndefinedOrNull(hide)) {
                            if (hide === 'true') {
                                try {
                                    jQuery(btn).hide();
                                } catch (e) {
                                }
                            }
                        } else {
                            try {
                                jQuery(btn).button('option', 'disabled', false);
                            } catch (e) {
                            }
                        }
                        try {
                            eval(f);
                        } catch (e) {
                        }
                    }
                }
        );
    }

};



var LoadObject = function (btn, url, params, div, func) {
    jQuery("#dvresolucionesiva_solicitud").load("/dv/resoluciones/devoluciones/iva/new", params)
    wait('#' + div);
    var ismultipart = jQuery(btn).hasClass('multipart');
    if (isUndefinedOrNull(ismultipart) || (ismultipart !== 'true' && ismultipart !== true && ismultipart !== 1 && ismultipart !== '1' && ismultipart !== 'yes' && ismultipart !== 'si' && ismultipart !== 'on')) {

        jQuery('#' + div).load(url + ' #' + div, params, function (responseTxt, statusTxt, xhr) {
            if (statusTxt === 'success') {
                unlockButton(btn);
                var f = func + '(responseTxt, statusTxt, xhr)';
                var hide = jQuery(btn).attr('hide');
                if (!isUndefinedOrNull(hide)) {
                    if (hide === 'true') {
                        try {
                            jQuery(btn).hide();
                        } catch (e) {
                        }
                    }
                } else {
                    try {
                        jQuery(btn).button('option', 'disabled', false);
                    } catch (e) {
                    }
                }
                try {
                    eval(f);
                } catch (e) {
                }
            } else {
                var divname = isUndefinedOrNull(div) ? 'waitDiv' : div + 'waitDiv';
                var msg = "Ha ocurrido un error inesperado: ";
                jQuery('#' + divname).hide();
                jQuery('#' + divname).remove();
                uialert('ERROR: ' + msg + xhr.status + " " + xhr.statusText, '¡Error Inesperado!');
            }
        });
    } else {
        var data = new FormData();
        jQuery("input[type=file]").each(function () {
            var files = jQuery(this)[0].files;
            if (!isUndefinedOrNull(files)) {
                var filenameseparator = jQuery(files).length > 1 ? '_' : '';
                jQuery.each(jQuery(this)[0].files, function (i, file) {
                    var filename = jQuery(this).attr('id');
                    filename = (isUndefinedOrNull(filename) ? 'archivo' : filename)
                            + ((!isUndefinedOrNull(filenameseparator) && filenameseparator !== '') ? (filenameseparator + i) : '');
                    data.append(filename, file);
                    console.log('search for(' + filename + ')');
                });
            }
        });

        jQuery.ajax({
            url: url,
            data: data,
            cache: false,
            contentType: false,
            processData: false,
            sync: false,
            type: 'POST'
        }).always(
                function (responseTxt, statusTxt, xhr) {
                    if (statusTxt === 'success') {
                        var divname = isUndefinedOrNull(div) ? 'waitDiv' : div + 'waitDiv';
                        jQuery('#' + divname).hide();
                        jQuery('#' + div).html(jQuery(responseTxt).find('#' + div).html() + '<div id="#errorMessages">' + jQuery(responseTxt).find('#errorMessages').html() + '</div>');
                        jQuery('#errorMessages').hide();
                        unlockButton(btn);
                        var f = func + '(responseTxt, statusTxt, xhr)';
                        var hide = jQuery(btn).attr('hide');
                        if (!isUndefinedOrNull(hide)) {
                            if (hide === 'true') {
                                try {
                                    jQuery(btn).hide();
                                } catch (e) {
                                }
                            }
                        } else {
                            try {
                                jQuery(btn).button('option', 'disabled', false);
                            } catch (e) {
                            }
                        }
                        try {
                            eval(f);
                        } catch (e) {
                        }
                    }
                }
        );
    }

};


var LoadObject = function (btn, url, params, div, func) {
    jQuery("#dvresolucionesrenta_solicitud").load("/dv/resoluciones/devoluciones/renta/new", params)
    wait('#' + div);
    var ismultipart = jQuery(btn).hasClass('multipart');
    if (isUndefinedOrNull(ismultipart) || (ismultipart !== 'true' && ismultipart !== true && ismultipart !== 1 && ismultipart !== '1' && ismultipart !== 'yes' && ismultipart !== 'si' && ismultipart !== 'on')) {

        jQuery('#' + div).load(url + ' #' + div, params, function (responseTxt, statusTxt, xhr) {
            if (statusTxt === 'success') {
                unlockButton(btn);
                var f = func + '(responseTxt, statusTxt, xhr)';
                var hide = jQuery(btn).attr('hide');
                if (!isUndefinedOrNull(hide)) {
                    if (hide === 'true') {
                        try {
                            jQuery(btn).hide();
                        } catch (e) {
                        }
                    }
                } else {
                    try {
                        jQuery(btn).button('option', 'disabled', false);
                    } catch (e) {
                    }
                }
                try {
                    eval(f);
                } catch (e) {
                }
            } else {
                var divname = isUndefinedOrNull(div) ? 'waitDiv' : div + 'waitDiv';
                var msg = "Ha ocurrido un error inesperado: ";
                jQuery('#' + divname).hide();
                jQuery('#' + divname).remove();
                uialert('ERROR: ' + msg + xhr.status + " " + xhr.statusText, '¡Error Inesperado!');
            }
        });
    } else {
        var data = new FormData();
        jQuery("input[type=file]").each(function () {
            var files = jQuery(this)[0].files;
            if (!isUndefinedOrNull(files)) {
                var filenameseparator = jQuery(files).length > 1 ? '_' : '';
                jQuery.each(jQuery(this)[0].files, function (i, file) {
                    var filename = jQuery(this).attr('id');
                    filename = (isUndefinedOrNull(filename) ? 'archivo' : filename)
                            + ((!isUndefinedOrNull(filenameseparator) && filenameseparator !== '') ? (filenameseparator + i) : '');
                    data.append(filename, file);
                    console.log('search for(' + filename + ')');
                });
            }
        });

        jQuery.ajax({
            url: url,
            data: data,
            cache: false,
            contentType: false,
            processData: false,
            sync: false,
            type: 'POST'
        }).always(
                function (responseTxt, statusTxt, xhr) {
                    if (statusTxt === 'success') {
                        var divname = isUndefinedOrNull(div) ? 'waitDiv' : div + 'waitDiv';
                        jQuery('#' + divname).hide();
                        jQuery('#' + div).html(jQuery(responseTxt).find('#' + div).html() + '<div id="#errorMessages">' + jQuery(responseTxt).find('#errorMessages').html() + '</div>');
                        jQuery('#errorMessages').hide();
                        unlockButton(btn);
                        var f = func + '(responseTxt, statusTxt, xhr)';
                        var hide = jQuery(btn).attr('hide');
                        if (!isUndefinedOrNull(hide)) {
                            if (hide === 'true') {
                                try {
                                    jQuery(btn).hide();
                                } catch (e) {
                                }
                            }
                        } else {
                            try {
                                jQuery(btn).button('option', 'disabled', false);
                            } catch (e) {
                            }
                        }
                        try {
                            eval(f);
                        } catch (e) {
                        }
                    }
                }
        );
    }

};
var wait2 = function (element) {
    if (!isUndefinedOrNull(element) && !isUndefinedOrNull(jQuery(element).position())) {
        var width = 350;
        var height = 60;
        var offset = 30; // 22
        var outset = 6; // 22
        var message = 'Espere por favor...';
        var top = jQuery(element).height() / 2 - height / 2;
        var left = jQuery(element).width() / 2 - width / 2;
//        var divname = jQuery(element).attr('id');
        var divname = 'waitDiv';
//    console.log('element:'+element);
        var newDiv = '<div id="' + divname + '" class="ui-overlay ui-corner-all"  style="margin-top: ' + jQuery(element).css('marginTop') + '; margin-left:' + jQuery(element).css('marginLeft') + '; margin-right:' + jQuery(element).css('marginRight') + '; margin-bottom:' + jQuery(element).css('marginBottom') + '; width: ' + jQuery(element).width() + 'px; height: ' + jQuery(element).height() + 'px; position: absolute; left: ' + jQuery(element).position().left + 'px; top: ' + jQuery(element).position().top + 'px;">'
                + '<div class="ui-widget-overlay ui-corner-all"></div>'
                + '<div class="ui-widget-shadow ui-corner-all"            style="position: absolute; width: ' + (width + outset) + 'px; height: ' + (height + outset) + 'px; left: ' + left + 'px; top: ' + top + 'px;"></div>'
                + '<div class="ui-widget ui-widget-content ui-corner-all" style="vertical-align:middle; position: absolute; width: ' + (width - offset) + 'px; height: ' + (height - offset) + 'px; left: ' + (left + offset / 5) + 'px; top: ' + (top + offset / 5) + 'px; padding: 10px;"><div class="w20 L"><img style=" float:left; padding:0 0; margin: 0 0;" id="loadingIcon" src="/colas/images/loading.gif" title="' + message + '" /></div><div class="waitmessagetext w70 L">' + message + '</div></div></div>';
        jQuery(element).append(newDiv);
//        console.log(newDiv);
    }
};
jQuery(document).ready(function () {
    configureIOpenerButtons();
});
function alertError(msg) {
    if (jQuery(".failed").length) {
        jQuery(".failed").show().animate({right: "0px"}).find("ul").empty().html(msg);
    } else if (jQuery(".notice").length) {
        jQuery(".notice").show().animate({right: "0px"}).removeClass("notice").addClass("failed").find("ul").empty().html(msg);
    } else if (jQuery(".warning").length) {
        jQuery(".warning").show().animate({right: "0px"}).removeClass("warning").addClass("failed").find("ul").empty().html(msg);
    }
}
;
function alertNotice(msg) {
    if (jQuery(".notice").length) {
        jQuery(".notice").show().animate({right: "0px"}).find("ul").empty().html(msg);
    } else if (jQuery(".failed").length) {
        jQuery(".failed").show().animate({right: "0px"}).removeClass("failed").addClass("notice").find("ul").empty().html(msg);
    } else if (jQuery(".warning").length) {
        jQuery(".warning").show().animate({right: "0px"}).removeClass("warning").addClass("notice").find("ul").empty().html(msg);
    }
}
;
function alertWarning(msg) {
    if (jQuery(".warning").length) {
        jQuery(".warning").show().animate({right: "0px"}).find("ul").empty().html(msg);
    } else if (jQuery(".failed").length) {
        jQuery(".failed").show().animate({right: "0px"}).removeClass("failed").addClass("warning").find("ul").empty().html(msg);
    } else if (jQuery(".notice").length) {
        jQuery(".notice").show().animate({right: "0px"}).removeClass("notice").addClass("warning").find("ul").empty().html(msg);
    }
}
;