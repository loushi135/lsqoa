var ProjectSealSelector = {
	getView : function(h, f, isSelf) {
		var g = null;
		if (f) {
			var g = new Ext.grid.CheckboxSelectionModel({
						singleSelect : true
					});
		} else {
			g = new Ext.grid.CheckboxSelectionModel();
		}
		var b = new Ext.grid.ColumnModel({
					columns : [g, new Ext.grid.RowNumberer(), {
								dataIndex : "id",
								hidden : true
							}, {
								header : "项目编号",
								dataIndex : "project",
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value.proNo;
									}
								}
							}, {
								header : "项目名称",
								dataIndex : "proName"
							}, {
								header : "章刻印内容",
								dataIndex : "content"
							}, {
								header : "项目印章申请人",
								dataIndex : "applyUser",
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value.fullname;
									}
								}
							}, {
								header : "印章保管人",
								dataIndex : "keeper",
								renderer : function(value) {
									if (!Ext.isEmpty(value)) {
										return value.fullname;
									}
								}
							}]
				});
		var baseParams = {};
		if (isSelf) {
			baseParams = {
			// 'Q_manager_S_EQ':__currentUser,
			// 'Q_status_N_EQ':0
			}
		} else {
			baseParams = {
			// 'Q_status_N_EQ':0
			}
		}
		var c = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : __ctxPath
										+ "/statistics/listProjectSeal.do"
							}),
					baseParams : baseParams,
					reader : new Ext.data.JsonReader({
								root : 'result',
								totalProperty : 'totalCounts',
								remoteSort : true,
								fields : [{
											name : 'id',
											type : 'int'
										}, 'proName', 'project', 'applyUser',
										'keeper', 'applyDate', 'returnDate',
										'content']
							}),
					remoteSort : true
				});
		var d = new Ext.grid.GridPanel({
					id : "ProjectSealSelectorGrid",
					width : 950,
					height : 300,
					region : "center",
					title : "项目印章列表",
					store : c,
					shim : true,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					cm : b,
					sm : g,
					viewConfig : {
						forceFit : true,
						enableRowBody : false,
						showPreview : false
					},
					bbar : new Ext.PagingToolbar({
								pageSize : 25,
								store : c,
								displayInfo : true,
								displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
								emptyMsg : "当前没有记录"
							})
				});
		c.load({
					params : {
						start : 0,
						limit : 25
					}
				});
		var a = new Ext.FormPanel({
					width : 950,
					region : "north",
					id : "ProjectSealForm",
					height : 40,
					frame : false,
					border : false,
					layout : "hbox",
					layoutConfig : {
						padding : "5",
						align : "middle"
					},
					defaults : {
						xtype : "label",
						margins : {
							top : 0,
							right : 4,
							bottom : 4,
							left : 4
						}
					},
					items : [{
								text : "查询条件:"
							},
//							{
//								text : "项目编号"
//							}, {
//								xtype : "textfield",
//								width : 80,
//								name : "Q_project.proNo_S_LK"
//							},
							{
								text : "项目名称"
							}, {
								xtype : "textfield",
								width : 80,
								name : "Q_proName_S_LK"
							}, {
								text : "章刻印内容"
							}, {
								xtype : "textfield",
								width : 80,
								name : "Q_content_S_LK"
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '申领时间:'
							}, {
								name : 'Q_applyDate_D_EQ',
								format : 'Y-m-d',
								xtype : 'datefield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '归还时间:'
							}, {
								name : 'Q_returnDate_D_EQ',
								format : 'Y-m-d',
								xtype : 'datefield'
							}, {
								xtype : "button",
								text : "查询",
								iconCls : "search",
								handler : function() {
									var i = Ext.getCmp("ProjectSealForm");
									var j = Ext
											.getCmp("ProjectSealSelectorGrid");
									if (i.getForm().isValid()) {
										var params = c.baseParams;
										Ext.apply(params, a.getForm()
														.getValues());
										c.load(params);
									}
								}
							}]
				});
		var e = new Ext.Window({
			title : "项目印章选择器",
			iconCls : "menu-project",
			width : 1000,
			height : 380,
			layout : "border",
			border : false,
			items : [a, d],
			modal : true,
			buttonAlign : "center",
			buttons : [{
				iconCls : "btn-ok",
				text : "确定",
				handler : function() {
					var n = Ext.getCmp("ProjectSealSelectorGrid");
					var o = n.getSelectionModel().getSelections();

					var sealId = "";
					var proName = "";
					var content = "";
					var applyUserName = "";
					var applyUserId = "";
					var keeperUserId = "";
					var keeperUserName = "";
					var proAreaName = "";
					var proAreaId = "";
					var proChargerUserName = "";
					var proChargerUserId = "";

					for (var m = 0; m < o.length; m++) {
						if (m > 0) {
							sealId += ",";
							proName += ",";
							content += ",";
							applyUserName += ",";
							applyUserId += ",";
							keeperUserId += ",";
							keeperUserName += ",";
							proAreaName += ",";
							proAreaId += ",";
							proChargerUserName += ",";
							proChargerUserId += ",";
						}
						sealId += o[m].data.id;
						proName += o[m].data.proName;
						content += o[m].data.content;
						applyUserName += o[m].data.applyUser.fullname;
						applyUserId += o[m].data.applyUser.userId;
						keeperUserName += o[m].data.keeper.fullname;
						keeperUserId += o[m].data.keeper.userId;
						proChargerUserName += o[m].data.project.proChargerUser.fullname;
						proChargerUserId += o[m].data.project.proChargerUser.userId;
						proAreaName += o[m].data.project.area;

						var tmp;

						if (!Ext.isEmpty(o[m].data.project.contract)
								&& (!Ext
										.isEmpty(o[m].data.project.contract.deptRegional))) {
							proAreaId += o[m].data.project.contract.deptRegional.depId;
							tmp = o[m].data.project.contract.deptRegional.depId;
						} else {
							if (!Ext.isEmpty(o[m].data.project.area)
									&& Ext.isEmpty(tmp)) {
								var url = __ctxPath
										+ '/system/getDeptByNameDepartment.do'
								var params = "depName="
										+ o[m].data.project.area;
								var d = ajaxSyncCall(url, params).data;
								if (!Ext.isEmpty(d)) {
									proAreaId += d.depId;
								}
							}
						}

					}
					if (h != null) {
						h.call(this, sealId, proName, content, applyUserName,
								applyUserId, keeperUserName, keeperUserId,
								proAreaName, proAreaId, proChargerUserName,
								proChargerUserId);
					}
					e.close();
				}
			}, {
				text : "取消",
				iconCls : "btn-cancel",
				handler : function() {
					e.close();
				}
			}]
		});
		return e;
	}
};