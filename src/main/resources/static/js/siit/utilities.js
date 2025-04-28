/*requiere jQuery*/

function isNullOrUndefined(val) {
    return (val === undefined || val === null);
}


function getUrlParam(param) {
    param = param.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
    var r1 = "[\\?&]" + param + "=([^&#]*)";
    var r2 = new RegExp(r1);
    var r3 = r2.exec(window.location.href);
    if (r3 === null) {
        return "";
    } else {
        return r3[1];
    }
}


function UpdateQueryString(key, value, url) {
    if (!url) {
        url = window.location.href;
    }
    var re = new RegExp("([?|&])" + key + "=.*?(&|#|$)", "gi");
    if (url.match(re)) {
        if (value) {
            return url.replace(re, '$1' + key + "=" + value + '$2');
        } else {
            return url.replace(re, '$2');
        }
    } else {
        if (value) {
            var separator = url.indexOf('?') !== -1 ? '&' : '?',
                    hash = url.split('#');
            url = hash[0] + separator + key + '=' + value;
            if (hash[1]) {
                url += '#' + hash[1];
            }
            return url;
        } else {
            return url;
        }
    }
}


var URLParser = function(url) {
    if (!url) {
        url = window.location.href;
    }
    var path = "", query = "", hash = "", params;
    if (url.indexOf("#") > 0) {
        hash = url.substr(url.indexOf("#") + 1);
        url = url.substr(0, url.indexOf("#"));
    }
    if (url.indexOf("?") > 0) {
        path = url.substr(0, url.indexOf("?"));
        query = url.substr(url.indexOf("?") + 1);
        params = query.split('&');
    } else {
        path = url;
    }

    return {
        getUrl: function() {
            return url;
        },
        getHost: function() {
            var hostexp = /\/\/([\w.-]*)/;
            var match = hostexp.exec(path);
            if (match !== null && match.length > 1) {
                return match[1];
            }
            return "";
        },
        getPath: function() {
            var pathexp = /\/\/[\w.-]*(?:\/([^?]*))/;
            var match = pathexp.exec(path);
            if (match !== null && match.length > 1) {
                return match[1];
            }
            return "";
        },
        getHash: function() {
            return hash;
        },
        getParams: function() {
            return params;
        },
        getQuery: function() {
            return query;
        },
        setHash: function(value) {
            if (query.length > 0) {
                query = "?" + query;
            }
            if (value.length > 0) {
                query = query + "#" + value;
            }
            return path + query;
        },
        setParam: function(name, value) {
            if (!params) {
                params = new Array();
            }
            params.push(name + '=' + value);
            for (var i = 0; i < params.length; i++) {
                if (query.length > 0) {
                    query += "&";
                }
                query += params[i];
            }
            if (query.length > 0) {
                query = "?" + query;
            }
            if (hash.length > 0) {
                query = query + "#" + hash;
            }
            return path + query;
        },
        getParam: function(name) {
            if (params) {
                for (var i = 0; i < params.length; i++) {
                    var pair = params[i].split('=');
                    if (decodeURIComponent(pair[0]) === name) {
                        return decodeURIComponent(pair[1]);
                    }
                }
            }
            console.log('Query variable %s not found', name);
        },
        hasParam: function(name) {
            if (params) {
                for (var i = 0; i < params.length; i++) {
                    var pair = params[i].split('=');
                    if (decodeURIComponent(pair[0]) === name) {
                        return true;
                    }
                }
            }
            console.log('Query variable %s not found', name);
        },
        removeParam: function(name) {
            query = "";
            if (params) {
                var newparams = new Array();
                for (var i = 0; i < params.length; i++) {
                    var pair = params[i].split('=');
                    if (decodeURIComponent(pair[0]) !== name) {
                        newparams.push(params[i]);
                    }
                }
                params = newparams;
                for (var i = 0; i < params.length; i++) {
                    if (query.length > 0) {
                        query += "&";
                    }
                    query += params[i];
                }
            }
            if (query.length > 0) {
                query = "?" + query;
            }
            if (hash.length > 0) {
                query = query + "#" + hash;
            }
            return path + query;
        }
    };
};

