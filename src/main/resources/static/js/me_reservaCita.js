
$(document).ready(function(){
	
	$.session.set('loadedPage', 'reservaCita');
	var str = 'Todos';
	loadDataTable(str);
	
//	$("#btnPrint").click(function(){
//		enableReservaCita();
//	});
        
        $("#btnLoadTiquete").click(function(){
            enableReservaCita();
//            $("#btnShowTiquete").trigger("click");
        });
	
});




/**
 * Oscar Vides
 * Function para cargar datos de las reservaciones realizadas
 * @param {type} str
 * @returns {undefined}
 */

function loadDataTable(str){
	
	 $(function () {
	 
         $('#table-javascript').bootstrapTable({
             method: 'get',
             url: window.location.protocol + "//" + window.location.host+'/colas/rcta/data/'+str+'',
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
                 field: 'codigoVerificacion',
                 title: 'Código',
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
                 field: 'unidad',
                 title: 'Centro de Servicio',
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
                 sortable: true,
             }, {
                 field: 'idTiquete',
                 title: 'Ver detalle',
                 align: 'center',
                 valign: 'middle',
                 clickToSelect: false,
                 formatter: operateDetail,
                 events: operateEvents
             }, {
                 field: 'idTiquete',
                 title: 'Imprimir tiquete',
                 align: 'center',
                 valign: 'middle',
                 clickToSelect: false,
                 formatter: operatePrint,
                 events: operateEvents
             }]
         });
         
         $(".fixed-table-header:visible").hide();
         //$("div .fixed-table-toolbar").css("padding-bottom","80px");
         $("#modalDetalle .modal-dialog").css("width","515px");
         $(".search input[type=text]").attr("id","txtBuscar");
         $(".search input[type=text]").attr("class","form-control keyboard-normal");
         $("table tbody").attr("class","searchable");
         
        var i=0;
        $("table thead tr th").each(function(index){
            i+=1;
        });
        var enc = $("table thead").html();
        var title = "<tr><th class='ui-widget-header' colspan="+i+">Reserva de cita</th></tr>";
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
                
            $.get(window.location.protocol + "//" + window.location.host+'/colas/rcta/data/'+($(this).val()===''?'Todos':$(this).val())+'',
                function(data){
                    $('#table-javascript').bootstrapTable("load", data);
            }, "json");

            }
        });
		
 });
	
}




/**
 * Oscar Vides
 * Function para asignar número de tiquete a reserva de cita previamente realizada
 * 
 */

function enableReservaCita(){
	
	$(function(){
		
		var json = { "idReservaCita" : $("#idReservaCita").val(), "idTramite" : $("#idTramite").val(), "fecha": $("#lblFecha").text(), "nit" : $("#nit").val() };
		$.ajax({ 
		    url: window.location.protocol + "//" + window.location.host + "/colas/rcta/enableReservacion",
		    type: 'POST', 
		    dataType: 'json', 
		    data: JSON.stringify(json),
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) { 
				if(parseInt(data)===0){
					//mostrarAlertaError("Error","No se puede realizar la operación","error");
                                        mostrarAlertaError("Error","Este trámite se encuentra fuera de servicio","error");
				}else{
					paraImprimir(data);
					$("button[name='refresh']").trigger("click");
				}
		    },
		    error:function(data,status,er) { 
		        alert("error: "+data+" status: "+status+" er:"+er);
		    }
		});
		
	});
	
}




/**
 * Oscar Vides
 * Eventos de tabla
 * 
 */

window.operateEvents = {
    'click .detail': function (e, value, row, index) {
       
    	$("#idReservaCita").val(row.idReservaCita);
    	$("#idTramite").val(row.idTramite);
    	$("#idTiquete").val(row.idTiquete);
    	$("#lblCodigoConf").text(row.codigoVerificacion);
    	$("#lblAreaServicio").text(row.areaServicio);
    	$("#lblTramite").text(row.tramite);
    	$("#lblCentroAtencion").text(row.unidad);
    	$("#lblFecha").text(row.fecha);
    	$("#lblHora").text(row.hora);
    	$("#lblNIT").text(row.nit);
    	$("#lblCorreo").text(row.correo);
    	$("#lblTelefono").text(row.telefono);
    	
    },
    
    'click .print': function (e, value, row, index) {
        $("#nit").val(row.nit);
    	$("#idReservaCita").val(row.idReservaCita);
    	$("#idTramite").val(row.idTramite);
    	$("#idTiquete").val(row.idTiquete);
    	$("#lblFecha").text(row.fecha);
    	enableReservaCita();
    	
    }
};


