/**
 * @author:
 * @class TrainPlanView
 * @extends Ext.Panel
 * @description [TrainPlan]管理
 * @company
 * @createtime:2010-07-19
 */
TrainPlanView = Ext.extend(Ext.Panel, {
	// 条件搜索Panel
	searchPanel : null,
	// 数据展示Panel
	gridPanel : null,
	// GridPanel的数据Store
	store : null,
	// 头部工具栏
	topbar : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		TrainPlanView.superclass.constructor.call(this, {
			id : 'TrainPlanView',
			title : '[培训计划]管理',
			region : 'center',
			layout : 'border',
			items : [this.searchPanel, this.gridPanel]
		});
	},// end of constructor

	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.searchPanel = new Ext.FormPanel({
			layout : 'column',
			region : 'north',
			height : 60,
			bodyStyle : 'padding:6px 10px 6px 10px',
			border : false,
			frame : true,
			defaults : {
				border : false,
				anchor : '98%,98%'
			},
			items : [{
						style : 'margin-top:5px;',
						xtype : 'label',
						text : '请输入查询条件:'
					}, {
						style : 'margin-top:5px;',
						xtype : 'label',
						text : '课程名称:'
					}, {
						name : 'Q_trainCourse.courseName_S_LK',
						xtype : 'textfield'
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						handler : this.search.createCallback(this)
					}]
		});// end of the searchPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
			url : __ctxPath + "/hrm/listTrainPlan.do",
			root : 'result',
			totalProperty : 'totalCounts',
			remoteSort : true,
			fields : [{
						name : 'id',
						type : 'int'
					}, 'sn', 'trainCourse', 'trainTime', 'trainStatus','trainPlanPlace', 'trainType', 'sumNum']
		});
		this.store.setDefaultSort('id', 'desc');
		// 加载数据
		this.store.load({
			params : {
				start : 0,
				limit : 25
			}
		});

		this.rowActions = new Ext.ux.grid.RowActions({
			header : '管理',
			width : 110,
			actions : [{
						iconCls : 'btn-del',
						qtip : '删除',
						hide : !isGranted('_TrainPlanDel'),
						style : 'margin:0 3px 0 3px'
					}, {
						iconCls : 'btn-edit',
						qtip : '编辑',
						hide : !isGranted('_TrainPlanEdit'),
						style : 'margin:0 3px 0 3px'
					}, {
						iconCls : 'assets-type',
						qtip : '生成培训报告',
						hide : !isGranted('_TrainReportAdd'),
						style : 'margin:0 3px 0 3px'
					}, {
						iconCls : 'btn-cancel',
						qtip : '取消',
						hide : !isGranted('_TrainPlanEdit'),
						style : 'margin:0 3px 0 3px'
					},{
						iconCls : 'menu-administrator',
						qtip : '发送短信',
						hide : !isGranted('_TrainPlanSendMsg'),
						style : 'margin:0 3px 0 3px'
					}]
		});

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			columns : [sm, new Ext.grid.RowNumberer(), {
						header : 'id',
						dataIndex : 'id',
						hidden : true
					}, {
						header : '课程名称',
						dataIndex : 'trainCourse',
						renderer : function(value) {
							return value.courseName
						}
					}, {
						header : '计划编号',
						dataIndex : 'sn'
					},{
						header : '培训地点',
						dataIndex : 'trainPlanPlace'
					}, {
						header : '培训时间',
						dataIndex : 'trainTime'
					}, {
						header : '培训状态',
						dataIndex : 'trainStatus'
					}, {
						header : '培训类型',
						dataIndex : 'trainType'
					}, {
						header : '总人数',
						dataIndex : 'sumNum'
					},this.rowActions],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		// 初始化工具栏
		this.topbar = new Ext.Toolbar({
			height : 30,
			bodyStyle : 'text-align:left',
			items : [{
						iconCls : 'btn-add',
						text : '添加[培训计划]',
						hidden : !isGranted('_TrainPlanAdd'),
						xtype : 'button',
						handler : this.createRecord
					}, {
						iconCls : 'btn-del',
						hidden : !isGranted('_TrainPlanDel'),
						text : '删除[培训计划]',
						xtype : 'button',
						handler : this.delRecords,
						scope: this
					}
					]
		});

		this.gridPanel = new Ext.grid.GridPanel({
			id : 'TrainPlanGrid',
			region : 'center',
			stripeRows : true,
			tbar : this.topbar,
			store : this.store,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			autoHeight : true,
			cm : cm,
			sm : sm,
			plugins : this.rowActions,
			viewConfig : {
				forceFit : true,
				autoFill : true, // 自动填充
				forceFit : true
				// showPreview : false
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

		this.gridPanel.addListener('rowdblclick', function(grid, rowindex, e) {
			if (isGranted('_TrainPlanEdit')) {
				grid.getSelectionModel().each(function(rec) {
					new TrainPlanForm({
						id : rec.data.id
					}).show();
				});
			}
		});
		this.rowActions.on('action', this.onRowAction, this);
	},// end of the initComponents()

	/**
	 * 
	 * @param {}
	 *            self 当前窗体对象
	 */
	search : function(self) {
		if (self.searchPanel.getForm().isValid()) {// 如果合法
			self.searchPanel.getForm().submit({
				waitMsg : '正在提交查询',
				url : __ctxPath + '/hrm/listTrainPlan.do',
				success : function(formPanel, action) {
					var result = Ext.util.JSON.decode(action.response.responseText);
					self.gridPanel.getStore().loadData(result);
				}
			});
		}
	},

	/**
	 * 添加记录
	 */
	createRecord : function() {
		new TrainPlanForm().show();
	},
	/**
	 * 按IDS删除记录
	 * 
	 * @param {}
	 *            ids
	 */
	delByIds : function(ids) {
		Ext.Msg.confirm('信息确认', '删除培训计划的时候会删除相应的报告信息,您确认要删除所选记录吗？', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
					url : __ctxPath + '/hrm/multiDelTrainPlan.do',
					params : {
						ids : ids
					},
					method : 'POST',
					success : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '成功删除该计划！');
						Ext.getCmp('TrainPlanGrid').getStore().reload();
					},
					failure : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
					}
				});
			}
		});// end of comfirm
	},
	/**
	 * 删除多条记录
	 */
	delRecords : function() {
		var gridPanel = Ext.getCmp('TrainPlanGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
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

	/**
	 * 编辑记录
	 * 
	 * @param {}
	 *            record
	 */
	editRecord : function(record) {
		new TrainPlanForm({
			id : record.data.id
		}).show();
	},
	createTrainReport : function(record) {
		new TrainReportForm({
			'record' : record
		}).show();
	},
    createCancle:function(){
    	var tabs = Ext.getCmp('centerTabPanel');
    	 var tabItem = new TrainPlanWin();
		                tabs.add(tabItem);
    },
	cancelTrainPlan : function(record) {
		Ext.Msg.confirm('信息确认', '你确定要取消此培训计划？', function(btn) {
			if (btn == 'yes') {
				var window = new Ext.Window({
					id : 'cancelWin',
					title : '请填写取消的原因',
					width : 300,
					autoHeight : true,
					shadow : false,
					modal : true,
					layout : 'anchor',
					plain : true,
					bodyStyle : 'padding:5px;',
					buttonAlign : 'center',
					items : [{
								xtype : 'form',
								//name : 'cancelForm',
								id : 'cancelForm',
								layout : 'fit',
								items : [{
											xtype : 'textarea',
											name : 'cancelReason',
											id : 'cancelReasonV',
											blankText : '不能为空!',
											allowBlank : false,
											height : 100
										}]
							}]
				});
				window.addButton(new Ext.Button({
					text : '提交',
					iconCls : 'btn-refresh',
					handler : function() {
						if (Ext.getCmp('cancelForm').getForm().isValid()) {
							Ext.Ajax.request({
								url : __ctxPath + '/hrm/cancelTrainPlan.do',
								params : {
									id :record.data.id,
									reason : Ext.getCmp('cancelReasonV').getValue()
								},
								method : 'post',
								success : function() {
									Ext.ux.Toast.msg('操作信息', '成功取消该计划！');
									window.close();
									Ext.getCmp('TrainPlanGrid').getStore().reload();
								},
								failure : function(response, options) {
									Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
								}
							});
						}

					}
				}));
				window.show();
			}
		})

	},
	sendTrainPlanMessage : function(record){
		new TrainPlanSendMsgForm({
			'record' : record
		}).show();
	},
	/**
	 * 管理列中的事件处理
	 * 
	 * @param {}
	 *            grid
	 * @param {}
	 *            record
	 * @param {}
	 *            action
	 * @param {}
	 *            row
	 * @param {}
	 *            col
	 */
	onRowAction : function(gridPanel, record, action, row, col) {
		switch (action) {
			case 'btn-del' :
				this.delByIds(record.data.id);
				break;
			case 'btn-edit' :
				this.editRecord(record);
				break;
			case 'assets-type' :
				if (record.data.trainStatus == '已结束'){
					Ext.ux.Toast.msg('操作信息', '培训计划已生成报告，请到培训报告中操作！');
				}else{
					this.createTrainReport(record);
				}
				break;
			case 'btn-cancel' :
				if (record.data.trainStatus == '未安排' || record.data.trainStatus=='已安排'){
					this.cancelTrainPlan(record);
				}else{
					Ext.ux.Toast.msg('操作信息', '培训计划已结束，不能删除！');
				}
				break;
			case 'menu-administrator' :
				this.sendTrainPlanMessage(record);
				break;
			default :
				break;
		}
	}
});
