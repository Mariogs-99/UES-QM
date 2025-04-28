$(document).ready(function () {
    var F;
    var datos;
    $("#tramites").change(function () {
        $("#usuario2").html("");
        var selected = $(this).val();
        var time = "";
        var usr = "";
        var dat = "";
        var gr = "";
        var urll = window.location.protocol + "//" + window.location.host + "/colas/monitoreoT/getMonAll?idT=" + selected;
        //INICIO JSON
        var json = {"idT": selected};
        //alert(JSON.stringify(json));
        $.ajax({
            url: urll, // window.location.protocol + "//" + window.location.host+"/colas/monitoreoT/getMonAll",
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
                    $("#ctittle").html($("#tramites option:selected").text());
                    dat += "<option value='0'>" + "<h4>Total de Personas Atendidas:  </h4><style='color:#ccc'>" + 
                            datos.mst.procesados + "</option>" + "<option value='1'>" + "<h4>Personas Esperando:  </h4>" + 
                            datos.mst.esperando + "</option> " + "<option value='3'>" + "<h4>Usuarios Asignados:  </h4>" + 
                            datos.usuarios.length + "</option>";
                    $(".dat").html(dat);
                            $('#bodymaximo').html("Espera  :   "+datos.maxEspera+"<br>");
                            $('#bodymaximo').append("Llamada  :   "+datos.maxLlamado+"<br>");
                            $('#bodymaximo').append("Proceso  :   "+datos.maxProceso);
                            $('#bodypromedios').html("Espera  :   "+datos.promEspera+"<br>");
                            $('#bodypromedios').append("Llamada  :   "+datos.promLlamado+"<br>");
                            $('#bodypromedios').append("Proceso  :   "+datos.promProceso);
                            $('#bodyminimos').html("Espera  :   "+datos.minEspera+"<br>");
                            $('#bodyminimos').append("Llamada  :   "+datos.minLlamado+"<br>");
                            $('#bodyminimos').append("Proceso  :   "+datos.minProceso);
                            $('#bodymoda').html("Espera  :   "+datos.modaEspera+"<br>");
                            $('#bodymoda').append("Llamada  :   "+datos.modaLlamado+"<br>");
                            $('#bodymoda').append("Proceso  :   "+datos.modaProceso);
                    $.each(datos.usuarios, function (idx3, obj3) {
                        usr += "<div id='ubox'>";
                        usr += "<div id='foto' style='cursor:pointer'><img src='../images/1.png' alt='usuario' width='60' height='55 style='cursor:pointer'/><div id='info' style='font-size:12.5px; color:#fff;''>" + "<option class='name'>" + obj3.usuario + "</option>";
                        var color="degradadoGris";    
                        if (obj3.estado.toUpperCase() === 'INACTIVO'){
                            color = "degradadoGris";
                        }else if(obj3.estado.toUpperCase() === 'RECESO' || obj3.estado.toUpperCase() === 'PAUSA' || obj3.estado.toUpperCase() === 'ALMUERZO'){
                            color = "degradadoRojo";
                        }else{
                            color = "degradadoVerde";
                        }
                        usr += "</div></div><div id='este' style='cursor:pointer'><div id='estado' title='" + obj3.estado.toUpperCase() + "' class='"+color+"' ></div></div></div>";
                    });
                    if (usr === "") {
                        usr = "<h3>No hay Usuarios</h3>";
                    }
                    $("#usuario2").html(usr);
                    var letitle = $("#tramites option:selected").text();
                    var SList = new Array();
                    var singleObj = {};
                    var auxList = new Array(); 
                    auxList.push(datos.mst.hora);
                    auxList.push(datos.mst.min45);
                    auxList.push(datos.mst.min30);
                    auxList.push(datos.mst.min15);
                    auxList.push(datos.mst.actual);
                    singleObj['name'] = letitle;
                    singleObj['data'] = auxList;
                    SList.push(singleObj);
                    $('#grafTr').highcharts({
                        chart: {
                            type: 'area'
                        },
                        title: {
                            text: letitle
                        },
                        xAxis: {
                            categories: ['1 Hora', '45 min', '30 Min', '15 Min', 'Actual']
                        },
                        yAxis: {
                            allowDecimals: false,
                            min: 0,
                            title: {
                                text: 'No Contribuyentes'
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
                            headerFormat: '<b>{series.name}</b><br/>{point.x}: {point.y}',
                            pointFormat: ''
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
                    if (F != null) {
                        clearTimeout(F);
                    }
                    F = setTimeout(cambioC, 120000);
                }
            },
            error: function (data, status, er) {
               
            }
        });
    });
    function cambioC() {
        $("#tramites").trigger("change");
    }
    $('#cambioRe').on('click', function () {
        clearTimeout(F);
        F = setTimeout(cambioC, 120000);
    });
    function graF(min, max) {
        return Math.floor(Math.random() * (max - min)) + min;
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

    $("#tramites").trigger("change");

});
