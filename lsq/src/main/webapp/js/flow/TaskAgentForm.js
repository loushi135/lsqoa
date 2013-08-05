/**
 * @author 
 * @createtime 
 * @class TaskAgentForm
 * @extends Ext.Window
 * @description TaskAgent表单
 */
TaskAgentForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		TaskAgentForm.superclass.constructor.call(this,{
			id:'TaskAgentFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			height:250,
			width:400,
			title:'任务代办规则详细信息',
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
				frame:true,
				url : __ctxPath + '/flow/saveTaskAgent.do',
				id : 'TaskAgentForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'taskAgent.id',
							id : 'id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						},
						{
							name : 'taskAgent.assignId',
							id : 'assignId',
							xtype:'hidden'
						},
						{
							name : 'taskAgent.agentAssignId',
							id : 'agentAssignId',
							xtype:'hidden'
						}
						,{
							fieldLabel : '原任务办理人',	
							name : 'taskAgent.assignName',
							id : 'assignName',
							allowBlank : false,
							editable : false,
							readOnly : true,
							listeners:{
			    				'focus' : function() {
					    			Ext.getCmp('assignId').setValue('');
				    				Ext.getCmp('assignName').setValue('');
			    					 UserSelector.getView(
			        						 function(id,name){
			        							  Ext.getCmp('assignId').setValue(id);
			        						      Ext.getCmp('assignName').setValue(name);
			        						  },false   
			        					 ).show();
			    					}
			    			}
						}
						,{
							fieldLabel : '任务代理办理人',	
							name : 'taskAgent.agentAssignName',
							id : 'agentAssignName',
							allowBlank : false,
							editable : false,
							readOnly : true,
							listeners:{
			    				'focus' : function() {
					    			Ext.getCmp('agentAssignId').setValue('');
				    				Ext.getCmp('agentAssignName').setValue('');
			    					 UserSelector.getView(
			        						 function(id,name){
			        							  Ext.getCmp('agentAssignId').setValue(id);
			        						      Ext.getCmp('agentAssignName').setValue(name);
			        						  },false   
			        					 ).show();
			    					}
			    			}
						},
						{
							fieldLabel : '是否生效',
							hiddenName : 'taskAgent.status',
							id : 'status',									
							editable : false,
							xtype : 'combo',
							mode : 'local',
							allowBlank : false,
							triggerAction : 'all',
							store : [['0','失效'],['1','有效']]
						}
						,{
							fieldLabel : '备注',	
							name : 'taskAgent.remark',
							xtype:'textarea',
							id : 'remark',
							height:70
						}]
			});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/flow/getTaskAgent.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
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
					var gridPanel=Ext.getCmp('TaskAgentGrid');
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