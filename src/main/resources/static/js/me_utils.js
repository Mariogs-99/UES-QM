
$(document).ready(function () {

    $("#idSalir").click(function () {
        $.session.clear();
    });

    var href = document.location.href;
    var lastPathSegment = href.substr(href.lastIndexOf('/') + 1);
    // Alcides Nolasco
    // Se inicializa webapp-hardware-bridge
    //***************
    var printService = new WebSocketPrinter();

    if (lastPathSegment === 'home') {
        if ($.session.get("loadedPage") === 'reasignacionTiquete') {
            $("#navegationPage").html("<a href='#'>Trámite</a> > Reasignación de tiquete");
        } else {
            $("#navegationPage").html("<p>Trámite</p>");
        }
    } else if (lastPathSegment === 'reservaCita') {
        $("#navegationPage").html("<a href='#'>Trámite</a> > Confirmación de cita");
    } else if (lastPathSegment === 'reasignacionTiquete') {
        $("#navegationPage").html("<a href='#'>Trámite</a> > Reasignación de tiquete");
    } else if (lastPathSegment === 'escalamientoTiquete') {
        $("#navegationPage").html("<a href='#'>Trámite</a> > Escalamiento");
    } else if (lastPathSegment === 'reimpresionTiquete') {
        $("#navegationPage").html("<a href='#'>Trámite</a> > Reimpresión de tiquete");
    }


    $("#navegationPage a").click(function () {
        $.session.clear();
        $(location).attr('href', window.location.protocol + "//" + window.location.host + "/colas/me/home");
    });



    $("#btnPrint").click(function () {

        //location.reload();

        // Alcides Nolasco
        // silent print using Webapp-hardware-bridge
        // https://github.com/imTigger/webapp-hardware-bridge
        // *****
  //Toda esta seccion es para convertir html a cambas y luego mandar a cola de impresion en backgroud
//        $("#Ticket").css("opacity", 1);
//        html2canvas(document.querySelector("#Ticket")).then(
//                canvas => {
//                    var image = canvas.toDataURL("image/png").replace("data:image/png;base64,", "");
//                    console.log(image);
//                    printService.submit({
//                        'type': 'LABEL',
//                        'url': 'file.png',
//                        'file_content': image
//                    });
//
//                });
//        $("#Ticket").css("opacity", 0);

        var raw=html2escpos($("#htmlData").val());
        var b64=toBase64(raw); // encode 64bits
        printService.submit({
                        'type': 'LABEL',                    
                        'raw_content': b64}); 
//         Alcides Nolasco
//         habilitar menu primario y deshabilitar secundario 
//         corregir comportamiento: al mostrar perdia bordes redondeados en los botones
//         Se elimina el ticket que ya se imprimio 
        $("#marcoTiquete").empty();
        $("#htmlData").val(" ");

        $('#content').find(".panel.panel-i").show();
        $('#content').find("[id*=sub]").hide();

//        $('#content').find(".btn.btn-custom-primary").addClass("btn-custom-primary");
//        $('#content').find(".btn.btn-custom-secondary").addClass("btn-custom-secondary");


    });
    


});


$(window).scroll(function () {
    if ($(this).scrollTop() > 0)
    {
        $('.fadeInOut').fadeOut();
    } else
    {
        $('.fadeInOut').fadeIn();
    }
});

/* Oscar Vides
 * Función para mostrar alerta de error
 *  
 *  */
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



/* Oscar Vides
 * Función para validar si objeto json está vacío
 *  
 *  */

function isEmptyObject(obj) {
    return JSON.stringify(obj) === '{}';
}



/* Oscar Vides
 * Función para agregar opcion Ver detalle a tabla
 *  
 *  */

function operateDetail(value, row, index) {
    return [
        '<a class="detail" href="javascript:void(0)" title="Ver detalle">',
        '<img src="../images/ver.gif" data-toggle="modal" data-target="#modalDetalle" />',
        '</a>'
    ].join('');
}
;



/* Oscar Vides
 * Función para agregar opcion Imprimir tiquete a tabla
 *  
 *  */

function operatePrint(value, row, index) {
    return [
        '<a class="print" href="javascript:void(0)" title="Imprimir tiquete">',
        '<img src="../images/icon_print.gif" />',
        '</a>'
    ].join('');
}
;


/* Oscar Vides
 * Función para agregar opcion Reasignar tiquete a tabla
 *  
 *  */

function operateAsign(value, row, index) {
    return [
        '<a class="assign" href="javascript:void(0)" title="Reasignar tiquete">',
        '<img src="../images/assign.png" />',
        '</a>'
    ].join('');
}
;



/* Oscar Vides
 * Función para agregar opcion Reasignar tiquete a tabla
 *  
 *  */

function operateReprint(value, row, index) {
    return [
        '<a class="re-print" href="javascript:void(0)" title="Reasignar tiquete">',
        '<img src="../images/icon_print.gif" />',
        '</a>'
    ].join('');
}
;



/**
 * Agustín Romero
 * Metodo para impresión de tiquete
 * @param v_tiquete
 */

function paraImprimir(v_tiquete) {

    $("#marcoImp").html("");

    var json = {"idTiquete": v_tiquete};
    $.ajax({
        url: window.location.protocol + "//" + window.location.host + "/colas/me/leerTiquete",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(json),
        contentType: 'application/json;charset=utf-8',
        mimeType: 'application/json',
        /*
         beforeSend: function () {
         //$("#loader").css("display", "block");
         $("#loader").show();
         },
         complete: function () {
         //$("#loader").css("display", "none");
         $("#loader").hide();
         },
         */
        success: function (data) {
            if (isEmptyObject(data)) {
                mostrarAlertaError("Error", "Error al imprimir Tiquete", "error");
            } else {


                $("#marcoTiquete").html(data.resultado);
              
                $("#htmlData").val(data.resultado);
                $("#theimg").attr("src", data.img);
                //$("#btnShowTiquete").trigger("click");
                // Alcides Nolasco para que imprima de una vez
                //setTimeout($("#btnPrint").trigger("click"), 50000);
                $("#btnPrint").trigger("click");

                // se convierte a png y luego se manda a la cola en backgroud 

            }
        },
        error: function (data, status, er) {
            vda = JSON.stringify(data);
            alert("error: " + vda + " status: " + status + " er:" + er);
        }
    });

}
;




/**
 * Oscar Vides
 * Function para crear teclado virtual
 * @param div
 */
function showKeyBoard(div) {
    $('#' + div).on('click', '.form-control', function () {
        $(this).keyboard({
            layout: 'custom',
            customLayout: {
                'normal': [
                    '` 1 2 3 4 5 6 7 8 9 0 - = {bksp}',
                    '{tab} q w e r t y u i o p [ ] \\',
                    'a s d f g h j k l ; \' {enter}',
                    '{shift} z x c v b n m , . / {shift}',
                    '{accept} {space} {left} {right} {sp:.2} {del} {toggle}'
                ],
                'shift': [
                    '~ ! @ # $ % ^ & * ( ) _ + {bksp}',
                    '{tab} Q W E R T Y U I O P { } |',
                    'A S D F G H J K L : " {enter}',
                    '{shift} Z X C V B N M < > ? {shift}',
                    '{accept} {space} {left} {right} {sp:.2} {del} {toggle}'
                ]
            }
        }).addTyping();
    });
}

function toBase64(str) {
  // Codificar la cadena en UTF-8
  const utf8Bytes = new TextEncoder().encode(str);
  
  // Convertir los bytes a una cadena binaria
  let binaryString = "";
  utf8Bytes.forEach(byte => {
    binaryString += String.fromCharCode(byte);
  });

  // Codificar la cadena binaria en Base64
  return btoa(binaryString);
}
