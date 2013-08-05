/**
 * @author
 * @createtime
 * @class PrequalificareportForm
 * @extends Ext.Window
 * @description Prequalificareport表单
 */
PrequalificareportForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		PrequalificareportForm.superclass.constructor.call(this, {
					id : 'PrequalificareportFormWin',
					layout : 'fit',
					items : this.formPanel,
					modal : true,
					autoScroll : true,
					width : 560,
					height : 500,
					maximizable : true,
					title : '[资格预审报备]详细信息',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	// 初始化组件
	projectPreQFocus : function() {
		ProjectNewSelector.getView(
				function(proId, proName, proNo, contractId, contractNo,
						contractAmount, proAreaId, proArea, proChargerUserId,
						proChargerUserName, proChargerUserTel, proAdress,
						buildUnit, designUnit) {
					Ext.getCmp("prequalificareport.proName").setValue(proName);
					Ext.getCmp("prequalificareport.proId").setValue(proId);

					if (!Ext.isEmpty(buildUnit)) {
						Ext.getCmp("buildUnit").setValue(buildUnit);
					} else {
						Ext.getCmp("buildUnit").emptyText = '请填写建设单位';
						Ext.getCmp("buildUnit").setValue('');
						Ext.getCmp("buildUnit").purgeListeners();
					}
					if (!Ext.isEmpty(designUnit)) {
						Ext.getCmp("designUnit").setValue(designUnit);
					} else {
						Ext.getCmp("designUnit").emptyText = '请填写设计单位';
						Ext.getCmp("designUnit").setValue('');
						Ext.getCmp("designUnit").purgeListeners();
					}

				}, true, true).show();
	},
	initUIComponents : function() {
		var _url = __ctxPath + '/system/listWorkDepartment.do?opt=appUser&hasUnknow=yes';
		var depSelector = new TreeSelector(
				'prequalificareport.materialFitDeptNameIIDD', _url, '',
				'prequalificareport.materialFitDept.depName', false, 110,
				'prequalificareport.materialFitDept.depName');

		Ext.getCmp("prequalificareport.materialFitDeptNameIIDDTree").on(
				"click", function(g) {
					Ext
							.getCmp("prequalificareport.materialFitDept.depId")
							.setValue(Ext
									.getCmp("prequalificareport.materialFitDeptNameIIDD").id);
				});
		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			trackResetOnLoad : true,
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			url : __ctxPath + '/statistics/savePrequalificareport.do',
			id : 'PrequalificareportForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
						name : 'prequalificareport.id',
						id : 'id',
						xtype : 'hidden',
						value : this.id == null ? '' : this.id
					}, {
						xtype : "container",
						layout : "column",
						height : 25,
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [
								// {
								// xtype : 'hidden',
								// name : 'prequalificareport.project.id',
								// id : 'prequalificareport.proId'
								// },
								{
							text : '项目名称：',
							width : 100
						}, {
							xtype : 'textfield',
							name : 'prequalificareport.proName',
							id : 'prequalificareport.proName',
							allowBlank : false,
							// emptyText : '请选择项目',
							width : 150,
							listeners : {
							// 'focus' : this.projectPreQFocus
							// .createCallback()
							}
						}, {
							style : "margin-left:5px",
							text : '招标类型：',
							width : 100
						}, {
							xtype : "radiogroup",
							id : 'bidType',
							name : "prequalificareport.bidType",
							allowBlank : false,
							width : 100,
							items : [{
										boxLabel : '设计',
										inputValue : "设计",
										name : "prequalificareport.bidType",
										checked : true
									}, {
										boxLabel : '施工',
										inputValue : "施工",
										name : "prequalificareport.bidType"
									}]
						}]
					}, {
						xtype : "container",
						layout : "column",
						height : 25,
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									text : '建设单位：',
									width : 100
								}, {
									xtype : 'textfield',
									name : 'prequalificareport.buildUnit',
									id : 'buildUnit',
									allowBlank : false,
									// emptyText : '请选择项目',
									width : 150,
									listeners : {
									// 'focus' : this.projectPreQFocus
									// .createCallback()
									}
								}, {
									style : "margin-left:5px",
									text : '设计单位：',
									width : 100
								}, {
									xtype : 'textfield',
									name : 'prequalificareport.designUnit',
									id : 'designUnit',
									// emptyText : '请选择项目',
									allowBlank : false,
									width : 150,
									listeners : {
									// 'focus' : this.projectPreQFocus
									// .createCallback()
									}
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [

						{
									text : '招标代理：',
									width : 100
								}, {
									xtype : 'textfield',
									name : 'prequalificareport.bidProxy',
									id : 'bidProxy',
									allowBlank : false,
									width : 150
								}, {
									style : 'margin-left:5px',
									text : '项目估算价（万元）：',
									width : 120
								}, {
									xtype : 'numberfield',
									name : 'prequalificareport.ProEstimatPrice',
									id : 'proEstimatPrice',
									allowBlank : false,
									width : 130
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									text : '公告发布时间：',
									width : 100
								}, {
									xtype : 'datefield',
									name : 'prequalificareport.timeNotice',
									id : 'timeNotice',
									allowBlank : false,
									width : 145,
									format : 'Y-m-d'
								}, {
									style : "margin-left:5px",
									text : '资格预审时间：',
									width : 100
								}, {
									xtype : 'datefield',
									name : 'prequalificareport.timePreQualifica',
									id : 'timePreQualifica',
									allowBlank : false,
									width : 145,
									format : 'Y-m-d'
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									text : '预计开标时间：',
									width : 100
								}, {
									xtype : 'datefield',
									name : 'prequalificareport.timePerBidOpen',
									id : 'timePerBidOpen',
									allowBlank : false,
									width : 145,
									format : 'Y-m-d'
								}, {
									style : "margin-left:5px",
									text : '建造师资质要求：',
									width : 100
								}, {
									xtype : "radiogroup",
									name : 'prequalificareport.requireConstrLevel',
									id : 'requireConstrLevel',
									width : 100,
									items : [{
										boxLabel : '一级',
										inputValue : "一级",
										name : 'prequalificareport.requireConstrLevel',
										checked : true
									}, {
										boxLabel : '二级',
										inputValue : "二级",
										name : 'prequalificareport.requireConstrLevel'
									}]
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									text : '资格预审是否到场：',
									width : 110
								}, {
									xtype : "radiogroup",
									name : 'prequalificareport.sureArrivalPreQ',
									id : 'sureArrivalPreQ',
									width : 140,
									items : [{
										boxLabel : '否',
										inputValue : "否",
										name : "prequalificareport.sureArrivalPreQ",
										checked : true
									}, {
										boxLabel : '是',
										inputValue : "是",
										name : "prequalificareport.sureArrivalPreQ"
									}]
								}, {
									style : "margin-left:5px",
									text : '开标是否到场：',
									width : 100
								}, {
									name : 'prequalificareport.sureArrivalBid',
									id : 'sureArrivalBid',
									xtype : "radiogroup",
									width : 100,
									items : [{
										boxLabel : '否',
										inputValue : "否",
										name : "prequalificareport.sureArrivalBid",
										checked : true
									}, {
										boxLabel : '是',
										inputValue : "是",
										name : "prequalificareport.sureArrivalBid"
									}]

								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									text : '市场部推荐建造师：',
									width : 110
								}, {
									xtype : 'textfield',
									name : 'prequalificareport.suggestConstructor',
									id : 'suggestConstructor',
									allowBlank : false,
									width : 140
								}, {
									style : "margin-left:5px",
									text : '施工配合区域：',
									width : 100
								}, {
									xtype : 'hidden',
									name : 'prequalificareport.materialFitDept.depId',
									id : 'prequalificareport.materialFitDept.depId'
								}, depSelector]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									text : '企业资质要求：',
									width : 100
								}, {
									xtype : 'textarea',
									name : 'prequalificareport.reqEnterpriseQua',
									id : 'reqEnterpriseQua',
									width : 400,
									allowBlank : false,
									autoScroll : true
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									text : '业绩要求（企业、项目经理）：',
									width : 100
								}, {
									xtype : 'textarea',
									name : 'prequalificareport.reqPerformance',
									id : 'reqPerformance',
									allowBlank : false,
									width : 400,
									autoScroll : true
								}]
					}, {
						xtype : "panel",
						layout : "column",
						border : false,
						unstyled : false,
						defaults : {
							xtype : 'label',
							style : {
								margin : '0px 0px 3px 0px'
							}
						},
						items : [{
									text : '其他要求：',
									width : 100
								}, {
									xtype : 'textarea',
									name : 'prequalificareport.reqOther',
									id : 'reqOther',
									width : 400,
									autoScroll : true
								}]
					}, {
						xtype : 'hidden',
						name : 'prequalificareport.applyUser.userId',
						id : 'prequalificareport.applyUserId',
						value : this.id == null ? __currentUserId : ''
					}

			]
		});
		// 加载表单对应的数据
		this.initData();
		// 初始化功能按钮
		this.buttons = [{
			text : '保存',
			iconCls : 'btn-save',
			hidden : !(isGranted("_PrequalificareportAdd") || isGranted("_PrequalificareportEdit")),
			handler : this.save.createCallback(this.formPanel, this)
		}, {
			text : '重置',
			iconCls : 'btn-reset',
			hidden : !(isGranted("_PrequalificareportAdd") || isGranted("_PrequalificareportEdit")),
			handler : this.reset.createCallback(this.formPanel)
		}, {
			text : '取消',
			iconCls : 'btn-cancel',
			handler : this.cancel.createCallback(this)
		}];
	},// end of the initcomponents
	/**
	 * 初始化数据
	 * 
	 * @param {}
	 *            formPanel
	 */
	initData : function() {
		// 加载表单对应的数据
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getPrequalificareport.do?id='
						+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var jsonData = Ext.util.JSON
							.decode(action.response.responseText);
					var res = jsonData.data;
					// Ext.getCmp("").originalValue=

					// Ext.getCmp("prequalificareport.proId").originalValue=res.project.id;
					// Ext.getCmp("prequalificareport.proId").setValue(res.project.id);
					Ext.getCmp("prequalificareport.proName").originalValue = res.proName;
					Ext.getCmp("prequalificareport.proName")
							.setValue(res.proName);

					Ext.getCmp("buildUnit").purgeListeners();
					Ext.getCmp("designUnit").purgeListeners();

					if (Ext.isEmpty(res.materialFitDept)) {
						Ext.getCmp("prequalificareport.materialFitDept.depId").originalValue = 0;
						Ext.getCmp("prequalificareport.materialFitDept.depId")
								.setValue(0);
						Ext.getCmp("prequalificareport.materialFitDeptNameIIDD").originalValue = '不确定';
						Ext.getCmp("prequalificareport.materialFitDeptNameIIDD")
								.setValue('不确定');
					} else {
						Ext.getCmp("prequalificareport.materialFitDept.depId").originalValue = res.materialFitDept.depId;
						Ext.getCmp("prequalificareport.materialFitDept.depId")
								.setValue(res.materialFitDept.depId);

						Ext.getCmp("prequalificareport.materialFitDeptNameIIDD").originalValue = res.materialFitDept.depName;
						Ext.getCmp("prequalificareport.materialFitDeptNameIIDD")
								.setValue(res.materialFitDept.depName);
					}

					Ext.getCmp("prequalificareport.applyUserId").originalValue = res.applyUser.userId;
					Ext.getCmp("prequalificareport.applyUserId")
							.setValue(res.applyUser.userId);

				},
				failure : function(form, action) {
				}
			});
		}
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
							var gridPanel = Ext
									.getCmp('PrequalificareportGrid');
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
							// window.close();
						}
					});
		}
	}// end of save

});