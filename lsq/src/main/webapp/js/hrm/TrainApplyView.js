/**
 * @author:
 * @class TrainApplyView
 * @extends Ext.Panel
 * @description [TrainApply]管理
 * @company
 * @createtime:2010-07-19
 */
TrainApplyView = Ext.extend(Ext.Panel, {
	//条件搜索Panel
	searchPanel : null,
	//数据展示Panel
	gridPanel : null,
	//GridPanel的数据Store
	store : null,
	//头部工具栏
	topbar : null,
	//构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		//初始化组件
		this.initUIComponents();
		//调用父类构造
		TrainApplyView.superclass.constructor.call(this, {
			id : 'TrainApplyView',
			title : '培训申请管理',
			region : 'center',
			layout : 'border',
			items : [this.searchPanel, this.gridPanel]
		});
	},//end of constructor

	//初始化组件
	initUIComponents : function() {
		var thiz = this;
		//初始化搜索条件Panel
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
					text : '申请人:'
				}, {
					name : 'Q_applyUser_S_LK',
					xtype : 'textfield'
				}, {
					style : 'margin-top:5px;',
					xtype : 'label',
					text : '申请部门:'
				}, {
					name : 'Q_department_S_LK',
					xtype : 'textfield'
				}, {
					style : 'margin-top:5px;',
					xtype : 'label',
					text : '培训对象类型:'
				}, {
					name : 'Q_trainTarget_S_LK',
					xtype : 'textfield'
				}, {
					style : 'margin-top:5px;',
					xtype : 'label',
					text : '培训类型:'
				}, {
					name : 'Q_trainType_S_LK',
					xtype : 'textfield'
				}, {
					style : 'margin-top:5px;',
					xtype : 'label',
					text : '培训课程:'
				}, {
					name : 'Q_trainCourse_S_LK',
					xtype : 'textfield'
				}, {
					xtype : 'button',
					text : '查询',
					iconCls : 'search',
					handler : this.search.createCallback(this)
				}]
		});//end of the searchPanel

		//加载数据至store
		this.store = new Ext.data.GroupingStore({
			proxy : new Ext.data.HttpProxy({
				url : __ctxPath + "/hrm/listTrainApply.do"
			}),
			reader : new Ext.data.JsonReader({
				root : 'result',
				totalProperty : 'totalCounts',
				id : 'id',
				fields : [{
					name : 'id',
					type : 'int'
				}, 'applyUser', 'department','trainTarget', 'trainPlan', 'trainType', 'trainCourse', 'createTime','publish']
			}),
			remoteSort : true,
			groupField:"department"
		});
		this.store.setDefaultSort('id', 'desc');
		//加载数据
		this.store.load({
			params : {
				start : 0,
				limit : 25
			}
		});

		this.rowActions = new Ext.ux.grid.RowActions({
			header : '管理',
			width : 80,
			actions : [{
					iconCls : 'btn-del',
					qtip : '删除',
					style : 'margin:0 3px 0 3px'
				}, {
					iconCls : 'btn-edit',
					qtip : '编辑',
					style : 'margin:0 3px 0 3px'
				},{
					iconCls : 'btn-ok',
					qtip : '发布计划',
					style : 'margin:0 3px 0 3px'
				}]
		});

		//初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
			columns : [sm, new Ext.grid.RowNumberer(), {
					header : 'id',
					dataIndex : 'id',
					hidden : true
				}, {
					header : '申请人',
					dataIndex : 'applyUser',
					renderer:function(value,meta,record){
						if(!Ext.isEmpty(value)){
							return value.fullname;
						}
					}
				}, {
					header : '申请时间',
					dataIndex : 'createTime'
				}, {
					header : '申请部门',
					dataIndex : 'department',
					renderer:function(value,meta,record){
						if(!Ext.isEmpty(value)){
							return value.depName;
						}
					}
				}, {
					header : '培训对象类型',
					dataIndex : 'trainTarget'
				}, {
					header : '培训类型',
					dataIndex : 'trainType'
				}, {
					header : '培训课程',
					dataIndex : 'trainCourse',
					renderer:function(value,meta,record){
						if(!Ext.isEmpty(value)){
							return value.courseName;
						}
					}
				}, this.rowActions],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		//初始化工具栏
		this.topbar = new Ext.Toolbar({
			height : 30,
			bodyStyle : 'text-align:left',
			items : [{
					iconCls : 'btn-add',
					text : '添加',
					xtype : 'button',
					handler : this.createRecord
				}, {
					iconCls : 'btn-del',
					text : '删除',
					xtype : 'button',
					handler : this.delRecords,
					scope : this
				}]
		});

		this.gridPanel = new Ext.grid.GridPanel({
			id : 'TrainApplyGrid',
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
				enableRowBody : false,
				showPreview : false
			},
			view:new Ext.grid.GroupingView({
					forceFit:true,   
		            autoFill: true,   
					groupTextTpl:"{text} 共{[values.rs.length]}条",
					groupByText:"使用当前字段进行分组",
					showGroupsText:"表格分组",
					showGroupName:true,
					startCollapsed:false,
					hideGroupedColumn:true
				}),
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
			grid.getSelectionModel().each(function(rec) {
				new TrainApplyForm({
					id : rec.data.id
				}).show();
			});
		});
		this.gridPanel.getStore().on('load',function(s,records){
			var count = 0;
			s.each(function(r){
				if(!Ext.isEmpty(r.data.publish)){
					thiz.gridPanel.getView().getRow(count).style.backgroundColor='#FFFF00';
				}
				count++;
			})
			
		})

		this.rowActions.on('action', this.onRowAction, this);
	},//end of the initComponents()

	/**
	 * 
	 * @param {}
	 *            self 当前窗体对象
	 */
	search : function(self) {
		if (self.searchPanel.getForm().isValid()) {//如果合法
			var params = self.gridPanel.getStore().baseParams;
			Ext.apply(params, self.searchPanel.getForm().getValues());
			self.gridPanel.getStore().load(params);
		}
	},

	/**
	 * 添加记录
	 */
	createRecord : function() {
		new TrainApplyForm().show();
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
					url : __ctxPath + '/hrm/multiDelTrainApply.do',
					params : {
						ids : ids
					},
					method : 'POST',
					success : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '成功删除该！');
						Ext.getCmp('TrainApplyGrid').getStore().reload();
					},
					failure : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
					}
				});
			}
		});//end of comfirm
	},
	/**
	 * 删除多条记录
	 */
	delRecords : function() {
		var gridPanel = Ext.getCmp('TrainApplyGrid');
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
		new TrainApplyForm({
			id : record.data.id
		}).show();
	},
	createPlane : function(record){
		new TrainPlanForm({
			sign : 'trainApplyCreatePlan',
			trainApplyId : record.data.id,
			courseId : record.data.trainCourse.courseId,
			courseNo : record.data.trainCourse.courseNo,
			courseName : record.data.trainCourse.courseName,
			trainType : record.data.trainType,
			trainTarget : record.data.trainTarget
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
			case 'btn-ok' :
				if(Ext.isEmpty(record.data.publish)){
					this.createPlane(record)
				}else{
					Ext.ux.Toast.msg("信息", "该申请已发布计划！");
				}
			default :
				break;
		}
	}
});
