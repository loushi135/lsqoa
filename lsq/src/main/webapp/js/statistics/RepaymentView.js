/**
 * @author 
 * @createtime 
 * @class RepaymentView
 * @extends Ext.Window
 * @description RepaymentView表单
 */
RepaymentView=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	store:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		RepaymentView.superclass.constructor.call(this,{
			id:'RepaymentViewWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			autoScroll : true,
			width : 815,
			height : 350,
			maximizable:true,
			title:'还款记录',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		this.store = new Ext.data.JsonStore({
								url : __ctxPath + "/statistics/listRepayment.do",
								baseParams:{'Q_payment.id_L_EQ':this.paymentId},
								root : 'result',
								totalProperty : 'totalCounts',
								remoteSort : true,
								autoLoad:false,
								fields : [{
											name : 'id',
											type : 'int'
										},
										 'payment',
										 'returnAmount', 'returnDate','operator','returnAmountBig','returnType']
							})
		this.store.setDefaultSort('id', 'desc');
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				bodyStyle: 'padding:10px 10px 10px 10px;',
				border:false,
				url : __ctxPath + '/statistics/savePaymentList.do',
				id : 'RepaymentView',
				defaults : {
					anchor : '98%,98%'
				},
				items : [{
							xtype:'grid',
							id : 'repayGrid',
							height:250,
							width:600,
							trackMouseOver : true,
							disableSelection : false,
							autoScroll : true,
							loadMask : true,
							viewConfig : {
								forceFit : false
							},
							store:this.store,
							cm: new Ext.grid.ColumnModel({
										columns : [new Ext.grid.RowNumberer(), {
														header : 'id',
														dataIndex : 'id',
														hidden : true
													}, {
														header : '还款类别',
														dataIndex : 'returnType'
													}, {
														header : '还款项目',
														dataIndex : 'payment',
														renderer:function(value){
															if(!Ext.isEmpty(value.project)){
																return value.project.proName;
															}
														}
													}, {
														header : '还款人',
														dataIndex : 'payment',
														renderer:function(value){
															if(!Ext.isEmpty(value.user)){
																return value.user.fullname;
															}
														}
													}, {
														header : '还款金额',
														dataIndex : 'returnAmount'
													}, {
														header : '还款金额大写',
														dataIndex : 'returnAmountBig'
													}, {
														header : '还款日期',
														dataIndex : 'returnDate'
													}, {
														header : '操作员',
														dataIndex : 'operator'
													}],
										defaults : {
											sortable : true,
											menuDisabled : false,
											width : 100
										}
									}),
									bbar : new Ext.PagingToolbar({
										pageSize : 25,
										store : this.store,
										displayInfo : true,
										displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
										emptyMsg : "当前没有记录"
									})
								}
						]
			});
		//加载数据
		this.store.load({params : {
					start : 0,
					limit : 25
		}});
		
		//初始化功能按钮
		this.buttons=[
			{
				text : '关闭',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	/**
	 * 取消
	 * @param {} window
	 */
	cancel:function(window){
		window.close();
	}
});