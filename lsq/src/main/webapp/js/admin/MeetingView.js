/**
 * @author juql
 * @class MeetingView
 * @extends Ext.Panel
 * @description [Meeting]管理
 */
MeetingView = Ext.extend(Ext.Panel, {
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
				MeetingView.superclass.constructor.call(this, {
							id : 'MeetingView',
							title : '会议申请管理',
							region : 'center',
							layout : 'border',
							items : [ this.gridPanel]
						});
			},// end of constructor

			// 初始化组件
			initUIComponents : function() {
				// 初始化搜索条件Panel
				this.searchPanel = new Ext.FormPanel({
							layout : 'column',
							region : 'north',
							bodyStyle : 'padding:6px 10px 6px 10px',
							border : false,
							frame:true,
							defaults : {
								border : false,
								anchor : '98%,98%'
							},
							items : []
						});// end of the searchPanel

				// 加载数据至store
				this.store = new Ext.data.JsonStore({
							url : __ctxPath + "/admin/listMeeting.do",
							root : 'result',
							totalProperty : 'totalCounts',
							remoteSort : true,
							fields : [{
										name : 'id',
										type : 'int'
									}, 'meetingNo', 'department', 'applyUser',
									'startTime', 'entTime', 'meetingTitle',
									'personNum', 'meetingType', 'meetingRoom',
									'meetingRequire','processRunId']
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
										header : '会议编号',
										dataIndex : 'meetingNo'
									}, {
										header : '申请部门',
										dataIndex : 'department'
									}, {
										header : '申请人',
										dataIndex : 'applyUser'
									}, {
										header : '开始时间',
										dataIndex : 'startTime'
									}, {
										header : '结束时间',
										dataIndex : 'entTime'
									}, {
										header : '会议主题',
										dataIndex : 'meetingTitle'
									}, {
										header : '人数',
										dataIndex : 'personNum'
									}, {
										header : '会议类型',
										dataIndex : 'meetingType',
										renderer : function(value) {
											if (value == '1') {
												return '部门会议';
											}
											if (value == '2') {
												return '公司会议';
											}
											if (value == '3') {
												return '股东会';
											}
											if (value == '4') {
												return '董事会';
											}
											if (value == '5') {
												return '外部接待';
											}
											if (value == '6') {
												return '专项会议';
											}
											if (value == '7') {
												return '员工培训';
											}
										}
									}, {
										header : '会议室',
										dataIndex : 'meetingRoom'
									}, {
										header : '涉会需求',
										dataIndex : 'meetingRequire'
									}, {
										header : '管理',
										dataIndex : 'id',
										sortable : false,
										width : 50,
										renderer : function(value, metadata, record, rowIndex,
												colIndex) {
											var id = record.data.id;
											var str = '';
											if (isGranted('_MeetingDel')) {
												str = '<button title="删除" value=" " class="btn-del" onclick="MeetingView.delByIds('
														+ id + ')">&nbsp;</button>';
											}
											if (isGranted('_MeetingQuery')) {
												str += '&nbsp;<button title="查看" value=" " class="btn-edit" onclick="MeetingView.editRecord('
														+ id + ')">&nbsp;</button>';
											}
											var processRunId=record.data.processRunId;
											str += '<button title="查看审批表单" value=" " class="btn-flowView" onclick="AppUtil.selectProcessForm('+ processRunId +')"></button>';

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
							items : [{
										id : 'delBtn',
										iconCls : 'btn-del',
										text : '删除会议',
										xtype : 'button',
										handler : this.delRecords,
										scope : this,
										listeners : {
											'beforerender' : function() {
												if (!isGranted('_MeetingAdd')) {
													Ext.getCmp('delBtn').hide();
												}
											}
										}
									}]
						});

				this.gridPanel = new Ext.grid.GridPanel({
							id : 'MeetingGrid',
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

				this.gridPanel.addListener('rowdblclick', function(grid,
								rowindex, e) {
							grid.getSelectionModel().each(function(rec) {
										new MeetingForm({
													id : rec.data.id
												}).show();
									});
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
						url : __ctxPath + '/admin/listMeeting.do',
						success : function(formPanel, action) {
							var result = Ext.util.JSON
									.decode(action.response.responseText);
							var params = self.gridPanel.getStore().baseParams; 
			            	Ext.apply(params,self.searchPanel.getForm().getValues());
							self.gridPanel.getStore().loadData(result);
						}
					});
				}
			},

			/**
			 * 删除多条记录
			 */
			delRecords : function() {
				var gridPanel = Ext.getCmp('MeetingGrid');
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
				MeetingView.delByIds(ids);
			}
});
/**
 * 按IDS删除记录
 */
MeetingView.delByIds = function(ids) {
	Ext.Msg.confirm('信息确认', '您确认要删除所选记录吗？', function(btn) {
				if (btn == 'yes') {
					Ext.Ajax.request({
								url : __ctxPath
										+ '/admin/multiDelMeeting.do',
								params : {
									ids : ids
								},
								method : 'POST',
								success : function(response,
										options) {
									Ext.ux.Toast.msg('操作信息',
											'成功删除该会议！');
									Ext.getCmp('MeetingGrid')
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
};

/**
 * 编辑记录
 */
MeetingView.editRecord = function(id) {
	new MeetingForm({
				id : id
			}).show();
};
