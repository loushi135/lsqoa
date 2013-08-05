/**
 * @author 
 * @createtime 
 * @class SuppliersAssessForm
 * @extends Ext.Window
 * @description SuppliersAssess表单
 */
SuppliersAssessForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		SuppliersAssessForm.superclass.constructor.call(this,{
			id:'SuppliersAssessFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			width : 700,
			height : 500,
			frame:true,
			title:'供应商详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				bodyStyle: 'padding:10px 10px 10px 10px',
				autoScroll : true, 
				border:false,
				url : __ctxPath + '/customer/saveSuppliersAssess.do',
				id : 'SuppliersAssessForm',
				defaults : {
					anchor : '96%,96%'
				},
				defaultType : 'textfield',
				items : [{
							name : 'suppliersAssess.suppliersId',
							id : 'suppliersId',
							xtype:'hidden',
							value : this.suppliersId == null ? '' : this.suppliersId
						},{
					xtype : 'container',
					layout : 'column',
					height : 25,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '公司名称：',
								columnWidth : 0.15
							}, {
								xtype : 'textfield',
								id : 'suppliersName',
								name : 'suppliersAssess.suppliersName',
								allowBlank : false,
								columnWidth : 0.85,
								listeners:{
									blur:function(field){
										var suppliersName = field.getValue();
										if(!Ext.isEmpty(suppliersName)){
											var url = __ctxPath + '/customer/checkSuppliersNameSuppliersAssess.do';
											var params = "suppliersName="+encodeURI(suppliersName);
											var data = ajaxSyncCall(url,params);
											if(!data.success){
												Ext.ux.Toast.msg('操作信息', '信息修改，公司名称为:<font color="red">'+field.getValue()+'</font>的供应商已经存在，请修改公司名称！');
												field.setValue('');
											}
										}
									}
								}
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					height : 25,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '合作类型：',
								columnWidth : 0.15
							}, {
								xtype : 'combo',
								id : 'cooperateType',
								name : 'suppliersAssess.cooperateType',
								allowBlank : false,
								columnWidth : 0.85,
								editable : false,
								triggerAction : 'all',
								mode : 'local',
								store : ['战略','长期','临时','待考察'],
								listeners:{
									select:function(field){
									      var cooperateType = field.getValue();
									      if(cooperateType=='临时'){
									      	 Ext.getCmp('suppliersAssess.projectContainer').show();
									      	 Ext.getCmp('suppliersAssess.project.proName').allowBlank=false;
									      }else{
									      	 Ext.getCmp('suppliersAssess.projectContainer').hide();
									      	 Ext.getCmp('suppliersAssess.project.proName').setValue('');
									      	 Ext.getCmp('suppliersAssess.project.proName').allowBlank=true;
									      	 Ext.getCmp('suppliersAssess.project.id').setValue('');
									      }
									}
								}
							}]
				}, {
					xtype : "container",
					layout : 'column',
					height : 25,
					id:'suppliersAssess.projectContainer',
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '项目名称：',
								columnWidth : .15
							},{
								xtype : 'textfield',
								name : 'suppliersAssess.project.proName',
								id:'suppliersAssess.project.proName',
								columnWidth : .85,
								emptyText:'请选择项目',
								listeners:{
									'focus':function(){
										ProjectNewSelector.getView(function(proId,proName,proNo,contractId,contractNo,contractAmount,proAreaId,proArea){
											Ext.getCmp("suppliersAssess.project.id").setValue(proId);
											Ext.getCmp("suppliersAssess.project.proName").setValue(proName);
										},true,true).show();
									}
								}
							},{
								xtype : 'hidden',
								name : 'suppliersAssess.project.id',
								id:'suppliersAssess.project.id',
								listeners:{
									afterrender:function(){
										Ext.getCmp('suppliersAssess.projectContainer').doLayout();
										Ext.getCmp('suppliersAssess.projectContainer').hide();
									}
								}
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					height : 25,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '供应商性质：',
								columnWidth : 0.15
							}, {
								xtype : 'combo',
								id : 'suppliersNature',
								name : 'suppliersAssess.suppliersNature',
								allowBlank : false,
								columnWidth : 0.85,
								editable : false,
								triggerAction : 'all',
								mode : 'local',
								store : ['品牌公司', '总代理', '省级代理', '市级代理', '经销商']
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					height : 35,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '主要产品/业务范围：',
								columnWidth : 0.15
							}, {
								xtype : 'textfield',
								id : 'mainProduct',
								name : 'suppliersAssess.mainProduct',
								allowBlank : false,
								columnWidth : 0.85,
								maxLength : 64
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					height : 25,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '注册地址：',
								columnWidth : .15
							}, {
								xtype : 'textfield',
								id : 'supplierAddress',
								name : 'suppliersAssess.supplierAddress',
								allowBlank : false,
								columnWidth : .3,
								maxLength : 64
							}, {
								text : '法人代表：',
								style : 'margin-left:10px;',
								columnWidth : .13
							}, {
								xtype : 'textfield',
								id : 'legalRepresentative',
								name : 'suppliersAssess.legalRepresentative',
								allowBlank : false,
								maxLength : 64,
								columnWidth : .15
							}, {
								text : '注册资金：',
								style : 'margin-left:10px;',
								columnWidth : .12
							}, {
								xtype : 'textfield',
								id : 'registeredCapital',
								name : 'suppliersAssess.registeredCapital',
								allowBlank : false,
								maxLength : 64,
								columnWidth : .15
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					height : 25,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '公司创立日期：',
								columnWidth : .15
							}, {
								xtype : 'textfield',
								id : 'establishDate',
								name : 'suppliersAssess.establishDate',
								allowBlank : false,
								columnWidth : .3,
								maxLength : 64
							}, {
								text : '业务联系人：',
								style : 'margin-left:10px;',
								columnWidth : .13
							}, {
								xtype : 'textfield',
								id : 'supplierContacter',
								name : 'suppliersAssess.supplierContacter',
								allowBlank : false,
								maxLength : 64,
								columnWidth : .15
							}, {
								text : '联系电话：',
								style : 'margin-left:10px;',
								columnWidth : .12
							}, {
								xtype : 'textfield',
								id : 'supplierPhone',
								name : 'suppliersAssess.supplierPhone',
								allowBlank : false,
								maxLength : 64,
								columnWidth : .15
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					height : 25,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '公司所有制：',
								columnWidth : 0.15
							}, {
								xtype : 'combo',
								id : 'companyNature',
								name : 'suppliersAssess.companyNature',
								allowBlank : false,
								columnWidth : 0.85,
								editable : false,
								triggerAction : 'all',
								mode : 'local',
								store : ['国企', '私企', '外资', '其他']
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					height : 25,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '开户银行：',
								columnWidth : .15
							}, {
								xtype : 'textfield',
								id : 'suppliersbank',
								name : 'suppliersAssess.suppliersbank',
								allowBlank : false,
								columnWidth : .3,
								maxLength : 64
							}, {
								text : '开户账号：',
								style : 'margin-left:10px;',
								columnWidth : .13
							}, {
								xtype : 'textfield',
								id : 'bankAccount',
								name : 'suppliersAssess.bankAccount',
								allowBlank : false,
								maxLength : 64,
								columnWidth : .42
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					height : 25,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '雇员总人数：',
								columnWidth : .16
							}, {
								xtype : 'numberfield',
								id : 'staffNum',
								name : 'suppliersAssess.staffNum',
								allowBlank : false,
								columnWidth : .12,
								maxLength : 9
							}, {
								text : '人'
							}, {
								text : '管理人员：',
								style : 'margin-left:10px;',
								columnWidth : .13
							}, {
								xtype : 'numberfield',
								id : 'managerNum',
								name : 'suppliersAssess.managerNum',
								allowBlank : false,
								columnWidth : .12,
								maxLength : 9
							}, {
								text : '人'
							}, {
								text : '营销人员：',
								style : 'margin-left:10px;',
								columnWidth : .12
							}, {
								xtype : 'numberfield',
								id : 'salesNum',
								name : 'suppliersAssess.salesNum',
								allowBlank : false,
								columnWidth : .12,
								maxLength : 9
							}, {
								text : '人'
							}, {
								text : '技术人员：',
								style : 'margin-left:10px;',
								columnWidth : .12
							}, {
								xtype : 'numberfield',
								id : 'techNum',
								name : 'suppliersAssess.techNum',
								allowBlank : false,
								columnWidth : .12,
								maxLength : 9
							}, {
								text : '人'
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					height : 25,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '机构性质：',
								columnWidth : 0.15
							}, {
								xtype : 'combo',
								id : 'institutionNature',
								name : 'suppliersAssess.institutionNature',
								allowBlank : false,
								columnWidth : 0.85,
								editable : false,
								triggerAction : 'all',
								mode : 'local',
								store : ['总公司', '子公司', '分公司']
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					height : 80,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '营业范围：',
								columnWidth : 0.15
							}, {
								xtype : 'textarea',
								id : 'businessArea',
								name : 'suppliersAssess.businessArea',
								allowBlank : false,
								columnWidth : 0.85
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					height : 35,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '经营(代理) 品牌：',
								columnWidth : .15
							}, {
								xtype : 'textfield',
								id : 'brand',
								name : 'suppliersAssess.brand',
								allowBlank : false,
								columnWidth : .42,
								maxLength : 64
							}, {
								text : '系统类别：',
								style : 'margin-left:10px;',
								columnWidth : .13
							}, {
								xtype : 'combo',
								id : 'systemType',
								name : 'suppliersAssess.systemType',
								allowBlank : false,
								maxLength : 64,
								columnWidth : .3,
								editable : false,
								triggerAction : 'all',
								mode : 'local',
								store : [],
								listeners : {
									'afterrender' : function(combo) {
										var _store = Ext.getCmp('systemType').getStore();
										if (_store.getCount() <= 0) {
											Ext.Ajax.request({
												url : __ctxPath + '/system/loadDictionary.do',
												method : 'post',
												params : {
													itemName : '系统类别'
												},
												success : function(response) {
													var result = Ext.util.JSON.decode(response.responseText);
													_store.loadData(result);
												}
											});
										}
									}
								}
							}]
				}, {
					xtype : 'container',
					layout : 'column',
					height : 25,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '所在省市：',
								columnWidth : 0.15
							}, {
								xtype : 'textfield',
								id : 'provinceCity',
								name : 'provinceCity',
								allowBlank : false,
								columnWidth : 0.85,
								emptyText:'请选择省市',
								editable : false,
								listeners : {
									'focus' : function() {
										CitySelector.getView(function(cityId, cityName, provinceId, provinceName) {
											if(!Ext.isEmpty(cityId)&&!Ext.isEmpty(provinceId)){
												Ext.getCmp('provinceCity').setValue(provinceName + '/' + cityName);
												Ext.getCmp('suppliersAssess.province.provinceId').setValue(provinceId);
												Ext.getCmp('suppliersAssess.city.cityId').setValue(cityId);
											}
										}, true).show()
									}
								}
							},
							{
								xtype:'hidden',
								name:'suppliersAssess.province.provinceId',
								id:'suppliersAssess.province.provinceId'
							},
							{
								xtype:'hidden',
								name:'suppliersAssess.city.cityId',
								id:'suppliersAssess.city.cityId'
							},
							{
								xtype:'hidden',
								name:'suppliersAssess.delFlag',
								id:'delFlag',
								value:0
							},
							{
								xtype:'hidden',
								name:'suppliersAssess.suppliersNo',
								id:'suppliersNo'
							},
							{
								xtype:'hidden',
								name:'suppliersAssess.type',
								id:'type',
								value:0
							}
							]
				}, {
					xtype : 'container',
					layout : 'column',
					height : 80,
					defaults : {
						xtype : 'label',
						style : {
							margin : '0px 0px 3px 0px'
						}
					},
					items : [{
								text : '推荐原因：',
								columnWidth : 0.15
							}, {
								xtype : 'textarea',
								id : 'recommendReason',
								name : 'suppliersAssess.recommendReason',
								allowBlank : false,
								columnWidth : 0.85
							}]
				}, {
					xtype : 'container',
					autoHeight : true,
					layout : 'column',
					autoWidth : true,
					defaultType : 'label',
					style : 'padding-left:0px;padding-bottom:5px;',
					items : [{
								text : '附件:',
								width : 85,
								style : "margin-top:15px;"
							}, {
								xtype : 'hidden',
								name : 'attachIds',
								id : 'suppliersAssess.attachIds'
							}, {
								xtype : 'hidden',
								name : 'attachFiles',
								id : 'suppliersAssess.attachFiles'
							}, {
								xtype : 'panel',
								id : 'suppliersAssess.displayAttach',
								columnWidth : .95,
								height : 65,
								frame : false,
								autoScroll : true,
								style : 'padding-left:10px;padding-top:0px;',
								html : ''
							}, {
								xtype : 'button',
								iconCls : 'btn-upload',
								text : '上传',
								handler : function() {
									var dialog = App.createUploadDialog({
										file_cat : 'flow/suppliersAssess',
										callback : function(data) {
											var attachFiles = Ext.getCmp("suppliersAssess.attachFiles");
											var fileIds = Ext.getCmp('suppliersAssess.attachIds');
											var display = Ext.getCmp('suppliersAssess.displayAttach');
											display.body.update('');
											attachFiles.setValue('');
											fileIds.setValue('');
											for (var i = 0; i < data.length; i++) {
												if (fileIds.getValue() != '') {
													fileIds.setValue(fileIds.getValue() + ',');
													attachFiles.setValue(attachFiles.getValue() + ',');
												}
												attachFiles.setValue(attachFiles.getValue() + data[i].filepath + ":" + data[i].filename.replace(/\s+/g, ""));
												fileIds.setValue(fileIds.getValue() + data[i].fileId);
												Ext.DomHelper.append(display.body, '<span><a href="#" onclick="FileAttachDetail.show(' + data[i].fileId + ')">'
												+ data[i].filename.replace(/\s+/g, "") + '</a>&nbsp;|&nbsp;</span>');
											}
										},
										permitted_max_size : 1024 * 1024 * 20
									});
									dialog.show(this);
								}
							}]
				}
				]
			});
		//加载表单对应的数据	
		if (this.suppliersId != null && this.suppliersId != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/customer/getSuppliersAssess.do?suppliersId='+ this.suppliersId,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var result = Ext.util.JSON.decode(action.response.responseText);
					var res = result.data;
					if(res.province!=null&&res.city!=null){
						Ext.getCmp("provinceCity").setValue(res.province.provinceName+"/"+res.city.cityName);
						Ext.getCmp("provinceCity").originalValue = res.province.provinceName+"/"+res.city.cityName;
						Ext.getCmp("suppliersAssess.province.provinceId").setValue(res.province.provinceId);
						Ext.getCmp("suppliersAssess.province.provinceId").originalValue = res.province.provinceId;
						Ext.getCmp("suppliersAssess.city.cityId").setValue(res.city.cityId);
						Ext.getCmp("suppliersAssess.city.cityId").originalValue = res.city.cityId;
					}
					Ext.getCmp('suppliersName').el.dom.readOnly = true;
					Ext.getCmp('suppliersName').purgeListeners();
					if(!Ext.isEmpty(res.project)){
						Ext.getCmp('suppliersAssess.projectContainer').show();
						Ext.getCmp("suppliersAssess.project.proName").setValue(res.project.proName);
						Ext.getCmp("suppliersAssess.project.proName").originalValue = res.project.proName;
						Ext.getCmp("suppliersAssess.project.id").setValue(res.project.id);
						Ext.getCmp("suppliersAssess.project.id").originalValue = res.project.id;
					}
					var af = res.fileAttachs;
					var filePanel = Ext.getCmp('suppliersAssess.displayAttach');
					var fileIds = Ext.getCmp("suppliersAssess.attachIds");
					for (var i = 0; i < af.length; i++) {
						if (fileIds.getValue() != '') {
							fileIds.setValue(fileIds.getValue() + ',');
						}
						fileIds.setValue(fileIds.getValue() + af[i].fileId);
						Ext.DomHelper
								.append(
										filePanel.body,
										'<span><a href="#" onclick="FileAttachDetail.show('
												+ af[i].fileId
												+ ')">'
												+ af[i].fileName.replace(/\s+/g,"")+'</a>&nbsp;|&nbsp;</span>');
					}
					fileIds.originalValue = fileIds.getValue();
				},
				failure : function(form, action) {
				}
			});
		}
		//初始化功能按钮
		this.buttons=[{
				text : '保存',
				iconCls : 'btn-save',
				hidden:!(isGranted("_SuppliersAssessAdd")||isGranted("_SuppliersAssessEdit")) ,
				handler :this.save.createCallback(this.formPanel,this)
			}
//			, {
//				text : '重置',
//				iconCls : 'btn-reset',
//				handler :this.reset.createCallback(this.formPanel)
//			}
			,{
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	
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
					var gridPanel=Ext.getCmp('SuppliersAssessGrid');
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
//					window.close();
				}
			});
		}
	},//end of save
	getCheckBoxInputValue : function(chkComponent){
		if(chkComponent.getValue()){
			return chkComponent.inputValue
		}else{
			return '0';
		}
	}
});