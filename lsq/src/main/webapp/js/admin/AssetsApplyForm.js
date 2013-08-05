/**
 * @author
 * @createtime
 * @class AssetsApplyForm
 * @extends Ext.Window
 * @description AssetsApply表单
 * @company
 */
AssetsApplyForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	formPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		AssetsApplyForm.superclass.constructor.call(this, {
			id : 'AssetsApplyFormWin',
			layout : 'fit',
			items : this.formPanel,
			modal : true,
			height : 500,
			width : 700,
			title : '资产请购详细信息',
			buttonAlign : 'center',
			buttons : this.buttons
		});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		// 加载数据至store
		this.store = new Ext.data.JsonStore({
			url : __ctxPath + "/admin/detailListAssetsApply.do?id=" + this.id,
			root : 'result',
			totalProperty : 'totalCounts',
			remoteSort : true,
			fields : [{
					name : 'id',
					type : 'int'
				}, 'name', 'model', 'brand', 'num', 'unit', 'price', 'arrivalDate']
		});
		this.store.setDefaultSort('id', 'desc');
		// 加载数据
		this.store.load();

		this.formPanel = new Ext.FormPanel({
			layout : 'form',
			bodyStyle : 'padding:10px 10px 10px 10px',
			border : false,
			frame : true,
			url : __ctxPath + '/admin/saveAssetsApply.do',
			id : 'AssetsApplyForm',
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items : [{
					name : 'assetsApply.id',
					id : 'id',
					xtype : 'hidden',
					value : this.id == null ? '' : this.id
				}, {
					xtype : 'container',
					style : 'padding-left:0px;',
					layout : 'form',
					defaultType : 'textfield',
					items : [{
							xtype : 'container',
							style : 'padding-left:0px;',
							layout : 'column',
							defaultType : 'textfield',
							items : [{
									xtype : 'label',
									text : '申请人:',
									style : 'margin-top:5px;',
									width : 70
								}, {
									name : 'assetsApply.name',
									id : 'name'
								}, {
									xtype : 'label',
									text : '申请部门:',
									style : 'margin-top:5px;',
									width : 70
								}, {
									name : 'assetsApply.dept',
									id : 'dept'
								}, {
									xtype : 'label',
									text : '申请日期:',
									style : 'margin-top:5px;',
									width : 70
								}, {
									name : 'assetsApply.applyDate',
									id : 'applyDate'
								}]
						}, {
							xtype : 'container',
							layout : 'column',
							id : 'useProjectManagerContainer',
							style : 'padding-left:0px;padding-top:10px;',
							items : [ {
									xtype : 'label',
									text : '项目名称:',
									width : 70
								}, {
									xtype : 'textfield',
									columnWidth : .45,
									readOnly : true,
									maxLength : 20,
									allowBlank : false,
									id : 'useProjectName',
									name : 'assetsApply.useProject.proName',
									listeners : {
										'focus' : function() {
											ProjectNewSelector.getView(function(proId, proName, proNo, contractId, contractNo, contractAmount, proAreaId, proArea, proChargerUserId, proChargerUserName) {
												Ext.getCmp("useProjectName").setValue(proName);
												Ext.getCmp("useProjectId").setValue(proId);
												if (!Ext.isEmpty(proChargerUserName)) {
													Ext.getCmp("useProjectManagerName").setValue(proChargerUserName);
													Ext.getCmp("useProjectManagerId").setValue(proChargerUserId);
												}
											}, true).show();
										}
									}
								}, {
									xtype : 'label',
									text : '项目经理:',
									width : 80,
									style : 'padding-left:0px;padding-top:3px;margin-left:10px;'
								}, {
									xtype : 'textfield',
									columnWidth : .4,
									readOnly : true,
									maxLength : 20,
									allowBlank : false,
									id : 'useProjectManagerName',
									name : 'assetsApply.useProjectManager.fullname',
									listeners : {
										'focus' : function() {
											UserSelector.getView(function(ids, names) {
												Ext.getCmp("useProjectManagerName").setValue(names);
												Ext.getCmp("useProjectManagerId").setValue(ids);
											}, true).show();
										}
									}
								}]

						}, {
							xtype : 'container',
							style : 'padding-left:0px;margin-top:10px;',
							layout : 'column',
							defaultType : 'textfield',
							items : [{
									xtype : 'label',
									text : '资产类型:',
									style : 'margin-top:5px;',
									width : 70
								}, {
									hiddenName : 'assetsApply.applyType',
									id : 'applyType',
									xtype : 'combo',
									allowBlank : false,
									editable : false,
									triggerAction : 'all',
									store : [['0', '电子设备'], ['1', '运输设备'], ['2', '其他'], ['3', '办公家具']]
								}, {
									xtype : 'label',
									text : '是否补贴:',
									style : 'margin-top:5px;',
									width : 70
								}, {
									name : 'assetsApply.isSubsidyOrNot',
									id : 'isSubsidyOrNot',
									xtype : 'combo',
									emptyText : '---请选择---',
									editable : false,
									selectOnFocus : true,
									forceSelection : true,
									width : 100,
									triggerAction : 'all',
									// store:[['IT设备', 'IT设备'], ['车辆', '车辆'],['其他', '其他']],
									store : [['是', '是'], ['否', '否']],
									allowBlank : false
								}]
						}, {
							xtype : 'container',
							style : 'padding-left:0px;margin-top:10px;',
							layout : 'column',
							defaultType : 'textfield',
							items : [{
									xtype : 'label',
									text : '请购说明:',
									style : 'margin-top:5px;',
									width : 70
								}, {
									name : 'assetsApply.applyDescription',
									id : 'applyDescription',
									xtype : 'textarea',
									width : 550,
									height : 100
								}]
						}]
				}, {
					xtype : 'container',
					layout : 'column',
					id : 'gridContainer',
					style : 'padding-left:0px;margin-top:10px;',
					items : [{
							xtype : 'label',
							style : 'padding-left:0px;padding-top:3px;',
							text : '请购内容:',
							width : 70
						}, {
							xtype : 'editorgrid',
							id : 'detailGrid',
							width : '85%',
							height : 200,
							clicksToEdit : 2,
							store : this.store,
							cm : new Ext.grid.ColumnModel({
								columns : [new Ext.grid.RowNumberer(), {
										header : 'id',
										dataIndex : 'id',
										hidden : true
									}, {
										header : '品名',
										dataIndex : 'name',
										editor : new Ext.grid.GridEditor(new Ext.form.TextField({
											allowBlank : false

										}))
									}, {
										header : '规格',
										dataIndex : 'model',
										editor : new Ext.grid.GridEditor(new Ext.form.TextField({
											allowBlank : false
										}))
									}, {
										header : '品牌',
										dataIndex : 'brand',
										editor : new Ext.grid.GridEditor(new Ext.form.TextField({
											allowBlank : false
										}))
									}, {
										header : '数量',
										dataIndex : 'num',
										editor : new Ext.grid.GridEditor(new Ext.form.TextField({
											allowBlank : false
										}))
									}, {
										header : '单位',
										dataIndex : 'unit',
										editor : new Ext.grid.GridEditor(new Ext.form.TextField({
											allowBlank : false
										}))
									}, {
										header : '价格',
										dataIndex : 'price',
										editor : new Ext.grid.GridEditor(new Ext.form.TextField({
											allowBlank : false
										}))
									}, {
										header : '到货日期',
										dataIndex : 'arrivalDate',
										editor : new Ext.grid.GridEditor(new Ext.form.TextField({
											allowBlank : true
										})),
										renderer : function(value) {
											return value.substring(0, 10);
										}
									}],
								defaults : {
									sortable : false,
									menuDisabled : false,
									width : 70
								}
							})
						}]

				}]
		});
		// 加载表单对应的数据
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/admin/getAssetsApply.do?id=' + this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var res = action.result.data;
					Ext.getCmp('applyDate').setValue(res.applyDate.substring(0, 10));

					if (Ext.isEmpty(res.useProject) && Ext.isEmpty(res.useProjectManager)) {
						Ext.getCmp("useProjectManagerContainer").hide();
						Ext.getCmp("useProjectName").allowBlank = true;
						Ext.getCmp("useProjectManagerName").allowBlank = true;
					}else{
						Ext.getCmp("useProjectName").setValue(res.useProject.proName);
						Ext.getCmp("useProjectManagerName").setValue(res.useProjectManager.fullname);
					}

				},
				failure : function(form, action) {
				}
			});
		}
		// 初始化功能按钮
		this.buttons = [{
				text : '关闭',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
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
	save : function(formPanel, window) {
		if (formPanel.getForm().isValid()) {
			formPanel.getForm().submit({
				method : 'POST',
				waitMsg : '正在提交数据...',
				success : function(fp, action) {
					Ext.ux.Toast.msg('操作信息', '成功保存信息！');
					var gridPanel = Ext.getCmp('AssetsApplyGrid');
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