/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

                             $(document).ready(function() {
                            $('.navbar a.dropdown-toggle').on('click', function(e) {
                                var $el = $(this);
                                var $parent = $(this).offsetParent(".dropdown-menu");
                                $(this).parent("li").toggleClass('open');

                                if(!$parent.parent().hasClass('nav')) {
                                    $el.next().css({"top": $el[0].offsetTop, "left": $parent.outerWidth() - 4});
                                }

                                $('.nav li.open').not($(this).parents("li")).removeClass("open");

                                return false;
                            });
                        });
