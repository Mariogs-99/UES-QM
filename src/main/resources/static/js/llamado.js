$(document).ready(function () {

    //$("#mensaje").text(varMensaje);



    $(function () {
        $(".grid").sortable({
            tolerance: 'pointer',
            revert: 'invalid',
            placeholder: 'span2 well placeholder tile',
            forceHelperSize: true
        });
    });


    function formatTime(minutos) {
        if (parseInt(minutos) == 0) {
            return 0;
        }
//			    var today = new Date();
        var today = new Date(2024, 11, 1);
        today.setMinutes(minutos / 60);
        var h = today.getHours();
        var m = today.getMinutes();
        var s = today.getSeconds();
        // add a zero in front of numbers<10
        m = checkTime(m);
        s = checkTime(s);
        var minutos = m + (h * 60);
        return minutos;
    }

    function checkTime(i) {
        if (i < 10) {
            i = "0" + i;
        }
        return i;
    }
});

function dblck() {
    $('#videoLlamado').trigger("pause");
    var valor = $('#tiporepro').val();
    if (valor == "TV") {
        $('#tiporepro').val("TV");
        alert("Cambio a TV");
        $('#videoLlamado').attr('src', 'http://localhost:8001/tv.ogg');
        $('#videoLlamado').trigger("play");
    } else {
        $('#tiporepro').val("VI");
        alert("Cambio a VIDEO");
        setTimeout(reproducir, 3000);
    }
}
function escribirHora() {
    var Digital = new Date()
    var hours = Digital.getHours()
    var minutes = Digital.getMinutes()
    var seconds = Digital.getSeconds()
    var dia = Digital.getUTCDate()
    var mes = Digital.getMonth()
    var nMes = ""
    var anho = Digital.getFullYear()

    if (minutes <= 9)
        minutes = "0" + minutes
    if (seconds <= 9)
        seconds = "0" + seconds
    /* if (dia<=9)
     dia="0"+dia
     * 
     */
    switch (mes) {
        case 0:
            nMes = "ene"
            break
        case 1:
            nMes = "feb"
            break
        case 2:
            nMes = "mar"
            break
        case 3:
            nMes = "abr"
            break
        case 4:
            nMes = "may"
            break
        case 5:
            nMes = "jun"
            break
        case 6:
            nMes = "jul"
            break
        case 7:
            nMes = "ago"
            break
        case 8:
            nMes = "sep"
            break
        case 9:
            nMes = "oct"
            break
        case 10:
            nMes = "nov"
            break
        case 11:
            nMes = "dic"
            break

    }

    $("#ti").text(hours + ":" + minutes + ":" + seconds);
    $("#fe").text(dia + " " + nMes + " " + anho);
    setTimeout(escribirHora, 1000);
}
function leerContenido() {
    setTimeout(readData, 5000);
    setTimeout(writeData, 3000);
    setTimeout(escribirHora, 1000);
}
function readData() {
    var json = {"id": 0};
    if (videoList.length > 0)
        return;
    else
        $.ajax({
            url: window.location.protocol + "//" + window.location.host + "/colas/llamado/getContenido",
            type: 'POST',
            async: false,
            dataType: 'json',
            data: JSON.stringify(json),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                if (isEmptyObject(data)) {
                    mostrarAlertaError("Error", "Error al cargar", "error");

                } else {
                    if (data.length > 0) {
                        aux = "";
                        for (i = 0; i < data.length; i++) {
                            videoList.push(data[i]);
                        }
                        $('#selnum').val(0);
                        setTimeout(playNextVideo(), 3000);
                    }
                }
            },
            error: function (data, status, er) {
//		        alert("error: "+JSON.stringify(data)+" status: "+status+" er:"+er);
            }
        });

}
function llamarS() {
    var valor = $('#tiporepro').val();
    if (valor == "VI") {
        setTimeout(reproducir, 3000);
    }
}
function reproducir() {
    var turno = parseInt($('#selnum').val());
    var nf = $('#selcon option').eq(turno).prop('value');
    var ll = $('#selcon').children('option').length;

    //
    // 
    console.log("NUMERO DE NF:" + nf);
    $('#videoLlamado').attr('src', 'C:\\usr\\local\\apache-tomcat-8.5.98\\bin\\uploads\\tv.mp4');
    $('#videoLlamado').prop('volume', 1);
    $('#videoLlamado').trigger("play");
    turno++;
    if (turno >= ll)
        turno = 0;
    $('#selnum').val(turno);

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


function ordenarGuardar(data) {
    var listTemporal = [];
    listTemporal.push(data);
    for (index = 0; index < llamados.length; index++) {
        if (data.tiquete != llamados[index].tiquete) {
            listTemporal.push(llamados[index]);
        }
    }
    if (listTemporal.length == 12) {
        listTemporal.pop();
    }
    llamados = listTemporal;
}

function writeData() {

    var json = {
        "centro": varCentro
    };

    $.ajax({
        url: window.location.protocol + "//" + window.location.host + "/colas/llamado/getData",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(json),
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (data) { 
            if (data.tiquete !== "NoTiquete" && data.tiquete !== undefined) {
                console.log("DATA RES LLAMADO: ", data.tiquete);
                ordenarGuardar(data);
                $("#servicioF").text(llamados[0].tramite);
                $("#escritorioF").text(llamados[0].escritorio);
                $("#numeroF").text(llamados[0].tiquete);
                // Metodo Inmportante para la reproduccion del sonido
                // este metodo reproduce el sonido del # ticket al # de escritorio
                getSound(llamados[0].tiquete, llamados[0].escritorio);
            } else
                setTimeout(writeData, 3000);
        },
        error: function (data, status, er) {
            console.log("ERROR: ", er + status);
//			alert("error in llamando/getData: " + JSON.stringify(data) + " status: " + status + " er:" + er);
        }
    });
}

function playNextVideo() {
    // Verifica que el índice esté dentro del rango válido
    if (currentVideoIndex >= videoList.length) {
        currentVideoIndex = 0; // Reinicia el índice si se llega al final de la lista
    }

    var path = "/colas/uploads/"; // Ruta base del archivo
    var file = videoList[currentVideoIndex][14]; // Nombre del archivo
    var type = videoList[currentVideoIndex][1]; // Tipo de medio {imagen,video}
    var fullPath = path + file; // Ruta completa del archivo

    var videoElement = document.getElementById("videoLlamado");

    if (type === "imagen") {
        // Si es una imagen, cambia el poster del video
        videoElement.setAttribute('poster', fullPath);
        videoElement.removeAttribute('src'); // Elimina cualquier video previamente cargado
        videoElement.pause(); // Pausa cualquier reproducción si está en curso
    } else if (type === "video") {
        // Si es un video, cambia la fuente y reproduce automáticamente
        videoElement.setAttribute('src', fullPath);
        videoElement.removeAttribute('poster'); // Elimina el poster
        videoElement.load(); // Carga el nuevo video
        
        // Asegúrate de que el video se reproduzca automáticamente
        videoElement.play().catch(error => {
            console.error("Error al intentar reproducir el video:", error);
        });
    }

    currentVideoIndex++; // Incrementa el índice para el siguiente video/imagen
}



 