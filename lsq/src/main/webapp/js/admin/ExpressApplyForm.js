/**
 * @author 
 * @createtime 
 * @class ExpressApplyForm
 * @extends Ext.Window
 * @description ExpressApply表单
 */
ExpressApplyForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		ExpressApplyForm.superclass.constructor.call(this,{
			id:'ExpressApplyFormWin',
			layout:'fit',
			iconCls : 'menu-express',
			items:this.formPanel,
			modal:true,
			height:300,
			width:500,
			title:'快递详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				trackResetOnLoad:true,
				bodyStyle: 'padding:10px 10px 10px 10px',
				border:false,
				url : __ctxPath + '/admin/saveExpressApply.do',
				id : 'ExpressApplyForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'expressApply.id',
							id : 'id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						},{
							name : 'expressApply.applyerId',
							id : 'applyerId',
							xtype:'hidden'
						},{
							name : 'expressApply.provinceId',
							id : 'provinceId',
							xtype:'hidden'
						},{
							name : 'expressApply.province',
							id : 'province',
							xtype:'hidden'
						}, {
							name : 'expressApply.cityId',
							id : 'cityId',
							xtype:'hidden'
						},{
							name : 'expressApply.city',
							id : 'city',
							xtype:'hidden'
						}, {
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:3px;',
							items : [{
								xtype : 'label',
								text : '申请人:',
								width : 105,
								style : 'padding-left:0px;padding-bottom:3px;'
							}, {
								name : 'expressApply.applyer',
								id : 'applyer',
								xtype : 'textfield',
								readOnly : true,
								allowBlank : false,
								width : 200
							}, {
								xtype : 'button',
								text : '选择人员',
								iconCls : 'btn-user-sel',
								handler : function() {
									UserSelector.getView(function(id, name) {
										Ext.getCmp('applyer').setValue(name);
										Ext.getCmp('applyerId').setValue(id);
									}, true).show();
								}
							}, {
								xtype : 'button',
								text : '清除记录',
								iconCls : 'reset',
								handler : function() {
									Ext.getCmp('applyer').setValue('');
								}
							}]
						},{
								fieldLabel : '申请日期',	
								name : 'expressApply.applyDate',
								id : 'applyDate',
								xtype : 'datefield',
								format : 'Y-m-d',
								allowBlank : false
						},{
								fieldLabel : '快递属性',	
								xtype : 'combo',
								hiddenName : 'expressApply.expressType',
								id : 'expressType',
								width : 150,
								mode : 'local',
								editable : false,
								allowBlank : false,
								triggerAction : 'all',
								store : [[1, '客户'], [2, '供应商'], [3, '其他']],
								listeners:{
									'select':function(cmb){
										if(cmb.value==3){
											Ext.getCmp('toWhereNameSelectbtn').disable();
											Ext.getCmp('toWhereNameCancelbtn').disable();
										}else{
											Ext.getCmp('toWhereNameSelectbtn').enable();
											Ext.getCmp('toWhereNameCancelbtn').enable();
										}
										
									}
								}
						},{
								fieldLabel : '目的地城市',	
								name : 'cityAprovince',
								id : 'cityAprovince',
								listeners : {
									'focus' : function() {
										CitySelector.getView(function(cityId, cityName,provinceId,provinceName) {
															//alert(cityId+'-'+cityName+'-'+provinceId+'-'+provinceName);
															Ext.getCmp('cityAprovince').setValue(provinceName+'/'+cityName);
															Ext.getCmp('provinceId').setValue(provinceId);
															Ext.getCmp('cityId').setValue(cityId);
															Ext.getCmp('province').setValue(provinceName);
															Ext.getCmp('city').setValue(cityName);
														},true)
												.show()
									}
								}
						},{
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:3px;',
							items : [{
								xtype : 'label',
								text : '目的单位:',
								width : 105,
								style : 'padding-left:0px;padding-bottom:3px;'
							}, {
								name : 'expressApply.toWhereName',
								id : 'toWhereName',
								xtype : 'textfield',
								readOnly : false,
								allowBlank : false,
								width : 200
							}, {
								xtype : 'button',
								text : '选择单位',
								id:'toWhereNameSelectbtn',
								iconCls : 'btn-user-sel',
								handler : function() {
									
									var expressType= Ext.getCmp('expressType').getValue();
									switch(expressType){
										case 1:
											CustomerSelector.getView(function(customerId, customerName) {
												Ext.getCmp('toWhereName').setValue(customerName);
											}, true).show();
											break;
										case 2:
											new SuppliersAssessSelector(function(suppliersId,suppliersName,contractNo) {
												Ext.getCmp('toWhereName').setValue(suppliersName);
											}, true).show();
											break;
										default:
											Ext.ux.Toast.msg('操作信息', '请先选择快递属性！');
										
									}
								}
							}, {
								xtype : 'button',
								text : '清除记录',
								id:'toWhereNameCancelbtn',
								iconCls : 'reset',
								handler : function() {
									Ext.getCmp('toWhereName').setValue('');
								}
							}
							]
						},
						{
							xtype : 'container',
							layout : 'column',
							style : 'padding-left:0px;padding-bottom:3px;',
							items : [{
								xtype : 'label',
								text : '分摊对象:',
								width : 105,
								style : 'padding-left:0px;padding-bottom:3px;'
							},{
								xtype:'combo',
								mode:'local',
								hiddenName:'dispatchType',
								id:'dispatchTypeCombo',
								editable : false,
								allowBlank : false,
								triggerAction : 'all',
								store:['部门','项目'],
								value:'部门',
								width:80,
								listeners:{
									select:function(field){
										if(field.getValue()=='部门'){
											Ext.getCmp("expressApplyDeptName").show();
											Ext.getCmp("expressApplyDeptName").allowBlank=false;
											Ext.getCmp("expressApplyProContainer").hide();
											Ext.getCmp("expressApplyProName").reset();
											Ext.getCmp("expressApplyProName").allowBlank=true;
											Ext.getCmp("expressApply.projectNew.id").reset();
										}else if(field.getValue()=='项目'){
											Ext.getCmp("expressApplyProContainer").show();
											Ext.getCmp("expressApplyProName").allowBlank=false;
											Ext.getCmp("expressApplyDeptName").hide();
											Ext.getCmp("expressApplyDeptName").reset();
											Ext.getCmp("expressApplyDeptName").allowBlank=true;
											Ext.getCmp("expressApply.dept.depId").reset();
										}
									}
								}
							},{
								xtype:'textfield',
								name:'expressApplyDeptName',
								id:'expressApplyDeptName',
								style:'margin-left:5px;',
								width:272,
								emptyText:'请选择部门',
								allowBlank : false,
								listeners:{
									focus:function(){
										DepSelector.getView(function(ids, names) {
											Ext.getCmp("expressApply.dept.depId").setValue(ids);
											Ext.getCmp("expressApplyDeptName").setValue(names);
										}, true).show();
									}
								}
							},{
								xtype:'container',
								id:'expressApplyProContainer',
								layout : "column",
								border : false,
								unstyled : false,
								items:[
									{
										xtype:'textfield',
										name:'expressApplyProName',
										id:'expressApplyProName',
										style:'margin-left:5px;',
										width:272,
										emptyText:'请选择项目',
										listeners:{
											'focus':function(){
												ProjectNewSelector.getView(function(proId,proName){
													Ext.getCmp("expressApply.projectNew.id").setValue(proId);
													Ext.getCmp("expressApplyProName").setValue(proName);
												},true,true).show();
											},
											afterrender:function(field){
												Ext.getCmp("expressApplyProContainer").hide();
											}
										}
									}
								]
							},{
								xtype:'hidden',
								name:'expressApply.projectNew.id',
								id:'expressApply.projectNew.id'
							},{
								xtype:'hidden',
								name:'expressApply.dept.depId',
								id:'expressApply.dept.depId'
							}
							]
						}
						,{
								fieldLabel : '快递公司',	
								name : 'expressApply.expressName',
								id : 'expressName',
								allowBlank : false
						},{
								fieldLabel : '快递单号',	
								name : 'expressApply.expressNo',
								id : 'expressNo',
								allowBlank : false
						}]
			});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/admin/getExpressApply.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var res = action.result.data;
					var applyDate = action.result.data.applyDate;
					var applyDateField = Ext.getCmp('applyDate');
					applyDateField.setValue(new Date(getDateFromFormat(applyDate,
							"yyyy-MM-dd HH:mm:ss")));
					applyDateField.originalValue=new Date(getDateFromFormat(applyDate,
							"yyyy-MM-dd HH:mm:ss"));
					if(!Ext.isEmpty(res.dept)){
						Ext.getCmp("expressApply.dept.depId").setValue(res.dept.depId);
						Ext.getCmp("expressApply.dept.depId").originalValue=res.dept.depId;
						Ext.getCmp("expressApplyDeptName").setValue(res.dept.depName);
						Ext.getCmp("expressApplyDeptName").originalValue=res.dept.depName;
						Ext.getCmp("dispatchTypeCombo").setValue("部门");
						Ext.getCmp("dispatchTypeCombo").originalValue="部门";
						Ext.getCmp("expressApplyDeptName").show();
						Ext.getCmp("expressApplyDeptName").allowBlank=false;
						Ext.getCmp("expressApplyProContainer").hide();
						Ext.getCmp("expressApplyProName").reset();
						Ext.getCmp("expressApplyProName").allowBlank=true;
						Ext.getCmp("expressApply.projectNew.id").reset();
					}
					if(!Ext.isEmpty(res.projectNew)){
						Ext.getCmp("expressApply.projectNew.id").setValue(res.projectNew.id);
						Ext.getCmp("expressApply.projectNew.id").originalValue=res.projectNew.id;
						Ext.getCmp("expressApplyProName").setValue(res.projectNew.proName);
						Ext.getCmp("expressApplyProName").originalValue=res.projectNew.proName;
						Ext.getCmp("dispatchTypeCombo").setValue("项目");
						Ext.getCmp("dispatchTypeCombo").originalValue="项目";
						Ext.getCmp("expressApplyProContainer").show();
						Ext.getCmp("expressApplyProName").allowBlank=false;
						Ext.getCmp("expressApplyDeptName").hide();
						Ext.getCmp("expressApplyDeptName").reset();
						Ext.getCmp("expressApplyDeptName").allowBlank=true;
						Ext.getCmp("expressApply.dept.depId").reset();
					}
					Ext.getCmp('cityAprovince').setValue(action.result.data.province+ '/' +action.result.data.city);
					Ext.getCmp('cityAprovince').originalValue=action.result.data.province+ '/' +action.result.data.city;
							
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
					var gridPanel=Ext.getCmp('ExpressApplyGrid');
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