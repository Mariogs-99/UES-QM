var incomeType		=	null;
var incomeTypes	=	{NONTAXEABLE:"nogravado",
		SALARY:"salarios",
		ACREDITABLE:"acreditable",
		NONACREDITABLE:"nonAcreditable",
		ACTIVITIES:"actividades"};
var Porcentajes={
	retenido01:1,
	retenido:0.3
};

var DATAS	=	{
		incomesNR:[],
		incomesR:[],
		newR:{nit:null,isBienestar:false,isIpsfa:false,nombreAgente:null},
		newING:{nit:null,isBienestar:false,isIpsfa:false,nombreAgente:null}
};


var incomesNR={
		profesionesArtesOficios:"Profesiones, Artes y Oficios",
		servicios:"Por Actividades de servicios",
		comerciales:"Por Actividades Comerciales",
		industriales:"Por Actividades Industriales",
		agropecuarias:"Por Actividades agropecuarias",
		utilidadesDividendos:"Por Utilidades y Dividendos",
		serviciosExterior:"Por servicios realizados en el exterior y utilizados en El Salvador",
		otrasRentasGravables:"Otras Rentas Gravables"
			
};

//var acreditableCodes	=	["01","07","08","09","11","19","20","21","22","24","26","27"];
//var nonAcreditableCodes	=	["28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48"];
//var nonTaxableCodes		=	["70", "71", "72"];
var totalMontoGravado			= 	0.0;
var totalGravadosSinRetencion	=	0.0;
var totalRetenidoGravado		=	0.0;

function getAcreditableCodes(isJuridica){
	if(isJuridica){
		return ["05","06","07","08","09","11","19","20","23","25","26"];
	}else{
		return ["01","07","08","09","11","19","20","21","22","24","26","27"];
	}
}

function getNonAcreditableCodes(isJuridica, isDomiciliado){
	if(isJuridica){
		return ["29","34","35","36","37","38","39","40","41","42","43","44","45","46","48"];
	}else{
		if(isDomiciliado){
			return ["28","29","34","37","40","42","43","44","45","46","47","48"];
		}else{
			return ["30","31","32","33","34","36","39","41","44"];
		}		
	}
}

function getNonTaxableCodes(isJuridica){
	if(isJuridica){
		return ["71"];
	}else{
		return ["70", "71", "72"];
	}
}

function setIngresos(){
	
	jQuery("input.cf11Ingreso").each(
			function(index, object){
				var obj	= jQuery(object);
				
				if(!isNullOrUndefined(obj.attr("id"))){
					var code1	=	obj.attr("id").replace("_","");					
					if(!isNullOrUndefined(f11.get(code1))){
						if(!isNullOrUndefined(f11.get(code1).valor)){
							var casilla	= f11.get(code1);
							var val		=	parseFloat(casilla.valor).toFixed(2);
							if(obj.hasClass("string")){
								val	=	casilla.valor;
							}
							obj.val(val);
						}else{
							obj.val(f11.get(code1));
						}
					}
				}
			}
	);
	//Agregado por Hugo para visualizar ingresos sin retenciones que vienen del servidor
	//esto puede ser cuando se da F5 al navegador y el objeto persiste en session los datos	
	jQuery("#addingIncomesNR .inputText").each(
			function(index, object){
				obj	=	jQuery(object);
				if(isNaN(obj.val())){
					return;
				}
				if(parseFloat(obj.val())<=0){
					return;
				}
				if(obj.attr("name")!=null){
					if(incomesNR[obj.attr("name")]!=null){
						
						labelIncomeNR	=	incomesNR[obj.attr("name")];
						devengado		=	parseFloat(obj.val()).toFixed(2);
						tr				=	createIncomeRowNR(labelIncomeNR, devengado, obj.attr("name"), true);							
						jQuery("table#incomesNR tbody").append(tr);
					}
				}
			}
	);
	jQuery("a.delete").hide();
}

function setDatas(){
	try{
		
		jQuery("input.cf11").each(
			function(index, object){
				var obj	= jQuery(object);
				
				if(!isNullOrUndefined(obj.attr("id"))){
					var code1	=	obj.attr("id").replace("_","");					
					if(!isNullOrUndefined(f11.get(code1))){
						if(!isNullOrUndefined(f11.get(code1).valor)){
							var casilla	= f11.get(code1);
							var val		=	parseFloat(casilla.valor).toFixed(2);							
							if(obj.hasClass("string") || code1==="c920"){//ugly hack
								val	=	casilla.valor;
							}
							obj.val(val);
						}else{
							obj.val(f11.get(code1));
						}
					}
				}
			}
		);
		jQuery("a.cf11,p.cf11,span.cf11").each(
				function(index, object){
					var obj	= jQuery(object);
					if(!isNullOrUndefined(obj.attr("id"))){
						code3	=	obj.attr("id").replace("_","");
						
						if(!isNullOrUndefined(f11.get(code3))){
							var data	=	null;
							if(obj.hasClass("string")){
								data	=	!isNullOrUndefined(f11.get(code3).valor) ?  f11.get(code3).valor : f11.get(code3);
									
							}else{
								data	=	!isNullOrUndefined(f11.get(code3).valor) ?  
										formatearMoneda(f11.get(code3).valor) : 
											formatearMoneda(f11.get(code3));
							}
							
							
							obj.html(data);
							
						}
					}
				}
			);
		
		
		if(f11.v("647")>0){
			jQuery("#tr_646").show();
		}else{
			jQuery("#tr_646").hide();
		}
		
		jQuery("p.totalMontoAcreditable").text(formatearMoneda(f11.getMontoRetencionesAcreditables()));
		jQuery("p.totalRetenidoAcreditable").text(formatearMoneda(f11.getRetenidoRetencionesAcreditables()));
		
		jQuery("p.totalMontoNoAcreditable").text(formatearMoneda(f11.getMontoRetencionesNoAcreditables()));
		jQuery("p.totalRetenidoNoAcreditable").text(formatearMoneda(f11.getRetenidoRetencionesNoAcreditables()));
		
		jQuery("p.totalMontoSalarios").text(formatearMoneda(f11.getMontoByRetencion(["60"])));
		jQuery("p.totalMontoNoGravado").text(formatearMoneda(f11.getMontoRetencionesIngNoGrav()));
		
		totalGravadosSinRetencion	=	f11.profesionesArtesOficios
									+	f11.servicios 
									+	f11.comerciales
									+	f11.industriales
									+	f11.agropecuarias
									+	f11.utilidadesDividendos
									+	f11.serviciosExterior
									+	f11.otrasRentasGravables;
		
		
		jQuery("p.totalMontoSinRetencion").text(""+formatearMoneda(parseFloat(totalGravadosSinRetencion + f11.getMontoByRetencion(["60"])).toFixed(2)));
		jQuery("p.totalMontoSinRetencion_actividades").text(""+formatearMoneda(parseFloat(totalGravadosSinRetencion).toFixed(2)));
		
		totalMontoNoGravado	=f11.getMontoRetencionesIngNoGrav();
		jQuery("p.totalMontoNoGravado").html(formatearMoneda(parseFloat(totalMontoNoGravado).toFixed(2)));
		
		totalMontoGravado = f11.getMontoRetencionesAcreditables() + f11.getMontoByRetencion(["60"]) + totalGravadosSinRetencion;
		
		totalRetenidoGravado = f11.getRetenidoRetencionesAcreditables();
		
		
		jQuery("p.totalMontos").html(formatearMoneda(parseFloat(totalMontoGravado + f11.getMontoRetencionesNoAcreditables() +
																f11.getMontoRetencionesIngNoGrav()).toFixed(2)));
		jQuery("p.totalRetenidos").html(formatearMoneda(parseFloat(f11.getRetenidoRetencionesAcreditables()+f11.getRetenidoRetencionesNoAcreditables()).toFixed(2)));
		
		jQuery("p.totalMontosGravados").html(formatearMoneda(parseFloat(totalMontoGravado).toFixed(2)));
		jQuery("p.totalRetencionesGravados").html(formatearMoneda(parseFloat(totalRetenidoGravado).toFixed(2)));
		
		
		var totalImpuestosDirectos	=	f11.get("c305").valor
									+	f11.get("c306").valor
									+	f11.get("c307").valor
									+	f11.get("c308").valor
									+	f11.get("c309").valor
									+	f11.get("c304").valor;
									
		
		jQuery("#totalImpuestosDirectos").html(""+formatearMoneda(totalImpuestosDirectos));
		
		jQuery("p#_total70").text(""+formatearMoneda(parseFloat(f11.getMontoByRetencion(["70"])).toFixed(2)));
		jQuery("p#_total71y72").text(""+formatearMoneda(parseFloat(f11.getMontoByRetencion(["71","72"])).toFixed(2)));
		jQuery("p#total71y72_").text(""+formatearMoneda(parseFloat(f11.getMontoByRetencion(["71","72"])).toFixed(2)));
		
		if(jQuery("table#incomesR tbody tr").length>0){			
			jQuery("tr#trRetencionesAcreditables").show();
		}else{
			jQuery("tr#trRetencionesAcreditables").hide();
		}
		if(jQuery("table#incomesR2 tbody tr").length>0){
			jQuery("tr#trRetencionesNoAcreditables").show();
		}else{
			jQuery("tr#trRetencionesNoAcreditables").hide();
		}
		
		if(jQuery("table#incomesNR602 tbody tr").length>0){
			jQuery("tr#trIngresosNoGravados").show();
		}else{
			jQuery("tr#trIngresosNoGravados").hide();
		}
		if(jQuery("table#incomesNR60 tbody tr").length>0 || jQuery("table#incomesNR tbody tr").length>0){
			jQuery("tr#trIngresosSinRetencion").show();
		}else{
			jQuery("tr#trIngresosSinRetencion").hide();
		}
		
		jQuery("div.minimal").hide();
		jQuery("div.minimal2").hide();
		jQuery("div.minimal3").hide();
		
		jQuery("a.link_pago").hide();
		
		if(f11.get("c650").visible){
			jQuery("div.minimal2").show();
		}else{
			jQuery("div.minimal2").hide();
		}
		
		if(f11.get("c628").visible){
			jQuery("div.minimal3").show();
		}else{
			jQuery("div.minimal3").hide();
		}
		
		if(f11.get("c630").visible){
			jQuery("div.minimal").show();				
		}else{
			jQuery("div.minimal").hide();
		}
		
		if(f11.get("c345").visible){// total a devolver visible?
			jQuery(".rTotal").html("<p>Total a Devolver <span class='rCant'>$"+formatearMoneda(parseFloat(f11.get("c345").valor).toFixed(2))+"</span></p>");
		}else if(f11.get("c350").visible && f11.v("350")>0){// total a pagar visible?
			jQuery("a.link_pago").show();			
			jQuery(".rTotal").html("<p>Total a Pagar <span class='rCant'>$"+formatearMoneda(parseFloat(f11.get("c350").valor).toFixed(2))+"</span></p>");
		}
		if(f11.get("c345").valor == 0 && f11.get("c350").valor == 0 ){
			jQuery(".rTotal").html("<p>Declaraci&oacute;n <span class='rCant'>$"+formatearMoneda(parseFloat(f11.get("c350").valor).toFixed(2))+"</span></p>");
		}
		
		jQuery("div.minimal4").show();
		
		/*chequeando la casilla 24*/
		
		jQuery("input#code24M, input#code24A").attr("disabled", "disabled"); // por defecto deshabilitadas
		jQuery("input#code24M, input#code24A").removeAttr("checked"); //por defecto no chequeadas
		
		if(!isNullOrUndefined(f11.get("c024"))){
			letras = f11.get("c024").valor;
			for (var i = 0, len = letras.length; i < len; i++) {				  
				  jQuery("input#code24"+letras[i]).attr("checked", "checked");
			}
			if(f11.get("c024").valor.indexOf("A")>=0){
				jQuery("input#code24A").attr("checked", "checked");
			}
			if(f11.get("c024").valor.indexOf("M")>=0){
				jQuery("input#code24M").attr("checked", "checked");
			}
			
			c024 = getCodes24();
		}
		
		if(!isNullOrUndefined(f11.get("c017"))){
			letras = f11.get("c017").valor;
			for (var i = 0, len = letras.length; i < len; i++) {				  
				  jQuery("input#code17"+letras[i]).attr("checked", "checked");
			}
			c017 = getCodes17();
		}
		
		jQuery("div#deductibles table.list .cf11").each(
			function(index,object){
				var o	=	jQuery(object);
				if(o.attr("id")){
					var code	=	o.attr("id").replace("_","");					
					if(!isNullOrUndefined(f11.get(code))){						
						if(f11.get(code).visible){
							o.parents("tr:first").show();
						}else{
							o.parents("tr:first").hide();
						}
					}
				}
			}
		);
		setMessage();
		//showBalance();
		showPagoMinimo();
		
		
	}catch(e){
		msg("setDatas :"+e);
	}
	
	
}