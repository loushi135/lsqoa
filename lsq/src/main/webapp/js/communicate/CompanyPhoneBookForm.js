CompanyPhoneBookForm = Ext.extend(Ext.Window,{
	formPanel : null,
	photoPanel : null,
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		CompanyPhoneBookForm.superclass.constructor.call(this, {
					id : 'CompanyPhoneBookForm',
					layout : 'border',
					items : [this.photoPanel,this.formPanel],
					modal : true,
					width:450,
					height:300,
					maximizable : true,
					title : '通讯录详细信息',
					buttonAlign : 'center',
					buttons : this.buttons
				});
	},// end of the constructor
	initUIComponents : function() {
		var thiz = this;
		this.photoPanel = new Ext.Panel({
			id : 'photoPanelId',
			width : 175,
			region : 'west',
			border : false,
			html : ''
		})
		this.formPanel = new Ext.FormPanel({
			region : 'center',
			layout:"form",
			bodyStyle : 'padding:10px 10px 10px 10px',
			width:650,
			height:465,
			border : false,
			labelWidth : 60,
			defaults : {
				anchor : '98%,98%'
			},
			defaultType : 'textfield',
			items :[
				{
					fieldLabel : "姓名",
					id : 'fullname',
					readOnly : true
				},{
					fieldLabel : "直线",
					id : 'phone',
					readOnly : true
				},{
					fieldLabel : "分机",
					id : 'fax',
					readOnly : true
				},{
					fieldLabel : "手机",
					id : 'mobile',
					readOnly : true
				},{
					fieldLabel : "手机2",
					id : 'mobile2',
					readOnly : true
				},{
					fieldLabel : "家庭住址",
					id : 'address',
					readOnly : true
				},{
					fieldLabel : "邮编",
					id : 'zip',
					readOnly : true
				},{
					fieldLabel : "E-mail",
					id : 'email',
					readOnly : true
				}
			]
		});
		if (this.userId != null && this.userId != 'undefined' && this.userId != '') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + "/system/getAppUser.do",
				params : {
					userId : this.userId
				},
				waitMsg : '正在载入数据...',
				success : function(form, action) {
					var res = Ext.util.JSON.decode(action.response.responseText).data[0];
					Ext.getCmp("fullname").setValue(res.fullname);
					Ext.getCmp("phone").setValue(res.phone);
					Ext.getCmp("fax").setValue(res.fax);
					Ext.getCmp("mobile").setValue(res.mobile);
					Ext.getCmp("mobile2").setValue(res.mobile2);
					Ext.getCmp("address").setValue(res.address);
					Ext.getCmp("zip").setValue(res.zip);
					Ext.getCmp("email").setValue(res.businessEmail);
					if(res.photo != ''){
						Ext.getCmp("photoPanelId").body.update('<img src="' + __ctxPath
								+ '/attachFiles/' + res.photo
								+ '" width="100%" height="100%"/>')
					}else{
						if(res.title == 1){
							Ext.getCmp("photoPanelId").body.update('<img src="' + __ctxPath
								+ '/images/default_image_male.jpg' + '" width="100%" height="100%"/>')
						}else{
							Ext.getCmp("photoPanelId").body.update('<img src="' + __ctxPath
								+ '/images/default_image_female.jpg' + '" width="100%" height="100%"/>')
						}
					}
				},
				failure : function(form, action) {
				}
			});
		}
		this.buttons = [
			{
				text : '关闭',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}
		];
	},
	cancel : function(window) {
		window.close();
	}
})