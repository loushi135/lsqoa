TrainPlanSendMsgForm= Ext.extend(Ext.Window, {
	//array存储参与会议人员
	array : null,
	courseName : null,
	coursePlace : null,
	courseTime : null,
	// 内嵌FormPanel
	formPanel : null,
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		TrainPlanSendMsgForm.superclass.constructor.call(this, {
			id : 'TrainPlanSendMsgFormWin',
			layout : 'border',
			items : this.formPanel,
			modal : true,
			height : 400,
			width : 400,
			maximizable : true,
			title : '详细信息',
			buttonAlign : 'center',
			buttons : this.buttons
		});
	},
	initUIComponents : function() {
		var thiz = this;
		this.formPanel = new Ext.Panel({
			layout : 'border',
			region : 'center',
			trackResetOnLoad : true,
			border : false,
			id : 'TrainPlanSendMsgForm',
			items : [
				{
					region : 'center',
					xtype : 'panel',
					id : 'sendMsgUser',
					frame : true,
					autoScroll : true,
					html : ''
				},
				{
					xtype : 'container',
					layout : 'border',
					height : 65,
					region : 'south',
					items : [
						{
							xtype : 'label',
							region : 'west',
							text : '附加内容:'
						},
						{
							id : 'psContext',
							region : 'center',
							xtype : 'textarea'
						}
					]
				}
			]
		})
		this.buttons = [{
					text : '发送短信',
					iconCls : 'btn-save',
					id : 'saveBtn',
					handler : this.save.createCallback(this)
				}, {
					text : '取消',
					iconCls : 'btn-cancel',
					handler : this.cancel.createCallback(this)
				}];
		if(!Ext.isEmpty(this.record)){
			thiz.courseName = this.record.data.trainCourse.courseName;
			thiz.coursePlace = this.record.data.trainPlanPlace;
			thiz.courseTime = this.record.data.trainTime;
			Ext.Ajax.request({
				url : __ctxPath + '/hrm/queryRegistUserTrainUser.do',
				params : {
					'trainPlanId' : this.record.data.id
				},
				success : function(response, options) {
					var result = (Ext.util.JSON.decode(response.responseText)).result;
					if (!Ext.isEmpty(result)) {
						if(Ext.isEmpty(thiz.array)){
							thiz.array = new Array();
						}else{
							thiz.array = [];
						}
					}
					Ext.DomHelper.overwrite(Ext.getCmp("sendMsgUser").body, '');
					var sumPeople = 0;
					var deptArray = new Array();
					for (var i = 0; i < result.length; i++) {
						var data = result[i];
						var appUser = data.appUser;
						thiz.array.push(appUser.userId);
						if(deptArray.indexOf(appUser.department.depName) == -1){
							deptArray.push(appUser.department.depName);
						}
					}
					for(var i = 0; i < deptArray.length; i++){
						var attendUser = '';
						for (var j = 0; j < result.length; j++) {
							var data = result[j];
							var appUser = data.appUser;
							if (data.attend == "0") {//已出席人员
								if(appUser.department.depName == deptArray[i]){
									sumPeople += 1;
									attendUser += "[" + appUser.fullname + "] ";
								}
							}
						}
						Ext.DomHelper.append(Ext.getCmp("sendMsgUser").body, '<div><span style="display:-moz-inline-box;display:inline-block;width:80px;"><font color="blue">['+deptArray[i]+']:</font></span>'+'<span>'+attendUser+'</span>'+'</div>');
					}
					Ext.DomHelper.insertFirst(Ext.getCmp("sendMsgUser").body, '<div><span>总共 ['+sumPeople+'] 人参加，人员如下：</span>'+'</div>');
				},
				failure : function() {
					Ext.MessageBox.show({
						title : '操作信息',
						msg : '信息保存出错，请联系管理员！',
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
					window.close();
				}
			})
		}
	},
	/**
	 * 取消
	 * 
	 * @param {}
	 *            window
	 */
	cancel : function(window) {
		window.close();
	},
	/**
	 * 保存记录
	 */
	save : function(window) {
		Ext.Ajax.request({
			url : __ctxPath + '/hrm/sendMsgTrainPlan.do',
			params : {
					'userIds' : window.array,
					'courseName' : window.courseName,
					'coursePlace' : window.coursePlace,
					'courseTime' : window.courseTime,
					'psContext' : Ext.isEmpty(Ext.getCmp("psContext").getValue())?'':Ext.getCmp("psContext").getValue()
				},
			success : function(response, options) {
				Ext.ux.Toast.msg('操作信息', '成功保存信息！');
				window.close();
			},
			failure : function() {
				Ext.MessageBox.show({
					title : '操作信息',
					msg : '信息保存出错，请联系管理员！',
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.ERROR
				});
				window.close();
			}
		})
	}
})