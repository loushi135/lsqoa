/**
 * @author:
 * @class TrainCourseView
 * @extends Ext.Panel
 * @description [TrainCourse]管理
 * @company
 * @createtime:2010-07-19
 */
TrainCourseView = Ext.extend(Ext.Panel, {
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
		TrainCourseView.superclass.constructor.call(this, {
					id : 'TrainCourseView',
					title : '培训课程管理',
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
								text : '课程编号:'
							}, {
								name : 'Q_courseNo_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '课程名称:'
							}, {
								name : 'Q_courseName_S_LK',
								xtype : 'textfield'
							},{
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '课程类型:'
							}, {
								name : 'Q_courseType_S_LK',
								xtype : 'textfield'
							},{
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '讲师:'
							}, {
								name : 'Q_trainTeacher_S_LK',
								xtype : 'textfield'
							},{
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search.createCallback(this)
							}]
				});// end of the searchPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/hrm/listTrainCourse.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : [{
								name : 'courseId',
								type : 'int'
							}, 'courseNo', 'courseName', 'deptName',
							'courseType', 'trainTarget', 'trainCause',
							'coursePriority', 'trainType', 'trainWay',
							'courseTotal', 'courseTime', 'trainTeacher',
							'checkType', 'trainBudget', 'remarks', 'credit']
				});
		this.store.setDefaultSort('courseId', 'desc');
		// 加载数据
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
								hide : !isGranted('_TrainCourseDel'),
								style : 'margin:0 3px 0 3px'
							}, {
								iconCls : 'btn-edit',
								qtip : '编辑',
								hide : !isGranted('_TrainCourseEdit'),
								style : 'margin:0 3px 0 3px'
							}]
				});

		// 初始化ColumnModel
		var sm = new Ext.grid.CheckboxSelectionModel();
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'courseId',
								dataIndex : 'courseId',
								hidden : true
							}, {
								header : '课程编号',
								dataIndex : 'courseNo'
							}, {
								header : '课程名称',
								dataIndex : 'courseName'
							}, {
								header : '部门编号',
								dataIndex : 'deptName'
//								renderer : function(value) {
//									return value.depName
//								}
							}, {
								header : '课程类型',
								dataIndex : 'courseType'
							}, {
								header : '培训对象',
								dataIndex : 'trainTarget'
							}, {
								header : '培训目的',
								dataIndex : 'trainCause'
							}, {
								header : '课程优先级',
								dataIndex : 'coursePriority'
							}, {
								header : '培训类型',
								dataIndex : 'trainType'
							}, {
								header : '培训方式',
								dataIndex : 'trainWay'
							}, {
								header : '总课时',
								dataIndex : 'courseTotal'
							}, {
								header : '课时',
								dataIndex : 'courseTime'
							}, {
								header : '讲师',
								dataIndex : 'trainTeacher'
							}, {
								header : '考核方式',
								dataIndex : 'checkType'
							}, {
								header : '培训成本预算',
								dataIndex : 'trainBudget'
							}, {
								header : '备注说明',
								dataIndex : 'remarks'
							}, {
								header : '学分',
								dataIndex : 'credit'
							}, this.rowActions],
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
								text : '添加',
								hidden : !isGranted('_TrainCourseAdd'),
								xtype : 'button',
								handler : this.createRecord
							}, {
								iconCls : 'btn-del',
								text : '删除',
								hidden : !isGranted('_TrainCourseDel'),
								xtype : 'button',
								handler : this.delRecords,
                                scope : this
							},{
							    iconCls:'btn-signIn',
							    text:'导入课程',
                                xtype:'button',
                                handler:this.importTrainCourse,
                                scope : this
							}]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'TrainCourseGrid',
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
				if(isGranted('_TrainCourseEdit')){
					grid.getSelectionModel().each(function(rec) {
								new TrainCourseForm({
											courseId : rec.data.courseId
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
				url : __ctxPath + '/hrm/listTrainCourse.do',
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
		new TrainCourseForm().show();
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
							url : __ctxPath + '/hrm/multiDelTrainCourse.do',
							params : {
								ids : ids
							},
							method : 'POST',
							success : function(response, options) {
								Ext.ux.Toast.msg('操作信息', '成功删除该课程信息！');
								Ext.getCmp('TrainCourseGrid').getStore()
										.reload();
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
		var gridPanel = Ext.getCmp('TrainCourseGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.courseId);
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
		new TrainCourseForm({
					courseId : record.data.courseId
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
				this.delByIds(record.data.courseId);
				break;
			case 'btn-edit' :
				this.editRecord(record);
				break;
			default :
				break;
		}
	},
	importTrainCourse:function(){
		new TrainCourseImport().show();
	}
});
