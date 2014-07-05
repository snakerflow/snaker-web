/**
 * jQuery wBox plugin
 * wBox是一个轻量级的弹出窗口jQuery插件，基于jQuery1.4.2开发，
 * 主要实现弹出框的效果，并且加入了很多有趣的功能，比如可img灯箱效果，callback函数，显示隐藏层，Ajax页面，iframe嵌入页面等功能
 * @name wBox
 * @author WangYongqing - http://www.js8.in（王永清 http://www.js8.in）
 * @version 0.1
 * @copyright (c) 2010 WangYongqing (js8.in)
 * @example Visit http://www.js8.in/mywork/wBox/ for more informations about this jQuery plugin
 */
(function($){
    //class为.wBox_close为关闭
    $.fn.wBox = function(options){
        var defaults = {
            wBoxURL: "wbox/",
            opacity: 0.5,//背景透明度
            callBack: null,
            noTitle: false,
			show:false,
			timeout:0,
			target:null,
			requestType:null,//iframe,ajax,img
            title: "wBox Title",
			drag:true,
			iframeWH: {//iframe 设置高宽
                width: 400,
                height: 300
            },
            html: ''//wBox内容
        },_this=this;
		this.YQ = $.extend(defaults, options);
        var  wBoxHtml = '<div id="wBox"><div class="wBox_popup"><table><tbody><tr><td class="wBox_tl"/><td class="wBox_b"/><td class="wBox_tr"/></tr><tr><td class="wBox_b"><div style="width:10px;">&nbsp;</div></td><td><div class="wBox_body">' + (_this.YQ.noTitle ? '' : '<table class="wBox_title"><tr><td class="wBox_dragTitle"><div class="wBox_itemTitle">' + _this.YQ.title + '</div></td><td width="20px" title="关闭"><div class="wBox_close"></div></td></tr></table> ') +
        '<div class="wBox_content" id="wBoxContent"></div></div></td><td class="wBox_b"><div style="width:10px;">&nbsp;</div></td></tr><tr><td class="wBox_bl"/><td class="wBox_b"/><td class="wBox_br"/></tr></tbody></table></div></div>', B = null, C = null, $win = $(window),$t=$(this);//B背景，C内容jquery div   
        this.showBox=function (){
            $("#wBox_overlay").remove();
			$("#wBox").remove();
            
            B = $("<div id='wBox_overlay' class='wBox_hide'></div>").hide().addClass('wBox_overlayBG').css('opacity', _this.YQ.opacity).dblclick(function(){
                _this.close();
            }).appendTo('body').fadeIn(300);
            C = $(wBoxHtml).appendTo('body');
            handleClick();
        }
        /*
         * 处理点击
         * @param {string} what
         */
        function handleClick(){
            var con = C.find("#wBoxContent");
			if (_this.YQ.requestType && $.inArray(_this.YQ.requestType, ['iframe', 'ajax','img'])!=-1) {
				con.html("<div class='wBox_load'><div id='wBox_loading'><img src='"+_this.YQ.wBoxURL+"loading.gif' /></div></div>");				
				if (_this.YQ.requestType === "img") {
					var img = $("<img />");
					img.attr("src",_this.YQ.target);
					img.load(function(){
						img.appendTo(con.empty());
						setPosition();
					});
				}
				else 
					if (_this.YQ.requestType === "ajax") {
						$.get(_this.YQ.target, function(data){
							con.html(data);
							C.find('.wBox_close').click(_this.close);
							setPosition();
						})
						
					}
					else {
						ifr = $("<iframe name='wBoxIframe' style='width:" + _this.YQ.iframeWH.width + "px;height:" + _this.YQ.iframeWH.height + "px;' scrolling='auto' frameborder='0' src='" + _this.YQ.target + "'></iframe>");
						ifr.appendTo(con.empty());
						ifr.load(function(){
							try {
								$it = $(this).contents();
								$it.find('.wBox_close').click(_this.close);
								fH = $it.height();//iframe height
								fW = $it.width();
								w = $win;
								newW = Math.min(w.width() - 40, fW);
								newH = w.height() - 25 - (_this.YQ.noTitle ? 0 : 30);
								newH = Math.min(newH, fH);
								if (!newH) 
									return;
								var lt = calPosition(newW);
								C.css({
									left: lt[0],
									top: lt[1]
								});
								
								$(this).css({
									height: newH,
									width: newW
								});
							} 
							catch (e) {
							}
						});
					}
				
			}
			else 
				if (_this.YQ.target) {
					$(_this.YQ.target).clone(true).show().appendTo(con.empty());
					
				}
				else 
					if (_this.YQ.html) {
						con.html(_this.YQ.html);
					}
					else {
						$t.clone(true).show().appendTo(con.empty());
					}         
            afterHandleClick();
        }
        /*
         * 处理点击之后的处理
         */
        function afterHandleClick(){     
            setPosition();
            C.show().find('.wBox_close').click(_this.close).hover(function(){
                $(this).addClass("on");
            }, function(){
                $(this).removeClass("on");
            });
            $(document).unbind('keydown.wBox').bind('keydown.wBox', function(e){
                if (e.keyCode === 27) 
                    _this.close();
                return true
            });
            typeof _this.YQ.callBack === 'function' ? _this.YQ.callBack() : null;
            !_this.YQ.noTitle&&_this.YQ.drag?drag():null;
			if(_this.YQ.timeout){
                setTimeout(_this.close,_this.YQ.timeout);
            }
				
        }
        /*
         * 设置wBox的位置
         */
        function setPosition(){
            if (!C) {
                return false;
            }
			
            var width = C.width(),  lt = calPosition(width);
            C.css({
                left: lt[0],
                top: lt[1]
            });
            var $h = $("body").height(), $wh = $win.height(),$hh=$("html").height();
            $h = Math.max($h, $wh);
            B.height($h).width($win.width())            
        }
        /*
         * 计算wBox的位置
         * @param {number} w 宽度
         */
        function calPosition(w){
            l = ($win.width() - w) / 2;
            t = $win.scrollTop() + $win.height() /9;
            return [l, t];
        }
        /*
         * 拖拽函数drag
         */
        function drag(){
            var dx, dy, moveout;
            var T = C.find('.wBox_dragTitle').css('cursor', 'move');
            T.bind("selectstart", function(){
                return false;
            });
            
            T.mousedown(function(e){
                dx = e.clientX - parseInt(C.css("left"));
                dy = e.clientY - parseInt(C.css("top"));
                C.mousemove(move).mouseout(out).css('opacity', 0.8);
                T.mouseup(up);
            });
            /*
             * 移动改变生活
             * @param {Object} e 事件
             */
            function move(e){
                moveout = false;
                if (e.clientX - dx < 0) {
                    l = 0;
                }
                else 
                    if (e.clientX - dx > $win.width() - C.width()) {
                        l = $win.width() - C.width();
                    }
                    else {
                        l = e.clientX - dx
                    }
                C.css({
                    left: l,
                    top: e.clientY - dy
                });
                
            }
            /*
             * 你已经out啦！
             * @param {Object} e 事件
             */
            function out(e){
                moveout = true;
                setTimeout(function(){
                    moveout && up(e);
                }, 10);
            }
            /*
             * 放弃
             * @param {Object} e事件
             */
            function up(e){
                C.unbind("mousemove", move).unbind("mouseout", out).css('opacity', 1);
                T.unbind("mouseup", up);
            }
        }
        
        /*
         * 关闭弹出框就是移除还原
         */
        this.close=function (){
            if (C) {
                B.remove();
                C.stop().fadeOut(300, function(){
                    C.remove();
                })
            }
        }
        /*
         * 触发click事件
         */		
        $win.resize(function(){
            setPosition();
        });
		_this.YQ.show?_this.showBox():$t.click(function(){
            _this.showBox();
            return false;
        });
		return this;
    };
})(jQuery);
