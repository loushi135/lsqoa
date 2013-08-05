
/**
 * 处理代办事项方式
 */
var DoTaskModeForm = function(userId){
	var formPanel = new Ext.FormPanel({
				url : __ctxPath+ '/system/saveDoModeUserFlowconfig.do',
				layout : 'form',
				id:'doTaskModeForm',
				frame : true,
				labelWidth:120,
				height : 220,
				defaults : {
					width : 300,
					anchor : '100%,100%'
				},
	        	defaultType : 'textfield',
				items : [{
					xtype:'checkbox',
					id:'isEmail',
					name:'isEmail',
					style:'margin-top:5px;',
					fieldLabel:'是否启用Email通知'
					
				},{
					xtype:'checkbox',
					id:'isMsg',
					name:'isMsg',
					style:'margin-top:5px;',
					fieldLabel:'是否启用短信审批'
					
				},{
					xtype:'checkbox',
					id:'isAgent',
					name:'isAgent',
					style:'margin-top:5px;',
					fieldLabel:'是否启用任务代办',
					listeners:{
						'check':function(chk){
							if(chk.checked){
								Ext.getCmp('agentName').setDisabled(false);
								Ext.getCmp('agentName').allowBlank=false;
							}else{
								Ext.getCmp('agentName').setDisabled(true);
								Ext.getCmp('agentName').allowBlank=true;
							}
						}
					}
					
				},{
					id:'agentName',
					name:'agentName',
					fieldLabel:'任务代办人姓名',
					disabled:true,
					listeners:{
	    				'focus' : function() {
			    			Ext.getCmp('agentName').setValue('');
	    					 UserSelector.getView(
	        						 function(id,name){
	        							  Ext.getCmp('agentId').setValue(id);
	        							  Ext.getCmp('agentName').setValue(name);
	        						  },false   
	        					 ).show();
	    					}
	    			}
				},{
					xtype:'hidden',
					id:'agentId'
				}]
			});
	
	Ext.Ajax.request({
		url:__ctxPath+'/system/getDoModeUserFlowconfig.do',
		method:'POST',
		success:function(response,options){
			//alert(isGranted('_SmsRePly'));
			if(!isGranted('_SmsRePly')){
				Ext.getCmp('isMsg').setDisabled(true);
			}
			var resultObj=Ext.util.JSON.decode(response.responseText);
			Ext.getCmp('isMsg').setValue(resultObj.isMsg);
			Ext.getCmp('isEmail').setValue(resultObj.isEmail);
		
			Ext.getCmp('isAgent').setValue(resultObj.isAgent);
			Ext.getCmp('agentId').setValue(resultObj.agentId);
			if(resultObj.agentName!=''){
				Ext.getCmp('agentName').setValue(resultObj.agentName);
			}else{
				Ext.getCmp('agentName').setValue('');
			}
		},
		failure:function(response,options){
			Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
		}
	});
	
	var doTaskModeWin = new Ext.Window({
		title:'任务通知处理方式',
		iconCls:'btn-mail_resend',
		width : 400,
		height : 300,
		modal: true,
		layout : 'anchor',
		bodyStyle : 'padding:5px;',
		buttonAlign : 'center',
		items:[formPanel],
		buttons : [{
					text : '保存',
					iconCls:'btn-save',
					handler : function() {
						var fp=Ext.getCmp('doTaskModeForm');
							if (fp.getForm().isValid()) {
								fp.getForm().submit({
									method: 'post',
									waitMsg : '正在提交数据...',
									success : function(fp,action) {
										Ext.ux.Toast.msg('操作信息', '操作成功！');
										doTaskModeWin.close();
									},
									failure : function(fp,action) {
										Ext.ux.Toast.msg('错误提示','操作出错，请联系管理员！');
										fp.getForm().reset();
									}
								});
							}
					}
				}, {
					text : '取消',
					iconCls:'btn-cancel',
					handler : function() {
						doTaskModeWin.close();
					}
				}]
			});
	doTaskModeWin.show();

}
