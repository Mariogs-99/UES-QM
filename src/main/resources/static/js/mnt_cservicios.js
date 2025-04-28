
$(document).ready(function () {


    $.get(window.location.protocol + "//" + window.location.host + "/colas/monitoreo/getListCentroServicios",
            function (data) {

                var listcservicio = "";
                $.each(data, function (idx, obj) {
                    listcservicio += "<option value='" + obj.dlista + "'>" + obj.dlista + "</option>";
                });

                $("#cdservicio1").html(listcservicio);
                $("#cdservicio").html(listcservicio);
                $(".selectpicker").selectpicker();

            }, "json");



});