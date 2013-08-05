/**
 * @author
 * @createtime
 * @class TrainSurveyForm
 * @extends Ext.Window
 * @description TrainSurvey表单
 */
TrainSurveyForm = Ext.extend(Ext.Panel, {
	// 内嵌FormPanel
	formPanel : null,
	// 头部工具栏
	topbar : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		TrainSurveyForm.superclass.constructor.call(this, {
			id : 'TrainSurveyFormWin',
			layout : 'fit',
			items : this.formPanel,
			frame : true,
			tbar : this.topbar,
			maximizable : true,
			title : '详细信息'
		});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		var thiz = this;
		var trainSurveyId = this.id;
		var trainSurveyUserId = this.trainSurveyUserId;
		this.topbar = new Ext.Toolbar({
			height : 30,
			bodyStyle : 'text-align:left',
			items : [{
					iconCls : 'btn-save',
					text : '保存',
					hidden : (!isGranted('_TrainSurveyEdit') || !isGranted('_TrainSurveyAdd')),
					xtype : 'button',
					handler : this.save.createCallback(this),
					scope : this
				}, {
					iconCls : 'vote-btn',
					text : '投票',
					xtype : 'button',
					handler : this.interest.createCallback(this, trainSurveyId,trainSurveyUserId),
					scope : this
				}]
		});
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			trackResetOnLoad : true,
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			url : __ctxPath + '/hrm/saveTrainSurvey.do',
			id : 'TrainSurveyForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
					name : 'trainSurvey.id',
					id : 'id',
					xtype : 'hidden',
					value : this.id == null ? '' : this.id
				}, {
					title : '调研信息',
					xtype : 'fieldset',
					collapsible : true,
					titleCollapse : true,
					layout : 'form',
					items : [{
							xtype : 'container',
							layout : 'column',
							items : [{
									xtype : 'container',
									layout : 'form',
									defaultType : 'textfield',
									columnWidth : .4,
									defaults : {
										anchor : '98%,98%'
									},
									items : [{
											fieldLabel : '编号',
											name : 'trainSurvey.sn',
											allowBlank : false,
											id : 'sn'
										}, {
											fieldLabel : '开始时间',
											allowBlank : false,
											xtype : 'datetimefield',
											format : 'Y-m-d H:i:s',
											name : 'trainSurvey.beginTime',
											id : 'beginTime'
										}, {
											fieldLabel : '发起人',
											allowBlank : false,
											name : 'trainSurvey.sponsor.fullname',
											id : 'sponsor',
											readOnly : true,
											cls : 'x-item-disabled',
											value : __currentUser
										}, {
											xtype : 'hidden',
											name : 'trainSurvey.sponsor.userId',
											id : 'sponsorUserId',
											value : __currentUserId
										}]
								}, {
									xtype : 'container',
									layout : 'form',
									defaultType : 'textfield',
									columnWidth : .4,
									defaults : {
										anchor : '98%,98%'
									},
									items : [{
											fieldLabel : '版本号',
											allowBlank : false,
											name : 'trainSurvey.verNum',
											id : 'verNum'
										}, {
											fieldLabel : '结束时间',
											allowBlank : false,
											xtype : 'datetimefield',
											format : 'Y-m-d H:i:s',
											name : 'trainSurvey.endTime',
											id : 'endTime'
										}]
								}]
						}, {
							xtype : 'container',
							layout : 'column',
							items : [{
									xtype : 'container',
									layout : 'form',
									columnWidth : 1,
									defaults : {
										anchor : '98%,98%'
									},
									items : [{
											fieldLabel : '备注',
											xtype : 'textarea',
											name : 'trainSurvey.remark',
											id : 'remark'
										}]
								}]
						}]
				}, {
					title : '调研课程/投票',
					xtype : 'fieldset',
					collapsible : true,
					titleCollapse : true,
					layout : 'form',
					items : [{
							xtype : 'button',
							style : 'margin-bottom : 5px',
							text : '编辑',
							hidden : (!isGranted('_TrainSurveyEdit') || !isGranted('_TrainSurveyAdd')),
							listeners : {
								click : function() {
									var courseContainer = Ext.getCmp("courseContainer");
									var courseIds = Ext.getCmp("courseIds");
									var courseNos = Ext.getCmp("courseNoes");
									var courseNames = Ext.getCmp("courseNames");
									CourseBoxSelector.getView(function(courseId, courseNo, courseName) {
										if (!Ext.isEmpty(courseId)) {
											courseContainer.removeAll();
											courseIds.setValue(courseId);
											courseNos.setValue(courseNo);
											courseNames.setValue(courseName);
											var courseIdsSelect = courseId.split(",");
											var courseNoesSelect = courseNo.split(",");
											var courseNamesSelect = courseName.split(",");
											var courseCheckboxItems = [];
											for (var i = 0; i < courseIdsSelect.length; i++) {
												courseCheckboxItems.push({
													boxLabel : ((courseNamesSelect[i]).length>10)?((courseNamesSelect[i]).substring(0,10)+"..."):courseNamesSelect[i],
													courseId : courseIdsSelect[i]
												});
											}
											var courseCheckboxGroup = new Ext.form.CheckboxGroup({
												xtype : 'checkboxgroup',
												id : 'courseCheckboxGroup',
												itemCls : 'x-check-group-alt',
												columns : 6,
												items : courseCheckboxItems
											});
											courseContainer.add(courseCheckboxGroup);
											courseContainer.doLayout();
										} else {
											courseContainer.removeAll();
										}
									}, false, courseIds.getValue(), courseNos.getValue(), courseNames.getValue()).show();
								}
							}
						}, {
							xtype : 'hidden',
							name : 'courseIds',
							id : 'courseIds'
						}, {
							xtype : 'hidden',
							id : 'courseNoes'
						}, {
							xtype : 'hidden',
							id : 'courseNames'
						}, {
							xtype : 'panel',
							height : 200,
							autoScroll : true,
							layout : 'column',
							id : 'courseContainer',
							items : []
						}]
				}]
		});
		// 加载表单对应的数据
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/hrm/getTrainSurvey.do?id=' + this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var result = Ext.util.JSON.decode(action.response.responseText);
					Ext.getCmp("sponsor").setValue(result.data.sponsor.fullname);
					Ext.getCmp("sponsorUserId").setValue(result.data.sponsor.userId);
					thiz.loadbutton(thiz, trainSurveyId);
				},
				failure : function(form, action) {
				}
			});
		}
	},// end of the initcomponents

	/**
	 * 重置
	 * 
	 * @param {}
	 *            formPanel
	 */
	reset : function(formPanel) {
		formPanel.getForm().reset();
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
		if (window.formPanel.getForm().isValid()) {
			window.formPanel.getForm().submit({
				method : 'POST',
				waitMsg : '正在提交数据...',
				success : function(fp, action) {
					Ext.ux.Toast.msg('操作信息', '成功保存信息！');
					var gridPanel = Ext.getCmp('TrainSurveyGrid');
					if (gridPanel != null) {
						gridPanel.getStore().reload();
					}
					window.removeTrainSurveyForm(window);
				},
				failure : function(fp, action) {
					Ext.MessageBox.show({
						title : '操作信息',
						msg : '信息保存出错，请联系管理员！',
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
					window.removeTrainSurveyForm(window);
				}
			});
		}
	},// end of save
	removeTrainSurveyForm : function(trainSurveyForm) {
		var tabs = Ext.getCmp('centerTabPanel');
		var tabItem = tabs.getItem('TrainSurveyFormWin');
		if (tabItem != null) {
			tabs.remove(tabItem)
		}
	},
	loadbutton : function(thiz, trainSurveyId) {
		Ext.Ajax.request({
			url : __ctxPath + '/hrm/queryTrainSurCoTrainSurvey.do?trainSurveyId=' + trainSurveyId,
			success : function(response, options) {
				var res = Ext.util.JSON.decode(response.responseText);
				var courseContainer = Ext.getCmp("courseContainer");
				if (!Ext.isEmpty(res.result)) {
					var courseId = '';
					var courseNo = '';
					var courseName = '';
					var courseCheckboxItems = [];
					var checked = false;
					for (var i = 0; i < res.result.length; i++) {
						if (i > 0) {
							courseId += ",";
							courseNo += ",";
							courseName += ",";
						} else {
							courseContainer.removeAll();//第一次清空所有checkbox
						}
						if (res.result[i].interest == '1') {
							checked = false;
						} else {
							checked = true;
						}
						courseId += res.result[i].courseId;
						courseNo += res.result[i].courseNo;
						courseName += res.result[i].courseName;
						courseCheckboxItems.push({
							boxLabel : ((res.result[i].courseName).length > 10)?((res.result[i].courseName).substring(0,10)+"..."):res.result[i].courseName,
							courseId : res.result[i].courseId,
							checked : checked
						});
					}
					var courseCheckboxGroup = new Ext.form.CheckboxGroup({
						xtype : 'checkboxgroup',
						id : 'courseCheckboxGroup',
						itemCls : 'x-check-group-alt',
						columns : 6,
						items : courseCheckboxItems
					});
					courseContainer.add(courseCheckboxGroup);
					courseContainer.doLayout();
					Ext.getCmp("courseIds").setValue(courseId);
					Ext.getCmp("courseNoes").setValue(courseNo);
					Ext.getCmp("courseNames").setValue(courseName);
				}
			},
			failure : function() {
				Ext.MessageBox.show({
					title : '操作信息',
					msg : '信息保存出错，请联系管理员！',
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.ERROR
				});
			}
		})
	},
	interest : function(thiz, trainSurveyId,trainSurveyUserId) {
		var courseCheckboxGroup = Ext.getCmp("courseCheckboxGroup");
		if (Ext.isEmpty(courseCheckboxGroup)) {
			Ext.ux.Toast.msg('操作信息', '没有投票的课程！');
		} else {
			if (courseCheckboxGroup.validate()) {
				var cbitems = courseCheckboxGroup.items;
				var courseIds = [];
				for (var i = 0; i < cbitems.length; i++) {
					if (cbitems.itemAt(i).checked) {
						courseIds.push(cbitems.itemAt(i).courseId);
					}
				}
				Ext.Ajax.request({
					url : __ctxPath + '/hrm/updateVoteTrainSurvey.do',
					params : {
						'trainSurveyId' : trainSurveyId,
						'trainSurveyUserId' : trainSurveyUserId,
						'trainCourseIds' : courseIds,
						'userId' : __currentUserId
					},
					success : function(response, options) {
						Ext.ux.Toast.msg('操作信息', '投票成功！');
						var gridPanel = Ext.getCmp('TrainSurveyGrid');
						if (gridPanel != null) {
							gridPanel.getStore().reload();
						}
						thiz.removeTrainSurveyForm(thiz);
					},
					failure : function(fp, action) {
						Ext.MessageBox.show({
							title : '操作信息',
							msg : '信息保存出错，请联系管理员！',
							buttons : Ext.MessageBox.OK,
							icon : Ext.MessageBox.ERROR
						});
						thiz.removeTrainSurveyForm(thiz);
					}
				})
			}

		}
	}
});