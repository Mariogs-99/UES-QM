$(document).ready(function(){
	
	
	
	/*
	 * Oscar Vides
	 * Método para mostrar servicios
	 * 
	 * */
	$("#loader").css("display", "block");
        var jServicios = { };
        var acc = "";
        $.ajax({ 
            url: window.location.protocol + "//" + window.location.host+"/colas/me/getServicios",
            type: 'POST', 
            dataType: 'json', 
            data: JSON.stringify(jServicios),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function(data) { 
                if(isEmptyObject(data)){
                      
                }else{
                   $.each(data, function(idx,obj){
                        acc += "<div class='panel panel-primary'>";   
                        acc += "<div class='panel-heading'>";  
                        acc += "<h4 class='panel-title'>";
                        acc += "<a id='"+obj[0]+"' href='#"+obj[0]+"'>"+obj[9]+"</a>";
                        acc += "</h4>";
                        acc += "</div>";
                        var servid = obj[0];
                        /*acc += "<div id='"+servid+"' class='panel-collapse collapse'>";
                        acc += "<div class='panel-body'>";
                        acc +="<table class='table table-striped table-bordered table-hover table-condensed data-selectable-rows='true'>";
                        $.each(getTramites(servid), function(idtx,objx){
                            acc += "<tr>";
                            acc += "<td id="+servid+"#"+objx.nTramiteId.nTramiteId+">";
                            acc += "<span>"+objx.nTramiteId.sNombre+"</span>";
                            acc += "</td>";
                            acc += "</tr>";
                        });
                        acc += "</table>";
                        acc += "</div>";
                        acc += "</div>";*/
                        acc += "</div>";
                   });
                    $("#accordion").append(acc);
                    $("accordion .panel-title a").removeAttr("class");
                    $("#loader").css("display", "none");
                }

            },
            error:function(data,status,er) { 
                alert("error: "+data+" status: "+status+" er:"+er);
            }
        });
	
        
        
        $("#accordion").on("click",".panel-heading",function(){
            var s = $(this).find("a").prop("id");
            //$(this).find("a").addClass("collapsed collapse");
            $(this).find("h4 a").addClass("collapsed");
            //var item = s.split("#");
            //$("#txtSeccion").val(item[0]);
            $("#txtSeccion").val(s);
            //$("#txtTramite").val(item[1]);
            
            jJerarquia = {seccion : s};
            $.ajax({ 
                url: window.location.protocol + "//" + window.location.host+"/colas/escTiq/getJerarquia",
                type: 'POST', 
                dataType: 'json', 
                data: JSON.stringify(jJerarquia),
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function(data) { 
                    var jerarquia = "";
                    jerarquia += "<select id='cbNivel' name='cbNivel' class='selectpicker' data-style='btn-warning' style='display: none;'' >";
		    jerarquia += "<option value='0'>Seleccion un nivel</option>";
                    $.each(data, function(idx, obj) {
                        jerarquia += "<option value='"+obj.nJerarquiaId.nJerarquiaId+"'>"+obj.nJerarquiaId.sJerarquia+"</option>";
                    });
                    jerarquia += "</select>";
                    $("#divCbNivel").html(jerarquia);
                    $('.selectpicker').selectpicker();
                    $("#divCbNivel .bootstrap-select").css("width","300px");
                    $('select[name=cbNivel]').selectpicker();
                    var sVal = $('select[name=cbNivel] option:first').val();
                    $('select[name=cbNivel]').val(sVal);
                    $('select[name=cbNivel]').selectpicker('refresh');
                },
                error:function(data,status,er) { 
                    alert("error: "+data+" status: "+status+" er:"+er);
                }
            });
            
            var acc="";
            acc +="<table id='tbTra' class='table table-striped table-bordered table-hover table-condensed' data-selectable-rows='true'>";
            acc += "<thead>";
            acc += "<th></th>";
            acc += "<th>Trámite</th>";
            acc += "</thead>";
            acc += "<tbody>";
            $.each(getTramites(s), function(idtx,objx){
                acc += "<tr style='cursor:pointer;'>";
                acc += "<td><input class='ddShow' id='rd"+objx.nTramiteId.nTramiteId+"' type='radio' name='tra' value='"+objx.nTramiteId.nTramiteId+"'></td>";
                acc += "<td id="+s+"#"+objx.nTramiteId.nTramiteId+">";
                acc += "<span>"+objx.nTramiteId.sNombre+"</span>";
                acc += "</td>";
                acc += "</tr>";
            });
            acc += "</tbody>";
            acc += "</table>";
            $("#tableTramites").html(acc);
            $("#btnJerarquia").trigger("click");   
        });
        
        
        $("#tableTramites table tbody tr").click(function(){
            $(this).find("td .ddShow").prop("checked",true);
        });

       
	/*
	 * Oscar Vides
	 * Método para mostrar tramites por servicios
	 * 
	 * */
	
	$("#cbArea").change(function(){
		var json = {"servicioId" : $(this).val() };
		$.ajax({ 
		    url: window.location.protocol + "//" + window.location.host+"/colas/escTiq/tramitesByService",
		    type: 'POST', 
		    dataType: 'json', 
		    data: JSON.stringify(json),
		    contentType: 'application/json',
		    mimeType: 'application/json',
		    success: function(data) { 
		    	var tramites = "<select id='cbNivel' class='selectpicker' data-style='btn-warning' style='display: none;'' >";
		    	tramites += "<option value='0'>Seleccion un nivel</option>";
		    	$.each(data, function(idx, obj) {
		    		tramites += "<option value='"+obj.nTramiteId.nTramiteId+"'>"+obj.nTramiteId.sNombre+"</option>";
				});
		    	tramites += "</select>";
		 
		    	$("#divCbNivel").html(tramites);
		    	$('.selectpicker').selectpicker();
		    	$("#divCbNivel .bootstrap-select").css("width","500px");

		    },
		    error:function(data,status,er) { 
		        alert("error: "+data+" status: "+status+" er:"+er);
		    }
		});	
	});
	
	
	/**
	 * Oscar Vides
	 * Metodo para escalar tiquete
	 */
	
	$("#btnEscalar").click(function(){
		
                var s = $("input[name=tra]:checked").is(":checked");
                var v = 0;
                if(s===true){
                    v = $('input[name=tra]:checked').val();
                }
                $("#txtTramite").val(v);
                if(v===0){
                    mostrarAlertaError("Error","Seleccione un trámite","error");
                }
                else if(parseInt($("#cbNivel").val())===0){
                    mostrarAlertaError("Error","Seleccione un nivel","error");
		}else{
			var json = {"servicioId" : $("#txtSeccion").val(), "tramiteId" : v, jerarquiaId: $("#cbNivel").val() };
			$.ajax({ 
			    url: window.location.protocol + "//" + window.location.host+"/colas/escTiq/insertData",
			    type: 'POST', 
			    dataType: 'json', 
			    data: JSON.stringify(json),
			    contentType: 'application/json',
			    mimeType: 'application/json',
			    success: function(data) { 
			    	if(isEmptyObject(data)){
			    		mostrarAlertaError("Error","No se puede realizar el escalamiento","error");                
					}else{
                                            $('#modalJerarquia').modal('hide');
						//Data ok here
						paraImprimir(data.nTiqueteId);
					}
	
			    },
			    error:function(data,status,er) { 
			        alert("error: "+data+" status: "+status+" er:"+er);
			    }
			});	
			
//			$("#cbArea").val($("#prioridadOne option:first").val());
//			$("#cbNivel").val($("#prioridadOne option:first").val());
		}
		
	});
	

	$("#tableTramites table tbody tr").click(function(){
            $(this).find("input[type='radio']").attr("checked",true);
        });

});


    function getTramites(servid) {
        var result="";
        var jTramites = {idServicios:servid };
        $.ajax({ 
            url: window.location.protocol + "//" + window.location.host+"/colas/me/getTramites",
            type: 'POST', 
            dataType: 'json', 
            data: JSON.stringify(jTramites),
            contentType: 'application/json',
            mimeType: 'application/json',
            async: false,
            success: function(data) {
                if(isEmptyObject(data)){

                }else{
                   result = data;
                }
            },
            error:function(data,status,er) { 
                alert("error: "+data+" status: "+status+" er:"+er);
            }
        });
       return result;
    }