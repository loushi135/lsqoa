Ext.ns("ProDefinitionSetting");
var ProDefinitionSetting = function(a, c) {
	this.defId = a;
	this.name = c;
	var b = new Ext.Panel({
				title : "流程示意图",
				layout : "fit",
				width : 480,
				split : true,
				region : "west",
				autoScroll : true,
				margin : "5 5 5 5",
				autoLoad : {
					url : __ctxPath + "/flow/processImage.do?defId="
							+ this.defId
				}
			});
	var e = this.getRightNorthPanel(a);
	var d = this.getRightCenterPanel(a);
	var f = new Ext.Panel({
				region : "center",
				layout : "anchor",
				height : 800,
				autoScroll : true,
				items : [e, d]
			});
	var g = new Ext.Panel({
				id : "ProDefinitionSetting" + this.defId,
				title : "流程设置－" + this.name,
				layout : "border",
				autoScroll : true,
				iconCls : "btn-setting",
				items : [b, f]
			});
	return g;
};
ProDefinitionSetting.prototype.getRightNorthPanel = function(a) {
	var f = new Ext.Toolbar({
		items : [{
			text : "保存",
			iconCls : "btn-save",
			handler : function() {
				var k = [];
				for (i = 0, cnt = c.getCount(); i < cnt; i += 1) {
					var j = c.getAt(i);
					if (j.data.assignId == "" || j.data.assignId == null) {
						j.set("assignId", -1);
					}
					if (j.data.isSigned == "" || j.data.isSigned == null
							|| j.data.isSigned == "null") {
						j.set("isSigned", 0);
					}
					if (j.dirty) {
						k.push(j.data);
					}
				}
				if (k.length == 0
						&& Ext.getCmp(a + 'subRefFieName').value == Ext
								.getCmp(a + 'subRefFieName').originalValue
						&& Ext.getCmp(a + 'printUserIds').value == Ext.getCmp(a
								+ 'printUserIds').originalValue) {
					Ext.ux.Toast.msg("信息", "没有对数据进行任何更改");
					return;
				}
				Ext.Ajax.request({
					method : "post",
					url : __ctxPath + "/flow/saveProUserAssign.do",
					success : function(l) {
						Ext.ux.Toast.msg("操作信息", "成功设置流程表单");
						c.reload();
						e.getView().refresh();
						Ext.Ajax.request({
							url : __ctxPath
									+ "/flow/getDefProSubjectDef.do?id=" + a,
							method : "POST",
							success : function(e, f) {
								var d = Ext.util.JSON.decode(e.responseText);
								Ext.getCmp(a + 'subRefFieName')
										.setValue(d.data.defaultVal);
								Ext.getCmp(a + 'subRefFieName').originalValue = d.data.defaultVal;

								Ext.getCmp(a + 'subRef.pId')
										.setValue(d.data.id);

								Ext.getCmp(a + 'printUserNames').originalValue = d.printUserNames;
								Ext.getCmp(a + 'printUserNames')
										.setValue(d.printUserNames);

								Ext.getCmp(a + 'printUserIds').originalValue = d.printUserIds;
								Ext.getCmp(a + 'printUserIds')
										.setValue(d.printUserIds);

							},
							failure : function(d, e) {
							},
							scope : this
						});
					},
					failure : function(l) {
						Ext.MessageBox.show({
									title : "操作信息",
									msg : "信息保存出错，请联系管理员！",
									buttons : Ext.MessageBox.OK,
									icon : "ext-mb-error"
								});
					},
					params : {
						data : Ext.encode(k),
						subRefFieName : Ext.getCmp(a + 'subRef.tmp').value == ''
								? Ext.getCmp(a + 'subRefFieName').value
								: Ext.getCmp(a + 'subRef.tmp').value,
						subRefLabel : Ext.getCmp(a + 'subRef.tmp').value == ''
								? Ext.getCmp(a + 'subRefLabel').value
								: Ext.getCmp(a + 'subRefFieName').value,
						defId : a,
						pId : Ext.getCmp(a + 'subRef.pId').value,
						printUserIds : Ext.getCmp(a + 'printUserIds').value,
						printUserNames : Ext.getCmp(a + 'printUserNames').value
						// 初始化赋值defId
					}
				});
			}
		}]
	});
	f.add({
				text : '流程主题设置:'
			}, {
				id : this.defId + "subRefLabel",
				xtype : 'hidden',
				value : ''
			}, {
				id : this.defId + "subRef.pId",
				xtype : 'hidden',
				value : ''
			}, {
				id : this.defId + "subRef.tmp",
				xtype : 'hidden',
				value : ''
			},// 初始化的时候不会选中,暂时存储refFiled
			// {id:this.defId+"defId",xtype : 'hidden',value:this.defId},
			new Ext.form.ComboBox({
						id : this.defId + "subRefFieName",
						name : "subRefFieName",
						editable : true,
						enableKeyEvents : true,
						triggerAction : 'all',
						emptyText : '请选择或输入流程主题',
						valueField : 'name',
						displayField : 'label',
						// value:'',//初始化赋值
						triggerAction : "all",
						store : new Ext.data.JsonStore({
									url : __ctxPath
											+ '/flow/getFiledProSubjectDef.do?defId='
											+ this.defId,
									root : 'result',
									autoLoad : true,
									fields : ['name', 'label']
								}),
						listeners : {
							select : function(combo, record, index) {
								Ext.getCmp(a + 'subRefLabel')
										.setValue(record.data.label);
								Ext.getCmp(a + 'subRef.tmp').setValue(''); // 清空tmp

							},
							keypress : function() {
								Ext.getCmp(a + 'subRefLabel').setValue("");// 在编辑输入时清除选
								// 择的内容
							}
						}
					}));

	f.add({
				text : '打印权限设置:'
			}, {
				id : this.defId + "printUserIds",
				xtype : 'hidden',
				value : ''
			}, {
				id : this.defId + "printUserNames",
				width : 250,
				xtype : 'textfield',
				emptyText : '请选择该流程可以打印的人员',
				listeners : {
					'focus' : function() {
						// Ext.getCmp(a + "printUserIds").setValue('');
						// Ext.getCmp(a + "printUserNames").setValue('');
						UserSelector.getView(function(id, name) {
									Ext.getCmp(a + "printUserIds").setValue(id);
									Ext.getCmp(a + "printUserNames")
											.setValue(name);
								}, false).show();
					}
				}
			});

	Ext.Ajax.request({
		url : __ctxPath + "/flow/getDefProSubjectDef.do?id=" + this.defId,
		method : "POST",
		success : function(e, f) {
			var d = Ext.util.JSON.decode(e.responseText);
			Ext.getCmp(this.defId + 'subRefFieName')
					.setValue(d.data.defaultVal);
			Ext.getCmp(this.defId + 'subRefFieName').originalValue = d.data.defaultVal;
			Ext.getCmp(this.defId + 'subRef.tmp').setValue(d.data.refField);
			Ext.getCmp(this.defId + 'subRef.pId').setValue(d.data.id);

			Ext.getCmp(this.defId + 'printUserNames').originalValue = d.printUserNames;
			Ext.getCmp(this.defId + 'printUserNames')
					.setValue(d.printUserNames);

			Ext.getCmp(this.defId + 'printUserIds').originalValue = d.printUserIds;
			Ext.getCmp(this.defId + 'printUserIds').setValue(d.printUserIds);

		},
		failure : function(d, e) {
		},
		scope : this
	});

	var c = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath
									+ "/flow/listProUserAssign.do?defId="
									+ this.defId
						}),
				reader : new Ext.data.JsonReader({
							root : "result",
							id : "id",
							fields : ["assignId", "deployId", "activityName",
									"userId", "username", "roleId", "roleName",
									'isSigned']
						})
			});
	c.load();
	var h = 0;
	var d = 0;
	var g = new Ext.form.TriggerField({
				triggerClass : "x-form-browse-trigger",
				onTriggerClick : function(j) {
					UserSelector.getView(function(m, n) {
								var l = e.getStore();
								var k = l.getAt(h);
								k.set("userId", m);
								k.set("username", n);
							}, false, true).show();
				}
			});
	var b = new Ext.form.TriggerField({
				triggerClass : "x-form-browse-trigger",
				onTriggerClick : function(j) {
					RoleSelector.getView(function(m, n) {
								var l = e.getStore();
								var k = l.getAt(h);
								k.set("roleId", m);
								k.set("roleName", n);
							}).show();
				}
			});
	var e = new Ext.grid.EditorGridPanel({
		title : "人员设置",
		id : "ProDefinitionSettingGrid" + this.defId,
		autoHeight : true,
		store : c,
		tbar : f,
		columns : [new Ext.grid.RowNumberer(), {
					header : "assignId",
					dataIndex : "assignId",
					hidden : true
				}, {
					header : "deployId",
					dataIndex : "deployId",
					hidden : true
				}, {
					header : "流程任务",
					dataIndex : "activityName",
					width : 100,
					sortable : true
				}, {
					dataIndex : "userId",
					header : "userId",
					hidden : true
				}, {
					header : "用户",
					dataIndex : "username",
					width : 150,
					sortable : true,
					editor : g
				}, {
					dataIndex : "roleId",
					hidden : true
				}, {
					header : "角色",
					dataIndex : "roleName",
					width : 150,
					editor : b
				}, {
					header : "是否会签",
					dataIndex : "isSigned",
					width : 70,
					renderer : function(l) {
						return l == 1 ? "是" : "否";
					},
					editor : new Ext.form.ComboBox({
								valueField : "id",
								displayField : "name",
								store : [["0", "否"], ["1", "是"]],
								triggerAction : "all",
								editable : false
							})
				}, {
					header : "会签设置",
					dataIndex : "isSigned",
					width : 70,
					renderer : function(p, o, l, r, n) {
						var m = l.get("assignId");
						var q = l.get("activityName");
						// alert('<button class="btn-setting" title ="会签设置"
						// onclick="ProDefinitionSetting.setTaskAssign(' + m +
						// ",'" + q + "')\"/>");
						return p == 1
								? '<button class="btn-setting" title ="会签设置" onclick="ProDefinitionSetting.setTaskAssign('
										+ m + ",'" + q + "')\"/>"
								: "";
					}
				}]
	});
	e.on("cellclick", function(j, m, k, l) {
				h = m;
			});
	return e;
};
ProDefinitionSetting.prototype.getRightCenterPanel = function(a) {
	var b = new Ext.data.JsonStore({
				url : __ctxPath + "/flow/listFormDef.do?defId=" + a,
				root : "result",
				remoteSort : true,
				fields : [{
							name : "formDefId",
							type : "int"
						}, "formName", "activityName", "deployId"]
			});
	b.load();
	var c = new Ext.grid.EditorGridPanel({
		tbar : new Ext.Toolbar({
			height : 30,
			items : [{
						text : "添加所有定义",
						iconCls : "btn-add",
						handler : function() {
							Ext.Ajax.request({
										method : "post",
										url : __ctxPath
												+ "/flow/addAllFormDef.do",
										success : function(d) {
											Ext.ux.Toast.msg("操作信息",
													"成功添加所有流程任务表单定义");
											b.reload();
										},
										failure : function(d) {
											Ext.MessageBox.show({
														title : "操作信息",
														msg : "信息保存出错，请联系管理员！",
														buttons : Ext.MessageBox.OK,
														icon : "ext-mb-error"
													});
										},
										params : {
											defId : a
										}
									});
						}
					}]
		}),
		title : "表单设置",
		id : "ProDefinitionFormGrid" + a,
		autoHeight : true,
		store : b,
		columns : [new Ext.grid.RowNumberer(), {
					header : "formDefId",
					dataIndex : "assignId",
					hidden : true
				}, {
					dataIndex : "deployId",
					hidden : true
				}, {
					header : "流程任务",
					dataIndex : "activityName",
					width : 100,
					sortable : true
				}, {
					header : "表单",
					dataIndex : "formName"
				}, {
					header : "管理",
					dataIndex : "formDefId",
					renderer : function(j, f, d) {
						var g = "";
						var h = d.data.activityName;
						var e = d.data.deployId;
						if (j == null || j == "") {
							return "";
						} else {
							g += '<button onclick="ProDefinitionSetting.formDesign('
									+ j
									+ ",'"
									+ h
									+ '\')" class="btn-form-design" title="设置表单">&nbsp;</button>';
						}
						g += '&nbsp;<button onclick="ProDefinitionSetting.formEditor('
								+ e
								+ ",'"
								+ h
								+ '\')" class="btn-form-tag" title="编辑表单源代码">&nbsp;</button>';
						return g;
					}
				}]
	});
	c.on("cellclick", function(d, h, f, g) {
				row = h;
			});
	return c;
};
ProDefinitionSetting.roleSelect = function(e, c, a) {
	var d = Ext.getCmp("ProDefinitionSettingGrid" + a);
	var b = d.getStore().getAt(e);
	d.getStore().reload();
	d.doLayout();
};
ProDefinitionSetting.formDesign = function(c, b) {
	var a = new FormDesignWindow({
				formDefId : c,
				activityName : b
			});
	a.show();
};
ProDefinitionSetting.formEditor = function(a, c) {
	var b = new FormEditorWindow({
				deployId : a,
				activityName : c
			});
	b.show();
};

ProDefinitionSetting.setTaskAssign = function(a, b) {

	new TaskSignForm({
				assignId : a,
				activityName : b
			}).show();
};