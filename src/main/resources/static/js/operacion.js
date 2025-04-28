$(document).ready(function () {

    /**
     * Función para resetear estados y eventos
     */
    function resetStates() {
        // Cambiar texto de los botones a sus estados iniciales
        $("#iniciar_tramite").html("Iniciar Tramite<br/><i class='fa fa-play fa-3x'></i>");
        $("#detener_tramite").html("Finalizar Tramite<br/><i class='fa fa-stop fa-3x'></i>");
        $("#detener_tramite").css("display", "none");
        $("#iniciar_tramite").css("display", "block");

        // Resetear los botones de la interfaz
        document.getElementById("salir_div").style.cursor = "pointer";
        $("#salir").removeClass("module btn-is-disabled");

        document.getElementById("reasignacion_div").style.cursor = "not-allowed";
        $("#reasignacion").addClass("module btn-is-disabled");

        document.getElementById("escalamiento_div").style.cursor = "not-allowed";
        $("#escalamiento").addClass("module btn-is-disabled");

        document.getElementById("reasignar_div").style.cursor = "not-allowed";
        $("#reasignar_").addClass("module btn-is-disabled");

        document.getElementById("iniciar_tramite_div").style.cursor = "not-allowed";
        $("#iniciar_tramite").addClass("module btn-is-disabled");

        document.getElementById("llamar_siguiente_div").style.cursor = "pointer";
        $("#llamar_siguiente").removeClass("module btn-is-disabled");

        document.getElementById("pausa_div").style.cursor = "pointer";
        $("#pausa").removeClass("module btn-is-disabled");
        $("#detener_pausa").removeClass("module btn-is-disabled");

        $("#infotramite").hide();

        // Resetear el valor de #atendiendo a 0
        $("#atendiendo").val("0");

        // Limpiar timers si están en uso
        clearTimeout(timeout);
        clearTimeout(t);

        // Eliminar cualquier evento de click asociado previamente
        $("#iniciar_tramite").off("click");

        // Reasignar el evento click correctamente
        $("#iniciar_tramite").click(function () {
            iniciarTramite();  // Volver a asociar la función iniciar trámite
        });

        // Eliminar y reasignar el evento de click para el botón de finalizar trámite
        $("#detener_tramite").off("click").click(function () {
            finalizarTramite();  // Asociar la función finalizar trámite
        });
    }

    /**
     * Función para iniciar el trámite
     */
    function iniciarTramite() {
        if ($("#atendiendo").val() == "0") {
            // Cambiar el estado a finalizar trámite
            $("#iniciar_tramite").html("Finalizar Tramite<br/><i class='fa fa-stop fa-3x'>");
            $("#detener_tramite").html("Finalizar Tramite<br/><i class='fa fa-stop fa-3x'></i>");
            $("#detener_tramite").css("display", "block").removeClass("module btn-is-disabled");
            $("#iniciar_tramite").css("display", "none");
            $("#atendiendo").val("1");

            // Habilitar otros botones
            document.getElementById("reasignacion_div").style.cursor = "pointer";
            $("#reasignacion").removeClass("module btn-is-disabled");
            document.getElementById("escalamiento_div").style.cursor = "pointer";
            $("#escalamiento").removeClass("module btn-is-disabled");
            document.getElementById("reasignar_div").style.cursor = "pointer";
            $("#reasignar_").removeClass("module btn-is-disabled");

            // Deshabilitar botones como pausa y salir
            document.getElementById("pausa_div").style.cursor = "not-allowed";
            $("#pausa").addClass("module btn-is-disabled");
            $("#detener_pausa").addClass("module btn-is-disabled");
            document.getElementById("salir_div").style.cursor = "not-allowed";
            $("#salir").addClass("module btn-is-disabled");

            // Enviar la solicitud para iniciar trámite
            var json = { "gcTiqueteId": varGcTiqueteId };
            $.ajax({
                url: window.location.protocol + "//" + window.location.host + "/colas/iniciar_tramite",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function (data) {
                    $("#inicio").text(data.fhiProceso);
                    $("#tiquete").text(data.sCorrelativo);
                    $("#tramite").text(data.nTramiteId);
                },
                error: function (data, status, er) {
                    alert("Error iniciar_tramite: " + data + " status: " + status + " er:" + er);
                }
            });
        }
    }

    /**
     * Función para finalizar el trámite
     */
    function finalizarTramite() {
        // Cambiar el botón a iniciar trámite nuevamente
        $("#iniciar_tramite").html("Iniciar Tramite<br/><i class='fa fa-play fa-3x'>");
        $("#detener_tramite").css("display", "none");
        $("#iniciar_tramite").css("display", "block");
        $("#atendiendo").val("0");

        // Resetear los botones y otros estados
        resetStates();

        // Enviar la solicitud para finalizar trámite
        var json = { "gcTiqueteId": varGcTiqueteId };
        $.ajax({
            url: window.location.protocol + "//" + window.location.host + "/colas/finalizar_tramite",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(json),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                console.log("Trámite finalizado con éxito");
            },
            error: function (data, status, er) {
                alert("Error finalizar_tramite: " + data + " status: " + status + " er:" + er);
            }
        });
        varGcTiqueteId = 0;
    }

    /**
     * Función para manejar escalamiento
     */
    $("#ConfirmacionEscalar").click(function () {
        resetStates();

        var json = { "gcTiqueteId": varGcTiqueteId };
        $.ajax({
            url: window.location.protocol + "//" + window.location.host + "/colas/escalamiento",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(json),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                console.log("Escalamiento exitoso");
                // Cambiar el estado del botón "Detener Tramite" a "Iniciar Tramite" después de escalamiento
                $("#detener_tramite").css("display", "none");
                $("#iniciar_tramite").css("display", "block").html("Iniciar Tramite<br/><i class='fa fa-play fa-3x'></i>");
            },
            error: function (data, status, er) {
                alert("Error escalar: " + data + " status: " + status + " er:" + er);
            }
        });
        varGcTiqueteId = 0;
    });

    /**
     * Función para manejar reasignación
     */
    $("#ConfirmacionReasginar").click(function () {
        resetStates();

        var json = { "gcTiqueteId": varGcTiqueteId };
        $.ajax({
            url: window.location.protocol + "//" + window.location.host + "/colas/reasignar",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(json),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                console.log("Reasignación exitosa");
                // Cambiar el estado del botón "Detener Tramite" a "Iniciar Tramite" después de reasignación
                $("#detener_tramite").css("display", "none");
                $("#iniciar_tramite").css("display", "block").html("Iniciar Tramite<br/><i class='fa fa-play fa-3x'></i>");
            },
            error: function (data, status, er) {
                alert("Error reasignar: " + data + " status: " + status + " er:" + er);
            }
        });
        varGcTiqueteId = 0;
    });

    function llamardeNuevo() {
        if ($("#llamando").val() != 0) {
         
            var json = { "gcTiqueteId": varGcTiqueteId };
            $.ajax({
                url: window.location.protocol + "//" + window.location.host + "/colas/llamarDeNuevo",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function (data) {
                    
                    let respuesta = parseInt(data);

                    if (respuesta == 1) {
                        $("#llamando").val(parseInt($("#llamando").val()) + 1);
                    } 
                    if (parseInt($("#llamando").val()) < parseInt(varNllamados)) {
                        timeout = setTimeout(llamardeNuevo, varTimer1);
                    }
                
                },
                error: function (data, status, er) {
                    alert("error in function llamardeNuevo(): "
                        + data
                        + " status: "
                        + status + " er:" + er);
                }
            });
        }
    }

    /**
     * Función para llamar al siguiente tiquete en la cola 
     */
    function siguiente() {
        if ($("#llamando").val() != 0) {
            var json = { "gcTiqueteId": varGcTiqueteId };
            $.ajax({
                url: window.location.protocol + "//" + window.location.host + "/colas/siguiente",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function (data) {
                    // Si el backend detecta que el mismo usuario ya atendió este tiquete
                    console.log(data);
                    if (data.error) {
                        console.log("Error: " + data.error);
                        alert("Este tiquete no puede ser atendido nuevamente por el mismo usuario. Pasando al siguiente...");
                        siguiente();  // Llamar de nuevo a 'siguiente()' para obtener el próximo tiquete
                        return;
                    }

                    // Si no hay más clientes en la cola
                    if (data.turno == "No hay clientes en la cola") {
                        $("#turno").text(data.turno);
                        resetStates();
                        return;
                    }

                    // Si hay un tiquete válido en la cola
                    $("#turno").text(data.turno);
                    varGcTiqueteId = data.gcTiqueteId;
                    varSCorrelativo = data.sCorrelativo;

                    // Actualizar la interfaz de usuario
                    document.getElementById("iniciar_tramite_div").style.cursor = "pointer";
                    $("#iniciar_tramite").removeClass("module btn-is-disabled");
                    $("#detener_tramite").removeClass("module btn-is-disabled");
                    document.getElementById("llamar_siguiente_div").style.cursor = "not-allowed";
                    $("#llamar_siguiente").addClass("module btn-is-disabled");
                    document.getElementById("pausa_div").style.cursor = "pointer";
                    $("#pausa").removeClass("module btn-is-disabled");
                    $("#detener_pausa").removeClass("module btn-is-disabled");

                    setTimeout(llamardeNuevo, varTimer1);
                },
                error: function (data, status, er) {
                    alert("Error en la función siguiente(): " + data + " status: " + status + " er:" + er);
                }
            });
        }
    }

    /**
     * Evento click para llamar al siguiente tiquete en la cola
     */
    $("#llamar_siguiente").click(function () {
        $("#llamando").val("1");
        document.getElementById("llamar_siguiente_div").style.cursor = "not-allowed";
        $("#llamar_siguiente").addClass("module btn-is-disabled");
        siguiente();
    });

    // Iniciar el botón de trámite correctamente
    resetStates();
});


jQuery(document).ready(function () {

    /**
     * funcion para obtener los servicion configurados para la unidad receptora. 
     * Florenti Lazo 
     * infologic
     * **/
    $("#loader").css("display", "block");
    var jServicios = {};
    var acc = "";
    $.ajax({
        url: window.location.protocol + "//" + window.location.host + "/colas/me/getServicios",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(jServicios),
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (data) {
            if (isEmptyObject(data)) {

            } else {
                $.each(data, function (idx, obj) {
                    acc += "<div class='panel panel-primary'>";
                    acc += "<div class='panel-heading'>";
                    acc += "<h4 class='panel-title'>";
                    acc += "<a id='" + obj[0] + "' href='#" + obj[0] + "'>" + obj[9] + "Tramites Disponibles</a>";
                    acc += "</h4>";
                    acc += "</div>";
                    acc += "</div>";
                });
                $("#accordion").append(acc);
                $("accordion .panel-title a").removeAttr("class");
                $("#loader").css("display", "none");
            }
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
    $("#accordion").on("click", ".panel-heading", function () {
        var s = $(this).find("a").prop("id");
        $(this).find("h4 a").addClass("collapsed");
        $("#txtSeccion").val(s);
        jJerarquia = { seccion: s };
        var acc = "";
        acc += "<table id='tbTra' class='table table-striped table-bordered table-hover table-condensed' data-selectable-rows='true'>";
        acc += "<thead>";
        acc += "<th></th>";
        acc += "<th>Trámite</th>";
        acc += "</thead>";
        acc += "<tbody>";
        $.each(getTramites(s), function (idtx, objx) {
            acc += "<tr style='cursor:pointer;'>";
            acc += "<td><input id='rd" + objx.nTramiteId.nTramiteId + "' type='radio' name='tra' value='" + objx.nTramiteId.nTramiteId + "'></td>";
            acc += "<td id=" + s + "#" + objx.nTramiteId.nTramiteId + ">";
            acc += "<span>" + objx.nTramiteId.sNombre + "</span>";
            acc += "</td>";
            acc += "</tr>";
        });
        acc += "</tbody>";
        acc += "</table>";
        $("#tableTramites").html(acc);
        $("#btnJerarquia").trigger("click");
    });
    $.get(window.location.protocol + "//" + window.location.host + "/colas/getPreguntas",
        function (data) {
            preguntas = data;
        }, "json");
    $("#infotramite").hide();
    $("#ConfirmacionReasignar").click(function () {
        varGcTiqueteIdReasig = varGcTiqueteId;
        $("#iniciar_tramite").trigger("click");
        document.getElementById("txt2").innerHTML = varTimetoStop;
        $("#tiempo_fin_tramite").show();
    });
    $("#reasignar").click(function () {
        // Alcides Nolasco tableTramitest no existe 
        //var tramites = JSON.parse(JSON.stringify($('#tableTramitest').bootstrapTable('getSelections')));
        var s = $("input[name=tra]:checked").is(":checked");
        if (s === false) {
            $('#reasignacion_elegiruno').trigger("click");
        } else {
            var nTramiteId = $('input[name=tra]:checked').val();
            var prioridadOne = $('#prioridadTwo').val();
            if (prioridadOne === null || prioridadOne === undefined) {
                prioridadOne = $('#prioridadTwo').val();
            }
            var holgura = $('#tholguraOne').prop('checked');
            var json = {
                "tramite": nTramiteId, "prioridad": prioridadOne, "holgura": holgura, "opt": 0,
                "idTiquete": varGcTiqueteIdReasig
            };
            console.log("JSON: ", json);
            $.ajax({
                url: window.location.protocol + "//" + window.location.host + "/colas/me/insertData",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function (data) {
                    $("#modal-tramites-cerrar").trigger("click");
                    $("#cerrarModalReasignacion").trigger("click");
                    $('#reasignacion_exitosa').trigger("click");
                },
                error: function (data, status, er) {
                    alert("error: " + data + " status: " + status + " er:" + er);
                    $("#modal-tramites-cerrar").trigger("click");
                    $("#cerrarModalReasignacion").trigger("click");
                    $('#reasignacion_exitosa').trigger("click");
                }
            });
            varGcTiqueteIdReasig = varGcTiqueteId;
            varGcTiqueteId = 0;
            varSCorrelativo = 0;
            $("#tiempo_fin_tramite").hide();
        }
    });

    $("#escalar").click(function () {
        var tramites = JSON.parse(JSON.stringify($('#table-Escalamiento').bootstrapTable('getSelections')));
        if (tramites.length == 0) {
            $('#modalEscalamientoSeleccioneUno').modal('show');
        } else {
            var json = { "jerarquiaId": tramites[0].nTramiteId, "idTiquete": varGcTiqueteIdReasig };
            $.ajax({
                url: window.location.protocol + "//" + window.location.host + "/colas/operacion/realizarEscalamiento",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function (data) {
                    $('#modalEscalamientoExitoso').modal('show');
                    $('#modalEscalamiento').modal('hide');
                },
                error: function (data, status, er) {
                    alert("error: " + data + " status: " + status + " er:" + er);
                }
            });
            $('#table-Escalamiento').bootstrapTable('uncheckAll');
            varGcTiqueteId = 0;
            varSCorrelativo = 0;
            $("#tiempo_fin_tramite").hide();
        }
    });


    /**
     * Metodo para que los tecnicos tomen una pausa 
     * Florentin Lazo 
     * Infologic
     * 
     **/
    $("#tomar_pausa").click(function () {
        estadoPausa();
        var json = { "nEventoId": 1 };
        nEventoId = 1;
        $.ajax({
            url: window.location.protocol + "//" + window.location.host + "/colas/pausa",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(json),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                document.getElementById("pausa").innerHTML = "Finalizar Pausa o Receso<br/><i class='fa fa-bell-slash-o fa-4x'></i>";
                document.getElementById("detener_pausa").innerHTML = "Finalizar Pausa o Receso<br/><i class='fa fa-bell-slash-o fa-4x'></i>";
            },
            error: function (data, status, er) {
                alert("error: " + data + " status: " + status + " er:" + er);
            }
        });
    });
    /**
     * Metodo para que los tecnicos tomen un receso 
     * Florentin Lazo 
     * Infologic
     * 
     **/
    $("#tomar_receso").click(function () {
        estadoPausa();
        var json = { "nEventoId": 2 };
        nEventoId = 2;
        $.ajax({
            url: window.location.protocol + "//" + window.location.host + "/colas/pausa",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(json),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                document.getElementById("pausa").innerHTML = "Finalizar Pausa o Receso<br/><i class='fa fa-bell-slash-o fa-4x'></i>";
                document.getElementById("detener_pausa").innerHTML = "Finalizar Pausa o Receso<br/><i class='fa fa-bell-slash-o fa-4x'></i>";
            },
            error: function (data, status, er) {
                alert("error: " + data + " status: " + status + " er:" + er);
            }
        });
    });
    /**
     * Metodo para que los tecnicos tomen tiempo de almuerzo 
     * Florentin Lazo 
     * Infologic
     * 
     **/
    $("#tomar_almuerzo").click(function () {
        estadoPausa();
        var json = { "nEventoId": 3 };
        nEventoId = 3;
        $.ajax({
            url: window.location.protocol + "//" + window.location.host + "/colas/pausa",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(json),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                document.getElementById("pausa").innerHTML = "Finalizar Pausa o Receso<br/><i class='fa fa-bell-slash-o fa-4x'></i>";
                document.getElementById("detener_pausa").innerHTML = "Finalizar Pausa o Receso<br/><i class='fa fa-bell-slash-o fa-4x'></i>";
            },
            error: function (data, status, er) {
                alert("error: " + data + " status: " + status + " er:" + er);
            }
        });
    });
    /**
     * Metodo para cambiar texto de boton tomar pausa o finalizar pausa.
     * Florentin Lazo 
     * Infoligic
     * 
     * **/
    $("#pausa").click(function () {
        if (parseInt($("#pausado").val()) === 1) {
            document.getElementById("pausa").innerHTML = "Tomar Pausa o Receso<br/><i class='fa fa-bell-o fa-4x'></i>";
            $("#pausa").css("display", "block");
            document.getElementById("detener_pausa").innerHTML = "Tomar Pausa o Receso<br/><i class='fa fa-bell-o fa-4x'></i>";
            $("#detener_pausa").css("display", "none");
            document.getElementById("llamar_siguiente_div").style.cursor = "pointer";
            $("#llamar_siguiente").removeClass("module btn-is-disabled");
            document.getElementById("salir_div").style.cursor = "pointer";
            $("#salir").removeClass("module btn-is-disabled");
            $("#pausado").val(0);
            var json = { "nEventoId": 1 };
            $.ajax({
                url: window.location.protocol + "//" + window.location.host + "/colas/finPausa",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function (data) {
                    switch (nEventoId) {
                        case 1:
                            $('#Modal_confirmacion_fin_pausa').modal('show');
                            break;
                        case 2:
                            $('#Modal_confirmacion_fin_pausa').modal('show');
                            break;
                        case 3:
                            $('#Modal_confirmacion_fin_almuerzo').modal('show');
                            break;
                        default:
                            $('#Modal_confirmacion_fin_pausa').modal('show');
                            break;
                    }
                },
                error: function (data, status, er) {
                    alert("error: " + data + " status: " + status + " er:" + er);
                }
            });
        } else {
            $("#pausa").attr("data-target", "#modalPausa");
            $("#detener_pausa").attr("data-target", "#modalPausa");
        }
    });
    $("#detener_pausa").click(function () {
        if (parseInt($("#pausado").val()) === 1) {
            document.getElementById("pausa").innerHTML = "Tomar Pausa o Receso<br/><i class='fa fa-bell-o fa-4x'></i>";
            $("#pausa").css("display", "block");
            $("#detener_pausa").css("display", "none");
            $("#pausado").val(0);

            // Habilitar botones
            $("#llamar_siguiente").removeClass("module btn-is-disabled");
            document.getElementById("llamar_siguiente_div").style.cursor = "pointer";
            $("#salir").removeClass("module btn-is-disabled");
            document.getElementById("salir_div").style.cursor = "pointer";
        }
    });
    /**
     * funcion ajustar botones al estado pausa
     * Florenti Lazo 
     * infologic
     * **/
    function estadoPausa() {
        $("#pausado").val(1);
        document.getElementById("pausa").innerHTML = "Finalizar Pausa o Receso";
        $("#pausa").removeAttr("data-target");
        $("#pausa").css("display", "none");
        document.getElementById("detener_pausa").innerHTML = "Finalizar Pausa o Receso";
        $("#detener_pausa").removeAttr("data-target");
        $("#detener_pausa").css("display", "block");
        document.getElementById("detener_pausa").style.cursor = "pointer";  // Asegura que el cursor sea pointer
        $("#detener_pausa").removeClass("btn-is-disabled");  // Asegura que no tenga clases deshabilitantes

        document.getElementById("llamar_siguiente_div").style.cursor = "not-allowed";
        $("#llamar_siguiente").addClass("module btn-is-disabled");
        document.getElementById("salir_div").style.cursor = "not-allowed";
        $("#salir").addClass("module btn-is-disabled");
    }

    /**
     * Metodo para mostrar las preguntas de evaluacion.
     * Florentin Lazo 
     * Infoligic
     * 
     * **/
    $("#evaluacion").click(function () {
        if (enEvaluacion == 0) {
            enEvaluacion = 1;
            pSiguiente();
        }
    });
    /**
     * funcion para moatrar la siguiente pregunta de evaluacion.
     * Florenti Lazo 
     * infologic
     * **/

    function pSiguiente() {
        if (pIterador < preguntas.length) {
            var tema = "";
            var obj = preguntas[pIterador];
            $("#Pregunta").text(obj.dPregunta);
            tema = "";
            tema = "<div id='" + pIterador + "'>";
            $.each(obj.gcRespuestasList, function (idxt, objt) {
                var numero = idxt + 1;
                tema += "<div class='well text-center font2'>" + numero + ". " + objt.sRespuesta + "</div>";
            });
            $("#respuestas").empty();
            $("#respuestas").append(tema);
            tema += "</div>";
            pIterador = parseInt(pIterador) + 1;
        } else {
            $("#closeButon").trigger("click");
            if (pIterador == 1) {
                respuestas += "]}";
            }
            pIterador = 0;
            enEvaluacion = 0;
            var json = JSON.parse(respuestas);
            $.ajax({
                url: window.location.protocol + "//" + window.location.host + "/colas/setRespuestas",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function (data) {
                },
                error: function (data, status, er) {
                    alert("error: " + data + " status: " + status + " er:" + er);
                }
            });
            $("#iniciar_tramite").trigger("click");
            varSCorrelativo = 0;
        }
    }

    /**
     * funcion para capturar las respuestas de las preguntas de evaluacion
     * Florenti Lazo 
     * infologic
     * **/
    $(document).keypress(function (event) {
        var obj = preguntas[pIterador - 1];
        if (enEvaluacion == 1) {
            var largo = obj.gcRespuestasList.length;
            if (parseInt(event.keyCode) > 48 && parseInt(event.keyCode) < 59) {
                if (parseInt(event.keyCode) - 48 <= largo) {
                    respuesta(obj.gcRespuestasList[((event.keyCode) - 49)].nRespuestaId);
                    pSiguiente();
                }
            }
        }
    });
    /**
     * funcion para crear json para enviar a guardarlas respuestas de las preguntas de evaluacion
     * Florenti Lazo 
     * infologic
     * **/
    function respuesta(idRespuesta) {

        if (pIterador == 1) {
            respuestas = '{ "gcRespuestasList": [ { "nRespuestaId":' + idRespuesta + '}';
        } else {
            if (pIterador < preguntas.length) {
                respuestas += ',{ "nRespuestaId":' + idRespuesta + '}';
            } else {
                respuestas += ',{ "nRespuestaId":' + idRespuesta + ' } ]}';
            }
            location.reload();
        }
    }


    /**
     * funcion para mostrar elerta de errores. 
     * Florenti Lazo 
     * infologic
     * **/
    function mostrarAlertaError(title, content, type) {

        $.msgBox({
            title: title,
            content: content,
            type: type,
            buttons: [{ value: "Cerrar" }],
            opacity: 0.5,
            autoClose: false
        });
    }
    ;
    loadDataTableReasignacion();
    $("#tiempo_fin_tramite").hide();
    if (usrConfigurado == "false") {
        document.getElementById("llamar_siguiente_div").style.cursor = "not-allowed";
        $("#llamar_siguiente").addClass(" module btn-is-disabled");
        document.getElementById("salir_div").style.cursor = "not-allowed";
        $("#salir").addClass(" module btn-is-disabled");
        document.getElementById("pausa_div").style.cursor = "not-allowed";
        $("#pausa").addClass(" module btn-is-disabled");
        $("#detener_pausa").addClass(" module btn-is-disabled");
        mostrarAlertaError("Error", "Usted no tiene un escritorio asignado en el sistema, para solucionar su problema favor ponerse en contacto con el administrador", "error");
    }

    if (varEnPausa == "true") {
        estadoPausa();
    }

    $(".msgButton").click(function () {
        //window.location.href = $('#cerrarError').attr('href');
        window.location.href = '/colas';
    });
});
function isEmptyObject(obj) {
    return JSON.stringify(obj) === '{}';
}


/**
 * Florentin Lazo 
 * Function para cargar datos de los tramites disponibles para reasignacion
 * 
 */

function loadDataTableReasignacion() {

    $(function () {

        $('#table-javascript').bootstrapTable({
            method: 'get',
            url: window.location.protocol + "//" + window.location.host + '/colas/operacion/getTramites',
            cache: false,
            height: 400,
            striped: true,
            pagination: false,
            pageSize: 20,
            pageList: [20, 50, 100, 250, 500],
            search: true,
            showColumns: false,
            showRefresh: false,
            minimumCountColumns: 1,
            clickToSelect: true,
            columns: [{
                field: 'state',
                radio: true
            }, {
                field: 'sNombre',
                align: 'center',
                valign: 'middle',
                sortable: false
            }]
        });
        $(".fixed-table-header:visible").hide();
        var count = 0;
        $("#table-javascript thead tr").each(function () {
            count = parseInt(count) + 1;
            if (parseInt(count) === 1) {
                $(this).css("display", "none");
            }
        });
        $("div .fixed-table-header").css("height", "5px");
        $("#modalDetalle .modal-dialog").css("width", "400px");
        $(".search input[type=text]").attr("id", "txtBuscar");
        $(".search input[type=text]").attr("class", "form-control keyboard-normal");
        $("table tbody").attr("class", "searchable");
        $(".fixed-table-container").css("height", "200px");
        $(".fixed-table-container").css("padding-bottom", "10px");
    });
}

/**
 * Florentin Lazo
 * Function para cargar datos de las posibles Escalamientos para el centro de servicio  
 */

function loadDataTableEscalamiento() {
    $(function () {
        $('#table-Escalamiento').bootstrapTable({
            method: 'get',
            url: window.location.protocol + "//" + window.location.host + '/colas/operacion/getEscalamiento/' + varGcTiqueteIdReasig,
            cache: false,
            height: 400,
            striped: true,
            pagination: false,
            pageSize: 20,
            pageList: [20, 50, 100, 250, 500],
            search: true,
            showColumns: false,
            showRefresh: false,
            minimumCountColumns: 1,
            clickToSelect: true,
            columns: [{
                field: 'state',
                radio: true
            }, {
                field: 'sNombre',
                align: 'center',
                valign: 'middle',
                sortable: false
            }]
        });
        $(".fixed-table-header:visible").hide();
        var count = 0;
        $("#table-Escalamiento thead tr").each(function () {
            count = parseInt(count) + 1;
            if (parseInt(count) === 1) {
                $(this).css("display", "none");
            }
        });
        $("div .fixed-table-toolbar").css("padding-bottom", "5px");
        $("#modalDetalle .modal-dialog").css("width", "515px");
        $(".search input[type=text]").attr("id", "txtBuscar");
        $(".search input[type=text]").attr("class", "form-control keyboard-normal");
        $("table tbody").attr("class", "searchable");
    });
}

/**
 * Oscar Vides
 * Eventos de tabla
 * 
 */

//window.operateEvents = {'click .detail': function (e, value, row, index) {
//    	$("#idReservaCita").val(row.idReservaCita);
//    	$("#idTramite").val(row.idTramite);
//    	$("#idTiquete").val(row.idTiquete);
//    	$("#lblCodigoConf").text(row.codigoVerificacion);
//    	$("#lblAreaServicio").text(row.areaServicio);
//    	$("#lblTramite").text(row.tramite);
//    	$("#lblCentroAtencion").text(row.unidad);
//    	$("#lblFecha").text(row.fecha);
//    	$("#lblHora").text(row.hora);
//    	$("#lblNIT").text(row.nit);
//    	$("#lblCorreo").text(row.correo);
//    	$("#lblTelefono").text(row.telefono);
//    	
//    },
//    
//    'click .print': function (e, value, row, index) {
//    	$("#idReservaCita").val(row.idReservaCita);
//    	$("#idTramite").val(row.idTramite);
//    	$("#idTiquete").val(row.idTiquete);
//    	$("#lblFecha").text(row.fecha);
//    	enableReservaCita();
//    }
//};
//
//$(function () {
//    $('#table-methods').next().click(function () {
//        $(this).hide();
//        var id = 0,
//            getRows = function () {
//                var rows = [];
//                for (var i = 0; i < 10; i++) {
//                    rows.push({
//                        id: id,
//                        name: 'test' + id,
//                        price: '$' + id
//                    });
//                    id++;
//                }
//                return rows;
//            },
//            $table = $('#tableTramites').bootstrapTable({
//                data: getRows()
//            });
//
//        $('#column-reasignar').click(function () {
//            alert('Selected values: ' + JSON.stringify($('#tableTramites').bootstrapTable('getSelections')));
//        });                                 
//        $('#load-data, #append-data, #check-all, #uncheck-all, ' + '#show-loading, #hide-loading').click(function () {
//            $table.bootstrapTable($(this).data('method'), getRows());
//        });
//        $('#show-, #hide-column').click(function () {
//            $table.bootstrapTable($(this).data('method'), 'id');
//        });
//    });
//});


function getTramites(servid) {
    var result = "";
    var jTramites = { idServicios: servid };
    $.ajax({
        url: window.location.protocol + "//" + window.location.host + "/colas/me/getTramites",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(jTramites),
        contentType: 'application/json',
        mimeType: 'application/json',
        async: false,
        success: function (data) {
            if (isEmptyObject(data)) {

            } else {
                result = data;
            }
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
    return result;
}