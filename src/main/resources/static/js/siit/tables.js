/*
 * 
 * Requiere jQuery
 * TMessages
 * utilities/utilities.js
 */


var TableManager = {
    paginate: {}

};


var tableRefresh = function(table) {
    var url = jQuery(table).data("urlPrefix");
    if (!isUndefinedOrNull(url)) {
        var currentpage = jQuery(table).attr('currentpage');
//        console.log('currentpage:'+currentpage);
        currentpage = isUndefinedOrNull(currentpage) ? 1 : currentpage;
        tableGo(table, url + '?page=' + currentpage);
    }
};

var tableGo = function(table, href) {
    var tbody = jQuery(table).find("tbody");
    try {
        wait('#' + tbody.attr('id'));
    } catch (e) {
    }
    var ref = jQuery(table).data("divRefresh");
    var div = (!isUndefinedOrNull(ref)) ? jQuery("#" + ref) : tbody;
    var id = (!isUndefinedOrNull(ref)) ? " #" + div.attr("id") + " table" : " #" + tbody.attr("id") + " tr";
    div.load(href + id, function(responseTxt, statusTxt, xhr) {
//    tbody.load(href + " #" + tbody.attr("id") + " tr", function(responseTxt, statusTxt, xhr) {
        if (statusTxt === 'error') {
            jQuery('#' + tbody.attr('id') + 'waitDiv').remove();
            var msg = "Ha ocurrido un error inesperado: ";
            uialert('ERROR: ' + msg + xhr.status + " " + xhr.statusText, 'Â¡Error Inesperado!');
        }
        setTRBehaviorTlist(0, table);
        postTRBehaviorTlist(0, table);
    });
};

function createPaginator(pageCount, table) {
    var ul = jQuery("<div>", {"class": "paginator"});
    var li = null;
    var recs = jQuery(table).data('pageCount');
    recs = isNullOrUndefined(recs) ? 0 : parseInt(recs, 10);

    var urlPrefix = isNullOrUndefined(table.data("urlPrefix")) ? "" : table.data("urlPrefix");
    for (var i = 1; i <= pageCount; i++) {
        li = jQuery("<div>", {"class": "li"});
        var a = jQuery("<a>", {"href": UpdateQueryString("page", i, urlPrefix)}).text(i);
        if (!isNaN(parseInt(getUrlParam("page")))) {
            if (parseInt(getUrlParam("page")) === i) {
                a.addClass("active");
                jQuery(table).find("tbody").attr('currentpage', i);
            }
        }
        a.click(
                function(e) {
                    e.preventDefault();
                    transitionPaginator(this);
                }
        );
        li.append(a);
        ul.append(li);

    }
    return ul;
}
var postTRBehaviorTlist = function(index, table) {
    try {
        var postFunctionName = jQuery(table).attr('id') + 'PostTRBehaviorTlist()';
        try {
            jQuery(table).attr('postTRBehaviorTlist="' + postFunctionName);
            eval(postFunctionName + ';');
        } catch (e) {
//            console.log('could not find javascript function  postFunctionName, was ignored.');
        }
        configureAjaxHeaders('#' + jQuery(table).attr('id'));
    } catch (e) {
    }
};

var configureTable = function(index, table) {
    var t = jQuery(table);
    configure_editable_table(t);
    var tfoot = t.find("tfoot");
    if (tfoot.length > 0) {
        var firstRow = t.find("tbody tr:first");
        var hidepaginator = jQuery(table).attr('hidepaginator');
        if (firstRow.length > 0 && (isUndefinedOrNull(hidepaginator) || hidepaginator === false || hidepaginator === 'false')) {
            var cellsCount = firstRow.find("td").length;
            var tr = jQuery("<tr>");
            var cell = jQuery("<td>", {"class": "tdPaginator", "colspan": cellsCount});
            var div = jQuery("<div>", {"class": "divPaginator"});
            var pages = createPaginator(t.data("pageCount"), t);
            div.append(pages);

            /*var divfl = jQuery("<div>", {"class": "divPaginator"});
             divfl.append(jQuery("First"));
             divfl.append(jQuery("Last"));
             cell.append(divfl);*/

            cell.append(div);
            tr.append(cell);
            tfoot.append(tr);
            var width = 0;
            tfoot.find("div.li").each(
                    function(index, object) {
                        width += jQuery(object).innerWidth() + 10;
                    }
            );
            tfoot.find("div.paginator").css({"width": width});
            var clickDefaultRef = true;
            var newRef = tfoot.find("div.paginator div.li:first a");
            var currentpage = jQuery(table).attr('currentpage');
            currentpage = isNaN(currentpage) ? 0 : parseInt(currentpage) - 1;
            var currRef = tfoot.find('div.paginator div.li:eq(' + currentpage + ') a');
            if (!isUndefinedOrNull(currRef) && !isNaN(currRef.text())) {
                if (!isUndefinedOrNull(newRef) && !isNaN(newRef.text())) {
                    var newPage = parseInt(newRef.text());
                    var currPage = parseInt(currRef.text());
                    try {
//                        console.log('current('+currPage+'), new('+newPage+')');
                        clickDefaultRef = currPage < 2 || (newPage > 1 && currPage !== newPage);
                    } catch (e) {
                    }
                }
            }
            if (clickDefaultRef) {
                newRef.click();
            } else if (!isUndefinedOrNull(currRef)) {
                currRef.click();
            }
        }
    }
    setTRBehaviorTlist(index, table);
};

function setTRBehaviorTlist(index, table) {
    jQuery(table).find("tbody tr").mouseenter(
            function() {
                var tr = jQuery(this);
                var toolArray = isNullOrUndefined(jQuery(table).data("itemTools")) ? [] : jQuery(table).data("itemTools").split(",");
                if (toolArray.length > 0) {
                    var urlPrefix = isNullOrUndefined(jQuery(table).data("urlPrefix")) ? "" : jQuery(table).data("urlPrefix");
                    var urlSufix = isNullOrUndefined(jQuery(table).data("urlSufix")) ? "/edit" : jQuery(table).data("urlSufix");
                    urlPrefix = isNullOrUndefined(tr.data("urlPrefix")) ? urlPrefix : tr.data("urlPrefix");
                    urlSufix = isNullOrUndefined(tr.data("urlSufix")) ? urlSufix : tr.data("urlSufix");
                    var rowId = isNullOrUndefined(tr.data("rowId")) ? "" : tr.data("rowId");

                    var hrefEdit = (urlPrefix + rowId + urlSufix).trim().length > 0 ? urlPrefix + rowId + urlSufix : "#";
                    var hrefShow = (urlPrefix + rowId).trim().length > 0 ? urlPrefix + rowId : "#";
                    var showTitle = jQuery(table).data("showTitle");
                    showTitle = isUndefinedOrNull(showTitle) ? 'Consultar' : showTitle;
                    var editTitle = jQuery(table).data("editTitle");
                    editTitle = isUndefinedOrNull(editTitle) ? 'Editar' : editTitle;
                    var isdeactivated = tr.is('.deactivated');
                    var isnoeditable = tr.is('.noeditable');
//                    console.log('isdeactivated:' + isdeactivated);
                    if (isUndefinedOrNull(isdeactivated)) {
                        var divTool = jQuery("<div>", {"class": "editRow"});
                        for (var i = 0; i < toolArray.length; i++) {
                            if (isUndefinedOrNull(isnoeditable)) {
                                if (toolArray[i] === "edit") {
                                    divTool.append(jQuery("<a>", {href: hrefEdit, "class": "editRow command-orange " + toolArray[i], "style": "right:10px", title: editTitle}));
                                }
                            }
                            if (toolArray[i] === "show") {
                                divTool.append(jQuery("<a>", {href: hrefShow, "class": "editRow command-orange " + toolArray[i], "style": "right:30px", title: showTitle}));
                            }
                        }
                        tr.find("td:last").append(divTool);
                    }
                }
            }
    ).mouseleave(
            function() {
                var tr = jQuery(this);
                tr.find(".editRow").remove();
            }
    );
    jQuery(table).find("thead th.order").each(
            function(index, th) {
                var jth = jQuery(th);
                var orderBy = jth.data("orderBy");
                if (isNullOrUndefined(orderBy)) {
                    return;
                }
                var text = jth.text();
                var prefix = jQuery(this).closest('table').data('urlPrefix');
                var isajax = jQuery(this).closest('table').attr('useajax');
                isajax = isNullOrUndefined(isajax) ? false : (isajax === 'true' || isajax === 'on' || isajax === 'si');
                var a = jQuery("<a>", {"href": prefix + "?order-by=" + escape(orderBy) + "&switchOrder=1"}).text(text);
                if (isajax) {
                    a.addClass("ajaxcall");
                }
                jth.html("");
                jth.append(a);
            }
    );

    try {
        configureReportButtons();
    } catch (e) {
    }
//    try {
//        var postFunctionName = jQuery(table).attr('id') + 'PostTRBehaviorTlist()';
//        try {
//            jQuery(table).attr('postTRBehaviorTlist="' + postFunctionName);
//            eval(postFunctionName + ';');
//        } catch (e) {
////            console.log('could not find javascript function  postFunctionName, was ignored.');
//        }
//        configureAjaxHeaders('#' + jQuery(table).attr('id'));
//    } catch (e) {
//    }
}

var updateDisplayTable = function(table) {
    var title = '';
    // BEGIN try to remove attribute width = fixed pxm to 100%
    try {
        jQuery(table).parents().find('.dataTables_scroll').first().find('.dataTables_scrollHead').first().find('.dataTables_scrollHeadInner').first().removeAttr('style').find('.dataTable').first().removeAttr('style');
        jQuery(table).parents().find('.dataTables_scroll').first().find('.dataTables_scrollHead').first().find('.dataTables_scrollHeadInner').first().removeAttr('style').find('.dataTable').first().width('100%');
    } catch (e) {
    }
    // END try to remove attribute width = fixed pxm to 100%

    jQuery(table).find("tr").each(function() {
        var tr = jQuery(this);
        var td1 = tr.find("td:first");

        if (jQuery.trim(title) === '') {
            title = jQuery.trim(td1.html());
        }
        if (jQuery.trim(td1.html()) === '') {
            tr.attr('title', title);
        } else {
            title = jQuery.trim(td1.html());
        }
        tr.find("td").each(function() {
            var valor = jQuery(this).text();
            valor = jQuery.trim(valor);
            if (valor.indexOf('-') === 0 || valor.indexOf('(') === 0) {
                jQuery(this).addClass("negativeNumber");
            }
            if (valor.indexOf('subtotal') !== -1) {
                var tr = jQuery(this).closest('tr');
                tr.removeClass("odd");
                tr.removeClass("even");
                tr.addClass("total");
            }
            if (valor.indexOf('TOTAL') === 0) {
                var tr = jQuery(this).closest('tr');
                tr.removeClass("odd");
                tr.removeClass("even");
                tr.addClass("totalover");
            }

        });
    });
};
var updateDisplayTables = function() {
    jQuery("table.displaytable").each(function() {
        updateDisplayTable(this);
    });
};

var configureNOScrollableDataTable = function(table) {
//    console.log('noscrollableDataTable:' + jQuery(table).attr('id'));
    configure_editable_table(table);
    var bsort = jQuery(table).attr('sortable');
    bsort = isUndefinedOrNull(bsort) ? true : (bsort === true || bsort === 'true' || bsort === 'on' || bsort === 'yes' || bsort === 'si');
    var columns = jQuery(table).attr('columns');
    columns = (isUndefinedOrNull(columns)) ? null : eval('columns=' + columns);
    if (!isUndefinedOrNull(columns)) {
        for (var i = 0; i < columns.length; i++) {
            console.log(columns[i]);
        }
    }
    var usejqueryui = jQuery(table).attr('usejqueryui');
    usejqueryui = isUndefinedOrNull(usejqueryui) ? false : (usejqueryui === true || usejqueryui === 'true' || usejqueryui === 'on' || usejqueryui === 'yes' || usejqueryui === 'si');
    var filter = jQuery(table).attr('filter');
    filter = isUndefinedOrNull(filter) ? true : (filter === true || filter === 'true' || filter === 'on' || filter === 'yes' || filter === 'si');
    var showinfo = jQuery(table).data('showinfo');
    showinfo = isUndefinedOrNull(showinfo) ? false : (showinfo === true || showinfo === 'true' || showinfo === 'on' || showinfo === 'yes' || showinfo === 'si');
    jQuery(table).dataTable({
        "bFilter": filter,
        "bPaginate": false,
        "bLengthChange": false,
        "bInfo": showinfo,
        "bAutoWidth": false,
        "bSort": bsort,
        "bScrollInfinite": true, //this property disables pagination
        "bJQueryUI": usejqueryui,
        "aoColumns": columns,
        "oLanguage": {
            "sSearch": "Buscar rapido:",
            "sLengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
            "sZeroRecords": "No se encontraron registros",
            "sInfo": "Mostrando registros del <b>_START_</b> al <b>_END_</b> de un total de <b>_TOTAL_</b>",
            "sInfoEmpty": "Mostrando de 0 a 0 de 0 registros",
            "sInfoFiltered": "(se ha filtrado de un total de _MAX_ registros)",
            "sEmptyTable": !isUndefinedOrNull(globalEmptyTableMessage) ? globalEmptyTableMessage : "Tabla vac&iacute;a",
            "oPaginate": {
                "sFirst": "|<",
                "sLast": ">|",
                "sNext": ">",
                "sPrevious": "<"
            }
        }});
    updateDisplayTable(table);
};

var configureNOScrollableDataTable1 = function(table) {
//    console.log('noscrollableDataTable:' + jQuery(table).attr('id'));
    configure_editable_table(table);
    var bsort = jQuery(table).attr('sortable');
    bsort = isUndefinedOrNull(bsort) ? true : (bsort === true || bsort === 'true' || bsort === 'on' || bsort === 'yes' || bsort === 'si');
    var columns = jQuery(table).attr('columns');
    columns = (isUndefinedOrNull(columns)) ? null : eval('columns=' + columns);
    if (!isUndefinedOrNull(columns)) {
        for (var i = 0; i < columns.length; i++) {
            console.log(columns[i]);
        }
    }
    var usejqueryui = jQuery(table).attr('usejqueryui');
    usejqueryui = isUndefinedOrNull(usejqueryui) ? false : (usejqueryui === true || usejqueryui === 'true' || usejqueryui === 'on' || usejqueryui === 'yes' || usejqueryui === 'si');
    var filter = jQuery(table).attr('filter');
    filter = isUndefinedOrNull(filter) ? true : (filter === true || filter === 'true' || filter === 'on' || filter === 'yes' || filter === 'si');
    var showinfo = jQuery(table).data('showinfo');
    showinfo = isUndefinedOrNull(showinfo) ? false : (showinfo === true || showinfo === 'true' || showinfo === 'on' || showinfo === 'yes' || showinfo === 'si');
    jQuery(table).dataTable({
        "bFilter": filter,
        "bPaginate": false,
        "bLengthChange": false,
        "bInfo": showinfo,
        "bAutoWidth": false,
        "bSort": bsort,
        "sScrollY": "300px",
        "bScrollInfinite": true, //this property disables pagination
        "bJQueryUI": usejqueryui,
        "aoColumns": columns,
        "oLanguage": {
            "sSearch": "Buscar rapido:",
            "sLengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
            "sZeroRecords": "No se encontraron registros",
            "sInfo": "Mostrando registros del <b>_START_</b> al <b>_END_</b> de un total de <b>_TOTAL_</b>",
            "sInfoEmpty": "Mostrando de 0 a 0 de 0 registros",
            "sInfoFiltered": "(se ha filtrado de un total de _MAX_ registros)",
            "sEmptyTable": !isUndefinedOrNull(globalEmptyTableMessage) ? globalEmptyTableMessage : "Tabla vac&iacute;a",
            "oPaginate": {
                "sFirst": "|<",
                "sLast": ">|",
                "sNext": ">",
                "sPrevious": "<"
            }
        }});
    updateDisplayTable(table);
};
var configureNOScrollableDataTable2 = function(table) {
//    console.log('noscrollableDataTable:' + jQuery(table).attr('id'));
    configure_editable_table(table);
    var bsort = jQuery(table).attr('sortable');
    bsort = isUndefinedOrNull(bsort) ? true : (bsort === true || bsort === 'true' || bsort === 'on' || bsort === 'yes' || bsort === 'si');
    var columns = jQuery(table).attr('columns');
    columns = (isUndefinedOrNull(columns)) ? null : eval('columns=' + columns);
    if (!isUndefinedOrNull(columns)) {
        for (var i = 0; i < columns.length; i++) {
            console.log(columns[i]);
        }
    }
    var usejqueryui = jQuery(table).attr('usejqueryui');
    usejqueryui = isUndefinedOrNull(usejqueryui) ? false : (usejqueryui === true || usejqueryui === 'true' || usejqueryui === 'on' || usejqueryui === 'yes' || usejqueryui === 'si');
    var filter = jQuery(table).attr('filter');
    filter = isUndefinedOrNull(filter) ? true : (filter === true || filter === 'true' || filter === 'on' || filter === 'yes' || filter === 'si');
    var showinfo = jQuery(table).data('showinfo');
    showinfo = isUndefinedOrNull(showinfo) ? false : (showinfo === true || showinfo === 'true' || showinfo === 'on' || showinfo === 'yes' || showinfo === 'si');
    jQuery(table).dataTable({
        "bFilter": filter,
        "bPaginate": true,
        "bLengthChange": true,
        "bInfo": showinfo,
        "bAutoWidth": false,
        "bSort": bsort,
        "sScrollY": "300px",
        "bScrollInfinite": false, //this property disables pagination
        "bJQueryUI": usejqueryui,
        "aoColumns": columns,
        "oLanguage": {
            "sSearch": "Buscar rapido:",
            "sLengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
            "sZeroRecords": "No se encontraron registros",
            "sInfo": "Mostrando registros del <b>_START_</b> al <b>_END_</b> de un total de <b>_TOTAL_</b>",
            "sInfoEmpty": "Mostrando de 0 a 0 de 0 registros",
            "sInfoFiltered": "(se ha filtrado de un total de _MAX_ registros)",
            "sEmptyTable": !isUndefinedOrNull(globalEmptyTableMessage) ? globalEmptyTableMessage : "Tabla vac&iacute;a",
            "oPaginate": {
                "sFirst": "|<",
                "sLast": ">|",
                "sNext": ">",
                "sPrevious": "<"
            }
        }});
    updateDisplayTable(table);
};

var configureScrollableDataTable = function(table) {
    configure_editable_table(table);
    var columns = jQuery(table).attr('columns');
    columns = (isUndefinedOrNull(columns)) ? null : eval('columns=' + columns);
    if (!isUndefinedOrNull(columns)) {
        for (var i = 0; i < columns.length; i++) {
            console.log(columns[i]);
        }
    }
    var usejqueryui = jQuery(table).attr('usejqueryui');
    usejqueryui = isUndefinedOrNull(usejqueryui) ? false : (usejqueryui === true || usejqueryui === 'true' || usejqueryui === 'on' || usejqueryui === 'yes' || usejqueryui === 'si');
    var filter = jQuery(table).attr('filter');
    filter = isUndefinedOrNull(filter) ? false : (filter === true || filter === 'true' || filter === 'on' || filter === 'yes' || filter === 'si');
    jQuery(table).dataTable({
        "bFilter": filter,
        "bPaginate": false,
        "bLengthChange": false,
        "bInfo": false,
        "bAutoWidth": false,
        "bSort": false,
        "sScrollY": "300px",
        "bScrollInfinite": true, //this property disables pagination
        "bJQueryUI": usejqueryui,
        "aoColumns": columns,
        "oLanguage": {
            "sSearch": "Filtrar:",
            "sLengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
            "sZeroRecords": "No se encontraron registros",
            "sInfo": "Mostrando registros del <b>_START_</b> al <b>_END_</b> de un total de <b>_TOTAL_</b>",
            "sInfoEmpty": "Mostrando de 0 a 0 de 0 registros",
            "sInfoFiltered": "(se ha filtrado de un total de _MAX_ registros)",
            "sEmptyTable": !isUndefinedOrNull(globalEmptyTableMessage) ? globalEmptyTableMessage : "Tabla vac&iacute;a",
            "oPaginate": {
                "sFirst": "|<",
                "sLast": ">|",
                "sNext": ">",
                "sPrevious": "<"
            }
        }});
    try {
        var scrollBody = jQuery(table).closest('.dataTables_scrollBody');
        if (!isUndefinedOrNull(scrollBody)) {
            var currentHeight = parseInt(replaceAll(jQuery(table).css('height'), 'px', ''));
            var changingHeight = parseInt(replaceAll(scrollBody.css('height'), 'px', ''));
            if (currentHeight < changingHeight) {
                var offset = 25;
                var setHeight = parseInt(replaceAll(jQuery(table).css('height'), 'px', ''));
                setHeight += offset;
                scrollBody.css('height', setHeight + 'px');
            }
        }
    } catch (e) {
        console.log(e);
    }
    updateDisplayTable(table);
};
var configureScrollablePaginableDataTable = function(table) {
    configure_editable_table(table);
    var columns = jQuery(table).attr('columns');
    columns = (isUndefinedOrNull(columns)) ? null : eval('columns=' + columns);
    if (!isUndefinedOrNull(columns)) {
        for (var i = 0; i < columns.length; i++) {
            console.log(columns[i]);
        }
    }
    var usejqueryui = jQuery(table).attr('usejqueryui');
    usejqueryui = isUndefinedOrNull(usejqueryui) ? false : (usejqueryui === true || usejqueryui === 'true' || usejqueryui === 'on' || usejqueryui === 'yes' || usejqueryui === 'si');
    var filter = jQuery(table).attr('filter');
    filter = isUndefinedOrNull(filter) ? false : (filter === true || filter === 'true' || filter === 'on' || filter === 'yes' || filter === 'si');
    jQuery(table).dataTable({
        "bFilter": filter,
        "bPaginate": true,
        "bLengthChange": true,
        "bInfo": true,
        "bAutoWidth": true,
        "bSort": false,
        "bScrollCollapse": true,
        "bScrollInfinite": false, //this property disables pagination
        "sPaginationType": "full_numbers",
        "bJQueryUI": usejqueryui,
        "aoColumns": columns,
        "oLanguage": {
            "sSearch": "Filtrar:",
            "sLengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
            "sZeroRecords": "No se encontraron registros",
            "sInfo": "Mostrando registros del <b>_START_</b> al <b>_END_</b> de un total de <b>_TOTAL_</b>",
            "sInfoEmpty": "Mostrando de 0 a 0 de 0 registros",
            "sInfoFiltered": "(se ha filtrado de un total de _MAX_ registros)",
            "sEmptyTable": !isUndefinedOrNull(globalEmptyTableMessage) ? globalEmptyTableMessage : "Tabla vac&iacute;a",
            "oPaginate": {
                "sFirst": "|<",
                "sLast": ">|",
                "sNext": ">",
                "sPrevious": "<"
            }
        }});
    try {
        var scrollBody = jQuery(table).closest('.dataTables_scrollBody');
        if (!isUndefinedOrNull(scrollBody)) {
            var currentHeight = parseInt(replaceAll(jQuery(table).css('height'), 'px', ''));
            var changingHeight = parseInt(replaceAll(scrollBody.css('height'), 'px', ''));
            if (currentHeight < changingHeight) {
                var offset = 25;
                var setHeight = parseInt(replaceAll(jQuery(table).css('height'), 'px', ''));
                setHeight += offset;
                scrollBody.css('height', setHeight + 'px');
            }
        }
    } catch (e) {
        console.log(e);
    }
    updateDisplayTable(table);
};

var configurePaginableDataTable = function(table) {
    configure_editable_table(table);
    var columns = jQuery(table).attr('columns');
    columns = (isUndefinedOrNull(columns)) ? null : eval('columns=' + columns);
    if (!isUndefinedOrNull(columns)) {
        for (var i = 0; i < columns.length; i++) {
            console.log(columns[i]);
        }
    }
    var usejqueryui = jQuery(table).attr('usejqueryui');
    usejqueryui = isUndefinedOrNull(usejqueryui) ? false : (usejqueryui === true || usejqueryui === 'true' || usejqueryui === 'on' || usejqueryui === 'yes' || usejqueryui === 'si');
    var filter = jQuery(table).attr('filter');
    filter = isUndefinedOrNull(filter) ? false : (filter === true || filter === 'true' || filter === 'on' || filter === 'yes' || filter === 'si');
    jQuery(table).dataTable({
        "bFilter": filter,
        "bPaginate": true,
        "bLengthChange": true,
        "bInfo": true,
        "bAutoWidth": false,
        "bSort": false,
        "bScrollInfinite": false, //this property disables pagination
        "sPaginationType": "full_numbers",
        "bJQueryUI": usejqueryui,
        "aoColumns": columns,
        "oLanguage": {
            "sSearch": "Filtrar:",
            "sLengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
            "sZeroRecords": "No se encontraron registros",
            "sInfo": "Mostrando registros del <b>_START_</b> al <b>_END_</b> de un total de <b>_TOTAL_</b>",
            "sInfoEmpty": "Mostrando de 0 a 0 de 0 registros",
            "sInfoFiltered": "(se ha filtrado de un total de _MAX_ registros)",
            "sEmptyTable": !isUndefinedOrNull(globalEmptyTableMessage) ? globalEmptyTableMessage : "Tabla vac&iacute;a",
            "oPaginate": {
                "sFirst": "|<",
                "sLast": ">|",
                "sNext": ">",
                "sPrevious": "<"
            }
        }});

    try {
        var scrollBody = jQuery(table).closest('.dataTables_scrollBody');
        if (!isUndefinedOrNull(scrollBody)) {
            var currentHeight = parseInt(replaceAll(jQuery(table).css('height'), 'px', ''));
            var changingHeight = parseInt(replaceAll(scrollBody.css('height'), 'px', ''));
            if (currentHeight < changingHeight) {
                var offset = 25;
                var setHeight = parseInt(replaceAll(jQuery(table).css('height'), 'px', ''));
                setHeight += offset;
                scrollBody.css('height', setHeight + 'px');
            }
        }
    } catch (e) {
        //console.log(e);
    }
    updateDisplayTable(table);
};



var configureDaTaTableSortD = function(table) {
    jQuery.fn.dataTableExt.oSort['fecha-asc'] = function(a, b) {
        var ukDatea = a.split('/');
        var ukDateb = b.split('/');

        var x = (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
        var y = (ukDateb[2] + ukDateb[1] + ukDateb[0]) * 1;

        return ((x < y) ? -1 : ((x > y) ? 1 : 0));
    };

    jQuery.fn.dataTableExt.oSort['fecha-desc'] = function(a, b) {
        var ukDatea = a.split('/');
        var ukDateb = b.split('/');

        var x = (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
        var y = (ukDateb[2] + ukDateb[1] + ukDateb[0]) * 1;

        return ((x < y) ? 1 : ((x > y) ? -1 : 0));
    };
    configure_editable_table(table);
    var columns = new Array();
    jQuery(table).find('th').each(function() {
        tipo = jQuery(this).attr("sType");
        if (isNullOrUndefined(tipo)) {
            columns.push(null);
        } else {
            columns.push({"sType": tipo});
        }
    });

    var usejqueryui = jQuery(table).attr('usejqueryui');
    usejqueryui = isUndefinedOrNull(usejqueryui) ? false : (usejqueryui === true || usejqueryui === 'true' || usejqueryui === 'on' || usejqueryui === 'yes' || usejqueryui === 'si');
    var filter = jQuery(table).attr('filter');
    filter = isUndefinedOrNull(filter) ? false : (filter === true || filter === 'true' || filter === 'on' || filter === 'yes' || filter === 'si');
    jQuery(table).dataTable({
        "bFilter": filter,
        "bPaginate": true,
        "bLengthChange": true,
        "bInfo": true,
        "bAutoWidth": false,
        "bSort": true,
        "bScrollInfinite": false, //this property disables pagination
        "sPaginationType": "full_numbers",
        "bJQueryUI": usejqueryui,
        "aoColumns": columns,
        "oLanguage": {
            "sSearch": "Filtrar:",
            "sLengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
            "sZeroRecords": "No se encontraron registros",
            "sInfo": "Mostrando registros del <b>_START_</b> al <b>_END_</b> de un total de <b>_TOTAL_</b>",
            "sInfoEmpty": "Mostrando de 0 a 0 de 0 registros",
            "sInfoFiltered": "(se ha filtrado de un total de _MAX_ registros)",
            "sEmptyTable": !isUndefinedOrNull(globalEmptyTableMessage) ? globalEmptyTableMessage : "Tabla vac&iacute;a",
            "oPaginate": {
                "sFirst": "|<",
                "sLast": ">|",
                "sNext": ">",
                "sPrevious": "<"
            }
        }});

    try {
        var scrollBody = jQuery(table).closest('.dataTables_scrollBody');
        if (!isUndefinedOrNull(scrollBody)) {
            var currentHeight = parseInt(replaceAll(jQuery(table).css('height'), 'px', ''));
            var changingHeight = parseInt(replaceAll(scrollBody.css('height'), 'px', ''));
            if (currentHeight < changingHeight) {
                var offset = 25;
                var setHeight = parseInt(replaceAll(jQuery(table).css('height'), 'px', ''));
                setHeight += offset;
                scrollBody.css('height', setHeight + 'px');
            }
        }
    } catch (e) {
        //console.log(e);
    }
    updateDisplayTable(table);

};



















var configureDaTaTablesSortD = function() {
    jQuery('table.dataTableSortD').each(function() {
        configureDaTaTableSortD(this);
    });
};



var configurePaginableDataTables = function() {
    jQuery('table.paginableDataTable').each(function() {
        configurePaginableDataTable(this);
    });
};
var configureScrollablePaginableDataTables = function() {
    jQuery('table.scrollablePaginableDataTable').each(function() {
        configureScrollablePaginableDataTable(this);
    });
};
var configureScrollableDataTables = function() {
    jQuery('table.scrollableDataTable').each(function() {
        configureScrollableDataTable(this);
    });
};
var configureNOScrollableDataTables = function() {
    jQuery('table.noscrollableDataTable').each(function() {
        configureNOScrollableDataTable(this);
    });
};
var configureNOScrollableDataTables1 = function() {
    jQuery('table.noscrollableDataTable1').each(function() {
        configureNOScrollableDataTable1(this);
    });
};
var configureNOScrollableDataTables2 = function() {
    jQuery('table.noscrollableDataTable2').each(function() {
        configureNOScrollableDataTable2(this);
    });
};

var configureTables = function() {
    try {
        jQuery('div#waitDiv.ui-overlay').hide();
    } catch (e) {
    }
    try {
        jQuery("table.list").each(
                function(index, table) {
                    configureTable(index, table);
                }
        );
    } catch (ex) {
        alert("tables.js " + ex);
    }
};



var refresh_editable_cell = function(vtable, einput) {

    var params = '';

    if (!isUndefinedOrNull(jQuery(einput))) {
        var vname = jQuery(einput).attr('id');
        var val = jQuery(einput).val();
        if (!isUndefinedOrNull(val)) {
            params += '&' + vname + '=' + escape(val);
//            console.log(params);
        }
    }
    var url = jQuery(vtable).data('urlPrefix') + jQuery(vtable).data('editableRequestmapping');
    params += '&recid=' + jQuery(einput).attr('itemid');
    url = isUndefinedOrNull(url) ? '' : (url + (isUndefinedOrNull(params) ? '' : ('?' + params)));
//    console.log('url:' + url);
    var afterfunction = jQuery(vtable).data('afterEditFunction');
    afterfunction = isUndefinedOrNull(afterfunction) ? 'postrefresheditablecell' : afterfunction;
    var refreshdiv = jQuery(vtable).data('refreshDiv');
    refreshdiv = isUndefinedOrNull(refreshdiv) ? 'body' : refreshdiv;

    doLoad(null, url, refreshdiv, afterfunction);


//    params += ", recid:" + jQuery(einput).attr('itemid');
//    url = isUndefinedOrNull(url) ? '' : url;
//    var afterfunction = jQuery(vtable).data('afterEditfunction');
//    afterfunction = isUndefinedOrNull(afterfunction) ? 'postrefresheditablecell' : afterfunction;
//    var refreshobj = jQuery(vtable).data('refreshObj');
//    refreshobj = isUndefinedOrNull(refreshobj) ? 'body' : refreshobj;
//    LoadObject(null, url, params, refreshobj, afterfunction);




};
function updateVal() {
    //var val = jQuery("#totalFootAuto").html().replace("$", "").replace(",", ".").trim();
    compensacionInput = jQuery("#vcompe");
    montoInput = jQuery("#vtotal");
    acreditamientoInput = jQuery("#vacredi");
    devolucionInput = jQuery("#vdevolefec");
    compensacionInput.val(0.00);
    montoInput.val(0.00);
    if (acreditamientoInput.val().trim() == "") {
        acreditamientoInput.val(0.00);
    }

    acreditamientoInput.on("change", function() {
        updateVal();
        devolucionInput.val(devolucionInput.val() - jQuery(this).val());
    });
    var monto = jQuery("#totalFootAuto").html().replace("$", "").replace(/,/g, "").trim();
    var totalcompe = jQuery("#totalCompensaciones").html().replace("$", "").replace(/,/g, "").trim();
    if (totalcompe == "") {
        totalcompe = 0.00;
    }
    compensacionInput.val(totalcompe);
    montoInput.val(monto);
    var efectivo = monto - totalcompe;
    devolucionInput.val(efectivo);
}


var configure_editable_table = function(vtable) {
    vtable = jQuery(vtable);
    if (vtable.hasClass('editable-table')) {
        var editable_columns = jQuery.makeArray();
        var i = 0;

        vtable.find('thead th').each(function() {
            var editor = jQuery(this).data('editor');
            if (!isUndefinedOrNull(editor)) {
                editable_columns[editable_columns.length ] = [i, editor, jQuery(this).attr('id'), jQuery(this).data('editorSource')];
            }
            i++;
        });
//        console.log('configureEditableTables>' + vtable.attr('id') + ">editable_columns:");
//        for (i = 0; i < editable_columns.length; i++) {
//            console.log('column_index(' + i + '):(' + editable_columns[i][0] + ',' + editable_columns[i][1] + ')');
//        }

        vtable.find('tbody tr').each(function() {
            var vtr = jQuery(this);

            vtr.find('td').each(function(index, value) {
                for (i = 0; i < editable_columns.length; i++) {
                    if (index === editable_columns[i][0]) {
                        var vtd = jQuery(this);
                        var editor = editable_columns[i][1];
                        var vid = editable_columns[i][2];
                        var datasource = editable_columns[i][3];
                        if (!isUndefinedOrNull(editor) && (editor === true || editor === 'true' || editor === 'input' || editor === 'select')) {
                            var voutput = vtd.find('span.editable-output');
                            if (isUndefinedOrNull(voutput.html())) {
                                vtd.html('<span class="editable-output">' + vtd.html().trim() + '</span>');
                                voutput = vtd.find('span.editable-output');
                            }
                            voutput.show();
                            var eval = jQuery(voutput).html();

                            var vinput = vtd.find('span.editable-input');
                            if (isUndefinedOrNull(vinput.html())) {
                                var einput;
                                if (isUndefinedOrNull(datasource)) {
                                    einput = '<input type="text" value="' + eval + '" size="' + (isUndefinedOrNull(eval) ? '5' : vtd.size) + '"  />';
                                } else {
                                    einput = '<select class="w100">' + jQuery('#' + datasource).html() + '</select>';
                                }
                                vtd.append('<span class="editable-input">' + einput + '</span>');
                                vinput = vtd.find('span.editable-input');
                            }

                            var einput = vtd.find(':input:not([type=button])').first();
                            einput.attr('itemid', vtr.data('rowId'));
                            einput.attr('id', vid);
                            einput.attr('name', vid);
                            einput.on('change', function() {

                                setTimeout(function() {
                                    updateVal();
                                }, 1000)
                                refresh_editable_cell(vtable, this);

                            });
                            einput.show();

                            einput.on('blur', function() {

                                var vtd = jQuery(this).closest('td');
                                var voutput = vtd.find('span.editable-output');
                                var vinput = vtd.find('span.editable-input');
                                voutput.show();
                                vinput.hide();

                            });

                            vinput.hide();
                            vtd.on('click', function(event) {
                                event.preventDefault();
                                var vtd = jQuery(this);
                                var voutput = vtd.find('span.editable-output');
                                var vinput = vtd.find('span.editable-input');
                                var einput = vtd.find(':input:not([type=button])').first();
                                if (voutput.is(':visible')) {
                                    voutput.hide();
                                    vinput.show();
                                    einput.focus();
                                }
                            });

                            //...
                        }
                    }
                }
            });
        });
    }
};

var postrefresheditablecell = function(responseTxt, statusTxt, xhr) {
    console.log('DEFAULT tables.js>postrefresheditablecell ...');
};

jQuery(document).ready(function() {
    configureScrollablePaginableDataTables();
    configureDaTaTablesSortD();
});
var transitionPaginator = function(btn) {
    try {
        var table = jQuery(btn).parents("table:first");
        if (!isNullOrUndefined(table)) {
            var selected = parseInt(jQuery(btn).text());
            selected = isNullOrUndefined(selected) ? 1 : parseInt(selected);
            var actual = jQuery(table).attr('currentpage');
            actual = isNullOrUndefined(actual) ? 1 : parseInt(actual);
            jQuery(table).attr('currentpage', actual)
            if (selected !== actual) {
                jQuery(table).attr('currentpage', selected);
                tableGo(table, jQuery(btn).attr("href"));
            }
            var pos = jQuery(btn).parents("div.divPaginator:first").innerWidth() / 2;
            jQuery(btn).parents("div.paginator:first").find("a").removeClass("active");
            jQuery(btn).addClass("active");
            jQuery(btn).parents("div.paginator:first").animate({"left": pos - jQuery(btn).position().left - (jQuery(btn).innerWidth() / 2)});
//                            console.log('CLICK'+jQuery(table).attr('currentpage'));
        }
    } catch (e) {
        alert(e);
    }
};