/**
 * @author:
 * @class GoodApplyTotal
 * @extends Ext.Panel
 * @description [LeaseHouse]管理
 * @company
 * @createtime:2010-07-19
 */
GoodApplyTotal = Ext.extend(Ext.Panel, {
	// 条件搜索Panel
	searchPanel : null,
	// 数据展示Panel
	gridPanel : null,
	// GridPanel的数据Store
	store : null,
	// 头部工具栏
	topbar : null,
	plugins :null,
	cm : null,
	sm : null,
	columnRows : null,
	summary:null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		GoodApplyTotal.superclass.constructor.call(this, {
					id : 'GoodApplyTotal',
					title : '办公用品汇总统计',
					region : 'center',
					layout : 'border',
					items : [this.searchPanel, this.gridPanel]
				});
	},// end of constructor

	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.summary = new Ext.ux.grid.GridSummary();
		this.loadCm();
		this.searchPanel = new Ext.FormPanel({
					layout : 'column',
					region : 'north',
					height:50,
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
								text : '年或月份:'
							}, {
								name : 'month',
								id:'applyMonth',
								xtype : 'textfield',
								width : 150,
								regex:/(^(1[89]\d\d|2[01][01]\d)-(1[0-2]|0\d)$)|^(1[89]\d\d|2[01][01]\d)$/,
								emptyText:'格式：2013-04或2013',
								regexText:'格式：2013-04或2013'
							} ,{
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : this.search.createCallback(this)
							}]
				});// end of the searchPanel

		// 加载数据至store
		this.store = new Ext.data.JsonStore({
					url : __ctxPath + "/admin/listForReportGoodsApply.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					fields : ['goodsId', 'goodsName',
							'totalNum', 'price', 'totalAmount',
							'goodApplyDepList']
				});
		this.store.setDefaultSort('id', 'desc');
		// 加载数据
		this.store.load({
					params : {
						start : 0,
						limit : 9999
					}
				});

		this.rowActions = new Ext.ux.grid.RowActions({
					header : '管理',
					width : 80,
					actions : [{
					            iconCls:'btn-del'
				                ,qtip:'删除'
				                ,style:'margin:0 3px 0 3px'
				                ,hide:!isGranted("_LeaseHouseDel")
				            },{
								iconCls : 'btn-edit',
								qtip : '编辑',
								style : 'margin:0 3px 0 3px'
							   ,hide:!isGranted("_LeaseHouseEdit")
							},{
								 iconCls:'btn-flowView',
								 qtip:'查看审批表单',
								 style:'margin:0 3px 0 3px'
							}
							]
				});

		
//		var cm = new Ext.grid.ColumnModel({
//					columns : [sm, new Ext.grid.RowNumberer(), {
//								header : 'id',
//								dataIndex : 'id',
//								hidden : true
//							}, {
//								header : '报告人',
//								dataIndex : 'reporter'
//							}, {
//								header : '所属部门',
//								dataIndex : 'reporterDepatment'
//							}, {
//								header : '所在公司',
//								dataIndex : 'reporterCompanyName'
//							}, {
//								header : '项目名称',
//								dataIndex : 'project',
//								renderer:function(value){
//									return value.proName
//								}
//							}, {
//								header : '租期开始日期',
//								dataIndex : 'leaseStart'
//							}, {
//								header : '租期结束日期',
//								dataIndex : 'leaseEnd'
//							}, {
//								header : '月租金',
//								dataIndex : 'monthlyRent'
//							},{
//							    header:'租金总额',
//							    dataIndex:'yearRent'
//							},{
//								header : '申请原因',
//								dataIndex : 'cause'
//							},{
//								header : '附件',
//								dataIndex : 'attachFiles'
//							}
////							, this.rowActions
//							],
//					defaults : {
//						sortable : true,
//						menuDisabled : false,
//						width : 100
//					}
//				});
		// 初始化工具栏
		this.topbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					items : [
							{
								iconCls : 'btn-xls',
								text : '导出',
								xtype : 'button',
								handler :this.exportRecords,
								scope: this
						   }
						   ]
				});

		this.gridPanel = new Ext.grid.GridPanel({
					id : 'GoodApplyTotalGrid',
					region : 'center',
					stripeRows : true,
					tbar : this.topbar,
					store : this.store,
					trackMouseOver : true,
					disableSelection : false,
					autoScroll:true,
					loadMask : true,
					cm : this.cm,
					sm : this.sm,
					plugins : [new Ext.ux.grid.ColumnHeaderGroup({
						rows : [this.columnRows],
						pluginId:'gridHeaderPlugin'
					})],
					viewConfig : {
						forceFit : false
						// showPreview : false
					},
					bbar : new Ext.PagingToolbar({
								pageSize : 9999,
								store : this.store,
								plugins : [new Ext.ux.PageSizePlugin()],
								displayInfo : true,
								displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
								emptyMsg : "当前没有记录"
							})
				});
//		this.rowActions.on('action', this.onRowAction, this);
	},// end of the initComponents()

	/**
	 * 
	 * @param {}
	 *            self 当前窗体对象
	 */
	search : function(self) {
		if (self.searchPanel.getForm().isValid()) {// 如果合法
			self.submitEmptyText();
			self.loadCm();
			Ext.applyIf(self.cm, {rows:[self.columnRows]});
			var params = self.gridPanel.getStore().baseParams;
			Ext.apply(params, self.searchPanel.getForm().getValues());
			self.gridPanel.getStore().load({
				params:params,
				callback:function(records,options,success){
					self.gridPanel.reconfigure(self.store,self.cm);
//					self.summary.refreshSummary();
				}
			});
//			var config = {
//						rows : [self.columnRows],
//						pluginId:'gridHeaderPlugin'
//			}
//			self.gridPanel.getPlugin('gridHeaderPlugin').config = config;
//			self.gridPanel.getPlugin('gridHeaderPlugin').renderHeaders();
		}
	},
	exportRecords:function(self){
		var params = "";
		if(!Ext.isEmpty(Ext.getCmp("applyMonth"))&&!Ext.isEmpty(Ext.getCmp("applyMonth").getValue())){
			params='month='+Ext.getCmp("applyMonth").getValue();
		}
//		Ext.ux.Printer.print(Ext.getCmp('GoodApplyTotalGrid'));
		location =__ctxPath	+ '/admin/exportGoodsApply.do?'+params;
//		Ext.ux.Printer.print(self.gridPanel);
//		startPrint(document.getElementById("GoodApplyTotalGrid"),'导出');
	},
	/**
	 * 添加记录
	 */
	createRecord : function() {
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
									url : __ctxPath
											+ '/statistics/multiDelLeaseHouse.do',
									params : {
										ids : ids
									},
									method : 'POST',
									success : function(response, options) {
										Ext.ux.Toast.msg('操作信息',
												'成功删除该租房信息！');
										Ext.getCmp('GoodApplyTotalGrid').getStore()
												.reload();
									},
									failure : function(response, options) {
										Ext.ux.Toast
												.msg('操作信息', '操作出错，请联系管理员！');
									}
								});
					}
				});// end of comfirm
	},
	/**
	 * 删除多条记录
	 */
	delRecords : function() {
		var gridPanel = Ext.getCmp('GoodApplyTotalGrid');
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
	submitEmptyText: function(){//解决 emptyText传值到后台
		var form = this.searchPanel.getForm();
		var submitValues = form.getValues();
		for (var param in submitValues) {
			if (form.findField(param).emptyText == submitValues[param]) {
				form.findField(param).emptyText = '';
				form.findField(param).setValue('');
			}
		}
	},
	/**
	 * 编辑记录
	 * 
	 * @param {}
	 *            record
	 */
	editRecord : function(record) {
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
			case 'btn-flowView':
				AppUtil.selectProcessForm(record.data.processRunId);
				break;
			default :
				break;
		}
	},
	loadCm : function(){
		// 初始化ColumnModel
		this.sm = new Ext.grid.CheckboxSelectionModel();
		var url=__ctxPath+'/admin/genCmGoodsApply.do';
		var params = "";
		if(!Ext.isEmpty(Ext.getCmp("applyMonth"))&&!Ext.isEmpty(Ext.getCmp("applyMonth").getValue())){
			params='month='+Ext.getCmp("applyMonth").getValue();
		}
		var result = ajaxSyncCall(url,params).data;
		var cmItems = [];  
	    var cmConfig = {};  
	    var cmConfigMoney = {};
	    var rows = [];
		rows.push({colspan : 4});
	    cmItems.push(new Ext.grid.RowNumberer(),
	    	{
	            header : '物品名称',  
	            dataIndex : 'goodsName',
				summaryRenderer : function(v, params, data) {
					return '<font color="red">合计:</font>';
				}
		    },{
	            header : '入库数量',  
	            dataIndex : 'totalNum',
	            summaryType : 'sum'
		    },{
	            header : '单价',  
	            dataIndex : 'price',
				renderer:function(value){
					if(!Ext.isEmpty(value)){
						return value+"元";
					}
				}
		    }
	    );
	    Ext.each(result,function(item,i){
	    	 var depAmount = 0;
	         var cmConfigNum = {
		            header : '数量',  
		            dataIndex : 'singleNum',
		            width:100,
		            renderer : function(value, cellmeta, record, rowIndex, columnIndex){  
		            	 var goodApplyDepList = record.data.goodApplyDepList;
		            	 var str = "";
		            	 if(!Ext.isEmpty(goodApplyDepList)){
		            	 	Ext.each(goodApplyDepList,function(dep,j){
//				            	console.log(i+": "+dep.depName);
//				            	console.log(i+": "+item);
		            	 		if(dep.depName.trim() == item.trim()){
//				            		console.log(i+": "+dep.num);
		            	 			str = dep.num;
		            	 			return;
		            	 		}
		            	 	});
		            	 }
		            	 return Ext.isEmpty(str)?"0":str;
		            }
		        }
	        var cmConfigMoney = {
		            header : '金额',
		            width:100,
		            dataIndex : 'singleAmount',
		            renderer : function(value, cellmeta, record, rowIndex, columnIndex){
		            	 var goodApplyDepList = record.data.goodApplyDepList;
		            	 var str = "";
		            	 if(!Ext.isEmpty(goodApplyDepList)){
		            	 	Ext.each(goodApplyDepList,function(dep,j){
		            	 		if(dep.depName.trim() == item.trim()){
		            	 			str = dep.amount;
		            	 			depAmount+=dep.amount;
//		            	 			record.set("amountT",str);
		            	 			return;
		            	 		}
		            	 	});
		            	 }
		            	 return Ext.isEmpty(str)?"0":(str+"元");
		            },
					summaryRenderer : function(v, params, data) {
						var records = Ext.getCmp('GoodApplyTotalGrid').getStore().getRange();
						var str = 0;
						Ext.each(records,function(record,i){
							var goodApplyDepList = record.data.goodApplyDepList;
			            	 if(!Ext.isEmpty(goodApplyDepList)){
			            	 	Ext.each(goodApplyDepList,function(dep,j){
			            	 		if(dep.depName.trim() == item.trim()){
			            	 			str += dep.amount;
						console.log(str);
			            	 			return;
			            	 		}
			            	 	});
			            	 }
						})
						return '<font color="green">'+str+'元</font>';
					}
	        	}
	        cmItems.push(cmConfigNum,cmConfigMoney);  
	        var cmConfigDept =  {
								header : '<span style="color:green;">'+item+'</span>',
								colspan : 2,
								align : 'center'
				}
			rows.push(cmConfigDept);
		});
		cmItems.push(
	    	{
	            header : '总金额',  
	            dataIndex : 'totalAmount',
				renderer:function(value){
					if(!Ext.isEmpty(value)){
						return value+"元";
					}
				},
				summaryType : 'sum',
				summaryRenderer : function(v, params, data) {
					return '<font color="green">'+v+'元</font>';
				}
		    }
	    );
	    rows.push({});
	    this.columnRows = rows;
		this.cm = new Ext.grid.ColumnModel({
				columns:cmItems,
				defaults : {
					sortable : true,
					menuDisabled : false,
					width : 100
				}
		});
//		console.log(cmItems);
	}
});
