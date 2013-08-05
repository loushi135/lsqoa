var BookBorrowForm = function(recordId, bookId) {
	this.recordId = recordId;
	this.bookId = bookId;
	var fp = this.setup();
	var window = new Ext.Window({
		id : 'BookBorrowFormWin',
		title : '档案借出详细记录',
		iconCls : 'menu-book-borrow',
		width : 520,
		// height : 420,
		autoHeight : true,
		modal : true,
		layout : 'form',
		plain : true,
		bodyStyle : 'padding:5px;',
		buttonAlign : 'center',
		items : [this.setup()],
		buttons : [{
			text : '借出档案',
			iconCls : 'btn-save',
			handler : function() {
				var fp = Ext.getCmp('BookBorrowForm');
				if (fp.getForm().isValid()) {
					fp.getForm().submit({
						method : 'post',
						waitMsg : '正在提交数据...',
						success : function(fp, action) {
							Ext.ux.Toast.msg("操作信息", "成功保存信息！");
							var BookBorrowGrid = Ext.getCmp('BookBorrowGrid');
							if (BookBorrowGrid == null) {
								Ext.getCmp('BookGrid').getStore().reload();
							} else {
								Ext.getCmp('BookBorrowGrid').getStore()
										.reload();
							}

							var BookGrid = Ext.getCmp('BookGrid');
							if (BookGrid != null) {
								BookGrid.getStore().reload();
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

BookBorrowForm.prototype.setup = function() {

	var formPanel = new Ext.FormPanel({
		url : __ctxPath + '/admin/saveBorrowBookBorRet.do',
		layout : 'form',
		id : 'BookBorrowForm',
		frame : true,
		formId : 'BookBorrowFormId',
		defaultType : 'textfield',
		labelWidth:'98',
		items : [{
			name : 'bookBorRet.recordId',
			id : 'recordId',
			xtype : 'hidden',
			value : this.recordId == null ? '' : this.recordId
		}, {
			name : 'bookId',
			id : 'bookId',
			xtype : 'hidden',
			value : this.bookId == null ? '' : this.bookId
		}, {
			name : 'bookBorRet.bookSnId',
			id : 'bookSnId',
			xtype : 'hidden'
		}, {
			name : 'bookBorRet.userId',
			id : 'userId',
			xtype : 'hidden'
		}, {
			xtype : 'container',
			layout : 'column',
			style : 'padding-left:0px;margin-bottom:4px;',
			items : [{
				xtype : 'label',
				text : '借出档案名称:',
				style : 'padding-left:0px;margin-top:2px;',
				width : 100
			}, {
				xtype : 'textfield',
				name : 'bookBorRet.bookName',
				id : 'bookName',
				allowBlank : false,
				blankText : '借出档案名称不能为空',
				readOnly : true,
				width : 200
			}, {
				xtype : 'button',
				text : '选择档案',
				iconCls : 'menu-book-manage',
				handler : function() {
					BookSelector.getView(function(id, name) {
						var bookNameField = Ext.getCmp('bookName');
						bookNameField.setValue(name);
						var store = Ext.getCmp('borrowIsbn').getStore();
						store.reload({
							params : {
								bookId : id
							}
						});
					}, true).show();

				}
			}, {
				xtype : 'button',
				text : ' 清除记录',
				iconCls : 'reset',
				handler : function() {
					var bookNameField = Ext.getCmp('bookName');
					bookNameField.setValue('');
				}
			}]
		}
		, {
			xtype : 'container',
			layout : 'column',
			style : 'padding-left:0px;margin-bottom:4px;',
			//style : 'height:0px;',
			items : [{
				xtype : 'label',
				text : '借出档案的副本:',
				style : 'padding-left:0px;margin-top:2px;',
				width : 100
			}, {
				name : 'bookBorRet.borrowIsbn',
				id : 'borrowIsbn',
				allowBlank : false,
				blankText : '借出档案的ISBN不能为空',
				// readOnly : true,
				width : 200,
				maxHeight : 200,
				xtype : 'combo',
				mode : 'local',
				editable : false,
				triggerAction : 'all',
				emptyText : '请选择档案系列',
				store : new Ext.data.SimpleStore({
					// 借阅时只显示在库的档案标签（只有在库的档案才能选择被借阅）
					url : __ctxPath + '/admin/getBorrowSnBookSn.do',
					fields : ['bookSnId', 'bookSn'],
					listeners : {
						load : function(store, records, options) {// 读取完数据后设定默认值
							// cboType.setValue(data.dataType);
							if (records.length > 0) {
								Ext.getCmp('borrowIsbn')
										.setValue(records[0].data.bookSnId);
								Ext.getCmp('bookSnId')
										.setValue(records[0].data.bookSnId);
							}
						}
					}
				}),
				displayField : 'bookSn',
				valueField : 'bookSnId',
				listeners : {
					select : function(combo, record, index) {
						Ext.getCmp('bookSnId').setValue(combo.value);
					},
					focus : function(combo, record, index) {
						var bookName = Ext.getCmp('bookName').getValue();
						if (bookName == '') {
							Ext.ux.Toast.msg('提示信息', '请先选择档案');
						}
					}
				}
			}]
		}
		, {
			xtype : 'container',
			layout : 'column',
			style : 'padding-left:0px;padding-bottom:3px;',
			items : [{
				xtype : 'label',
				text : '借阅人:',
				width : 100,
				style : 'padding-left:0px;padding-bottom:3px;'
			}, {
				name : 'bookBorRet.fullname',
				id : 'fullname',
				xtype : 'textfield',
				readOnly : true,
				allowBlank : false,
				width : 200
			}, {
				xtype : 'button',
				text : '选择人员',
				iconCls : 'btn-user-sel',
				handler : function() {
					UserSelector.getView(function(id, name) {
						Ext.getCmp('fullname').setValue(name);
						Ext.getCmp('userId').setValue(id);
					}, true).show();
				}
			}, {
				xtype : 'button',
				text : '清除记录',
				iconCls : 'reset',
				handler : function() {
					Ext.getCmp('fullname').setValue('');
				}
			}]
		}, {
			fieldLabel : '归还时间',
			name : 'bookBorRet.returnTime',
			id : 'returnTime',
			xtype : 'datefield',
			format : 'Y-m-d',
			allowBlank : false,
			width : 200,
			blankText : '归还时间不能为空'
		}]
	});

	if (this.recordId != null && this.recordId != 'undefined') {
		formPanel.getForm().load({
			deferredRender : false,
			url : __ctxPath + '/admin/getBookBorRet.do?recordId='
					+ this.recordId,
			// method : 'post',
			waitMsg : '正在载入数据...',
			success : function(form, action) {
				// 对应还日期格式化后再进行输出
				var returnTime = action.result.data.returnTime;
				var returnTimeField = Ext.getCmp('returnTime');
				returnTimeField.setValue(new Date(getDateFromFormat(returnTime,
						"yyyy-MM-dd HH:mm:ss")));
			},
			failure : function(form, action) {
			}
		});
	}

	return formPanel;

};
