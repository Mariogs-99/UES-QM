var intervalo;
$(document).ready(function () {
    intervalo= setInterval(function(){ llenar(); },120000);
    llenar();
});


   function llenar() {
       clearInterval(intervalo);
       intervalo= setInterval(function(){ llenar(); },30000);
        $.ajax({
            url: "/colas/seccionm/getall?seccion="+$('#seccion').val(), 
            type: "GET", 
            success: function(datos) {
                console.log("DATOS: ", datos);
                var usr = "";
                var gr1 = "";
                $("#ctittle").html(datos.scNombre);
                $("#trams").html(" ");
                $("#seccionG3").html(datos.scNombre);
                $('#headgra2').html("<th></th>");
                $('#horatra').html("<th>1 Hora</th>");
                $('#min45').html("<th>45 Min</th>");
                $('#min30').html("<th>30 Min</th>");
                $('#min15').html("<th>15 Min</th>");
                $('#esperandoTra').html("<th>Actual</th>");
                $.each(datos.tramites, function (idx3, tramite) {
                    gr1+="<tr><th>"+tramite.tramite+"</th>";
                    gr1+="<td>"+tramite.esperando+"</td></tr>";
                    $('#headgra2').append("<td>"+tramite.tramite+"</td>");
                    $('#horatra').append("<td>"+tramite.hora+"</td>");
                    $('#min45').append("<td>"+tramite.min45+"</td>");
                    $('#min30').append("<td>"+tramite.min30+"</td>");
                    $('#min15').append("<td>"+tramite.min15+"</td>");
                    $('#esperandoTra').append("<td>"+tramite.actual+"</td>");
                    $("#trams").append( '<a href="#" class="list-group-item"><h3 class="list-group-item-heading">'+tramite.tramite+'</h3></a>');
                });
                $("#tramitesG3").html(gr1);
                $('#graf2').highcharts({
                    data:  { table: 'datatableLine'},
                    chart: { type: 'line' },
                    title: { text: datos.scNombre},
                    yAxis: { allowDecimals: false, title: { text: 'Esperando' }},
                    tooltip: {
                        formatter: function () {
                            return '<b>' + this.series.name + '</b><br/>Esperando: ' +
                                this.point.y + ' En ' + this.point.name.toLowerCase();
                        }
                    }
                });
                
                $('#graf3').highcharts({
                    data:  { table: 'datatable'},
                    chart: { type: 'column' },
                    title: { text: datos.scNombre},
                    yAxis: { allowDecimals: false, title: { text: 'Esperando: ' }},
                    tooltip: {
                        formatter: function () {
                            return '<b>' + this.series.name + '</b><br/>Esperando: ' +
                                this.point.y + ' Tramite: ' + this.point.name.toLowerCase();
                        }
                    }
                });
                
                $("#tramitesG3").html(gr1);
                $('#graf2').highcharts({
                    data:  { table: 'datatableLine'},
                    chart: { type: 'line' },
                    title: { text: datos.scNombre},
                    yAxis: { allowDecimals: false, title: { text: 'Esperando' }},
                    tooltip: {
                        formatter: function () {
                            return '<b>' + this.series.name + '</b><br/>Esperando: ' +
                                this.point.y + ' Tiempo: ' + this.point.name.toLowerCase();
                        }
                    }
                });
                
                $("#usersDetalles").html("");
                var countUsers=0;
                try{
                    $.each(datos.usuarios, function (idx3, obj3) {
                        var color="degradadoGris";    
                        if (obj3.estado === 'INACTIVO') {
                                color = "degradadoGris";
                        }else if (obj3.estado === 'Receso' || obj3.estado === 'Pausa' || obj3.estado === 'Almuerzo') {
                            color = "degradadoRojo";
                        }else {
                            color = "degradadoVerde";
                        }
                        usr +=   '<li class="list-group-item" style="background: #777777">'+
                                '<div class="row vertical-align"><div class="col-xs-6 col-md-2">'+
                                '<img src="../images/1.png" width="35" height="30"/></div><div class="col-xs-6 col-md-4">'+
                                '<h3 style="color:#fff;">'+obj3.usuario+'</h3></div>'+
                                '<div id="este" title="'+obj3.estado+'"><div id="estado" class="'+color+'"></div></div>'+
                                '</div></li>';
                        countUsers++;
                    });
                }catch(err){
                    console.log("No Usuarios");
                }
                $("#usersDetalles").append(usr);
                $("#maxim").html("<th scope='row' style='width: 40%'><h3>M&aacute;xima:</h3></th><td><h3><b>"+datos.maximo+"</b></h3></td>");
                $("#minim").html("<th scope='row'><h3>M&iacute;nima:</h3> </th><td><h3><b>"+datos.minimo+"</b></h3></td>");
                $("#promm").html("<th scope=><h3>Promedio:</h3></th><td><h3><b>"+datos.promedio+"</b></h3></td>");
                $("#modda").html("<th scope=><h3>Moda:</h3></th><td><h3><b>"+datos.moda+"</b></h3></td>");
                $("#tespera").html("<th scope=><h3>Total en Espera:</h3></th><td><h3><b>"+datos.esperando+"</b></h3></td>");
                $("#tprocesados").html("<th scope=><h3>Total Procesados:</h3></th><td><h3><b>"+datos.Procesados+"</b></h3></td>");
                $("#tusers").html("<th scope='row'><h3>Total de Usuarios:</h3></th><td><h3><b>"+countUsers+"</b></h3></td>");                
            },
            error: function(resp) {
                alert("Error: "+resp);
            }
        });
    }

		