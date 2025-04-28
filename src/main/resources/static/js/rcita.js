$(document).ready(function () {
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = now.getFullYear() + "-" + (month) + "-" + (day);
    $('#fecha').val(today);

    function setMinMaxDateTime() {
        var now = new Date();
        var year = now.getFullYear();
        var month = ('0' + (now.getMonth() + 1)).slice(-2);
        var day = ('0' + now.getDate()).slice(-2);

        // Definir la hora mínima y máxima
        var minTime = '08:00';
        var maxTime = '16:00';

        var currentHours = ('0' + now.getHours()).slice(-2);
        var currentMinutes = ('0' + now.getMinutes()).slice(-2);
        var currentDateTime = `${year}-${month}-${day}T${currentHours}:${currentMinutes}`;

        var fechaInput = document.getElementById('fecha');

        if (now.getHours() >= 16) {
            alert('No se pueden reservar citas después de las 4:00 PM hoy. Por favor, seleccione una fecha futura.');
        } else {
            fechaInput.setAttribute('min', currentDateTime);
        }

        // Establecer el máximo como las 4:00 PM para cualquier día
        var maxDateTime = `${year}-${month}-${day}T${maxTime}`;

        fechaInput.setAttribute('max', '2036-12-31T16:00');
    }


    // Cargar sucursales
    $.get(window.location.protocol + "//" + window.location.host + "/colas/rcita/getsucursales", function (data) {
        var tema = "";
        tema += "<option value='' disabled selected> Seleccione una sucursal... </option>";
        $.each(data, function (idx, obj) {
            tema += "<option value='" + obj.cUnidadResepId + "'>" + obj.sNombre + "</option>";
        });
        $("#sucursales").append(tema);
        $("#divCbTramites .bootstrap-select").css("width", "400px");
    }, "json");

    // Cargar servicios
    $.get(window.location.protocol + "//" + window.location.host + "/colas/rcita/getServicios", function (data) {
        var tema = "";
        var seenIds = new Set();

        tema += "<option value='' disabled selected> Seleccione un tramite... </option>";

        $.each(data, function (idx, obj) {
            if (!seenIds.has(obj.nServiciosId)) {
                tema += "<option value='" + obj.nServiciosId + "'>" + obj.sNombre + "</option>";
                seenIds.add(obj.nServiciosId);
            }
        });

        $("#tramites").append(tema);
        $("#divCbTramites .bootstrap-select").css("width", "400px");
    }, "json");

    // Inicializar datepickers
    $('#datetimepicker1').datetimepicker();
    $('#datetimepicker12').datetimepicker({
        inline: true,
        sideBySide: true
    });

    $('#btn_conmit').on('click', function () {
        // Limpiar mensajes de error
        $('.error-message').hide();

        // Validar campos
        var valid = true;
        var fechaHoraSeleccionada = new Date($("#fecha").val());
        var horaActual = new Date();

        // Validar que la fecha y hora seleccionadas no sean anteriores a la actual
        if (fechaHoraSeleccionada < horaActual) {
            $("#fecha").next('.error-message').text('La fecha y hora seleccionadas no pueden ser anteriores a la actual.').show();
            valid = false;
        }

        // Validar que la hora esté dentro del rango permitido (08:00 AM a 04:00 PM)
        var minHora = new Date(fechaHoraSeleccionada);
        minHora.setHours(8, 0, 0, 0); // Hora mínima: 08:00 AM
        var maxHora = new Date(fechaHoraSeleccionada);
        maxHora.setHours(17, 0, 0, 0); // Hora máxima: 04:00 PM

        if (fechaHoraSeleccionada < minHora || fechaHoraSeleccionada > maxHora) {
            $("#fecha").next('.error-message').text('La cita debe ser entre las 08:00 AM y las 04:00 PM.').show();
            valid = false;
        }

        if (!$("#InputName").val()) {
            $("#InputName").next('.error-message').show();
            valid = false;
        }
        if (!$("#fecha").val()) {
            $("#fecha").next('.error-message').show();
            valid = false;
        }
        if (!$("#sucursales").val()) {
            $("#sucursales").next('.error-message').show();
            valid = false;
        }
        if (!$("#tramites").val()) {
            $("#tramites").next('.error-message').show();
            valid = false;
        }

        if (valid) {
            var json = {
                "nombre": $("#InputName").val(),
                "nCarnet": $("#carnet").val(),
                "nTelefono": $("#telefono").val(),
                "nTramiteId": $("#tramites").val(),
                "sCorreo": $("#correo").val(),
                "fecha": $("#fecha").val(),
                "cUnidadResepId": $("#sucursales").val(),
                "sObservaciones": $("#observaciones").val()
            };
            $.ajax({
                url: window.location.protocol + "//" + window.location.host + "/colas/rcita/commit",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(json),
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function (data) {
                    $("#dia").text($("#fecha").val());
                    $("#oficina").text(data.oficina);
                    $("#cita").text(data.code);
                    $("#btnShowMensaje").trigger("click");

                    // Limpiar todos los campos del formulario
                    $("#InputName").val('');
                    $("#carnet").val('');
                    $("#telefono").val('');
                    $("#tramites").val('').change();
                    $("#correo").val('');
                    $("#fecha").val(today); // Restablecer la fecha al día actual
                    $("#sucursales").val('').change();
                    $("#observaciones").val('');
                },
                error: function (data, status, er) {
                    // Manejar error de la petición
                }
            });
        }
    });

    /*
    // Validar carnet al hacer clic en el botón "Validar"
    $("#validateCarnetButton").on("click", function () {
        // Obtener el encabezado Authorization ya codificado del localStorage
        const authHeader = localStorage.getItem("authHeader");

        // Verificar si el valor existe en localStorage
        if (!authHeader) {
            alert("Authorization header not found in localStorage.");
            return;
        }

        // Construir la URL de manera dinámica
        var apiUrl = window.location.protocol + "//" + window.location.host + "/colas/proxy/carnet?carnet=" + $("#carnet").val();

        $.ajax({
            url: apiUrl, // Usar la URL construida
            type: 'GET',
            dataType: 'json',
            headers: {
                "Authorization": authHeader // Agregar el encabezado codificado aquí
            },
            success: function (response) {
                if (response.error) {
                    // Si el carnet no es válido, mostrar el div de error
                    $("#carnetError").show(); // Asegúrate de que el div esté visible
                    $("#carnet").val(''); // Vaciar el input de carnet
                } else {
                    // Si el carnet es válido, ocultar el div de error y llenar los campos con los datos
                    $("#carnetError").hide(); // Ocultar el div de error si es válido
                    $("#InputName").val(response.data.nombre + " " + response.data.apellidos);
                    $("#carnet").val(response.nit);
                }
            },
            error: function (data, status, er) {
                alert("error: " + data + " status: " + status + " er:" + er);
            }
        });
    });
    */
   
    /*
     // Validar carnet - alumnos de prueba
     $("#carnet").on("focusout", function () {
     var json = {"nit": $("#carnet").val()};
     
     $.ajax({
     url: window.location.protocol + "//" + window.location.host + "/colas/me/validarID",
     type: 'POST',
     dataType: 'json',
     data: JSON.stringify(json),
     contentType: 'application/json',
     mimeType: 'application/json',
     success: function (data) {
     if (isEmptyObject(data)) {
     $("#InputName").val("Numero de carnet no existe");
     } else {
     $("#InputName").val(data.SNombres);
     $("#carnet").val(data.nit);
     }
     },
     error: function (data, status, er) {
     alert("error: " + data + " status: " + status + " er:" + er);
     }
     });
     });
     */
});
