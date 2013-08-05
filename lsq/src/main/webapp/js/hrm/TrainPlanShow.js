TrainPlanShow = Ext.extend(Ext.Panel, {
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
		TrainPlanShow.superclass.constructor.call(this, {
					id : 'TrainPlanShow',
					title : '查看培训计划',
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
								text : '培训类型:'
							}, {
								name : 'Q_trainPlan.trainType_S_LK',
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
					url : __ctxPath + "/hrm/listTrainUser.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [{
								name : 'id',
								type : 'int'
							},'sn','trainPlan','appUser','regist','attend']
				});
		this.store.setDefaultSort('id', 'desc');
		// 加载数据
		this.store.load({
					params : {
						start : 0,
						limit : 25
					}
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
								dataIndex : 'trainPlan',
								renderer : function(value,metadata,record) {
									if(value.trainCourse!=null){
									return value.trainCourse.courseName
								}}
							},{
								header : '培训积分',
								dataIndex : 'trainPlan',
								renderer : function(value,metadata,record) {
									if(value.trainCourse!=null){
									return value.trainCourse.credit
								}}
							},{
								header : '计划编号',
								dataIndex : 'trainPlan',
								renderer : function(value) {
									return value.sn
								}
							},{
								header : '培训地点',
								dataIndex : 'trainPlan',
								renderer : function(value) {
									return value.trainPlanPlace
								}
							}, {
								header : '培训时间',
								dataIndex : 'trainPlan',
								renderer : function(value) {
									return value.trainTime
								}
							}, {
								header : '培训状态',
								dataIndex : 'trainPlan',
								renderer : function(value) {
										if(value.trainStatus=="已结束"){
											return value.trainStatus;
										}else{
											if(value.sumNum<=value.nowNum){
												return value.trainStatus+"/"+"已满";
											}else{
												return value.trainStatus+"/"+"未满";
											}
											
										}
								}
							}, {
								header : '培训类型',
								dataIndex : 'trainPlan',
								renderer : function(value) {
									return value.trainType
								}
							}, { 
								header:'参与状态',
							    dataIndex : 'attend',
							    renderer:function(value){
							    	if(value==1){
							    	   return "未参加"
							    	}else{
							    	 return  "已参加"
							    	}
							    }
							    
							},{  
								header : '参加/取消',
								dataIndex : 'trainPlan',
								sortable : false,
								width : 200,
								renderer : function(value,metadata,record,rowIndex ,colIndex ,store ) {
									var id = value.id;
									var attend = record.data.attend;
									var trainStatus = value.trainStatus;
									var str = '';
									if('已结束' == trainStatus||"已取消"==trainStatus||(value.sumNum<=value.nowNum&&attend=="1")){
										return str;
									}
									if(attend == "1"){
										if(value.trainTime!=null){
											str += '<button title="参加" value=" " class="menu-effective" onclick="TrainPlanShow.confirmByAttend('+ id +','+attend+')">&nbsp;</button>';
										}
									}else if(attend == "0" ){
										str += '<button title="取消" value=" " class="btn-cancel" onclick="TrainPlanShow.confirmByAttend('+id+','+attend+')">&nbsp;</button>';
									}
									return str;
						}
							}],
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
					items : [{}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'TrainPlanShowGrid',
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
					//plugins : this.rowActions,
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
				url : __ctxPath + '/hrm/listTrainUser.do',
				success : function(formPanel, action) {
					var result = Ext.util.JSON
							.decode(action.response.responseText);
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
		Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
							url : __ctxPath + '/hrm/multiDelTrainUser.do',
							params : {
								ids : ids
							},
							method : 'POST',
							success : function(response, options) {
								Ext.ux.Toast.msg('操作信息', '成功删除该计划！');
								Ext.getCmp('TrainPlanShowGrid').getStore().reload();
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
	confirmRecords: function() {
		var gridPanel = Ext.getCmp('TrainPlanShowGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要确认的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.id);
		}
		TrainPlanShow.confirmByAttend(ids);
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
			default :
				break;
		}
	}
	
});
TrainPlanShow.confirmByAttend=function(id,attend){
	var msg = '您确认要参加本次培训吗？';
	if(attend == "0"){
		msg = '您确认要取消本次培训吗？';
	}
	Ext.Msg.confirm('信息确认',msg,function(btn){
		if(btn=='yes'){
		  Ext.Ajax.request({
				url : __ctxPath + '/hrm/confirmByAttendTrainUser.do',
				params : {
					id:id,attend:attend
				},
				method : 'POST',
				success : function(response, options) {
					var res = Ext.util.JSON.decode(response.responseText);
					Ext.ux.Toast.msg('操作信息', res.data);
					
					Ext.getCmp('TrainPlanShowGrid').getStore().reload();
				},
				failure : function(response, options) {
					Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
				}
			});
		  }
		})
}