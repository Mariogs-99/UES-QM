var bancos = {
        bnc24:"Hipotecario",
        bnc19:"Citi Bank El Salvador",
        bnc18:"Banco Agr&iacute;cola",
        bnc21:"DAVIVIENDA",
        bnc29:"Prom&eacute;rica",
        bnc22:"ScotiaBank",
        bnc23:"Am&eacute;rica Central",
        bnc30:"G&T Continental El Salvador",
        bnc31:"Banco Industrial",
        bnc25:"NCTP"
};

function prepareBancos(){
    $(".bncSpn").text("ninguno"); 
    $("td.bncs").css({opacity:0.6});
    $("input#nvrbnc_id").click(
        function(){
            if($("#bncfrm input[name=banco]").val().length == 2){
            	$(this).attr("disabled", "disabled");
            	$(this).val("Espere un momento...");
                $("form#bncfrm").submit();
                return true;
            }
            $("#errr").css({display:"block"}).find("p").text("Advertencia:Debe seleccionar un banco");
            return false;
        }
    );
    
    $(".bncs").click(
        function(){
            $("#errr").css({display:"none"});
            $("td.bncs").css({opacity:0.6});
            var codigo = $(this).attr("id").replace("bnc","");            
            $("#bncfrm input[name=banco]").val(codigo);
            $(".bncs").removeClass("bncs-active");
            $(this).addClass("bncs-active");
            $(this).css({opacity:1});
            $("span.bncSpn").html(bancos[$(this).attr("id")]);
        }
    );
}