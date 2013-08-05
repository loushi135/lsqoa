/**
 * @author
 * @createtime
 * @class TrainReportForm
 * @extends Ext.Window
 * @description TrainReport表单
 */
TrainReportForm = Ext.extend(Ext.Window, {
	//array存储参与会议人员
	array : null,
	// 内嵌FormPanel
	formPanel : null,
	replyContainer : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		TrainReportForm.superclass.constructor.call(this, {
			id : 'TrainReportFormWin',
			layout : 'form',
			autoScroll : true,
			items : [this.formPanel, this.replyContainer],
			modal : true,
			height : 500,
			width : 720,
			maximizable : true,
			title : '详细信息',
			buttonAlign : 'center',
			buttons : this.buttons
		});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		var thiz = this;
		var trainReportId = this.id;
		this.replyContainer = new Ext.Panel({
			layout : 'form',
			trackResetOnLoad : true,
			autoScroll : true,
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			frame : false,
			defaults : {
				anchor : '96%,96%'
			},
			items : []
		});
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			trackResetOnLoad : true,
			autoScroll : true,
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			url : __ctxPath + '/hrm/saveTrainReport.do',
			id : 'TrainReportForm',
			defaults : {
				anchor : '96%,96%'
			},
			defaultType : 'textfield',
			items : [{
				xtype : 'fieldset',
				title : '报告信息',
				collapsible : true,
				titleCollapse : true,
				defaults : {
					anchor : '96%,96%'
				},
				defaultType : 'textfield',
				layout : 'form',
				items : [{
						name : 'trainReport.id',
						id : 'id',
						xtype : 'hidden',
						value : Ext.isEmpty(this.id) ? '' : this.id
					}, {
						fieldLabel : '计划id',
						xtype : "hidden",
						name : 'trainReport.trainPlan.id',
						id : 'planId'
					}, {
						fieldLabel : '课程名称',
						cls : 'x-item-disabled',
						id : 'planCourseName',
						readOnly : true
					},{
						fieldLabel : '培训时间',
						cls : 'x-item-disabled',
						id : 'planCourseTime',
						readOnly : true
					},{
						fieldLabel : '培训地点',
						cls : 'x-item-disabled',
						id : 'planCoursePlace',
						readOnly : true
					},{
						fieldLabel : '培训讲师',
						cls : 'x-item-disabled',
						id : 'trainTeacher',
						readOnly : true
					},{
						fieldLabel : '培训目的',
						xtype : 'textarea',
						name : 'trainReport.trainReportTarget',
						cls : 'x-item-disabled',
						readOnly : true,
						id : 'trainReportTarget'
					},{
						fieldLabel : '培训议程',
						xtype : 'textarea',
						name : 'trainReport.trainReportAgenda',
						id : 'trainReportAgenda'
					},{
						xtype : 'panel',
						id : 'statisticsUser',
						style : 'margin-bottom:5px;',
						height : 80,
						frame : true,
						autoScroll : true,
						html : ''
					},{
						xtype : 'container',
						layout : 'column',
						anchor : '98%',
						items : [
							{
								xtype : 'button',
								text : '添加未报名人员',
								columnWidth : 0.5,
								hidden : !isGranted('_TrainReportEdit'),
								style : 'margin-bottom:5px;',
								listeners : {
									click : function() {
										var planId = Ext.getCmp("planId").getValue();
										if (!Ext.isEmpty(planId)) {
											thiz.addTrainUser(thiz,planId)
										} else {
											Ext.ux.Toast.msg('操作信息', '没有可操作人员！');
										}
									}
								}
							},
							{
								xtype : 'button',
								text : '删除报名人员',
								columnWidth : 0.5,
								hidden : !isGranted('_TrainReportEdit'),
								style : 'margin-bottom:5px;',
								listeners : {
									click : function() {
										var planId = Ext.getCmp("planId").getValue();
										if (!Ext.isEmpty(planId)) {
											thiz.editTrainUser(thiz.array, thiz, planId)
										} else {
											Ext.ux.Toast.msg('操作信息', '没有可操作人员！');
										}
									}
								}
							}
						]
					},{
						fieldLabel : '培训考核',
						xtype : 'textarea',
						name : 'trainReport.examine',
						id : 'examine'
					}, {
						fieldLabel : '培训总结',
						xtype : 'textarea',
						name : 'trainReport.remark',
						id : 'remark'
					}, {
						xtype : 'panel',
						id : 'registUser',
						style : 'margin-bottom:5px;',
						height : 65,
						frame : true,
						hidden : true,
						autoScroll : true,
						html : ''
					}, {
						xtype : 'panel',
						id : 'attendUser',
						style : 'margin-bottom:5px;',
						height : 65,
						frame : true,
						hidden : true,
						autoScroll : true,
						html : ''
					}, {
						xtype : 'container',
						autoHeight : true,
						layout : 'column',
						autoWidth : true,
						defaultType : 'label',
						style : 'padding-left:0px;padding-bottom:5px;',
						items : [{
								text : '培训资料:',
								width : 93
							}, {
								xtype : 'hidden',
								name : 'trainReportAttachIDs',
								id : 'trainReportAttachIDs'
							}, {
								xtype : 'panel',
								id : 'trainReportAttach',
								width : 447,
								height : 60,
								frame : true,
								autoScroll : true,
								html : ''
							}, {
								xtype : 'button',
								iconCls : 'btn-upload',
								text : '上传',
								hidden : !isGranted('_TrainReportEdit'),
								handler : function() {
									var dialog = App.createUploadDialog({
										file_cat : 'hrm/trainReport',
										callback : function(data) {
											var fileIds = Ext.getCmp('trainReportAttachIDs');
											var display = Ext.getCmp('trainReportAttach');
											for (var i = 0; i < data.length; i++) {
												if (fileIds.getValue() != '') {
													fileIds.setValue(fileIds.getValue() + ',');
												}
												fileIds.setValue(fileIds.getValue() + data[i].fileId);
												Ext.DomHelper.append(display.body, '<span><a href="#" onclick="FileAttachDetail.show(' + data[i].fileId + ')">' + data[i].filename.replace(/\s+/g, "") + '</a><img class="img-delete" src="' + __ctxPath + '/images/system/delete.gif" onclick="removeFile(this,'
												+ data[i].fileId + ')"/>&nbsp;|&nbsp;</span>');
											}
										},
										permitted_max_size : 1024 * 1024 * 50
									});
									dialog.show(this);
								}
							}]
					}]
			}]
		});
		var cm = new Ext.grid.ColumnModel({
			columns : [{
					width : 40,
					dataIndex : 'start',
					renderer : function(value, metadata, record, rowIndex, colIndex) {
						return rowIndex + 1 + '楼';
					}
				}, {
					dataIndex : 'commentId',
					hidden : true
				}, {
					width : 400,
					dataIndex : 'content',
					renderer : function(value, metadata, record, rowIndex, colIndex) {
						html = '<table width="100%"><tr>' + '<td width="80%"><font color="gray">评论人:' + record.data.fullname + '</font></td><td align="right"><font color="gray">' + record.data.createDate + '</font></td></tr>' + '<tr><td rowspan="2" style="white-space:normal;"><font style="font:13px 宋体;color: black;">'
						+ value + '</font></td></tr></table>'
						return html;
					}
				}],
			defaults : {
				sortable : true,
				menuDisabled : false,
				width : 100
			}
		});
		var store = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
				url : __ctxPath + '/hrm/listTrainreportReply.do'
			}),
			baseParams : {
				'Q_trainReport.id_L_EQ' : trainReportId
			},
			reader : new Ext.data.JsonReader({
				root : 'result',
				totalProperty : 'totalCounts',
				fields : [{
						name : 'replyId',
						type : 'int'
					}, 'content', 'createDate', 'fullname']
			})
		});
		store.setDefaultSort('createDate', 'asc');
		if (!Ext.isEmpty(this.id)) {
			store.load({
				params : {
					start : 0,
					limit : 10
				}
			});
		}
		var grid = new Ext.grid.GridPanel({
			store : store,
			hideHeaders : true,
			trackMouseOver : true,
			disableSelection : false,
			loadMask : true,
			autoHeight : true,
			id : 'TrainReportReplyGrid',
			autoWidth : true,
			title : '查看评论',
			iconCls : 'menu-info',
			collapsible : true,
			collapsed : false,
			cm : cm,
			viewConfig : {
				forceFit : true,
				enableRowBody : false,
				showPreview : false
			},
			bbar : new Ext.PagingToolbar({
				pageSize : 10,
				store : store,
				plugins : [new Ext.ux.PageSizePlugin()],
				displayInfo : true,
				displayMsg : '当前显示{0}至{1}， 共{2}条记录',
				emptyMsg : "当前没有记录"
			})
		});
		var report = new Ext.FormPanel({
			url : __ctxPath + '/hrm/saveTrainreportReply.do',
			id : 'TrainReportReplyForm',
			iconCls : 'menu-info',
			title : '我要评论',
			autoScroll : true,
			autoHeight : true,
			collapsible : true,
			defaultType : 'textfield',
			labelWidth : 55,
			defaults : {
				anchor : '96%,96%'
			},
			layout : 'form',
			items : [{
					name : 'trainreportReply.replyId',
					xtype : 'hidden'
				}, {
					name : 'trainreportReply.trainReport.id',
					xtype : 'hidden',
					value : trainReportId
				}, {
					name : 'trainreportReply.appUser.userId',
					xtype : 'hidden',
					value : curUserInfo.userId
				}, {
					fieldLabel : '用户',
					allowBlank : false,
					name : 'trainreportReply.fullname',
					readOnly : true,
					value : curUserInfo.fullname
				}, {
					fieldLabel : '内容',
					xtype : 'textarea',
					blankText : '评论内容为必填!',
					allowBlank : false,
					name : 'trainreportReply.content',
					id : 'TrainreportReplyContent'
				}],
			buttonAlign : 'center',
			buttons : [{
					text : '提交',
					iconCls : 'btn-save',
					handler : function() {
						var fp = Ext.getCmp('TrainReportReplyForm');
						if (fp.getForm().isValid()) {
							fp.getForm().submit({
								method : 'post',
								waitMsg : '正在提交数据...',
								success : function(fp, action) {
									Ext.ux.Toast.msg('操作信息', '成功保存信息！');
									Ext.getCmp('TrainreportReplyContent').setValue('');
									Ext.getCmp('TrainReportReplyGrid').getStore().reload();
								},
								failure : function(fp, action) {
									Ext.MessageBox.show({
										title : '操作信息',
										msg : '信息保存出错，请联系管理员！',
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
								}
							});
						}
					}
				}, {
					text : '重置',
					iconCls : 'reset',
					handler : function() {
						Ext.getCmp('TrainreportReplyContent').setValue('');
					}
				}]
		})
		// 初始化功能按钮
		// （新建时）加载表单对应的数据
		if (!Ext.isEmpty(this.record)) {
			Ext.getCmp("planId").setValue(this.record.data.id);
			Ext.getCmp("planCourseName").setValue(this.record.data.trainCourse.courseName);
			Ext.getCmp("trainTeacher").setValue(this.record.data.trainCourse.trainTeacher);
			Ext.getCmp("trainReportTarget").setValue(this.record.data.trainCourse.trainCause);
			Ext.getCmp("planCourseTime").setValue(this.record.data.trainTime);
			Ext.getCmp("planCoursePlace").setValue(this.record.data.trainPlanPlace);
			this.loadUser(this, this.record.data.id);
		}
		if (!Ext.isEmpty(this.id)) {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/hrm/getTrainReport.do?id=' + this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var data = (Ext.util.JSON.decode(action.response.responseText)).data;
					Ext.getCmp("planId").setValue(data.trainPlan.id);
					Ext.getCmp("planCourseName").setValue(data.trainPlan.trainCourse.courseName);
					Ext.getCmp("trainTeacher").setValue(data.trainPlan.trainCourse.trainTeacher);
					Ext.getCmp("trainReportTarget").setValue(data.trainPlan.trainCourse.trainCause);
					Ext.getCmp("planCourseTime").setValue(data.trainPlan.trainTime);
					Ext.getCmp("planCoursePlace").setValue(data.trainPlan.trainPlanPlace);
					thiz.loadUser(thiz, data.trainPlan.id);
					var display = Ext.getCmp('trainReportAttach');
					var fileSet = data.trainReportFiles;
					var trainFileId = "";
					for (var i = 0; i < fileSet.length; i++) {
						var file = fileSet[i];
						if (trainFileId.length > 0) {
							trainFileId += ",";
						}
						trainFileId += file.fileId;
						Ext.DomHelper.append(display.body, '<span><a href="#" onclick="FileAttachDetail.show(' + file.fileId + ')">' + file.fileName.replace(/\s+/g, ""));
						if (isGranted('_TrainReportEdit')) {
							Ext.DomHelper.append(display.body, '</a><img class="img-delete" src="' + __ctxPath + '/images/system/delete.gif" onclick="removeFile(this,' + file.fileId + ')"/>&nbsp;|&nbsp;</span>');
						} else {
							Ext.DomHelper.append(display.body, '&nbsp;|&nbsp;</span>');
						}
					}

					Ext.getCmp("trainReportAttachIDs").setValue(trainFileId);
				},
				failure : function(form, action) {
				}
			});
		}
		if (!Ext.isEmpty(this.id)) {
			this.replyContainer.add(grid);
			this.replyContainer.add(report);
		}
		this.buttons = [{
				text : '保存',
				iconCls : 'btn-save',
				hidden : (!isGranted('_TrainReportEdit') || !isGranted('_TrainReportAdd')),
				handler : this.save.createCallback(this.formPanel, this)
			}, {
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},// end of the initcomponents

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
	save : function(formPanel, window) {
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
				method : 'POST',
				waitMsg : '正在提交数据...',
				success : function(fp, action) {
					Ext.ux.Toast.msg('操作信息', '成功保存信息！');
					var gridPanel = Ext.getCmp('TrainReportGrid');
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
	},// end of save
	loadUser : function(thiz, trainPlanId) {
		Ext.Ajax.request({
			url : __ctxPath + '/hrm/queryRegistUserTrainUser.do',
			params : {
				'trainPlanId' : trainPlanId
			},
			success : function(response, options) {
				var result = (Ext.util.JSON.decode(response.responseText)).result;
				var registUser = '';
				var attendUser = '';
				var flag = false;
				if (!Ext.isEmpty(result)) {
					if(Ext.isEmpty(thiz.array)){
						thiz.array = new Array();
					}else{
						thiz.array = [];
					}
					var deptArray = new Array();
					for (var i = 0; i < result.length; i++) {
						var data = result[i];
						var appUser = data.appUser;
						thiz.array.push({
							'id' : data.id,
							'userName' : appUser.fullname,
							'attend' : data.attend
						});
						if(deptArray.indexOf(appUser.department.depName) == -1){
							deptArray.push(appUser.department.depName);
						}
						if (data.attend == "0") {//已出席人员
							if (appUser.userId == __currentUserId) {
								flag = true;
							}
							attendUser += "[" + appUser.fullname + "] ";
						} else {
							registUser += "[" + appUser.fullname + "] ";
						}
					}
				}
				Ext.DomHelper.overwrite(Ext.getCmp("statisticsUser").body, '');
				var sumPeople = 0;
				for(var i = 0; i < deptArray.length; i++){
					var deptPeople = 0;
					for (var j = 0; j < result.length; j++) {
						var data = result[j];
						var appUser = data.appUser;
						if (data.attend == "0") {//已出席人员
							if(appUser.department.depName == deptArray[i]){
								deptPeople += 1;
								sumPeople += 1;
							}
						}
					}
					Ext.DomHelper.append(Ext.getCmp("statisticsUser").body, '<div><span style="display:-moz-inline-box;display:inline-block;width:100px;"><font color="blue">['+deptArray[i]+']:</font></span>'+'<span>'+deptPeople+'人</span>'+'</div>');
				}
				Ext.DomHelper.insertFirst(Ext.getCmp("statisticsUser").body, '<div><span>总共 ['+sumPeople+'] 人参加，其中：</span>'+'</div>');
				
				Ext.DomHelper.overwrite(Ext.getCmp("registUser").body, '');
				Ext.DomHelper.append(Ext.getCmp("registUser").body, '<font color="blue">已报名未出席人员：</font>' + registUser);
				Ext.DomHelper.overwrite(Ext.getCmp("attendUser").body, '');
				Ext.DomHelper.append(Ext.getCmp("attendUser").body, '<font color="blue">已报名已出席人员：</font>' + attendUser);
				if (flag) {
					Ext.getCmp("TrainReportReplyForm").show();
				} else {
					Ext.getCmp("TrainReportReplyForm").hide();
				}
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
		})
	},
	editTrainUser : function(array, thiz, planId) {
		var trainReportUserEditForm = new TrainReportUserEditForm({
			'array' : array
		});
		trainReportUserEditForm.show();
		trainReportUserEditForm.addListener("close", function() {
			trainReportUserEditForm.removeListener("close");
			thiz.loadUser(thiz, planId);
		})
	},
	addTrainUser : function(thiz,planId) {
		UserSelector.getView(function(userIds,fullnames){
			Ext.Msg.confirm('提示信息','是否确定要添加:['+fullnames+']为参会人员',function(btn){
				if(btn=='yes'){
					Ext.Ajax.request({
						url : __ctxPath + '/hrm/addRegistUserTrainUser.do',
						params : {
							'trainPlanId' : planId,
							'userIds' : userIds
						},
						success : function(response, options) {
							Ext.ux.Toast.msg('操作信息', '操作成功！');
							thiz.loadUser(thiz, planId);
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
					})
				}
		})
		},false,false).show();
	}
});
function removeFile(obj, fileId) {
	var fileIds = Ext.getCmp("trainReportAttachIDs");
	var value = fileIds.getValue();
	if (value.indexOf(',') < 0) {// 仅有一个附件
		fileIds.setValue('');
	} else {
		value = value.replace(',' + fileId, '').replace(fileId + ',', '');
		fileIds.setValue(value);
	}

	var el = Ext.get(obj.parentNode);
	el.remove();
};
