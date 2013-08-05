/**
 * @author 
 * @createtime 
 * @class ProjectQuotaForm
 * @extends Ext.Window
 * @description ProjectQuota表单
 */
ProjectQuotaForm=Ext.extend(Ext.Window,{
	//内嵌FormPanel
	formPanel:null,
	mainPanel:null,
	//构造函数
	constructor:function(_cfg){
		Ext.applyIf(this,_cfg);
		//必须先初始化组件
		this.initUIComponents();
		ProjectQuotaForm.superclass.constructor.call(this,{
			id:'ProjectQuotaFormWin',
			layout:'border',
			items:[this.mainPanel,this.formPanel],
			modal:true,
			height:200,
			width:450,
			maximizable:true,
			title:'[垫资审批额度]详细信息',
			buttonAlign : 'center',
			buttons:this.buttons
		});
	},//end of the constructor
	//初始化组件
	initUIComponents:function(){
		this.mainPanel = new Ext.FormPanel({
					layout : 'form',
					region : 'north',
					bodyStyle : 'padding:6px 10px 6px 10px',
					border : false,
					frame : true,
					height : 70,
					items : [ 
							{
								xytpe:'container',
								layout:'column',
								defaults : {
									border : false,
									anchor : '98%,98%',
									xtype:'label',
									style : 'margin-left:5px;margin-top:5px;'
								},
								items:[
									{
										text : '项目名称:'
									}, {
										id:'proNameLabel'
									}, {
										text : '项目编号:'
									}, {
										id:'proNoLabel'
									}
								]
							},{
								xytpe:'container',
								layout:'column',
								defaults : {
									border : false,
									anchor : '98%,98%',
									xtype:'label',
									style : 'margin-left:5px;margin-top:5px;'
								},
								items:[
									 {
										text : '所属区域:'
									}, {
										id : 'areaLabel'
									}, {
										text : '项目负责人:'
									}, {
										id : 'proChargerLabel'
									}
								]
							}]
				});// end of the mainPanel
		this.formPanel=new Ext.FormPanel({
				layout : 'form',
				trackResetOnLoad:true,
				bodyStyle: 'padding:10px 10px 10px 10px',
				border:false,
				region : 'center',
				url : __ctxPath + '/statistics/saveProjectQuota.do',
				id : 'ProjectQuotaForm',
				defaults : {
					anchor : '98%,98%'
				},
				defaultType : 'textfield',
				items : [
							{
								name : 'projectQuota.id',
								xtype : 'hidden',
								id : 'id',
								value : this.id == null ? '' : this.id
							}, {
								name : 'projectQuota.project.id',
								id : 'projectQuota.project.id',
								xtype : 'hidden',
								value : this.proId == null ? '' : this.proId
							}, {
								xtype : 'container',
								layout : 'column',
								items : [{
											xtype : 'label',
											text : '额度:'
										}, {
											name : 'projectQuota.quota',
											id : 'quota',
											xtype : 'numberfield',
											width : 120,
											maxLength : 9,
											regex:/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/,
											regexText:'请输入正确格式的金额'
										}]
							}
						]
			});
		this.initData();
		//初始化功能按钮
		this.buttons=[{
				text : '保存',
				iconCls : 'btn-save',
				handler :this.save.createCallback(this.formPanel,this),
				hidden:!isGranted("_ProjectQuotaEdit")
			}, {
				text : '重置',
				iconCls : 'btn-reset',
				handler :this.reset.createCallback(this.formPanel),
				hidden:!isGranted("_ProjectQuotaEdit")
			},{
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
	},//end of the initcomponents
	initData:function(){
		if (this.proId != null && this.proId != 'undefined') {
			this.mainPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getProjectNew.do?id='+ this.proId,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
						var jsonData = Ext.util.JSON.decode(action.response.responseText);
						var res = jsonData.data;
						Ext.getCmp("proNameLabel").setText("<font color='green'>"+res.proName+"</font>",false);
						Ext.getCmp("proNoLabel").setText("<font color='green'>"+res.proNo+"</font>",false);
						Ext.getCmp("areaLabel").setText("<font color='green'>"+res.area+"</font>",false);
						var proCharger = "";
						if(!Ext.isEmpty(res.proChargerUser)){
							proCharger = res.proChargerUser.fullname;
						}
						Ext.getCmp("proChargerLabel").setText("<font color='green'>"+proCharger+"</font>",false);
				},
				failure : function(form, action) {
				}
			});
			Ext.getCmp("projectQuota.project.id").setValue(this.proId);
			Ext.getCmp("projectQuota.project.id").originalValue=this.proId;
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/statistics/getProjectQuota.do?proId='+ this.proId,
				waitMsg : '正在载入数据...',
				success : function(form, action) {
				},
				failure : function(form, action) {
				}
			});
		}
	},
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
					var gridPanel=Ext.getCmp('ProjectQuotaGrid');
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