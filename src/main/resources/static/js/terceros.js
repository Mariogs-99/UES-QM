function onSuccessNit(data, statusText){
		    if(statusText == "success"){
		        try{
		            jQuery("input[name=nitT]").val("");
		            
		            if(!data.valid){		            	
		            	if(data.mensaje!=""){
		            		jQuery("#nitSearchResult").addClass("notFound").html(data.mensaje);
		            	}else{
		            		jQuery("#nitSearchResult").addClass("notFound").html("no v&aacute;lido");
		            	}
		            	
		            }else{
		            	
		            	jQuery("#nitSearchResult").addClass("success").html(data.nombre + " ").show(200);
		            	jQuery(".nitResult").addClass("success").html(data.nombre + " ");
		            	jQuery("#initProcess").show(200);
		            }
		        }catch(e){
		        	jQuery("#nitSearchResult").addClass("notFound").html("inconveniente:"+e);
		            
		        }
		    }else{
		    	
		    }     
		    
		}
	
	
		jQuery(document).ready(
			function(){
				try{
					jQuery("input[name=nitT]").keyup(
						function(){
							jQuery("#initProcess").hide(200);
							jQuery(".nitResult").addClass("success").html(" ");
							if(parseInt(event.keyCode) == 13){
								jQuery("input#sNit").click();
							}
						}	
					);
					jQuery("input#sNit").click(	
							function(){
								jQuery("#initProcess").hide(200);
								jQuery(".nitResult").addClass("success").html(" ");
								var rg_nit  =  /\d{14}/;
								jQuery("#nitSearchResult").show();
								jQuery("#nitSearchResult").removeClass("notFound");
								jQuery("#nitSearchResult").removeClass("info");
								jQuery("#nitSearchResult").removeClass("success");
				                if(jQuery("input[name=nitT]").val().match(rg_nit)==null){
				                	jQuery("#nitSearchResult").addClass("notFound").html("Revise nit.");
				                   return;
				                }
				                jQuery("#nitSearchResult").html("Buscando, por favor espere...");
				                jQuery.ajax({   url: jsContext + "ruc/terceros/nit/"+jQuery("input[name=nitT]").val(), 
				                                type:"POST",
				                                dataType:"json",				                                
				                                success:onSuccessNit, 
				                                error:function(data){
				                                    switch(parseInt(data.status)){
				                                    case 400:
				                                    case 403:
				                                    case 404:
				                                    case 405:
				                                    case 500:
				                                    case 503:
				                                    	jQuery("#nitSearchResult").addClass("notFound").html("Inconveniente:"+data.status);
				                                        break;
				                                    default:
				                                    	jQuery("#nitSearchResult").addClass("notFound").html("Error al intentar consultar NIT");
				                                        break;
				                                }
				                                
				                            }
				                            });
								
								
								
							}
					);
					//jQuery("span#variableNit").text(NIT);
				}catch(e){
					
				}
				
			}
			
		);