/*--*/

function enableAddIncomeR(){
	var devengado	=	isNumeric(jQuery("input[name=gR]").val())?parseFloat(jQuery("input[name=gR]").val()):0.0;
	var retenido	=	isNumeric(jQuery("input[name=rR]").val())?parseFloat(jQuery("input[name=rR]").val()):0.0;
	var porcentajeR	=	Porcentajes.retenido;
	if(jQuery("select[name=codeIncomeR]").val() == "01"){
		porcentajeR	=	Porcentajes.retenido01;
	}
	if(devengado*retenido>0 && retenido<=devengado*porcentajeR){
		jQuery("div#b_addIncomeRContainer").show();
		jQuery("p#advertenciaR").show();
		jQuery("input#b_addIncomeR").removeAttr("disabled");
	}else{
		jQuery("div#b_addIncomeRContainer").hide();
		jQuery("p#advertenciaR").hide();
		jQuery("input#b_addIncomeR").attr("disabled", "disabled");
	}
}
function enableAddIncomeRNR60(){
	var devengado	=	isNumeric(jQuery("input[name=gRNR60]").val())?parseFloat(jQuery("input[name=gRNR60]").val()):0.0;
	
	if(devengado>0){
		jQuery("div#b_addIncomeRNR60Container").show();
		jQuery("input#b_addIncomeNR60").removeAttr("disabled");
	}else{
		jQuery("div#b_addIncomeRNR60Container").hide();
		jQuery("input#b_addIncomeNR60").attr("disabled", "disabled");
	}
}

function inputEvents(){
	jQuery("input.inputText, input.inputText2").keyup(
		function(event){
			_keyUp(this,event);
		}
	);
	jQuery("input.inputText, input.inputText2").focusout(
		function(event){
			_focusOut(this);
		}
	);
	jQuery("input[type=text].cf11").focusout(
			function(){
				var obj	=	jQuery(this);
				if(!isNullOrUndefined(obj.attr("id"))){
					var _code	=	obj.attr("id").replace("_","");
					var val		=	parseFloat(obj.val());
					if(obj.hasClass("string")){
						val	=	obj.val();
					}
					if(!isNullOrUndefined(f11.get(_code))){
						if(!isNullOrUndefined(f11.get(_code).valor)){
							f11.update(_code.replace("c",""), val, false, false);
						}else{
							f11.set(_code, val);
						}
					}
					
					calculator.calcular(f11, false);
					setDatas();
					
				}
			}	
		);
	jQuery("#expensesContainer .inputText, #deductibles .inputText, #industrialContainer .inputText").focus(
		function(){
			jQuery("input.cancelR-NR").click();
			
		}	
	);
	jQuery("input.inputText, input.inputText2").focus(
		function(){
			if(jQuery(this).hasClass("readMode")) return;
			if(jQuery(this).attr("type")=="text"){jQuery(this).select();}
		}
	);
	
	jQuery("input[name=gR], input[name=rR]").keyup(
		function(){
			enableAddIncomeR();
		}
	);
	jQuery("input[name=gRNR60]").keyup(
			function(){
				enableAddIncomeRNR60();
			}
		);
	jQuery(".documentForm .inputText2").focusout(
		function(event){
			checkEditedDocuments();
			_focusOut(this);
		}	
	);
	jQuery(".documentForm .inputText2").focus(
		function(){
			checkEditedDocuments();
			jQuery(this).select();
		}	
	);
}



function fillCodes(codes, jQuerySelect){
	jQuery("select[name=codeIncomeR] option").remove();
	jQuery("select[name=codeIncomeNR60] option").remove();
	if(jQuerySelect.find("option").length>0){
		jQuerySelect.find("option").remove();
	}
	for(var i=0; i<codes.length; i++){                                      
		for(var l=0; l<codigosIngreso.length; l++){
			if(codes[i]==codigosIngreso[l].CIngreso){
				
				jQuerySelect.append(jQuery("<option>", {"value":codigosIngreso[l].CIngreso, "text":codigosIngreso[l].CIngreso +" "+codigosIngreso[l].DIngreso}));
			}
		}
	}
}

function buttonEvents(){
		/*************** Nuevo Ingreso****************/
		jQuery("ul.subCommand a").click(
			function(){
				var t	=	jQuery(this);
				jQuery("ul.subCommand a").removeClass("active");
				t.addClass("active");
				jQuery("ul.subCommand div.context-a").css({display:"none"});
			}
		);
		jQuery("ul.subCommand li.item a.commandLine2").mouseenter(
				function(){
					var l	=	jQuery(this).parents("li.item:first");
					
					jQuery("ul.subCommand div.context-a").css({display:"none"});
					
					l.find("div.context-a").css({display:"block"});
				}
			);
		jQuery("ul.subCommand li.item a.commandLine2").mouseleave(
				function(){
					jQuery("ul.subCommand div.context-a").css({display:"none"});
				}
			);
		jQuery("a#b_addNewNR_60, a#b_addNewNG").click(
			function(){
				jQuery(".newR input.reset").val("");
				jQuery(".newR p.reset").text("");
				jQuery("div#b_addIncomeRNR60Container").hide();
				jQuery("input#b_addIncomeNR60").attr("disabled", "disabled");
				jQuery(".newR").css({"display":"none"});
				jQuery("#formNewNR60 div#confirmAnotherIncomeNR60").css({"display":"none"});
				jQuery("#formNewNR60 div#addingIncomeNR60").css({"display":"block"});
				jQuery("#formNewNR60").show();
				jQuery("#formNewNR60 .conditional").hide();
			}
		);
		jQuery("a#b_addNewNG").click(
			function(){
				var nonTaxableCodes = getNonTaxableCodes(f11.tipoContribuyente == "J");
				fillCodes(nonTaxableCodes, jQuery("select[name=codeIncomeNR60]"));
				incomeType	=	incomeTypes.NONTAXEABLE;
				jQuery("#formNewNR60 h3").html("Nuevo ingreso NO Gravado");
				jQuery("#formNewNR60 span.monto").text("monto:");
				
			}	
		);
		jQuery("a#b_addNewNR_60").click(
			function(){
				
				jQuery("select[name=codeIncomeNR60] option").remove();
				incomeType	=	incomeTypes.SALARY;
				jQuery("#formNewNR60 h3").html("Nuevo Salario SIN Retenci&oacute;n");
				jQuery("#formNewNR60 span.monto").text("monto:");
			}	
		);
		jQuery("a#b_addNewNR").click(
				function(){
					incomeType=incomeTypes.ACTIVITIES;
					jQuery(".newR").css({"display":"none"});
					jQuery("#formNewNR").show();
				}
		);
			
		jQuery("a#b_addNewR, a#b_addNewR2").click(
				function(){
					jQuery(".newR input.reset").val("");
					jQuery(".newR p.reset").text("");
					jQuery("div#b_addIncomeRContainer").hide();
					jQuery("input#b_addIncomeR").attr("disabled", "disabled");
					jQuery(".newR").css({"display":"none"});
					jQuery("#formNewR div#confirmAnotherIncomeR").css({"display":"none"});
					jQuery("#formNewR div#addingIncomeR").css({"display":"block"});
					jQuery("#formNewR").show();
					jQuery("#formNewR .conditional").hide();
					jQuery("p#advertenciaR").hide();
					
				}
		);
		jQuery("a#b_addNewR").click(
			function(){
				incomeType=incomeTypes.ACREDITABLE;
				jQuery("div#formNewR h3").html("Nuevo Ingreso con Retenci&oacute;n Acreditable");
				var acreditableCodes = getAcreditableCodes(f11.tipoContribuyente == "J");
				fillCodes(acreditableCodes, jQuery("select[name=codeIncomeR]"));
			}
		);
		jQuery("a#b_addNewR2").click(
			function(){
				incomeType=incomeTypes.NONACREDITABLE;
				jQuery("div#formNewR h3").html("Nuevo Ingreso con Retenci&oacute;n No Acreditable");
				var nonAcreditableCodes = getNonAcreditableCodes(f11.tipoContribuyente == "J", f11.domiciliado);
				fillCodes(nonAcreditableCodes, jQuery("select[name=codeIncomeR]"));
			}
		);
		jQuery("select[name=codeIncomeR]").change(
			function(){
				enableAddIncomeR();
			}
		);
		jQuery("ul.subCommand > li > a").click(function(e){e.preventDefault();});
		jQuery("ul.documents > li > a").click(function(e){e.preventDefault();});
		jQuery("ul.multipleCommand > li").mouseenter(
			function(){
				jQuery(this).find("ul").css({"top":jQuery(this).height()}).show();
			}
		);
		
		jQuery("input[name=nitR]").keyup(
			function(event){
				if(parseInt(event.keyCode) == 13){
					jQuery("a#searchNit").click();
				}
			}
		);
		jQuery("input[name=nitRNR60]").keyup(
				function(event){
					if(parseInt(event.keyCode) == 13){
						jQuery("a#searchNitNR60").click();
					}
				}
			);
		jQuery("input#b_addIncomeNR").click(
				function(){
					if(addIncomeRowsNR()){
						jQuery(".newR").css({"display":"none"});
						showHideIncomesList();
					}else{
						//msg("no fue posible agregar los ingresos");
					}
					
				}	
			);
		jQuery("input#b_addIncomeNR60").click(
			function(){
				if(addIncomeRowNR60()){
					jQuery(".newR input.reset").val(0.0);
					jQuery("div#b_addIncomeRNR60Container").hide();
					jQuery("input#b_addIncomeNR60").attr("disabled", "disabled");
					jQuery("#formNewNR60 div#confirmAnotherIncomeNR60").css({"display":"block"});
					jQuery("#formNewNR60 div#addingIncomeNR60").css({"display":"none"});
					jQuery("#formNewNR60 div#addingIncomeNR60 .conditional").css({"display":"none"});
					showHideIncomesList();
				}
				
//				jQuery(".newR").cssctrlz({"display":"none"});
//				showHideIncomesList();
			}
		);
		jQuery("input#b_addIncomeR").click(
			function(){
				
				if(addIncomeRowR()){
					jQuery(".newR input.reset").val(0.0);
					jQuery("div#b_addIncomeRContainer").hide();
					jQuery("input#b_addIncomeR").attr("disabled", "disabled");
					jQuery("#formNewR div#confirmAnotherIncomeR").css({"display":"block"});
					jQuery("#formNewR div#addingIncomeR").css({"display":"none"});
					jQuery("#formNewR div#addingIncomeR .conditional").css({"display":"none"});
					
				}
				showHideIncomesList();
			}	
		);
		jQuery("input.cancelR-NR").click(
				function(){
					jQuery(".newR").hide();
					jQuery(".newR input.reset").val(0.0);
					incomeType	=	null;
					jQuery("ul.subCommand a.commandLine2").removeClass("active");
				}	
			);
		jQuery("#b_finishAddIncomeR, #b_finishAddIncomeNR60").click(
				function(){
					jQuery("input.cancelR-NR").click();
					
				}	
			);
		jQuery("#b_addAnotherIncomeR").click(
				function(){
					if(incomeType==incomeTypes.ACREDITABLE){
						jQuery("a#b_addNewR").click();
					}
					if(incomeType==incomeTypes.NONACREDITABLE){
						jQuery("a#b_addNewR2").click();
					}
					
				}	
			);
		jQuery("#b_addAnotherIncomeNR60").click(
				function(){
					if(incomeType==incomeTypes.SALARY){
						jQuery("a#b_addNewNR_60").click();
					}
					if(incomeType==incomeTypes.NONTAXEABLE){
						jQuery("a#b_addNewNG").click();
					}
					
				}	
			);
		
		/********************NIT************************/
		
		jQuery("a#searchNit").click(
				function(){
					var rg_nit  =  /\d{14}/;
	                if(jQuery("input[name=nitR]").val().match(rg_nit)==null){
	                   msgR(m.nit.malEscrito);
	                   return;
	                }
					buscarNit(jQuery("input[name=nitR]").val(), onSuccessNitR);
					
					
				}
		);
		jQuery("a#searchNitNR60").click(
				function(){
					var rg_nit  =  /\d{14}/;
	                if(jQuery("input[name=nitRNR60]").val().match(rg_nit)==null){
	                   msgRNR60(m.nit.malEscrito);
	                   return;
	                }
					buscarNit(jQuery("input[name=nitRNR60]").val(), onSuccessNitRNR60);
					
					
				}
		);
		
		
		/*****************wizard***********************/
		
		jQuery("a.command, a.wizard, a.cf11").click(
				function(e){e.preventDefault();}
		);
		jQuery("#actionButtons input.action").click(
			function(){jQuery("input.cancelR-NR").click();}
		);
		
		jQuery("#modify").click(
				function(){
					switch(_stp){
						case 1:
							jQuery("#deductiblesContainer input.inputText, " +
									"#expensesContainer input.inputText, " +
									"#industrialContainer input.inputText")
									.addClass("editMode")
									.removeClass("readMode")
									.removeAttr("readonly");
							jQuery("#toolsIncomes").show();
							jQuery("div#modifyResume").show();
							jQuery("#next, #commit")
								.attr("disabled","disabled")
								.css({"opacity":lOpacity});
							jQuery(this).attr("disabled","disabled").css({"display":"none"});
							jQuery("#apply").removeAttr("disabled").css({"display":"block", "opacity":hOpacity});
							jQuery("a.delete").show();
							jQuery("html, body").animate({scrollTop:jQuery("a#anclaTop").offset().top}, 500);
							break;
					}
				}

		);
		
		
		
		jQuery("#apply").click(
				function(){
					swal("Calculando...");					
					f11.post(jsContext + "api/sug/f11/save", 
							applyPost, 
							function(e){
								error("<p>Status:"+e.status+"</p><p>"+e.error+"</p>");
								}
					);					
				}

		);
		jQuery("#ctrlz").click(
			function(){
				msg("Reestableciendo datos...");
				jQuery("html, body").animate({scrollTop:jQuery("a#anclaTop").offset().top}, 500);
				window.location=jsContext+"f11/restart";
//				setTimeout(
//						function(){
//							f11.setFromJson(DATOS);
//							printDatas();
//							
//							setDatas();
//							showHideIncomesList();
//							msg("Los datos han sido reestablecidos!");
//						},
//						1000
//				);
				/*jQuery.ajax({  url:jsContext + "f11/cancelar", 
                    type:"GET",                    
                    success:function(){
                    	window.location=jsContext+"f11";
                    }, 
                    error:function(e){
						error("<p>Status:"+e.status+"</p><p>"+e.error+"</p>");
					}
                });*/
				
				
			}
		);
		
		function validarPagoMinimo(){
			casilla = f11.get("c630");			
			if(casilla.visible && f11.v("630") <= 0){
				swal({					
					text: "Contribuyente sujeto a c"+String.fromCharCode(225)+"lculo de pago m"+String.fromCharCode(237)+"nimo, complementar Secci"+String.fromCharCode(243)+"n " +
							"de Pago M"+String.fromCharCode(237)+"nimo",   
					type: "warning",
					showConfirmButton:true
				});
				return false;
			}
			return true;
		}
		
		jQuery("#next").click(
				
				function(){
					if(_stp>=4) return;
					
					switch(_stp){
						case 1:
							_stp=2;
							jQuery(".step").css({"display":"none"});
							jQuery("#step"+_stp).css({"display":"block"});
							wizardStp(_stp);
							break;
						case 2:	
							 if(!validarPagoMinimo()){
								return; 
							 }
							  if(f11.get("c345").valor == 0){// a pagar
								_stp=4;
								swal("Calculando...");
								f11.post(jsContext + "api/sug/f11/save", 
										function(data){
											nextPost(data);
											jQuery(".step").css({"display":"none"});
											wizardStp(_stp);
											jQuery("#step"+_stp).css({"display":"block"});
											swalClose();
										}, 
										function(e){
											error("<p>Status:"+e.status+"</p><p>"+e.error+"</p>");
											swalClose();
										}
								);
							}else{
								// a devolver								
								if(f11.tipoContribuyente == "N"){
									_stp=3;
									jQuery(".step").css({"display":"none"});
									wizardStp(_stp);
									jQuery("#step"+_stp).css({"display":"block"});
								}else{
									_stp=4;
									swal("Calculando...");
									f11.post(jsContext + "api/sug/f11/save", 
											function(data){
												nextPost(data);
												jQuery(".step").css({"display":"none"});
												wizardStp(_stp);
												jQuery("#step"+_stp).css({"display":"block"});
												swalClose();
											}, 
											function(e){
												error("<p>Status:"+e.status+"</p><p>"+e.error+"</p>");
												swalClose();
											}
									);
								}
								
								
							}						
							break;
						case 3:
							if(!validarPagoMinimo()){
									return; 
							}
							swal("Calculando...");
							f11.post(jsContext + "api/sug/f11/save", 
									function(data){
										nextPost(data);
										_stp=4;
										jQuery(".step").css({"display":"none"});
										wizardStp(_stp);										
										jQuery("#step"+_stp).css({"display":"block"});
										swalClose();
							}, 
							function(e){
								error("<p>Status:"+e.status+"</p><p>"+e.error+"</p>");
								swalClose();
								}
							);
							
							/*var jsonBanco	=	{banco:"", tipoCuenta:"", numeroCuenta:""};
							if(jQuery("input[name=metodo_pago]:checked").val()=="otro"){
								jsonBanco.banco=jQuery("select[name=banco] option:checked").val();
								jsonBanco.tipoCuenta=jQuery("select[name=tipoCuenta] option:checked").val();
								jsonBanco.numeroCuenta=jQuery("input[name=c920]").val();
							}
							jQuery.ajax({  url:jsContext + "api/sug/f11/banco", 
			                    type:"POST",
			                    data:jsonBanco,
			                    success:function(){
			                    	_stp=4;
									jQuery(".step").css({"display":"none"});
									wizardStp(_stp);
									
									
									jQuery("#step"+_stp).css({"display":"block"});

			                    }, 
			                    error:function(e){
									error("<p>Status:"+e.status+"</p><p>"+e.error+"</p>");
									}
			                });*/
							
														
							break;
					}
				}

		);
		
		jQuery("input[name=commit]").click(
			function(){
				if(jQuery("input[name=commit]:checked").length==0){
					jQuery("#commit").css({"display":"none"}).attr("disabled","disabled").css({opacity:lOpacity});
				}else{
					jQuery("#commit").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
				}
			}	
		);
			
		jQuery("#commit").click(
			function(){
				if(jQuery("input[name=commit]:checked").length==0){
					alert("Por favor, marque la casilla de confirmación, antes de presionar este botón");
					return;
				}
				error("Enviando declaraci&oacute;n, espere un momento...");				
				jQuery("#commit").css({"display":"none"}).attr("disabled","disabled").css({opacity:lOpacity});
				f11.presentar(commitD,
						function(e){
							//swalClose();
							jQuery("#commit").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
							error("<p>Status:"+e.status+"</p><p>"+e.error+"</p>");
				});
			}
				
		);
		jQuery("#back").click(
				
				function(){
					if(_stp<1) return;
					if(_stp==4){
						if(f11.get("c345").valor==0){// a pagar
							_stp=3;
						}else{
							//a devolver
							if(f11.tipoContribuyente == "N"){
								_stp=4;
							}else{
								//juridico
								_stp=3;
							}
							
						}
					}
					switch(_stp){
						case 2:
							_stp=1;
							
							jQuery(".step").css({"display":"none"});
							jQuery("#step"+_stp).css({"display":"block"});
							wizardStp(_stp);
							break;
						case 3:
							_stp=2;
							jQuery(".step").css({"display":"none"});
							jQuery("#step"+_stp).css({"display":"block"});
							wizardStp(_stp);
							break;
						case 4:
							_stp=3;
							jQuery(".step").css({"display":"none"});
							jQuery("#step"+_stp).css({"display":"block"});
							wizardStp(_stp);
							break;
					}
				}

		);
		/*cuenta bancaria*/
                jQuery("#step3 .opt input[name=metodo_pago]").click(
			function(){
                                
				//jQuery("#step3 div.opt input[type=radio]").removeAttr("checked");
				//jQuery(this).find("input[type=radio]").attr("checked", "checked");
				jQuery("#step3 .opt").removeClass("active");
				jQuery(this).parents("div.opt:first").addClass("active");
				jQuery(".optionBanks").hide();
				jQuery("#banks_"+jQuery(this).val()).show();
				if(jQuery(this).val()=="agricola"){
					jQuery("#next").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
					jQuery("#step3 select, #step3 input[type=text], #step3 input[type=hidden]").attr("disabled","disabled");
					f11.update("920","",false,false);
					f11.update("925","",false,false);
					f11.update("930","",false,false);
				}
				if(jQuery(this).val()=="otro"){
					jQuery("#step3 select, #step3 input").removeAttr("disabled");
					//jQuery("select[name=c930] option:first").attr("selected","selected");
					jQuery("select[name=c925]").val(f11.get("c925").valor);
					jQuery("select[name=c930]").val(f11.get("c930").valor);
					jQuery("#next")
					.css({"display":"none"})
					.attr("disabled","disabled")
					.css({opacity:lOpacity});
				}
                                
			}
		);
//		jQuery("#step3 .opt").click(
//			function(){
//				jQuery("#step3 div.opt input[type=radio]").removeAttr("checked");
//				jQuery(this).find("input[type=radio]").attr("checked", "checked");
//				jQuery("#step3 .opt").removeClass("active");
//				jQuery(this).addClass("active");
//				jQuery(".optionBanks").hide();
//				jQuery("#banks_"+jQuery(this).find("input[type=radio]").val()).show();
//				if(jQuery("input[name=metodo_pago]:checked").val()=="agricola"){
//					jQuery("#next").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
//					jQuery("#step3 select, #step3 input").attr("disabled","disabled");
//				}
//				if(jQuery("input[name=metodo_pago]:checked").val()=="otro"){
//					jQuery("#step3 select, #step3 input").removeAttr("disabled");
//					jQuery("select[name=banco] option:first").attr("selected","selected");
//					jQuery("select[name=tipoCuenta] option:first").attr("selected","selected");
//					jQuery("#next")
//					.css({"display":"none"})
//					.attr("disabled","disabled")
//					.css({opacity:lOpacity});
//				}
//			}
//		);
		jQuery("input[name=c920]").keyup(
			function(){
				jQuery("select[name=c930]").change();				
				f11.update(jQuery(this).attr("id").replace("c_",""), 
						jQuery(this).val(), true, true);
				//jQuery("input[name="+jQuery(this).attr("id").replace("_","")+"]").val(jQuery(this).val());
			}
		);
		jQuery("select[name=c930], select[name=c925]").change(
			function(){
				if(jQuery("input[name=metodo_pago]:checked").val()=="otro"){
					if(jQuery("select[name=c930]").val().length>0 
						&& jQuery("select[name=c925]").val().length>0
						&& jQuery("input[name=c920]").val().length>0){
							jQuery("#next").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
					}else{
						jQuery("#next").css({"display":"none"}).attr("disabled","disable");
					}					
					f11.update(jQuery(this).attr("id").replace("c_",""),
							jQuery(this).val(), true, true);
					//jQuery("input[name="+jQuery(this).attr("id").replace("_","")+"]").val(jQuery(this).val());
				}
			}
		);
		/************************seleccionar opcion en los documentos***************************/
		
                //Colocar en color llamativo titulo de pago minimo.
		        //mostrar en vista previa el pdf capado sin datos de sello y recepcion
		        // mensajes para personas juridicas
                
                jQuery("#step2 .opt input[name=fill_docs]").click(
			
			function(){
				try{
					//jQuery("#step2 div.opt input[type=radio]").removeAttr("checked");
                                        //jQuery(this).find("input[type=radio]").attr("checked", "checked");
					jQuery("#step2 .opt").removeClass("active");
					jQuery(this).parents("div.opt:first").addClass("active");
					jQuery("#next").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
					if(jQuery(this).val()=="2"){
						jQuery("div#documentList ul.documents a:first").click();
						jQuery("div#documentList").show();
						//anclaDocuments
						jQuery("html, body").animate({scrollTop:jQuery("a#anclaDocuments").offset().top}, 500);
					}else{
						jQuery("div#documentList").hide();
						
						jQuery("div#documentList input.cf11").each(
							function(index, object){
								var obj	=	jQuery(object);
								if(!isNullOrUndefined(obj.attr("id"))){
									var code	=	obj.attr("id").replace("_","");
									
									if(!isNullOrUndefined(f11.get(code))){
										if(!isNullOrUndefined(f11.get(code).valor)){
											code	=	code.replace("c","").replace("_","");
											f11.update(code, 0.0, false, false);
										}else{
											f11.set(code, 0.0);
										}
										
									}
								}
							}
						);
						c017="";
						jQuery("a#c_017").text(c017);
						jQuery("tr#trCode17").hide();
						//jQuery("tr#trCode22").hide();
						if(!isNullOrUndefined(f11.get("c017"))){
							f11.update("017", c017, false, false);
						}
						
						/*if(!isNullOrUndefined(f11.get("c022"))){
							f11.update("022", "", false, false);
							jQuery("input[name=codes22].user:checked").removeAttr("checked");
						}*/
						calculator.calcular(f11, false);
						setDatas();
						checkEditedDocuments();
					}
				}catch(e){
					error(e);
					
				}
				
				
			}
		);
                
		
//		jQuery("#step2 .opt").click(
//			
//			function(){
//				try{
//					jQuery("#step2 div.opt input[type=radio]").removeAttr("checked");
//                                        jQuery(this).find("input[type=radio]").attr("checked", "checked");
//					jQuery("#step2 .opt").removeClass("active");
//					jQuery(this).addClass("active");
//					jQuery("#next").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
//					if(jQuery("input[name=fill_docs]:checked").val()==2){
//						jQuery("div#documentList ul.documents a:first").click();
//						jQuery("div#documentList").show();
//						//anclaDocuments
//						jQuery("html, body").animate({scrollTop:jQuery("a#anclaDocuments").offset().top}, 500);
//					}else{
//						jQuery("div#documentList").hide();
//						
//						jQuery("div#documentList input.cf11").each(
//							function(index, object){
//								var obj	=	jQuery(object);
//								if(!isNullOrUndefined(obj.attr("id"))){
//									var code	=	obj.attr("id").replace("_","");
//									
//									if(!isNullOrUndefined(f11.get(code))){
//										if(!isNullOrUndefined(f11.get(code).valor)){
//											code	=	code.replace("c","").replace("_","");
//											f11.update(code, 0.0, false, false);
//										}else{
//											f11.set(code, 0.0);
//										}
//										
//									}
//								}
//							}
//						);
//						c017="";
//						jQuery("a#c_017").text(c017);
//						jQuery("tr#trCode17").hide();
//						jQuery("tr#trCode22").hide();
//						if(!isNullOrUndefined(f11.get("c017"))){
//							f11.update("017", c017, false, false);
//						}
//						
//						if(!isNullOrUndefined(f11.get("c022"))){
//							f11.update("022", "", false, false);
//							jQuery("input[name=codes22].user:checked").removeAttr("checked");
//						}
//						calculator.calcular(f11, false);
//						setDatas();
//						checkEditedDocuments();
//					}
//				}catch(e){
//					error(e);
//					
//				}
//				
//				
//			}
//		);
		
		
		
		/****************** Donacion *******************/
		
		jQuery("a#showDonationDetail").click(
			function(){
				jQuery("div#deductibles").hide();
				jQuery("div#donationsDetail").show();
			}
		);
		jQuery("a#_backDeductibles").click(
			function(){
				jQuery("div#donationsDetail").hide();
				jQuery("div#deductibles").show();
			}
		);
		
		
		/***************** Documentos ******************/
		jQuery("ul.documents a").click(
				function(){
					
					index	=	jQuery(this).parents("li:first").index();					
					jQuery("ul.documents a").removeClass("active");
					jQuery(this).addClass("active");
					jQuery(".documentForm").hide();
					jQuery("#documentDetail .dialog").hide();
					jQuery(jQuery(".documentForm")[index]).show();
					jQuery(".documentForm-resume").show();
					checkEditedDocuments();
				}	
			);
		jQuery("a#b_c738, a#c_738").click(
			function(){
				
				jQuery("tr#c738form").show(200);
				
			}
		);
		jQuery("a#b_c730, a#c_730").click(
				function(){
					jQuery("tr#c730form").show(200);
					
				}
			);
		jQuery("input.closeDialog").click(
			function(){
				
				jQuery(this).parents("tr:first").hide(200);
			}	
		);
		
		
		
		
		
		jQuery("a#c_017, a#editCode17").click(
			function(){
				jQuery("tr#trCode17").show(200);
				return false;
			}
		);
		
		jQuery("a#c_024, a#editCode24").click(
				function(){
					jQuery("tr#trCode24").show(200);
					return false;
				}
		);
		
		jQuery("input.addCode17").click(
			function(){
				if(c017.length<=0){
					//alert("Advertencia: No ha seleccionado casillas.");
				}
				jQuery("a#c_017").text(c017);
				jQuery("tr#trCode17").hide();
				f11.update("017", c017, false, false);
			}
		);
		jQuery("input.addCode24").click(
				function(){
					if(jQuery("input[name=codes24]:checked").length==0){
						//alert("Advertencia: No ha seleccionado casillas.");
					}					
					jQuery("tr#trCode24").hide();
					f11.update("024", c024, false, false);
					calculator.calcular(f11,false);
					setDatas();
				}
		);
		
		jQuery("input[name=codes17]").click(
				function(){
					jQuery("input[name=codes17]").removeAttr("disabled");
					if(jQuery("input[name=codes17]:checked").length>=5){
						
						jQuery("input[name=codes17]").attr("disabled","disabled");
						jQuery("input[name=codes17]:checked").removeAttr("disabled");
						
					}
					c017 = getCodes17();
					
					
				}
			);
		jQuery("input[name=codes24]").click(
				function(){
					jQuery("input[name=codes24].user").removeAttr("disabled");
					if(jQuery("input[name=codes24]:checked").length>=5){
						
						jQuery("input[name=codes24].user").attr("disabled","disabled");
						jQuery("input[name=codes24].user:checked").removeAttr("disabled");
					}
					c024 = getCodes24();
					
				}
			);
		
		jQuery("a#setc322-325, a#c_325").click(
			function(){
				jQuery("tr#tr322-325").show(200);
				return false;
			}
		);
		jQuery("a#setc326-328, a#c_328").click(
				function(){
					jQuery("tr#tr326-328").show(200);
					return false;
				}
			);
		jQuery("a#setc317-327, a#c_327").click(
				function(){
					jQuery("tr#tr317-327").show(200);
					return false;
				}
			);
		
		jQuery("input#cancelc322-325").click(
			function(){
				f11.update("322","",false,false);
				f11.update("325",0.0,false,false);
				calculator.calcular(f11,false);
				setDatas();
				jQuery("input#vc322").val("");
				jQuery("input#vc325").val(0.0);
				jQuery("tr#tr322-325").hide(200);
			}
		);
		jQuery("input#cancelc326-328").click(
				function(){
					f11.update("326","",false,false);
					f11.update("328",0.0,false,false);
					calculator.calcular(f11,false);
					setDatas();
					jQuery("input#vc326").val("");
					jQuery("input#vc328").val(0.0);
					jQuery("tr#tr326-328").hide(200);
				}
			);
		jQuery("input#cancelc317-327").click(
				function(){
					f11.update("317","",false,false);
					f11.update("327",0.0,false,false);
					calculator.calcular(f11,false);
					setDatas();
					jQuery("input#vc317").val("");
					jQuery("input#vc327").val(0.0);
					jQuery("tr#tr317-327").hide(200);
				}
			);
		jQuery("input#setc322-325").click(
				function(){
					
					if(isNumeric(jQuery("input#vc325").val())){
						if(parseFloat(jQuery("input#vc325").val())>0 && jQuery("input#vc322").val().length>0){
							f11.update("322",jQuery("input#vc322").val(),false,false);
							f11.update("325",parseFloat(jQuery("input#vc325").val()),false,false);
							calculator.calcular(f11,false);
							setDatas();
							jQuery("tr#tr322-325").hide(200);
							return;
						}
						
						
					}
					msg("verifique que los valores sean correctos");
					
				}
			);
		jQuery("input#setc326-328").click(
				function(){
					
					if(isNumeric(jQuery("input#vc328").val())){
						if(parseFloat(jQuery("input#vc328").val())>0 && jQuery("input#vc326").val().length>0){
							f11.update("326",jQuery("input#vc326").val(),false,false);
							f11.update("328",parseFloat(jQuery("input#vc328").val()),false,false);
							calculator.calcular(f11);
							setDatas();
							jQuery("tr#tr326-328").hide(200);
							return;
						}
						
						
					}
					msg("verifique que los valores sean correctos");
					
				}
			);
		jQuery("input#setc317-327").click(
				function(){
					
					if(isNumeric(jQuery("input#vc327").val())){
						if(parseFloat(jQuery("input#vc327").val())>0 && jQuery("input#vc317").val().length>0){
							f11.update("317",jQuery("input#vc317").val(),false,false);
							f11.update("327",parseFloat(jQuery("input#vc327").val()),false,false);
							calculator.calcular(f11);
							setDatas();
							jQuery("tr#tr317-327").hide(200);
							return;
						}
						
						
					}
					msg("verifique que los valores sean correctos");
					
				}
			);
}

var c017="";
var c024="";

function getCodes17(){
	code = "";
	jQuery("input[name=codes17]:checked").each(
		function(index, o){
			var obj	=	jQuery(o);
			code +=obj.val();
		}
	);
	return code;
}

function getCodes24(){
	code = "";
	jQuery("input[name=codes24]:checked").each(
		function(index, o){
			var obj	=	jQuery(o);
			code +=obj.val();
		}
	);
	return code;
}

function cerrar(){
	swalClose();
}
function commitD(data){
	//swalClose();
	if(!isNullOrUndefined(data.status)){
		if(data.status == "error"){
			error(data.error);
			return;
		}		
	}
        
    jQuery(".step").css({"display":"none"});
	jQuery("#step"+5).css({"display":"block"});
        jQuery("div#link_pago").hide();
        if(f11.get("c350").visible){// total a pagar visible?
            jQuery("div#link_pago").show(); 
            //jQuery( "#dialogRS" ).dialog("open");
        }
        
    var PDF_URL= jsContext + "f11/pdf";
    var token = jQuery("meta[name='_csrf']").attr("content");
	var paramName = jQuery("meta[name='_csrf_param_name']").attr("content");
    var action_url = PDF_URL+"?cdeclaracion="+data.cdeclaracion + "&"+paramName+"="+token;
	f   =   jQuery("<form>").attr("id","pdf_forma").attr("action", action_url).attr("method", "POST").attr("target", "pdfreceptorDeclaracion");
	f.appendTo("div#ifrm");
    /*if(jQuery("#frmDec").contents().find("form").length){
        jQuery("#frmDec").contents().find("form:first").attr("action", PDF_URL+"?download=true&cdeclaracion="+data.cdeclaracion);        
    }*/
	//error("<b>Datos presentados.</b><br/>Espere un momento mientras se genera su declaraci&oacute;n en formato PDF...");
    swal("Declaracion presentada. Generando PDF...");
	setTimeout(function(){f.submit(); jQuery("div#step1, div#step2, div#step3, div#step4, div#wizardButtons").remove();}, 1000);
	confirmarAbandono(false);
}

function applyPost(data){
	try{
		if(!isNullOrUndefined(data.status)){
			error(data.error);
			swalClose();
			return;
		}
		//jQuery("#modifyResume").hide();
		f11.setFromJson(data);
		setDatas();
		jQuery("#deductiblesContainer input.inputText, " +
				"#expensesContainer .inputText, " +
				"#industrialContainer input.inputText")
				.removeClass("editMode")
				.addClass("readMode")
				.attr("readonly","readonly");
		jQuery("#toolsIncomes").hide();
		jQuery("#next, #commit")
			.removeAttr("disabled")
			.css({"opacity":hOpacity});
		jQuery("#apply").attr("disabled","disabled").css({"display":"none"});
		jQuery("#modify").removeAttr("disabled").css({"display":"block", "opacity":hOpacity});
		jQuery("a.delete").hide();
		jQuery("html, body").animate({scrollTop:jQuery("a#anclaTop").offset().top}, 500);
	}catch(e){
		error("applyPost : "+e);
	}
	swalClose();	
}

function nextPost(data){
	try{
		if(!isNullOrUndefined(data.status)){
			error(data.error);
			return;
		}
		f11.setFromJson(data);
		setDatas();		
	}catch(e){
		error("nextPost : "+e);
	}
	
}

var lOpacity	=	0.2;
var hOpacity	=	1;

function isREdited(){
	var acum	=	0.0;
	jQuery("input.cf11").each(
		function(index, object){
			var	o	= jQuery(object);
			if(!isNullOrUndefined(o.attr("id"))){
				var code	=	o.attr("id").replace("_","");
				if(!isNullOrUndefined(f11.get(code))){
					if(!isNullOrUndefined(f11.get(code).valor)){
						acum	+=	f11.get(code).valor;
					}
					
				}
			}
		}
	);
	return acum>0;
}
var _stp	=	1;
function wizardStp(stp){
	_stp	= stp;
	jQuery("#wizardButtons input[type=button]")
		.css({"display":"none"})
		.attr("disabled","disabled")
		.css({opacity:lOpacity});
	jQuery("div#incomesR-resume *").remove();
	jQuery("#deductiblesContainer-clon *").remove();
	jQuery("#expensesContainer-clon *").remove();
	jQuery("#industrialContainer-clon *").remove();
	jQuery("#documentForm-clon *").remove();
	jQuery("#donations-clon *").remove();
//	if(isREdited()==true){
		jQuery("#ctrlz").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
//	}
	jQuery("html, body").animate({scrollTop:jQuery("a#anclaTop").offset().top}, 500);
	switch(stp){
		case 1:
			jQuery("#modify").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
			jQuery("#next").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
			
			break;
		case 2:
			jQuery("#back").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
			if(jQuery("#step2 input[name=fill_docs]:checked").length > 0){
				jQuery("#next").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
			}
			
			break;
		case 3:
			jQuery("#back").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
			if(jQuery("#step3 input[name=metodo_pago]:checked").length > 0){
				jQuery("#next").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
			}
			break;
		case 4:
			jQuery("#back").css({"display":"block"}).removeAttr("disabled").css({opacity:hOpacity});
			jQuery("input[name=commit]:checked").removeAttr("checked");
			jQuery("#incomesR-resume-clon").append(jQuery("<h2>Ingresos</h2>"));
			jQuery("#tablesListIncomes div.clonable-table").each(
				function(index, object){
					var o		=	jQuery(object);
					var clon	=	o.clone(false);
					if(clon.find("table.list tbody tr").length == 0) return;
					clon.removeAttr("id");
					clon.find("table.list").removeAttr("id");
					clon.find("table.list td.action *").remove();
					jQuery("div#incomesR-resume").append(clon);
				}
			);
			if(f11.donaciones.length>0){
				var d	=	jQuery("#donationsDetail table.clonable-table").clone(false);
				d.removeAttr("id");
				jQuery("#donations-clon").append(jQuery("<h2>Detalle donaciones</h2>"));
				jQuery("#donations-clon").append(d);
				
			}
			jQuery("#ResumeMessage").hide();
			if(f11.get("c345").visible){// total a devolver visible?
				if(jQuery("input[name=metodo_pago]:checked").length>0){
					if(jQuery("input[name=metodo_pago]:checked").val()=="otro"){
						jQuery("#ResumeMessage").html("<b>Datos bancarios</b><br/>Banco:"+jQuery("select[name=c930] option:checked").text()+"<br/>" +
								"Tipo cuenta:"+jQuery("select[name=c925] option:checked").text()+"<br/>" +
								"N&uacute;mero:"+jQuery("input[name=c920]").val());
						jQuery("#ResumeMessage").show();
					}else{
						jQuery("#ResumeMessage").html("<b>Datos bancarios</b><br/>Dep&oacute;sito en efectivo BANCO AGRICOLA");
						jQuery("#ResumeMessage").show();
					}
				}
				
			}else if(f11.get("c350").visible && f11.v("350")>0){// total a pagar visible?
				jQuery("#ResumeMessage").html("Su Declaraci&oacute;n presenta pago.");
				jQuery("#ResumeMessage").show();
                                
			}
			if(f11.get("c350").valor == 0 && f11.get("c345").valor == 0 && f11.tipoContribuyente == "N"){				
				jQuery("#ResumeMessage").html("Declaraci&oacute;n a CERO.");
				jQuery("#ResumeMessage").show();
			}
			var deductiblesClon	=	jQuery("#deductiblesContainer #deductibles").clone(false);
			deductiblesClon.find("table.list").removeAttr("id");
			deductiblesClon.removeAttr("id");
			deductiblesClon.find("h1").remove();
			deductiblesClon.find("a").remove();
			deductiblesClon.find("table.list tbody td.numeric").each(
				function(index, object){
					var o	=	jQuery(object);
					if(o.find("input.cf11:first").length>0){
						o.html("<p>"+jQuery(o.find("input.cf11:first")).val()+"</p>");
					}
					
				}
			);
			
			jQuery("#deductiblesContainer-clon").append(jQuery("<h2>Deducciones</h2>"));
			jQuery("#deductiblesContainer-clon").append(deductiblesClon);
			if(showColAgro() || showColServ() || showColCom()){
				var expensesClon	=	jQuery("#expensesContainer").clone(false);
				expensesClon.find("table.list").removeAttr("id");
				expensesClon.removeAttr("id");
				expensesClon.find("h1").remove();
				expensesClon.find("table.list tbody td.numeric").each(
					function(index, object){
						var o	=	jQuery(object);
						if(o.find("input.cf11:first").length>0){
							o.html("<p>"+jQuery(o.find("input.cf11:first")).val()+"</p>");
						}
					}
				);
				
				jQuery("#expensesContainer-clon").append(jQuery("<h2>Costos y Gastos</h2>"));
				jQuery("#expensesContainer-clon").append(expensesClon);
			}
			if(jQuery("table#incomesNR tbody tr").length>0){
				
				if(showColIndustrial()){
					var industrialContainerClon	=	jQuery("#industrialContainer").clone(false);
					industrialContainerClon.find("table.list").removeAttr("id");
					industrialContainerClon.removeAttr("id");
					industrialContainerClon.find("h1").remove();
					industrialContainerClon.find("table.list tbody td.numeric").each(
						function(index, object){
							var o	=	jQuery(object);
							if(o.find("input.cf11:first").length>0){
								o.html("<p>"+jQuery(o.find("input.cf11:first")).val()+"</p>");
							}
							
						}
					);
					
					jQuery("#industrialContainer-clon").append(jQuery("<h2>Costo de lo Vendido y Gastos Industriales</h2>"));
					jQuery("#industrialContainer-clon").append(industrialContainerClon);
				}	
			}
			if(jQuery("input[name=fill_docs]:checked").length>0){
				if(jQuery("input[name=fill_docs]:checked").val() == 2){ // si el usuario decide llenar los documentos
					jQuery("#documentDetail .edited").each(
						function(index, object){
							var d	=	jQuery(object).clone(false);
							
							d.find("a.command").remove();
							d.find("tr.removableResume").remove();
							
							d.find("input.cf11").each(
								function(index, object){
									var o	=	jQuery(object);
									if(parseFloat(o.val())==0){
										o.parents("tr:first").remove();
									}else{
										o.replaceWith("<p>"+o.val()+"</p>");
									}
									
								}
							);
							
							d.find("a.cf11").each(
								function(index, object){
									var o	=	jQuery(object);
									if(parseFloat(o.text())==0){
										o.parents("tr:first").remove();
									}else{
										o.replaceWith("<p>"+o.text()+"</p>");	
									}
								}
							);
							var div	=	jQuery("<div>", {"class":"print"});
							div.append(d);
							jQuery("#documentForm-clon").append(div);
							jQuery("#documentForm-clon .clonable").show();
						}
					);
					
				}
			}
			break;
	}
		
}





