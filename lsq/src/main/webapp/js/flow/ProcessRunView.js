Ext.ns("ProcessRunView");
var ProcessRunView = function() {
	return new Ext.Panel({
		id : "ProcessRunView",
		title : "我参与的流程列表",
		iconCls : "menu-flowMine",
		autoScroll : true,
		items : [new Ext.FormPanel({
			id : "ProcessRunSearchForm",
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
						text : "请输入查询条件:"
					}, {
						text : "标题"
					}, {
						xtype : "textfield",
						name : "Q_processRun.subject_S_LK"
					}, {
						text : "时间 从"
					}, {
						xtype : "datefield",
						name : "Q_createtime_D_GT",
						format : "Y-m-d"
					}, {
						text : " 至 "
					}, {
						xtype : "datefield",
						name : "Q_createtime_D_LT",
						format : "Y-m-d"
					}, {
						text : "状态"
					}, {
						xtype : "combo",
						width : 80,
						hiddenName : "Q_processRun.runStatus_SN_EQ",
						editable : false,
						mode : "local",
						triggerAction : "all",
						store : [["1", "正在运行"], ["2", "结束"]]
					}, {
						xtype : "button",
						text : "查询",
						iconCls : "search",
						handler : function() {
							var a = Ext.getCmp("ProcessRunSearchForm");
							var b = Ext.getCmp("ProcessRunGrid");
							if (a.getForm().isValid()) {
								a.getForm().submit({
									waitMsg : "正在提交查询",
									url : __ctxPath + "/flow/listProcessForm.do",
									success : function(d, e) {
										var c = Ext.util.JSON
												.decode(e.response.responseText);
										b.getStore().loadData(c);
									}
								});
							}
						}
					}]
		}), this.setup()]
	});
};
ProcessRunView.prototype.setup = function() {
	return this.grid();
};
ProcessRunView.prototype.grid = function() {
	var d = new Ext.grid.CheckboxSelectionModel();
	var a = new Ext.grid.ColumnModel({
		columns : [d, new Ext.grid.RowNumberer(), {
					header : "runId",
					dataIndex : "runId",
					hidden : true
				}, {
					header : "标题",
					dataIndex : "processRun.subject"
				}, {
					header : "时间",
					dataIndex : "createtime",
					width : 60
				},{
					header:'执行人',
			      	dataIndex:'assignee',
			      	sortable:false,
			      	width:70
				}, {
					header : "流程状态",
					dataIndex : "processRun.runStatus",
					renderer : function(e) {
						if (e == 0) {
							return '<font color="red">草稿</font>';
						} else {
							if (e == 1) {
								return '<font color="green">正在运行</font>';
							} else {
								if (e == 2) {
									return '<font color="gray">结束</font>';
								}else {
									if (e == 3){
										return '<font color="red">审批不通过</font>';
									}
								}
							}
						}
					}
				}, {
					header : "管理",
					dataIndex : "runId",
					sortable:false,
					width : 50,
					renderer : function(m, k, f, j, o) {
						var l = f.data.runId;
						var e = f.data.defId;
						var n = f.data.piId;
						var appsouce=f.data.appsouce;
						var taskId=f.data.taskId;
						var taskName=f.data.taskName;
						var url=f.data.url;
						var i=f.get("processRun.subject");
						var g=f.get("processRun.runStatus");
						
						var h = "";
						if (n != null && n != undefined && n != "") {
							h += '&nbsp;<button type="button" title="审批明细" value=" " class="btn-flowView" onclick="ProcessRunView.detail('
									+ l
									+ ","
									+ e
									+ ",'"
									+ n
									+ "','"
									+ i
									+ "')\"></button>";
						}
						if((n==null || n==undefined || n=='') && (appsouce!=null && appsouce!=undefined && appsouce!='')){
							h += '&nbsp;<button type="button" title="审批明细" value=" " class="btn-flowView" onclick="ProcessRunView.detailYhoa('
									+ taskId
									+ ",'"
									+ taskName
									+ "','"
									+ url
									+ "')\"></button>";
						}
//						if (g == 0) {
//							h += '&nbsp;<button title="编辑" class="btn-edit" onclick="ProcessRunView.edit('
//									+ l + ",'" + i + "')\"></button>";
//							h += '&nbsp;<button title="删除" value=" " class="btn-del" onclick="ProcessRunView.remove('
//									+ l + ')"></button>';
//						}
//						//若流程尚未结束，可进行编辑,及删除
//						if(g!=2){
//						//str+='&nbsp;<button title="编辑" class="btn-edit" onclick="ProcessRunView.edit('+runId+',\''+subject+'\')"></button>';
//							h+= '&nbsp;<button title="取消" value=" " class="btn-del" onclick="ProcessRunView.remove('+ l + ')"></button>';
//						}
						return h;
					}
				}],
		defaults : {
			sortable : true,
			menuDisabled : false,
			width : 100
		}
	});
	var b = this.store();
	b.load({
				params : {
					start : 0,
					limit : 25
				}
			});
	var c = new Ext.grid.GridPanel({
				id : "ProcessRunGrid",
				store : b,
				trackMouseOver : true,
				disableSelection : false,
				loadMask : true,
				autoHeight : true,
				tbar : new Ext.Toolbar(),
				cm : a,
				sm : d,
				viewConfig : {
					forceFit : true,
					enableRowBody : false,
					showPreview : false
				},
				bbar : new Ext.PagingToolbar({
							pageSize : 25,
							store : b,
							displayInfo : true,
							displayMsg : "当前显示从{0}至{1}， 共{2}条记录",
							emptyMsg : "当前没有记录"
						})
			});
	c.addListener("rowdblclick", function(g, f, h) {
				g.getSelectionModel().each(function(e) {
					var piId=e.data.piId;
					var appsouce=e.data.appsouce;
					if((piId==null || piId==undefined || piId=='') && (appsouce!=null && appsouce!=undefined && appsouce!='')){
						ProcessRunView.detailYhoa(e.data.taskId, e.data.taskName,e.data.url);
					}
					if (piId != null && piId != undefined && piId != "") {
						ProcessRunView.detail(e.data.runId, e.data.defId,e.data.piId, e.get('processRun.subject'));
					}
				});
			});
	return c;
};
ProcessRunView.prototype.store = function() {
	var a = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath + "/flow/listProcessForm.do"
						}),
				reader : new Ext.data.JsonReader({
							root : "result",
							totalProperty : "totalCounts",
							id : "id",
							fields : ['formId','runId',
							          'defId',
							          'piId',
							          {name:"processRun.subject", mapping:"subject"},
							          'createtime',
							          {name:"processRun.runStatus", mapping:"runStatus"},
							          'assignee','appsouce','taskId','taskName','url']
						}),
				remoteSort : true
			});
	a.setDefaultSort("formId", "desc");
	return a;
};
ProcessRunView.remove = function(id) {
	var grid = Ext.getCmp("ProcessRunGrid");
	
	var window= new Ext.Window({
		id : 'ReasonWin',
		title : '请填写取消该流程的原因',
		width : 500,
		autoHeight : true,
		shadow : false,
		modal : true,
		layout : 'anchor',
		plain : true,
		bodyStyle : 'padding:5px;',
		buttonAlign : 'center',
		items : [{
			xtype : 'form',
			name : 'reasonForm',
			id : 'reasonForm',
			layout : 'fit',
			items:[{
					xtype : 'textarea',
					name : 'reason',
					id : 'reason',
					blankText : '不能为空!',
					allowBlank:false,
					height:100
				}]
		}]
	});
	
	window.addButton(new Ext.Button({
			text : '提交',
			iconCls : 'btn-refresh',
			handler : function() {
					if (Ext.getCmp('reasonForm').getForm().isValid()) {
						Ext.Msg.confirm('信息确认', '您确认要删除该记录吗？', function(btn) {
						if (btn == 'yes') {
								Ext.Ajax.request({
											url : __ctxPath + '/flow/multiDelProcessRun.do',
											params : {
												id : id,
												reason:Ext.getCmp('reason').getValue()
											},
											method : 'post',
											success : function() {
												Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
												window.close();
												grid.getStore().reload({params : {start : 0,limit : 25}});
											}
										});
							}
						});
					}else{
						Ext.ux.Toast.msg("信息提示", "请填写撤销原因！");
					}
					
			}
	}));
	window.show();
	

};
ProcessRunView.edit = function(c, b) {
	var d = App.getContentPanel();
	var a = d.getItem("ProcessRunStart" + c);
	if (a == null) {
		a = new ProcessRunStart(null, b, c);
		d.add(a);
	}
	d.activate(a);
};
ProcessRunView.detail = function(d, a, c, b) {
	var f = App.getContentPanel();
	var e = f.getItem("ProcessRunDetail" + d);
	if (e == null) {
		e = new ProcessRunDetail(d, a, c, b);
		f.add(e);
	}
	f.activate(e);
};
ProcessRunView.detailYhoa = function(taskId,taskName,url) {
    var c = App.getContentPanel();
    var a = c.getItem("ProcessNextFormForYhoa" + taskId);
    if (a == null) {
        a = new ProcessNextFormForYhoa({
            taskId: taskId,
            url: url,
            taskName:taskName
        });
        c.add(a);
    }
    c.activate(a);
};