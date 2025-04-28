var menuManagerUtils={
	data:null,	
	getModuleById:function(moduleId){
		try{
			returnModule	=	null;
			if(this.data!=null){
				for(var i=0; i<this.data.menuMap.modules.length; i++){
					
					if(this.data.menuMap.modules[i].id == moduleId){
						returnModule	=	this.data.menuMap.modules[i];
					}
				}			
			}
		}catch(ex){
			alert("menuManagerUtils.getModuleById:"+ex);
		}
		return returnModule;
	}
};