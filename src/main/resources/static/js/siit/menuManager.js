/*
 menuManager
 * requiere jquery
 * requiere jsonSiit.js
 * menuManagerUtils.js
 */
var menuManager = {
    navElement: "",
    source: "./sec/menu/json",
    data: {},
    configure: function() {

        this.loadConfiguration();
    },
    loadConfiguration: function() {

        jQuery.getJSON(this.source, function(data) {
            menuManager.data = data;
            menuManagerUtils.data = data;
            if (data) {
                menuManager.createNavigation();
            }
        });
    },
    createNavigation: function() {
        this.createNav();
        if (jQuery("nav.mainNav").length > 0) {
            this.createModules();
            this.addModuleBehaviors();
        }
    },
    addModuleBehaviors: function() {
        jQuery("nav.mainNav").mouseenter(
                function() {
                    jQuery(this).stop();
                    jQuery(this).animate({left: 0}, 200);
//                    jQuery(".mainPanel").animate({"margin-left": 105}, 200);
                    jQuery(this).find("dl").stop();
                    jQuery(this).find("dl").show();
                }
        );
        jQuery("nav.mainNav").mouseleave(
                function() {
                    jQuery(this).stop();
                    jQuery(this).animate({left: -50}, 200);
//                    jQuery(".mainPanel").animate({"margin-left": 55}, 200);
                    jQuery(this).find("dl").stop();
                    jQuery(this).find("dl").hide();
                    jQuery(this).find("div.dashboardPanel").css({display: "none"});

                }
        );

        jQuery("dd.mainModule > a.appLinkModule").click(
                function() {
                    jQuery("nav.mainNav div.dashboardPanel div.dashboardPages *").remove();
                    pages = jQuery("nav.mainNav div.dashboardPages");
                    jQuery("nav.mainNav a.appLinkModule").removeClass("moduleActive");
                    jQuery(this).addClass("moduleActive");
                    resizePanel();
                    var module = menuManagerUtils.getModuleById(jQuery(this).data("moduleId"));
                    if (module !== null) {
                        jQuery("nav.mainNav div.dashboardPanel h3").text(module.name);
                        if (module.pages.length > 0) {
                            for (var i = 0; i < module.pages.length; i++) {
                                var div = jQuery("<div>").addClass("unitPage");
                                var subdiv = jQuery("<div>").addClass("unitPage-i");

                                var a = jQuery("<a>", {"class": "module", "id": module.pages[i].id, "title": module.pages[i].name + " (" + module.pages[i].id + ")"}).text(module.pages[i].name).attr("href", module.pages[i].url);
                                subdiv.append(a);
                                div.append(subdiv);
                                if (typeof module.pages[i].pages === "object") {
                                    if (module.pages[i].pages.length > 0) {
                                        a.addClass("subL2");
                                        menuManager.createSub(div, module.pages[i].pages);

                                    }
                                }
                                pages.append(div);
                            }
                            menuManager.setBehaviorSubLevel();
                            jQuery("nav.mainNav div.dashboardPanel").css({display: "block"});
                            return false;
                        } else {
                            //go to url;
                        }
                    }
                }
        );
    },
    setBehaviorSubLevel: function() {



        jQuery("div.unitPage a.subL2").click(
                function(e) {
                    try {
                        e.preventDefault();
                        var ths = jQuery(this).parents(".unitPage:first");
                        act = false;
                        if (ths.is(".active")) {
                            act = true;
                        } else {
                            act = false;
                        }
                        jQuery(".unitPage").removeClass("active");
                        if (!act) {
                            ths.addClass("active");
                        }

                        ths.find("> .subContainerL2").css("width", jQuery(".dashboardPages").innerWidth() * 1);
                        ths.find("> .subContainerL2").css("left", (-0.95) * ths.position().left);
                        jQuery("div.unitPage").css("z-index", 8000);
                        ths.css("z-index", 9000);
                    } catch (e) {
                        alert(e);
                    }

                }
        );

    },
    createSubL: function(page) {
        var d = jQuery("<div>", {"class": "subLd"});
        var a = jQuery("<a>", {"class": "subLa", "href": page.url, "title": page.name + " (" + page.id + ")"});
        a.html(page.name);
        if (typeof page.id === "string") {
            a.attr("id", page.id);
        }
        return d.append(a);
    },
    createSub: function(container, pages) {
        var subContainer = jQuery("<div>", {"class": "subContainerL2"});
        subContainer.append(jQuery("<h4>").text(container.find("a.subL2:first").text()));
        for (var e = 0; e < pages.length; e++) {
            container.append(subContainer.append(menuManager.createSubL(pages[e])));
        }

    },
    createModules: function() {
        try {
            jQuery.each(this.data.menuMap.modules, function(index, module) {
                dd = jQuery("<dd>").addClass("mainModule");
                dd_a = jQuery("<a>").addClass("appLinkModule")
                        .attr("href", module.url)
                        .attr("data-module-id", module.id)
                        .text(module.name);
                dd.append(dd_a);
                jQuery("nav.mainNav").find("dt").append(dd);
            });
        } catch (ex) {
            alert(ex);
        }
    },
    createNav: function() {
        try {
            nav = jQuery("<nav>").addClass("mainNav");
            divLogo = jQuery("<div>").addClass("appLogo").attr("id", "appLogo_");
            if (this.data.siglas !== null) {
                divLogo.append(jQuery("<span>").text(this.data.siglas));
            }
            nav.append(divLogo);
            board = jQuery("<div>", {"class": "dashboardPanel alpha60"});
            h3board = jQuery("<h3>").addClass("modulePath");
            boardPages = jQuery("<div>").addClass("dashboardPages");
            board.append(h3board);
            board.append(boardPages);
            nav.append(board);
            dl = jQuery("<dl>");
            dt = jQuery("<dt>");
            dl.append(dt);
            nav.append(dl);
            jQuery("body").append(nav);
        } catch (Ex) {
            alert(Ex);
        }

    }

};

resizePanel = function() {
    jQuery("nav.mainNav div.dashboardPanel").css({"width": jQuery(window).width() - jQuery("nav.mainNav div#appLogo_:first").innerWidth(),
        height: jQuery(window).height(),
        left: jQuery("nav.mainNav div#appLogo_:first").innerWidth() - 2});
    jQuery("section.mainPanel").css({"width": jQuery(window).width() - 125});
//    jQuery("section.mainPanel").css({"width": jQuery(window).width() - 125});
//    jQuery("section.mainPanel").css({"width": jQuery(window).width() - 125});
    jQuery(".subContainerL2").css("width", jQuery(".dashboardPages").innerWidth() * 1);

    //para ajustar las paginas actuales de cada una de las tablas en pantalla

    jQuery("div.divPaginator a.active").each(
            function(index, obj) {

                var a = jQuery(obj);
                var pos = a.parents("div.divPaginator:first").innerWidth() / 2;
                a.parents("div.paginator:first").css({"left": pos - a.position().left - (a.innerWidth() / 2)});
            }
    );



};

jQuery(window).resize(function() {
    resizePanel();
});
