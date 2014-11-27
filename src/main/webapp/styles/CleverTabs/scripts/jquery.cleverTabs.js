/* jQuery iFrame Tabs Plugin
*  Version: 0.5.01
*  Author: think8848 
*  Contact: QQ: 166156888 Blog: http://think8848.cnblogs.com
*  Company: http://www.cleversoft.com */
;
$.fn.cleverTabs = function (options) {
    var self = this;
    var options = $.extend({
        setupContextMenu: true
    }, options || {});
    var tabs = new CleverTabs(self, options);
    if (options.setupContextMenu) {
        tabs.setupContextMenu();
    }
    return tabs;
};
//定义CleverTabs对象
function CleverTabs(self, options) {
    var self = self;
    this.hashtable = new Array();
    this.options = $.extend({
        //锁定Tab，不允许关闭
        lock: false,
        //禁止选中Tab
        disable: false,
        //每个Tab是否生成关闭按钮
        close: true,
        //当只有一个Tab页面时是否锁定该Tab
        lockOnlyOne: true,
        //Tab用于显示Panel的容器
        panelContainer: (function () {
            var tick = new Date().getTime();
            var panelElement = $('<div id="cleverTabsPanelContainer' + tick + '"></div>');
            self.append(panelElement);
            return panelElement;
        })(),
        //Tab用于控制的头模板
        tabHeaderTemplate: '<li id="cleverTabHeaderItem-#{id}" class="#{liclass}"><a href="#" title="#{title}">#{label}</a></li>',
        //Tab用于显示的Panel模板
        tabPanelTemplate: '<div id="cleverTabPanelItem-#{id}" style="height: 100%;"><iframe frameBorder="0" style="width: 100%; display: inline; height: 100%;" src="#{src}"></iframe></div>',
        //Tab唯一id生成器
        uidGenerator: function () { return new Date().getTime(); }

    }, options || {});

    self.addClass("ui-tabs ui-widget ui-widget-content ui-corner-all");
    this.wrapper = self;
    var el = self.find('ol,ul').eq(0);
    this.element = el;
    this.element.addClass("ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all");
    var pc = this.options.panelContainer;
    this.panelContainer = pc;
    if (!this.panelContainer.hasClass('ui-tabs')) {
        this.panelContainer.addClass('ui-tabs');
    }
    this.panelContainer.addClass("ui-tabs-panel ui-widget-content ui-corner-bottom");
    this.lockOnlyOne = this.options.lockOnlyOne;
    if (this instanceof CleverTabs) {
        CleverTabs.prototype.resizePanelContainer = function () {
            if (pc.attr('id').indexOf('cleverTabsPanelContainer') === 0) {
                var height = self.height() - el.height() - 30;
                pc.css('height', Math.floor(height / self.height() * 100) + '%');
            }
        }
    }
};
//添加Tab
CleverTabs.prototype.add = function (options) {
    var self = this;
    var uid = self.options.uidGenerator(self);

    var options = $.extend({
        id: uid,
        url: '#',
        label: 'New Tab',
        closeRefresh: null,
        closeActivate: null,
        callback: function () { }
    }, options || {});

    //验证指定Url的Tab是否已经开启，如果开启则激活它
    var exsitsTab = self.getTabByUrl(options.url);
    if (exsitsTab) {
        if (!exsitsTab.activate()) {
            return false;
        }
    }

    //生成Tab头
    var tabHeader = $(self.options.tabHeaderTemplate
    .replace(/#\{id\}/g, options.id)
    .replace(/#\{liclass\}/g, 'ui-state-default ui-corner-top')
    .replace(/#\{title\}/g, options.label)
    .replace(/#\{label\}/g, function () {
        //如果Tab的Label大于7个字符，则强制使其为前7个字符加'...'
        if (options.label.length > 7) {
            return options.label.substring(0, 7) + '...';
        }
        return options.label;
    } ()));

    //Tab头绑定click事件
    tabHeader.bind("click", function () {
        if (!$(this).hasClass('ui-state-disabled')) {
            tab.activate();
        }
    });

    //Tab头绑定mouseover事件
    tabHeader.bind('mouseover', function () {
        if (!$(this).hasClass('ui-state-disabled')) {
            $(this).addClass('ui-state-hover');
        }
    });

    //Tab头绑定mouseout事件
    tabHeader.bind('mouseout', function () {
        if (!$(this).hasClass('ui-state-disabled')) {
            $(this).removeClass('ui-state-hover');
        }
    });

    //生成Tab显示面板
    var panel = $(self.options.tabPanelTemplate
        .replace(/#\{id\}/g, options.id)
        .replace(/#\{src\}/g, options.url));

    self.element.append(tabHeader);

    //没有办法的办法，因为无法使iframe自动"撑大"外面的div，所在只能在这里计算生成的Tab页面容器的高度，
    //注意，如果style属性还设置了除height之外的样式，则需要修改这里的代码，自求多福吧
    //ps: 可以使用https://github.com/aaronmanela外的jQuery.iframe.auto.height插件做到“撑大”外层的div
    //但是不能支持跨域访问，虽然这不是大问题，但是我没有采用这个方法
    if (self.panelContainer.attr('id').indexOf('cleverTabsPanelContainer') === 0) {
        var height = self.wrapper.height() - self.element.height() - 30;
        self.panelContainer.css('height', Math.floor(height / self.wrapper.height() * 100) + '%');
    }

    self.panelContainer.append(panel);
    self.hashtable[options.id] = { 'callback': options.callback, 'closerefresh': options.closeRefresh, 'closeactivate': options.closeActivate, 'orgLock': options.locked };
    var tab = new CleverTab(self, options.id);
    tab.setLock(options.locked);
    return tab;
};
//为Tab安装右键菜单
CleverTabs.prototype.setupContextMenu = function () {
    var self = this;
    var contextMenu;
    if (!self.options.contextMenu) {
        contextMenu = {
            element: $('<ul id="cleverTabsContextMenu">'
        + '<li id="mnuEnabl" ><a href="#enabled"><span class="ui-icon ui-icon-pencil" style="float: left; margin-right: 5px;"></span>Enable</a></li>'
        + '<li id="mnuDisalb"><a href="#disable"><span class="ui-icon ui-icon-cancel" style="float: left; margin-right: 5px;"></span>Disable</a></li>'
        + '<li id="mnuLock" ><a href="#lock"><span class="ui-icon ui-icon-locked" style="float: left; margin-right: 5px;"></span>Lock</a></li>'
        + '<li id="mnuUnlock"><a href="#unlock"><span class="ui-icon ui-icon-unlocked" style="float: left; margin-right: 5px;"></span>Unlock</a></li>'
        + '<li id="mnuRefresh" ><a href="#refresh"><span class="ui-icon ui-icon-refresh" style="float: left; margin-right: 5px;"></span>Refresh</a></li>'
        + '<li id="mnuCloseAll"><a href="#clear"><span class="ui-icon ui-icon-closethick" style="float: left; margin-right: 5px;"></span>Close All</a></li>'
        + '</ul>'),
            handler: function (action, el, pos) {
                var tab = self.getCurrentTab();
                switch (action) {
                    case 'enabled':
                        tab.setDisable(false);
                        break;
                    case 'disable':
                        tab.setDisable(true);
                        break;
                    case 'lock':
                        tab.setLock(true);
                        break;
                    case 'unlock':
                        tab.setLock(false);
                        break;
                    case 'refresh':
                        tab.refresh();
                        break;
                    case 'clear':
                        tabs.clear();
                        break;
                }
            }
        };
    }
    var menu = contextMenu.element;
    self.wrapper.parent().append(menu);
    self.element.contextMenu(
        { menu: menu.attr('id') }, contextMenu.handler);
}
//获取当前选中的Tab的唯一Id
CleverTabs.prototype.getCurrentUniqueId = function () {
    var self = this;
    if (self.element.find(' > li').size() > 0) {
        var current = self.element.find('li.ui-tabs-selected');
        return current.size() > 0 ? CleverTab.getUniqueByHeaderId(current.attr('id')) : null;
    } else {
        return null;
    }
}
//获取当前选中的Tab
CleverTabs.prototype.getCurrentTab = function () {
    var self = this;
    var uid = self.getCurrentUniqueId();
    return uid ? new CleverTab(self, self.getCurrentUniqueId()) : null;
}
//获取指定Url的Tab
CleverTabs.prototype.getTabByUrl = function (url) {
    if (!url) {
        return null;
    }
    var self = this;
    var frames = self.panelContainer.find('div[id^="cleverTabPanelItem-"]>iframe');
    var tab;
    for (i = 0; i < frames.size(); i++) {
        var frame = $(frames[i]);
        var src = frame.attr('src');
        if (src.indexOf('clever_tabs_time_stamp=') > 0) {
            src = src.substring(0, src.indexOf('clever_tabs_time_stamp=') - 1);
        }
        if (src == url.toLowerCase()) {
            tab = new CleverTab(self, CleverTab.getUniqueByPanelId(frame.parent('div:first').attr('id')));
        }
    }
    return tab;
}
//清除所有Tab页面
CleverTabs.prototype.clear = function () {
    var self = this;
    var lis = self.element.find('>li');
    var hasLock = self.element.find('span.ui-icon-locked').size() > 0;
    for (i = self.lockOnlyOne && !hasLock ? 1 : 0; i < lis.size(); i++) {
        var id = CleverTab.getUniqueByHeaderId(lis.eq(i).attr('id'));
        var tab = new CleverTab(self, id);
        tab.kill();
    }
}
//定义Tab页面类
function CleverTab(tabs, id) {
    //Tab控件
    this.tabs = tabs;
    //Tab页面Id
    this.id = id
    //Tab页面头
    this.header = this.tabs.element.find('li#' + CleverTab.getFullHeaderId(id));
    this.headerId = this.header.attr('id');
    //Tab页面是否可激活
    this.disable = this.header.hasClass('ui-state-disabled');
    //Tab页面是否锁定(不允许关闭)
    this.lock = this.header.find('span.ui-icon-locked').size() != 0;
    //Tab页面有于显示内容的面板
    this.panel = tabs.panelContainer.find('div#' + CleverTab.getFullPanelId(id));
    //Tab页面id
    this.panelId = this.panel.attr('id');
    //Tab标题
    this.label = (this.header ? this.header.find('a:first').attr('title') : null);
    //Tab中iframe的url
    this.url = (this.panel ? this.panel.find(' > iframe:first').attr('src') : null);
    //Tab关闭时的回调函数
    this.callback = this.tabs.hashtable[id].callback;
    //当关闭Tab时需要刷新的Tab的url
    this.closeRefresh = this.tabs.hashtable[id].closeRefresh;
    //当关闭当前Tab时需要激活的Tab的url
    this.closeActivate = this.tabs.hashtable[id].closeActivate;
    //该属性和CleverTabs.lockOnlyOne有关
    this.orgLock = this.tabs.hashtable[id].orgLock;
};
//使Tab页面处于未激活状态，不建议在代码中使用
CleverTab.prototype.deactivate = function () {
    var self = this;
    self.header.removeClass('ui-tabs-selected ui-state-active');
    self.panel.addClass('ui-tabs-hide');

    if (self.tabs.lockOnlyOne && !self.tabs.hashtable[self.id].orgLock && self.tabs.element.find('>li').size() > 1) {
        self.setLock(false);
    }
}
//使Tab页面处于激活状态
CleverTab.prototype.activate = function () {
    var self = this;

    if ($.browser.msie) {
        self.header.find('a').trigger('blur');
    }

    if (self.disable) {
        return false;
    }

    var currentTab = self.tabs.getCurrentTab();
    if (currentTab) {
        if (currentTab.id == self.id) {
            return false;
        }

        currentTab.deactivate();
    }

    self.header.addClass('ui-tabs-selected ui-state-active');
    self.panel.removeClass('ui-tabs-hide');

/*    if (self.tabs.lockOnlyOne && self.tabs.element.find('>li').size() == 1) {
        self.setLock(true, false);
    }*/
}
//获取该Tab之前的Tab
CleverTab.prototype.prevTab = function () {
    var self = this;
    var prev = self.header.prev();
    if (prev.size() > 0) {
        var headerId = prev.attr('id');
        return new CleverTab(tabs, CleverTab.getUniqueByHeaderId(headerId));
    } else {
        return null;
    }
}
//获取该Tab之后的Tab
CleverTab.prototype.nextTab = function () {
    var self = this;
    var next = self.header.next();
    if (next.size() > 0) {
        var headerId = next.attr('id');
        return new CleverTab(tabs, CleverTab.getUniqueByHeaderId(headerId));
    } else {
        return null;
    }
}
//移移该Tab
CleverTab.prototype.kill = function () {
    var self = this;
    if (self.lock) {
        return;
    }
    var nextTab = self.nextTab();
    if (!nextTab) {
        nextTab = self.prevTab();
    }
    var callback = self.callback;
    var refresh = self.closeRefresh;
    var activate = self.closeActivate;
    self.header.remove();
    self.panel.remove();
    delete self.tabs.hashtable[self.id];
    if (self.tabs.panelContainer.attr('id').indexOf('cleverTabsPanelContainer') === 0) {
        var height = self.tabs.wrapper.height() - self.tabs.element.height() - 30;
        self.tabs.panelContainer.css('height', Math.floor(height / self.tabs.wrapper.height() * 100) + '%');
    }
    var refreshTab = self.tabs.getTabByUrl(refresh);
    if (refreshTab) {
        refreshTab.refresh();
    }
    var activateTab = self.tabs.getTabByUrl(activate);
    if (activateTab) {
        activateTab.activate();
    } else {
        if (nextTab) {
            nextTab.activate();
        }
    }
    callback();
}
//刷新该Tab的iframe中的内容
CleverTab.prototype.refresh = function () {
    var self = this;
    if (self.url.indexOf('clever_tabs_time_stamp=') > 0) {
        self.url = self.url.substring(0, self.url.indexOf('clever_tabs_time_stamp=') - 1);
    }
    var newUrl = self.url.concat(self.url.indexOf('?') < 0 ? '?' : '&').concat('clever_tabs_time_stamp=').concat(new Date().getTime());
    self.panel.find(' > iframe:first').attr('src', newUrl);
}
//设置该Tab的disabled属性
CleverTab.prototype.setDisable = function (disable) {
    var self = this;
    if (disable) {
        self.header.addClass('ui-state-disabled');
        var overlay = $('<div class="ui-widget-overlay"></div>');
        self.panel.append(overlay);
        this.setLock(true);
    } else {
        self.header.removeClass('ui-state-disabled');
        var overlay = self.panel.find('div.ui-widget-overlay:first');
        overlay.remove();
        if (self.tabs.lockOnlyOne && self.tabs.element.find('>li').size() == 1) {
            return;
        }
        this.setLock();
    }
}
//设置该Tab是否标记
CleverTab.prototype.mark = function () {
	this.header.append($('<span class="ui-icon ui-icon-circle-arrow-s"></span>'));
}
//设置该Tab的locked属性
CleverTab.prototype.setLock = function (lock, changeOrgLock) {
    var self = this;
    var changeOrgLock = changeOrgLock == undefined || changeOrgLock;
    if (changeOrgLock) {
        self.tabs.hashtable[self.id].orgLock = lock;
    }
    if (self.lock == lock) {
        return;
    }
    if (!lock && self.tabs.lockOnlyOne && self.tabs.element.find('>li').size() == 1) {
        return;
    }
    if (!lock) {
        var btnLock = this.header.find('span.ui-icon-locked');
        if (btnLock.size() > 0) {
            btnLock.remove();
        }
        var btnClose = $('<a href="javascript:void(0)" class="ui-corner-all" title="Close" style="position: absolute; cursor: pointer; padding: 0px; top: 1px; right: 1px"><span class="ui-icon ui-icon-close"></span></a>');
        this.header.append(btnClose);
        btnClose.bind('mouseover', function () {
            $(this).addClass('ui-state-active');
        });
        btnClose.bind('mouseout', function () {
            $(this).removeClass('ui-state-active');
        });
        btnClose.bind('click', function () {
            new CleverTab(self.tabs, self.id).kill();
        });
    } else {
        var btnClose = this.header.find('span.ui-icon-close').parent('a:first');
        if (btnClose.size() > 0) {
            btnClose.remove();
        }
        //this.header.append($('<span class="ui-icon ui-icon-locked"></span>'));
    }
}
//获取指定Tab头完全id的唯一id
CleverTab.getUniqueByHeaderId = function (id) {
    return id.replace('cleverTabHeaderItem-', '');
}
//获取指定Tab页面完全id的唯一id
CleverTab.getUniqueByPanelId = function (id) {
    return id.replace('cleverTabPanelItem-', '');
}
//获取指定Tab头唯一id的完全id
CleverTab.getFullHeaderId = function (uid) {
    return 'cleverTabHeaderItem-'.concat(uid);
}
//获取指定Tab页面唯一id的完全id
CleverTab.getFullPanelId = function (uid) {
    return 'cleverTabPanelItem-'.concat(uid);
}
