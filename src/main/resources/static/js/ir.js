
var F;
$(document).ready(function () {

    //var datos;  
    var demole = 0;
    var datos;
    var datosGrafico;
    var totalTiquetesEnEspera = 0; // Variable global para acumular los tiquetes

    function init() {
        //inicio del json
        //$('#flotar').css('visibility','hidden');
        var unidadJ = $('#cunidadRecepId').val();
        var json = {"unidad": unidadJ};
        var urllx = window.location.protocol + "//" + window.location.host + "/colas/cdservicio/getall?unidad=" + unidadJ;
        // alert(JSON.stringify(json));
        $.ajax({
            url: urllx,
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                if (isEmptyObject(data)) {
                    mostrarAlertaError("Error", "Error sumo y profundo", "error");

                } else {
                    //alert(JSON.stringify(data));
                    datos = data;
                    setPromedioEspera(datos.promedioEspera);
                    setAtendidos(datos.totalTiquetes);
                    // Accede al objeto topTramites
                    var topTramites = data.topTramites;
                    if (topTramites && topTramites.length > 0) {
                        for (var i = 0; i < topTramites.length && i < 3; i++) {
                            $('#name_tra' + (i + 1)).text(topTramites[i][0]); // Asegúrate de que `nombre` sea un campo en tu objeto
                            $('#cant_tra' + (i + 1)).text(topTramites[i][1]); // Asegúrate de que `porcentaje` sea un campo en tu objeto
                        }
                    } else {
                        // Manejo de caso en que no haya trámites
                        for (var i = 0; i < 3; i++) {
                            $('#name_tra' + (i + 1)).text('Sin datos');
                            $('#cant_tra' + (i + 1)).text('-');
                        }
                    }
                    
                    
                    // Accede al objeto topTramites2
                    var topTramites2 = data.ultimosTramites;
                    if (topTramites2 && topTramites2.length > 0) {
                        for (var i = 0; i < topTramites2.length && i < 3; i++) {
                            $('#name_tra_' + (i + 1)).text(topTramites2[i]); // Asegúrate de que `nombre` sea un campo en tu objeto
                        }
                    } else {
                        // Manejo de caso en que no haya trámites
                        for (var i = 0; i < 3; i++) {
                            $('#name_tra_' + (i + 1)).text('Sin datos');
                        }
                    }
                    
                    console.log("DATOS SEC: ", datos);
                    $("#box2").html("");
                    $(".list").html("");
                    $('#').html(0);
                    $('#ncolas').html(0);
                    $("#probar2").html("");
                    $("#tabsId").html("");
                    $('#titllece').html("<b>" + $("#cunidadRecepId option:selected").html() + "</b>");
                    if (typeof datos.saturacion === "undefined") {
                        $('#titlepor').html("--");
                    } else
                        $('#titlepor').html(datos.saturacion + "%");
                    var selected = $('#cunidadRecepId').val();
                    ;
                    var centros = "";
                    var usr = "";
                    var name = "";
                    var progres = "";
                    var proges = "";
                    var name = "";

                    if (parseInt(datos.saturacion) <= (50)) {
                        progres += "<div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='20' aria-valuemin='0' aria-valuemax='100' style='width: " + datos.saturacion + "%;  heigth:10px;'>";

                    } else if (parseInt(datos.saturacion) <= (75)) {
                        progres += "<div class='progress-bar progress-bar-info ' role='progressbar' aria-valuenow='20' aria-valuemin='0' aria-valuemax='100' style='width: " + datos.saturacion + "%; heigth:10px;'>";

                    } else if (parseInt(datos.saturacion) < (100)) {
                        progres += "<div class='progress-bar progress-bar-warning ' role='progressbar' aria-valuenow='20' aria-valuemin='0' aria-valuemax='100' style='width: " + datos.saturacion + "%; heigth:10px;'>";
                    } else {
                        progres += "<div class='progress-bar progress-bar-danger' role='progressbar' aria-valuenow='20' aria-valuemin='0' aria-valuemax='100' style='width: " + datos.saturacion + "%; heigth:10px;'>";
                    }
                    $("#probar1").html(progres);

                    var num = 1;
                    try {
                        $.each(datos.monitoreoZonasPojo, function (idx2, obj2) {
                            if (parseInt(selected) === parseInt(datos.csId)) {
                                centros += "<li><a id=lin" + num + " data-toggle='tab' style='height:32px' href='#" + obj2.zonaId + "'>" + obj2.nombre + "</a></li>";
                            }
                            num++;
                        });
                        if (centros === "") {
                            centros = "<h3>No hay Zonas</h3>"
                        }
                        $("#tabsId").html(centros);
                    } catch (e) {

                    }
                    $("#containerbox").on("click", "ul li a", function () {
                        $('#divsecci').val("" + $(this).attr('id'));
                        $("#box2").html("");
                        $(".list").html("");
                        var s = $(this).attr("href");
                        var selected = $(this).attr("href").replace("#", "0");
                        ;
                        var usr = "";
                        var proges = "";
                        var name = "";
                        var nusu = 0;
                        var tmt = "";
                        var tdat = "";
                        var ncol = 0;
                        try {
                            $.each(datos.monitoreoZonasPojo, function (idx, obj2) {
                                /*Barra de Progreso por Zona*/
                                if (parseInt(selected) === parseInt(obj2.zonaId)) {
                                    if (parseInt(obj2.saturacion) <= (50)) {
                                        proges += "<div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width: " + obj2.saturacion + "%;'>";

                                    } else if (parseInt(obj2.saturacion) <= (75)) {
                                        proges += "<div class='progress-bar progress-bar-info ' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width: " + obj2.saturacion + "%;'>";

                                    } else if (parseInt(obj2.saturacion) < (100)) {
                                        proges += "<div class='progress-bar progress-bar-warning ' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width: " + obj2.saturacion + "%;'>";
                                    } else {
                                        proges += "<div class='progress-bar progress-bar-danger' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width: " + obj2.saturacion + "%;'>";
                                    }
                                    $("#probar2").html(proges);
                                    $("#porcizoni").html(obj2.saturacion + "%");
                                }

                                //$.each(obj,function(idx2,obj2){       
                                /*Usuarios por zonas*/
                                var options = {valueNames: ['name', 'escritorio', 'operador', 'tramite', 'autoUpdate']};
                                var userList = new List('users', options);

                                $.each(obj2.monitoreoUsuarioPojo, function (idx3, obj3) {
                                    if (parseInt(selected) === parseInt(obj2.zonaId)) {
                                        //  alert( "Size: " + $(parseInt(obj3.nombre)).size() );
                                        // $(document).ready(function  () {
                                        nusu++;
                                        usr += "<li>";
                                        usr += "<div id='ubox'>"
                                        usr += "<div id='foto'><img src='/colas/images/1.png' alt='usuario' width='100' height='100'/><div id='info'>" + "<option class='name'>" + obj3.nombre + "</option>" + "<option class='escritorio'>" + "escritorio:   " + obj3.escritorio + "</option>" + "<option  class='operador'>" + "Operador:   " + obj3.rol + "</option>" + "<option  class='tramite'>" + "Tramite:   " + obj3.tramites + "</option>";
                                        $('#checkbox1').change(function () {
                                            if (!this.checked)
                                                $('.autoUpdate').fadeIn('slow');
                                            else
                                                $('.autoUpdate').fadeOut('slow');
                                        });
                                        var color = "degradadoGris";
                                        if (obj3.estado.toUpperCase() === 'INACTIVO') {
                                            color = "degradadoGris";
                                        } else if (obj3.estado.toUpperCase() === 'RECESO' || obj3.estado.toUpperCase() === 'PAUSA' || obj3.estado.toUpperCase() === 'ALMUERZO') {
                                            color = "degradadoRojo";
                                        } else {
                                            color = "degradadoVerde";
                                        }
                                        usr += "<div  class='autoUpdate' style='overflow-y: hidden; height: 20px;'> " + obj3.secciones + "</div></div></div><div id='est2'><div id='estado' title='" + obj3.estado.toUpperCase() + "' class='" + color + "' ></div></div>";
                                        usr += "</div></div>";
                                        usr += "</li>";

                                        // });  //ready              
                                    }
                                });

                                $(".list").html(usr);
                                $('#nusuarios').html(nusu);

                                /*Cambio de Tramite por Zona*/

                                $.each(obj2.monitoreoColasPojo, function (idx, obj3) {
                                    // $.each(obj.nZonaId.Ptramites,function(idx4, obj4){
                                    if (parseInt(selected) === parseInt(obj2.zonaId)) {
                                        tmt += "<div id='tbox'>" + "<option value='" + obj3.tramite + "'>" + obj3.tramite + "&nbsp;&nbsp;&nbsp;" + obj3.saturacion + "%</option>"

                                        if (parseInt(obj3.saturacion) <= (50)) {
                                            tmt += "<div class='progress'  style='height:10px; '><div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width: " + obj3.saturacion + "%;   height:10px;'></div></div>"

                                        } else if (parseInt(obj3.saturacion) <= (75)) {
                                            tmt += "<div class='progress' style='height:10px; '><div class='progress-bar progress-bar-info' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width: " + obj3.saturacion + "%;   height:10px;'></div></div>"

                                        } else if (parseInt(obj3.saturacion) < (100)) {
                                            tmt += "<div class='progress' style='height:10px; '><div class='progress-bar progress-bar-warning' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width: " + obj3.saturacion + "%;  height:10px;'></div></div>"

                                        } else {
                                            tmt += "<div class='progress' style='height:10px; '><div class='progress-bar progress-bar-danger' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' style='width: " + obj3.saturacion + "%; height:10px;'></div></div>"

                                        }
                                        tmt += "<option value='" + obj3.tramite + "'>" + "Total en Espera:  " + obj3.totEspera + "</option>" + "<option value='" + obj3.tramite + "'>" + "Total Procesados:  " + obj3.totProcesados + "</option>" + "<option value='" + obj3.tramite + "'>" + "Tiquetes Anulados:  " + obj3.totAnulados + "</option>" + "<option value='" + obj3.tramite + "'>" + "Procesando:  " + obj3.tiquetes + "</option>" + "</div>";
                                        ncol++;
                                    }
                                    //});
                                });


                                $("#box2").html(tmt);
                                $('#ncolas').html(ncol);
                                $(".progress").css("margin-bottom", "5px");




                            });
                        } catch (e) {

                        }
                    });


                }
                //click a una zona
                var zn = $('#divsecci').val();
                var lazn = "";
                if (zn == "") {
                    $("#lin1").trigger("click");
                    $('#divsecci').val("lin1");
                } else {
                    lazn = "#" + zn;
                    $(lazn).trigger("click");
                }
                setTimeout(datosGR, 500);
                F = setTimeout(procesar, 300000);
            },
            error: function (data, status, er) {
                alert("error: " + JSON.stringify(data) + " status: " + status + " er:" + er);
            }
        });
        //fin de json

    } //init
    $("#cunidadRecepId").change(function () {
        clearTimeout(F);
        procesar();
    });
    $("#reffrescar").on("click", function () {
        clearTimeout(F);
        procesar();
    });
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
    function datosGR() {
        //$('#flotar').css('visibility','hidden');
        var unidadJ = $('#cunidadRecepId').val();
        var json = {"unidad": unidadJ};
        var urlx = window.location.protocol + "//" + window.location.host + "/colas/cdservicio/getGrafica?unidad=" + unidadJ;
        // alert(JSON.stringify(json));
        $.ajax({
            url: urlx,
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                if (isEmptyObject(data)) {
                    mostrarAlertaError("Error", "Error sumo y profundo", "error");

                } else {
                    //alert(JSON.stringify(data));
                    datosGrafico = data;
                    dibujar(true);
                }
            },
            error: function (data, status, er) {

            }
        });
    }
    function procesar() {
        init();
    }
    $(function () {
        $("#flotar").draggable();
    });

    setTimeout(procesar, 500);

    // Función para establecer la cantidad de tiquetes en espera
    function setTiquetesEnEspera(cantidad) {
        document.getElementById('tiq_espera').textContent = cantidad;
    }

    // Función para establecer el promedio de espera
    function setPromedioEspera(promedio, promedio2) {
        document.getElementById('promedio').textContent = promedio + " min";
    }

    // Función para establecer el promedio de espera
    function setAtendidos(cant) {
        document.getElementById('atendidos').textContent = cant;
    }


    function dibujar(xlabel) {
        totalTiquetesEnEspera = 0; // Reiniciar el total al inicio de la función

        //Colocar 
        //correccion de parametro
        var t = parseInt($('#divtam').val());
        if (t == 1)
            xlabel = true;
        else
            xlabel = true;
        $('#flotar').css('visibility', 'visible');
        var unidadJ = $('#cunidadRecepId option:selected').text();
        var titleFix = "Flujo de Tramites";
        var CList = new Array();
        var NList = new Array();
        var XList;
        var SList = new Array();
        var totLin = datosGrafico.length;
        var posAntes = 0;
        var letitle = "";
        $.each(datosGrafico, function (idx, obj) {
            CList.push(obj.scNombre);
            NList.push(".");
            $.each(obj.tramites, function (idx, obj1) {
                var singleObj = {};
                var auxList = new Array();
                singleObj['name'] = obj1.tramite;
                // Sumar los tiquetes en espera a la variable global
                totalTiquetesEnEspera += obj1.esperando;
                for (i = 0; i < posAntes; i++) {
                    auxList.push(0);
                }
                auxList.push(obj1.esperando);
                //auxList.push(graF(1,10));
                for (i = posAntes + 1; i < totLin; i++) {
                    auxList.push(0);
                }
                singleObj['data'] = auxList;
                SList.push(singleObj);
            });
            posAntes++;
        });
        setTiquetesEnEspera(totalTiquetesEnEspera);

        if (xlabel) {
            XList = CList;
            letitle = titleFix;
        } else {
            XList = NList;
            letitle = "";
        }

        $('#grafoC').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: letitle
            },
            xAxis: {
                enabled: false,
                categories: XList
            },
            yAxis: {
                allowDecimals: false,
                min: 0,
                title: {
                    text: 'Tiquetes en espera'
                },
                stackLabels: {
                    enabled: true,
                    style: {
                        fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    }
                }
            },
            legend: {
                enabled: false,
                align: 'right',
                x: -30,
                verticalAlign: 'top',
                y: 25,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: true
            },
            tooltip: {
                headerFormat: '<b>{point.x}</b><br/>',
                pointFormat: '{series.name}: {point.y} Esperando<br/>Total Secci\u00F3n: {point.stackTotal} En Espera'
            },
            plotOptions: {
                column: {
                    stacking: 'normal',
                    dataLabels: {
                        enabled: false,
                        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white',
                        style: {
                            textShadow: '0 0 3px black'
                        }
                    }
                }
            },
            series: SList
        });
        $('.highcharts-button').css('visibility', 'hidden');
    }
    function graF(min, max) {
        return Math.floor(Math.random() * (max - min)) + min;
    }
    $('#spaID').on('click', function () {
        /*var t=parseInt($('#divtam').val());
         if (t==0){
         $('#flotar').css("width",600);
         $('#flotar').css("height",500)
         $('#grafoC').css("width",580);
         $('#grafoC').css("height",480)       
         $('#divtam').val(1)
         dibujar(true);      
         } else{
         $('#flotar').css("width",200);
         $('#flotar').css("height",200)
         $('#grafoC').css("width",180);
         $('#grafoC').css("height",180)
         $('#divtam').val(0)
         dibujar(false);
         }*/
    });

});




