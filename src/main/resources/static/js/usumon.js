var datos = [
    {
        "Idusuario": 0,
        "nombre": "usuario.inactivo",
        "escritrio": "0",
        "rol": "--",
        "tramite": "--",
        "estado": "Inactivo",
        "toatendidos": "0",
        "isesion": "7:35am",
        "iproceso": "7:40am",
        "seccion": "--"
    }];
function asignar(result) {
    datos = $.parseJSON(result);
}
$(document).ready(function () {
    var usr = "";
    var act = "";
    $('#checkbox1').change(function () {
        if (!this.checked)
            $('.autoUpdate').fadeIn('slow');
        else
            $('.autoUpdate').fadeOut('slow');
    });
    $.each(datos, function (idx, obj) {
        usr += "<li style='list-style:none'>";
        usr += "<div id=" + obj.nombre + " class='ubox'>";
        usr += "<div class='foto' style='cursor:pointer'><img src='../images/1.png' alt='usuario' width='90' height='80' style='cursor:pointer'/><div class='info' style='cursor:pointer'>" + "<option class='name' style='font-size:14px; margin-top:15px;'>" + obj.nombre + "</option>" + "<option class='born' style='font-size:13px;'>" + "escritorio:  #  " + obj.escritorio + "</option>";
        
        var arrs = obj.secciones.split(',');
        usr += "<div id='seccion' class='autoUpdate'> ";
        $.each(arrs, function (k){
            usr += arrs[k] + "<br>";
        });
        usr +="</div>"
        if (obj.estado === 'INACTIVO') {
            usr += "</div></div><div class='est'style='cursor:pointer'><div class='estado degradadoGris'  title='" + obj.estado + "' style=' background-color: #333; ' ></div>";
        }else if (obj.estado === 'Almuerzo' || obj.estado === 'Pausa' || obj.estado === 'Receso') {
            usr += "</div></div><div class='est'style='cursor:pointer'><div class='estado degradadoRojo'  title='" + obj.estado + "' style=' background-color: red;' ></div>";
        }else {
            usr += "</div></div><div class='est' style='cursor:pointer'><div class='estado degradadoVerde'  title='" + obj.estado + "' style=' background-color: green;' ></div>";
        }
        usr += "</div></div>";
        usr += "</li>";
        $(".list").html(usr);
    });
    $("#userDatailIda .ubox").click(function(){
        var n = $(this).attr("id");
        $.each(datos, function (idx, obj){
            if (obj.nombre === n) {
                act="";
                if (obj.estado === 'INACTIVO') {
                    act += "degradadoGris";
                }else if (obj.estado === 'Almuerzo' || obj.estado === 'Pausa' || obj.estado === 'Receso') {
                    act += "degradadoRojo";
                } else {
                    act += "degradadoVerde";
                }
                var headUsuarioDetalle='<div id="ll">'+
                    '<li style="list-style:none">'+
                    '<div id="xenia.rivera.test" class="ubox" style="width: 350px; height:45px;">'+
                    '<div class="foto" style="cursor:pointer">'+
                    '<img src="../images/1.png" alt="usuario" width="50" height="50"/>'+
                    '<div class="informacion" style="cursor:pointer; width:70px; height:45px; font-size:13px;"> '+obj.nombre+
                    '</div></div><div class="este" style="height:45px; margin-left: 100px;">'+
                    '<div class="estado '+act+'" title="'+obj.estado+'" style="background-color: #333; width: 40px; height:40px; margin-top:2px;"></div>'+
                    '</div></div></li></div>';
                $('#detalleUsuario').html(headUsuarioDetalle);
                $('#estadou').html(obj.estado);
                $('#toatendidou').html(obj.toatendidos);
                $('#escritoriou').html(obj.escritorio);
                $('#inicioProceu').html(obj.iproceso);
                var arrs = obj.secciones.split(',');
                var seccio="";
                $.each(arrs, function (j) {
                    seccio += "<li>"+arrs[j].toString().toUpperCase() + "</li><br>";
                });
                $('#seccionesu').html(seccio);
                var arrt = obj.tramites.split(',');
                var trami="";
                $.each(arrt, function (k) {
                    trami += "<li>"+arrt[k].toString().toUpperCase() + "</li><br>";
                });
                $('#tramitesu').html(trami);  
            }
        });
    });
    
//    $("#userDatailIda .ubox").click(function () {
//        var n = $(this).attr("id");
//        $.each(datos, function (idx, obj) {
//            if (obj.nombre === n) {
//                //act = "<div style=' height: 1px; width: 1px; background-color: gray; margin-top: 10px;'></div>";
//                act += "<li style='list-style:none'>";
//                act += "<div id=" + obj.nombre + " class='ubox' style='width: 275px; height:50px;'>";
//                act += "<div class='foto' style='cursor:pointer'><img src='../images/1.png' alt='usuario' width='50' height='50'  style='cursor:pointer; width=50; height=50; '/><div class='informacion' style='cursor:pointer; width:70px; height:50px; font-size:13px;'>" + "<option class='name' >" + obj.nombre + "</option>";
//                if (obj.estado === 'INACTIVO') {
//                    act += "</div></div><div class='este'style='cursor:pointer  width:90px; height:50px;' ><div class='estado degradadoGris' title='" + obj.estado + "' style='background-color: #333; width: 45px; height:40px; margin-top:5px;' ></div>"
//                }else if (obj.estado === 'Almuerzo' || obj.estado === 'Pausa' || obj.estado === 'Receso') {
//                    act += "</div></div><div class='este'style='cursor:pointer  width:90px; height:50px;'><div class='estado degradadoRojo' title='" + obj.estado + "' style='background-color: red; width: 45px; height:40px; margin-top:5px;' ></div>"
//                } else {
//                    act += "</div></div><div class='este' style='cursor:pointer width:90px; height:50px;'><div class='estado degradadoVerde' title='" + obj.estado + "' style='background-color: green; width: 45px; height:40px; margin-top:5px;' ></div>"
//                }
//                act += "</div></div>";
//                act += "</li>";
//            }
//        });
//        $("#ll").css("display", "inline");
//        $("#ll").html(" ");
//        $("#ll").html(act);
//    });

    //var actv = "";
//    $("#userDatailIda .ubox").click(function () {
//        $("#actividades").html(" ");
//        var n = $(this).attr("id");
//        $.each(datos, function (idx, obj) {
//            $("#actividades").html(" ");
//            if (obj.nombre === n) {
//                $("#actividades").html(" ");
//                var arrt = obj.secciones.split(',');
//                var arrs = obj.tramites.split(',');
//                actv = "<div  style='width: 220px;  background: linear-gradient( 0deg,#000 ,#08298A); margin-top: 10px; border-radius: 10px; margin-left:20px; color:#fff; font-size:13px;'>\n\
//                " + "<h3 style='color:#fff; margin:auto; text-aling:center;'>ACTIVIDADES </h3>  " + "<br> " + "Estado:   " + obj.estado + "\n\
//               <br><br><hr style='border: 0; border-top: 1px solid #102E88; border-bottom: 1px solid #333; height:0; margin-bottom:0px; margin-top:0px;'>" + "Total de atendidos   " + obj.toatendidos + "\n\
//               <br><br><hr style='border: 0; border-top: 1px solid #102E88; border-bottom: 1px solid #333; height:0; margin-bottom:0px; margin-top:0px;'>" + "Escritorio asignado:   " + obj.escritorio + "\n";
//                actv += "<br><br><hr style='border: 0; border-top: 1px solid #102E88; border-bottom: 1px solid #333; height:0; margin-bottom:0px; margin-top:0px;'>Seccion(es): ";
//                $.each(arrs, function (j) {
//                    actv += arrs[j] + "<br>";
//                });
//                actv += "<br><br><hr style='border: 0; border-top: 1px solid #102E88; border-bottom: 1px solid #333; height:0; margin-bottom:0px; margin-top:0px;'>" + "Inicio de sesion   " + obj.isesion + "\n\
//               <br><br><hr style='border: 0; border-top: 1px solid #102E88; border-bottom: 1px solid #333; height:0; margin-bottom:0px; margin-top:0px;'>" + "Inicio de Proceso   " + obj.iproceso + "\n\
//               <div id='l'style=' width: 190px; border-color: #ccc;'><br><hr style='border: 0; border-top: 1px solid #102E88; border-bottom: 1px solid #333; height:0; margin-bottom:0px; margin-top:0px;'>";
//                $.each(arrt, function (i) {
//                    actv += arrt[i] + "<hr style='border: 0; border-top: 1px solid #102E88; border-bottom: 1px solid #333; height:0; margin-bottom:0px; margin-top:0px;'><br>";
//                });
//                "</div><div>";
//            }
//        });
//        $("#actividades").css("display", "inline");
//        $("#actividades").html(actv);
//    });


});







        