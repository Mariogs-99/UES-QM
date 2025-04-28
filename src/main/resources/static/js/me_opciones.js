
$(document).ready(function () {

    var idTiqueteSessionLoad = $.session.get('idTiqueteSession') === undefined ? 0 : $.session.get('idTiqueteSession');

    if (idTiqueteSessionLoad !== 0) {
        $('#cosId').prop('disabled', true);
        $('#reaId').prop('disabled', true);
        $('#escId').prop('disabled', true);
        $('#reimId').prop('disabled', true);

        $('#cosId').css('cursor', 'not-allowed');
        $('#reaId').css('cursor', 'not-allowed');
        $('#escId').css('cursor', 'not-allowed');
        $('#reimId').css('cursor', 'not-allowed');

    }



    /*
     * Oscar Vides
     * Método para mostrar trámites agrupados por servicios
     * 
     * */
    //$("#loader").css("display", "block");
    // $("#loader").show();
    var jServicios = {};
    var tema = "";
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
                var mcount = 0;
                $.each(data, function (idx, obj) {
                    if (parseInt(obj[0]) !== parseInt(mcount)) {

                        tema += "<div class='panel panel-i' style='justify-content: center'>";

                        tema += "<button id='" + obj[0] + "' type='button' class='btn btn-custom-primary'><span class='glyphicon glyphicon-ok-circle'></span>" + obj[9] + "</button>";
                        tema += "</div>";

                        var servid = obj[0];
                        var tdcount = 0;
                        tema += "<div class='container' style='display:none;' id ='sub" + obj[0] + "'>";
                        tema += "<table class='tablame' style= 'margin-left: auto; margin-right: auto; border-collapse:separate; border-spacing:10px 10px'>";
                        tema += "<tr>";
                        tema += "<td colspan='2'><div style='justify-content: right>";
                        tema += "<a href='#' id='back' class='link-with-icon'><i class='fa fa-reply fa-3x' aria-hidden='true'></i></a>";
                        tema += "</div></td></tr>";
                        var i = 0;
                        $.each(getTramites(servid), function (idxt, objt) {
                            if (tdcount === 0) {
                                tema += "<tr>";
                            }


                            if (parseInt(tdcount) < 2) {
                                if (parseInt(servid) === parseInt(objt.nTramiteId.nServiciosId.nServiciosId)) {
                                    parseInt(objt.nTramiteId.bNitRequerido) === 0 ?
                                            tema += "<td class='tbm'><button id='" + objt.nTramiteId.nTramiteId + "' type='button' class='btn btn-custom-secondary' value='" + objt.nTramiteId.dTramite + "' data-backdrop='static' data-keyboard='false' data-toggle='modal' data-target='#modalPrioridadSinNit' ><span class='glyphicon glyphicon-ok-circle'></span>" + objt.nTramiteId.sNombre + "</button></td>" :
                                            tema += "<td class='tbm'><button id='" + objt.nTramiteId.nTramiteId + "' type='button' class='btn btn-custom-secondary' value='" + objt.nTramiteId.dTramite + "' data-backdrop='static' data-keyboard='false' data-toggle='modal' data-target='#modalPrioridadConNit'><span class='glyphicon glyphicon-ok-circle'></span>" + objt.nTramiteId.sNombre + "</button></td>";
                                }
                                tdcount = parseInt(tdcount) + 1;
                            } else {
                                tema += "</tr><tr>";
                                if (parseInt(servid) === parseInt(objt.nTramiteId.nServiciosId.nServiciosId)) {
                                    parseInt(objt.nTramiteId.bNitRequerido) === 0 ?
                                            tema += "<td class='tbm'><button id='" + objt.nTramiteId.nTramiteId + "' type='button' class='btn btn-custom-secondary' value='" + objt.nTramiteId.dTramite + "' data-backdrop='static' data-keyboard='false' data-toggle='modal' data-target='#modalPrioridadSinNit' ><span class='glyphicon glyphicon-ok-circle'></span>" + objt.nTramiteId.sNombre + "</button></td>" :
                                            tema += "<td class='tbm'><button id='" + objt.nTramiteId.nTramiteId + "' type='button' class='btn btn-custom-secondary' value='" + objt.nTramiteId.dTramite + "' data-backdrop='static' data-keyboard='false' data-toggle='modal' data-target='#modalPrioridadConNit' ><span class='glyphicon glyphicon-ok-circle'></span>" + objt.nTramiteId.sNombre + "</button></td>";
                                    tdcount = 1;
                                }
                            }

                            i++;
                        });

                        tema += "</tr>";
                        tema += "</table>";
                        tema += "</div>";


                        //tema += "</div>";
                        mcount = obj[0];
                    }
                });
                /* Alcides Nolasco
                 * Se agrega boton statico para confirmar cita
                 */
                tema += "<div class='panel panel-i' style='justify-content: center'>";
                tema += "<button id='opcionCita' type='button' class='btn btn-custom-primary' data-toggle='modal' data-target='#modalparaCita'><span class='glyphicon glyphicon-ok-circle'></span>Tramite con Cita</button>";
                tema += "</div>";
                console.log("CONTENIDO HTML: ", tema);
                $("#content").append(tema);


            }
            /* Alcides Nolasco 
             * Metodo para ocultar menu principal y desplegar menus secundario al terminar de cargar el contenido dinamico
             */
            $('#content').on('click', '.btn.btn-custom-primary', function () {
                var myid = this.id;
                $('#content').find(".panel.panel-i").hide();
                $('#content').find("#sub" + myid).show();
            });

            // event handler fo button back 
            $('#content').on('click', '#back', function () {
                $('#content').find(".panel.panel-i").show();
                $('#content').find("[id*=sub]").hide();
            });

        },
        error: function (data, status, er) {
            alert("Error");
        }
    });


    /* Oscar Vides
     * Método para mostrar listado de prioridades
     *  
     *  */

    $.get(window.location.protocol + "//" + window.location.host + "/colas/me/getPrioridades",
            function (data) {

                $.each(data, function (idx, obj) {
                    $("#prioridadOne").append("<option value='" + obj.nPrioridadId + "'>" + obj.sNombre + "</option>");
                    $("#prioridadTwo").append("<option value='" + obj.nPrioridadId + "'>" + obj.sNombre + "</option>");
                });
                $('.selectpicker').selectpicker();
            }, "json");

    var idPrioridad = $.session.get('idPrioridad') === undefined ? 0 : $.session.get('idPrioridad');
    if (idPrioridad !== 0) {
        $("#prioridadOne").prop("disabled", true);
        $("#prioridadTwo").prop("disabled", true);
        $('.selectpicker').selectpicker('refresh');
    }



    /* Oscar Vides
     * Método para seleccionar trámite, además limpia campos
     *  
     *  */
    // Manejador de evento para ocultar omitir NIT
    $(document).on('click', '.btn-custom-secondary', function () {
        let buttonId = $(this).attr('id'); // Obtiene el id del botón que se ha clicado
        if (buttonId !== "51") {
            $('#omitirNIT-div').hide(); // Oculta el div si el id del botón no es 51
        }
    });



    $('#content').on('click', '.btn.btn-custom-secondary', function () {

        var idPrioridad = $.session.get('idPrioridad') === undefined ? 0 : $.session.get('idPrioridad');
        var nitSession = $.session.get('nitSession') === undefined ? "" : $.session.get('nitSession');

        $("#idTramite").val($(this).attr("id"));
        $(".modal-title").html($(this).val());

        //Limpiar campos de formularios -> modal 1

        $('select[name=prioridadOne]').selectpicker();
        var sVal = $('select[name=prioridadOne] option:first').val();
        $('select[name=prioridadOne]').val(idPrioridad === 0 ? sVal : idPrioridad);
        $('select[name=prioridadOne]').selectpicker('refresh');

        $("#modalPrioridadSinNit .toggle").prop("class", "toggle btn btn-default off");
        $("#tholguraOne").prop("checked", false);

        //Limpiar campos de formularios -> modal 2
        $("#omitirNIT").prop("checked", false);
        // Ocultar el div si el ID del botón clickeado no es 51
        if ($(this).attr("id") !== "51") {
            $("#omitirNIT-div").hide(); // Ocultar el div
        } else {
            $("#omitirNIT-div").show(); // Mostrar el div
        }

        if ($("#omitirNIT").is(":checked")) {
            $("#txtNIT").prop("disabled", "disabled");
            $("#btnValidarNIT").prop("class", "btn btn-danger disabled");
            $("#btnPrintTwo").prop("class", "btn btn-danger");
        }

        if (nitSession === "") {
            $("#txtNIT").val('');
        } else {
            $("#txtNIT").val(nitSession);


            var json = {"nit": $("#txtNIT").val()};

            $.ajax({
                url: window.location.protocol + "//" + window.location.host + "/colas/me/validarID",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function (data) {

                    if (isEmptyObject(data)) {
                        $("#lblNitError").text($("#txtNIT").val());
                        $(".infoError").removeAttr("style");
                        $(".informationBar").css("display", "none");
                        $(".infoNombre").css("display", "none");
                        $(".infoNIT").css("display", "none");

                        $("#lblNombre").text(' ');
                        $("#lblNIT").text(' ');
                        $("#btnPrintTwo").prop("class", "btn btn-danger disabled");
                        $("#txtNIT").val(' ');
                    } else {
                        $("#lblNombre").text(data.SNombres);
                        $("#lblNIT").text(data.nit);
                        $("#btnPrintTwo").prop("class", "btn btn-danger");

                        $(".infoError").css("display", "none");
                        $(".informationBar").removeAttr("style");
                        $(".infoNombre").removeAttr("style");
                        $(".infoNIT").removeAttr("style");
                    }

                },
                error: function (data, status, er) {
                    alert("Error");
                }
            });

            $("#omitirNIT").prop('checked', false);
            $("#txtNIT").removeProp("disabled");
            $("#btnValidarNIT").prop("class", "btn btn-danger");

            if ($("#lblNIT").text() !== "") {
                $("#btnPrintTwo").prop("class", "btn btn-danger");
            }

        }

        $("#lblNombre").text('');
        $("#lblNIT").text('');

        $('select[name=prioridadTwo]').selectpicker();
        var sVal = $('select[name=prioridadTwo] option:first').val();
        $('select[name=prioridadTwo]').val(idPrioridad === 0 ? sVal : idPrioridad);
        $('select[name=prioridadTwo]').selectpicker('refresh');

        $("#modalPrioridadConNit .toggle").prop("class", "toggle btn btn-default off");
        $("#tholguraTwo").prop("checked", false);

        $(".informationBar").css("display", "none");
        $(".infoNombre").css("display", "none");
        $(".infoNIT").css("display", "none");
        $(".infoError").css("display", "none");

    });






    /* Oscar Vides
     * Método para generar número de tiquetes
     *  
     *  */

    $("#btnPrintOne").click(function () {

        var tramite = $("#idTramite").val();
        var prioridadOne = $("#prioridadOne option:selected").val();
        var holguraOne = $("#tholguraOne").is(":checked") ? "1" : "0";
        var idTiqueteSession = $.session.get('idTiqueteSession') === undefined ? 0 : $.session.get('idTiqueteSession');

        var json = {"tramite": tramite, "prioridad": prioridadOne, "holgura": holguraOne, "opt": 0, "idTiquete": idTiqueteSession};
        if (isExistConfTiquete() === 1) {
            $.ajax({
                url: window.location.protocol + "//" + window.location.host + "/colas/me/insertData",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
//                beforeSend: function () {
//                   // $("#loader").css("display", "block");
//                   $("#loader").show();
//                },
//                complete: function () {
//                    //$("#loader").css("display", "none");
//                    $("#loader").hide();
//                },
                success: function (data) {
                    if (isEmptyObject(data)) {
                        if (parseInt(idTiqueteSession) === 0) {
                            mostrarAlertaError("Error", "Este trámite se encuentra fuera de servicio", "error");
                        } else {
                            mostrarAlertaError("Error", "Este trámite no se puede reasignar", "error");
                        }
                    } else {
                        $.session.remove('idTiqueteSession');
                        $.session.remove('idPrioridad');
                        $.session.remove('nitSession');

                        $('#cosId').removeProp('disabled');
                        $('#reaId').removeProp('disabled');
                        $('#escId').removeProp('disabled');
                        $('#reimId').removeProp('disabled');
                        $('#cosId').css('cursor', 'pointer');
                        $('#reaId').css('cursor', 'pointer');
                        $('#escId').css('cursor', 'pointer');
                        $('#reimId').css('cursor', 'pointer');
                        $("#prioridadOne").removeProp("disabled");
                        $("#prioridadTwo").removeProp("disabled");
                        $("#navegationPage").html("Trámite");
                        paraImprimir(data.nTiqueteId);
                    }
                },
                error: function (data, status, er) {
                    alert("errorXXX: " + data.nTiqueteId + " statusyyy: " + status + " er:" + er);
                }
            });
            $('select[name=prioridadOne]').selectpicker('val', 1);
            $("#prioridadOne").val($("#prioridadOne option:first").val());
            $("#modalPrioridadSinNit .toggle").prop("class", "toggle btn btn-default off");
            $("#tholguraOne").prop("checked", false);
        } else {
            mostrarAlertaError("Error", "No se puede visualizar número de tiquete, <br /> asegúrse que para este Centro de Servicio exista una configuración de tiquete.", "error");
        }


    });




    /* Oscar Vides
     * Método para validar NIT
     *  
     *  */

    $("#btnValidarNIT").click(function () {

        var json = {"nit": $("#txtNIT").val()};

        $.ajax({
            url: window.location.protocol + "//" + window.location.host + "/colas/me/validarID",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(json),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {

                if (isEmptyObject(data)) {
                    $("#lblNitError").text($("#txtNIT").val());
                    $(".infoError").removeAttr("style");
                    $(".informationBar").css("display", "none");
                    $(".infoNombre").css("display", "none");
                    $(".infoNIT").css("display", "none");

                    $("#lblNombre").text(' ');
                    $("#lblNIT").text(' ');
                    $("#btnPrintTwo").prop("class", "btn btn-danger disabled");
                    //$("#txtNIT").val(' ');
                } else {
                    $("#lblNombre").text(data.SNombres);
                    $("#lblNIT").text(data.nit);
                    $("#btnPrintTwo").prop("class", "btn btn-danger");

                    $(".infoError").css("display", "none");
                    $(".informationBar").removeAttr("style");
                    $(".infoNombre").removeAttr("style");
                    $(".infoNIT").removeAttr("style");
                }

            },
            error: function (data, status, er) {
                alert("Error");
            }
        });

    });








    /* Oscar Vides
     * Método para generar número de tiquetes
     *  
     *  */

    $("#btnPrintTwo").click(function () {


        var tramite = $("#idTramite").val();
        var prioridadTwo = $("#prioridadTwo option:selected").val();
        var holguraTwo = $("#tholguraTwo").is(":checked") ? "1" : "0";
        var idTiqueteSession = $.session.get('idTiqueteSession') === undefined ? 0 : $.session.get('idTiqueteSession');

        var nit = $("#txtNIT").val();

        var json;
        if ($("#omitirNIT").is(":checked")) {
            json = {"tramite": tramite, "prioridad": prioridadTwo, "holgura": holguraTwo, "opt": 1, "idTiquete": idTiqueteSession};
        } else {
            json = {"tramite": tramite, "prioridad": prioridadTwo, "holgura": holguraTwo, "nit": nit, "opt": 1, "idTiquete": idTiqueteSession};
        }

        if (isExistConfTiquete() === 1) {
            $.ajax({
                url: window.location.protocol + "//" + window.location.host + "/colas/me/insertData",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
//                beforeSend: function () {
//                    //$("#loader").css("display", "block");
//                    $("#loader").show();
//                },
//                complete: function () {
//                    //$("#loader").css("display", "none");
//                    $("#loader").hide();
//                },
                success: function (data) {
                    if (isEmptyObject(data)) {
                        if (parseInt(data.nTiqueteId) === 0) {
                            mostrarAlertaError("Error", "Este trámite se encuentra fuera de servicio", "error");
                        } else {
                            mostrarAlertaError("Error", "Este trámite no se puede reasignar", "error");
                        }
                    } else {
                        $.session.remove('idTiqueteSession');
                        $.session.remove('idPrioridad');
                        $.session.remove('nitSession');

                        $('#cosId').removeProp('disabled');
                        $('#reaId').removeProp('disabled');
                        $('#escId').removeProp('disabled');
                        $('#reimId').removeProp('disabled');
                        $('#cosId').css('cursor', 'pointer');
                        $('#reaId').css('cursor', 'pointer');
                        $('#escId').css('cursor', 'pointer');
                        $('#reimId').css('cursor', 'pointer');
                        $("#prioridadOne").removeProp("disabled");
                        $("#prioridadTwo").removeProp("disabled");
                        $("#navegationPage").html("Trámite");
                        if (parseInt(data.nTiqueteId) === 0) {
                            mostrarAlertaError("Error", "Este trámite se encuentra fuera de servicio", "error");
                        } else {
                            paraImprimir(data.nTiqueteId);
                        }

                    }
                },
                error: function (data, status, er) {
                    alert("error");
                }
            });

            $("#carnet").val('');
            $("#btnPrintTwo").prop("disabled", true); // Mantener el botón deshabilitado
            $("#btnPrintTwo").prop("class", "btn btn-danger disabled");
            $("#mensaje").text('Ingrese su codigo numero de carnet y validelo con tecla OK');


            $("#omitirNIT").prop("checked", false);
            $("#txtNIT").val('');
            $("#lblNombre").text('');
            $("#lblNIT").text('');
            $('select[name=prioridadTwo]').selectpicker('val', 1);
            $("#prioridadTwo").val($("#prioridadTwo option:first").val());
            $("#modalPrioridadConNit .toggle").prop("class", "toggle btn btn-default off");
            $("#tholguraTwo").prop("checked", false);
            
            //OCULTAR EL MODAL
            $('#modalPrioridadConNit').modal('hide');

            if (!$("#omitirNIT").is(":checked")) {
                $("#txtNIT").removeProp("disabled");
                $("#btnValidarNIT").prop("class", "btn btn-danger");
                $("#btnPrintTwo").prop("class", "btn btn-danger disabled");
            }
        } else {
            mostrarAlertaError("Error", "No se puede visualizar número de tiquete, <br /> asegúrse que para este Centro de Servicio exista una configuración de tiquete.", "error");
        }
    });





    $("#omitirNIT").click(function () {

        if ($(this).is(":checked")) {
            $("#txtNIT").prop("disabled", "disabled");
            $("#btnValidarNIT").prop("class", "btn btn-danger disabled");
            $("#btnPrintTwo").prop("class", "btn btn-danger");
            $("#btnPrintTwo").prop("disabled", false);
            $("#mensaje").text("¡Carnet omitido! Puede proceder a generar su tiquete.");

        } else {
            $("#txtNIT").removeProp("disabled");
            $("#btnValidarNIT").prop("class", "btn btn-danger");
            $("#btnPrintTwo").prop("disabled", true);

            if ($("#lblNIT").text() === '')
                $("#btnPrintTwo").prop("class", "btn btn-danger disabled");
        }

    });

    $("#omitirNIT").change(function () {
        if ($(this).is(":checked")) {
            // Si el checkbox está marcado, habilitar el botón
            $("#btnPrintTwo").prop("disabled", false);
            $("#btnPrintTwo").removeClass("disabled").addClass("btn btn-danger");
            $("#mensaje").text("¡Carnet omitido! Puede proceder a generar su tiquete.");
        } else {
            // Si el checkbox está desmarcado, restablecer el estado del botón
            $("#btnPrintTwo").prop("disabled", true);
            $("#mensaje").text("Ingrese su codigo numero de carnet y validelo con tecla OK");
        }
    });




    /****************************************************************************************************
     * Access Links Pages
     */
    /***************************************************************************************************/

    $("#cosId").click(function () {

        $(location).attr('href', window.location.protocol + "//" + window.location.host + "/colas/me/reservaCita");

    });


    $("#reaId").click(function () {

        $(location).attr('href', window.location.protocol + "//" + window.location.host + "/colas/me/reasignacionTiquete");

    });


    $("#escId").click(function () {

        $(location).attr('href', window.location.protocol + "//" + window.location.host + "/colas/me/escalamientoTiquete");

    });


    $("#reimId").click(function () {

        $(location).attr('href', window.location.protocol + "//" + window.location.host + "/colas/me/reimpresionTiquete");

    });


    function getTramites(servid) {
        var result = "";
        var jTramites = {idServicios: servid};
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


});

function colorUI(nu) {
    /*Modificacion para generar color a los servicios
     var letters = '0123456789ABCDEF'.split('');
     var color = '#';
     for (var i = 0; i < 6; i++ ) {
     color += letters[Math.round((Math.random() * 14)+1)];
     }
     return color;*/
    var colores = ["#077F29", "#7F066D", "#C1B352", "#1A80A9", "#DC703E", "#B69794", "#1D1DE4", "#04B404", "#53B8A2", "#BF23A0", "#FF4000", "#948D84", "#B40404", "#A4A4A4", "#DB6B46", "#00BFFF", "#00FF00", "#B40486"];
    if (nu >= 16)
        nu = nu % 18;
    //var nu=Math.floor(Math.random()*(colores.length));
    return colores[nu];
}


function isExistConfTiquete( ) {
    var result = 0;
    $.ajax({
        url: window.location.protocol + "//" + window.location.host + "/colas/me/isExistConfTramite",
        type: 'POST',
        dataType: 'json',
        data: {},
        contentType: 'application/json',
        mimeType: 'application/json',
        async: false,
        success: function (data) {
            if (isEmptyObject(data)) {

            } else {
                result = data.isExist;
            }
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
    return result;
}

function enableReservaCita() {

    $(function () {

        var json = {"idReservaCita": $("#idReservaCita").val(), "idTramite": $("#idTramite").val(), "fecha": $("#lblFecha").text(), "nit": $("#nit").val()};
        $.ajax({
            url: window.location.protocol + "//" + window.location.host + "/colas/rcta/enableReservacion",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(json),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                if (parseInt(data) === 0) {
                    //mostrarAlertaError("Error","No se puede realizar la operación","error");
                    mostrarAlertaError("Error", "Este trámite se encuentra fuera de servicio", "error");
                } else {
                    paraImprimir(data);
                    $("button[name='refresh']").trigger("click");
                }
            },
            error: function (data, status, er) {
                alert("error: " + data + " status: " + status + " er:" + er);
            }
        });

    });

}
/*
 * Alcides Nolasco
 * Funcion para confirmar una cita
 */
function confirmaCita(citaId) {

    $(function () {

        var json = {"citaId": citaId};
        $.ajax({
            url: window.location.protocol + "//" + window.location.host + "/colas/rcta/confirmaCita",
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(json),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {

                if (data.mensaje === "ok") {
                    $('#modalparaCita').modal('hide');
                    paraImprimir(data.ticket);
                } else
                {
                    $("#headerCita").text(data.mensaje);
                }
            },
            error: function (data, status, er) {
                alert("error: " + data + " status: " + status + " er:" + er);
            }
        });

    });

}


