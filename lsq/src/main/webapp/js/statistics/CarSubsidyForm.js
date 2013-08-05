/**
 * @author 
 * @createtime 
 * @class CarSubsidyForm
 * @extends Ext.Window
 * @description CarSubsidy表单
 */
CarSubsidyForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		CarSubsidyForm.superclass.constructor.call(this,{
			id:'CarSubsidyFormWin',
			layout:'fit',
			items:this.formPanel,
			modal:true,
			width : 670,
			height : 320,
			maximizable:true,
			title:'车辆补贴详细信息',
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
				url : __ctxPath + '/statistics/saveCarSubsidy.do',
				id : 'CarSubsidyForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [{ 
							xtype:'hidden',		
							name:'carSubsidy.processRunId',
							value:-1
						 },{
							name : 'carSubsidy.id',
							id : 'id',
							xtype:'hidden',
							value : this.id == null ? '' : this.id
						},{
								xtype: 'container',
								layout:'column',
								defaults:{
									xtype:'label',
									style:{
										margin:'0px 0px 3px 0px'
									}
								},
								items:[
									{
										text:'报告人：',
										columnWidth:0.15
									},
									{
										xtype:'textfield',
										id:'reporter',
										name:'carSubsidy.reporter',
										maxLength : 64,
										allowBlank :false,
										value:__currentUser,
										columnWidth:0.3
									},
									{
										text:'所属部门：',
										style:'margin-left:10px;',
										columnWidth:0.15
									},
									{
										xtype:'hidden',
										id:'carDeptId',
										name:'carSubsidy.dept.depId'
									},
									{
										xtype:'textfield',
										id:'carDeptName',
										emptyText:'请选择部门',
										maxLength : 64,
										columnWidth:0.4,
										allowBlank :false,
										listeners:{
											focus:function(){
												DepSelector.getView(function(deptIds, deptNames) {
													Ext.getCmp("carDeptId").setValue(deptIds);
													Ext.getCmp("carDeptName").setValue(deptNames);
												},true).show();
											}
										}
									}
								]
							},{
								xtype : 'container',
								layout : 'column',
								defaults:{
									xtype:'label',
									style:{
										margin:'0px 0px 3px 0px'
									}
								},
								items : [ {
											text : '所在公司：',
											columnWidth:0.15
										}, {
											xtype : 'textfield',
											id:'companyName',
											name:'carSubsidy.companyName',
											value:__companyName,
											allowBlank : false,
//											readOnly:true,
											columnWidth : 0.85
										}]
						   },{
								xtype: 'container',
								layout:'column',
								defaults:{
									xtype:'label',
									style:{
										margin:'0px 0px 3px 0px'
									}
								},
								items:[
									{
										text:'车牌号：',
										columnWidth:0.15
									},
									{
										xtype:'textfield',
										id:'carNo',
										name:'carSubsidy.carNo',
										maxLength : 64,
										allowBlank :false,
										columnWidth:0.3
									},
									{
										text:'品牌型号：',
										style:'margin-left:10px;',
										columnWidth:0.15
									},
									{
										xtype:'textfield',
										id:'brand',
										name:'carSubsidy.brand',
										maxLength : 64,
										columnWidth:0.4,
										allowBlank :false
									}
								]
							},{
								xtype: 'container',
								layout:'column',
								defaults:{
									xtype:'label',
									style:{
										margin:'0px 0px 3px 0px'
									}
								},
								items:[
									{
										text:'排量：',
										columnWidth:0.15
									},
									{
										xtype:'textfield',
										id:'displacement',
										name:'carSubsidy.displacement',
										maxLength : 64,
										allowBlank :false,
										columnWidth:0.3
									},
									{
										text:'购置年份：',
										style:'margin-left:10px;',
										columnWidth:0.15
									},
									{
										xtype:'textfield',
										id:'boughtYear',
										name:'carSubsidy.boughtYear',
										maxLength : 64,
										allowBlank :false,
										columnWidth:0.4
									}
								]
							},{
								xtype : 'container',
								layout : 'column',
								defaults:{
									xtype:'label',
									style:{
										margin:'0px 0px 3px 0px'
									}
								},
								items : [ {
											text : '申请原因：',
											columnWidth:0.15
										}, {
											xtype : 'textarea',
											id:'subsidyReason',
											name:'carSubsidy.subsidyReason',
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
											id : 'carSubsidy.attachIds'
										}, {
											xtype : 'hidden',
											name : 'attachFiles',
											id : 'carSubsidy.attachFiles'
										}, {
											xtype : 'panel',
											id : 'carSubsidy.displayAttach',
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
													file_cat : 'flow/carSubsidy',
													callback : function(data) {
														var attachFiles = Ext.getCmp("carSubsidy.attachFiles");
														var fileIds = Ext.getCmp('carSubsidy.attachIds');
														var display = Ext.getCmp('carSubsidy.displayAttach');
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
													permitted_max_size : 1024 * 1024 * 20,
													permitted_extensions_size : [{'type':['jpg','png','gif','bmp','jpeg'],'MaxSize':1024*800}]
												});
												dialog.show(this);
											}
										}]
							}
							]
			});
		//加载表单对应的数据	
		if (this.id != null && this.id != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getCarSubsidy.do?id='+ this.id,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var res = Ext.util.JSON.decode(action.response.responseText).data;
					if(res.dept!=null){
						Ext.getCmp("carDeptId").setValue(res.dept.depId);
						Ext.getCmp("carDeptId").originalValue = res.dept.depId;
						Ext.getCmp("carDeptName").setValue(res.dept.depName);
						Ext.getCmp("carDeptName").originalValue = res.dept.depName;
					}
					var af = res.fileAttachs;
					var filePanel = Ext.getCmp('carSubsidy.displayAttach');
					var fileIds = Ext.getCmp("carSubsidy.attachIds");
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
//				hidden:true,
				handler :this.save.createCallback(this.formPanel,this)
			}, {
				text : '重置',
				iconCls : 'btn-reset',
//				hidden:true,
				handler :this.reset.createCallback(this.formPanel)
			},{
				text : '关闭',
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
					var gridPanel=Ext.getCmp('CarSubsidyGrid');
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
					window.close();
				}
			});
		}
	}//end of save
	
});