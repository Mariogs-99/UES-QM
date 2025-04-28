/*requiere jquery*/

function showTab(tabId){
	jQuery("#paneles .panel").css({display:"none"});
	if(tabId!=null){
		jQuery("#"+tabId).show();
	}
}

function tab(jQueryA){
	jQueryA.parents("ul.separators:first").find("a").each(
		function(index, object){
			var a	=	jQuery(object);
			jQuery("."+a.data("containerClass")).hide();
			
		}
	);
	jQuery("."+jQueryA.data("containerClass")).show();
}

jQuery(document).ready(
	function(){
		jQuery("ul.internalMenu > li > a").click(function(e){e.preventDefault();});
		jQuery("ul.internalMenu > li").mouseenter(
			function(){
				jQuery(this).find("ul").css({"top":jQuery(this).height()}).show();
			}
		);
		jQuery("ul.internalMenu > li").mouseleave(
			function(){
				jQuery(this).find("ul:first").hide();
			}
		);
		jQuery("#selector-paneles ul.subMenu a").click(
			function(event){
				event.preventDefault();
				var ref	=	jQuery(this).data("domElementRef");
				showTab(ref);
				
			}		
		);
		
		if(getUrlParam("tabId").trim().length>0){
			showTab(getUrlParam("tabId").trim());
		}else{
			jQuery("#paneles div.panel:first").show();
		}
		jQuery("ul.separators a").click(
			function(){
				var a	=	jQuery(this);
				a.parents("ul.separators:first").find("a").removeClass("active");
				a.addClass("active");
				tab(a);
			}
		)
		jQuery("ul.separators a:first").click();
		
	}
	
	
);