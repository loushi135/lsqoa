var FixedAssetsForm = function(assetsId) {
	this.assetsId = assetsId;
	var fp = this.setup();
	var window = new Ext.Window({
				id : 'FixedAssetsFormWin',
				title : '固定资产详细信息',
				width : 1010,
				iconCls : 'menu-arch-detail',
				// height : 430,
				x : 250,
				y : 80,
				autoHeight : true,
				shadow : false,
				modal : true,
				layout : 'anchor',
				plain : true,
				bodyStyle : 'padding:5px;',
				buttonAlign : 'center',
				items : [fp]
			});
	if(isGranted('_FixedAssetsEdit')||isGranted('_FixedAssetsAdd')){
		window.addButton(new Ext.Button({
			text : '保存',
			iconCls : 'btn-save',
			handler : function() {
				var form =Ext.getCmp('FixedAssetsForm');
				if (form.getForm().isValid()) {
					form.getForm().submit({
						method : 'post',
						waitMsg : '正在提交数据...',
						success : function(form, action) {
							Ext.ux.Toast.msg('操作信息', '成功保存信息！');
							Ext.getCmp('FixedAssetsGrid').getStore()
									.reload();
							window.close();
						},
						failure : function(form, action) {
							Ext.MessageBox.show({
										title : '操作信息',
										msg : '信息保存出错，请联系管理员！',
										buttons : Ext.MessageBox.OK,
										icon : 'ext-mb-error'
									});
							window.close();
						}
					});
				} else {
					Ext.ux.Toast.msg('操作信息', '带*项为必填！！');
				}
			}
		}));
		window.addButton(new Ext.Button({
			text : '取消',
			iconCls : 'btn-cancel',
			handler : function() {
				window.close();
			}
		}));
	}
	window.show();
};

FixedAssetsForm.prototype.setup = function() {

	var store = this.store();
	store.load({
			params : {
			start : 0,
			limit : 5
		}
	});
	
	var store1 = this.store1();
	store1.load({
			params : {
			start : 0,
			limit : 5
		}
	});
	
	var formPanel = new Ext.FormPanel({
		url : __ctxPath + '/admin/saveFixedAssets.do',
		layout : 'form',
		columnWidth : .6,
		id : 'FixedAssetsForm',
		frame : true,
		// defaults : {
		// width : 200,
		// anchor : '100%,100%',
		// },
		formId : 'FixedAssetsFormId',
		defaultType : 'textfield',
		items : [{
					name : 'fixedAssets.assetsId',
					id : 'assetsId',
					xtype : 'hidden',
					value : this.assetsId == null ? '' : this.assetsId
				}, {
					name : 'fixedAssets.depreRate',
					id : 'depreRate',
					xtype : 'hidden'
				},{
					xtype : 'container',
					layout : 'column',
					id : 'assetsNoContainer',
					style : 'padding-left:0px;padding-bottom:3px;',
					items : [{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '资产名称:',
								width : 100
							}, {
								name : 'fixedAssets.assetsName',
								xtype : 'textfield',
								id : 'assetsName',
								anchor : '95.5%',
								allowBlank : false,
								width : 150
							}, {
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;margin-left:20px;',
								text : '资产编号:',
								width : 100
							}, {
								xtype : 'textfield',
								name : 'fixedAssets.assetsNo',
								id : 'assetsNo',
								readOnly : false,
								width : 150
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					id : 'assetsTypeContainer',
					style : 'padding-left:0px;padding-bottom:3px;',
					items : [{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '资产类别:',
								width : 97
							}, {
								hiddenName : 'fixedAssets.assetsType.assetsTypeId',
								id : 'assetsTypeId',
								xtype : 'combo',
								mode : 'remote',
								anchor : '95.5%',
								allowBlank : false,
								editable : false,
								valueField : 'id',
								displayField : 'name',
								triggerAction : 'all',
								width : 150,
								store : new Ext.data.SimpleStore({
											autoLoad : true,
											fields : ['id', 'name'],
											url : __ctxPath
													+ '/admin/comboxAssetsType.do'
										})
							}, {
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;margin-left:17px;',
								text : '当前使用人:',
								width : 100
							}, {
								xtype : 'textfield',
								name : 'fixedAssets.user',
								id : 'user',
								readOnly : false,
								width : 150
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					id : 'inContainer',
					style : 'padding-left:0px;padding-bottom:3px;',
					items : [{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '入库日期:',
								width : 97
							}, {
								name : 'fixedAssets.buyDate',
								id : 'buyDate',
								format : 'Y-m-d',
								xtype : 'datefield',
								allowBlank : true,
								editable : true,
								anchor : '95.5%',
								width : 150,
								readOnly : false
							}, {
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;margin-left:17px;',
								text : '入库人:',
								width : 100
							}, {
								xtype : 'textfield',
								name : 'fixedAssets.inperson',
								id : 'inperson',
								readOnly : false,
								width : 150
							}]

				}, {
					xtype : 'container',
					layout : 'column',
					id : 'bgContainer',
					style : 'padding-left:0px;padding-bottom:3px;',
					items : [{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '存放地点:',
								width : 100
							}, {
								name : 'fixedAssets.beDep',
								id : 'beDep',
								xtype : 'textfield',
								allowBlank : false,
								editable : true,
								width : 150,
								readOnly : false
							}, {
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;margin-left:20px;',
								text : '保管人:',
								width : 100
							}, {
								xtype : 'textfield',
								name : 'fixedAssets.custodian',
								id : 'custodian',
								readOnly : false,
								width : 150
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					id : 'indexContainer',
					style : 'padding-left:0px;padding-bottom:3px;',
					items : [{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '档案索引:',
								width : 100
							}, {
								name : 'fixedAssets.fileindex',
								id : 'fileindex',
								xtype : 'textfield',
								allowBlank : true,
								editable : true,
								width : 150,
								readOnly : false
							}, {
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;margin-left:20px;',
								text : '标贴情况:',
								width : 97
							}, {
								xtype : 'combo',
								hiddenName : 'fixedAssets.labelStatus',
								id : 'labelStatus',
								width : 150,
								mode : 'local',
								editable : false,
								allowBlank : false,
								triggerAction : 'all',
								store : [[1, '已标贴'], [0, '未标贴']]

							}]
				}, {
					xtype : 'container',
					layout : 'column',
					id : 'statusContainer',
					style : 'padding-left:0px;padding-bottom:3px;',
					items : [{
								xtype : 'label',
								style : 'padding-left:0px;padding-top:3px;',
								text : '状态:',
								width : 97
							}, {
								xtype : 'combo',
								hiddenName : 'fixedAssets.status',
								id : 'status',
								width : 150,
								mode : 'local',
								editable : false,
								allowBlank : false,
								triggerAction : 'all',
								store : [[0, '存库'], [1, '使用'], [2, '报废']]

							}]
					
					
				},{
					xtype : 'tabpanel',
					height : 220,
					plain : true,
					border : false,
					activeTab : 0,
					items : [{
						layout : 'form',
						id : 'deprePanel',
						title : '资产信息',
						frame : true,
						height : 220,
						defaults : {
							anchor : '98%,98%'
						},
						items : [{
							xtype : 'container',
							layout : 'column',
							id : 'modelContainer',
							style : 'padding-left:0px;padding-bottom:3px;',
							items : [{
										xtype : 'label',
										style : 'padding-left:0px;padding-top:3px;',
										text : '品牌:',
										width : 100
									}, {
										name : 'fixedAssets.manufacturer',
										id : 'manufacturer',
										xtype : 'textfield',
										allowBlank : false,
										editable : true,
										width : 150,
										readOnly : false
									}, {
										xtype : 'label',
										style : 'padding-left:0px;padding-top:3px;margin-left:20px;',
										text : '型号:',
										width : 90
									}, {
										xtype : 'textfield',
										name : 'fixedAssets.model',
										id : 'model',
										readOnly : false,
										width : 150
									}]
						}, {
							xtype : 'container',
							layout : 'column',
							id : 'snContainer',
							style : 'padding-left:0px;padding-bottom:3px;',
							items : [{
										xtype : 'label',
										style : 'padding-left:0px;padding-top:3px;',
										text : '序列号:',
										width : 100
									}, {
										name : 'fixedAssets.sn',
										id : 'sn',
										xtype : 'textfield',
										allowBlank : true,
										editable : true,
										width : 150,
										readOnly : false
									}, {
										xtype : 'label',
										style : 'padding-left:0px;padding-top:3px;margin-left:20px;',
										text : '资产原值:',
										width : 90
									}, {
										name : 'fixedAssets.assetscost',
										id : 'assetscost',
										xtype : 'textfield',
										allowBlank : true,
										editable : true,
										width : 150,
										readOnly : false
									}]
						}, {
							xtype : 'container',
							layout : 'column',
							id : 'assetscostContainer',
							style : 'padding-left:0px;padding-bottom:3px;',
							items : [{
										xtype : 'label',
										style : 'padding-left:0px;padding-top:3px;',
										text : '配置:',
										width : 100
									}, {
										xtype : 'textfield',
										name : 'fixedAssets.configuration',
										id : 'configuration',
										readOnly : false,
										width : '76%'
									}]
						}, {
							xtype : 'textarea',
							fieldLabel : '备注：',
							id : 'remark',
							name : 'fixedAssets.notes',
							height : 120,
							anchor : '95%'

						}]

					}
//					, {
//						layout : 'form',
//						title : '使用记录',
//						width : 560,
//						id : 'assetsFormPanel',
//						height : 300,
//						frame : true,
//						items : [new Ext.grid.GridPanel({
//							id : 'CarUseInfoGrid',
//							trackMouseOver : true,
//							disableSelection : false,
//							loadMask : true,
//							autoHeight : true,
//							store : store,
//							sm : new Ext.grid.CheckboxSelectionModel(),
//							cm : new Ext.grid.ColumnModel({
//								columns : [
//										new Ext.grid.RowNumberer(), {
//											header : 'id',
//											dataIndex : 'id',
//											hidden : true
//										}, {
//											header : '使用人',
//											dataIndex : 'userId'
//										}, {
//											header : '资产编号',
//											dataIndex : 'assetsNo'
//										}, {
//											header : '资产名称',
//											dataIndex : 'assetsName'
//										}, {
//											header : '资产序列号',
//											dataIndex : 'sn'
//										}, {
//											header : '领用日期',
//											dataIndex : 'useDate'
//										}, {
//											header : '状态',
//											dataIndex : 'useStatus',
//											renderer : function(value) {
//												return value == 1 ? '领用' : '归还';
//											}
//										}],
//								defaults : {
//									sortable : true,
//									menuDisabled : false,
//									width : 20
//								}
//							}),
//							viewConfig : {
//								forceFit : true,
//								enableRowBody : false,
//								showPreview : false
//							},
//							bbar : new Ext.PagingToolbar({
//										pageSize : 5,
//										store : store,
//										plugins : [new Ext.ux.PageSizePlugin()], 
//										displayInfo : true,
//										displayMsg : '当前显示从{0}至{1}， 共{2}条记录',
//										emptyMsg : "当前没有记录"
//									})
//						})]
//					}, {
//						layout : 'form',
//						title : '维保记录',
//						width : 560,
//						id : 'assetsFormPanel1',
//						height : 300,
//						frame : true,
//						items : [new Ext.grid.GridPanel({
//							id : 'CarUseInfoGrid1',
//							trackMouseOver : true,
//							disableSelection : false,
//							loadMask : true,
//							autoHeight : true,
//							store : store1,
//							sm : new Ext.grid.CheckboxSelectionModel(),
//							cm : new Ext.grid.ColumnModel({
//								columns : [
//										new Ext.grid.RowNumberer(), {
//											header : 'id',
//											dataIndex : 'id',
//											hidden : true
//										}, {
//											header : '资产编号',
//											dataIndex : 'assetsNo'
//										}, {
//											header : '资产名称',
//											dataIndex : 'assetsName'
//										}, {
//											header : '资产序列号',
//											dataIndex : 'sn'
//										}, {
//											header : '申请人',
//											dataIndex : 'appUser'
//										}, {
//											header : '故障描述',
//											dataIndex : 'faultDespriction'
//										},{
//											header : '维修类型',
//											dataIndex : 'repairType',
//											renderer : function(value) {
//												return value == 0 ? '内部维修' : '外部维修';
//											}
//										}],
//								defaults : {
//									sortable : true,
//									menuDisabled : false,
//									width : 20
//								}
//							}),
//							viewConfig : {
//								forceFit : true,
//								enableRowBody : false,
//								showPreview : false
//							},
//							bbar : new Ext.PagingToolbar({
//										pageSize : 5,
//										store : store1,
//										plugins : [new Ext.ux.PageSizePlugin()], 
//										displayInfo : true,
//										displayMsg : '当前显示从{0}至{1}， 共{2}条记录',
//										emptyMsg : "当前没有记录"
//									})
//						})]
//					}
					]

				}]
	});
	if (this.assetsId != null && this.assetsId != 'undefined') {

		formPanel.getForm().load({
			deferredRender : false,
			url : __ctxPath + '/admin/getFixedAssets.do?assetsId='
					+ this.assetsId,
			// method : 'post',
			waitMsg : '正在载入数据...',
			success : function(form, action) {

				var buDate = action.result.data.buyDate;
				if (null != buDate) {
					Ext.getCmp('buyDate').setValue(new Date(getDateFromFormat(
							buDate, "yyyy-MM-dd HH:mm:ss")));
				}
				var configuration = action.result.data.configuration;
				var assetsImageDisplay = Ext.getCmp('assetsImageDisplay');
				if(configuration!=null&&configuration!=''&&(configuration.indexOf('.jpg')!=-1||configuration.indexOf('.JPG')!=-1)){
					if (assetsImageDisplay != null && assetsImageDisplay != ''
							&& assetsImageDisplay != 'undefind' && assetsImageDisplay.body != null) {
						assetsImageDisplay.body.update('<img src="' + __ctxPath
								+ '/attachFiles/' + configuration
								+ '"  width="100%" height="100%"/>');
					}
				}else{
					assetsImageDisplay.body.update('<img src="' + __ctxPath
								+ '/images/default_image_car.jpg"  width="100%" height="100%"/>');
				}
				

			},
			failure : function(form, action) {
				Ext.ux.Toast.msg('编辑', '载入失败');
			}
		});
	}
	
	
	var imagePanel=new Ext.Panel({
					id : 'assetsImageDisplay',
					frame:true,
					height : 295,
					columnWidth : .4,
					html : '<img src="'
							+ __ctxPath
							+ '/images/default_image_car.jpg" width="100%" height="100%"/>',
					tbar : new Ext.Toolbar({
						width : '100%',
						height : 30,
						items : [{
							text : '上传',
							iconCls : 'btn-upload',
							handler : function() {
								var photo = Ext.getCmp('configuration');
								var dialog = App.createUploadDialog({
											file_cat : 'assetsConfig',
											callback : uploadCarPhoto,
											permitted_extensions : ['jpg','JPG','jpeg','JPEG','BMP','bmp','PNG','png','GIF','gif']
										});
								if (photo.getValue() != ''
										&& photo.getValue() != null
										&& photo.getValue() != 'undefined') {
									var msg = '再次上传需要先删除原有图片,';
									Ext.Msg.confirm('信息确认', msg + '是否删除？',
											function(btn) {
												if (btn == 'yes') {
													// 删除图片
													Ext.Ajax.request({
														url : __ctxPath
																+ '/system/deleteFileAttach.do',
														method : 'post',
														params : {
															filePath : photo.value
														},
														success : function() {
																dialog.show('queryBtn');
														}
													});
												}
											})
								} else {
									dialog.show('queryBtn');
								}
							}
						}
						 ,
						 {
						 text : '删除',
							iconCls : 'btn-delete',
							handler : function() {
								var photo = Ext.getCmp('configuration');
								if (photo.value != null && photo.value != ''
										&& photo.value != 'undefined') {
									var msg = '照片一旦删除将不可恢复,';
									Ext.Msg.confirm('确认信息', msg + '是否删除?',
											function(btn) {
												if (btn == 'yes') {
													Ext.Ajax.request({
														url : __ctxPath
																+ '/system/deleteFileAttach.do',
														method : 'post',
														params : {
															filePath : photo.value
														},
														success : function() {
															photo.setValue('');
															var display = Ext.getCmp('assetsImageDisplay');
															display.body.update('<img src="'+ __ctxPath+ '/images/default_image_car.jpg" width="100%" height="100%" />');														
														}
													});
												}
											});
								}// end if
								else {
									Ext.ux.Toast.msg('提示信息', '您还未增加照片.');
								}

							}
						}
						]
					})
				});
	
	var mainPanel=new Ext.Panel({
		layout : 'column',
		id:'mainPanel',
		frame:true,
		items:[formPanel,imagePanel]
	});
	function uploadCarPhoto(data) {
		var photo = Ext.getCmp('configuration');
		var display = Ext.getCmp('assetsImageDisplay');
		photo.setValue(data[0].filepath);
		
		display.body.update('<img src="' + __ctxPath + '/attachFiles/'
				+ data[0].filepath + '"  width="100%" height="100%"/>');
	};
	return mainPanel;

};

// 使用记录数据源
FixedAssetsForm.prototype.store = function() {
	var store = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath + '/admin/listAssetsUseinfo.do'
						}),
				reader : new Ext.data.JsonReader({
							root : 'result',
							totalProperty : 'totalCounts',
							id : 'id',
							fields : [{
										name : 'id',
										type : 'int'
									}, {
										name : 'userId',
										mapping : 'appUser.fullname'
									}, {
										name : 'assetsNo',
										mapping : 'fixedAssets.assetsNo'
									}, {
										name : 'assetsName',
										mapping : 'fixedAssets.assetsName'
									}, {
										name : 'sn',
										mapping : 'fixedAssets.sn'
									}, 'useDate', 'useStatus']
						}),
				remoteSort : true,
				baseParams : {
					'Q_fixedAssets.assetsId_L_EQ':this.assetsId == null ? 0 : this.assetsId
				}
			});
	// alert(store);
	store.setDefaultSort('id', 'desc');
	return store;
};


//维修记录数据源
FixedAssetsForm.prototype.store1 = function() {
	var store = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath + '/admin/listAssetsRepair.do'
						}),
				reader : new Ext.data.JsonReader({
							root : 'result',
							totalProperty : 'totalCounts',
							id : 'id',
							fields : [{
										name : 'id',
										type : 'int'
									},{
										name : 'assetsNo',
										mapping : 'fixedAssets.assetsNo'
									}, {
										name : 'assetsName',
										mapping : 'fixedAssets.assetsName'
									}, {
										name : 'sn',
										mapping : 'fixedAssets.sn'
									},  {
										name : 'appUser',
										mapping : 'appUser.fullname'
									},'faultDespriction', 'repairType']
						}),
				remoteSort : true,
				baseParams : {
					'Q_fixedAssets.assetsId_L_EQ':this.assetsId == null ? 0 : this.assetsId
				}
			});
	// alert(store);
	store.setDefaultSort('id', 'desc');
	return store;
};
