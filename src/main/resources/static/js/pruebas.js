
$(document).ready(function(){	
	loadDataTable();
	
	$.get(window.location.protocol + "//" + window.location.host + "/colas/operacion/getServicios",function(data) {
//		alert(data);
		var tema = "";
		tema += "<option value='todos'> Todos los tiquetes </option>";
		$.each(data, function(idx, obj) {
			tema += "<option value='" + obj.nServiciosId + "'>" + obj.sNombre + "</option>";
		});
		$("#seccion").append(tema);
//		$('.selectpicker').selectpicker();
		$("#divCbTramites .bootstrap-select").css("width","400px");
	}, "json");
		

});




/**
 * Florentin Lazo 
 * cargar tabla con tiquetes en espera;
 * 
 */

function loadDataTable(){
	
	 $(function () {
		 
         $('#table-javascript').bootstrapTable({
             method: 'get',
             url: window.location.protocol + "//" + window.location.host+'/colas/operacion/enEspera/todos',
             cache: false,
             height: 400,
             striped: true,
             pagination: false,
             pageSize: 20,
             pageList: [20, 50, 100, 250, 500],
             search: true,
             showColumns: false,
             showRefresh: true,
             minimumCountColumns: 2,
             clickToSelect: true,
             columns: [{
                 field: 'cUnidadRecep',
                 title: 'Centro de Servicio',
                 align: 'center',
                 valign: 'middle',
                 sortable: true
             }, {
                 field: 'nTiqueteId',
                 title: 'Tiquete Id',
                 align: 'center',
                 valign: 'middle',
                 sortable: true
             }, {
                 field: 'sCorrelativo',
                 title: 'Numero de tiquete',
                 align: 'center',
                 valign: 'middle',
                 sortable: true
             }, {
                 field: 'mEstado',
                 title: 'Estado',
                 align: 'center',
                 valign: 'middle',
                 sortable: true
             }, {
                 field: 'fhLlegada',
                 title: 'Fecha de llegada ',
                 align: 'center',
                 valign: 'middle',
                 sortable: true

             },{
                 field: 'nTiempoHolgura',
                 title: 'Tiempo de holgura ',
                 align: 'center',
                 valign: 'middle',
                 sortable: false 
             },{
                 field: 'nTiqueteRea',
                 title: 'Reasignación',
                 align: 'center',
                 valign: 'middle',
                 sortable: true

             },{
                 field: 'nTiqueteEsc',
                 title: 'Escalamiento',
                 align: 'center',
                 valign: 'middle',
                 sortable: true

             },{
                 field: 'nTramiteId',
                 title: 'Tramite',
                 align: 'center',
                 valign: 'middle',
                 sortable: true,
             }, {
                 field: 'dPrioridad',
                 title: 'Prioridad',
                 align: 'center',
                 valign: 'middle',
                 sortable: false,
             }, {
                 field: 'puntuacion',
                 title: 'Puntuacion',
                 align: 'center',
                 valign: 'middle',
                 sortable: true,
             }]
         });
         
         $(".fixed-table-header:visible").hide();
         $("div .fixed-table-toolbar").css("padding-bottom","40px");
         $("#modalDetalle .modal-dialog").css("width","515px");
         $(".search input[type=text]").attr("id","txtBuscar");
         $(".search input[type=text]").attr("class","form-control keyboard-normal");
         $("table tbody").attr("class","searchable");
         
//		$('.keyboard-normal').keyboard({
//		    layout: 'qwerty',
//		    autoAccept: 'true',
//		    usePreview: false,
//		    visible: function(e, keyboard, el) {
//		        addNav(keyboard);
//		    },
//		    beforeClose: function(e, keyboard, el, accepted) {
//		        $('input.current').removeClass('current');
//		        $("body").css('padding-bottom', '0px');
//		        
//		        var rex = new RegExp($(this).val(), 'i');
//	            $('.searchable tr').hide();
//	            $('.searchable tr').filter(function () {
//	                return rex.test($(this).text());
//	            }).show();
//		        
//		    }
//		});
		
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
    	$("#idReservaCita").val(row.idReservaCita);
    	$("#idTramite").val(row.idTramite);
    	$("#idTiquete").val(row.idTiquete);
    	$("#lblFecha").text(row.fecha);
    	enableReservaCita();
    	
    }
};

function llenar() {
	var seccion = $('#seccion').val();
//	alert(seccion);
	$.get(window.location.protocol + "//" + window.location.host+"/colas/operacion/enEspera/"+seccion,function(data){
		$('#table-javascript').bootstrapTable('load', data);
	}, "json");
	
}



