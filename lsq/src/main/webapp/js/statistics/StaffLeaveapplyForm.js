/**
 * @author 
 * @createtime 
 * @class StaffLeaveapplyForm
 * @extends Ext.Window
 * @description StaffLeaveapply表单
 */
StaffLeaveapplyForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		StaffLeaveapplyForm.superclass.constructor.call(this,{
			id:'StaffLeaveapplyFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			autoScroll : true,
			width : 750,
			height :340,
			maximizable:false,
			title:'离职详细信息',
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
				url : __ctxPath + '/statistics/saveStaffLeaveapply.do',
				id : 'StaffLeaveapplyForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
					xtype:'panel',
					layout:'column',
					border:false,
					defaultType : 'label',
					style : "padding-top:3px;",
					items:[
						  {
									text : '所属公司:',
									width : 90
							},
						{
									xtype:'textfield',
									name:'staffLeaveapply.companyName',
									id:'companyName',
									readOnly:true,
									value:__companyName,
									width:567
						}
					]
				},{
				  xtype:'hidden',
				  name:'staffLeaveapply.id',
				  value:this.id,
				  id:'id'
				 },{
				   xtype:'hidden',
				   name:'staffLeaveapply.processRunId',
				   value:-1
				},
				{
					xtype : "panel",
					layout : "column",
					border : false,
					style : "padding-top:5px;",
					height : 30,
					items : [{
								xtype : "label",
								text : "姓名:",
								height : 30,
								width : 90
							}, {
								xtype : "textfield",
								name : "staffLeaveapply.applyName",
								id:'applyName',
								allowBlank:false,
								readOnly:true,
								value:__currentUser
							},{
								xtype : "label",
								style : "margin-left:5px;",
								text : "性别:",
								height : 30,
								width : 30
							}, {
								xtype : "combo",
								hiddenName : "staffLeaveapply.sex",
								id:'sex',
								displayField : 'label',
								valueField : 'value',
								store:new Ext.data.SimpleStore({
											fields : ['value', 'label'],
											data : [['男','男'],['女','女']]
										 }),
								mode : 'local',
								allowBlank:false,
								triggerAction : 'all',
								value:__currentUserSex==0?'女':'男',
								width : 60
							}, {
								xtype : "label",
								text : "部门:",
								style : "padding-left:5px;",
								height : 30
							}, {
								xtype : "textfield",
								name : "staffLeaveapply.deptName",
								id:'deptName',
								value:__currentUserDept,
								allowBlank:false
							}, {
								xtype : "label",
								text : "职位:",
								style : "padding-left:5px;",
								height : 30
							}, {
								xtype : "textfield",
								id:'workPosition',
								name : "staffLeaveapply.workPosition",
								width:160,
								value:__currentUserPosition=='null'?'':__currentUserPosition,
								allowBlank:false
							}]
				}
				,{
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'padding-top:5px;',
					height : 40,
					items : [{
								xtype : "label",
								text : "进公司日期:",
								width : 90
							}, {
								xtype : "textfield",
								name : "staffLeaveapply.comeInDate",
								id:'comeInDate',
//								format:'Y-m-d',
								width : 130,
								allowBlank:false,
								readOnly:true,
//								editable:false,
								value:Ext.util.Format.substr(__currentUserAccessionTime,0,10)
							},{
								xtype : "label",
								text : "离职申请日期:",
								style : "margin-left:5px;",
								width : 90
							}, {
								xtype : "datefield",
								name : "staffLeaveapply.applyDate",
								format:'Y-m-d',
								editable:false,
								value:new Date(),
								width : 120,
								listeners:{
									select:function(){
//										Ext.getCmp('signDate').setText(new Date(this.value).format("Y年m月d日"));
									}
								}
							}, {
								xtype : "label",
								text : "正式离职日期:",
								width : 90,
								style : "padding-left:3px;"
							}, {
								xtype : "datefield",
								id:"leaveDate",
								name : "staffLeaveapply.leaveDate",
								editable:false,
								allowBlank:false,
								format:'Y-m-d',
								width : 135
							}]
				}, {
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'padding-top:5px;',
					height : 80,
					items : [ {
								xtype : "label",
								text : "离职原因:",
								width : 90
							}, {
								xtype : "textarea",
								id:'leaveReason',
								name : "staffLeaveapply.leaveReason",
								allowBlank:false,
								width:560,
								height:60
							}]
				},{
				    xtype : "panel",
					layout : "form",
					border : true,
					unstyled : false,
					style:'padding-top:5px;',
					height : 60,
					items : [
							{
								xtype : "label",
								text : "本人意见：本人提出离职并承诺在任职期间如有违法的事宜承担相关的法律责任；同意不再享受年终效益工资（含业绩考核奖金）。不以公司原来印制名片行使各类活动，一经发现公司将通过法律手段追究责任。",
								width : '100%'
							}]
							},
//				{
//					xtype : "panel",
//					layout : "form",
//					border : true,
//					unstyled : false,
//					style:'padding-top:5px;',
//					height : 60,
//					items : [
//							{
//								xtype : "label",
//								text : "本人意见：本人提出离职并承诺在任职期间如有违法的事宜承担相关的法律责任；同意不再享受年终效益工资（含业绩考核奖金）。不以公司原来印制名片行使各类活动，一经发现公司将通过法律手段追究责任。",
//								width :568
//							},{
//								xtype:'panel',
//								layout:'column',
//								border:false,
//								style:'padding-top:5px',
//								items:[
//									{
//										xtype:'label',
//										text:'签名:',
//										style:'margin-left:180px'
//									},{
//										xtype:'label',
//										id:'signName',
//										text:__currentUser,
//										style:'margin-left:10px;color: #036; text-decoration: none; border-bottom: 1px solid #F90;'
//									},{
//										xtype:'label',
//										id:'signDate',
//										style:'margin-left:80px;'
//									}
//								]
//							}
//							]
//				}
				{
					xtype : "panel",
					layout : "column",
					border : false,
					unstyled : false,
					style:'padding-top:5px;',
					height : 25,
					items : [ {
								xtype : "label",
								text : "是否同意以上说明:",
								width : 120
							}, {
								xtype : "radiogroup",
								name : "staffLeaveapply.isAgree",
								id:'isAgree',
								allowBlank:false,
								width:200,
								items:[
									{
										name:'staffLeaveapply.isAgree',
										boxLabel:'同意',
										inputValue:'同意',
										checked : true
									},{
										name:'staffLeaveapply.isAgree',
										boxLabel:'不同意',
										inputValue:'不同意'
									}
								],
								listeners:{
									change : function(field,newValue,oldValue){
										var isAgree = newValue.getRawValue();
									}
								}
							}]
				}
				]
			});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getStaffLeaveapply.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON.decode(action.response.responseText);
					var res = jsonData.data;
//					if(res.signDate!=''&&typeof(res.signDate)!='undefined'){
//						Ext.getCmp("signDate").setText(new Date(res.signDate).format("Y年m月d日"));
//						Ext.getCmp("signDate").originalValue =new Date(res.signDate).format("Y年m月d日");
//					}
//					    Ext.getCmp("signName").setText(jsonData.data.signName);
//					    Ext.getCmp("signDate").originalValue =jsonData.data.signName;
				},
				failure : function(form, action) {
				}
			});
		}
		//初始化功能按钮
		this.buttons=[
				{
				text : '保存',
				iconCls : 'btn-save',
				handler :this.save.createCallback(this.formPanel,this)
			}, {
				text : '重置',
				iconCls : 'btn-reset',
				handler :this.reset.createCallback(this.formPanel)
			},
				{
				text : '关闭',
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
					var gridPanel=Ext.getCmp('StaffLeaveapplyGrid');
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