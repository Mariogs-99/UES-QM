Number.prototype.fixed = function() {   	
	return new Number(((Math.round(this * 100))/100).toFixed(2));  
};


//http://ejohn.org/blog/javascript-array-remove/
Array.prototype.remove = function(from, to) {
	  var rest = this.slice((to || from) + 1 || this.length);
	  this.length = from < 0 ? this.length + from : from;
	  return this.push.apply(this, rest);
	};

var F11V9 = klass(function () {	  
	  
	  
	  this.contador = {};
	  
	  this.retenciones = [];
	  
	  this.retencionesABorrar = [];
	  
	  this.ingresosNoGravados = [];
	  
	  this.valoresMaximos = [];
	  
	  this.donaciones = [];
	  
	  this.ingresos = [];
	  
	  this.liquidos = [];
	  
	  this.tipoContribuyente = "";
	  
	  this.domiciliado = true;
	  
	  this.last_id = 0;	  
	  this.profesionesArtesOficios = 0;//110
	  this.servicios = 0;
	  this.comerciales = 0;
	  this.industriales = 0;
	  this.agropecuarias = 0;
	  this.utilidadesDividendos = 0;
	  this.serviciosExterior = 0;
	  this.otrasRentasGravables = 0;
	  
	  this.rentasNoGravadasExentas = 0;//730
	  this.indemnizacionesNoGravadas = 0;//738
	  
	  this.c017 = this.c018 = this.c024 = this.c019 = null;
	  this.c105 = this.c110 = this.c115 = this.c120 = this.c125 = this.c130 = this.c135 = this.c137 = this.c140 = this.c145 = null;
	  this.c205 = this.c210 = this.c215 = this.c225 = this.c235 = this.c240 = this.c242 = this.c245 = this.c250 = this.c255 = null;
	  this.c300 = this.c646 = this.c647 = this.c648 = this.c305 = this.c306 = this.c307 = this.c308 = this.c309 = this.c304 = this.c310 = null;
	  this.c312 = this.c314 = this.c315 = this.c317 = this.c320 = this.c325 = this.c327 = this.c328 = this.c329 = this.c330 = this.c335 = this.c340 = this.c322 = this.c326 = null;
	  this.c345 = this.c350 = this.c405 = this.c410 = this.c415 = this.c420 = this.c425 = this.c430 = this.c435 = this.c440 = null;
	  this.c442 = this.c444 = this.c445 = this.c450 = this.c525 = this.c530 = this.c535 = this.c540 = this.c545 = this.c711 = null;
	  this.c712 = this.c713 = this.c714 = this.c715 = this.c716 = this.c717 = this.c718 = this.c719 = this.c721 = this.c722 = null;
	  this.c725 = this.c601 = this.c602 = this.c603 = this.c604 = this.c605 = this.c606 = this.c607 = this.c608 = this.c609 = null;
	  this.c610 = this.c611 = this.c612 = this.c613 = this.c614 = this.c615 = this.c616 = this.c617 = this.c618 = this.c619 = null;
	  this.c620 = this.c621 = this.c622 = this.c623 = this.c624 = this.c625 = this.c626 = this.c627 = this.c628 = this.c650 = null;
	  this.c630 = this.c631 = this.c632 = this.c633 = this.c634 = this.c635 = this.c636 = this.c637 = this.c638 = this.c639 = this.c645 = null;
	  this.c660 = this.c670 = this.c800 = this.c840 = this.c841 = this.c842 = this.c843 = this.c844 = this.c845 = this.c846 = null;
	  this.c847 = this.c850 = this.c851 = this.c855 = this.c860 = this.c862 = this.c865 = this.c730 = this.c732 = this.c734 = null;
	  this.c736 = this.c738 = this.c740 = this.c742 = this.c744 = this.c746 = this.c869 = this.c750 = this.c870 = this.c871 = null;
	  this.c872 = this.c873 = this.c920 = this.c925 = this.c930 = this.c820 = this.c830 = this.c940 = this.c950 = this.c970 = null;
	  this.c917 = this.c849 = this.c538 = this.c752 = null;
	  
	  for (var prop in this) {
  			if(prop[0]=="c" && prop != "comerciales" && prop != "contador"){
  				this[prop] = {alias:"",valor:0.00,visible:true,editable:true};
  			}
	  }
	  	
  	  this["c800"] = {alias:"",valor:new Date(),visible:false, editable:false}; 
	  this["c920"] = {alias:"",valor:"",visible:true,editable:true};
	  this["c317"] = {alias:"",valor:"",visible:true,editable:true};
	  this["c925"] = {alias:"",valor:"",visible:true,editable:true};
	  this["c930"] = {alias:"",valor:"",visible:true,editable:true};
	  this["c017"] = {alias:"",valor:"",visible:true,editable:true};
	  this["c024"] = {alias:"",valor:"",visible:true,editable:true};
	  
	})	  
	  .methods({
		setFromJson: function(data){
			
			for (var prop in data) {
	  			this[prop] = data[prop];
			}
			
			//El listado de ingresos lo muevo a cada una de sus propiedades
			//para hacer mas facil la retrocompatibilidad con las
			//librerias del UI, mientras se migran			
			for (var i = 0; i < this.ingresos.length; i++) {
				var ingreso = this.ingresos[i];				
				this[ingreso.casilla.alias] = ingreso.casilla.valor;
			}
			this.valoresMaximos[0] = {"max_711":800};
			this.valoresMaximos[1] = {"max_712":800};
			this.valoresMaximos[2] = {"max_713":this.v("713")};			
		},
		serializable: function(){
			var f ="";
			var existentes=0;
			for (var i = 0; i < this.retenciones.length; i++) {
				var ret = this.retenciones[i];
				if(!ret.nueva){
					existentes++;
				}
			}
			var index=existentes;
			for (var i = 0; i < this.retenciones.length; i++) {
				var ret = this.retenciones[i];				
				if(ret.nueva){
					//Agregar id para enviarlo al servidor
					f += "retenciones["+index+"].c810.valor="+ret.c810.valor+
					 	 "&retenciones["+index+"].id="+ret.id+
					 	 "&retenciones["+index+"].nombre="+ret.nombre+
						 "&retenciones["+index+"].c805.valor="+ret.c805.valor+
						 "&retenciones["+index+"].c815.valor="+ret.c815.valor+
						 "&retenciones["+index+"].c825.valor="+ret.c825.valor+
						 "&retenciones["+index+"].ipsfa="+ret.ipsfa+
						 "&retenciones["+index+"].nueva="+ret.nueva+
						 "&retenciones["+index+"].borrar="+ret.borrar+
						 "&retenciones["+index+"].bienestar="+ret.bienestar+"&";
					index++;
				}
		   }
		  
		  
			for (var prop in this) {
	  			if(prop[0]=="c" && prop != "comerciales" && prop != "contador"){
	  				f+=prop+".valor="+this[prop].valor+ "&";	  				
	  			}				
		    }
						
			var index=0;
			for (var i = 0; i < this.ingresos.length; i++) {
				var ing = this.ingresos[i];				
				f += "ingresos["+index+"].casilla.alias="+ing.casilla.alias+
			 	 "&ingresos["+index+"].casilla.valor="+ing.casilla.valor+"&";
				index++;				
		    }
			
			//serializar liquidez
			var index=0;
			for (var i = 0; i < this.liquidos.length; i++) {
				var liq = this.liquidos[i];				
				f += "liquidos["+index+"].nitLiquidez="+liq.nitLiquidez+
				 "&liquidos["+index+"].vmonto="+liq.vmonto+"&"+
				 "&liquidos["+index+"].borrar="+liq.borrar+"&"+
				 "&liquidos["+index+"].nombre="+liq.nombre+"&"+
			 	 "&liquidos["+index+"].faLiquidez="+liq.faLiquidez+"&";
				index++;
		    }
			
			//f+="rentasNoGravadasExentas="+this.rentasNoGravadasExentas+ "&";
			//f+="indemnizacionesNoGravadas="+this.indemnizacionesNoGravadas;			
						
			return f;
		},
		post: function(url,successCallback, errorCallback){
			
			jQuery.ajax({url:url, 
		        type:"POST",		    
		        data:this.serializable(),
		        success:successCallback,
		        error: errorCallback
		    });
		},
		presentar: function(successCallback, errorCallback){
			jQuery.ajax({   url: jsContext + "api/sug/f11/presentar", 
		        type:"POST",		        
		        success:successCallback,
		        error: errorCallback		        
		    });
		},
		update: function(casilla, valor,visible, editable){
			
			if(typeof valor == "number"){
				valor = valor.fixed();
			}
			this["c"+casilla].valor = valor;
			this["c"+casilla].visible = visible;
			this["c"+casilla].editable = editable;
		},
	    setRetenciones: function (rets) {
	    	this.retenciones = rets;
	    },
	    getRetenciones: function(){
	    	return this.retenciones;
	    },
	    existeRetencion: function(cod){
	    	return !(typeof this.contador[cod] === "undefined");
	    },
	    updateContador : function(){	    	
	    	t = this;
			for (var i = 0; i < this.retenciones.length; i++) {			
				t.contador[t.retenciones[i].c805.valor] = ((typeof t.contador[t.retenciones[i].c805.valor]=== "undefined") ? 0: t.contador[t.retenciones[i].c805.valor]) +1;
			}
	    },
	    updateTotales : function(){
	    	var totalDevengado = 0;
			var totalRetenido = 0;
			var totalDonaciones = 0;
			var total940 = 0;
			var total950 = 0;
			
	    	for (var i = 0; i < this.retenciones.length; i++) {
	    		var ingreso = this.retenciones[i];	    		
	    		if(ingreso.borrar){	    			
	    			continue;
	    		}	    		
	    		//Aqui excluyo los codigos 70,71,72 de la sumatoria
	    		//tambien debo excluir las retenciones no acreditables
	    		if(ingreso.c805.valor == "70" || ingreso.c805.valor == "71" || ingreso.c805.valor == "72"){
	    			continue;
	    		}
	    		
	    		if(ingreso.c805.valor == "28" || ingreso.c805.valor == "29" || ingreso.c805.valor == "34" ||
	    		   ingreso.c805.valor == "30" || ingreso.c805.valor == "31" || ingreso.c805.valor == "32" ||
	    		   ingreso.c805.valor == "33" || ingreso.c805.valor == "35" || ingreso.c805.valor == "36" ||
	    		   ingreso.c805.valor == "37" || ingreso.c805.valor == "38" || ingreso.c805.valor == "39" ||
	    		   ingreso.c805.valor == "41" || ingreso.c805.valor == "40" || ingreso.c805.valor == "42" || 
	    		   ingreso.c805.valor == "43" || ingreso.c805.valor == "44" || ingreso.c805.valor == "45" || 
	    		   ingreso.c805.valor == "46" || ingreso.c805.valor == "47" || ingreso.c805.valor == "48"){
	    			
	    			if(!isNaN(ingreso.c815.valor)){
						total940 = total940 + ingreso.c815.valor;
					}
					if(!isNaN(ingreso.c825.valor)){
						total950 = total950 + ingreso.c825.valor;
					}
					
	    		}else{
	    			if(!isNaN(ingreso.c815.valor)){
						totalDevengado = totalDevengado + ingreso.c815.valor;
					}
					if(!isNaN(ingreso.c825.valor)){
						totalRetenido = totalRetenido + ingreso.c825.valor;						
					}
	    		}
	    		
				
			}	    	
	    	this.donaciones = this.donaciones===undefined ? []:this.donaciones;
	    	for (var i = 0; i < this.donaciones.length; i++) {
	    		totalDonaciones = totalDonaciones + this.donaciones[i].c910.valor;
	    	}
	    	
	    	this.update("820",this.getMontoRetencionesAcreditables(),true,false);
	    	this.update("830",this.getRetenidoRetencionesAcreditables(),true,false);
	    	this.update("917",totalDonaciones,true,false);			
	    	this.update("940",total940,true,false);
	    	this.update("950",total950,true,false);
	    },
	    getMontoByRetencion : function(cods){
	    	var montoTemporal = 0;
	    	if( typeof cods === 'string' ) {
	    	    cods = [ cods ];
	    	}
	    	for (var i = 0; i < this.retenciones.length; i++) {
	    		for (var j = 0; j < cods.length; j++) {
	    			if(this.retenciones[i].borrar)continue;
	    			if(this.retenciones[i].c805.valor == cods[j]){
		    			montoTemporal = montoTemporal + this.retenciones[i].c815.valor; 
		    		}
	    		}
			}
	    	return montoTemporal;
	    },
	    getRetenidoByRetencion : function(cods){
	    	var montoTemporal = 0;
	    	if( typeof cods === 'string' ) {
	    	    cods = [ cods ];
	    	}
	    	for (var i = 0; i < this.retenciones.length; i++) {
	    		for (var j = 0; j < cods.length; j++) {
	    			if(this.retenciones[i].borrar)continue;
	    			if(this.retenciones[i].c805.valor == cods[j]){
		    			montoTemporal = montoTemporal + this.retenciones[i].c825.valor; 
		    		}
	    		}
			}
	    	return montoTemporal;
	    },
	    getMontoRetencionesAcreditables : function(){
	    	if(this.tipoContribuyente=="N"){
	    		return this.getMontoByRetencion(["01","07","08","09","11","19","20","21","22","24","26","27"]);
	    	}else{
	    		return this.getMontoByRetencion(["05","06","07","08","09","19","20","23","25","26"]);
	    	}
	    	
	    },
	    getMontoRetencionesNoAcreditables : function(){
	    	if(this.tipoContribuyente=="N"){
	    		return this.getMontoByRetencion(["28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48"]);
	    	}else{
	    		return this.getMontoByRetencion(["29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","48"]);
	    	}
	    	
	    },
	    getMontoRetencionesIngGravSinRet : function(){
	    	return this.getMontoByRetencion("60");
	    },
	    getMontoRetencionesIngNoGrav : function(){
	    	if(this.tipoContribuyente=="N"){
	    		return this.getMontoByRetencion(["70","71","72"]);
	    	}else{
	    		return this.getMontoByRetencion(["71"]);
	    	}
	    	
	    },
	    getRetenidoRetencionesAcreditables : function(){
	    	if(this.tipoContribuyente=="N"){
	    		return this.getRetenidoByRetencion(["01","07","08","09","11","19","20","21","22","24","26","27"]);
	    	}else{
	    		return this.getRetenidoByRetencion(["05","06","07","08","09","19","20","23","25","26"]);
	    	}	    	
	    },
	    getRetenidoRetencionesNoAcreditables : function(){
	    	if(this.tipoContribuyente=="N"){
	    		return this.getRetenidoByRetencion(["28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48"]);
	    	}else{
	    		return this.getRetenidoByRetencion(["29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","48"]);
	    	}
	    	
	    },
	    getRetenidoRetencionesIngGravSinRet : function(){
	    	return this.getRetenidoByRetencion("60");
	    },
	    getRetenidoRetencionesIngNoGrav : function(){
	    	if(this.tipoContribuyente=="N"){
	    		return this.getRetenidoByRetencion(["70","71","72"]);
	    	}else{
	    		return this.getRetenidoByRetencion(["71"]);
	    	}	    	
	    },
	    get : function(propiedad){	    	
	    	return this[propiedad];
	    },
	    set: function(propiedad,valor){
	    	//Ajuste para ingresos sin retencion
	    	if(propiedad=="profesionesArtesOficios" || propiedad=="servicios" || propiedad=="comerciales"
	    		|| propiedad=="industriales" || propiedad=="agropecuarias" || propiedad=="utilidadesDividendos"
	    		|| propiedad=="serviciosExterior" || propiedad=="otrasRentasGravables" || propiedad=="rentasNoGravadasExentas" 
	    			|| propiedad=="indemnizacionesNoGravadas"){
	    		
	    			this.setIngreso(propiedad,valor);	    		
	    	}else{
	    			this[propiedad] = valor;
	    	}	    	
	    },
	    setIngreso : function(ingreso,valor){
	    	
	    	var j = -1; //index del ingreso si ya existe
	    	for (var i = 0; i < this.ingresos.length; i++) {	    		
	    		ing = this.ingresos[i];	    		
	    		if(ing.casilla.alias == ingreso){
	    			j = i;
	    			break;
	    		}
	    	}
	    	if(this.ingresos.length==0){
	    		this.ingresos = [];
	    	}
	    	if(j<0){
	    		j = this.ingresos.length;
	    	}	    		
	    	this.ingresos[j] = {};
	    	this.ingresos[j].casilla = {};
	    	this.ingresos[j].casilla["alias"] = ingreso;
	    	this.ingresos[j].casilla["valor"] = new Number(valor);
	    	this[ingreso] = valor;
	    		    	
	    },
	    v : function(casilla){
	    	var _valor = this["c"+casilla].valor;
	    	if(typeof valor == "number")return new Number(_valor);	    	
	    	return _valor; 
	    },
	    isDeduccionFija: function(){
			return (this.c820.valor <= 5714.29 && !this.existeRetencion("11"));		
		},
		removeIngresoConRetencion : function(id){
			for (var i = 0; i < this.retenciones.length; i++) {			
				if(this.retenciones[i].id && id==this.retenciones[i].id){
					//this.retenciones.splice(i,1);
					this.retenciones[i].borrar = true;
				}
			}
		},		
		addIngresoConRetencion : function(nit,nombre,codIngreso,devengado,retenido,bienestar,ipsfa){
			//validacion de nit se hace previamente	
			id = new Date().getTime();
			this.last_id = id;
			ret = {
					"id":id,
					"nombre":nombre,
					"borrar":false,
					"c805": {
			            "alias": "codigoIngreso",
			            "valor": codIngreso,
			            "visible": true
			          },
			         "c810": {
				            "alias": "nit",
				            "valor": nit,
				            "visible": true
				          },
			         "c815": {
			            "alias": "ingresoGravado",
			            "valor": new Number(devengado),
			            "visible": false
			          },
			        "c825": {
			            "alias": "impuestoRetenido",
			            "valor": new Number(retenido),
			            "visible": false
			          },					
					"nueva" : true,
					"bienestar":bienestar,
					"ipsfa":ipsfa
			};			
			this.retenciones.push(ret);			
			return ret;
		},
		addLiquidez : function(nit,nombre,periodo,monto){	
			var liq_index = -1;
			for (var i = 0; i < this.liquidos.length; i++) {
				if(this.liquidos[i].nitLiquidez==nit && this.liquidos[i].faLiquidez==periodo){					
					liq_index = i;
				}
			}			
			if(liq_index>=0){
				this.liquidos[liq_index].vmonto = new Number(monto);
				this.liquidos[liq_index].borrar = false;
				return this.liquidos[liq_index];
			}else{
				liq = {
						"nitLiquidez":nit,
						"faLiquidez":periodo,
						"vmonto" : new Number(monto),
						"borrar": false,
						"nombre": nombre
				};
				this.liquidos.push(liq);
				return liq;
			}			
		},
		limpiarLiquidez : function(){			
			for (var i = 0; i < this.liquidos.length; i++) {			
				this.liquidos[i].borrar = true;				
			}			
		},
		totalLiquidez : function(){
			var total = 0.0;
			for (var i = 0; i < this.liquidos.length; i++) {
				if(!this.liquidos[i].borrar){
					total = total + this.liquidos[i].vmonto;
				}
	    	}
			return total;
		},
		removeLiquidez : function(nit,periodo){
			for (var i = 0; i < this.liquidos.length; i++) {			
				if(this.liquidos[i].nitLiquidez==nit && this.liquidos[i].faLiquidez==periodo){					
					this.liquidos[i].borrar = true;
				}
			}
		},
		getLiquidos : function(){
			return this.liquidos;
		}
});

var calculatorF112013 = klass(function () {	
	
	
}).methods({
	  calculateMedicosColegiaturas: function(f11){
		  
		  		f11.valoresMaximos[0] = {"max_711":800};
		  		f11.valoresMaximos[1] = {"max_712":800};
			
		  		f11.update("711", f11.v("711"),true,true);
		  		f11.update("712", f11.v("712"),true,true);
		  		
				//Si el monto es mayor al sugerido pongo el sugerido por fuerza
				if(f11.v("712") >800){
					f11.update("712", 800,true,true);
				}
				if(f11.v("711") >800){
					f11.update("711", 800,true,true);
				}		
	  },
	  getMontoCalculadoISSS: function(f11){
		  var monto_isss = 0;
		  var rets = f11.getRetenciones();
			
			for (var i = 0; i < rets.length; i++) {
				//Removiendo condicion para que calcule el maximo del ISSS en base a todos los salarios
				//Sin importar si tiene bienestar o no.
				//Cambio solicituda por el usuario el 01/04/2014
				if((rets[i].c805.valor == "01" || rets[i].c805.valor == "60")  /*&& !rets[i].bienestar*/){
					monto_isss = monto_isss + rets[i].c815.valor;
				}
			}
			
			
			var v_isss = (monto_isss * 100.00)/93.75;
			
			if(v_isss <= 8228.57){
				
				return (v_isss * 0.03).fixed();
			}else{
				
				return (8228.57 *0.03).fixed();
			}
	  },
	  getMontoCalculadoBienestar: function(f11){
		  return (f11.v("105") * 0.0325);
	  },
	  getMontoCalculadoIpsfa: function(f11){
		   return (f11.v("105") *0.125);
	  },
	  getMontoDonaciones: function(f11){
		   
			var limite_donacion = ( (f11.v("145") + f11.v("245") + f11.v("235") + f11.v("250") - f11.v("210") - f11.v("711")
			- f11.v("712") - f11.v("713") - f11.v("714") - f11.v("716") - f11.v("717")
			- f11.v("721")) - (f11.v("917") * 2) ) * 0.20;
			
			
			if(f11.v("917") <= limite_donacion){
				return f11.v("917");
			}
			
			if(limite_donacion < 0){
				limite_donacion = 0;
			}
			
			return limite_donacion;
			
	  },
	  calcular : function(f11,setMax){
		  
		  var VISIBLE = EDITABLE = true;
		  var NO_VISIBLE = NO_EDITABLE = false;
		  f11.updateContador();
		  f11.updateTotales();
		  
		  	//llenar los ingresos
			var total105 = f11.getMontoByRetencion(["01","60"]);
			var total110 = f11.getMontoByRetencion("10");			
			var total115 = f11.getMontoByRetencion(["11","21","27"]);
			if(f11.tipoContribuyente=="J"){
				total115 = f11.getMontoByRetencion(["21","27"]);
			}
			var total120 = 0;
			var total125 = 0;
			var total130 = f11.getMontoByRetencion("07");
			var total135 = 0;
			var total137 = 0;
			var total140 = f11.getMontoByRetencion(["08","09","19","20","22","23","24","25","26"]);
			
			if(f11.tipoContribuyente=="J"){
				total140+=f11.getMontoByRetencion(["12","05","06",]);
			}
			
			if(!f11.domiciliado){
				total105 = 0;
			}
			
			//Despues hay que sumar los valores directos que escribieron	06142511721114		
			total110 += f11.get("profesionesArtesOficios");
			total115 = total115 + f11.get("servicios");
			total120+=f11.get("comerciales");
			total125+=f11.get("industriales");
			total130+=f11.get("agropecuarias");
			total135+=f11.get("utilidadesDividendos");
			total137+=f11.get("serviciosExterior");
			total140+=f11.get("otrasRentasGravables");
			
			f11.update("105",total105,NO_VISIBLE,NO_EDITABLE);
			f11.update("110",total110,NO_VISIBLE,NO_EDITABLE);
			f11.update("115",total115,NO_VISIBLE,NO_EDITABLE);
			f11.update("120",total120,NO_VISIBLE,NO_EDITABLE);
			f11.update("125",total125,NO_VISIBLE,NO_EDITABLE);
			f11.update("130",total130,NO_VISIBLE,NO_EDITABLE);
			f11.update("135",total135,NO_VISIBLE,NO_EDITABLE);
			f11.update("137",total137,NO_VISIBLE,NO_EDITABLE);
			f11.update("140",total140,NO_VISIBLE,NO_EDITABLE);
			
			f11.update("145", 
					 f11.v("105")
					+f11.v("110")
					+f11.v("115")
					+f11.v("120")
					+f11.v("125")
					+f11.v("130")
					+f11.v("135")
					+f11.v("137")
					+f11.v("140"),
					NO_VISIBLE,
					NO_EDITABLE
			);
			
			if(f11.tipoContribuyente=="N" && !f11.domiciliado && periodo>2004){
				f11.update("870",0,NO_VISIBLE,NO_EDITABLE);
				f11.update("871",0,NO_VISIBLE,NO_EDITABLE);
			}
			
			if(f11.tipoContribuyente=="N" && 
			   f11.v("105")>0 && 
			   (f11.v("105")>0 && f11.v("105")<=9100.00 && 
					  (f11.v("145")-f11.v("105") == 0))){
				f11.update("722", 1600.00,VISIBLE,NO_EDITABLE);				
			}else{
				f11.update("722", 0,NO_VISIBLE,NO_EDITABLE);
			}
			
			if(f11.tipoContribuyente=="J" || f11.v("145")==0 || f11.v("722")>0){
				f11.update("711", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("712", 0,NO_VISIBLE,NO_EDITABLE);				
				f11.update("715", 0,NO_VISIBLE,NO_EDITABLE);				
				f11.update("717", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("718", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("719", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("721", 0,NO_VISIBLE,NO_EDITABLE);
				
				if(f11.v("722")>0){
					var contadorShowIsss = 0;
					var contadorShowBienestar = 0;
					var contadorShowIpsfa = 0;
					var rets = f11.getRetenciones();
					
					for (var i = 0; i < rets.length; i++) {
						if(rets[i].c805.valor == "01" || rets[i].c805.valor == "60"){
							if(!rets[i].bienestar)contadorShowIsss++;
							if(rets[i].bienestar)contadorShowBienestar++;
							if(rets[i].ipsfa)contadorShowIpsfa++;
						}
					}
					
					var montoCalculadoISSS = this.getMontoCalculadoISSS(f11);
					f11.valoresMaximos[2] = {"max_713":montoCalculadoISSS};
										
					if(setMax){						
						if(montoCalculadoISSS == 0){							
							f11.update("713", f11.v("713"),VISIBLE,EDITABLE);
						}						
					}else{						
						f11.update("713", f11.v("713"),VISIBLE,EDITABLE);
					}
										
					if(f11.v("713") >= montoCalculadoISSS){						
						f11.update("713", montoCalculadoISSS,VISIBLE,EDITABLE);
					}
					
					if(contadorShowBienestar>0){
					   var montoCalculadoBienestar = this.getMontoCalculadoBienestar(f11);					
					   if(f11.v("714") > montoCalculadoBienestar){
						   f11.update("714", montoCalculadoBienestar,VISIBLE,EDITABLE);
					   }else{
						   f11.update("714", f11.v("714"),VISIBLE,EDITABLE);
					   }
					}else{
						f11.update("714", 0,NO_VISIBLE,NO_EDITABLE);
					}
					
					if(contadorShowIpsfa>0){						
						var montoCalculadoIpsfa = this.getMontoCalculadoIpsfa(f11);
						f11.valoresMaximos[4] = {"max_716":montoCalculadoIpsfa};
						if(f11.v("716") > montoCalculadoIpsfa){
							f11.update("716", montoCalculadoIpsfa,VISIBLE,EDITABLE);
						}else{
							f11.update("716", f11.v("716"),VISIBLE,EDITABLE);
						}				    
					}else{
						f11.update("716",0,NO_VISIBLE,NO_EDITABLE);
					}
					
				}else{
					f11.update("713", 0,NO_VISIBLE,NO_EDITABLE);
					f11.update("714", 0,NO_VISIBLE,NO_EDITABLE);
					f11.update("716", 0,NO_VISIBLE,NO_EDITABLE);
				}
				
				
				
			}else{
				
				
				f11.update("717", f11.v("717"),VISIBLE,EDITABLE);
				f11.update("721", f11.v("721"),VISIBLE,EDITABLE);
				
				if(f11.tipoContribuyente=="N" && !f11.domiciliado){
					f11.update("721",0,NO_VISIBLE,NO_EDITABLE);
					f11.update("717",0,NO_VISIBLE,NO_EDITABLE);
				}
				
				this.calculateMedicosColegiaturas(f11);
				
				if(!f11.existeRetencion("01") && !f11.existeRetencion("60")){
					f11.update("713", 0,NO_VISIBLE,NO_EDITABLE);
					f11.update("714", 0,NO_VISIBLE,NO_EDITABLE);
					f11.update("716", 0,NO_VISIBLE,NO_EDITABLE);
										
				}else{
					var contadorShowIsss = 0;
					var contadorShowBienestar = 0;
					var contadorShowIpsfa = 0;
					var rets = f11.getRetenciones();
					
					for (var i = 0; i < rets.length; i++) {
						if(rets[i].c805.valor == "01" || rets[i].c805.valor == "60"){
							if(!rets[i].bienestar)contadorShowIsss++;
							if(rets[i].bienestar)contadorShowBienestar++;
							if(rets[i].ipsfa)contadorShowIpsfa++;
						}
					}
					
					
					if(f11.existeRetencion("01") || f11.existeRetencion("60")){
						var montoCalculadoISSS = this.getMontoCalculadoISSS(f11);
						f11.valoresMaximos[2] = {"max_713":montoCalculadoISSS};
						
						if(setMax){					
							if(montoCalculadoISSS == 0){									
								f11.update("713", f11.v("713"),VISIBLE,EDITABLE);
							}
						}else{								
							f11.update("713", f11.v("713"),VISIBLE,EDITABLE);
						}							
						if(f11.v("713") >= montoCalculadoISSS){								
							f11.update("713", montoCalculadoISSS,VISIBLE,EDITABLE);
						}
					}else{
						f11.update("713",0,NO_VISIBLE,NO_EDITABLE);
					}
					
					if(contadorShowBienestar>0){
					   var montoCalculadoBienestar = this.getMontoCalculadoBienestar(f11);					
					   if(f11.v("714") > montoCalculadoBienestar){
							f11.update("714", montoCalculadoBienestar,VISIBLE,EDITABLE);
					   }else{
						   f11.update("714", f11.v("714"),VISIBLE,EDITABLE);
					   }
					}else{
						f11.update("714", 0,NO_VISIBLE,NO_EDITABLE);
					}
				
					if(contadorShowIpsfa>0){						
						var montoCalculadoIpsfa = this.getMontoCalculadoIpsfa(f11);
						f11.valoresMaximos[4] = {"max_716":montoCalculadoIpsfa};
						if(f11.v("716") > montoCalculadoIpsfa){
							f11.update("716", montoCalculadoIpsfa,VISIBLE,EDITABLE);
						}else{
							f11.update("716", f11.v("716"),VISIBLE,EDITABLE);
						}				    
					}else{
						f11.update("716",0,NO_VISIBLE,NO_EDITABLE);
					}
					
				}//end else				
			}//end if
					
		  
		  if(f11.v("125")>0){
			  
			  f11.update("405", f11.v("405"),VISIBLE,EDITABLE);
			  f11.update("410", f11.v("410"),VISIBLE,EDITABLE);
			  f11.update("415", f11.v("415"),VISIBLE,EDITABLE);
			  
			  f11.update("420", 
						f11.v("405")
					  + f11.v("410")
					  - f11.v("415"),
					  VISIBLE,
					  NO_EDITABLE
				);
			  
			  f11.update("425", f11.v("425"),VISIBLE,EDITABLE);
			  f11.update("430", f11.v("430"),VISIBLE,EDITABLE);
			  f11.update("435", f11.v("435"),VISIBLE,EDITABLE);
			  f11.update("440", f11.v("440"),VISIBLE,EDITABLE);
			  
			  f11.update("442", 
						f11.v("420")
					   +f11.v("425")
					   +f11.v("430")
					   +f11.v("435")
					   -f11.v("440"),
					   VISIBLE,
					   NO_EDITABLE
				);
			  			  
			  f11.update("444", f11.v("444"),VISIBLE,EDITABLE);
			  f11.update("445", f11.v("445"),VISIBLE,EDITABLE);
			  
			  f11.update("450", 
						f11.v("442")
					   +f11.v("444")
					   -f11.v("445"),
					   VISIBLE,
					   NO_EDITABLE
				);
			  
			  f11.update("525", f11.v("525"),VISIBLE,EDITABLE);
			  f11.update("530", f11.v("530"),VISIBLE,EDITABLE);
			  f11.update("535", f11.v("535"),VISIBLE,EDITABLE);
			  
			  if(periodo>=2015){
				  f11.update("538", f11.v("538"),VISIBLE,EDITABLE);
			  }else{
				  f11.update("538",0,NO_VISIBLE,NO_EDITABLE);
			  }
			  
			  
			  f11.update("540", 
						f11.v("525")
					   +f11.v("530")
					   +f11.v("535")
					   +f11.v("538"),
					   VISIBLE,
					   NO_EDITABLE
				);
			  
			 f11.update("545",
						f11.v("540")
					   +f11.v("450"),
					   VISIBLE,
					   NO_EDITABLE
				);
		  }else{
			  	f11.update("405", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("410", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("415", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("420", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("425", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("430", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("435", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("440", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("442", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("444", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("445", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("450", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("525", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("530", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("535", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("538", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("540", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("545", 0,NO_VISIBLE,NO_EDITABLE);
		  }
			
		if(f11.v("120")>0){
				
				f11.update("601", f11.v("601"),VISIBLE,EDITABLE);
				f11.update("602", f11.v("602"),VISIBLE,EDITABLE);
				f11.update("603", f11.v("603"),VISIBLE,EDITABLE);
				
				f11.update("604",
						f11.v("601")
					   +f11.v("602")
					   -f11.v("603"),
					   VISIBLE,
					   NO_EDITABLE
				);
				
				f11.update("605", f11.v("605"),VISIBLE,EDITABLE);
				f11.update("606", f11.v("606"),VISIBLE,EDITABLE);
				
				if(periodo>=2015){
					f11.update("752", f11.v("752"),VISIBLE,EDITABLE);
				}else{
					f11.update("752", 0,NO_VISIBLE,NO_EDITABLE);
				}
				
				f11.update("607", f11.v("607"),VISIBLE,EDITABLE);
				
				f11.update("608",					
					   f11.v("605")	
					   +f11.v("606")
					   +f11.v("752")
					   +f11.v("607"),
					   VISIBLE,
					   NO_EDITABLE
				);
				
				f11.update("604", f11.v("604"),VISIBLE,NO_EDITABLE);
				f11.update("608", f11.v("608"),VISIBLE,NO_EDITABLE);
				
				f11.update("609",
						f11.v("604")
					   +f11.v("608"),
					   VISIBLE,
					   NO_EDITABLE
				);				
			}else{
				f11.update("601", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("602", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("603", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("604", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("605", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("606", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("607", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("608", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("609", 0,NO_VISIBLE,NO_EDITABLE);
			}
			
			if(f11.v("130")>0){
				
				f11.update("610", f11.v("610"),VISIBLE,EDITABLE);
				f11.update("611", f11.v("611"),VISIBLE,EDITABLE);
				f11.update("612", f11.v("612"),VISIBLE,EDITABLE);
				
				f11.update("613",
						f11.v("610")
					   +f11.v("611")	
					   -f11.v("612"),
					   VISIBLE,
					   NO_EDITABLE
				);
				
				f11.update("614", f11.v("614"),VISIBLE,EDITABLE);
				f11.update("615", f11.v("615"),VISIBLE,EDITABLE);
				if(periodo>=2015){
					f11.update("753", f11.v("753"),VISIBLE,EDITABLE);
				}else{
					f11.update("753",0,NO_VISIBLE,NO_EDITABLE);
				}
				
				f11.update("616", f11.v("616"),VISIBLE,EDITABLE);
				
				f11.update("617",					
					    f11.v("614")	
					   +f11.v("615")
					   +f11.v("753")
					   +f11.v("616"),
					   VISIBLE,
					   NO_EDITABLE
				);
				
				f11.update("613", f11.v("613"),VISIBLE,NO_EDITABLE);
				f11.update("617", f11.v("617"),VISIBLE,NO_EDITABLE);
				
				f11.update("618",
						f11.v("613")
					   +f11.v("617"),
					   VISIBLE,
					   NO_EDITABLE
				);
			}else{
			
				f11.update("610", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("611", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("612", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("613", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("614", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("615", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("616", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("617", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("618", 0,NO_VISIBLE,NO_EDITABLE);
			}
			
			if(f11.v("115")>0 || f11.v("110")>0){
				
				f11.update("619", f11.v("619"),VISIBLE,EDITABLE);
				f11.update("620", f11.v("620"),VISIBLE,EDITABLE);
				f11.update("621", f11.v("621"),VISIBLE,EDITABLE);
				
				f11.update("622",
						f11.v("619")
					   +f11.v("620")	
					   -f11.v("621"),
					   VISIBLE,
					   NO_EDITABLE
				);
				
				f11.update("623", f11.v("623"),VISIBLE,EDITABLE);
				f11.update("624", f11.v("624"),VISIBLE,EDITABLE);
				if(periodo>=2015){
					f11.update("754", f11.v("754"),VISIBLE,EDITABLE);
				}else{
					f11.update("754",0,NO_VISIBLE,NO_EDITABLE);
				}
				f11.update("625", f11.v("625"),VISIBLE,EDITABLE);
				
				f11.update("626",					
					   f11.v("623")	
					   +f11.v("624")
					   +f11.v("754")
					   +f11.v("625"),
					   VISIBLE,
					   NO_EDITABLE
				);
				
				f11.update("622", f11.v("622"),VISIBLE,NO_EDITABLE);
				f11.update("626", f11.v("626"),VISIBLE,NO_EDITABLE);
				
				f11.update("627",
						f11.v("622")
					   +f11.v("626"),
					   VISIBLE,
					   NO_EDITABLE
				);
			}else{
				f11.update("619", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("620", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("621", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("622", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("623", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("624", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("625", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("626", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("627", 0,NO_VISIBLE,NO_EDITABLE);
				
			}
			
			if(f11.v("145")>f11.v("105") && f11.v("145")<=30000){
				
				f11.update("840", f11.v("840"),VISIBLE,EDITABLE);
				f11.update("841", f11.v("841"),VISIBLE,EDITABLE);
				f11.update("842", f11.v("842"),VISIBLE,EDITABLE);
				f11.update("843", f11.v("843"),VISIBLE,EDITABLE);
				f11.update("844", f11.v("844"),VISIBLE,EDITABLE);
				f11.update("845", f11.v("845"),VISIBLE,EDITABLE);
				f11.update("846", f11.v("846"),VISIBLE,EDITABLE);
				f11.update("847", f11.v("847"),VISIBLE,EDITABLE);
				
				f11.update("849", f11.v("840")
						         +f11.v("841")
						         +f11.v("842")
						         +f11.v("843")
						         +f11.v("844")
						         +f11.v("845")
						         +f11.v("846")
						         +f11.v("847"),
						VISIBLE, NO_EDITABLE);
				
				f11.update("850", f11.v("850"),VISIBLE,EDITABLE);
				f11.update("851", f11.v("851"),VISIBLE,EDITABLE);
				f11.update("855", f11.v("855"),VISIBLE,EDITABLE);
				
				f11.update("860", f11.v("850")
								  +f11.v("851")
								  +f11.v("855")
								  ,VISIBLE, EDITABLE);		
				
				f11.update("862", f11.v("862"),VISIBLE,EDITABLE);
				
				f11.update("865", 
						f11.v("860")
					 +f11.v("862"), 
					VISIBLE, NO_EDITABLE);
				
				f11.update("800", new Date(),NO_VISIBLE,NO_EDITABLE);
				
			}else{
				f11.update("840", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("841", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("842", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("843", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("844", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("845", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("846", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("847", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("849", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("850", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("851", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("855", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("860", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("862", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("865", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("800", null,NO_VISIBLE,NO_EDITABLE);
			}
			
			f11.update("215", f11.v("215"),VISIBLE,NO_EDITABLE);
			
			f11.update("873", 
					f11.v("870")
				-f11.v("871")
				-f11.v("872"), 
				VISIBLE, NO_EDITABLE);
			
			if(f11.v("873")<0){
				f11.update("873",0,VISIBLE,NO_EDITABLE);
			}
			f11.update("245", f11.v("873"),VISIBLE,NO_EDITABLE);
			
			//calculo 650
			if(f11.tipoContribuyente=="J"){
				ld1 = f11.v("145");
				ld2 = f11.v("917");				
				ld3 = f11.v("215")+f11.v("545")+f11.v("609")+f11.v("618")+f11.v("627");				
				ld6 = f11.v("235")+f11.v("245")+f11.v("250");				
				ld4 = ld1 - (ld2 + ld3) + ld6;				
				ld5 = ld4 - ld2;
				if (ld5 < 0) {
		 			ld5 = 0;
				}
				ld = (ld5 * 0.2);				
				if(ld < f11.v("917")){					
					f11.update("650", ld, VISIBLE, NO_EDITABLE);
				}else{
					f11.update("650", f11.v("917"), VISIBLE, NO_EDITABLE);
				}
			}else{
				f11.update("650", 0, NO_VISIBLE, NO_EDITABLE);
			}
			
			f11.update("628", 
					 f11.v("545")
					+f11.v("609")
					+f11.v("618")
					+f11.v("627")
					+f11.v("650"),
					(f11.v("115")>0 || f11.v("120")>0 || f11.v("130")>0 || (f11.get("c650").visible && f11.v("650")>0)),
					NO_EDITABLE
			);
		  
			
			
			
			
			f11.update("210", f11.v("628"),NO_VISIBLE,NO_EDITABLE);
			
			if(f11.tipoContribuyente=="N" && f11.v("722")==0){
				//calculo de donaciones
				if((f11.v("105")>9100 || f11.v("145")>f11.v("105")) && f11.v("917")>0 ){
					f11.update("715", this.getMontoDonaciones(f11),VISIBLE,NO_EDITABLE);
				}else{
					f11.update("715",0,NO_VISIBLE,NO_EDITABLE);
				}
			}
			if(f11.tipoContribuyente=="N"){
				f11.update("725", 
						f11.v("711")
				    + f11.v("712")
				    + f11.v("713")
				    + f11.v("714")
				    + f11.v("715")
				    + f11.v("716")
				    + f11.v("717")
				    + f11.v("718")
				    + f11.v("719")
				    + f11.v("721")
				    + f11.v("722"),
				    VISIBLE,
				    NO_EDITABLE
			   );
			}else{
				f11.update("725",0,NO_VISIBLE,NO_EDITABLE);
			}
			

			f11.update("205", f11.v("725"),NO_VISIBLE,NO_EDITABLE);
	
			f11.update("225", 
					 f11.v("205")
					+f11.v("210")
					+f11.v("215"),
					NO_VISIBLE,
					NO_EDITABLE
			);
	
			//Si el resultado es mayor o igual que cero es una renta
			f11.update("240", 
					f11.v("145")
					-f11.v("225")
					+f11.v("235"),
					NO_VISIBLE,
					NO_EDITABLE
			);
			
			
			
			if(f11.v("240")>=0){
				f11.update("242",0,NO_VISIBLE,NO_EDITABLE);
			}else{
				f11.update("242", f11.v("240"),NO_VISIBLE,NO_EDITABLE);
				f11.update("240",0,NO_VISIBLE,NO_EDITABLE);
			}
					
			
			
			f11.update("255", 
					 f11.v("240")
					+f11.v("245")
					+f11.v("250"),
					NO_VISIBLE,
					NO_EDITABLE
			);
			
			
			
			//c300 y c302 calculadas
			//calcular c300			
			var porcentaje = 0.25;        
			if(periodo >= 2012) {
				porcentaje = 0.3;
			} 
			var porcentaje_tabla = 0;
			var agregado_tabla = 0;
			var minimo_tabla = 0;
			if(f11.tipoContribuyente=="N"){
			   if(periodo>= 1900 && periodo <= 2011){
				if(f11.v("255")>=2514.29 && f11.v("255")<=9142.85){
					porcentaje_tabla = 0.1;
					agregado_tabla = 57.14;
					minimo_tabla = 2514.29;
				}else if(f11.v("255")>=9142.86 && f11.v("255")<=22857.14){
					porcentaje_tabla = 0.1;
					agregado_tabla = 720;
					minimo_tabla = 9142.86;
				}else if(f11.v("255")>=22857.15 && f11.v("255")<=150000.00){
					porcentaje_tabla = 0.3;
					agregado_tabla = 3462.86;
					minimo_tabla = 22857.15;
				}
			   }else if(periodo>=2012){
				if(f11.v("255")>=0.01 && f11.v("255")<=4064){
					porcentaje_tabla = 0;
					agregado_tabla = 0;
					minimo_tabla = 0.00;
				}else if(f11.v("255")>=4064.01 && f11.v("255")<=9142.86){
					porcentaje_tabla = 0.1;
					agregado_tabla = 212.12;
					minimo_tabla = 4064.00;
				}else if(f11.v("255")>=9142.87 && f11.v("255")<=22857.14){
					porcentaje_tabla = 0.2;
					agregado_tabla = 720;
					minimo_tabla = 9142.86;
				}else if(f11.v("255")>=22857.15 && f11.v("255")<=1000000000){
					porcentaje_tabla = 0.3;
					agregado_tabla = 3462.86;
					minimo_tabla = 22857.14;
				}	
			   }
			
			  var retval = 0;
			  var valor300 = ((f11.v("255") - minimo_tabla) * porcentaje_tabla) + agregado_tabla;			  
			  if(valor300 > 0 && (valor300 > (f11.v("255") * porcentaje)) ){
					retval = (f11.v("255") * porcentaje);
			  }else{
			  		retval = valor300;
			  }
			
			}else{
			   if(periodo >= 2012) {
				if(f11.v("145") > 150000) {
					retval = (f11.v("255") * 0.3);
				}else{
					retval = (f11.v("255") * 0.25);
				}
			   }else{
				retval = (f11.v("255") * 0.25);
			   }			
			}
			
			if(!f11.domiciliado){
				if(periodo>=2012){
					retval = (f11.v("255") * 0.3);
				}else{
					retval = (f11.v("255") * 0.25);
				}
			}
			
			/*var imponible = f11.v("255");			
			var retval = 0;
			if(imponible <= 4064.00){
				retval = 0;
			}else if(imponible >= 4064.01 && imponible <= 9142.86){
				retval = ((imponible - 4064.00) * 0.1) + 212.12;
			}else if(imponible >= 9142.87 && imponible <= 22857.14){
				retval = ((imponible - 9142.86) *0.2)+720.00;
			}else if(imponible>=22857.15){
				retval = ((imponible - 22857.14) * 0.3) + 3462.86;
			}
			
			if(retval > 0 && retval > (imponible * 0.30)){
				retval = imponible*0.30;
			}*/
			
			f11.update("300", retval,VISIBLE,NO_EDITABLE);
			
			//PAGO MINIMO: LA QUE ES VUELVE			
			var sopa_de_letras = "";
			
			//logica de la sopa de letras Why?!?!?!?!
			if( f11.v("250")==0 && f11.v("870")==0 && (f11.v("145")-f11.v("105"))==0 && f11.tipoContribuyente=="N" ){
				sopa_de_letras+="A";				
			}
			if((f11.v("145")+f11.v("250")+f11.v("870"))<=(999999999.00 * 99)){
				sopa_de_letras+="M";
			}
			
			//Leo el valor de la c024
			var c_024 = f11.v("024");			
			if(c_024=="" || typeof c_024 === "undefined"){
				c_024 = "";				
			}else{
				c_024 = c_024.replace("A","").replace("M","");
			}			
			sopa_de_letras = sopa_de_letras + c_024;
			
			f11.update("024",sopa_de_letras,VISIBLE,NO_EDITABLE);
			
			//Segun correo de observaciones #3 de 5/2/2014. No mas pago minimo
			if(f11.v("024")==="" && ((f11.v("145")+f11.v("250")+f11.v("870"))>(999999999.00 * 99))){
				
				f11.update("660",f11.v("660"),VISIBLE,EDITABLE);
				f11.update("670",f11.v("670"),VISIBLE,EDITABLE);
				f11.update("630",f11.v("630"),VISIBLE,EDITABLE);
				f11.update("631",f11.v("631"),VISIBLE,EDITABLE);
				f11.update("632",f11.v("632"),VISIBLE,EDITABLE);
				f11.update("633",f11.v("633"),VISIBLE,EDITABLE);
				f11.update("634",f11.v("634"),VISIBLE,EDITABLE);
				f11.update("635",f11.v("635"),VISIBLE,EDITABLE);
				f11.update("636",f11.v("636"),VISIBLE,EDITABLE);
				f11.update("637",f11.v("637"),VISIBLE,EDITABLE);
				f11.update("638",f11.v("638"),VISIBLE,EDITABLE);
				f11.update("639",f11.v("639"),VISIBLE,EDITABLE);
				
				f11.update("645",
						f11.v("630")
						-f11.v("631")
						-f11.v("632")
						-f11.v("633")
						-f11.v("634")
						-f11.v("635")
						-f11.v("636")
						-f11.v("637")
						-f11.v("638")
						-f11.v("639"),						
						VISIBLE,NO_EDITABLE);
				
				if(f11.v("645")<0){
					f11.update("645",0,VISIBLE,NO_EDITABLE);
				}
				
				
			}else{				
				f11.update("660",0,NO_VISIBLE,NO_EDITABLE);
				f11.update("670",0,NO_VISIBLE,NO_EDITABLE);
				f11.update("630",0,NO_VISIBLE,NO_EDITABLE);
				f11.update("631",0,NO_VISIBLE,NO_EDITABLE);
				f11.update("632",0,NO_VISIBLE,NO_EDITABLE);
				f11.update("633",0,NO_VISIBLE,NO_EDITABLE);
				f11.update("634",0,NO_VISIBLE,NO_EDITABLE);
				f11.update("635",0,NO_VISIBLE,NO_EDITABLE);
				f11.update("636",0,NO_VISIBLE,NO_EDITABLE);
				f11.update("637",0,NO_VISIBLE,NO_EDITABLE);
				f11.update("638",0,NO_VISIBLE,NO_EDITABLE);
				f11.update("639",0,NO_VISIBLE,NO_EDITABLE);
				f11.update("645",0,NO_VISIBLE,NO_EDITABLE);
			}
			
			if( f11.v("645") > (f11.v("145")+f11.v("250")+f11.v("870"))){
				f11.update("646",(f11.v("145")+f11.v("250")+f11.v("870")),VISIBLE,EDITABLE);
			}else{
				f11.update("646",f11.v("645"),VISIBLE,EDITABLE);
			}
			
			f11.update("647",
						  (f11.v("646")						   
						 )*0.01,
						 VISIBLE,
						 NO_EDITABLE
			);				
			
			if(f11.v("300") >= f11.v("647")){
				f11.update("648", f11.v("300"),VISIBLE,NO_EDITABLE);
			}else{
				var tmp = f11.v("145") +f11.v("250") +f11.v("870");
				if(tmp>(999999999.00 * 99)){
					f11.update("648", f11.v("647"),VISIBLE,NO_EDITABLE);
				}else{
					f11.update("648", f11.v("300"),VISIBLE,NO_EDITABLE);
				}
			}
			//END FIN PAGO MINIMO
			
			f11.update("310", 
					 f11.v("648")
					+f11.v("305")
					+f11.v("306")
					+f11.v("307")
					+f11.v("308")
					+f11.v("309")
					+f11.v("304"),
					NO_VISIBLE,
					NO_EDITABLE
			);

			if(periodo<=2013 || !f11.domiciliado){
				f11.update("312",0,NO_VISIBLE,NO_EDITABLE);
			}else{
				f11.update("312",f11.totalLiquidez(),VISIBLE,EDITABLE);
				
				if(f11.v("312")>f11.v("310")){
					f11.update("312", f11.v("310"),VISIBLE,EDITABLE);
				}				
			}
			
			console.log(f11.v("312"));
			
			f11.update("315", f11.v("830"),NO_VISIBLE,NO_EDITABLE);
			f11.update("314", 0,NO_VISIBLE,NO_EDITABLE);//no visible, no editable

			f11.update("317", f11.v("317"),VISIBLE,EDITABLE);
			f11.update("322", f11.v("322"),VISIBLE,EDITABLE);
			f11.update("326", f11.v("326"),VISIBLE,EDITABLE);
			
			if(f11.v("018")!=""){				
				f11.update("329", f11.v("329"),VISIBLE,EDITABLE);				
			}else{				
				f11.update("329",0,NO_VISIBLE,NO_EDITABLE);
			}
			
			if(f11.tipoContribuyente=="N" && !f11.domiciliado){
				f11.update("328", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("326", 0,NO_VISIBLE,NO_EDITABLE);
			}
			
			//c320,c322,c325,c326,c328, se llenan por el contribuyente c329,c335 -> no se usa
			//console.log("830 " + f11.v("830").toString());
			f11.update("330",
				 (f11.v("310")-(f11.v("312")+f11.v("314")+f11.v("315")+f11.v("320")+f11.v("325")+f11.v("328")+f11.v("329"))) + f11.v("327")
				,
				NO_VISIBLE,
				NO_EDITABLE
			);
			
			f11.update("340",
					f11.v("330"),
					NO_VISIBLE,
					NO_EDITABLE
					//.add(f11.vC335().getDecimalValor())
		    );
			
			if(f11.v("340") > 0){
				f11.update("350", f11.v("340"),VISIBLE,NO_EDITABLE);
				f11.update("345", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("920", "",NO_VISIBLE,NO_EDITABLE);
				f11.update("925", "",NO_VISIBLE,NO_EDITABLE);
				f11.update("930", "",NO_VISIBLE,NO_EDITABLE);
			}else{
				f11.update("345", f11.v("340") * -1,VISIBLE,NO_EDITABLE);
				f11.update("350", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("920", f11.v("920"),VISIBLE,EDITABLE);
				f11.update("925", f11.v("925"),VISIBLE,EDITABLE);
				f11.update("930", f11.v("930"),VISIBLE,EDITABLE);
			}

			
			
			//determino si se muestra o no el balance general
			if((f11.v("145")-f11.v("105"))!=0 && f11.v("145")<=30000){
				f11.update("840", f11.v("840"),VISIBLE,EDITABLE);
				f11.update("841", f11.v("841"),VISIBLE,EDITABLE);
				f11.update("842", f11.v("842"),VISIBLE,EDITABLE);
				f11.update("843", f11.v("843"),VISIBLE,EDITABLE);
				f11.update("844", f11.v("844"),VISIBLE,EDITABLE);
				f11.update("845", f11.v("845"),VISIBLE,EDITABLE);
				f11.update("846", f11.v("846"),VISIBLE,EDITABLE);
				f11.update("847", f11.v("847"),VISIBLE,EDITABLE);
			}else{
				f11.update("840", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("841", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("842", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("843", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("844", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("845", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("846", 0,NO_VISIBLE,NO_EDITABLE);
				f11.update("847", 0,NO_VISIBLE,NO_EDITABLE);
			}
			
			
			
			var total730 = f11.getMontoByRetencion(["71","72"]);
			var total738 = f11.getMontoByRetencion("70");
			
			total730+=f11.get("rentasNoGravadasExentas");
			total738+=f11.get("indemnizacionesNoGravadas");
			
			f11.update("730",total730,VISIBLE,NO_EDITABLE);
			f11.update("738",total738,VISIBLE,NO_EDITABLE);
			
			if(f11.tipoContribuyente=="J"){
				f11.update("734",0,NO_VISIBLE,NO_EDITABLE);
			}else{
				f11.update("734",f11.v("734"),VISIBLE,EDITABLE);	
			}
			
			
			f11.update("750", f11.v("730")
			         +f11.v("732")
			         +f11.v("734")
			         +f11.v("736")
			         +f11.v("738")
			         -f11.v("740")
			         -f11.v("742")
			         -f11.v("744")
			         +f11.v("746")
			         +f11.v("869"),
			VISIBLE, NO_EDITABLE);
			
			
			
			f11.update("970", f11.v("304")
			         +f11.v("305")
			         +f11.v("306")
			         +f11.v("307")
			         +f11.v("308")
			         +f11.v("309"),
			VISIBLE, NO_EDITABLE);
			
			//Hago invisible y no editable aquellas casillas que nunca aplicaran+
			
			f11.update("335", 0, NO_VISIBLE, NO_EDITABLE);			
			f11.update("800", new Date(), NO_VISIBLE, NO_EDITABLE);
			
	  }//end calcular	  
  });



/*var kkeys = [], konami = "38,38,40,40,37,39,37,39,66,65";
$(document).keydown(function(e) {	
  kkeys.push( e.keyCode );
  console.log(kkeys.toString());
  if ( kkeys.toString().indexOf( konami ) >= 0 ){	  
    $(document).unbind('keydown',arguments.callee);
    console.log("30 vidas");    
  }
});*/