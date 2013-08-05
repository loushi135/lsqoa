var ProjectNewSelector = {
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
					dataIndex : "proNo",
					width : 30
				}, {
					header : "项目名称",
					dataIndex : "proName",
					width : 50
				}]
		});
		var baseParams = {};
		if (isSelf) {
			baseParams = {
				// 'Q_manager_S_EQ':__currentUser,
				'Q_status_N_EQ' : 0
			}
		} else {
			baseParams = {
				'Q_status_N_EQ' : 0
			}
		}
		var c = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
				url : __ctxPath + "/statistics/listProjectNew.do"
			}),
			baseParams : baseParams,
			reader : new Ext.data.JsonReader({
				root : 'result',
				totalProperty : 'totalCounts',
				remoteSort : true,
				fields : [{
						name : 'id',
						type : 'int'
					}, 'proName', 'proNo', 'area', 'proAddr', 'proCharger', 'proChargerTel', 'proFollower', 'proFollowerTel', 'designUnit', 'designCharger', 'designChargerTel', 'buildUnit', 'buildCharger', 'buildChargerTel', 'watchUnit', 'watchCharger', 'watchChargerTel', 'totalUnit', 'totalCharger', 'totalChargerTel',
					'contract', 'startDate', 'endDate', 'businessMain', 'enterDate', 'manager', 'proChargerUser', 'proAddr']
			}),
			remoteSort : true
		});
		var d = new Ext.grid.GridPanel({
			id : "ProjectSelectorGrid",
			width : 350,
			height : 300,
			region : "west",
			title : "项目列表",
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
				plugins : [new Ext.ux.PageSizePlugin()],
				displayInfo : false,
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
			width : 750,
			region : "north",
			id : "ProjectForm",
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
				}, {
					text : "项目编号"
				}, {
					xtype : "textfield",
					width : 80,
					name : "Q_proNo_S_LK"
				}, {
					text : "项目名称"
				}, {
					xtype : "textfield",
					width : 80,
					name : "Q_proName_S_LK"
				}, {
					xtype : "button",
					text : "查询",
					iconCls : "search",
					handler : function() {
						var i = Ext.getCmp("ProjectForm");
						var j = Ext.getCmp("ProjectSelectorGrid");
						if (i.getForm().isValid()) {
							var params = c.baseParams;
							Ext.apply(params, a.getForm().getValues());
							c.load(params);
						}
					}
				}]
		});

		var hp = new Ext.grid.CheckboxSelectionModel();
		var mp = new Ext.grid.GridPanel({
			id : "selectedProGrid",
			title : "已选项目",
			width : 350,
			height : 300,
			autoScroll : true,
			store : new Ext.data.ArrayStore({
				fields : ["proId", "proNo", 'proName', 'contractId', 'contractNo', 'contractAmount', 'proAreaId', 'proArea', 'proChargerUserId', 'proChargerUserName', 'proChargerUserTel', 'proAddr', 'buildUnit', 'designUnit']
			}),
			trackMouseOver : true,
			sm : hp,
			columns : [hp, new Ext.grid.RowNumberer(), {
					header : "项目编号",
					dataIndex : "proNo",
					width : 100
				}, {
					header : "项目名称",
					dataIndex : "proName",
					width : 200
				}]
		});

		var ap = new Ext.Panel({
			width : 200,
			region : "center",
			layout : "column",
			border : false,
			items : [new Ext.Panel({
					frame : true,
					width : 35,
					height : 330,
					layout : {
						type : "vbox",
						pack : "center",
						align : "stretch"
					},
					defaults : {
						margins : "0 0 5 0"
					},
					items : [{
							xtype : "button",
							text : "&gt;&gt;",
							handler : function() {
								var t = Ext.getCmp("ProjectSelectorGrid");
								var n = Ext.getCmp("selectedProGrid");
								var u = n.getStore();
								var x = t.getSelectionModel().getSelections();
								for (var p = 0; p < x.length; p++) {
									//"proId", "proNo",'proName'
									var proId = x[p].data.id;
									var proNo = x[p].data.proNo;
									var proName = x[p].data.proName;
									// 'area',
									var contractId = "";
									var contractNo = "";
									var contractAmount = "";
									var proArea = '';
									var proAreaId = '';
									var proChargerUserId = '';
									var proChargerUserName = '';
									var proChargerUserTel = '';
									var proAdress = '';
									var buildUnit = '';
									var designUnit = '';

									buildUnit += x[p].data.buildUnit;
									designUnit += x[p].data.buildUnit;
									proAdress += x[p].data.proAddr;
									proChargerUserTel += x[p].data.proChargerTel;
									if (Ext.isEmpty(x[p].data.contract)) {
										contractId += "";
										contractNo += "";
										contractAmount += "";
										proAreaId += "";
										if (!Ext.isEmpty(x[p].data.area)) {
											proArea += x[p].data.area;
										} else {
											proArea += "";
										}
									} else {
										var contract = x[p].data.contract
										contractId += contract.id;
										contractNo += contract.contractNo;
										contractAmount += contract.sumPrice;
										if (contract.deptRegional != null) {
											var dept = contract.deptRegional;
											proArea += dept.depName;
											proAreaId += dept.depId;
										}

									}
									if (!Ext.isEmpty(x[p].data.proChargerUser)) {
										proChargerUserId += x[p].data.proChargerUser.userId;
										proChargerUserName += x[p].data.proChargerUser.fullname;
									}

									var s = false;
									for (var o = 0; o < u.getCount(); o++) {
										if (u.getAt(o).data.proId == proId) {
											s = true;
											break;
										}
									}
									if(u.getCount()>0&&f){
										s=true;
									}
									if (!s) {
										var w = {
											proId : proId,
											proNo : proNo,
											proName : proName,
											contractId : contractId,
											contractNo : contractNo,
											contractAmount : contractAmount,
											proArea : proArea,
											proAreaId : proAreaId,
											proChargerUserId : proChargerUserId,
											proChargerUserName : proChargerUserName,
											proChargerUserTel : proChargerUserTel,
											proAddr : proAdress,
											buildUnit : buildUnit,
											designUnit : designUnit
										};
										var r = new u.recordType(w);
										n.stopEditing();
										u.add(r);
									}
								}
							}
						}, {
							xtype : "button",
							text : "&lt&lt;",
							handler : function() {
								var p = Ext.getCmp("selectedProGrid");
								var q = p.getSelectionModel().getSelections();
								var n = p.getStore();
								for (var o = 0; o < q.length; o++) {
									p.stopEditing();
									n.remove(q[o]);
								}
							}
						}]
				}), mp]
		});

		var tab1GroupMgr = new Ext.WindowGroup();
		//前置窗口
		tab1GroupMgr.zseed=99999;
		var e = new Ext.Window({
			title : "项目选择器",
			iconCls : "menu-project",
			width : 750,
			height : 380,
			layout : "border",
			manager: tab1GroupMgr,
			border : false,
			items : [a, d, ap],
			modal : true,
			buttonAlign : "center",
			buttons : [{
					iconCls : "btn-ok",
					text : "确定",
					handler : function() {
						var n = Ext.getCmp("selectedProGrid");
						var o = n.getStore();;
						var l = "";
						var k = "";
						var j = "";
						var contractId = "";
						var contractNo = "";
						var contractAmount = "";
						var proArea = '';
						var proAreaId = '';
						var proChargerUserId = '';
						var proChargerUserName = '';
						var proChargerUserTel = '';
						var proAdress = '';
						var buildUnit = '';
						var designUnit = '';
						for (var m = 0; m < o.getCount(); m++) {
							if (m > 0) {
								l += ",";
								k += ",";
								j += ",";
								contractId += ",";
								contractNo += ",";
								contractAmount += ",";
								proArea += ",";
								proAreaId += ",";
								proChargerUserId += ",";
								proChargerUserName += ",";
								proAdress += ",";
								proChargerUserTel += ",";
								buildUnit += ',';
								designUnit += ',';
							}
							contractId += o.getAt(m).data.contractId;
							contractNo += o.getAt(m).data.contractNo;
							contractAmount +=o.getAt(m).data.contractAmount;
							proChargerUserId += o.getAt(m).data.proChargerUserId;
							proChargerUserName +=o.getAt(m).data.proChargerUserName;
							buildUnit += o.getAt(m).data.buildUnit;
							designUnit += o.getAt(m).data.buildUnit;
							l += o.getAt(m).data.proId;
							k += o.getAt(m).data.proName;
							j += o.getAt(m).data.proNo;
							proAdress += o.getAt(m).data.proAddr;
							proChargerUserTel += o.getAt(m).data.proChargerTel;
							proArea += o.getAt(m).data.proArea;
							proAreaId += o.getAt(m).data.proAreaId;

						}
						if(Ext.isEmpty(proAdress)){
							proAdress='该项目暂无地址信息';
						}
						if (h != null) {
							h.call(this, l, k, j, contractId, contractNo, contractAmount, proAreaId, proArea, proChargerUserId, proChargerUserName, proChargerUserTel, proAdress, buildUnit, designUnit);
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