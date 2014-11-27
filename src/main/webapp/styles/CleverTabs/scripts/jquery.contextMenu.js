// jQuery Context Menu Plugin
//
// Version 1.01
//
// Cory S.N. LaViska
// A Beautiful Site (http://abeautifulsite.net/)
//
// More info: http://abeautifulsite.net/2008/09/jquery-context-menu-plugin/
//
// Terms of Use
//
// This plugin is dual-licensed under the GNU General Public License
//   and the MIT License and is copyright A Beautiful Site, LLC.
//
;

if (jQuery) (function () {
    $.extend($.fn, {

        contextMenu: function (o, callback) {
            var cleverContextMenu = {
                'position': 'absolute',
                'z-index': '99999',
                'display': 'none'
            };
            // Defaults
            if (o.menu == undefined) return false;
            if (o.inSpeed == undefined) o.inSpeed = 150;
            if (o.outSpeed == undefined) o.outSpeed = 75;
            // 0 needs to be -1 for expected results (no fade)
            if (o.inSpeed == 0) o.inSpeed = -1;
            if (o.outSpeed == 0) o.outSpeed = -1;
            // Loop each context menu
            $(this).each(function () {
                var el = $(this);
                var offset = $(el).offset();
                // Add contextMenu class
                $('#' + o.menu).addClass('cleverContextMenu ui-menu ui-widget ui-widget-content ui-corner-all');
                $('#' + o.menu).css(cleverContextMenu);
                $('#' + o.menu).find('>li').each(function () { $(this).addClass('ui-menu-item'); });
                $('#' + o.menu).find('>li a').each(function () { $(this).addClass('ui-corner-all'); });
                // Simulate a true right click
                $(this).mousedown(function (e) {
                    var evt = e;
                    evt.stopPropagation();
                    $(this).mouseup(function (e) {
                        e.stopPropagation();
                        var srcElement = $(this);
                        $(this).unbind('mouseup');
                        if (evt.button == 2) {
                            // Hide context menus that may be showing
                            $(".cleverContextMenu").hide();
                            // Get this context menu
                            var menu = $('#' + o.menu);

                            if ($(el).hasClass('ui-state-disabled')) return false;

                            // Detect mouse position
                            var d = {}, x, y;
                            if (self.innerHeight) {
                                d.pageYOffset = self.pageYOffset;
                                d.pageXOffset = self.pageXOffset;
                                d.innerHeight = self.innerHeight;
                                d.innerWidth = self.innerWidth;
                            } else if (document.documentElement &&
								document.documentElement.clientHeight) {
                                d.pageYOffset = document.documentElement.scrollTop;
                                d.pageXOffset = document.documentElement.scrollLeft;
                                d.innerHeight = document.documentElement.clientHeight;
                                d.innerWidth = document.documentElement.clientWidth;
                            } else if (document.body) {
                                d.pageYOffset = document.body.scrollTop;
                                d.pageXOffset = document.body.scrollLeft;
                                d.innerHeight = document.body.clientHeight;
                                d.innerWidth = document.body.clientWidth;
                            }
                            (e.pageX) ? x = e.pageX : x = e.clientX + d.scrollLeft;
                            (e.pageY) ? y = e.pageY : y = e.clientY + d.scrollTop;

                            // Show the menu
                            $(document).unbind('click');
                            $(menu).find('a').removeClass('ui-state-hover');
                            $(menu).css({ top: y, left: x }).fadeIn(o.inSpeed);
                            var ul = $(menu);
                            ul.outerWidth(ul.outerWidth() + 2);
                            // Hover events
                            $(menu).find('>li a').mouseover(function () {
                                $(this).addClass('ui-state-hover');
                            }).mouseout(function () {
                                $(this).removeClass('ui-state-hover');
                            });

                            // Keyboard
                            $(document).keyup(function (e) {
                                var keynum;
                                if ($.browser.msie) {
                                    keynum = e.keyCode;
                                }
                                else if (e.which) {
                                    keynum = e.which;
                                }
                                switch (keynum) {
                                    case 38: // up
                                        if ($(menu).find('>li a.ui-state-hover:first').size() == 0) {
                                            $(menu).find('>li:last a:first').addClass('ui-state-hover');
                                        } else {
                                            $(menu).find('>li a.ui-state-hover:first').removeClass('ui-state-hover').parent().prevAll('li:not(.ui-state-disabled)').eq(0).find('>a:first').addClass('ui-state-hover');
                                            if ($(menu).find('>li a.ui-state-hover:first').size() == 0) $(menu).find('>li:last a:first').addClass('ui-state-hover');
                                        }
                                        break;
                                    case 40: // down
                                        if ($(menu).find('>li a.ui-state-hover:first').size() == 0) {
                                            $(menu).find('>li:first a:first').addClass('ui-state-hover');
                                        } else {
                                            $(menu).find('>li a.ui-state-hover:first').removeClass('ui-state-hover').parent().nextAll('LI:not(.ui-state-disabled)').eq(0).find('a:first').addClass('ui-state-hover');
                                            if ($(menu).find('>li a.ui-state-hover:first').size() == 0) $(menu).find('>li:first a:first').addClass('ui-state-hover');
                                        }
                                        break;
                                    case 13: // enter
                                        $(menu).find('>li a.ui-state-hover:first').trigger('click');
                                        break;
                                    case 27: // esc
                                        $(document).trigger('click');
                                        break
                                }
                            });

                            // When items are selected
                            $('#' + o.menu).find('>li').unbind('click');
                            $('#' + o.menu).find('>li:not(.ui-state-disabled)').click(function () {
                                $(document).unbind('click').unbind('keyup');
                                $(".cleverContextMenu").hide();
                                // Callback
                                if (callback) callback($(this).find('>a:first').attr('href').substr(1), $(srcElement), { x: x - offset.left, y: y - offset.top, docX: x, docY: y });
                                return false;
                            });

                            // Hide bindings
                            setTimeout(function () { // Delay for Mozilla
                                $(document).click(function () {
                                    $(document).unbind('click').unbind('keyup');
                                    $(menu).fadeOut(o.outSpeed);
                                    return false;
                                });
                            }, 0);
                        }
                    });
                });

                // Disable text selection
                if ($.browser.mozilla) {
                    $('#' + o.menu).each(function () { $(this).css('ui-state-disabled'); });
                } else if ($.browser.msie) {
                    $('#' + o.menu).each(function () { $(this).bind('selectstart.disableTextSelect', function () { return false; }); });
                } else {
                    $('#' + o.menu).each(function () { $(this).bind('mousedown.disableTextSelect', function () { return false; }); });
                }
                // Disable browser context menu (requires both selectors to work in IE/Safari + FF/Chrome)
                $(el).add($('ul.cleverContextMenu')).bind('contextmenu', function () { return false; });

            });
            return $(this);
        },

        // Disable context menu items on the fly
        disableContextMenuItems: function (o) {
            if (o == undefined) {
                // Disable all
                $(this).find('>li').addClass('ui-state-disabled');
                return ($(this));
            }
            $(this).each(function () {
                if (o != undefined) {
                    var d = o.split(',');
                    for (var i = 0; i < d.length; i++) {
                        $(this).find('a[href="' + d[i] + '"]').parent().addClass('ui-state-disabled');

                    }
                }
            });
            return ($(this));
        },

        // Enable context menu items on the fly
        enableContextMenuItems: function (o) {
            if (o == undefined) {
                // Enable all
                $(this).find('>li.ui-state-disabled').removeClass('ui-state-disabled');
                return ($(this));
            }
            $(this).each(function () {
                if (o != undefined) {
                    var d = o.split(',');
                    for (var i = 0; i < d.length; i++) {
                        $(this).find('a[href="' + d[i] + '"]').parent().removeClass('ui-state-disabled');

                    }
                }
            });
            return ($(this));
        },

        // Disable context menu(s)
        disableContextMenu: function () {
            $(this).each(function () {
                $(this).addClass('ui-state-disabled');
            });
            return ($(this));
        },

        // Enable context menu(s)
        enableContextMenu: function () {
            $(this).each(function () {
                $(this).removeClass('ui-state-disabled');
            });
            return ($(this));
        },

        // Destroy context menu(s)
        destroyContextMenu: function () {
            // Destroy specified context menus
            $(this).each(function () {
                // Disable action
                $(this).unbind('mousedown').unbind('mouseup');
            });
            return ($(this));
        }

    });
})(jQuery);