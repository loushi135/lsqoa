var count = 0;
var IndexPage = Ext.extend(Ext.Viewport, {
	messageTipBar : null,
	top : new Ext.Panel({
				region : "north",
				id : "__nortPanel",
				contentEl : "app-header",
				height : 60
			}),
	center : null,
	west : new Ext.Panel({
				region : "west",
				id : "west-panel",
				title : "导航",
				iconCls : "menu-navigation",
				split : true,
				width : 180,
				autoScroll : true,
				layout : "accordion",
				collapsible : true,
				items : []
			}),
	south : new Ext.Panel({
				region : "south",
				height : 28,
				border : false,
				bbar : [
						"->",
						{
							xtype : "tbfill"
						},
						{
							xtype : "tbtext",
							text : __companyName + "办公协同管理系统",
							id : "toolbarCompanyName"
						},
						{
							xtype : "tbseparator"
						},
						new Ext.Toolbar.TextItem('技术支持 <a href="'
								+ __appSupportMail + '" target="_blank">'
								+ __appSupport + '</a>'), {
							xtype : "tbseparator"
						}, {
							pressed : false,
							text : "便签",
							iconCls : "tipsTile",
							handler : function() {
								App.clickTopTab("PersonalTipsView");
							}
						}, {
							xtype : "tbseparator"
						}, {
							text : "收展",
							iconCls : "btn-expand",
							handler : function() {
								var a = Ext.getCmp("__nortPanel");
								if (a.collapsed) {
									a.expand(true);
								} else {
									a.collapse(true);
								}
							}
						}]
			}),
	hideMessageTipBar:function(){
		messageTipBar.hide();
	},
	constructor : function() {
		messageTipBar=new Ext.Toolbar({
			border : false,
			hidden : true,
			width:92, //92 | 98 | 105
			height:25,
			id:"messageTipBar",
			style : "margin-top:2px;background-color:#F8FDFF;border-bottom-color:#F0F7F8; background-image:url();",
			renderTo : "messageTipHtml",
			items : {
				id : "messageTip",
//				hidden : true,
//				height:20,
				handler : function() {
					var a = Ext.getCmp("messageTip");
					var b = Ext.getCmp("win");
					if (b == null) {
						new MessageWin().show();
					}else{
						return;					
					}
					// a.hide();
					count = count - 1;
					if (count != 0) {
						messageTipBar.setWidth(count<10?92:count<100?98:count<1000?105:count<10000?111:120);
						a.setText('<div style="height:20px;"><img src="'
										+ __ctxPath
										+ '/images/newpm.gif" style="height:12px;"/>你有<strong style="color: red;">'
										+ count + "</strong>信息</div>")
					} else {
//						a.hide();
						messageTipBar.hide();
					}

				}
			}
		}),
		this.center = new Ext.TabPanel({
					id : "centerTabPanel",
					region : "center",
					deferredRender : true,
					enableTabScroll : true,
					activeTab : 0,
					defaults : {
						autoScroll : true
					},
					items : [],
					plugins : new Ext.ux.TabCloseMenu(),
					listeners : {
						"beforeadd" : function(tab, com, index) {
							if (com.getId() != undefined && com.getId() != ""
									&& com.getId() != null
									&& com.getId() == "AppHome")
								return;
							Ext.apply(com, {
										closable : true
									});
						}
					}
				});
		IndexPage.superclass.constructor.call(this, {
					layout : "border",
					items : [this.top, this.west, this.center, this.south]
				});
		this.afterPropertySet();
		this.loadWestMenu();
		this.buildToolBar();
	},
	buildToolBar : function() {

		var toolbar = new Ext.Toolbar({
			id : 'down-tool',
			renderTo : 'down',
			cls : 'x-toolbar-banner',
			items : [{
						id : "searchContent",
						xtype : "textfield",
						width : 100
					}, {
						id : "searchType",
						width : 60,
						xtype : "combo",
						mode : "local",
						editable : false,
						triggerAction : "all",
						store : [["news", "新闻"], ["mail", "邮件"],
								["notice", "公告"], ["document", "文档"]],
						value : "news"
					}, {
						xtype : "button",
						text : "搜索",
						iconCls : "search",
						handler : function() {
							var b = Ext.getCmp("searchContent").getValue();
							var a = Ext.getCmp("searchType").value;
							if (a == "news") {
								App.clickTopTab("SearchNews", b, function() {
											AppUtil.removeTab("SearchNews");
										});
							} else {
								if (a == "mail") {
									App.clickTopTab("SearchMail", b,
											function() {
												AppUtil.removeTab("SearchMail");
											});
								} else {
									if (a == "notice") {
										App.clickTopTab("SearchNotice", b,
												function() {
													AppUtil
															.removeTab("SearchNotice");
												});
									} else {
										if (a == "document") {
											App.clickTopTab("SearchDocument",
													b, function() {
														AppUtil
																.removeTab("SearchDocument");
													});
										}
									}
								}
							}
						}
					}, "-", Ext.getCmp("ChangeTheme")]
		});
		return toolbar;
	},

	afterPropertySet : function() {
		var a = this.center;
		var c = function(f) {
			var d = Ext.getCmp("messageTip");
			var g = Ext.getCmp("win");
			var e = Ext.getCmp("wind");
			// if (f > 0 && g == null && e == null) {
			if (f > 0) {
				messageTipBar.setWidth(f<10?92:f<100?98:f<1000?105:f<10000?111:120);
				d.setText('<div style="height:20px;"><img src="'
								+ __ctxPath
								+ '/images/newpm.gif" style="height:12px;"/>你有<strong style="color: red;">'
								+ f + "</strong>信息</div>");
				soundManager.play("msgSound");
				d.show();
				messageTipBar.show();
			} else {
//				d.hide();
				messageTipBar.hide();
			}
		};
		var b = function() {
			Ext.Ajax.request({
						url : __ctxPath + "/info/countInMessage.do",
						method : "POST",
						success : function(e, f) {
							var d = Ext.util.JSON.decode(e.responseText);
							count = d.count;
							c(count);
							setTimeout(b, 1000 * 60);
						},
						failure : function(d, e) {
						},
						scope : this
					});
		};
		setTimeout(function() {
					setInterval("CalConv()", 1000);
					soundManager = new SoundManager();
					soundManager.url = __ctxPath + "/js/sound/swf/";
					soundManager.beginDelayedInit();
					soundManager.debugMode = false;
					soundManager.consoleOnly = false;
					soundManager.onload = function() {
						soundManager.createSound({
									id : "msgSound",
									url : __ctxPath + "/js/sound/mp3/msg.mp3"
								});
						b();
					};
				}, 100);
	},
	loadWestMenu : function() {
		var westPanel = Ext.getCmp("west-panel");
		Ext.Ajax.request({
			url : __ctxPath + "/panelTreeMenu.do",
			success : function(response, options) {
				var arr = eval(response.responseText);
				var __activedPanelId = getCookie("__activedPanelId");
				for (var i = 0; i < arr.length; i++) {
					var doc = strToDom(arr[i].subXml);
					var root = doc.documentElement || doc;
					var panel = new Ext.tree.TreePanel({
								id : arr[i].id,
								title : arr[i].text,
								iconCls : arr[i].iconCls,
								layout : "fit",
								animate : true,
								border : false,
								autoScroll : true,
								loader : new xpsoft.ux.TreeXmlLoader({
											preloadChildren : true
										}),
								root : new Ext.tree.AsyncTreeNode({
											text : root.tagName,
											xmlNode : root
										}),
								listeners : {
									"click" : App.clickNode
								},
								rootVisible : false
							});
					westPanel.add(panel);
					panel.on("expand", function(p) {
						var expires = new Date();
						expires.setDate(expires.getDate() + 30);
						setCookie("__activedPanelId", p.id, expires, __ctxPath);
					});
					if (arr[i].id == __activedPanelId) {
						westPanel.layout.activeItem = panel;
					}
				}
				westPanel.doLayout();
			}
		});
	}
});