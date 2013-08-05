/**
 * @author juql
 * @createtime 
 * @class LeaveapplyForm
 * @extends Ext.Window
 * @description 请假申请表单
 */
LeaveapplyFormEdit=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		LeaveapplyFormEdit.superclass.constructor.call(this,{
			id:'LeaveapplyFormEditWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			height:350,
			width:400,
			title:'请假申请详细信息',
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
				url : __ctxPath + '/personal/saveLeaveapply.do',
				id : 'LeaveapplyFormEdit',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'leaveapply.id',
							id : 'id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						},{
							fieldLabel : '',	
							name : 'leaveapply.userId',
							id : 'userId',
							xtype:'hidden',
							value : this.userId == null ? '' : this.userId
						},{
							fieldLabel : '',	
							name : 'leaveapply.deptId',
							id : 'deptId',
							xtype:'hidden',
							value : this.depId == null ? '' : this.depId
						},{
							fieldLabel : '',	
							name : 'leaveapply.deptName',
							id : 'deptName',
							xtype:'hidden',
							value : this.depName == null ? '' : this.depName
						},{
							fieldLabel : '员工姓名',	
							name : 'leaveapply.userName',
							id : 'userName',
							value : this.username == null ? '' : this.username
						},{
							fieldLabel : '开始时间',	
							name : 'leaveapply.startTime',
							id : 'startTime',
							xtype:'datefield',
							format:'Y-m-d',
			    			readOnly:true,
			    			allowBlank:false,
							emptyText : '请选择出车时间',
							editable : false,
							showToday: true,
							listeners:{
								'select':function(){
									var startTimeValue = Ext.getCmp('startTime').getValue();
									var startTimeAmOrPmValue = Ext.getCmp('startAmOrPm').getValue();
									var endTimeValue = Ext.getCmp('endTime').getValue();
									var endTimeAmOrPmValue = Ext.getCmp('endAmOrPm').getValue();
									if(startTimeAmOrPmValue == '' || endTimeAmOrPmValue == '' ){
										return;
									} else if(startTimeValue == '' || endTimeValue == '' ){
										return;
									} else {
										var time=(endTimeValue.getTime()-startTimeValue.getTime())/3600/1000/24+1
									    if(startTimeAmOrPmValue == '下午' && endTimeAmOrPmValue == '上午'){
									    	Ext.getCmp('totalDays').setValue(time-1);
									    } else if(startTimeAmOrPmValue == '下午' && endTimeAmOrPmValue == '下午'){
									    	Ext.getCmp('totalDays').setValue(time-0.5);
									    } else if(startTimeAmOrPmValue == '上午' && endTimeAmOrPmValue == '上午'){
									    	Ext.getCmp('totalDays').setValue(time-0.5);
									    } else{
									    	Ext.getCmp('totalDays').setValue(time);
									    }
								    }
								}
							}
						},{
							fieldLabel : '上/下午',	
							name : 'leaveapply.startAmOrPm',
							id : 'startAmOrPm',
							xtype : 'combo',
							emptyText : '---请选择---',
							selectOnFocus : true,
							forceSelection : true,
							triggerAction : 'all',
							editable : false,
							style:'margin-left:0px;',
							store:[['上午', '上午'], ['下午', '下午']],
							allowBlank:false,
							listeners:{
								'select':function(){
									var startTimeValue = Ext.getCmp('startTime').getValue();
									var startTimeAmOrPmValue = Ext.getCmp('startAmOrPm').getValue();
									var endTimeValue = Ext.getCmp('endTime').getValue();
									var endTimeAmOrPmValue = Ext.getCmp('endAmOrPm').getValue();
									if(startTimeAmOrPmValue == '' || endTimeAmOrPmValue == '' ){
										return;
									} else if(startTimeValue == '' || endTimeValue == '' ){
										return;
									} else {
										var time=(endTimeValue.getTime()-startTimeValue.getTime())/3600/1000/24+1
									    if(startTimeAmOrPmValue == '下午' && endTimeAmOrPmValue == '上午'){
									    	Ext.getCmp('totalDays').setValue(time-1);
									    } else if(startTimeAmOrPmValue == '下午' && endTimeAmOrPmValue == '下午'){
									    	Ext.getCmp('totalDays').setValue(time-0.5);
									    } else if(startTimeAmOrPmValue == '上午' && endTimeAmOrPmValue == '上午'){
									    	Ext.getCmp('totalDays').setValue(time-0.5);
									    } else{
									    	Ext.getCmp('totalDays').setValue(time);
									    }
								    }
								}
							}
						},{
							fieldLabel : '结束时间',	
							name : 'leaveapply.endTime',
							id : 'endTime',
							xtype:'datefield',
							format:'Y-m-d',
			    			readOnly:true,
			    			allowBlank:false,
							emptyText : '请选择出车时间',
							editable : false,
							showToday: true,
							listeners:{
								'select':function(){
									var startTimeValue = Ext.getCmp('startTime').getValue();
									var startTimeAmOrPmValue = Ext.getCmp('startAmOrPm').getValue();
									var endTimeValue = Ext.getCmp('endTime').getValue();
									var endTimeAmOrPmValue = Ext.getCmp('endAmOrPm').getValue();
									if(startTimeAmOrPmValue == '' || endTimeAmOrPmValue == '' ){
										return;
									} else if(startTimeValue == '' || endTimeValue == '' ){
										return;
									} else {
										var time=(endTimeValue.getTime()-startTimeValue.getTime())/3600/1000/24+1
									    if(startTimeAmOrPmValue == '下午' && endTimeAmOrPmValue == '上午'){
									    	Ext.getCmp('totalDays').setValue(time-1);
									    } else if(startTimeAmOrPmValue == '下午' && endTimeAmOrPmValue == '下午'){
									    	Ext.getCmp('totalDays').setValue(time-0.5);
									    } else if(startTimeAmOrPmValue == '上午' && endTimeAmOrPmValue == '上午'){
									    	Ext.getCmp('totalDays').setValue(time-0.5);
									    } else{
									    	Ext.getCmp('totalDays').setValue(time);
									    }
								    }
								}
							}
						},{
							fieldLabel : '上/下午',	
							name : 'leaveapply.endAmOrPm',
							id : 'endAmOrPm',
							xtype : 'combo',
							emptyText : '---请选择---',
							selectOnFocus : true,
							forceSelection : true,
							triggerAction : 'all',
							editable : false,
							style:'margin-left:0px;',
							store:[['上午', '上午'], ['下午', '下午']],
							allowBlank:false,
							listeners:{
								'select':function(){
									var startTimeValue = Ext.getCmp('startTime').getValue();
									var startTimeAmOrPmValue = Ext.getCmp('startAmOrPm').getValue();
									var endTimeValue = Ext.getCmp('endTime').getValue();
									var endTimeAmOrPmValue = Ext.getCmp('endAmOrPm').getValue();
									if(startTimeAmOrPmValue == '' || endTimeAmOrPmValue == '' ){
										return;
									} else if(startTimeValue == '' || endTimeValue == '' ){
										return;
									} else {
										var time=(endTimeValue.getTime()-startTimeValue.getTime())/3600/1000/24+1
									    if(startTimeAmOrPmValue == '下午' && endTimeAmOrPmValue == '上午'){
									    	Ext.getCmp('totalDays').setValue(time-1);
									    } else if(startTimeAmOrPmValue == '下午' && endTimeAmOrPmValue == '下午'){
									    	Ext.getCmp('totalDays').setValue(time-0.5);
									    } else if(startTimeAmOrPmValue == '上午' && endTimeAmOrPmValue == '上午'){
									    	Ext.getCmp('totalDays').setValue(time-0.5);
									    } else{
									    	Ext.getCmp('totalDays').setValue(time);
									    }
								    }
								}
							}
						},{
							fieldLabel : '天数',	
							name : 'leaveapply.totalDays',
							id : 'totalDays',
							readOnly:true,
							editable:false
						},{
							fieldLabel : '假期类别',	
							name : 'leaveapply.type',
							id : 'type',
							xtype : 'combo',
							width : 130,
							emptyText : '---请选择---',
							selectOnFocus : true,
							forceSelection : true,
							triggerAction : 'all',
							style:'margin-left:0px;',
							store:[['年休假', '年休假'], ['事假', '事假'], ['调休', '调休'], ['病假', '病假'], ['婚假', '婚假'], ['产假', '产假'],['陪产假', '陪产假'], ['丧假', '丧假'], ['产检假', '产检假'], ['哺乳假', '哺乳假']],
							allowBlank:false
						},{
							fieldLabel : '其他假期类别',	
							name : 'leaveapply.other',
							id : 'other'
						},{
							fieldLabel : '',	
							name : 'leaveapply.applyUser',
							id : 'applyUser',
							xtype:'hidden'
						},{
							fieldLabel : '',	
							name : 'leaveapply.applyTime',
							id : 'applyTime',
							xtype:'hidden'
						}]
			});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/personal/getLeaveapply.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var startTime = action.result.data.startTime;
					Ext.getCmp('startTime').setValue(new Date(getDateFromFormat(startTime, "yyyy-MM-dd HH:mm:ss")));
					var endTime = action.result.data.endTime;
					Ext.getCmp('endTime').setValue(new Date(getDateFromFormat(endTime, "yyyy-MM-dd HH:mm:ss")));
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
			},{
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
					var gridPanel=Ext.getCmp('XzWagenoteQJDataGrid');
					var gridQJTotaldays = Ext.getCmp("ChangeDataQJGrid");
					if(gridPanel!=null){
						gridPanel.getStore().reload();
						gridQJTotaldays.getStore().reload();
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