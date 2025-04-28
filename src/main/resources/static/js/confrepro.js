

function load() {
    var s = "";
    var aux = "";
    var json = {"idqtx": 1};
    $.ajax({
        url: window.location.protocol + "//" + window.location.host + "/colas/crepro/cargar",
        type: 'POST',
        async: false,
        dataType: 'json',
        data: JSON.stringify(json),
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (data) {
            if (isEmptyObject(data)) {
                mostrarAlertaError("Error", "Datos vacios", "error");

            } else {
                //alert(JSON.stringify(data));
                $('#fmunidades').append($('<option>', {
                    value: 0,
                    text: "Elegir Centtro de Servicio..."
                }));
                if (data.unidades.length != 0) {
                    for (i = 0; i < data.unidades.length; i++) {
                        aux = data.unidades[i].split(";")
                        $('#fmunidades').append($('<option>', {
                            value: aux[0],
                            text: aux[1]
                        }));
                    }
                }



            }
        },
        error: function (data, status, er) {
            alert("error: " + JSON.stringify(data) + " status: " + status + " er:" + er);
        }
    });

}

function isEmptyObject(obj) {
    return JSON.stringify(obj) === '{}';
}

function mostrarAlertaError(title, content, type) {

    $.msgBox({
        title: title,
        content: content,
        type: type,
        buttons: [{value: "Cerrar"}],
        opacity: 0.5,
        autoClose: false
    });

}
;



function chango() {
    var aux = "";
    var id = "";
    var modo = "";
    var myclase = ""
    var color = "";
    $("#contenido").empty();
    var nunidad = $('#fmunidades').val();
    if (nunidad == 0)
        return;
    var idmedia = 0;
    // se le agrega el trigger a la tabla para que todos los check boxes ahgregados dinamicamente hereden el comportamiento

    var json = {"unidad": nunidad};
    $.ajax({
        url: window.location.protocol + "//" + window.location.host + "/colas/crepro/leerLista",
        type: 'POST',
        async: false,
        dataType: 'json',
        data: JSON.stringify(json),
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (data) {
            if (isEmptyObject(data)) {
                mostrarAlertaError("Error", "Datos vacios", "error");

            } else {
                plantilla = "<tr><td><input type='checkbox' id='$0' value='$0' $1  /></td><td>$2</td></tr></td>";
                for (i = 0; i < data.length; i++) {
                    idmedia = data[i][0];
                    etiqueta = data[i][2];
                    idservicio = data[i][1];
                    if (idservicio === "") {
                        check = "";
                    } else {
                        check = "checked";
                    }
                    s = plantilla.replaceAll("$0", idmedia);
                    s = s.replace("$2", etiqueta);
                    s = s.replace("$1", check);
                    $('#contenido').append(s);
                    // $("#" + idmedia).bootstrapToggle();  // aplica el estilo  boton deslizable
                    $("#" + idmedia).on("change", idmedia, function() {  
                     if (nunidad === 0)
                            return;
                           var action = "";
                    if (this.checked) {
                            action = "insert";
                        } else {
                            action = "delete";
                     }
                        var json = {"unidad": nunidad, "media": this.id, "action": action};

                        $.ajax({
                            url: window.location.protocol + "//" + window.location.host + "/colas/crepro/guardarMedia",
                          
                            type: 'POST',
                            async: false,
                            dataType: 'json',
                            data: JSON.stringify(json),
                            contentType: 'application/json',
                            mimeType: 'application/json',
                            success: function (data) {
                                if (isEmptyObject(data)) {
                                    mostrarAlertaError("Error", "Datos vacios", "error");

                                } else {
                                    if (data.resultado !== "ok") {
                                        mostrarAlertaError("Error", "Error al guardar: " + data.mensaje, "Error");
                                    }
                                }
                            },
                            error: function (data, status, er) {
                                alert("error: " + JSON.stringify(data) + " status: " + status + " er:" + er);
                            }
                        });
                    });
                }
            }


        },
        error: function (data, status, er) {
            alert("error: " + JSON.stringify(data) + " status: " + status + " er:" + er);
        }
    });
    // 

    function updatemedia(idmedia) {
        var nunidad = $('#fmunidades').val();
        if (nunidad === 0)
            return;
        // para evitar que se ejecute al agregar el event handler 
        if ($("#" + idmedia).val() !== 0) {
            $("#" + idmedia).val(0);
            return;
        }
        var action = "";
        var json = {"unidad": nunidad, "media": idmedia};
        if ($("#" + idmedia).is(':checked')) {
            action = "insert";
        } else {
            action = "delete";
        }
        var json = {"unidad": nunidad, "media": idmedia, "action": action};

        $.ajax({

            url: window.location.protocol + "//" + window.location.host + "/colas/crepro/guardarMedia",
            type: 'POST',
            async: false,
            dataType: 'json',
            data: JSON.stringify(json),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                if (isEmptyObject(data)) {
                    mostrarAlertaError("Error", "Datos vacios", "error");

                } else {
                    //alert(JSON.stringify(data));   
                    if (data.resultado == "ok") {
                        mostrarAlertaError("Informaci\u00F3n", "Reproduccion actualizada", "info");
                    } else {
                        mostrarAlertaError("Error", "Error al guardar: " + data.mensaje, "Error");
                    }
                }
                $('#fmunidades').trigger("change");

            },
            error: function (data, status, er) {
                alert("error: " + JSON.stringify(data) + " status: " + status + " er:" + er);
            }
        });
    }

//    $( "#sortable1 li" ).each(function( index ) {
//     alert( index + ": " + $( this ).text()+"::: "+$(this).attr('id'));
//    });
}
function liclick(control) {
    var id = $(control).attr('id');
    var nid = id.substring(2);
    //var sp="#sp"+nid;

    if ($(control).hasClass("selecta")) {
        //alert("es selecta");        
        $(control).css("background-color", "FFFFFF");
        $(control).css("color", "000000");
        //$(sp).css("color","FFFFFF");
        $(control).removeClass("selecta");
    } else {
        //alert("nada de selecta");
        $(control).css("background-color", "587FF6");
        $(control).css("color", "FFFFFF");
        //$(sp).css("color","FFFFFF");
        $(control).addClass("selecta")
    }
}
function guardar() {
    var nunidad = $('#fmunidades').val();
    var str = "";
    var sp = "";
    var id = "";
    var nid = "";
    var i = 0;
    $("#tabmar tr").each(function () {
        id = $(this).attr('id')
        nid = id.substring(2);
        if ($(this).hasClass("selecta")) {
            if (i > 0)
                str = str + ";";
            str = str + nid;
            i++;
        }

    });

    var json = {"unidad": nunidad, "cadena": str};
    $.ajax({
        url: window.location.protocol + "//" + window.location.host + "/colas/crepro/guardarLista",
        type: 'POST',
        async: false,
        dataType: 'json',
        data: JSON.stringify(json),
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (data) {
            if (isEmptyObject(data)) {
                mostrarAlertaError("Error", "Datos vacios", "error");

            } else {
                //alert(JSON.stringify(data));   
                if (data.resultado == "ok") {
                    mostrarAlertaError("Informaci\u00F3n", "Lista Guardada", "info");
                } else {
                    mostrarAlertaError("Error", "Error al guardar: " + data.mensaje, "Error");
                }
            }
            $('#fmunidades').trigger("change");

        },
        error: function (data, status, er) {
            alert("error: " + JSON.stringify(data) + " status: " + status + " er:" + er);
        }
    });

}
