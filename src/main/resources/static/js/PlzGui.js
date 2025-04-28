jQuery.noConflict();
var PlzGui = {
    paso:1,
    saldoResolucion:0,
    configurePlazo:function(){
        jQuery("table.plz tr").removeClass("odd");
        jQuery("table.plz tr").removeClass("even");
        PlzGui.mostrarStatus("Por favor, espere. Cargando...");
        jQuery("table.ps tr").removeClass();
        jQuery("#lnkImprmrrslcn").click( 
            function(){
            	PlzGui.psAlert("Espere un momento mientras se prepara la descarga del PDF de Resoluci&oacute;n...", 5000); 
                jQuery("form[name=crf]").submit();
                return false;    
            }
    	);
        jQuery("input#rprtr_btn_id").click(
            function(){
                PlzGui.psAlert(jQuery("div#rprtrpg").html(),-1);
            }
        );
        jQuery("div.mndmnt").click(
            function(){
            	PlzGui.psAlert("Espere un momento mientras se prepara la descarga del PDF de mandamiento...", 5000); 
            	f	=	jQuery(this).find("form[name=mndmntfmr]:first");
            	f.submit();
            	return false; 
            }  
        );
        jQuery("div.pgs").click(
                function(){
                    var c   =   jQuery(this).find("input[name=monto]:first");
                    if(c==null){return;}
                    if(isNaN(parseFloat(c.val()))){
                    	return;
                    };
                    PAGOES.widgetPagoes("rstdbncs", 
                    		parseFloat(c.val()).toFixed(2), 
                    		"deshabilitarConfirmarAbandono", 
                    		"errorBanco", 
                    		function(){
                    			PlzGui.paso=4;
                    			jQuery("div#pstp4").animate({left:0, top:0}
								                                ,500
								                                , function(){
								                                       PlzGui.configurePaso(null, null);
								                                   }
								                               );
                    		}
                    );
                    
                }
            );
        /*jQuery("table.plz tbody td.nmrT").each(function(){
            
            if(jQuery(this).hasClass("estdpg12")){
                if(!isNaN(parseFloat(jQuery(this).text()))){
                    PlzGui.saldoResolucion += new Number(parseFloat(jQuery(this).text()).toFixed(2));
                }
                
                   
            }
            
        });*/
        //jQuery("span.saldoResolucion").text(parseFloat(PlzGui.saldoResolucion).toFixed(2));
        PlzGui.formatearMonedas();
        
    },
    configurePaso:function(now, fx){
        jQuery("div#dstbls").stop();
        jQuery("div#dstbls").animate({height: jQuery("div#pstp"+PlzGui.paso).innerHeight()}, 200);
        jQuery("div#pstp4").css({"z-index":9500});
        jQuery("div#pstp3").css({"z-index":9400});
        jQuery("div#pstp2").css({"z-index":9300});
        jQuery("div#pstp1").css({"z-index":9200});
    },
    mostrarStatus:function(texto){
        jQuery("div#msjsstts").css({"z-index":10000});
        jQuery("div#msjsstts p").text(texto);
        jQuery("div#msjsstts").css({opacity:0.95});
        jQuery("div#msjsstts").animate({opacity:0}, 1000);
    },
    psAlert:function(){
        mensaje =   (arguments[0] == null ? "mensaje falta." : arguments[0]);
        tiempo  =   arguments[1];
        if(jQuery("div#errbck div#mnsjrrr p").length){
            jQuery("div#errbck div#mnsjrrr p").html(mensaje);
        }else{
            p   =   jQuery("<p>").html(mensaje).appendTo(jQuery("div#errbck div#mnsjrrr"));
        }
        jQuery("div#errbck div#mnsjrrr input[type=button]").remove();
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
        if(jQuery("a.crrrprtrpg").length){jQuery("a.crrrprtrpg").click(function(){jQuery("div#errbck").hide(200);})}
        jQuery("div#mnsjrrr").css({top:pos - (jQuery("div#mnsjrrr").innerHeight()/2)});
        
    },
    formatearMonedas:function(){
        jQuery("table.plz .fmt-m").each(
            function(){
                if(isNaN(parseFloat(jQuery(this).text()))){return;}
                jQuery(this).text(parseFloat(jQuery(this).text()).toFixed(2));
                jQuery(this).text(PlzGui.formatearMoneda(jQuery(this).text()));
	        }
	    );
        jQuery(".saldoResolucion").each(
        	function(){
        		if(isNaN(parseFloat(jQuery(this).text()))){return;}
                jQuery(this).text(parseFloat(jQuery(this).text()).toFixed(2));
                jQuery(this).text("$ "+PlzGui.formatearMoneda(jQuery(this).text()));
        	}
        );
        jQuery(".saldoResolucion").each(
            	function(){
            		if(isNaN(parseFloat(jQuery(this).text()))){return;}
                    jQuery(this).text(parseFloat(jQuery(this).text()).toFixed(2));
                    jQuery(this).text("$ "+PlzGui.formatearMoneda(jQuery(this).text()));
            	}
            );
        jQuery(".tResolucion").each(
            	function(){
            		if(isNaN(parseFloat(jQuery(this).text()))){return;}
                    jQuery(this).text(parseFloat(jQuery(this).text()).toFixed(2));
                    jQuery(this).text("$ "+PlzGui.formatearMoneda(jQuery(this).text()));
            	}
            )
        
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
    }
};
function deshabilitarConfirmarAbandono(url){PlzGui.psAlert("Espere unos instantes mientras es redirigido a la instituci&oacute;n bancaria...", 20000);confirmarAbandono(false); location.href=url;}
function errorBanco(error){PlzGui.psAlert("En estos momentos no podemos enlazar con la instituci&oacute;n seleccionada.<br/>Intente m&aacute;s tarde", 4000);confirmarAbandono(false); setTimeout(function(){location.href=jsContext+"pagorenta";}, 3000) }
function confirmarAbandono(on) {window.onbeforeunload = (on) ? unloadMessage : null;}
function unloadMessage() {
    return 'No ha seleccionado una forma de pago.';
}