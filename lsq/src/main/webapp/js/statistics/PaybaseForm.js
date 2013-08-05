/**
 * @author 
 * @createtime 
 * @class PaybaseForm
 * @extends Ext.Window
 * @description Paybase表单
 */
PaybaseForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		PaybaseForm.superclass.constructor.call(this,{
			id:'PaybaseFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			height:200,
			width:400,
			maximizable:true,
			title:'付款基数详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				bodyStyle: 'padding:10px 10px 10px 10px',
				border:false,
				trackResetOnLoad : true,
				url : __ctxPath + '/statistics/savePaybase.do',
				id : 'PaybaseForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
						name : 'paybase.paymentBaseId',
						id : 'paymentBaseId',
						xtype : 'hidden',
						value : this.paymentBaseId == null
								? ''
								: this.paymentBaseId
					}, {
						name : 'paybase.projectNew.id',
						id : 'paybase.projectNew.id',
						xtype : 'hidden',
						value:this.proId
					}, {
						fieldLabel : '付款时间',
						allowBlank:false,
						name : 'paybase.paymentTime',
						id : 'paymentTime',
						xtype:'datefield',
						format:'Y-m-d'
					}, {
						fieldLabel : '已付款金额',
						allowBlank:false,
						xtype:'numberfield',
						name : 'paybase.paymentBase',
						id : 'paymentBase',
						enableKeyEvents:true,
						regex:/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
						regexText:'请输入正确格式的金额',
						listeners : {
							keyup: function(field) {
								var paymentSumBig = AmountInWords(field.getValue());
								Ext.getCmp('paymentBaseBig').setValue(paymentSumBig); 
							}
						}
					}, {
						fieldLabel : '已付款金额大写',
						name : 'paybase.paymentBaseBig',
						id : 'paymentBaseBig',
						readOnly:true
					}
					]
			});
		// 加载表单对应的数据
		if (this.paymentBaseId != null && this.paymentBaseId != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getPaybase.do?paymentBaseId='+ this.paymentBaseId,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var record = Ext.util.JSON.decode(action.response.responseText);
					Ext.getCmp("paybase.projectNew.id").setValue(record.data.projectNew.id);
					Ext.getCmp("paybase.projectNew.id").originalValue=record.data.projectNew.id;
				},
				failure : function(form, action) {
				}
			});
		}
		//初始化功能按钮
		this.buttons=[{
				text : '保存',
				iconCls : 'btn-save',
				handler :this.save.createCallback(this.formPanel,this)
			}, {
				text : '重置',
				iconCls : 'btn-reset',
				handler :this.reset.createCallback(this.formPanel)
			},{
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	
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
					var gridPanel=Ext.getCmp('PaybaseGrid');
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