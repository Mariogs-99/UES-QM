jQuery.getScript('./js/wds.js');
var globalEmptyTableMessage = 'NO HAY DATOS.';
var globalErrorMessages;
var BrowserDetect = {
    init: function() {
        this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
        this.version = this.searchVersion(navigator.userAgent)
                || this.searchVersion(navigator.appVersion)
                || "an unknown version";
        this.OS = this.searchString(this.dataOS) || "an unknown OS";
    },
    searchString: function(data) {
        for (var i = 0; i < data.length; i++) {
            var dataString = data[i].string;
            var dataProp = data[i].prop;
            this.versionSearchString = data[i].versionSearch || data[i].identity;
            if (dataString) {
                if (dataString.indexOf(data[i].subString) !== -1)
                    return data[i].identity;
            }
            else if (dataProp)
                return data[i].identity;
        }
    },
    searchVersion: function(dataString) {
        var index = dataString.indexOf(this.versionSearchString);
        if (index === -1)
            return;
        return parseFloat(dataString.substring(index + this.versionSearchString.length + 1));
    },
    dataBrowser: [
        {
            string: navigator.userAgent,
            subString: "Chrome",
            identity: "Chrome"
        },
        {string: navigator.userAgent,
            subString: "OmniWeb",
            versionSearch: "OmniWeb/",
            identity: "OmniWeb"
        },
        {
            string: navigator.vendor,
            subString: "Apple",
            identity: "Safari",
            versionSearch: "Version"
        },
        {
            prop: window.opera,
            identity: "Opera",
            versionSearch: "Version"
        },
        {
            string: navigator.vendor,
            subString: "iCab",
            identity: "iCab"
        },
        {
            string: navigator.vendor,
            subString: "KDE",
            identity: "Konqueror"
        },
        {
            string: navigator.userAgent,
            subString: "Firefox",
            identity: "Firefox"
        },
        {
            string: navigator.vendor,
            subString: "Camino",
            identity: "Camino"
        },
        {// for newer Netscapes (6+)
            string: navigator.userAgent,
            subString: "Netscape",
            identity: "Netscape"
        },
        {
            string: navigator.userAgent,
            subString: "MSIE",
            identity: "Explorer",
            versionSearch: "MSIE"
        },
        {
            string: navigator.userAgent,
            subString: "Gecko",
            identity: "Mozilla",
            versionSearch: "rv"
        },
        {// for older Netscapes (4-)
            string: navigator.userAgent,
            subString: "Mozilla",
            identity: "Netscape",
            versionSearch: "Mozilla"
        }
    ],
    dataOS: [
        {
            string: navigator.platform,
            subString: "Win",
            identity: "Windows"
        },
        {
            string: navigator.platform,
            subString: "Mac",
            identity: "Mac"
        },
        {
            string: navigator.userAgent,
            subString: "iPhone",
            identity: "iPhone/iPod"
        },
        {
            string: navigator.platform,
            subString: "Linux",
            identity: "Linux"
        }
    ]

};

var init = function() {
    try {
        menuManager.configure();
        //TMessages.setMessageContainer(jQuery("div#tMessage"));
    } catch (Ext) {
        console.log(Ext);
    }

    try {
        BrowserDetect.init();
    } catch (e) {
        alert(e);
    }

    configureTMessages();
    configureInputs();
    configureDatePickers();
    configureDatePickershour();
    configureIWindows();
    configureAccordions();
    configureTables();
    configureScrollableDataTables();
    configureNOScrollableDataTables();
    configureNOScrollableDataTables1();
    configureNOScrollableDataTables2();
    configurePaginableDataTables();
    configureCollapsableTitles();

    configureButtons();
    configureSelectableCheckBoxes();
    configureReportButtons();
    configureUpdatingButtons();
    configureUpdatingLinks();
    configureSimpleUpdatingButtons();
    configureAjaxHeaders();
    configureRerportsButtons();

    configurereusablelists();
    configureNavigationTrees();
};

var nvl = function(source, out) {
    var ret = source;
    if (isUndefinedOrNull(ret)) {
        ret = out;
    }
    if (!isNaN(ret)) {
        ret = parseFloat(ret);
    }
    return ret;
};

var hideall = function(element) {
    jQuery(element).each(function() {
        jQuery(this).hide();
    });
};

var showall = function(element) {
    jQuery(element).each(function() {
        jQuery(this).show();
    });
};

var isUndefinedOrNull = function(o) {
    return (!o || o === null || o === undefined);
};

var validateObjectName = function(txt) {
    return  txt.replace(/[^a-zA-Z 0-9]+/g, '');
};

var replaceAll = function(txt, replace, with_this) {
    return txt.replace(new RegExp(replace, 'g'), with_this);
};

var getURLParameter = function(sURL, myParameterName) {
    //    var sURL = document.URL.toString();
    var sURL = './?' + sURL;
    if (sURL.indexOf("?") > 0) {
        var arrParams = sURL.split("?");

        var arrURLParams = arrParams[1].split("&");

        var arrParamNames = new Array(arrURLParams.length);
        var arrParamValues = new Array(arrURLParams.length);

        var i = 0;
        for (i = 0; i < arrURLParams.length; i++) {
            var sParam = arrURLParams[i].split("=");
            arrParamNames[i] = sParam[0];
            if (sParam[1] !== "")
                arrParamValues[i] = unescape(sParam[1]);
            else
                arrParamValues[i] = "";
        }

        for (i = 0; i < arrURLParams.length; i++) {
            if (arrParamNames[i] === myParameterName) {
                return arrParamValues[i];
            }
        }
    } else {
        return "";
    }
};

var configureDatePickers = function() {
    jQuery('.datepicker').each(function() {
        configureDatePicker(this);
    });
};

var configureDatePickershour = function() {
    jQuery('.datepickerhour').each(function() {
        configureDatePickerhour(this);
    });
};

var configureDatePickerhour = function(datepicker) {
    var hidebutton = jQuery(datepicker).attr('hidebutton');
    if (!isUndefinedOrNull(hidebutton) && (hidebutton === true || hidebutton === 'true' || hidebutton === 'yes' || hidebutton === 'on' || hidebutton === 'si')) {
        jQuery(datepicker).datepicker({
            monthNames: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
            monthNamesShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sept", "Oct", "Nov", "Dic"],
            dayNames: ["Domingo", "Lunes", "Martes", "MiÃƒÂ©rcoles", "Jueves", "Viernes", "SÃƒÂ¡bado"],
            dayNamesShort: ["Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"],
            dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
            dateFormat: "yyy-dd-mm HH:MM:ss",
            changeMonth: true,
            changeYear: true,
            yearRange: "1900:+0"
        });

    } else {
        jQuery(datepicker).datepicker({
            monthNames: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
            monthNamesShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sept", "Oct", "Nov", "Dic"],
            dayNames: ["Domingo", "Lunes", "Martes", "MiÃƒÂ©rcoles", "Jueves", "Viernes", "SÃƒÂ¡bado"],
            dayNamesShort: ["Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"],
            dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
            dateFormat: "dd/mm/yy",
            changeMonth: true,
            changeYear: true,
            showOn: "both",
            buttonImage: "images/calendar.gif",
            buttonImageOnly: true,
            yearRange: "1900:+0"
        });
    }
    jQuery(datepicker).mask('99/99/9999');
};

var configureDatePicker = function(datepicker) {
    var hidebutton = jQuery(datepicker).attr('hidebutton');
    if (!isUndefinedOrNull(hidebutton) && (hidebutton === true || hidebutton === 'true' || hidebutton === 'yes' || hidebutton === 'on' || hidebutton === 'si')) {
        jQuery(datepicker).datepicker({
            monthNames: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
            monthNamesShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sept", "Oct", "Nov", "Dic"],
            dayNames: ["Domingo", "Lunes", "Martes", "MiÃƒÂ©rcoles", "Jueves", "Viernes", "SÃƒÂ¡bado"],
            dayNamesShort: ["Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"],
            dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
            dateFormat: "dd/mm/yy",
            changeMonth: true,
            changeYear: true,
            yearRange: "1900:+0"
        });
    } else {
        jQuery(datepicker).datepicker({
            monthNames: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
            monthNamesShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sept", "Oct", "Nov", "Dic"],
            dayNames: ["Domingo", "Lunes", "Martes", "MiÃƒÂ©rcoles", "Jueves", "Viernes", "SÃƒÂ¡bado"],
            dayNamesShort: ["Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"],
            dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
            dateFormat: "dd/mm/yy",
            changeMonth: true,
            changeYear: true,
            showOn: "both",
            buttonImage: "images/calendar.gif",
            buttonImageOnly: true,
            yearRange: "1900:+0"
        });
    }
    jQuery(datepicker).mask('99/99/9999');
};

var configureInputs = function() {
    jQuery('.periodo').mask('99/9999');
    jQuery('.fecha-mask').mask('99/99/9999');
    jQuery('.formattednit').mask('9999-999999-999-9');
    jQuery('.numbertel').mask('9999-9999');
    jQuery('.periodo-g').mask('99-9999');
    jQuery('.numbersOnly').keyup(function() {
        this.value = this.value.replace(/[^\-?\d+$]/g, '');
    });
    jQuery('.numberOnly').keyup(function() {
        this.value = this.value.replace(/[^0-9\.]/g, '');
    });
    jQuery('.onlyAlphabet').keyup(function() {
        this.value = this.value.replace(/[^a-z A-Z\.]/g, '');
    });

//    jQuery('.moneyOnly').number(true, 2);
//    jQuery('.currencyFormat').number(true, 2);

    var oNumberMask = new Mask("$#,###.00", "number");
    jQuery(".currencyFormat").each(function() {
        oNumberMask.attach(this);
    });

    jQuery('.errorMessageItem').click(function() {
        var nextSpan = jQuery(this).next('span');
        console.log('next-span:' + nextSpan.html());
        if (nextSpan.is(':visible')) {
            nextSpan.hide();
        } else {
            nextSpan.show();
        }
    });
};

var configureAccordions = function() {
    jQuery(".acordion").accordion({
        collapsible: true,
        heightStyle: 'content',
        active: -1
    });
};

var justExpandTitle = function(title) {
    var nextDiv = jQuery(title).next('div:first');
    if (!nextDiv.is(':visible')) {
        var btn = jQuery(nextDiv).find('button.updatingDivButton:first');
        if (!isUndefinedOrNull(btn)) {
            var hide = jQuery(btn).attr('hide');
            if (!isUndefinedOrNull(hide) && (hide === 'true' || hide === 'on' || hide === 'si' || hide === true)) {
                jQuery(btn).hide();
            }
        }
        jQuery(nextDiv).show();
    }
};

var configureCollapsableTitles = function() {
    jQuery('.collapsableTitle').each(function() {
        jQuery(this).addClass('ui-button');
        jQuery(this).addClass('ui-widget');
        jQuery(this).addClass('ui-state-default');
        jQuery(this).addClass('ui-corner-all');
        jQuery(this).css("padding", "10px 0 10px 45px");
        var nextDiv = jQuery(this).next('div:first');
        if (!isUndefinedOrNull(nextDiv)) {
            jQuery(nextDiv).hide();
        }
    });
    jQuery('.collapsableTitle').click(function(event) {
//        console.log('collapsableTitle(' + jQuery(this).attr('id') + ') - CLICK event.target:' + jQuery(event.target).attr('id'));
        event.preventDefault();
        var nextDiv = jQuery(this).next('div:first');
        if (!isUndefinedOrNull(nextDiv)) {
            var isvisible = jQuery(nextDiv).is(':visible');
            if (isvisible) {
                jQuery(nextDiv).hide();
            } else {
                jQuery(nextDiv).show();
                var btn = jQuery(nextDiv).find('button.updatingDivButton:first');
//                console.log('founded button(' + btn.attr('id') + ')');
                if (!isUndefinedOrNull(btn)) {
                    var tbl = jQuery(nextDiv).find('table.list:first');
//                    console.log('founded button next table.list(' + tbl.html() + ')');
                    if (!isUndefinedOrNull(jQuery(tbl).html())) {
                        var recs = jQuery(tbl).data('pageCount');
                        recs = isNullOrUndefined(recs) ? 0 : parseInt(recs, 10);
//                        console.log('founded button next table.list RECS(' + recs + ')');
                        if (recs <= 0) {
                            jQuery(btn).click();
                        }
                    } else {
                        tbl = jQuery(nextDiv).find('table.displaytable').filter(function(index) {
                            return !isNullOrUndefined(jQuery(this).attr('id')) && jQuery(this).attr('id').length > 0;
                        }).first();
//                        console.log('founded button next table.displaytable(' + tbl.attr('id') + ')');
                        if (!isUndefinedOrNull(jQuery(tbl).html())) {
                            var recs = jQuery(tbl).find('tbody tr').length;
                            recs = isNullOrUndefined(recs) ? 0 : parseInt(recs, 10);
//                            console.log('founded button next table.displaytable RECS(' + recs + ')');
                            if (recs <= 0) {
                                jQuery(btn).click();
                            } else {
                                var tr1 = jQuery(tbl).find('tbody tr:first');
                                if (recs === 1 && !isUndefinedOrNull(tr1.html())
                                        && typeof (jQuery(tr1).find('td:first').html()) === 'string'
                                        && jQuery(tr1).find('td:first').html().indexOf(!isUndefinedOrNull(globalEmptyTableMessage) ? globalEmptyTableMessage : 'Tabla vac') !== -1) {
//                                    console.log('founded button next table.displaytable FORCE CLICK()');
                                    jQuery(btn).click();
                                } else {
//                                    console.log('founded button next table.displaytable FORCE disabled');
                                    var disabled = jQuery(btn).attr('disabled');
                                    if (!isUndefinedOrNull(disabled) && jQuery.trim(disabled) !== '') {
                                        jQuery(btn).button('option', 'disabled', true);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    });
};

var configureButtons = function() {
    jQuery('#buttonset').buttonset();
    jQuery('.radioExport').each(function() {
        var icon = jQuery(this).attr('icon');
        icon = isUndefinedOrNull(icon) ? 'ui-icon-note' : icon;
        jQuery(this).button({
            icons: {
                primary: icon
            }
        });
    });

    jQuery('.executeButton').button({
        icons: {
            primary: "ui-icon-gear"
        }
    });
    jQuery('.bookButton').button({
        icons: {
            primary: "ui-icon-note"
        }
    });
    jQuery('.returnButton').button({
        icons: {
            primary: "ui-icon-arrowreturnthick-1-w"
        }
    });
    jQuery('.denyButton').button({
        icons: {
            primary: "ui-icon-closethick"
        }
    });
    jQuery('.acceptButton').button({
        icons: {
            primary: "ui-icon-check"
        }
    });
    jQuery('.removeButton').button({
        icons: {
            primary: "ui-icon-closethick"
        }
    });
    jQuery('.continueButton').button({
        icons: {
            primary: "ui-icon-check"
        }
    });
    jQuery('.fwdButton').button({
        icons: {
            primary: "ui-icon-check"
        }
    });
    jQuery('.searchButton').button({
        icons: {
            primary: "ui-icon-search"
        }
    });
    jQuery('.findButton').button({
        icons: {
            primary: "ui-icon-search"
        }
    });
    jQuery('.cleanButton').button({
        icons: {
            primary: "ui-icon-refresh"
        }
    });
    jQuery('.clearButton').button({
        icons: {
            primary: "ui-icon-refresh"
        }
    });
    jQuery('input[type=button]').each(function() {
        if (jQuery(this).val() !== undefined && jQuery(this).val() !== null && jQuery(this).val().trim().length > 0) {
            jQuery(this).button({
                icons: {
                    primary: "ui-icon-refresh"
                }
            });
        }
    });
    jQuery('.addItemButton').button({
        icons: {
            primary: "ui-icon-plusthick"
        }
    });
    jQuery('.addButton').button({
        icons: {
            primary: "ui-icon-plusthick"
        }
    });
    jQuery(".editButton").button({
        icons: {
            primary: "ui-icon-pencil"
        }
    });
    jQuery(".showButton").button({
        icons: {
            primary: "ui-icon-circle-zoomout"
        }
    });
    jQuery(".saveButton").button({
        icons: {
            primary: "ui-icon-disk"
        }
    });
    jQuery(".guardarButton").button({
        icons: {
            primary: "ui-icon-disk"
        }
    });
    jQuery(".cancelButton").button({
        icons: {
            primary: "ui-icon-arrowreturnthick-1-w"
        }
    });
    jQuery(".closeButton").button({
        icons: {
            primary: "ui-icon-locked"
        }
    });
    jQuery(".nextButton").button({
        icons: {
            primary: "ui-icon-circle-arrow-e"
        }
    });
    jQuery(".prevButton").button({
        icons: {
            primary: "ui-icon-circle-arrow-w"
        }
    });
    jQuery(".reportButton").button({
        icons: {
            primary: "ui-icon-print"
        }
    });
    jQuery(".uploadButton").button({
        icons: {
            primary: "ui-icon-arrowthickstop-1-n"
        }
    });
    jQuery(".calcButton").button({
        icons: {
            primary: "ui-icon-calculator"
        }
    });
    jQuery(".personButton").button({
        icons: {
            primary: "ui-icon-person"
        }
    });
    jQuery(".inactivateButton").button({
        icons: {
            primary: "ui-icon-cancel"
        }
    });
    jQuery(".generateButton").button({
        icons: {
            primary: "ui-icon-script"
        }
    });
    jQuery(".reportContainer").button({
        icons: {
            primary: "ui-icon-circle-arrow-s"
        }
    });
    jQuery(".seekNext").button({
        icons: {
            primary: "ui-icon-seek-next"
        }
    });
    jQuery(".seekPrev").button({
        icons: {
            primary: "ui-icon-seek-prev"
        }
    });

};


var configureNavigationTrees = function() {
    jQuery(".navigationtree").treeview({
        persist: "location",
        collapsed: true,
        unique: true
    });
    jQuery(".treetable").treetable({
        expandable: true,
        clickableNodeNames: true,
        indent: 30,
        stringExpand: "Expandir",
        stringCollapse: "Colapsar"
    });
};

function createComboBox() {
    (function(jQuery) {
        jQuery.widget("custom.combobox", {
            _create: function() {
                this.title = jQuery(this.element).data("title");
                this.wrapper = jQuery("<span>")
                        .addClass("custom-combobox")
                        .insertAfter(this.element)
                        .attr('id', this.element[0].id + "_combobox");
                this.element.hide();
                this._createAutocomplete();
                this._createShowAllButton();
            },
            _createAutocomplete: function() {
                var selected = this.element.children(":selected"),
                        value = selected.val() ? selected.text() : "";

                this.input = jQuery("<input>")
                        .appendTo(this.wrapper)
                        .val(value)
                        .attr("title", "")
                        .addClass("custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left")
                        .autocomplete({
                    delay: 0,
                    minLength: 0,
                    source: jQuery.proxy(this, "_source")
                })
                        .tooltip({
                    tooltipClass: "ui-state-highlight"
                });

                this._on(this.input, {
                    autocompleteselect: function(event, ui) {
                        ui.item.option.selected = true;
                        this._trigger("select", event, {
                            item: ui.item.option
                        });
                    },
                    autocompletechange: "_removeIfInvalid"
                });      
            },
            _createShowAllButton: function() {
                var input = this.input,
                        wasOpen = false;

                jQuery("<a>")
                        .attr("tabIndex", -1)
                        .attr("title", this.title)
                        .tooltip()
                        .appendTo(this.wrapper)
                        .button({
                    icons: {
                        primary: "ui-icon-triangle-1-s"
                    },
                    text: false
                })
                        .removeClass("ui-corner-all")
                        .addClass("custom-combobox-toggle ui-corner-right")
                        .mousedown(function() {
                    wasOpen = input.autocomplete("widget").is(":visible");
                })
                        .click(function() {
                    input.focus();

                    // Close if already visible
                    if (wasOpen) {
                        return;
                    }

                    // Pass empty string as value to search for, displaying all results
                    input.autocomplete("search", "");
                });      
            },
            _source: function(request, response) {
                var matcher = new RegExp(jQuery.ui.autocomplete.escapeRegex(request.term), "i");
                response(this.element.children("option").map(function() {
                    var text = jQuery(this).text();
                    if (this.value && (!request.term || matcher.test(text)))
                        return {
                            label: text,
                            value: text,
                            option: this
                        };
                }));
            },
            _removeIfInvalid: function(event, ui) {

                // Selected an item, nothing to do
                if (ui.item) {
                    return;
                }

                // Search for a match (case-insensitive)
                var value = this.input.val(),
                        valueLowerCase = value.toLowerCase(),
                        valid = false;
                this.element.children("option").each(function() {
                    if (jQuery(this).text().toLowerCase() === valueLowerCase) {
                        this.selected = valid = true;
                        return false;
                    }
                });

                // Found a match, nothing to do
                if (valid) {
                    return;
                }

                // Remove invalid value
                this.input
                        .val("")
                        .attr("title", "version "+value+ " de formulario "+jQuery("#formulario_combobox > input").val()+" no se encuentra")
                        .tooltip("open");
                this.element.val("");
                this._delay(function() {
                    this.input.tooltip("close").attr("title", "");
                }, 2500);
                this.input.autocomplete("instance").term = "";
            },
            _destroy: function() {
                this.wrapper.remove();
                this.element.show();
            }
        });
    })(jQuery);
}


var full_name = function (nit, divId, conGuiones, isImprenta) {

    if (conGuiones) {
        nit = nit.replace(/-/g, "");
    }

    if (isImprenta) {
        _url = CTX + "/ruc/nombre/completo/imprenta/" + nit;
    } else {
        _url = CTX + "/ruc/nombre/completo/" + nit;
    }
    var div = jQuery("#" + divId);
    jQuery.ajax({
        url: _url
    }).success(function(xhr) {
        var html = '<div class="w100 L">'+xhr+'</div>';
        document.getElementById("nombre_completo").innerHTML="";
        jQuery(html).appendTo("div#nombre_completo");
        //jQuery("#" + divId).html(xhr);
    }).error(function(e) {
        var html = 'NIT NO EXISTE';
        document.getElementById("nombre_completo").innerHTML="";
        jQuery(html).appendTo("div#nombre_completo");
    });
}
