var URL_NIT	=	"";
var URL_PDF	=	"";
action	=	null;

/**
 * Seccion de balance eliminada para la V11, esta funcion no se utiliza mas.
 */
function showBalance(){
	try{
		var boo	=	false;
		jQuery("#balanceContainer .cf11").each(
			function(index,obj){
				o	=	jQuery(obj);
				if(!isNullOrUndefined(o.attr("id"))){
					var code	=	o.attr("id").replace("_","");
					if(!isNullOrUndefined(f11.get(code))){
						if(f11.get(code).visible){
							boo	=	true;
						}
					}
				}
			}
		);
		if(boo	== true){
			jQuery("a#itemBalance").show();
		}else{
			jQuery("a#itemBalance").hide();
		}
	}catch(e){
		alert("ocultarBalance"+e);
	}
	
}

function showPagoMinimo(){
	try{
		var pm	=	false;
		jQuery("#pagoMinimoContainer .cf11").each(
			function(index,obj){
				o	=	jQuery(obj);
				if(!isNullOrUndefined(o.attr("id"))){
					var code	=	o.attr("id").replace("_","");
					if(!isNullOrUndefined(f11.get(code))){
						if(f11.get(code).visible){
							pm	=	true;
						}
					}
				}
			}
		);
		if(pm == true){
			jQuery("a#itemPagoMinimo").show();
		}else{
			jQuery("a#itemPagoMinimo").hide();
		}
	}catch(e){
		alert("ocultarPagoMinimo"+e);
	}
	
}

function showColCom(){

	return (f11.get("c604").visible
	
	|| (f11.get("c608").visible)
	|| (f11.get("c609").visible));
}
function showColServ(){
	return (f11.get("c622").visible || f11.get("c622").editable) 
	|| (f11.get("c627").visible || f11.get("c627").editable)
	|| (f11.get("c626").visible || f11.get("c626").editable);
}
function showColAgro(){
	return ((f11.get("c613").visible || f11.get("c613").editable)
	|| (f11.get("c617").visible || f11.get("c617").editable)
	|| (f11.get("c618").visible || f11.get("c618").editable));
}

function showColIndustrial(){

	return (f11.get("c405").visible || f11.get("c405").editable)
	|| (f11.get("c410").visible || f11.get("c410").editable)
	|| (f11.get("c415").visible || f11.get("c415").editable)
	|| (f11.get("c425").visible || f11.get("c425").editable)
	|| (f11.get("c430").visible || f11.get("c430").editable)
	|| (f11.get("c435").visible || f11.get("c435").editable)
	|| (f11.get("c440").visible || f11.get("c440").editable)
	|| (f11.get("c444").visible || f11.get("c444").editable)
	|| (f11.get("c445").visible || f11.get("c445").editable);
}

function showHideIncomesList(){
	// no acreditables
	if(jQuery("table#incomesR2 tbody tr").length>0){
		jQuery("div#incomesR2Container").show();
	}else{
		jQuery("div#incomesR2Container").hide();
	}
	//salarios
	if(jQuery("table#incomesNR60 tbody tr").length>0){
		jQuery("div#incomesNR60Container").show();
	}else{
		jQuery("div#incomesNR60Container").hide();
	}
	//no gravados
	if(jQuery("table#incomesNR602 tbody tr").length>0){
		jQuery("div#incomesNR602Container").show();
	}else{
		jQuery("div#incomesNR602Container").hide();
	}
	
	
	
	if(jQuery("table#incomesNR tbody tr").length>0){
		
		jQuery("div#incomesNRContainer").show();
		
	}else{
		jQuery("div#incomesNRContainer").hide();
	}
	jQuery("div#expensesContainer").hide();
	jQuery(".col-agro, .col-serv, .col-com").hide();
	if(showColAgro() || showColServ() || showColCom()){
		jQuery("div#expensesContainer").show();
		jQuery("#pago_minimo_label").hide();
		
		if(showColAgro()){
			jQuery(".col-agro").show();
            //solucion temporal, ocultar casilla de pago minimo
			jQuery("#c_753").hide();
		}
		if(showColServ()){
			jQuery(".col-serv").show();
			jQuery("#c_754").hide();
		}
		if(showColCom()){
			jQuery(".col-com").show();
			jQuery("#c_752").hide();
		}
	}
	if(showColIndustrial()==true){
		jQuery("div#industrialContainer").show();
	}else{
		jQuery("div#industrialContainer").hide();
	}
	
	if(jQuery("table#incomesR tbody tr").length>0){
		jQuery("div#incomesRContainer").show();
		
	}else{
		jQuery("div#incomesRContainer").hide();
	}
	
}
function incomeRExists(_nit, _incomeCode){
	try{
		var r	=	false;
		
		for(var i=0; i<f11.retenciones.length; i++){
			ret	=	f11.retenciones[i];
			if(ret.borrar)continue;
			if(!isNullOrUndefined(ret.c805) && !isNullOrUndefined(ret.c810)){
				if(ret.c810.valor==_nit && ret.c805.valor == _incomeCode){
					r = true;
				}
			}
			
		}
		return r;
	}catch(e){
		error("incomeRExists"+e);
		return true;
	}
	
}

function incomeNGExists(_nit, _incomeCode){
	try{
		var r	=	false;
		
		for(var i=0; i<f11.ingresosNoGravados.length; i++){
			ret	=	f11.ingresosNoGravados[i];
			//if(ret.borrar)continue;
			if(!isNullOrUndefined(ret.c805) && !isNullOrUndefined(ret.c810)){
				if(ret.c810.valor==_nit && ret.c805.valor == _incomeCode){
					r = true;
				}
			}
			
		}
		return r;
	}catch(e){
		error("incomeNGExists"+e);
		return true;
	}
	
}

var mS	=	-1;
function msg(_msg){
	jQuery("html, body").animate({scrollTop:jQuery("a#anclaTop").offset().top}, 500);
	jQuery("#errors").html(_msg).show();
	if(mS>0){
		clearInterval(mS);
	}
	mS	=	setTimeout(function(){jQuery("#errors").hide();}, 10000);
}

var eR	=	-1;
function error(_msg){
	jQuery("html, body").animate({scrollTop:jQuery("a#anclaTop").offset().top}, 500);
	jQuery("#catch").html(_msg);
	jQuery("#catch").parents("div.errorsDiv:first").show();
	if(eR>0){
		clearInterval(eR);
	}
	eR = setTimeout(function(){jQuery("div.errorsDiv:first").hide();}, 3000);
}

var mR	=	-1;
function msgR(_msg){
	jQuery("#addingIncomeR-messages p").html(_msg);
	jQuery("#addingIncomeR-messages").show();
	if(mR>0){
		clearInterval(mR);
	}
	mR	=	setTimeout(function(){jQuery("#addingIncomeR-messages").hide();}, 5000);
}

var mRNR60	=	-1;
function msgRNR60(_msg){
	jQuery("#addingIncomeRNR60-messages p").html(_msg);
	jQuery("#addingIncomeRNR60-messages").show();
	if(mRNR60>0){
		clearInterval(mRNR60);
	}
	mRNR60	=	setTimeout(function(){jQuery("#addingIncomeRNR60-messages").hide();}, 5000);
}

function createIncomeRowNR(labelIncomeR, gR, codeIncome,removable){
	var	tr				=	jQuery("<tr>");
	var divLabelIncome	=	jQuery("<div>",{"class":"r cell"}).append(jQuery("<p>").text(labelIncomeR));
	var td1				=	jQuery("<td>").append(divLabelIncome);
	var td2				=	jQuery("<td>",{"class":"numeric"}).html("<p>"+formatearMoneda(parseFloat(gR).toFixed(2))+"</p>");
	var td3				=	jQuery("<td>",{"class":"numeric"}).html("<p></p>");
	var td4				=	jQuery("<td>",{"class":"action"});
	if(removable == true){
		var	deleteButton	=	jQuery("<a>",{"class":"command delete", "id":"a"+codeIncome});
		deleteButton.click(
			function(e){
				e.preventDefault();
				if(jQuery(this).attr("id")!=null) {
					codeIncomeNR	=	jQuery(this).attr("id").replace("a","");
					if(incomesNR[codeIncomeNR]	!=	null && incomesNR[codeIncomeNR] != undefined){
						jQuery("#addingIncomesNR input.inputText[name="+codeIncomeNR+"]").val(0.0);
						jQuery(this).parents("tr:first").remove();
						f11.set(codeIncomeNR, 0.0);
						calculator.calcular(f11,true);
						setDatas();
						showHideIncomesList();
					}
				}
			}
		);
		td4.append(deleteButton);
	}
	tr.append(td1)
		.append(td2)
		.append(td3)
		.append(td4);
	return tr;
}

function addIncomeRowsNR(){
	try{
		
		var labelIncomeNR	=	null;
		var devengado		=	0.00;
		jQuery("table#incomesNR tbody tr").remove();
		
		for(p in incomesNR){			
			f11.set(p,0.0);
		}
		jQuery("#addingIncomesNR .inputText").each(
			function(index, object){
				obj	=	jQuery(object);
				if(isNaN(obj.val())){
					return;
				}
				if(parseFloat(obj.val())<=0){
					return;
				}
				if(obj.attr("name")!=null){
					if(incomesNR[obj.attr("name")]!=null){
						
						labelIncomeNR	=	incomesNR[obj.attr("name")];
						devengado		=	parseFloat(obj.val()).toFixed(2);
						tr				=	createIncomeRowNR(labelIncomeNR, devengado, obj.attr("name"), true);
						f11.set(obj.attr("name"), parseFloat(obj.val()));
						jQuery("table#incomesNR tbody").append(tr);
											
					}
				}
			}
		);
		calculator.calcular(f11,true);
		setDatas();
		return true;
	}catch(e){
		
		error("addIncomeRowsNR : "+e);
		return false;
	}
	
	
}


function createIncomeRowR(nit, agentName, labelIncomeR, gR, rR, removable, id){
	var	tr				=	jQuery("<tr>",{"id":id});
	var divNit			=	jQuery("<div>",{"class":"r cell nit"}).append(jQuery("<p>").text(nit));
	var divAgent		=	jQuery("<div>",{"class":"r cell agent"}).append(jQuery("<p>").text(agentName));
	var divLabelIncome	=	jQuery("<div>",{"class":"r cell incomeCode"}).append(jQuery("<p>").text(labelIncomeR));
	var td1				=	jQuery("<td>").append(divLabelIncome)
							.append(divAgent)
							.append(divNit);
	var td2				=	jQuery("<td>",{"class":"numeric"}).html("<p>"+formatearMoneda(parseFloat(gR).toFixed(2))+"</p>");
	var td3				=	jQuery("<td>",{"class":"numeric"}).html("<p>"+formatearMoneda(parseFloat(rR).toFixed(2))+"</p>");
	var td4				=	jQuery("<td>",{"class":"action"});
	if(removable == true){
		var	deleteButton	=	jQuery("<a>",{"class":"command delete", "href":"#"});
		deleteButton.click(
			function(e){
				e.preventDefault();
				var tr	=	jQuery(this).parents("tr:first");
				if(isNumeric(tr.attr("id"))){
					f11.removeIngresoConRetencion(parseInt(tr.attr("id")));
					tr.remove();
					calculator.calcular(f11, true);
					setDatas();
				}
				
				showHideIncomesList();
			}
		);
		td4.append(deleteButton);
	}
	
	tr.append(td1)
		.append(td2)
		.append(td3)
		.append(td4);
	
	
	return tr;
}



function addIncomeRowR(){
	try{
		
		if(DATAS.newR.nit==null || 
		   DATAS.newR.nombreAgente==null ){
			msgR("datos insuficientes");
			return false;
		}
			
		
		var agentName		=	DATAS.newR.nombreAgente;
		var codeIncomeR		=	jQuery("select[name=codeIncomeR]").val();
		var devengado				=	isNumeric(jQuery("input[name=gR]").val())?parseFloat(jQuery("input[name=gR]").val()):0.0;
		var retenido				=	isNumeric(jQuery("input[name=rR]").val())?parseFloat(jQuery("input[name=rR]").val()):0.0;
		var porcentaje		=	codeIncomeR == "01" ? Porcentajes.retenido01 : Porcentajes.retenido;
		if(!(devengado*retenido>0 && retenido<=devengado*porcentaje)) {msgR("valores no cumplen");return false;}
		if(incomeRExists(DATAS.newR.nit, codeIncomeR)){msgR("ingreso existe "+codeIncomeR+" nit:"+DATAS.newR.nit); return false;}
		if(codeIncomeR=="01"){
			if(incomeRExists(DATAS.newR.nit, "60")){msgR("Ya existe un registro con &eacute;ste c&oacute;digo y nit"); return false;}
		}
		if(codeIncomeR=="60"){
			if(incomeRExists(DATAS.newR.nit, "01")){msgR("Ya existe un registro con &eacute;ste c&oacute;digo y nit"); return false;}
		}
		
		devengado	=	parseFloat(devengado).toFixed(2);
		retenido	=	parseFloat(retenido).toFixed(2);
		
		
		ret	=	f11.addIngresoConRetencion(DATAS.newR.nit,agentName,codeIncomeR,devengado, retenido, DATAS.newR.isBienestar, DATAS.newR.isIpsfa);
		tr	=	createIncomeRowR(ret.c810.valor, agentName, ""+codeIncomeR+"-"+getCodeLabel(codeIncomeR), devengado, retenido, true, ret.id);
		if(incomeType == incomeTypes.ACREDITABLE){
			jQuery("table#incomesR tbody").append(tr);
		}else if(incomeType == incomeTypes.NONACREDITABLE){
			jQuery("table#incomesR2 tbody").append(tr);
		}
		//f11.update("711",0.0,true,true);
		//f11.update("712",0.0,true,true);
		calculator.calcular(f11,false);
		setDatas();
		return true;
	}catch(e){
		error("addIncomeRowR : "+e);
		return false;
	}
	
	
}

function addIncomeRowNR60(){
	try{
		
		if(DATAS.newR.nit==null || 
		   DATAS.newR.nombreAgente==null ){
			msg("datos insuficientes");
			return false;
		}
			
		
		var agentName		=	DATAS.newR.nombreAgente;
		var incomeCode		=	jQuery("select[name=codeIncomeNR60]").val();
		var devengado				=	isNumeric(jQuery("input[name=gRNR60]").val())?parseFloat(jQuery("input[name=gRNR60]").val()):0.0;
		if(devengado<=0) {msgRNR60("valores no cumplen");return false;}
		
		devengado	=	parseFloat(devengado).toFixed(2);
		if(incomeType==incomeTypes.SALARY){
			if(incomeRExists(DATAS.newR.nit, "60")){msgRNR60("ingreso existe 60 con nit: "+DATAS.newR.nit); return false;}
			if(incomeRExists(DATAS.newR.nit, "01")){msgRNR60("codigo 01 ya fue ingresado"); return false;}
			ret	=	f11.addIngresoConRetencion(DATAS.newR.nit,agentName,"60",devengado, 0, DATAS.newR.isBienestar, DATAS.newR.isIpsfa);
			tr	=	createIncomeRowR(ret.c810.valor, agentName, "60-"+ getCodeLabel("60"), devengado, "",true, ret.id);
			jQuery("table#incomesNR60 tbody").append(tr);
		}
		if(incomeType==incomeTypes.NONTAXEABLE){
			if(incomeNGExists(DATAS.newR.nit, incomeCode)){msgRNR60("ingreso existe "+incomeCode+" con nit "+DATAS.newR.nit); return false;}
			ret	=	f11.addIngresoConRetencion(DATAS.newR.nit,agentName,incomeCode,devengado,0,DATAS.newR.isBienestar, DATAS.newR.isIpsfa);
			tr	=	createRowNoGravada(ret.c810.valor, agentName,  ""+incomeCode+"-"+getCodeLabel(incomeCode), devengado, true, ret.id);
			jQuery("table#incomesNR602 tbody").append(tr);
		}
		
		calculator.calcular(f11,true);
		setDatas();
		return true;
	}catch(e){
		error("addIncomeRowRNR60 : "+ e);
		return false;
	}
	
	
}



function createRowNoGravada(nit, agentName, labelIncome, monto,removable, id){
	var	tr				=	jQuery("<tr>",{"id":id});
	var divNit			=	jQuery("<div>",{"class":"r cell nit"}).append(jQuery("<p>").text(nit));
	var divAgent		=	jQuery("<div>",{"class":"r cell agent"}).append(jQuery("<p>").text(agentName));
	var divLabelIncome	=	jQuery("<div>",{"class":"r cell incomeCode"}).append(jQuery("<p>").text(labelIncome));
	var td1				=	jQuery("<td>").append(divLabelIncome)
							.append(divAgent)
							.append(divNit);
	var td2				=	jQuery("<td>",{"class":"numeric"}).html("<p>"+formatearMoneda(monto)+"</p>");
	var td3				=	jQuery("<td>",{"class":"numeric"}).html("<p>&nbsp;</p>");
	var td4				=	jQuery("<td>",{"class":"action"});
	if(removable == true){
		var	deleteButton	=	jQuery("<a>",{"class":"command delete", "href":"#"});
		deleteButton.click(
			function(e){
				e.preventDefault();
				var tr	=	jQuery(this).parents("tr:first");
				if(isNumeric(tr.attr("id"))){
					f11.removeIngresoConRetencion(parseInt(tr.attr("id")));
					tr.remove();
					calculator.calcular(f11,true);
					setDatas();
				}
				
				showHideIncomesList();
				
			}
		);
		td4.append(deleteButton);
	}
	
	tr.append(td1)
		.append(td2)
		.append(td3)
		.append(td4);
	
	
	return tr;
}



function getCodeLabel(codeIncome){
	for(var i=0; i<codigosIngreso.length; i++){
		if(codeIncome==codigosIngreso[i].CIngreso){
			return codigosIngreso[i].DIngreso;
		}
	}
	return "[desconocido]";
}

function createRowDonaciones(nit, nombre, valor){
	tr	=	jQuery("<tr>");
	td1	=	jQuery("<td>")
			.append(jQuery("<p>").html(nombre))
			.append(jQuery("<span>").text(nit));
	td2	=	jQuery("<td>", {"class": "numeric"})
			.append(jQuery("<p>").text(valor));
	tr.append(td1)
		.append(td2);
	return tr;
}

function inArray(array, code){
	for(var i=0; i<array.length; i++){
		if(array[i]==code){
			return true;
		}
	}
	return false;
}

function printDatas(){
	try{
		jQuery("table#incomesR tbody tr").remove();
		jQuery("table#incomesNR60 tbody tr").remove();
		jQuery("table#incomesR2 tbody tr").remove();
		jQuery("table#incomesNR602 tbody tr").remove();
		jQuery("table#incomesNR tbody tr").remove();
		for(var i=0; i<f11.retenciones.length; i++){
			if(!f11.retenciones[i].borrar){
			var acreditableCodes = getAcreditableCodes(f11.tipoContribuyente == "J");
			if(inArray(acreditableCodes, f11.retenciones[i].c805.valor) ||  f11.retenciones[i].c805.valor=="60"){
				trR	=createIncomeRowR(f11.retenciones[i].c810.valor, 
						f11.retenciones[i].nombre, 
						f11.retenciones[i].c805.valor+"-"+getCodeLabel(f11.retenciones[i].c805.valor), 
						f11.retenciones[i].c815.valor,
						f11.retenciones[i].c825.valor,
						f11.retenciones[i].nueva,//cambiado de false a true por Hugo
						f11.retenciones[i].id==""?(new Date().getTime())+"":f11.retenciones[i].id);
				if(  f11.retenciones[i].c805.valor=="60"){
					jQuery("table#incomesNR60 tbody").append(trR);
				}else{
					jQuery("table#incomesR tbody").append(trR);
				}
				
			}
			var nonAcreditableCodes = getNonAcreditableCodes(f11.tipoContribuyente == "J", f11.domiciliado);
			if(inArray(nonAcreditableCodes, f11.retenciones[i].c805.valor)){
				trR	=createIncomeRowR(f11.retenciones[i].c810.valor, 
						f11.retenciones[i].nombre, 
						f11.retenciones[i].c805.valor+"-"+getCodeLabel(f11.retenciones[i].c805.valor), 
						f11.retenciones[i].c815.valor,
						f11.retenciones[i].c825.valor,
						f11.retenciones[i].nueva,//cambiado de false a true por Hugo
						f11.retenciones[i].id==""?(new Date().getTime())+"":f11.retenciones[i].id);
				jQuery("table#incomesR2 tbody").append(trR);
			}
			var nonTaxableCodes = getNonTaxableCodes(f11.tipoContribuyente == "J");
			if(inArray(nonTaxableCodes, f11.retenciones[i].c805.valor)){
				trR	=	createRowNoGravada(f11.retenciones[i].c810.valor, 
						f11.retenciones[i].nombre, 
						f11.retenciones[i].c805.valor+"-"+getCodeLabel(f11.retenciones[i].c805.valor), 
						f11.retenciones[i].c815.valor,
						f11.retenciones[i].nueva,//cambiado de false a true por Hugo
						f11.retenciones[i].id==""?(new Date().getTime())+"":f11.retenciones[i].id);
				jQuery("table#incomesNR602 tbody").append(trR);
			}
		  }// end if borrar retencion
		}//end for retenciones
		
		for(var i=0; i<f11.ingresosNoGravados.length; i++){
			trR	=	createRowNoGravada(f11.ingresosNoGravados[i].c810.valor, 
					f11.ingresosNoGravados[i].nombre, 
					f11.ingresosNoGravados[i].c805.valor+"-"+getCodeLabel(f11.ingresosNoGravados[i].c805.valor), 
					f11.ingresosNoGravados[i].c815.valor,
					f11.retenciones[i].nueva,//cambiado de false a true por Hugo 
					(new Date().getTime())+"");
			jQuery("table#incomesNR602 tbody").append(trR);
		}
		if(f11.donaciones.length>0){
			for(var i=0; i<f11.donaciones.length; i++){
				trD	=createRowDonaciones(f11.donaciones[i].c905.valor, 
									f11.donaciones[i].nombre,
									f11.donaciones[i].c910.valor);
				jQuery("#donationsDetail table.list:first tbody").append(trD);
			}
		}else{
			jQuery("a#showDonationDetail").remove();
		}
		
		jQuery("a.delete").hide();
	}catch(e){
		error("printDatas"+ e);
	}
	
}

function prepareEvents(){
	inputEvents();
	buttonEvents();
}




//resizePanel=function(){
//	//jQuery(".resolution").text(jQuery(window).width());
//	
//};
//
//jQuery(window).resize(function() {resizePanel();});

function checkEditedDocuments(){
	jQuery(".documentForm").removeClass("edited");
	jQuery(".documentForm").each(
		function(index, object){
			var o	=	jQuery(object);
			var acum	=	0.0;
			o.find("input[type=text].cf11").each(
				function(index, object){
					if(jQuery(object).hasClass("string")) return;
					acum	+=	isNumeric(jQuery(this).val()) ? parseFloat(jQuery(this).val()) : 0;
				}
			);
			
			if(acum>0){
				o.addClass("edited");
				jQuery(jQuery("ul.documents a")[o.index()]).addClass("touched");
				jQuery(jQuery("ul.documents a")[o.index()]).find(".span-editado").remove();
				jQuery(jQuery("ul.documents a")[o.index()]).html(jQuery(jQuery("ul.documents a")[o.index()]).html()+" <span class='span-editado'>(editado)</span>");
				if(jQuery(jQuery("ul.documents a")[o.index()]).hasClass("active")){
					jQuery(jQuery("ul.documents a")[o.index()]).removeClass("touched");
				}
			}else{
				jQuery(jQuery("ul.documents a")[o.index()]).removeClass("touched");
				jQuery(jQuery("ul.documents a")[o.index()]).find(".span-editado").remove();
			}
		}
	);
}

/*  */

//var acreditableCodes	=	["01", "07", "08", "09", "11", "19", "20", "21", "22", "24", "26", "27"];
//var nonAcreditableCodes	=	["28", "29", "34", "40", "42", "43", "44", "45", "46", "47"];
//var nonTaxableCodes		=	["70", "71", "72"];

function fillUls(){
	try{
		var acreditableCodes = getAcreditableCodes(f11.tipoContribuyente == "J");
		for(var i=0; i<acreditableCodes.length; i++){                                      
			for(var l=0; l<codigosIngreso.length; l++){
				if(acreditableCodes[i]==codigosIngreso[l].CIngreso){
					
					jQuery("#ulA ul").append(jQuery("<li>").html("<p>"+codigosIngreso[l].CIngreso+" "+codigosIngreso[l].DIngreso+"</p>"));
				}
			}
		}
		var nonAcreditableCodes = getNonAcreditableCodes(f11.tipoContribuyente == "J", f11.domiciliado);
		for(var i=0; i<nonAcreditableCodes.length; i++){                                      
			for(var l=0; l<codigosIngreso.length; l++){
				if(nonAcreditableCodes[i]==codigosIngreso[l].CIngreso){
					
					jQuery("#ulNA ul").append(jQuery("<li>").html("<p>"+codigosIngreso[l].CIngreso+" "+codigosIngreso[l].DIngreso+"</p>"));
				}
			}
		}
		var nonTaxableCodes = getNonTaxableCodes(f11.tipoContribuyente == "J");
		for(var i=0; i<nonTaxableCodes.length; i++){                                      
			for(var l=0; l<codigosIngreso.length; l++){
				if(nonTaxableCodes[i]==codigosIngreso[l].CIngreso){
					
					jQuery("#ulNG ul").append(jQuery("<li>").html("<p>"+codigosIngreso[l].CIngreso+" "+codigosIngreso[l].DIngreso+"</p>"));
				}
			}
		}
		var s	=	["60"];
		for(var i=0; i<s.length; i++){                                      
			for(var l=0; l<codigosIngreso.length; l++){
				if(s[i]==codigosIngreso[l].CIngreso){
					
					jQuery("#ulS ul").append(jQuery("<li>").html("<p>"+codigosIngreso[l].CIngreso+" "+codigosIngreso[l].DIngreso+"</p>"));
				}
			}
		}
		
	}catch(e){
		alert("llenarULs"+e);
	}
	
}

function create_f11v9(callback){
	f11 = new F11V9();
	jQuery.ajax({   url: jsContext + "api/sug/f11/get", 
        type:"GET",
        success:function(json){			        	
        	f11.setFromJson(json);
        	//fill codigos ingreso
        	jQuery.ajax({   url: jsContext + "codigos/ingresos", 
		        type:"GET",
		        success:function(codes){			        	
		        	codigosIngreso = codes;
		        	callback();
		        },
		        error: function(response){
		        	alert(response);
		        }		        
		    });
        },
        error: function(response){
        	console.log(response);
        }		        
    });
}


jQuery(document).ready(
		function(){
			try{
				
				jQuery("#loading").dialog({
					autoOpen : false,
					modal : true,
					position : {
						my : "center",
						at : "center top",
						of : "#container"
					},
					inWidth : 300,
					minHeight : 50,
					closeOnEscape : false,
					title : "Espere un momento.."
				});
				
				if(isltIE7==true){
				
//				if (navigator.appName == 'Microsoft Internet Explorer'){
//				if(typeof navigator.msPointerEnabled != "undefined"){
//					if(navigator.msPointerEnabled){
						jQuery(".noBrowser").show(300);
						jQuery("#wizardButtons, #step1, #step2, #step3, #step4, #step5").remove();
						jQuery(".noBrowser-hide").hide(300);
						jQuery(".noBrowser-hide").remove();
//					}
					
					return;
				}
				
				jQuery("#loading").prevAll(".ui-dialog-titlebar").hide();
				jQuery("#loading").dialog("open");
				
				create_f11v9(function(){
					if(isltIE7==false){					
						calculator = new calculatorF112013();
						printDatas();					
						//jQuery("body").append(jQuery("<div>",{"id":"messageFixed"}).html("<p>Leer indicaciones <br/>del cuadro verde...</p>"));
						setIngresos();//Agregado por Hugo. 11/12/2014
						setDatas();
						prepareEvents();
						showHideIncomesList();
						wizardStp(1);
						jQuery("tr#trCode17").hide();
						//jQuery("tr#trCode22").hide();
						jQuery("tr#tr322-325").hide();
						jQuery("tr#tr326-328").hide();
						jQuery("tr#tr317-327").hide();
						fillUls();
						confirmarAbandono(false);
						jQuery("div.mask").show();
						jQuery("div#wizardButtons").show();
						
						if(f11.tipoContribuyente == "J"){
							jQuery("#b_addNewNR_60").hide();
							jQuery("#profesionesArtesOficiosDiv").hide();
							jQuery("#_profesionesArtesOficios").hide();
							jQuery("#deductiblesContainer").hide();
							jQuery("#deductiblesContainer-clon").hide();							
						}
						
						if(!f11.domiciliado){
							jQuery("#b_addNewNR_60").hide();
							jQuery("#b_addNewR").hide();
							jQuery("#b_addNewNG").hide();							 
							jQuery("#deductiblesContainer").hide();
							jQuery("#deductiblesContainer-clon").hide();
						}
						//jQuery("div#loading").hide();
						//jQuery("span#variableNit").text(NIT);
						jQuery("span#folioModifica").text(f11.v("018"));
						
						jQuery("input[name=c920]").val("");
						drawLiquidos();
					}
					jQuery("#loading").dialog("close");
					
				});
				
				
				
			}catch(e){
				error("ready " + e);
			}
			
			
		}
	);

function confirmarAbandono(on) {window.onbeforeunload = (on) ? unloadMessage : null;}
function unloadMessage() {
    return 'La declaraci'+String.fromCharCode(243)+'n a'+String.fromCharCode(250)+'n no ha sido presentada.';
}

