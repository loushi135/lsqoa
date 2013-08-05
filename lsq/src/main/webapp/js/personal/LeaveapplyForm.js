/**
 * @author juql
 * @createtime 
 * @class LeaveapplyForm
 * @extends Ext.Window
 * @description 请假申请表单
 */
LeaveapplyForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		LeaveapplyForm.superclass.constructor.call(this,{
			id:'LeaveapplyFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			height:350,
			width:480,
			title:'请假申请详细信息',
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
				frame:true,
				url : __ctxPath + '/personal/saveLeaveapply.do',
				id : 'LeaveapplyForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{
			xtype : 'panel',
    		title:'',
    		frame:false,
    		layout:'column',
			border:false,
    		height:30,
			items:[
			{
    			xtype:'hidden',
    			name : 'leaveapply.applyTime',
    			id : 'applyTime',
	    		value:formatDate(new Date(),'yyyy-MM-dd')
    		},{
    		    xtype:'hidden',
    			name : 'leaveapply.id',
    			id : 'id',
	    		value:this.id
    		},{
    		    xtype:'hidden',
    			name : 'leaveapply.processRunId',
	    		value:-1
    		},{
    			xtype:'hidden',
    			name : 'userId',
    			id : 'userId',
	    		value:__currentUserId
    		},{
    			xtype:'hidden',
    			name : 'deptId',
    			id : 'deptId',
	    		value:__currentUserDeptId
    		},{
    			xtype:'label',
    			style:'padding-left:0px;margin-left:0px;',
    			text:'申请人:',
    			width:70
    		},{
    			id:'userName',
    			xtype:'textfield',
    			name:'leaveapply.userName',
    			editable:false,
    			style:'margin-left:-10px;',
    			readOnly:true,
    			style:"background:#F0F0F0;",
    			allowBlank:false,
    			width:130,
    			value:__currentUser
    		},{
    			xtype:'label',
    			style:'padding-left:0px;margin-left:10px;',
    			text:'部门:',
    			width:70
    		},{
    			id:'deptName',
    			xtype:'textfield',
    			name:'leaveapply.deptName',
    			editable:false,
    			style:'margin-left:-30px;',
    			readOnly:true,
    			style:"background:#F0F0F0;",
    			allowBlank:false,
    			width:130,
    			value:__currentUserDept
    		}]
		},{
			xtype : 'panel',
    		title:'',
    		frame:false,
    		layout:'column',
			border:false,
    		height:30,
			items:[{
    			xtype:'label',
    			style:'padding-left:0px;',
    			text:'起始日期:',
    			width:70
    		},{
    			id:'startTime',
    			xtype:'datefield',
    			name:'leaveapply.startTime', 
    			allowBlank:false,
    			width:130,
				emptyText : '---请选择日期---',
				format : 'Y-m-d',				
				editable : false,
				showToday: true,
				value:new Date(),
				maxValue:formatDate(new Date(new Date().valueOf()+60* 24*60*60*1000),'yyyy-MM-dd'),//允许选择的最大日期
				listeners : {
					select : function(combo, record, index) {
						var flow_startTimeValue = Ext.getCmp('startTime').getValue();
						var flow_startTimeAmOrPmValue = Ext.getCmp('startAmOrPm').getValue();
						var flow_endTimeValue = Ext.getCmp('endTime').getValue();
						var flow_endTimeAmOrPmValue = Ext.getCmp('endAmOrPm').getValue();
						if(flow_startTimeAmOrPmValue == '' || flow_endTimeAmOrPmValue == '' ){
							return;
						} else {
							var time=(flow_endTimeValue.getTime()-flow_startTimeValue.getTime())/3600/1000/24+1
						    if(flow_startTimeAmOrPmValue == '下午' && flow_endTimeAmOrPmValue == '上午'){
						    	Ext.getCmp('totalDays').setValue(time-1);
						    } else if(flow_startTimeAmOrPmValue == '下午' && flow_endTimeAmOrPmValue == '下午'){
						    	Ext.getCmp('totalDays').setValue(time-0.5);
						    } else if(flow_startTimeAmOrPmValue == '上午' && flow_endTimeAmOrPmValue == '上午'){
						    	Ext.getCmp('totalDays').setValue(time-0.5);
						    } else{
						    	Ext.getCmp('totalDays').setValue(time);
						    }
					    }
//					    var comboTypeValue = Ext.getCmp('type').getValue();
//					    if(comboTypeValue == '年休假'){
//					    	Ext.getCmp('startFlowBtn').setDisabled(false);
//					    	Ext.Ajax.request({
//								url:__ctxPath+'/personal/checkHolidayAnnualleave.do',								
//								method:'POST',
//								success:function(response,options){
//									var result = Ext.util.JSON.decode(response.responseText);
//									Ext.getCmp('remainDays').setValue(result.totalHolidays);
//									var applyDays=Ext.getCmp('totalDays').getValue();
//									if(applyDays!=''&&applyDays>result.totalHolidays){
//										Ext.getCmp('startFlowBtn').setDisabled(true);
//										Ext.ux.Toast.msg('操作信息','您申请的年休假超过了可用年休假！');
//									}
//								},
//								failure:function(response,options){
//									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
//								}
//							});
//					    }else if(comboTypeValue == '调休'){
//					    	Ext.getCmp('startFlowBtn').setDisabled(false);
//					    	Ext.Ajax.request({
//								url:__ctxPath+'/personal/checkHolidayAnnualleave.do',								
//								method:'POST',
//								success:function(response,options){
//									var result = Ext.util.JSON.decode(response.responseText);
//									Ext.getCmp('remainHours').setValue(result.remainHours);
//									var applyDays=Ext.getCmp('totalDays').getValue();
//									var applyHours=applyDays*8;
//									if(applyHours>result.remainHours){
//										Ext.getCmp('startFlowBtn').setDisabled(true);
//										Ext.ux.Toast.msg('操作信息','您申请的调休时间超过了可用调休假！');
//									}
//								},
//								failure:function(response,options){
//									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
//								}
//							});
//					    }else{
//							Ext.getCmp('startFlowBtn').setDisabled(false);
//					    }
					}
				}
    		},{
				xtype : 'combo',
				width : 130,
				id : 'startAmOrPm',
				name : 'leaveapply.startAmOrPm',
				emptyText : '---请选择---',
				selectOnFocus : true,
				forceSelection : true,
				triggerAction : 'all',
				editable : false,
				store:[['上午', '上午'], ['下午', '下午']],
				allowBlank:false,
				listeners : {
					select : function(combo, record, index) {
						var flow_startTimeValue = Ext.getCmp('startTime').getValue();
						var flow_startTimeAmOrPmValue = Ext.getCmp('startAmOrPm').getValue();
						var flow_endTimeValue = Ext.getCmp('endTime').getValue();
						var flow_endTimeAmOrPmValue = Ext.getCmp('endAmOrPm').getValue();
						if(flow_startTimeAmOrPmValue == '' || flow_endTimeAmOrPmValue == '' ){
							return;
						} else {
							var time=(flow_endTimeValue.getTime()-flow_startTimeValue.getTime())/3600/1000/24+1
						    if(flow_startTimeAmOrPmValue == '下午' && flow_endTimeAmOrPmValue == '上午'){
						    	Ext.getCmp('totalDays').setValue(time-1);
						    } else if(flow_startTimeAmOrPmValue == '下午' && flow_endTimeAmOrPmValue == '下午'){
						    	Ext.getCmp('totalDays').setValue(time-0.5);
						    } else if(flow_startTimeAmOrPmValue == '上午' && flow_endTimeAmOrPmValue == '上午'){
						    	Ext.getCmp('totalDays').setValue(time-0.5);
						    } else{
						    	Ext.getCmp('totalDays').setValue(time);
						    }
					    }
//					    var comboTypeValue = Ext.getCmp('type').getValue();
//					    if(comboTypeValue == '年休假'){
//					    	Ext.getCmp('startFlowBtn').setDisabled(false);
//					    	Ext.Ajax.request({
//								url:__ctxPath+'/personal/checkHolidayAnnualleave.do',								
//								method:'POST',
//								success:function(response,options){
//									var result = Ext.util.JSON.decode(response.responseText);
//									Ext.getCmp('remainDays').setValue(result.totalHolidays);
//									var applyDays=Ext.getCmp('totalDays').getValue();
//									if(applyDays!=''&&applyDays>result.totalHolidays){
//										Ext.getCmp('startFlowBtn').setDisabled(true);
//										Ext.ux.Toast.msg('操作信息','您申请的年休假超过了可用年休假！');
//									}
//								},
//								failure:function(response,options){
//									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
//								}
//							});
//					    }else if(comboTypeValue == '调休'){
//					    	Ext.getCmp('startFlowBtn').setDisabled(false);
//					    	Ext.Ajax.request({
//								url:__ctxPath+'/personal/checkHolidayAnnualleave.do',								
//								method:'POST',
//								success:function(response,options){
//									var result = Ext.util.JSON.decode(response.responseText);
//									Ext.getCmp('remainHours').setValue(result.remainHours);
//									var applyDays=Ext.getCmp('totalDays').getValue();
//									var applyHours=applyDays*8;
//									if(applyHours>result.remainHours){
//										Ext.getCmp('startFlowBtn').setDisabled(true);
//										Ext.ux.Toast.msg('操作信息','您申请的调休时间超过了可用调休假！');
//									}
//								},
//								failure:function(response,options){
//									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
//								}
//							});
//					    }else{
//							Ext.getCmp('startFlowBtn').setDisabled(false);
//					    }
					}
				}
			}]
		},{
			xtype : 'panel',
    		title:'',
    		frame:false,
    		layout:'column',
			border:false,
    		height:30,
			items:[{
    			xtype:'label',
    			style:'padding-left:0px;',
    			text:'结束日期:',
    			width:70
    		},{
    			id:'endTime',
    			xtype:'datefield',
    			name:'leaveapply.endTime', 
    			allowBlank:false,
    			width:130,
				emptyText : '---请选择日期---',
				format : 'Y-m-d',				
				editable : false,
				showToday: true,
				value:new Date(),
				maxValue:formatDate(new Date(new Date().valueOf()+150* 24*60*60*1000),'yyyy-MM-dd'),//允许选择的最大日期
				listeners : {
					select : function(combo, record, index) {
						var flow_startTimeValue = Ext.getCmp('startTime').getValue();
						var flow_startTimeAmOrPmValue = Ext.getCmp('startAmOrPm').getValue();
						var flow_endTimeValue = Ext.getCmp('endTime').getValue();
						var flow_endTimeAmOrPmValue = Ext.getCmp('endAmOrPm').getValue();
						if(flow_startTimeAmOrPmValue == '' || flow_endTimeAmOrPmValue == '' ){
							return;
						} else {
							var time=(flow_endTimeValue.getTime()-flow_startTimeValue.getTime())/3600/1000/24+1
						    if(flow_startTimeAmOrPmValue == '下午' && flow_endTimeAmOrPmValue == '上午'){
						    	Ext.getCmp('totalDays').setValue(time-1);
						    } else if(flow_startTimeAmOrPmValue == '下午' && flow_endTimeAmOrPmValue == '下午'){
						    	Ext.getCmp('totalDays').setValue(time-0.5);
						    } else if(flow_startTimeAmOrPmValue == '上午' && flow_endTimeAmOrPmValue == '上午'){
						    	Ext.getCmp('totalDays').setValue(time-0.5);
						    } else{
						    	Ext.getCmp('totalDays').setValue(time);
						    }
					    }
//					    var comboTypeValue = Ext.getCmp('type').getValue();
//					    if(comboTypeValue == '年休假'){
//					    	Ext.getCmp('startFlowBtn').setDisabled(false);
//					    	Ext.Ajax.request({
//								url:__ctxPath+'/personal/checkHolidayAnnualleave.do',								
//								method:'POST',
//								success:function(response,options){
//									var result = Ext.util.JSON.decode(response.responseText);
//									Ext.getCmp('remainDays').setValue(result.totalHolidays);
//									var applyDays=Ext.getCmp('totalDays').getValue();
//									if(applyDays!=''&&applyDays>result.totalHolidays){
//										Ext.getCmp('startFlowBtn').setDisabled(true);
//										Ext.ux.Toast.msg('操作信息','您申请的年休假超过了可用年休假！');
//									}
//								},
//								failure:function(response,options){
//									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
//								}
//							});
//					    }else if(comboTypeValue == '调休'){
//					    	Ext.getCmp('startFlowBtn').setDisabled(false);
//					    	Ext.Ajax.request({
//								url:__ctxPath+'/personal/checkHolidayAnnualleave.do',								
//								method:'POST',
//								success:function(response,options){
//									var result = Ext.util.JSON.decode(response.responseText);
//									Ext.getCmp('remainHours').setValue(result.remainHours);
//									var applyDays=Ext.getCmp('totalDays').getValue();
//									var applyHours=applyDays*8;
//									if(applyHours>result.remainHours){
//										Ext.getCmp('startFlowBtn').setDisabled(true)
//										Ext.ux.Toast.msg('操作信息','您申请的调休时间超过了可用调休假！');
//									}
//								},
//								failure:function(response,options){
//									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
//								}
//							});
//					    }else{
//							Ext.getCmp('startFlowBtn').setDisabled(false);
//					    }
					}
				}
    		},{
				xtype : 'combo',
				width : 130,
				id : 'endAmOrPm',
				name : 'leaveapply.endAmOrPm',
				emptyText : '---请选择---',
				selectOnFocus : true,
				forceSelection : true,
				triggerAction : 'all',
				editable : false,
				style:'margin-left:0px;',
				store:[['上午', '上午'], ['下午', '下午']],
				allowBlank:false,
				listeners : {
					select : function(combo, record, index) {
						var flow_startTimeValue = Ext.getCmp('startTime').getValue();
						var flow_startTimeAmOrPmValue = Ext.getCmp('startAmOrPm').getValue();
						var flow_endTimeValue = Ext.getCmp('endTime').getValue();
						var flow_endTimeAmOrPmValue = Ext.getCmp('endAmOrPm').getValue();
						if(flow_startTimeAmOrPmValue == '' || flow_endTimeAmOrPmValue == '' ){
							return;
						} else {
							var time=(flow_endTimeValue.getTime()-flow_startTimeValue.getTime())/3600/1000/24+1
						    if(flow_startTimeAmOrPmValue == '下午' && flow_endTimeAmOrPmValue == '上午'){
						    	Ext.getCmp('totalDays').setValue(time-1);
						    } else if(flow_startTimeAmOrPmValue == '下午' && flow_endTimeAmOrPmValue == '下午'){
						    	Ext.getCmp('totalDays').setValue(time-0.5);
						    } else if(flow_startTimeAmOrPmValue == '上午' && flow_endTimeAmOrPmValue == '上午'){
						    	Ext.getCmp('totalDays').setValue(time-0.5);
						    } else{
						    	Ext.getCmp('totalDays').setValue(time);
						    }
					    }
//					    var comboTypeValue = Ext.getCmp('type').getValue();
//					    if(comboTypeValue == '年休假'){
//					    	Ext.getCmp('startFlowBtn').setDisabled(false);
//					    	Ext.Ajax.request({
//								url:__ctxPath+'/personal/checkHolidayAnnualleave.do',								
//								method:'POST',
//								success:function(response,options){
//									var result = Ext.util.JSON.decode(response.responseText);
//									Ext.getCmp('remainDays').setValue(result.totalHolidays);
//									var applyDays=Ext.getCmp('totalDays').getValue();
//									if(applyDays!=''&&applyDays>result.totalHolidays){
//										Ext.getCmp('startFlowBtn').setDisabled(true);
//										Ext.ux.Toast.msg('操作信息','您申请的年休假超过了可用年休假！');
//									}
//								},
//								failure:function(response,options){
//									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
//								}
//							});
//					    }else if(comboTypeValue == '调休'){
//					    	Ext.getCmp('startFlowBtn').setDisabled(false);
//					    	Ext.Ajax.request({
//								url:__ctxPath+'/personal/checkHolidayAnnualleave.do',								
//								method:'POST',
//								success:function(response,options){
//									var result = Ext.util.JSON.decode(response.responseText);
//									Ext.getCmp('remainHours').setValue(result.remainHours);
//									var applyDays=Ext.getCmp('totalDays').getValue();
//									var applyHours=applyDays*8;
//									if(applyHours>result.remainHours){
//										Ext.getCmp('startFlowBtn').setDisabled(true);
//										Ext.ux.Toast.msg('操作信息','您申请的调休时间超过了可用调休假！');
//									}
//								},
//								failure:function(response,options){
//									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
//								}
//							});
//					    }else{
//							Ext.getCmp('startFlowBtn').setDisabled(false);
//					    }
					}
				}
			},{
	    			xtype:'label',
	    			style:'padding-left:0px;',
	    			text:'共计:',
	    			width:40
	    		},{
					xtype : 'textfield',
					width : 30,
					id : 'totalDays',
					name : 'leaveapply.totalDays',
					style:'margin-left:0px;',
					//value: this.totalDays == null ? '' : '${totalDays}',
					readOnly : true,
					allowBlank:false
				},{
	    			xtype:'label',
	    			style:'padding-left:5px;',
	    			text:'天',
	    			width:20
	    	}]
		},{
			xtype : 'panel',
			border:false,
    		layout:'column',
			items:[{
    			xtype:'label',
    			style:'padding-left:0px;',
    			text:'假期类别:',
    			width:70
    		},{
				xtype : 'combo',
				width : 130,
				id : 'type',
				editable:false,
				name : 'leaveapply.type',
				emptyText : '---请选择---',
				selectOnFocus : true,
				forceSelection : true,
				triggerAction : 'all',
				style:'margin-left:0px;',
				store:[['年休假', '年休假'], ['事假', '事假'], ['调休', '调休'], ['病假', '病假'], ['婚假', '婚假'], ['产假', '产假'],['陪产假', '陪产假'], ['丧假', '丧假'], ['产检假', '产检假'], ['哺乳假', '哺乳假']],
				allowBlank:false
//				listeners : {
//					select : function(combo, record, index) {
//						var typeValue = Ext.getCmp('type').getValue();
//						//if(typeValue == '其他'){
//							//Ext.getCmp('other').setDisabled(false);
//						//} else {
//							//Ext.getCmp('other').setValue('');
//							//Ext.getCmp('other').setDisabled(true);
//						//}
//						if(combo.value=='年休假'){
//							Ext.getCmp('startFlowBtn').setDisabled(false);
//							Ext.Ajax.request({
//								url:__ctxPath+'/personal/checkHolidayAnnualleave.do',								
//								method:'POST',
//								success:function(response,options){
//									var result = Ext.util.JSON.decode(response.responseText);
//									Ext.getCmp('remainDays').setValue(result.totalHolidays);
//									var applyDays=Ext.getCmp('totalDays').getValue();
//									if(applyDays!=''&&applyDays>result.totalHolidays){
//										Ext.getCmp('startFlowBtn').setDisabled(true);
//										Ext.ux.Toast.msg('操作信息','您申请的年休假超过了可用年休假！');
//									}
//								},
//								failure:function(response,options){
//									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
//								}
//							});
//						}else if (combo.value=='调休'){
//							Ext.Ajax.request({
//								url:__ctxPath+'/personal/checkHolidayAnnualleave.do',								
//								method:'POST',
//								success:function(response,options){
//									var result = Ext.util.JSON.decode(response.responseText);
//									Ext.getCmp('remainHours').setValue(result.remainHours);
//									var applyDays=Ext.getCmp('totalDays').getValue();
//									var applyHours=applyDays*8;
//									if(applyHours>result.remainHours){
//										Ext.getCmp('startFlowBtn').setDisabled(true);
//										Ext.ux.Toast.msg('操作信息','您申请的调休时间超过了可用调休假！');
//									}
//								},
//								failure:function(response,options){
//									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
//								}
//							});
//						}else if(combo.value=='事假'){
//							Ext.getCmp('startFlowBtn').setDisabled(true);
//							Ext.Ajax.request({
//								url:__ctxPath+'/personal/checkHolidayAnnualleave.do',								
//								method:'POST',
//								success:function(response,options){
//									var result = Ext.util.JSON.decode(response.responseText);
//									Ext.getCmp('remainDays').setValue(result.totalHolidays);
//									var applyDays=Ext.getCmp('totalDays').getValue();
//									if(result.totalHolidays > 0 || result.remainHours > 0){
//										Ext.getCmp('startFlowBtn').setDisabled(true);
//										Ext.MessageBox.confirm('请确认', '<span style="color:red;font-size:14px">您还有年假'+result.totalHolidays+'天，调休假'+result.remainHours+'小时，确定要使用事假?<span>', function(btn, text) {
//											if (btn == 'yes') {
//												Ext.getCmp('startFlowBtn').setDisabled(false);
//											}else{
//												Ext.getCmp('startFlowBtn').setDisabled(true);
//											}
//										})
//									}else{
//										Ext.getCmp('startFlowBtn').setDisabled(false);
//									}
//								},
//								failure:function(response,options){
//									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
//								}
//							});
//						}else{
//							Ext.getCmp('startFlowBtn').setDisabled(false);
//						}
//					}
//				}
			}]
		},{
			xtype : 'container',
			layout : 'column',
			style : 'padding-left:0px;padding-top:5px;',
			items : [
					{
						xtype:'label',
		    			style:'padding-left:0px;',
		    			text:'请假原因:',
		    			width:55
					},{
						xtype : 'textarea',
						width : 540,
						height:100,
						id : 'other',
						name : 'leaveapply.other',
						maxLength:300,
						style:'margin-left:2px;'
					}]
		}
//		,{
//			xtype : 'container',
//			layout : 'column',
//			id : 'infoContainer',
//			style : 'padding-left:0px;padding-top:5px;',
//			items : [{
//	    			xtype:'label',
//	    			style:'padding-left:0px;margin-top:5px;',
//	    			text:'可用年休假:',
//	    			width:70
//	    	},{
//					xtype : 'textfield',
//					width : 50,
//					id : 'remainDays',
//					name : 'remainDays',
//					style:'margin-left:0px;',
//					value: this.remainDays == null ? '' : '${remainDays}',
//					readOnly : true
//			},{
//	    			xtype:'label',
//	    			style:'padding-left:5px;margin-top:5px;',
//	    			text:'天',
//	    			width:20
//	    	},{
//	    			xtype:'label',
//	    			style:'padding-left:0px;margin-left:20px;margin-top:5px;',
//	    			text:'可用调休假:',
//	    			width:70
//	    	},{
//					xtype : 'textfield',
//					width : 50,
//					id : 'remainHours',
//					name : 'remainHours',
//					style:'margin-left:0px;',
//					value: this.totalHolidays == null ? '' : '${totalHolidays}',
//					readOnly : true
//			},{
//	    			xtype:'label',
//	    			style:'padding-left:5px;margin-top:5px;',
//	    			text:'小时',
//	    			width:40
//	    	}]
//		}
		]
			});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/personal/getLeaveapply.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
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
	 * 重置
	 * 
	 * @param {}
	 *            formPanel
	 */
	reset : function(formPanel) {
		formPanel.getForm().reset();
	},
	save : function(formPanel, window) {
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
						method : 'POST',
						waitMsg : '正在提交数据...',
						success : function(fp, action) {
							Ext.ux.Toast.msg('操作信息', '成功保存信息！');
							var gridPanel = Ext.getCmp('LeaveapplyGrid');
							if (gridPanel != null) {
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
	}// end of save

	
});