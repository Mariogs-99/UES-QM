/**
 * 
 */

var currentNuevaCasilla	=	{};	// el actual registro que se esta agregado...
var nuevaCasillaDetalle	=	[]; // detalle de los registros que conforman la nueva casilla

//Dibujar cuando vienen del server
function drawLiquidos(){	
	for (var i = 0; i < f11.liquidos.length; i++) {
		liq = f11.liquidos[i];
		var li=	jQuery("<li>", {"class":"li-nvcsll"}).attr("data-nit", liq.nitLiquidez);
		li.attr("data-monto", parseFloat(liq.vmonto));
		li.attr("data-ejercicio", liq.faLiquidez);
		li.append("<b>"+liq.nombre+":</b>");
		li.append("<em>$"+parseFloat(liq.vmonto)+" para "+liq.faLiquidez+" </em>");
		var bttnRemove=jQuery("<a>", {"class":"command delete"});
		bttnRemove.click(function(e){
			e.preventDefault();
			el = jQuery(this).parents("li:first");
			nit = el.attr("data-nit");
			periodo = el.attr("data-ejercicio");
			f11.removeLiquidez(nit,periodo);
			jQuery(this).parents("li:first").remove();
			calculator.calcular(f11, true);					
			setDatas();
		}).appendTo(li);
		jQuery("#_regsNVCSLL__ ul:first").append(li);
	}
}
jQuery(document).ready(
	function(){
		// lapiz
		jQuery("[target=nvcsll]").click(
				function(){
					jQuery("tr#nvcsll_regs").show(200);
					return false;
				}
			);
		//search
		jQuery(".frst_prt .srch").click( // boton de busqueda
				function(){
					var ths=jQuery(this);
					ths.hide(200);
					ths.next("span").html("buscando...");
					jQuery(".scnd_prt").hide(200);
					currentNuevaCasilla={};					
					url = jsContext + "ruc/liquidez/nombre/"+ths.prev("input").val();
					jQuery.ajax({
							url:url,							
							type:"POST",							
							error:function(xhr,status,error){
								ths.next("span").html("Ocurrio un error!");
								jQuery("#nvcsll_nit").val("");
								setTimeout(function(){
												ths.show(200);
												ths.next("span").html("");
												
											},1000);
							},
							success:function(result,status,xhr){								
								jQuery("#nvcsll_nit").val("");
								if(result.valid){
									currentNuevaCasilla	=	result;
									// aqui es cuando viene el registro encontrado
									jQuery(".scnd_prt").show(200);									
									ths.next("span").html("");
									ths.show(200);
									jQuery(".scnd_prt p:first").html(currentNuevaCasilla.nombre); // la propiedad del nombre del contribuyente
									jQuery(".scnd_prt input[type=text]").val(0.00);
								}else{
									
									ths.next("span").html("No encontrado!");
									setTimeout(function(){
													ths.show(200);
													ths.next("span").html("");
													
												},2000);									
								}
								
								
							}
						}
					);
				}
			);
		
		
		
		// agregar el registro
		
		jQuery("#cancelrnvcsll").click(
				function(){
					jQuery(".scnd_prt").hide(200);
					jQuery("#nvcsll_nit").val("");
					currentNuevaCasilla={};	// currentNuevaCasilla vuelve a ser objeto sin propiedades para evitar que siga clavandole registros a la lista
				}
		);
		
		jQuery("#addrnvcsll").click(
			function(){
				// validaciones antes de agregar a la lista..
				
				if(typeof currentNuevaCasilla.nombre != "string"){   return; } // si no hay un current para el objeto a agregar salir
				if(isNaN(parseFloat(jQuery("#crrntNvCsllMnt").val()))){   return; } // si el valor del monto no es un numero...
				if((f11.totalLiquidez()+parseFloat(jQuery("#crrntNvCsllMnt").val()))>f11.v("310")){
					alert("[Mensaje temporal] 310 es mayor a 312");
					return;
				}				
				// si paso todos los returns de validaciones agrega el registro
				f11.addLiquidez(currentNuevaCasilla.nit,currentNuevaCasilla.nombre,jQuery("#crrntNvCsllEjrcc").val(), 
						parseFloat(jQuery("#crrntNvCsllMnt").val()));
				
				var old_li = jQuery('[data-nit="'+currentNuevaCasilla.nit+'"][data-ejercicio="'+jQuery("#crrntNvCsllEjrcc").val()+'"]');
				if(old_li){
					old_li.remove();
				}
				
				var li=	jQuery("<li>", {"class":"li-nvcsll"}).attr("data-nit", currentNuevaCasilla.nit);
				li.attr("data-monto", parseFloat(jQuery("#crrntNvCsllMnt").val()));
				li.attr("data-ejercicio", jQuery("#crrntNvCsllEjrcc").val());
				li.append("<b>"+currentNuevaCasilla.nombre+":</b>");
				li.append("<em>$"+parseFloat(jQuery("#crrntNvCsllMnt").val())+" para "+jQuery("#crrntNvCsllEjrcc").val()+" </em>");
				var bttnRemove=jQuery("<a>", {"class":"command delete"});
				bttnRemove.click(function(e){
					e.preventDefault();
					el = jQuery(this).parents("li:first");
					nit = el.attr("data-nit");
					periodo = el.attr("data-ejercicio");
					f11.removeLiquidez(nit,periodo);
					jQuery(this).parents("li:first").remove();					
					calculator.calcular(f11, true);					
					setDatas();
				}).appendTo(li);
				jQuery("#_regsNVCSLL__ ul:first").append(li);
				jQuery(".scnd_prt").hide(200);
				jQuery(".scnd_prt input[type=text]").val(0.00);
				
				currentNuevaCasilla={};	// currentNuevaCasilla vuelve a ser objeto sin propiedades para evitar que siga clavandole registros a la lista				
				calculator.calcular(f11, true);				
				setDatas();
			}	
		);
		
		
		jQuery("#nvcsll_cancel, #nvcsll_update").click(
			function(){
				nuevaCasillaDetalle=[];
				jQuery("tr#nvcsll_regs").hide(200);
				jQuery("#nvcsll_nit").val("");
				return false;				
			}
		);
		
		jQuery("#nvcsll_clean").click(
				function(){
					f11.limpiarLiquidez();
					jQuery("#_regsNVCSLL__ ul:first li").remove();
					jQuery("tr#nvcsll_regs").hide(200);
					jQuery("#nvcsll_nit").val("");					
					calculator.calcular(f11, true);
					setDatas();
					return false;				
				}
		);
		
		jQuery("#nvcsll_update").click(
			function (){
											
				/*if(jQuery("#_regsNVCSLL__ ul:first li").length==0){
					f11.limpiarLiquidez();
					jQuery("tr#nvcsll_regs").hide(200);
					jQuery("#c_312").html(""+"0.00");
				}*/
				
			}
				
		);
		
		
		jQuery(".scnd_prt").hide();
		
	}
);