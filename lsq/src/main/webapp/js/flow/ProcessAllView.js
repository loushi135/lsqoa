Ext.ns('ProcessAllView');
/**
 * 申请列表
 */
var ProcessAllView = function() {
	return new Ext.Panel({
		id : 'ProcessAllView',
		title : '所有流程列表',
		iconCls:'menu-flowMine',
		autoScroll : true,
		items : [new Ext.FormPanel({
			height : 35,
			frame : true,
			id : 'ProcessAllSearchForm',
			layout : 'column',
			defaults : {
				xtype : 'label'
			},
			items : [{
						text : '请输入查询条件:'
					}, {
						text : '标题'
					}, {
						xtype : 'textfield',
						name : 'Q_processRun.subject_S_LK'
					},{
						style : 'margin-top:5px;',
						text : '起始日期'
					}, {
						name : 'Q_processRun.createtime_DL_GE',
						xtype : 'datefield',
						format : 'Y-m-d'
					}, {
						style : 'margin-top:5px;',
						text : '结束日期'
					}, {
						name : 'Q_processRun.createtime_DG_LE',
						xtype : 'datefield',
						format : 'Y-m-d'
					},  {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						handler : function() {
							var searchPanel = Ext
									.getCmp('ProcessAllSearchForm');
							var grid = Ext.getCmp('ProcessAllGrid');
							if (searchPanel.getForm().isValid()) {
								searchPanel.getForm().submit({
									waitMsg : '正在提交查询',
									url : __ctxPath + "/flow/listProcessForm.do?type=ALL",
									success : function(formPanel, action) {
										var result = Ext.util.JSON.decode(action.response.responseText);
										var params = grid.getStore().baseParams; 
			            				Ext.apply(params,searchPanel.getForm().getValues());
										grid.getStore().loadData(result);
									}
								});
							}

						}
					},{
						xtype : 'button',
						iconCls : 'btn-clear',
						text : '清空',
						handler : function() {
							var searchPanel = Ext.getCmp('ProcessAllSearchForm');
							var grid = Ext.getCmp('ProcessAllGrid');
							searchPanel.getForm().reset();
							var params = grid.getStore().baseParams; 
            				Ext.apply(params,searchPanel.getForm().getValues());
						},
						width : 60
					}]
		}), this.setup()]
	});
};
/**
 * 建立视图
 */
ProcessAllView.prototype.setup = function() {
	return this.grid();
};
/**
 * 建立DataGrid
 */
ProcessAllView.prototype.grid = function() {
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel({
		columns : [sm, new Ext.grid.RowNumberer(), {
					header : 'runId',
					dataIndex : 'runId',
					hidden : true
				}, {
					header : '标题',
					dataIndex : 'processRun.subject'
				},{
					header : '时间',
					dataIndex : 'createtime',
					width:60
				},{
					header : '当前审批人',
					dataIndex : 'assignee',
					sortable:false,
					width:60
				},{
					header:'流程状态',
					dataIndex:'processRun.runStatus',
					renderer:function(e){
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
				},{
					header : '管理',
					dataIndex : 'runId',
					sortable:false,
					width : 50,
					renderer : function(value, metadata, record, rowIndex,colIndex) {
						var runId = record.data.runId;
						var defId=record.data.defId;
						var piId=record.data.piId;
						var appsouce=record.data.appsouce;
						var taskId=record.data.taskId;
						var taskName=record.data.taskName;
						var url=record.data.url;
						var subject=record.get("processRun.subject");
						var runStatus=record.get("processRun.runStatus");
						var str="";
						//runId,defId,piId,name
						if(piId!=null && piId!=undefined && piId!=''){		
							str += '&nbsp;<button type="button" title="审批明细" value=" " class="btn-flowView" onclick="ProcessAllView.detail('+
							runId+','+defId+',\''+ piId + '\',\''+ subject + '\')"></button>';
						}
						if((piId==null || piId==undefined || piId=='') && (appsouce!=null && appsouce!=undefined && appsouce!='')){
							str += '&nbsp;<button type="button" title="审批明细" value=" " class="btn-flowView" onclick="ProcessAllView.detailYhoa('
									+ taskId
									+ ",'"
									+ taskName
									+ "','"
									+ url
									+ "')\"></button>";
						}
						
						//if(runStatus==2){//删除已完成的流程并同时取消所有的流程造成的数据
						//	str+='&nbsp;<button title="编辑" class="btn-del" onclick="ProcessAllView.deleteFinish('+runId+',\''+subject+'\')"></button>';
						//}
						
////						//若流程尚未结束，可进行编辑,及删除
////						if(runStatus==0){
//							//str+='&nbsp;<button title="编辑" class="btn-edit" onclick="ProcessAllView.edit('+runId+',\''+subject+'\')"></button>';
//							str+= '&nbsp;<button title="删除" value=" " class="btn-del" onclick="ProcessAllView.remove('+ runId + ')"></button>';
////						}
						return str;
					}
				}],
		defaults : {
			sortable : true,
			menuDisabled : false,
			width : 100
		}
	});

	var store = this.store();
	store.load({
				params : {
					start : 0,
					limit : 25
				}
			});
	var grid = new Ext.grid.GridPanel({
				id : 'ProcessAllGrid',
				store : store,
				trackMouseOver : true,
				disableSelection : false,
				loadMask : true,
				autoHeight : true,
				tbar:new Ext.Toolbar(),
				cm : cm,
				sm : sm,
				viewConfig : {
					forceFit : true,
					enableRowBody : false,
					showPreview : false
				},
				bbar : new Ext.PagingToolbar({
							pageSize : 25,
							store : store,
							plugins : [new Ext.ux.PageSizePlugin()],
							displayInfo : true,
							displayMsg : '当前显示从{0}至{1}， 共{2}条记录',
							emptyMsg : "当前没有记录"
						})
			});
	AppUtil.addPrintExport(grid);
	return grid;

};

/**
 * 初始化数据
 */
ProcessAllView.prototype.store = function() {
	var store = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath + "/flow/listProcessForm.do?type=ALL"
						}),
				reader : new Ext.data.JsonReader({
							root : 'result',
							totalProperty : 'totalCounts',
							id : 'formId',
							fields : ['formId',
									  'runId',
							          'defId',
							          'piId',
							          {name:"processRun.subject", mapping:"subject"},
							          'createtime',
							          {name:"processRun.runStatus", mapping:"runStatus"},
							          'assignee','appsouce','taskId','taskName','url'
							          ]
						}),
				remoteSort : true
			});
	store.setDefaultSort('formId', 'desc');
	return store;
};



/**
 * 
 */
ProcessAllView.edit = function(runId,name) {
	var contentPanel=App.getContentPanel();
	var startForm=contentPanel.getItem('ProcessRunStart'+runId);
	if(startForm==null){
		startForm=new ProcessRunStart(null,name,runId);
		contentPanel.add(startForm);
	}
	contentPanel.activate(startForm);
}

/**
 * 删除已完成的记录
 */
ProcessAllView.deleteFinish = function(id,subject) {
	var grid = Ext.getCmp("ProcessAllGrid");
	Ext.Msg.confirm('信息确认', '删除该记录会撤销该记录引起的所有的相关数据，确认？', function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request({
								url : __ctxPath + '/flow/deleteFinishProcessRun.do',
								params : {
									id : id,
									subject : subject
								},
								method : 'post',
								success : function() {
									Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
									grid.getStore().reload({params : {start : 0,limit : 25}});
								}
							});
				}
			});
};

/**
 * 显示明细
 * @param {} runId
 * @param {} name
 */
ProcessAllView.detail=function(runId,defId,piId,name){
	var contentPanel=App.getContentPanel();
	var detailView=contentPanel.getItem('ProcessRunDetail'+runId);
	if(detailView==null){
		detailView=new ProcessRunDetail(runId,defId,piId,name);
		contentPanel.add(detailView);
	}
	contentPanel.activate(detailView);
};

ProcessAllView.detailYhoa = function(taskId,taskName,url) {
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
