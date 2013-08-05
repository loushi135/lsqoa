Ext.ns("App");
App.TreeLoader = Ext.extend(Ext.ux.tree.XmlTreeLoader, {
			processAttributes : function(a) {
				if (a.tagName == "Function") {
					a.leaf = true;
				} else {
					if (a.tagName == "Item") {
						a.loaded = true;
						a.expanded = true;
					} else {
						if (a.tagName == "Items") {
							a.loaded = true;
							a.expanded = true;
						}
					}
				}
			}
		});
var RoleGrantRightView = function(c, a) {
	var b = new Ext.ux.tree.CheckTreePanel({
				title : "为角色[" + a + "]授权",
				id : "roleGrantView",
				autoScroll : true,
				rootVisible : false,
				loader : new App.TreeLoader({
							dataUrl : __ctxPath + "/system/grantXmlAppRole.do"
						}),
				root : new Ext.tree.AsyncTreeNode({
							expanded : true
						}),
				tools : [{
							id : "refresh",
							qtip : "重新加载树",
							handler : function() {
								b.getRootNode().reload();
							}
						}]
			});
	var d = new Ext.Window({
				id : "RoleGrantView",
				title : "角色授权设置",
				width : 600,
				height : 450,
				modal : true,
				layout : "fit",
				plain : true,
				bodyStyle : "padding:5px;",
				buttonAlign : "center",
				items : [b],
				buttons : [{
					text : "保存",
					iconCls : "btn-save",
					handler : function() {
						Ext.Ajax.request({
									url : __ctxPath + "/system/grantAppRole.do",
									method : "POST",
									params : {
										roleId : c,
										rights : b.getValue().toString()
									},
									success : function(e, f) {
										Ext.ux.Toast.msg("操作提示",
												"你已经成功为角色[<b>{0}</b>]进行了授权", a);
										d.close();
									},
									failure : function(e, f) {
										Ext.ux.Toast
												.msg("操作信息", "授权出错，请联系管理员！");
									},
									scope : this
								});
					}
				}, {
					text : "取消",
					iconCls : "btn-cancel",
					handler : function() {
						d.close();
					}
				}]
			});
	d.show();
	
	setTimeout(loadRoleData,1000,c,b);
};
function loadRoleData(roleId,treePanel){
		Ext.Ajax.request({
				url : __ctxPath + "/system/getAppRole.do",
				method : "POST",
				params : {
					roleId : roleId
				},
				success : function(e, g) {
					var f = Ext.util.JSON.decode(e.responseText);
					if (f.data.rights != null) {
						treePanel.setValue(f.data.rights);
					}
				},
				failure : function(e, f) {
					Ext.ux.Toast.msg("操作信息", "加载权限出错！");
				},
				scope : this
			});
}