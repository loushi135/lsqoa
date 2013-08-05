/**
 * @author juql
 * @class LeaveapplyView
 * @extends Ext.Panel
 * @description 请假申请管理
 */
LeaveapplyView=Ext.extend(Ext.Panel,{
	searchPanel:null,
	gridPanel:null,
	store:null,
	topbar:null,
	constructor:function(_cfg){
			Ext.applyIf(this,_cfg);
			this.initUIComponents();
			LeaveapplyView.superclass.constructor.call(this,{
				id:'LeaveapplyView',
				title:'请假申请管理',
				region:'center',
				layout:'border',
				items:[this.searchPanel,this.gridPanel]
			});
	},//end of constructor

	//初始化组件
	initUIComponents:function(){
		//初始化搜索条件Panel
		this.searchPanel=new Ext.FormPanel({
		    layout : 'column',
		    region:'north',
			bodyStyle: 'padding:6px 10px 6px 10px',
			border:false,
			frame:false,
			height:35,
			defaults:{
				border:false,
				anchor:'98%,98%'
			},
		    items : [{
					xtype : 'label',
					style : 'margin-top:5px;',
					text : '查询:'
				}, {
					xtype : 'label',
					style : 'margin-top:5px;',
					text : '员工姓名:'
				}, {
					name : 'Q_userName_S_LK',
					xtype : 'textfield'
				},{
					xtype : 'label',
					style : 'margin-top:5px;',
					text : '部门名称:'
				}, {
					name : 'Q_deptName_S_LK',
					xtype : 'textfield'
				},{
					xtype : 'label',
					style : 'margin-top:5px;',
					text : '假期类别:'
				}, {
					name : 'Q_type_S_LK',
					xtype : 'textfield'
				}, {
					xtype : 'label',
					style : 'margin-top:5px;',
					text : '开始时间起点:'
				}, {
					name : 'Q_startTime_DL_GE',
					xtype : 'datefield',
					format : 'Y-m-d'
				}, {
					xtype : 'label',
					style : 'margin-top:5px;',
					text : '开始时间结束:'
				}, {
					name : 'Q_startTime_DG_LE',
					xtype : 'datefield',
					format : 'Y-m-d'
				},{
					xtype : 'button',
					text : '查询',
					iconCls : 'search',
					handler : this.search.createCallback(this)
    			}]
		});//end of the searchPanel
		
		//加载数据至store
		this.store = new Ext.data.JsonStore({
							url : __ctxPath+"/personal/listLeaveapply.do",
							root : 'result',
							totalProperty : 'totalCounts',
							remoteSort : true,
							fields : [{name : 'id',type:'int'}
									,'userId'
									,'userName'
									,'deptName'
									,'deptId'
									,'startTime'
									,'startAmOrPm'
									,'endTime'
									,'endAmOrPm'
									,'totalDays'
									,'type'
									,'other'
									,'applyUser'
									,'applyTime','processRunId']
		});
		this.store.setDefaultSort('id', 'desc');
		//加载数据
		this.store.load({params : {
					start : 0,
					limit : 25
		}});
		
		this.rowActions = new Ext.ux.grid.RowActions({
			header:'管理',
			width:80,
			actions:[{
						 iconCls:'btn-del'
						 ,qtip:'删除'
						 ,style:'margin:0 3px 0 3px',
						 hide:!(isGranted("_LeaveapplyDel"))
					  },{
						 iconCls:'btn-readdocument'
						,qtip:'编辑'
						,style:'margin:0 3px 0 3px',
					     hide:!(isGranted("_LeaveapplyEdit"))
					},{
						 iconCls:'btn-flowView',
						 qtip:'查看审批表单',
						 style:'margin:0 3px 0 3px'
					}
			]
		});
		
		//初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			columns : [sm, new Ext.grid.RowNumberer(), {
						header : 'id',
						dataIndex : 'id',
						hidden : true
					},{
						header : '员工姓名',	
						dataIndex : 'userName'
					},{
						header : '部门',	
						dataIndex : 'deptName'
					},{
						header : '开始时间',	
						dataIndex : 'startTime',
						renderer:function(value){
							return value.substring(0,10);
						}
					},{
						header : '上/下午',	
						dataIndex : 'startAmOrPm'
					},{
						header : '结束时间',	
						dataIndex : 'endTime',
						renderer:function(value){
							return value.substring(0,10);
						}
					},{
						header : '上/下午',	
						dataIndex : 'endAmOrPm'
					},{
						header : '天数',	
						dataIndex : 'totalDays'
					},{
						header : '请假类别',	
						dataIndex : 'type'
					}, this.rowActions],
					defaults : {
					sortable : true,
					menuDisabled : false,
					width : 100
				}
			});
		//初始化工具栏
		this.topbar=new Ext.Toolbar({
				height : 30,
				bodyStyle : 'text-align:left',
				items : [{
								iconCls : 'btn-add',
								text : '添加请假信息',
								xtype : 'button',
								hidden:!(isGranted("_LeaveapplyAdd")) ,
								handler : this.createRecord
						},{
								iconCls : 'btn-del',
								text : '删除请假信息',
								xtype : 'button',
								handler : this.delRecords,
								hidden:!(isGranted("_LeaveapplyDel")) ,
								scope : this
						}]
			});
			
		this.gridPanel=new Ext.grid.GridPanel({
				id : 'LeaveapplyGrid',
				region:'center',
				stripeRows:true,
				tbar : this.topbar,
				store : this.store,
				trackMouseOver : true,
				disableSelection : false,
				loadMask : true,
				autoHeight : true,
				cm : cm,
				sm : sm,
				plugins:this.rowActions,
				viewConfig : {
					forceFit : true,
					autoFill : true, //自动填充
					forceFit : true
					//showPreview : false
				},
				bbar : new Ext.PagingToolbar({
							pageSize : 25,
							store : this.store,
							plugins : [new Ext.ux.PageSizePlugin()],
							displayInfo : true,
							displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
							emptyMsg : "当前没有记录"
				})
			});
		AppUtil.addPrintExport(this.gridPanel);

			this.gridPanel.addListener('rowdblclick', function(grid, rowindex, e) {
				grid.getSelectionModel().each(function(rec) {
					if(isGranted("_LeaveapplyEdit")){new LeaveapplyForm({id:rec.data.id}).show();}
				});
			});		
			this.rowActions.on('action', this.onRowAction, this);
	},//end of the initComponents()
	
	/**
	 * 
	 * @param {} self 当前窗体对象
	 */
	search:function(self){
		if(self.searchPanel.getForm().isValid()){//如果合法
				self.searchPanel.getForm().submit({
					waitMsg:'正在提交查询',
					url:__ctxPath+'/personal/listLeaveapply.do',
					success:function(formPanel,action){
			            var result=Ext.util.JSON.decode(action.response.responseText);
			            var params = self.gridPanel.getStore().baseParams; 
			            Ext.apply(params,self.searchPanel.getForm().getValues());
			            self.gridPanel.getStore().loadData(result);
					}
			});
		}
	},
	delByIds : function(ids) {
				Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
							if (btn == 'yes') {
								Ext.Ajax.request({
											url : __ctxPath
													+ '/personal/multiDelLeaveapply.do',
											params : {
												ids : ids
											},
											method : 'POST',
											success : function(response,
													options) {
												Ext.ux.Toast.msg('操作信息',
														'成功删除该请假信息！');
												Ext.getCmp('LeaveapplyGrid')
														.getStore().reload();
											},
											failure : function(response,
													options) {
												Ext.ux.Toast.msg('操作信息',
														'操作出错，请联系管理员！');
											}
										});
							}
						});// end of comfirm
			},
			/**
			 * 删除多条记录
			 */
			delRecords : function() {
				var gridPanel = Ext.getCmp('LeaveapplyGrid');
				var selectRecords = gridPanel.getSelectionModel()
						.getSelections();
				if (selectRecords.length == 0) {
					Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
					return;
				}
				var ids = Array();
				for (var i = 0; i < selectRecords.length; i++) {
					ids.push(selectRecords[i].data.id);
				}
				this.delByIds(ids);
			},

	createRecord : function() {
		new LeaveapplyForm().show();
	},
	
	/**
	 * 编辑记录
	 * @param {} record
	 */
	editRecord:function(record){
		new LeaveapplyForm({id:record.data.id}).show();
	},
	
	/**
	 * 管理列中的事件处理
	 * @param {} grid
	 * @param {} record
	 * @param {} action
	 * @param {} row
	 * @param {} col
	 */
	onRowAction:function(gridPanel, record, action, row, col) {
		switch(action) {
			case 'btn-del' :
						this.delByIds(record.data.id);
						break;
			case 'btn-readdocument':
				this.editRecord(record);
				break;
			case 'btn-flowView':
				AppUtil.selectProcessForm(record.data.processRunId);
				break;
			default:
				break;
		}
	}
});
