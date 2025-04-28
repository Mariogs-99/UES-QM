/*
 * 
 * Requiere jQuery
 */
jQuery.getScript('./js/plugins-detect.js');
jQuery.getScript('./js/wds.js');
var const_iframe = "-iframe";
var const_dialog = 'dialog';
var reducedPercent = 0.60;
var reducedWidth = jQuery(window).width() * reducedPercent;
var reducedHeight = jQuery(window).height() * reducedPercent;

var configureReportButtons = function() {
    jQuery(".opener").each(function() {
        configureRerportsButton(this);
    });
    jQuery(".openerButton").each(function() {
        configureRerportsButton(this);
        var icon = jQuery(this).attr('icon');
        icon = isUndefinedOrNull(icon) ? 'ui-icon-print' : icon;
        jQuery(this).button({
            icons: {
                primary: icon
            }
        });
    });
    jQuery("#opener").each(function() {
        configureRerportsButton(this);
    });
    configureRerportsButtons();
};

var configureRerportsButtons = function() {
    jQuery('.reportsButton').each(function() {
        configureRerportsButton(this);
    });

};
var configureRerportsButton = function(openerButton) {
    var configured = jQuery(openerButton).attr('configured');
    if (isUndefinedOrNull(configured)) {
        jQuery(openerButton).attr('configured', true);
        jQuery(openerButton).click(function(event) {
            event.preventDefault();
            openReport(openerButton);
        });
    }
};

var lockReportButton = function(openerButton) {
    jQuery(openerButton).addClass('disabled');
};
var unlockReportButton = function(openerButton) {
    jQuery(openerButton).removeClass('disabled');
};

var closeReport = function(openerButton, dialog) {
    unlockReportButton(openerButton);
    jQuery(dialog).dialog('close');
};

var openReport = function(openerButton) {
	if (!jQuery(openerButton).hasClass('disabled')) {
        lockReportButton(openerButton);
        var updateUrl = jQuery(openerButton).attr('url') + replaceAll(jQuery(openerButton).attr('title'), ' ', '');
        if (!isUndefinedOrNull(updateUrl)) {
            var dialogId = createIWindow(openerButton);
            jQuery('#' + dialogId).html('<iframe id="' + dialogId + const_iframe + '" style="width:100%; " src="./loading"></iframe>');
            jQuery('#' + dialogId).dialog('open');
            jQuery('#' + dialogId).dialog('widget').find('.ui-dialog-buttonset .ui-button:first').next().button().hide();
            var args = jQuery(openerButton).closest('form').serialize();
            var itemId = jQuery(openerButton).attr('itemid');
            if (!isUndefinedOrNull(itemId)) {
                args += '&recid=' + itemId;
            }
            var dataDiv = jQuery(openerButton).attr('dataDiv');
//            console.log('dataDiv(' + dataDiv + ')');
            if (!isUndefinedOrNull(dataDiv)) {
                var inputs = jQuery('#' + dataDiv).find(':input:not([type=button])');
                if (!isUndefinedOrNull(inputs)) {
                    for (var i = 0; i < inputs.length; i++) {
                        var val;
                        var vname = jQuery(inputs[i]).attr('id');
//                        console.log('validating(' + vname + ')');
                        try {
                            val = jQuery(inputs[i]).val();
                        } catch (e) {
                        }
                        if (!isUndefinedOrNull(val)) {
                            args += '&' + vname + '=' + escape(val);
//                            console.log(params);
                        }
                    }
                }
            }
//            console.log('reporting service before calling server...');
            try {
                jQuery.post(updateUrl, args) //, function(reportName) {
                        .done(function(reportName) {
//                            console.log('reportName:'+reportName+', is null:'+isUndefinedOrNull(reportName));
                            if (!isUndefinedOrNull(reportName)) {
                                updatediv(openerButton);

//                console.log('reporting service doing call to server...');
//                    console.log('reportName=' + reportName);
                                if (!isNullOrUndefined(reportName) && (typeof reportName) === 'string' && reportName.indexOf('object XMLDocument') === -1) {
                                    var showEmbedded = (pluginlist.indexOf('Acrobat Reader') !== -1);
                                    var format = jQuery(openerButton).attr('format');
                                    try {
                                        format = isUndefinedOrNull(format) ? getURLParameter(args, 'formato') : format;
                                    } catch (e) {
                                    }
                                    format = isUndefinedOrNull(format) ? 'pdf' : format;
//                        console.log('reporting service calling "./report/report/' + reportName + '"...');
                                    if (format === 'pdf' && (BrowserDetect.browser === 'Chrome' || (BrowserDetect.browser !== 'Chrome' && showEmbedded))) {
                                        jQuery('#' + dialogId).dialog('option', 'buttons', [{
                                                text: 'Ampliar',
                                                click: function() {
                                                    var firstButton = '.ui-dialog-buttonset .ui-button:first';
                                                    jQuery(this).dialog({
                                                        width: jQuery(window).width(),
                                                        height: jQuery(window).height(),
                                                        position: "top-left"
                                                    });
                                                    jQuery(this).dialog('widget').find(firstButton).button().hide();
                                                    jQuery(this).dialog('widget').find(firstButton).next().button().show();
                                                }
                                            },
                                            {
                                                text: 'Reducir',
                                                click: function() {
                                                    var firstButton = '.ui-dialog-buttonset .ui-button:first';
                                                    jQuery(this).dialog({
                                                        width: reducedWidth,
                                                        height: reducedHeight
                                                    });
                                                    jQuery(this).dialog('widget').find(firstButton).button().show();
                                                    jQuery(this).dialog('widget').find(firstButton).next().button().hide();
                                                }
                                            },
                                            {
                                                text: 'Cerrar',
                                                click: function() {
                                                    jQuery(this).dialog('close');
                                                }
                                            }
                                        ]);
                                        jQuery('#' + dialogId).dialog('widget').find('.ui-dialog-buttonset .ui-button:first').button().click();
                                        jQuery('#' + dialogId).html('<iframe id="' + dialogId + const_iframe + '" style="width:100%; height: 100%;" src="./report/report/' + reportName + '"></iframe>');
                                        jQuery('#' + dialogId).hide().show('fast');
                                        unlockReportButton(openerButton);
                                    } else {
                                        closeReport(openerButton, '#' + dialogId);                                                                        
                                        jQuery('#' + dialogId).html('<script type="text/javascript"> window.open("./report/report/' + reportName + '","Report Viewer","width=600,height=400"); </script>');
                                    }
                                } else {
                                    closeReport(openerButton, '#' + dialogId);
                                    alert('No se pudo generar el reporte. Por favor revise que los campos requeridos esten completos. Gracias');
                                }
                            } else {
                                closeReport(openerButton, '#' + dialogId);
                                updatebyerrordiv(openerButton);
                                console.log('ERROR');
                            }
                        }).fail(function(xhr) {
                    unlockReportButton(openerButton);
                    updatediv(openerButton);
                    var msg = "Ha ocurrido un error inesperado: ";
                    jQuery('#' + dialogId).html(msg + xhr.status + " " + xhr.statusText);
                    try {
                        refreshErrors();
                    } catch (e) {
                        console.log(e);
                    }
                });
            } catch (e) {

            }
        } else {
            console.log('opener button must have an "url" attribute.');
        }
//    } else {
//        console.log('blocked by button max tries...');
    }
    var onClose = jQuery(openerButton).attr('onClose');
    var fn = window[onClose];            
    if(typeof fn === 'undefined'){
    	
    }else{
    	fn();
    } 
};

var updatebyerrordiv = function(openerButton) {
    // begin changes for updating div
    try {
        var updateDiv = jQuery(openerButton).attr('updateDiv');
        if (!isUndefinedOrNull(updateDiv)) {
            var updateUrl = jQuery(openerButton).attr('updateUrl');
            updateUrl = isUndefinedOrNull(updateUrl) ? jQuery(openerButton).attr('url') + "reporterrors" : updateUrl;
            if (!isUndefinedOrNull(updateUrl)) {
                var afterUpdateFunction = jQuery(openerButton).attr('afterUpdateFunction');
                if (!isUndefinedOrNull(afterUpdateFunction)) {
                    doLoad(openerButton, updateUrl, updateDiv, afterUpdateFunction);
                } else {
                    console.log('updateDiv-afterUpdateFunction was null.');
                }
            } else {
                console.log('updateDiv-updateUrl was null.');
            }
        }
    } catch (e) {
        console.log(e);
    }
    // end changes for updating div
};
var updatediv = function(openerButton) {
    // begin changes for updating div
    try {
        var updateDiv = jQuery(openerButton).attr('updateDiv');
        if (!isUndefinedOrNull(updateDiv)) {
            var updateUrl = jQuery(openerButton).attr('updateUrl');
            if (!isUndefinedOrNull(updateUrl)) {
                var afterUpdateFunction = jQuery(openerButton).attr('afterUpdateFunction');
                if (!isUndefinedOrNull(afterUpdateFunction)) {
                    doLoad(openerButton, updateUrl, updateDiv, afterUpdateFunction);
                } else {
                    console.log('updateDiv-afterUpdateFunction was null.');
                }
            } else {
                console.log('updateDiv-updateUrl was null.');
            }
        }
    } catch (e) {
        console.log(e);
    }
    // end changes for updating div
};
var createIWindow = function(openerButton) {
//    console.log('creando nueva iwindow!');
    var caption = (!jQuery(openerButton).attr('caption') || jQuery(openerButton).attr('caption') === undefined) ? (!jQuery(openerButton).attr('title') || jQuery(openerButton).attr('title') === undefined) ? jQuery(openerButton).text() : jQuery(openerButton).attr('title') : jQuery(openerButton).attr('caption');
//    caption = isUndefinedOrNull(caption) ? (!jQuery(openerButton).attr('title') || jQuery(openerButton).attr('title') === undefined) ? jQuery(openerButton).text() : jQuery(openerButton).attr('title') : caption;
    var modal = jQuery(openerButton).attr('modal');
    modal = isUndefinedOrNull(modal) ? false : (modal === 'true' || modal === 'si' || modal === 'on' || modal === 'modal');
    var dialogId = const_dialog + ((caption === undefined || caption === '') ? '' : replaceAll(caption, ' ', ''));
    dialogId = validateObjectName(dialogId);
    var minWidth = 460;
    var minHeight = 130;
    var index = 1;
    while (jQuery('#' + dialogId).html() && index < 100) {
        dialogId += (index++);
    }
    if (jQuery('#' + dialogId).html()) {
        dialogId = const_dialog + ((caption === undefined || caption === '') ? '' : replaceAll(caption, ' ', ''));
        jQuery('#' + dialogId).remove();
    }
    var iframeText = '';
    var div = jQuery('<div id="' + dialogId + '"></div>');
    jQuery(div).append(iframeText);
    jQuery('body').append(div);
    modal = (modal === undefined || modal === '') ? false : modal === 'true';
    jQuery('#' + dialogId).dialog({
        title: '<span class="ui-icon ui-icon-print" style="display: inline-table; vertical-align:middle; "></span><span  style="display: inline-table; vertical-align:middle;">' + caption + '</span>',
        icon: "ui-icon-folder-open",
        modal: modal,
        autoOpen: false,
        width: minWidth,
        height: minHeight
//        ,
//        open: function(event, ui) {
//            var titleBar = jQuery(this).parent().find('.ui-dialog-titlebar');
//            var titleBarHtml = jQuery(titleBar).html();
//        }
    });
    return dialogId;
};


