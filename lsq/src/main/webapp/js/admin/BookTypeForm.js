var BookTypeForm = function(typeId, ptypeId, ptypeName, isadd) {

	this.typeId = typeId;
	this.ptypeId = ptypeId;
	this.ptypeName = ptypeName;

	var fp = this.setup(isadd);
	var window = new Ext.Window({
		id : 'BookTypeFormWin',
		title : '档案类别详细信息',
		iconCls : 'menu-book-type',
		autoHeight : true,
		width : 500,
		modal : true,
		layout : 'anchor',
		plain : true,
		bodyStyle : 'padding:5px;',
		buttonAlign : 'center',
		items : [fp],
		buttons : [{
			text : '保存',
			iconCls : 'btn-save',
			handler : function() {
				var fp = Ext.getCmp('bookTypeForm');
				if (fp.getForm().isValid()) {
					fp.getForm().submit({
						method : 'post',
						waitMsg : '正在提交数据...',
						success : function(fp, action) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							if (Ext.getCmp('BookTypeGrid') != null) {
								Ext.getCmp('BookTypeGrid').getStore().reload();
							}
							if (Ext.getCmp('leftBookManagePanel') != null) {
								Ext.getCmp('leftBookManagePanel').root.reload();
							}
							window.close();
						},
						failure : function(fp, action) {
							Ext.MessageBox.show({
								title : '操作信息',
								msg : '信息保存出错，请联系管理员！',
								buttons : Ext.MessageBox.OK,
								icon : 'ext-mb-error'
							});
							window.close();
						}
					});
				}
			}
		}, {
			text : '取消',
			iconCls : 'btn-cancel',
			handler : function() {
				window.close();
			}
		}]
	});
	window.show();
};

BookTypeForm.prototype.setup = function(isadd) {

	var formPanel = new Ext.FormPanel({
		url : __ctxPath + '/admin/saveBookType.do',
		layout : 'form',
		id : 'bookTypeForm',
		frame : true,
		defaults : {
			anchor : '95%,95%'
		},
		formId : 'BookTypeFormId',
		defaultType : 'textfield',
		items : [{
			name : 'bookType.typeId',
			id : 'typeId',
			xtype : 'hidden',
			value : this.typeId == null ? '' : this.typeId
		}, {
			name : 'bookType.ptypeId',
			id : 'ptypeId',
			xtype : 'hidden',
			value : this.ptypeId == null ? '' : this.ptypeId
		}, {
			fieldLabel : '档案类别名称',
			name : 'bookType.typeName',
			id : 'typeName',
			allowBlank : false,
			blankText : '档案类别不能为空'
		}, {
			fieldLabel : '父类名称',
			name : 'bookType.path',
			name : 'path',
			readOnly : true,
			value : this.ptypeName == null ? '无父类别' : this.ptypeName
		}, {
			fieldLabel : '档案类别说明',
			name : 'bookType.remark',
			xtype : 'textarea',
			id : 'remark'
		}]
	});

	if (this.typeId != null && this.typeId != 'undefined') {
		formPanel.getForm().load({
			deferredRender : false,
			url : __ctxPath + '/admin/getBookType.do?typeId=' + this.typeId,
			method : 'post',
			waitMsg : '正在载入数据...',
			success : function(form, action) {

				if (isadd) {

					formPanel.get(1).setValue(formPanel.get(0).getValue())
					formPanel.get(0).setValue(null)
					formPanel.get(3).setValue(formPanel.get(2).getValue())
					formPanel.get(2).setValue("")
					formPanel.doLayout();
				}

			},
			failure : function(form, action) {
			}
		});
	}

	return formPanel;
};
