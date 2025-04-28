/**
 * async.js
 * Requiere jQuery
 *  */

var req;
var which;
var globalDivId;

var closeMessages = function(item) {
    jQuery(item).parents().find('div.jGrowl-notification').next().each(function() {
        if ((jQuery(this).attr('class').indexOf('loading') !== -1)) {
            jQuery(this).trigger('jGrowl.beforeClose');
        }
    });
};

var checkMessages = function(messages) {
    if (messages !== undefined && messages.trim() !== '') {
        jQuery.jGrowl(messages, {
            header: '<h1 class=\"error\"><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;">&nbsp;</span>Advertencia</h1>',
            theme: 'manilla',
            themeState: 'highlight',
            speed: 'slow',
            sticky: true,
            beforeOpen: function(e, m, o) {
                console.log("simpleErrorMessages is going to be opened!", this);
            },
            open: function(e, m, o) {
                console.log("simpleErrorMessages has been opened!", this);
            },
            beforeClose: function(e, m, o) {
                console.log("simpleErrorMessages is going to be closed!", this);
            },
            close: function(e, m, o) {
                console.log("simpleErrorMessages has been closed!", this);
            }
        });
    }
};
function getFormAsString(formName) {
    returnString = "";
    formElements = document.forms[formName].elements;
    for (var i = formElements.length - 1; i >= 0; --i) {
        if (formElements[i].type === 'radio') {
            if (formElements[i].checked === true) {
                returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value);
            }
        } else if (formElements[i].type === 'select-multiple') {
            var options = formElements[i].options;
            for (var j = 0; j < options.length; j++) {
                var option = options[j];
                if (option.selected === true) {
                    returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(option.value);
                }
            }
        } else {
            returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value).split('+').join('%2B');
        }
    }
    return returnString;
}

function getFormAsStringById(formId) {
    returnString = "";
    formElements = jQuery(formId).elements;
    for (var i = formElements.length - 1; i >= 0; --i) {
        if (formElements[i].type === 'radio') {
            if (formElements[i].checked === true) {
                returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value);
            }
        } else if (formElements[i].type === 'select-multiple') {
            var options = formElements[i].options;
            for (var j = 0; j < options.length; j++) {
                var option = options[j];
                if (option.selected === true) {
                    returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(option.value);
                }
            }
        } else {
            returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value);
        }
    }
    return returnString;
}

function getXMLHttpRequest() {
    if (window.XMLHttpRequest) { // Non-IE browsers
        req = new XMLHttpRequest();
    } else if (window.ActiveXObject) { // IE
        req = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return req;
}
function retrieveURLFormPost(url, formName, divId) {
    parameter = getFormAsString(formName);
//    showLoading();
    retrieveURLPost(url, parameter, divId);
}

function retrieveURLFormPostById(url, formId, divId) {
    parameter = Form.serialize(jQuery(formId));
//    showLoading();
    retrieveURLPost(url, parameter, divId);
}

function retrieveURLFormGet(url, formName, divId) {
    parameter = getFormAsString(formName);
    retrieveURLPost(url, parameter, divId);
}

function retrieveURLPost(url, parameters, divId) {
//    showLoading();
    globalDivId = divId;
    req = getXMLHttpRequest();
    req.onreadystatechange = processStateChange;
    if (req) {
        try {
            req.open("POST", url, true);
            req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=ISO-8859-1");
            req.send(parameters);
        } catch (e) {
            alert("Problem Communicating with Server\n" + e);
        }
    }
}
function retrieveURLPostFunction(url, parameters, divId,func) {
//    showLoading();
    globalDivId = divId;
    req = getXMLHttpRequest();
    req.onreadystatechange = processStateChangeFunction(func);
    if (req) {
        try {
            req.open("POST", url, true);
            req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=ISO-8859-1");
            req.send(parameters);
        } catch (e) {
            alert("Problem Communicating with Server\n" + e);
        }
    }
}

function retrieveURLGet(url, parameters, divId) {
    globalDivId = divId;
    req = getXMLHttpRequest();
    req.onreadystatechange = processStateChange;
    if (req) {
        try {
            req.open("GET", url, true);
            req.send(parameters);
        } catch (e) {
            alert("Problem Communicating with Server. " + e);
        }
    }
}

function processStateChange() {
    if (req.readyState === 4) { // Complete
        if (req.status === 200) { // OK response
            updateSession();
            newHtml = getNewHtml(req.responseText, globalDivId);
            replaceExistingDivWithNewHtml(newHtml, globalDivId);
            globalDivId = null;
//            hideLoading();
            try {
                postCommit();
            } catch (e) {
            }
        } else {
            alert("Session Finalizada con Servidor , vuelva a ingresar." + req.statusText);
        }
    }
}
function processStateChangeFunction(func) {
    if (req.readyState === 4) { // Complete
        if (req.status === 200) { // OK response
            updateSession();
            newHtml = getNewHtml(req.responseText, globalDivId);
            replaceExistingDivWithNewHtml(newHtml, globalDivId);
            globalDivId = null;
//            hideLoading();
            try {
                func();
            } catch (e) {
            }
        } else {
            alert("Session Finalizada con Servidor , vuelva a ingresar." + req.statusText);
        }
    }
}

function splitTextIntoSpan(textToSplit, spanId) {
    posicionInicial = textToSplit.search('<span id="' + spanId + '"');
    posicionFinal = textToSplit.indexOf("</span>", posicionInicial);
    spanText = textToSplit.substr(posicionInicial, posicionFinal - posicionInicial);
    returnElements = new Array(1);
    returnElements[0] = spanText;
    return returnElements;
}

function countDivs(text) {
    contadorDivOpen = 0;
    pos = text.search('<div');
    while (pos > 0) {
        contadorDivOpen++;
        pos = text.indexOf("<div", pos + 1);
    }
    return contadorDivOpen;
}

function replaceExistingWithNewHtml(newTextElements) {
    for (var i = newTextElements.length - 1; i >= 0; --i) {
        if (newTextElements[i].indexOf("<span") > -1) {
            startNamePos = newTextElements[i].indexOf('"') + 1;
            endNamePos = newTextElements[i].indexOf('"', startNamePos);
            name = newTextElements[i].substring(startNamePos, endNamePos);
            startContentPos = newTextElements[i].indexOf('>') + 1;
            content = newTextElements[i].substring(startContentPos);
            if (document.getElementById(name)) {
//                alert("Replacing Element:" + name);
                document.getElementById(name).innerHTML = content;
            } else {
                //alert("Element: "+name+" not found in existing document");
            }
        }
    }
}

function replaceExistingDivWithNewHtml(newHtmls, divId) {
    var divIds = divId.split(' ');
    for (var i = 0; i < divIds.length; i++) {
        if (document.getElementById(divIds[i])) {
            for (var j = 0; j < newHtmls.length; j++) {
                if (newHtmls[j][0] === divIds[i]) {
                    document.getElementById(divIds[i]).innerHTML = newHtmls[j][1];
                }
            }
        }
    }
}


function getNewHtml(requestText, divId) {
    var texts = [];
    var divIds;
    try {
        divIds = divId.split(' ');
        divAjax = document.createElement("div");
        divAjax.innerHTML = requestText;
        divs = divAjax.getElementsByTagName("div");
        for (var i = 0; i < divs.length; i++) {
            id = divs[i].getAttribute("id");
            for (var j = 0; j < divIds.length; j++) {
                if (id === divIds[j]) {
                    var currentTextIndex = texts.length;
                    texts[currentTextIndex] = [2];
                    texts[currentTextIndex][0] = id;
                    texts[currentTextIndex][1] = divs[i].innerHTML;
                    break;
                }
            }
        }
        divAjax.innerHTML = "";
        divs = null;
        divAjax = null;
    } catch (e) {
    }
    return texts;
}


function updateSession() {
    jQuery("warningMessage").css("display", "none");
    jQuery("timeoutMessage").css("display", "none");
//    clearInterval(warningTimer);
//    clearInterval(timeoutTimer);
//    warningTimer = setTimeout(onWarning, WARNING_TIME);
//    timeoutTimer = setTimeout(onTimeout, TIMEOUT_TIME);
}

function toggleFolder(url, parameters, div, toogleButton) {
    var divReplace = jQuery(div);
    if (divReplace.className === "showDiv") {
        divReplace.className = "hiddenDiv";
        toogleButton.value = "Mostrar >>";
    } else {
        divReplace.className = "showDiv";
        toogleButton.value = "<< Ocultar";
        retrieveURLPost(url, parameters, div);
    }
}

function toggleFolderWithForm(url, formName, div, toogleButton) {
    var divReplace = jQuery(div);
    if (divReplace.className === "showDiv") {
        divReplace.className = "hiddenDiv";
        toogleButton.value = "Mostrar >>";
    } else {
        retrieveURLFormPost(url, formName, div);
        divReplace.className = "showDiv";
        toogleButton.value = "<< Ocultar";
    }
}

function initProgress(progressDiv) {
    Element.show(progressDiv);
}

function resetProgress(progressDiv) {
    Effect.Fade(progressDiv);
}

function reportError(errorDiv) {
    jQuery(errorDiv).innerHTML = "Html content busted!";
    Element.show(errorDiv);
    setTimeout("Effect.DropOut('" + errorDiv + "')", 2500);
}


