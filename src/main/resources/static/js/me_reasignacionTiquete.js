$(document).ready(function(){
	
	$.session.set("loadedPage","reasignacionTiquete");
        var str = 'Todos';
        loadDataTable(str);
        
});



/**
 * Oscar Vides
 * Function para cargar listado de tiquetes en espera y finalizados
 * @param {type} str
 * @returns {undefined}
 */

function loadDataTable(str){
	
	 $(function () {
		 
         $('#table-javascript').bootstrapTable({
             method: 'get',
             url: window.location.protocol + "//" + window.location.host+'/colas/reaTiq/data/'+str+'',
             cache: false,
             height: 400,
             striped: true,
             pagination: true,
             pageSize: 6,
             pageList: [6, 12, 25, 50, 100],
             search: true,
             showColumns: false,
             showRefresh: true,
             minimumCountColumns: 2,
             clickToSelect: true,
             columns: [{
                 field: 'tiqueteNo',
                 title: 'No. Tiquete',
                 align: 'center',
                 valign: 'middle',
                 sortable: true
             }, {
                 field: 'nit',
                 title: 'NIT',
                 align: 'center',
                 valign: 'middle',
                 sortable: true
             }, {
                 field: 'servicio',
                 title: 'Sección',
                 align: 'center',
                 valign: 'middle',
                 sortable: true
             }, {
                 field: 'tramite',
                 title: 'Trámite',
                 align: 'center',
                 valign: 'middle',
                 sortable: true
             }, {
                 field: 'estado',
                 title: 'Estado',
                 align: 'center',
                 valign: 'middle',
                 sortable: true
             }, {
                 field: 'fecha',
                 title: 'Fecha',
                 align: 'center',
                 valign: 'middle',
                 sortable: true

             }, {
                 field: 'hora',
                 title: 'Hora',
                 align: 'center',
                 valign: 'middle',
                 sortable: true

             }, {
                 field: 'idTiquete',
                 title: 'Reasignar tiquete',
                 align: 'center',
                 valign: 'middle',
                 clickToSelect: false,
                 formatter: operateAsign,
                 events: operateEvents
             }]
         });
         
         $(".fixed-table-header:visible").hide();
         //$("div .fixed-table-toolbar").css("padding-bottom","80px");
         $(".search input[type=text]").attr("id","txtBuscar");
         $(".search input[type=text]").attr("class","form-control keyboard-normal");
         $("table tbody").attr("class","searchable");
         
        var i=0;
        $("table thead tr th").each(function(index){
            i+=1;
        });
        var enc = $("table thead").html();
        var title = "<tr><th class='ui-widget-header' colspan="+i+">Reasignación de Tiquete</th></tr>";
        var thead = title += enc;
        $("table thead").html(thead);
         
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
                //addNav(keyboard);
            },
            beforeClose: function(e, keyboard, el, accepted) {
                $('input.current').removeClass('current');
                $("body").css('padding-bottom', '0px');

                /*var rex = new RegExp($(this).val(), 'i');
                $('#table-javascript .searchable tr').hide();
                $('#table-javascript .searchable tr').filter(function () {
                return rex.test($(this).text());
                }).show();*/
                
                var s = $(this).val();    
                setInterval(function(){
                    if(s===''){
                        $("button[name='refresh']").trigger("click");
                    }
                },5000);
                
                $.get(window.location.protocol + "//" + window.location.host+'/colas/reaTiq/data/'+($(this).val()===''?'Todos':$(this).val())+'',
                    function(data){
                        $('#table-javascript').bootstrapTable("load", data);
                }, "json");
                
                
                
                
            }
        });

 });
	
};



/**
 * Oscar Vides
 * Eventos de tabla
 * 
 */

window.operateEvents = {
    'click .assign': function (e, value, row, index) {
       
    	$.session.set('idTiqueteSession', row.idTiquete);
    	$.session.set('idPrioridad', row.prioridad);
        $.session.set('nitSession', row.nit);
    	$(location).attr('href',window.location.protocol + "//" + window.location.host+"/colas/me/home");
    	
    }
};
