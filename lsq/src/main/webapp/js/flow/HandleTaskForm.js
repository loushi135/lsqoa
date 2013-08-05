/**
 * @author 
 * @createtime 
 * @class HandleTaskForm
 * @extends Ext.Window
 * @description HandleTask表单
 */
HandleTaskForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		HandleTaskForm.superclass.constructor.call(this,{
			id:'HandleTaskFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			height:200,
			width:400,
			maximizable:true,
			title:'[HandleTask]详细信息',
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
				url : __ctxPath + '/flow/saveHandleTask.do',
				id : 'HandleTaskForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'handleTask.id',
							id : 'id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						}
																																										,{
												fieldLabel : '',	
												name : 'handleTask.taskId',
						id : 'taskId'
							}
																																				,{
												fieldLabel : '',	
												name : 'handleTask.runId',
						id : 'runId'
							}
																																				,{
												fieldLabel : '',	
												name : 'handleTask.subject',
						id : 'subject'
							}
																																				,{
												fieldLabel : '',	
												name : 'handleTask.processrun_createtime',
						id : 'processrun_createtime'
							}
																																				,{
												fieldLabel : '',	
												name : 'handleTask.creater',
						id : 'creater'
							}
																																				,{
												fieldLabel : '',	
												name : 'handleTask.activityName',
						id : 'activityName'
							}
																																				,{
												fieldLabel : '',	
												name : 'handleTask.task_createtime',
						id : 'task_createtime'
							}
																																				,{
												fieldLabel : '',	
												name : 'handleTask.assignee',
						id : 'assignee'
							}
																																				,{
												fieldLabel : '',	
												name : 'handleTask.assigneeId',
						id : 'assigneeId'
							}
																																				,{
												fieldLabel : '',	
												name : 'handleTask.runStatus',
						id : 'runStatus'
							}
																																				,{
												fieldLabel : '',	
												name : 'handleTask.url',
						id : 'url'
							}
																																				,{
												fieldLabel : '',	
												name : 'handleTask.appsouce',
						id : 'appsouce'
							}
																								
												]
			});
		//加载表单对应的数据	
		this.initData();
		//初始化功能按钮
		this.buttons=[{
				text : '保存',
				iconCls : 'btn-save',
				hidden:!(isGranted("_HandleTaskAdd")||isGranted("_HandleTaskEdit")) ,
				handler :this.save.createCallback(this.formPanel,this)
			}, {
				text : '重置',
				iconCls : 'btn-reset',
				hidden:!(isGranted("_HandleTaskAdd")||isGranted("_HandleTaskEdit")) ,
				handler :this.reset.createCallback(this.formPanel)
			},{
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	/**
	 * 初始化数据
	 * @param {} formPanel
	 */
	initData:function(){
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/flow/getHandleTask.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
						var jsonData = Ext.util.JSON.decode(action.response.responseText);
						var res = jsonData.data;
						//Ext.getCmp("").originalValue=
				},
				failure : function(form, action) {
				}
			});
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
					var gridPanel=Ext.getCmp('HandleTaskGrid');
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
					//window.close();
				}
			});
		}
	}//end of save
	
});