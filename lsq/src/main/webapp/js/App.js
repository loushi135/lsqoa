Ext.ns("App");
var PortalItem = function(a, b, c) {
	this.panelId = a;
	this.column = b;
	this.row = c;
};
var UserInfo = function(d, a, c, f, e, b) {
	this.userId = d;
	this.fullname = a;
	this.depId = c;
	this.depName = f;
	this.rights = e;
	this.portalConfig = b;
};
var curUserInfo = null;
function isGranted(a) {
	if (curUserInfo.rights.indexOf("__ALL") != -1) {
		return true;
	}
	if (curUserInfo.rights.indexOf(a) != -1) {
		return true;
	}
	return false;
}
App.init = function() {
	Ext.QuickTips.init();
	Ext.BLANK_IMAGE_URL = __ctxPath + "/ext3/resources/images/default/s.gif";
	setTimeout(function() {
		Ext.get("loading").remove();
		Ext.get("loading-mask").fadeOut({
			remove : true
		});
	}, 1000);
	Ext.util.Observable.observeClass(Ext.data.Connection);
	Ext.data.Connection.on("requestcomplete", function(c, d, b) {
		if (d && d.getResponseHeader) {
			if (d.getResponseHeader("__timeout")) {
				Ext.ux.Toast.msg("操作提示：", "操作已经超时，请重新登录!");
				window.location.href = __ctxPath + "/index.jsp?randId="
						+ parseInt(1000 * Math.random());
			}
			if (d.getResponseHeader("__forbidden")) {
				Ext.ux.Toast.msg("系统访问权限提示：", "你目前没有权限访问：{0}", b.url);
			}
		}
	});
	Ext.Ajax.request({
		url : __ctxPath + "/system/getCurrentAppUser.do?random="
				+ Math.random(),
		method : "Get",
		success : function(d, g) {
			var f = Ext.util.JSON.decode(d.responseText);
			var c = f.user;
			var e = c.items;
			curUserInfo = new UserInfo(c.userId, c.fullname, c.depId,
					c.depName, c.rights, e);
			var b = Ext.getCmp("centerTabPanel");
			var h = b.add(new AppHome());
			
			if(__modifyPWD){
				App.clickTopTab('ProfileForm'); 
			}
			
			
			b.activate(h);
		}
	});
	var a = new IndexPage();
};
Ext.Ajax.request({
		url:__ctxPath+'/system/needNoticeAnnounceRemind.do',
		method:'post',
		success:function(response,options){
			var object = Ext.util.JSON.decode(response.responseText);
			var msg = object.msg;
			if(msg == true){
				updateWin.show();
			}
		}
	});
App.clickTopTab = function(f, c, a, e) {
	if (a != null) {
		a.call(this);
	}
	var b = Ext.getCmp("centerTabPanel");
	var d = b.getItem(f);
	if (d == null) {
		$ImportJs(f, function(g) {
			Ext.apply(g,{closable:true});
			d = b.add(g);
			b.activate(d);
		}, c);
	} else {
		if (e != null) {
			e.call(this);
		}
		Ext.apply(d,{closable:true});
		b.activate(d);
	}
};
App.MyDesktopClickTopTab = function(id, params, precall, callback) {
	if (precall != null) {
		precall.call(this);
	}
	var tabs = Ext.getCmp("centerTabPanel");
	var tabItem = tabs.getItem(id);
	if (tabItem == null) {
		$ImportJs(id, function(view) {
			Ext.apply(view,{closable:true});
			tabItem = tabs.add(view);
			tabs.activate(tabItem);
		}, params);
	} else {
		tabs.remove(tabItem);
		var str = "new " + id;
		if (params != null) {
			str += "(params);";
		} else {
			str += "();";
		}
		var view = eval(str);
		Ext.apply(view,{closable:true});
		tabItem = tabs.add(view);
		tabs.activate(tabItem);
	}
};
App.clickNode = function(a) {
	if (a.id == null || a.id == "" || a.id.indexOf("xnode") != -1) {
		return;
	}
	App.clickTopTab(a.id);
};
App.MyDesktopClick = function() {
	var a = Ext.getCmp("MyDesktop");
	a.expand(true);
	App.clickTopTab("AppHome");
};
App.Logout = function() {
	Ext.Ajax.request({
		url : __ctxPath + "/j_logout.do",
		success : function() {
			window.location.href = __ctxPath + "/login.jsp";
		}
	});
};

Ext.ux.PageSizePlugin = function() {
    Ext.ux.PageSizePlugin.superclass.constructor.call(this, {
        store: new Ext.data.SimpleStore({
            fields: ['text', 'value'],
            data: [['10', 10], ['20', 20], ['25', 25], ['30', 30], ['50', 50], ['100', 100],['全部', 99999999]]
        }),
        mode: 'local',
        displayField: 'text',
        valueField: 'value',
        editable: false,
        allowBlank: false,
        triggerAction: 'all',
        width: 60
    });
};

Ext.extend(Ext.ux.PageSizePlugin, Ext.form.ComboBox, {
    init: function(paging) {
        paging.on('render', this.onInitView, this);
    },
    
    onInitView: function(paging) {
        paging.add('-',
            this,
            '条/页'
        );
        this.setValue(paging.pageSize);
        this.on('select', this.onPageSizeChanged, paging);
    },
    
    onPageSizeChanged: function(combo) {
        this.pageSize = parseInt(combo.getValue());
        compage = parseInt(combo.getValue());
        this.doLoad(0);
    }
});
    //解决Ext.form.DateField在浏览器中显示可能有问题，界面将会拉的很长很长。
	Ext.override(Ext.menu.DateMenu,{
			render : function(){
					Ext.menu.DateMenu.superclass.render.call(this);
					if(Ext.isGecko|| Ext.isSafari||Ext.isChrome){
						this.picker.el.dom.childNodes[0].style.width = '178px';
						this.picker.el.dom.style.width = '178px';
					}
				}
	});
	//解决extjs 谷歌浏览器grid 里面cm错位
	Ext.grid.ColumnModel.override({
		getTotalWidth: function(includeHidden) {
			var off = 0;
//			if(!Ext.isDefined(Ext.isChrome19)){
//				Ext.isChrome19 = /\bchrome\/19\b/.test(navigator.userAgent.toLowerCase());
//				};
			if (Ext.isChrome){
				off = 2;
			};
			if (!this.totalWidth) {
				this.totalWidth = 0;
				for (var i = 0, len = this.config.length; i < len; i++) {
					if (includeHidden || !this.isHidden(i)) {
						this.totalWidth += this.getColumnWidth(i)+off;
					};
				};
			};
			return this.totalWidth;
			}
		});
	//使得所有window弹出不能超出边界  http://www.sencha.com/forum/archive/index.php/t-47164.html
	Ext.override(Ext.Window, {
//		constrainHeader:true,//True表示为将window header约束在视图中显示
		constrain :true//True表示为将window约束在视图中显示
	});
	
	//解决 使用form布局时 使用hide()方法或者setVisible()隐藏textfield 等组件 fieldLabel不隐藏 问题
	Ext.layout.FormLayout.prototype.trackLabels = true;
Ext.onReady(App.init);