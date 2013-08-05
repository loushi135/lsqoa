/**
 * @author:
 * @class BankpayapplyotherView
 * @extends Ext.Panel
 * @description [Bankpayapplyother]管理
 * @company 
 * @createtime:2010-07-19
 */
BankpayapplyotherView=Ext.extend(Ext.Panel,{
	//条件搜索Panel
	searchPanel:null,
	//数据展示Panel
	gridPanel:null,
	//GridPanel的数据Store
	store:null,
	//头部工具栏
	topbar:null,
	//构造函数
	constructor:function(_cfg){
			Ext.applyIf(this,_cfg);
			//初始化组件
			this.initUIComponents();
			//调用父类构造
			BankpayapplyotherView.superclass.constructor.call(this,{
				id:'BankpayapplyotherView',
				title:'其他银行付款管理',
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
			frame : true,
			height:50,
			defaults:{
				border:false,
				anchor:'98%,98%'
			},
		    items : [	
				{
					style : 'margin-top:3px;',
					xtype : 'label',
					text : '请输入查询条件:'
				},{
					style : 'margin-top:3px;margin-left:5px;',
					xtype : 'label',
					text : '项目名称:'
				},{
					name:'Q_bpaProjectName_S_LK',
					xtype:'textfield'
				},{
					style : 'margin-top:3px;margin-left:5px;',
					xtype : 'label',
					text : '项目编号:'
				},{
					name:'Q_bpaProjectNo_S_LK',
					xtype:'textfield'
				},{
					style : 'margin-top:3px;margin-left:5px;',
					xtype : 'label',
					text : '付款类别:'
				},{
					name:'Q_bpaPayType_S_LK',
					xtype:'textfield'
				},{
					style : 'margin-top:3px;margin-left:5px;',
					xtype : 'label',
					text : '收款单位:'
				},{
					name:'Q_bpaReceiptDept_S_LK',
					xtype:'textfield'
				},{
						xtype : 'button',
						text : '查询',
						iconCls : 'search',
						handler : this.search.createCallback(this)
					}
				]
		});//end of the searchPanel
		
		//加载数据至store
		this.store = new Ext.data.JsonStore({
							url : __ctxPath+"/statistics/listBankpayapplyother.do",
							root : 'result',
							totalProperty : 'totalCounts',
							remoteSort : true,
							fields : [{name : 'bankPayApplyId',type:'int'},'bpaProjectName','bpaProjectNo','bpaPayType','bpaReceiptDept','bpaReceiptReason','bpaContract'
									,'bpaSumMoney','bpaSumMoneyRatio','bpaInvoiceBalance','bpaApplyMoneyXX','bpaApplyMoneyDX','bpaRemark','processRunId']
		});
		this.store.setDefaultSort('bankPayApplyId', 'desc');
		//加载数据
		this.store.load({params : {
					start : 0,
					limit : 25
		}});
		
		this.rowActions = new Ext.ux.grid.RowActions({
			header:'管理',
			width:80,
			actions:[
					{iconCls:'btn-del' ,qtip:'删除' ,style:'margin:0 3px 0 3px',hide:!isGranted("_BankPayApplyotherDel")},
					{iconCls : 'btn-edit',qtip : '编辑',style : 'margin:0 3px 0 3px',hide:!isGranted("_BankPayApplyotherEdit")}
					,{
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
						header : 'bankPayApplyId',
						dataIndex : 'bankPayApplyId',
						hidden : true
					} 
						,{
										header : '项目名称',	
										dataIndex : 'bpaProjectName'
						},{
										header : '项目编号',	
										dataIndex : 'bpaProjectNo'
						},{
										header : '付款类别',	
										dataIndex : 'bpaPayType'
						},{
										header : '收款单位',	
										dataIndex : 'bpaReceiptDept'
						},{
										header : '收款事由',	
										dataIndex : 'bpaReceiptReason'
						},{
										header : '合同/结算金额',	
										dataIndex : 'bpaContract',
										renderer:function(value){
											if(!Ext.isEmpty(value)){
												return value+"元";
											}
										}
						},{
										header : '累计已付款',	
										dataIndex : 'bpaSumMoney',
										renderer:function(value){
											if(!Ext.isEmpty(value)){
												return value+"元";
											}
										}
						},{
										header : '已付款比例',	
										dataIndex : 'bpaSumMoneyRatio',
										renderer:function(value){
											if(!Ext.isEmpty(value)){
												return value+"%";
											}
										}
						},{
										header : '发票结余',	
										dataIndex : 'bpaInvoiceBalance',
										renderer:function(value){
											if(!Ext.isEmpty(value)){
												return value+"元";
											}
										}
						},{
										header : '本次申请用款',	
										dataIndex : 'bpaApplyMoneyXX',
										renderer:function(value){
											if(!Ext.isEmpty(value)){
												return value+"元";
											}
										}
						},{
										header : '本次申请用款',	
										dataIndex : 'bpaApplyMoneyDX'
						},{
										header : '经办人员（备注）',	
										dataIndex : 'bpaRemark'
						},this.rowActions],
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
							text : '添加[其他银行付款]',
							xtype : 'button',
							hidden:!isGranted("_BankPayApplyotherAdd"),
							handler:this.createRecord
						}, {
							iconCls : 'btn-del',
							text : '删除[其他银行付款]',
							xtype : 'button',
							hidden:!isGranted("_BankPayApplyotherDel"),
							handler :this.delRecords,
							scope: this
						}]
			});
			
		this.gridPanel=new Ext.grid.GridPanel({
				id : 'BankpayapplyotherGrid',
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

			this.gridPanel.addListener('rowdblclick', function(grid, rowindex, e) {
				grid.getSelectionModel().each(function(rec) {
					 if(isGranted("_BankPayApplyotherEdit")){
						new BankpayapplyotherForm({bankPayApplyId:rec.data.bankPayApplyId}).show();
					 }
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
					url:__ctxPath+'/statistics/listBankpayapplyother.do',
					success:function(formPanel,action){
			            var result=Ext.util.JSON.decode(action.response.responseText);
			            var params = self.gridPanel.getStore().baseParams; 
			            Ext.apply(params,self.searchPanel.getForm().getValues());
			            self.gridPanel.getStore().loadData(result);
					}
			});
		}
	},
	
	/**
	 * 添加记录
	 */
	createRecord:function(){
		new BankpayapplyotherForm().show();
	},
	/**
	 * 按IDS删除记录
	 * @param {} ids
	 */
	delByIds:function(ids){
		Ext.Msg.confirm('信息确认','您确认要删除所选记录吗？',function(btn){
			if(btn=='yes'){
				Ext.Ajax.request({
								url:__ctxPath+'/statistics/multiDelBankpayapplyother.do',
								params:{ids:ids},
								method:'POST',
								success:function(response,options){
									Ext.ux.Toast.msg('操作信息','成功删除该[其他银行付款]！');
									Ext.getCmp('BankpayapplyotherGrid').getStore().reload();
								},
								failure:function(response,options){
									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
								}
							});
			}
		});//end of comfirm
	},
	/**
	 * 删除多条记录
	 */
	delRecords:function(){
		var gridPanel=Ext.getCmp('BankpayapplyotherGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.bankPayApplyId);
		}
		this.delByIds(ids);
	},
	
	/**
	 * 编辑记录
	 * @param {} record
	 */
	editRecord:function(record){
		new BankpayapplyotherForm({bankPayApplyId:record.data.bankPayApplyId}).show();
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
			case 'btn-del':
				this.delByIds(record.data.bankPayApplyId);
				break;
			case 'btn-edit':
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
