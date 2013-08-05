/**
 * @author 
 * @createtime 
 * @class BankpayReportForm
 * @extends Ext.Window
 * @description Bankpayapply表单
 */
BankpayReportForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	// 条件搜索Panel
	searchPanel : null,
	// 数据展示Panel
	gridPanel : null,
	// GridPanel的数据Store
	store : null,
	// 头部工具栏
	topbar : null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		BankpayReportForm.superclass.constructor.call(this,{
			id:'BankpayReportFormWin',
			layout:'border',
			items:[this.searchPanel, this.gridPanel],
			modal:true,
			width : 900,
			height : 550,
			title:'工程项目银行付款统计详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		// 初始化搜索条件Panel
		this.searchPanel = new Ext.FormPanel({
					layout : 'column',
					region : 'north',
					bodyStyle : 'padding:6px 10px 6px 10px',
					border : false,
					frame : true,
					height:50,
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
								text : '项目名称:'
							}, {
								name : 'Q_bpaProjectName_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '项目编号:'
							}, {
								name : 'Q_bpaProjectNo_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '付款类别:'
							}, {
								name : 'Q_bpaPayType_S_LK',
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '收款单位:'
							}, {
								name : 'Q_bpaReceiptDept_S_LK',
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
					url : __ctxPath + "/statistics/listBankpayapply.do",
					root : 'result',
					totalProperty : 'totalCounts',
					remoteSort : true,
					baseParams:{'Q_bpaProjectNo_S_EQ':this.proNo},
					fields : [{
								name : 'bankPayApplyId',
								type : 'int'
							}, 'bpaProjectName', 'bpaProjectNo', 'bpaPayType',
							'bpaReceiptDept', 'bpaReceiptReason',
							'bpaContract', 'bpaSumMoney', 'bpaSumMoneyRatio',
							'bpaFundBalance', 'bpaPayRatio',
							'bpaInvoiceBalance', 'bpaApplyMoneyXX','bpaApplyMoneyDX',
							'bpaGTFiveMillion', 'bpaRemark','processRunId']
				});
		this.store.setDefaultSort('bankPayApplyId', 'desc');
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
								header : 'bankPayApplyId',
								dataIndex : 'bankPayApplyId',
								hidden : true
							}, {
								header : '项目名称',
								dataIndex : 'bpaProjectName'
							}, {
								header : '项目编号',
								dataIndex : 'bpaProjectNo'
							}, {
								header : '付款类别',
								dataIndex : 'bpaPayType'
							}, {
								header : '收款单位',
								dataIndex : 'bpaReceiptDept'
							}, {
								header : '收款事由',
								dataIndex : 'bpaReceiptReason'
							}, {
								header : '合同/结算金额',
								dataIndex : 'bpaContract',
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value+"元";
									}
								}
							}, {
								header : '累计已付款',
								dataIndex : 'bpaSumMoney',
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value+"元";
									}
								}
							}, {
								header : '已付款比例',
								dataIndex : 'bpaSumMoneyRatio',
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value+"%";
									}
								}
							}, {
								header : '资金结余',
								dataIndex : 'bpaFundBalance',
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value+"元";
									}
								}
							}, {
								header : '垫资审批额度',
								dataIndex : 'bpaPayRatio'
							}, {
								header : '发票结余',
								dataIndex : 'bpaInvoiceBalance',
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value+"元";
									}
								}
							}, {
								header : '本次申请用款(小写)',
								dataIndex : 'bpaApplyMoneyXX',
								renderer:function(value){
									if(!Ext.isEmpty(value)){
										return value+"元";
									}
								}
							}, {
								header : '本次申请用款(大写)',
								dataIndex : 'bpaApplyMoneyDX'
							}, {
								header : '经办人员',
								dataIndex : 'bpaRemark'
							}],
					defaults : {
						sortable : true,
						menuDisabled : false,
						width : 100
					}
				});
		this.gridPanel = new Ext.grid.GridPanel({
					id : 'BankpayReportFormGrid',
					region : 'center',
					stripeRows : true,
					tbar : this.topbar,
					store : this.store,
					trackMouseOver : true,
					disableSelection : false,
					autoScroll : true,
					loadMask : true,
					height : 100,
					cm : cm,
					sm : sm,
					plugins : this.rowActions,
					viewConfig : {
						forceFit : false
						// showPreview : false
					},
					bbar : new Ext.PagingToolbar({
								pageSize : 25,
								store : this.store,
								displayInfo : true,
								displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
								emptyMsg : "当前没有记录"
							})
				});
		this.buttons=[
//				{
//					text : '保存',
//					iconCls : 'btn-save',
//					handler :this.save.createCallback(this.formPanel,this)
//				}, {
//					text : '重置',
//					iconCls : 'btn-reset',
//					handler :this.reset.createCallback(this.formPanel)
//				},
				{
					text : '关闭',
					iconCls : 'btn-cancel',
					handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	/**
	 * 
	 * @param {}
	 *            self 当前窗体对象
	 */
	search : function(self) {
		if(self.searchPanel.getForm().isValid()){//如果合法
			var params = self.gridPanel.getStore().baseParams; 
            Ext.apply(params,self.searchPanel.getForm().getValues());
            self.gridPanel.getStore().load(params);
		}
	},
	/**
	 * 重置
	 * @param {} formPanel
	 */
	reset:function(formPanel){
		formPanel.getForm().reset();
	},
	/**
	 * 取消
	 * @param {} window
	 */
	cancel:function(window){
		window.close();
	},
	/**
	 * 保存记录
	 */
	save:function(formPanel,window){
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
				method : 'POST',
				waitMsg : '正在提交数据...',
				success : function(fp, action) {
					Ext.ux.Toast.msg('操作信息', '成功保存信息！');
					var gridPanel=Ext.getCmp('BankpayapplyGrid');
					if(gridPanel!=null){
						gridPanel.getStore().reload();
					}
					window.close();
				},
				failure : function(fp, action) {
					Ext.MessageBox.show({
								title : '操作信息',
								msg : '信息保存出错，请联系管理员！',
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
					window.close();
				}
			});
		}
	}//end of save
	
});