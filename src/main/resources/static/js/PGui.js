jQuery.noConflict();
var PGui = {
    paso:1,
    pagoMax:-1,
    anchoSlider:685,
    configurePagos:function(maxPago){
		jQuery("div#psLytExt a").attr("tabindex","-1")
	    jQuery("div#psLytExt input").attr("tabindex","-1")
	    
        this.anchoSlider=(jQuery("div#psLytExt").width());
        jQuery("div#pstp2").css({left:PGui.anchoSlider});
        jQuery("div#pstp3").css({left:PGui.anchoSlider});
        jQuery("div#pstp4").css({left:PGui.anchoSlider});
        jQuery("div#pstp5").css({left:PGui.anchoSlider});
        jQuery("div#pstp6").css({left:PGui.anchoSlider});
        
        if(!isNaN(parseFloat(maxPago))){
        	this.pagoMax = parseFloat(maxPago).toFixed(2);
        }
        
        
        jQuery("div.mdfcrglb-opcnspg").css({top:-(jQuery("div.mdfcrglb-opcnspg").innerHeight()), left:-8, "z-index":9599});
        jQuery("table.ps tr").removeClass();
        jQuery("#opcnscts a:first").addClass("actv");
        jQuery("#opcnscts a").click(
            function(){
                PGui.seleccionarPlanPago(jQuery(this).parents("li:first").index() - 1);
                jQuery("#opcnscts a").removeClass("actv");
                var c   = jQuery(this);
                c.addClass("actv");
                if(jQuery("input[name=commitPlz]:checked").length){
                    jQuery("input[name=commitPlz]:checked").removeAttr("checked");              
                }
                return false;
            }
            
        );
        jQuery("div.pg").css({width:(100/jQuery("div.pg").length -1)+"%"});
        jQuery("a#dtllprcl").click(
            function(){
                PGui.psAlert(jQuery("div#dtllpgprcl").html());
            }
        );
        jQuery("a#dtllbncstrzds_lnk").click(
            function(){
                PGui.psAlert(jQuery("div#dtllbncstrzds").html());
            }
        );
        jQuery("a#dtllbncstrzds_mndmnt_lnk").click(
            function(){
                PGui.psAlert(jQuery("div#dtllbncstrzds_mndmnt").html());
            }
        );
        jQuery("a.dtllbncstrzds_lnk").click(
            function(){
                PGui.psAlert(jQuery("div#dtllbncstrzds").html());
            }
        );
        jQuery("a.dtllbncstrzds_mndmnt_lnk").click(
            function(){
                PGui.psAlert(jQuery("div#dtllbncstrzds_mndmnt").html());
            }
        );
        jQuery("a#rprtrpg_lnk").click(
            function(){
                PGui.psAlert(jQuery("div#rprtrpg").html(),-1);
            }
        );
        jQuery("a#dtllclctrs_lnk").click(
            function(){
                PGui.psAlert(jQuery("div#dtllclctrs").html());
            }
        );
        jQuery("a.dtllclctrs_lnk").click(
            function(){
                PGui.psAlert(jQuery("div#dtllclctrs").html());
            }
        );
        jQuery("a#dtllttl").click(
            function(){
                PGui.psAlert(jQuery("div#dtllpgttl").html());
            }
        );
        jQuery("a#dtllplz").click(
            function(){
                PGui.psAlert(jQuery("div#dtllpgplz").html());
            }
        );
        jQuery("div#tblscts").css({height:jQuery("div.plnct:first").innerHeight()});
        jQuery("div.plnct").each(
            function(){
                jQuery(this).css({top:-jQuery(this).innerHeight()})
            }
        );
        jQuery("div.plnct:first").css({top:0});
        jQuery("input[name=tpago]").click(
            function(){
//                jQuery("div.dpg div.dpgr").animate({top:0}, 200);
            	jQuery("div.dpg div.dpgr").css("top",0);
                jQuery("div.pg").removeClass("pg-act");
                jQuery("div.hpg").each(function(){
                    jQuery(this).removeClass(jQuery(this).attr("id"));
                    jQuery(this).addClass(jQuery(this).attr("id"));
                    jQuery(this).removeClass(jQuery(this).attr("id")+"_over");
                });
                var c   =   jQuery(this).parents("div.hpg:first"); 
                var cl  =   jQuery(this).parents("div.pg:first");
                if(c.length){
                    c.addClass(c.attr("id")+"_over");
                    c.removeClass(c.attr("id"));
                    cl.addClass("pg-act");
//                    cl.find("div.dpgr").animate({top:200}, 200);
                    cl.find("div.dpgr").css("top",200);
                }
            }
        );
        jQuery("div.hpg").mouseover(
            function(){ 
                jQuery("div.mdfcrglb-opcnspg").css({visibility:"hidden"});
                pg  =   jQuery(this).parents("div.pg:first");
                if(pg.find("input[name=tpago]:checked").length){return;}
                c   =   pg.find("div.hpg:first");
                //pg.addClass("pg-act");
                if(c.length){
                    pg.find("div.mdfcrglb-opcnspg:first").css({visibility:"visible"});
                    c.addClass(c.attr("id")+"_over");
                    c.removeClass(c.attr("id"));
                }
            }
        );
        jQuery("div.hpg").mouseout(
            function(){
                jQuery("div.mdfcrglb-opcnspg").css({visibility:"hidden"});
                pg  =   jQuery(this).parents("div.pg:first");
                if(pg.find("input[name=tpago]:checked").length){return;}
                c   =   pg.find("div.hpg:first");
                pg.removeClass("pg-act");
                
                    pg.find("div.mdfcrglb-opcnspg:first").css({visibility:"hidden"});
                    c.removeClass(c.attr("id")+"_over");
                    c.addClass(c.attr("id"));
                
            }
        );
        
//        jQuery("div.hpg").click(
//            function(){
//                jQuery("div.mdfcrglb-opcnspg").css({visibility:"hidden"});
//                pg  =   jQuery(this).parents("div.pg:first");
//                if(pg.find("input[name=tpago]:checked").length){return;}
//                if(pg.find("input[name=tpago]").length){
//                    pg.find("input[name=tpago]").attr("checked", "checked");
//    
//                    jQuery("div.dpg div.dpgr").css("top",0);
//                    jQuery("div.pg").removeClass("pg-act");
//                    jQuery("div.hpg").each(function(){
//                        jQuery(this).removeClass(jQuery(this).attr("id"));
//                        jQuery(this).addClass(jQuery(this).attr("id"));
//                        jQuery(this).removeClass(jQuery(this).attr("id")+"_over");
//                    });
//                    
//                    var cl  =   jQuery(this).parents("div.pg:first");
//                    if(cl.length){
//                    	jQuery(this).addClass(c.attr("id")+"_over");
//                    	jQuery(this).removeClass(c.attr("id"));
//                        cl.addClass("pg-act");
//
//                        cl.find("div.dpgr").css("top",200);
//                    }
//                    
//                }
//            }
//        );
        jQuery("div.dpgr").css({opacity:0.9});
        jQuery("div.dpgr").mouseout(
            function(){
                jQuery("div.mdfcrglb-opcnspg").css({visibility:"hidden"});
                pg  =   jQuery(this).parents("div.pg:first");
                if(pg.find("input[name=tpago]:checked").length){return;}
                c   =   pg.find("div.hpg:first");
                pg.removeClass("pg-act");
                
                    pg.find("div.mdfcrglb-opcnspg:first").css({visibility:"hidden"});
                    c.removeClass(c.attr("id")+"_over");
                    c.addClass(c.attr("id"));
                     
            }
        );
        jQuery("div.dpgr").click(
            function(){
                jQuery("div.mdfcrglb-opcnspg").css({visibility:"hidden"});
                pg  =   jQuery(this).parents("div.pg:first");
                if(pg.find("input[name=tpago]:checked").length){return;}
                if(pg.find("input[name=tpago]").length){
                    pg.find("input[name=tpago]").attr("checked", "checked");
                    pg.find("input[name=tpago]").click();
                }
            
            }
        );
        jQuery("div.dpgr").mouseover(
            function(){
                jQuery("div.mdfcrglb-opcnspg").css({visibility:"hidden"});
                pg  =   jQuery(this).parents("div.pg:first");
                if(pg.find("input[name=tpago]:checked").length){return;}
                c   =   pg.find("div.hpg:first");
                //pg.addClass("pg-act");
                pg.find("div.mdfcrglb-opcnspg:first").css({visibility:"visible"});
                c.addClass(c.attr("id")+"_over");
                c.removeClass(c.attr("id"));
                
            }
        );/**/
        
        jQuery("div.mndmnt").click(
            function(){
                var c   =   jQuery("input[name=tpago]:checked").length > 0 ? jQuery("input[name=tpago]:checked") : null;
                if(c==null){return;}
                if(c.val()=="parcial"){
                    if(isNaN(parseFloat(jQuery("input[name=pparcial]").val()))){
                        PGui.psAlert("Verifique que el monto de pago parcial sea v&aacute;lido.");
                        return;
                    }else{
                        if(parseFloat(jQuery("input[name=pparcial]").val())<=0){
                            PGui.psAlert("Verifique que el monto de pago parcial sea v&aacute;lido.");
                            return;
                        }
                    }
                }
                asincronoPago.imprimirMandamiento();
            }
        );
        jQuery("div.pgs").click(
            function(){
                var c   =   jQuery("input[name=tpago]:checked").length > 0 ? jQuery("input[name=tpago]:checked") : null;
                if(c==null){return;}
                var pago    =   PGui.pagoMax;
                if(c.val()=="parcial"){
                    if(isNaN(parseFloat(jQuery("input[name=pparcial]").val()))){
                        PGui.psAlert("Verifique que el monto de pago parcial sea v&aacute;lido.");
                        return;
                    }else{
                        if(parseFloat(jQuery("input[name=pparcial]").val())<=0 || parseFloat(jQuery("input[name=pparcial]").val())>parseFloat(PGui.pagoMax)){
                            PGui.psAlert("Verifique que el monto de pago parcial sea v&aacute;lido.");
                            return;
                        }
                        pago    =   parseFloat(jQuery("input[name=pparcial]").val()).toFixed(2);
                    }
                }
                PAGOES.widgetPagoes("rstdbncs", 
                		pago, 
                		"deshabilitarConfirmarAbandono", 
                		"errorBanco",
                		function(){
                			PGui.paso=6;
                			jQuery("div#pstp6").animate({left:0, top:0},500, function(){PGui.configurePaso(null, null);});
                		}
                );
                
                
            }
        );
        jQuery("a#mprmrdtllprcls").click(
            function(){
                jQuery("form[name=mprmrdtllprclsfrm]").submit();
                confirmarAbandono(true);
                return false;
            	//PGui.imprimirHistorialPagos(jQuery(this).attr("cd"));
            }
        );
        jQuery("div#slctrplz").click(
            function(){
                PGui.paso=3;
                jQuery("div#pstp3").animate({left:0, top:0}
                                             ,500
                                             , function(){
                                                    PGui.configurePaso(null, null);
                                                }
                                            );
                                            
            }
        );
        jQuery("input[name=pparcial]").keyup(function(event){
                /* /^\$?[1-9][0-9]{0,2}(,[0-9]{3})*(\.[0-9]{2})?$/.test(str) por si quieren tipo moneda -- pensar en la forma de verificar lo de la coma. */
                if(jQuery(this).val().length == 0){return;}
                c   =   jQuery(this);
                if((parseInt(event.keyCode)==37 || parseInt(event.keyCode)==39 || parseInt(event.keyCode)==8 || parseInt(event.keyCode)==46)){return;} //las flechas y teclas al borrar
                
                if(parseInt(event.keyCode)==190 || parseInt(event.keyCode)==110){ // el punto del teclado y del numerico
                    if(c.val().indexOf(".")==c.val().lastIndexOf(".") && c.val().lastIndexOf(".")!=-1){return;}
                }
                if(!(/^[+-]?[0-9]+(\.[0-9]+)?$/.test(c.val()))){
                    c.val("0.00");
                }else{
                    if(!isNaN(parseFloat(c.attr("max")))){
                        if(parseFloat(c.val()) > parseFloat(c.attr("max"))){
                            c.val(parseFloat(c.attr("max")).toFixed(2));
                        }                   
                    }
                }
                
            });
        jQuery("input#emtrrslcn_btn").click(
            function(){
                if(jQuery("input[name=commitPlz]:checked").length){
                    asincronoPago.imprimirResolucion();
                }else{
                    PGui.psAlert("Debe marcar la casilla de confirmaci&oacute;n del plan de pago para poder emitir su Resoluci&oacute;n respectiva.")
                }
            }
        );
        jQuery("input#cnclrrslcn_btn").click(
            function(){
                PGui.paso   =   1;
                jQuery("div#pstp3").animate({top:(-10), left:(jQuery("div#pstp1").width()+10)}, 500, PGui.configurePaso(null, null))
            }
        );
        this.mostrarStatus("cargando...");
        this.formatearMonedas();
    },
    psAlert:function(){
        mensaje =   (arguments[0] == null ? "mensaje falta." : arguments[0]);
        tiempo  =   arguments[1];
        if(jQuery("div#errbck div#mnsjrrr p").length){
            jQuery("div#errbck div#mnsjrrr p").html(mensaje);
        }else{
            p   =   jQuery("<p>").html(mensaje).appendTo(jQuery("div#errbck div#mnsjrrr"));
        }
        jQuery("div#errbck div#mnsjrrr input:button").remove();
        if(tiempo!=null && !isNaN(parseInt(tiempo))){
            if(parseInt(tiempo)>0){
                setTimeout(function(){jQuery("div#errbck").hide(200)}, tiempo);
            }
        }else{
            
            s = jQuery("<input>", {type:"button", value:"aceptar", name:"acptr_btn", align:"center", id:"acptr_btn_id"});
            s.click(
                function(){jQuery("div#errbck").hide(200);}
                
            );
            jQuery("div#errbck div#mnsjrrr").append(s);
        }
        jQuery("div#errbck").css("z-index", 9900);
        jQuery("div#errbck").css({height:jQuery("div#rsLytExt").innerHeight()});
        var pos =   jQuery("div#errbck").innerHeight()*0.5;
        
        
        jQuery("div#errbck").show(200);
        if(jQuery("a.dtllbncstrzds_lnk").length){jQuery("a.dtllbncstrzds_lnk").click(function(){jQuery("a#dtllbncstrzds_lnk").click()})}
        if(jQuery("a.dtllbncstrzds_mndmnt_lnk").length){jQuery("a.dtllbncstrzds_mndmnt_lnk").click(function(){jQuery("a#dtllbncstrzds_mndmnt_lnk").click()})}
        if(jQuery("a.dtllclctrs_lnk").length){jQuery("a.dtllclctrs_lnk").click(function(){jQuery("a#dtllclctrs_lnk").click()})}
        if(jQuery("a.crrrprtrpg").length){jQuery("a.crrrprtrpg").click(function(){jQuery("div#errbck").hide(200);})}
        jQuery("div#mnsjrrr").css({top:pos - (jQuery("div#mnsjrrr").innerHeight()/2)});
        this.moverA("arriba");
        
    },
    seleccionarPlanPago:function(index){
        jQuery("div.plnct").stop();
        var active  =   jQuery("#opcnscts a.actv");
        if(active.length){
            var p1 = jQuery(jQuery("div.plnct")[active.parents("li:first").index()-1]);
            var p2 = jQuery(jQuery("div.plnct")[index]);
            p1.animate( {top:-(p1.innerHeight())},
                        200,
                        function(){
                             p2.animate({top:0}, 
						200, 
						function(){jQuery("div#tblscts").animate({height:p2.innerHeight()}, 
															200, 
															function(){jQuery("div#dstbls").animate({height: jQuery("div#pstp"+PGui.paso).innerHeight()}, 200);})
								}
						);         
                        }
                    )
        }
	
    },
    configurePaso:function(now, fx){
        switch(PGui.paso){
            case 1:
                break;
            case 2:
                break;  
            case 3:
                if(jQuery("input[name=commitPlz]:checked").length){
                    jQuery("input[name=commitPlz]:checked").removeAttr("checked");              
                }
                break;
            case 4:
                jQuery("div#pstp2 *").remove();
                jQuery("div#pstp1 *").remove();
                jQuery("div#pstp3 *").remove();
                break;
            case 5:
                jQuery("div#pstp2 *").remove();
                jQuery("div#pstp1 *").remove();
                jQuery("div#pstp3 *").remove();
                jQuery("div#pstp4 *").remove();
                break;
            case 6:
               
                jQuery("div#pstp2 *").remove();
                jQuery("div#pstp1 *").remove();
                jQuery("div#pstp3 *").remove();
                jQuery("div#pstp4 *").remove();
                jQuery("div#pstp5 *").remove();
                break;
        }
        jQuery("div#dstbls").stop();
        jQuery("div#dstbls").animate({height: jQuery("div#pstp"+PGui.paso).innerHeight()}, 200);
        jQuery("div#pstp2").css({"z-index":9300});
        jQuery("div#pstp1").css({"z-index":9200});
        jQuery("div#pstp3").css({"z-index":9400});
        jQuery("div#pstp4").css({"z-index":9500});
        jQuery("div#pstp5").css({"z-index":9600});
        jQuery("div#pstp6").css({"z-index":9700});
    },
    mostrarStatus:function(texto){
        jQuery("div#msjsstts").css({"z-index":10000});
        jQuery("div#msjsstts p").text(texto);
        jQuery("div#msjsstts").css({opacity:0.95});
        jQuery("div#msjsstts").animate({opacity:0}, 1000);
    },
    imprimirHistorialPagos:function(cdeclaracion){
        confirmarAbandono(false);
        PGui.paso = 5;
                    jQuery("div#pstp5").animate({left:0, top:0}
                                 ,500
                                 , function(){
                                        PGui.configurePaso(null, null);
                                        PGui.psAlert("<b>Generando el historial de pagos parciales. Espere unos segundos...", 4000);
                                        
                                    }
                                );
        f   =   jQuery("<form>").attr("action", asincronoPago.historialPagosUrlPdf+"?cdeclaracion="+cdeclaracion).attr("method", "POST").attr("target", "pdfreceptorHistorial").appendTo("div#ifrmHistorial");
        setTimeout(function(){f.submit();}, 1000);
    },
    procesarBancos:function(){
    	alert(arguments[0]+" "+arguments[1]);
    },
    formatearMonedas:function(){
        jQuery("div#psLytExt .resumenTotal .fmt-m").each(
		function(){
			if(isNaN(parseFloat(jQuery(this).text()))){
				return;
			}
			jQuery(this).text(parseFloat(jQuery(this).text()).toFixed(2));
			jQuery(this).text("$ "+PGui.formatearMoneda(jQuery(this).text()));
		}
	);
        jQuery("div#psLytExt  div#pstp1 .fmt-m").each( 
            function(){
				if(isNaN(parseFloat(jQuery(this).text()))){
					return;
				}
				jQuery(this).text(parseFloat(jQuery(this).text()).toFixed(2));
                jQuery(this).text("$ "+PGui.formatearMoneda(jQuery(this).text()));
            }
        );
	jQuery("div#psLytExt  table.ps .fmt-m").each(
            function(){
		if(isNaN(parseFloat(jQuery(this).text()))){
			return;
		}
		jQuery(this).text(parseFloat(jQuery(this).text()).toFixed(2));
                jQuery(this).text(PGui.formatearMoneda(jQuery(this).text()));
            }
        );
	//fmttot
		jQuery("div#psLytExt  b.fmttot").each(
            function(){
		if(isNaN(parseFloat(jQuery(this).text()))){
			return;
		}
		jQuery(this).text(parseFloat(jQuery(this).text()).toFixed(2));
                jQuery(this).text("$ "+PGui.formatearMoneda(jQuery(this).text()));
            }
        );
    },
    formatearMoneda:function(valor){
	
        if(valor.length){
            var m   =   valor.replace(",","").replace("-","");
            var d   =   ".00";
            if(m.indexOf(".")>0){
                m   =   m.split(".")[0];
                d   =   "."+valor.split(".")[1];
            }
            var temp    =   "";
            var c   =   1;
            
            for(var i=(m.length-1); i>=0; i--){
                if(c%3 == 0 && i!=0){
                    temp = ","+m.charAt(i)+temp;
                }else{
                    temp = m.charAt(i)+temp;
                }
                c++;
            }
            return temp + d;
        }
        return "";
    },
    moverA:function(donde){
        if(donde=="arriba"){
            jQuery("html, body").animate({scrollTop:jQuery("a#anclaTop").offset().top}, 500);
        }
        
    }


};
function deshabilitarConfirmarAbandono(url){PGui.psAlert("Espere unos instantes mientras es redirigido a la instituci&oacute;n bancaria...", 20000);confirmarAbandono(false); location.href=url;}
function errorBanco(error){PGui.psAlert("En estos momentos no podemos enlazar con la instituci&oacute;n seleccionada.<br/>Intente m&aacute;s tarde", 4000);confirmarAbandono(false); setTimeout(function(){location.href=jsContext+"/pagorenta/";}, 3000) }
function confirmarAbandono(on) {window.onbeforeunload = (on) ? unloadMessage : null;}
function unloadMessage() {
    return 'No ha seleccionado una forma de pago.';
}
