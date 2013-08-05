var BookForm = function(bookId) {
	this.bookId = bookId;
	var fp = this.setup();
	var window = new Ext.Window({
		id : 'BookFormWin',
		title : '档案详细信息',
		iconCls : 'menu-book-manage',
		width : 500,
		height : 580,
		shadow : false,
		modal : true,
		layout : 'anchor',
		plain : true,
		bodyStyle : 'padding:5px;',
		buttonAlign : 'center',
		items : [this.setup()],
		buttons : [{
					text : '保存',
					iconCls : 'btn-save',
					handler : function() {
						var fp = Ext.getCmp('BookForm');
						if (fp.getForm().isValid()) {
							fp.getForm().submit({
										method : 'post',
										waitMsg : '正在提交数据...',
										success : function(fp, action) {
											Ext.ux.Toast.msg("操作信息", "成功保存信息！");
											Ext.getCmp('BookGrid').getStore().reload();
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

BookForm.prototype.setup = function() {
	// 获取图书类别下位选框
	var _url = __ctxPath + '/admin/treeBookType.do?method=1';
	var bookTypeSelector = new TreeSelector('bookTypeSelect', _url, '资料分类','typeId',false,123);
	

	var formPanel = new Ext.FormPanel({
		url : __ctxPath + '/admin/saveBook.do',
		layout : 'form',
		id : 'BookForm',
		frame : true,
		height : 800,
		labelWidth:80,
		defaults : {
			anchor : '100%,100%'
		},
		formId : 'BookFormId',
		defaultType : 'textfield',
		items : [{
					name : 'book.bookId',
					id : 'bookId',
					xtype : 'hidden',
					value : this.bookId == null ? '' : this.bookId
				}, {
					name : 'book.typeId',
					id : 'typeId',
					xtype : 'hidden'
				}, {
					name : 'book.projectId',
					id : 'projectId',
					xtype : 'hidden'
				}, {
					xtype : 'label'
				}, {
					xtype : 'container',
					layout : 'column',
					style : 'padding-left:0px;margin-left:0px;margin-bottom:4px;',
					defaultType : 'textfield',
					height : 26,
					items : [{
						xtype : 'label',
						text : '资料编号：',
						style : 'padding-left:0px;margin-left:0px;margin-bottom:4px;',
						width : 85
					}, {
						name : 'book.isbn',
						id : 'isbn',
//						readOnly : true,
						allowBlank : false,// 不允许为空
						blankText : '编号不能为空'
					}, {
						xtype : 'label',
						text : '老编号：',
						style : 'padding-left:0px;margin-left:30px;margin-bottom:4px;',
						width : 85
					}, {
						name : 'book.sn',
						id : 'sn',
						// readOnly : true,
						allowBlank : true,// 不允许为空
						blankText : '老编号不能为空'
					}]
				}, {
					xtype : 'container',
					layout : 'column',
					style : 'padding-left:0px;margin-left:0px;margin-bottom:4px;',
					defaultType : 'textfield',
					height : 26,
					items : [{
						xtype : 'label',
						text : '资料名称：',
						style : 'padding-left:0px;margin-left:0px;margin-bottom:4px;',
						width : 85
					}, {
						name : 'book.bookName',
						id : 'bookName',
						allowBlank : false,// 不允许为空
						blankText : '资料名称不能为空'
					}, {
						xtype : 'label',
						text : '归档期限：',
						style : 'padding-left:0px;margin-left:30px;margin-bottom:4px;',
						width : 85
					}, {
						name : 'book.fileDate',
						id : 'fileDate',
						xtype : "datefield",
						width:125,
						format : "Y-m-d",
						editable : false
					}]
				}, {
					xtype : 'container',
					layout : 'column',
					style : 'padding-left:0px;margin-left:0px;margin-bottom:4px;',
					defaultType : 'textfield',
					height : 26,
					items : [{
						xtype : 'label',
						text : '资料所属部门：',
						style : 'padding-left:0px;margin-left:0px;margin-bottom:4px;',
						width : 85
					}, {
						name : 'book.department',
						id : 'department',
						allowBlank : true,// 不允许为空
						blankText : '所属部门不能为空',
						width : 106
					}, {
						xtype : 'button',
						text : '',
						iconCls : 'btn-select',
						width : 20,
						// 部门选择器
						handler : function() {
							DepSelector.getView(function(ids, names) {
										var department = Ext.getCmp('department');
										department.setValue(names);
									}, true).show();// true表示单选，因为一本书只能属于一个部门
						}
					}, {
						xtype : 'label',
						text : '保管期限：',
						style : 'padding-left:0px;margin-left:30px;margin-bottom:4px;',
						width : 85
					}, {
						name : 'book.expirationDate',
						id : 'expirationDate',
						xtype : "datefield",
						width:125,
						format : "Y-m-d",
						allowBlank : false,// 不允许为空
						editable : false
					}]
				},{
						xtype : 'container',
						layout : 'column',
						style : 'padding-left:0px;margin-left:0px;margin-bottom:4px;',
						defaultType : 'textfield',
						height : 26,
						items : [{
									xtype : 'label',
									text : '资料分类：',
									style : 'padding-left:0px;margin-left:0px;margin-bottom:4px;',
									width : 85
								}, bookTypeSelector, {
									xtype : 'label',
									text : '保密级别：',
									style : 'padding-left:0px;margin-left:30px;margin-bottom:4px;',
									width : 85
								}, {
									id : 'secretLevel',
									hiddenName : "book.secretLevel",
									xtype : "combo",
									editable : false,
									allowBlank : false,// 不允许为空
									value : 0,
									width:125,
									anchor : "44%",
									triggerAction : "all",
									mode : "local",
									displayField : "text",
									valueField : "value",
									store : new Ext.data.SimpleStore({
												fields : ["value", "text"],
												data : [[0, "一般"], [1, "机密"], [2, "绝密"]]
											})
								}]
					}, {
						xtype : 'container',
						layout : 'column',
						style : 'padding-left:0px;margin-left:0px;margin-bottom:4px;',
						defaultType : 'textfield',
						height : 26,
						items : [{
									xtype : 'label',
									text : '项目：',
									style : 'padding-left:0px;margin-left:0px;margin-bottom:4px;',
									width : 85
								}, {
									fieldLabel : '项目',
									name : 'book.projectName',
									id : 'projectName',
									allowBlank : true,// 不允许为空
									width : 106
								}, {
									xtype : 'button',
									text : '',
									iconCls : 'btn-select',
									width : 20,
									// 部门选择器
									handler : function() {
										ProjectNewSelector.getView(function(proId,proName,proNo,contractId,contractNo,contractAmount,proAreaId,proArea){
											Ext.getCmp("projectId").setValue(proId);
											Ext.getCmp("projectName").setValue(proName);
											
										},true,true).show();
									}
								}, {
									xtype : 'label',
									text : '正副本：',
									style : 'padding-left:0px;margin-left:30px;margin-bottom:4px;',
									width : 85
								}, {
									id : 'pcFile',
									hiddenName : "book.pcFile",
									xtype : "combo",
									editable : false,
									allowBlank : false,// 不允许为空
									width : 125,
									value : true,
									triggerAction : "all",
									mode : "local",
									displayField : "text",
									valueField : "value",
									store : new Ext.data.SimpleStore({
												fields : ["value", "text"],
												data : [[true, "正本"], [false, "副本"]]
											}),
									listeners : {
										'select' : function(combo, record, index) {
											var pcFile_up_button = Ext.getCmp('pcFile_up_button');
											pcFile_up_button.disable();
											if (record.data.value == 1) {
												pcFile_up_button.enable();
											}
										}
									}
				
								}]
					}, {
						xtype : 'container',
						layout : 'column',
						style : 'padding-left:0px;margin-left:0px;margin-bottom:4px;',
						defaultType : 'textfield',
						height : 26,
						items : [{
									xtype : 'label',
									text : '总份数:',
									style : 'padding-left:0px;margin-left:0px;margin-bottom:2px;',
									width : 85
								}, {
									name : 'book.amount',
									id : 'amount',
									xtype : 'numberfield',// 数量只能输入数字
									allowDecimals : false,// 只允许输入整数
									nanText : '只能输入数字',
									allowBlank : false,// 不允许为空
									blankText : '数量不能为空',
									minValue : 1,
									minText : '图书数量必须大于0'
								}, {
									xtype : 'label',
									text : '资料制成日期：',
									style : 'padding-left:0px;margin-left:30px;margin-bottom:4px;',
									width : 85
								}, {
									name : 'book.makeFileDate',
									id : 'makeFileDate',
									xtype : "datefield",
									allowBlank : false,// 不允许为空
									format : "Y-m-d",
									width:125,
									editable : false
								}]
					}, {
						xtype : 'container',
						layout : 'column',
						style : 'padding-left:0px;margin-left:0px;margin-bottom:4px;',
						defaultType : 'textfield',
						height : 26,
						items : [{
									xtype : 'label',
									text : '已移交份数:',
									style : 'padding-left:0px;margin-left:0px;margin-bottom:2px;',
									width : 85
								}, {
									name : 'book.leftAmount',
									id : 'leftAmount',
									xtype : 'numberfield',// 数量只能输入数字
									allowDecimals : false,// 只允许输入整数
									nanText : '只能输入数字',
									allowBlank : false,// 不允许为空
									blankText : '数量不能为空',
									minValue : 1,
									minText : '图书数量必须大于0'
								}, {
									xtype : 'label',
									text : '创建日期：',
									style : 'padding-left:0px;margin-left:30px;margin-bottom:4px;',
									width : 85
								}, {
									fieldLabel : '创建日期',
									name : 'book.createDate',
									id : 'createDate',
									xtype : "datefield",
									format : "Y-m-d",
									allowBlank : false,// 不允许为空
									width:125,
									editable : false,
									readOnly:true
								}]
					},  {
						fieldLabel : '状态',
						name : 'state',
						xtype:'checkbox',
						boxLabel:'禁用',
						id : 'state'
					},  {
						fieldLabel : '备注',
						name : 'book.remark',
						style:'margin-right:15px;',
						xtype:'textarea',
						height:'100',
						id : 'remark'
					},{
						xtype : "container",
						autoHeight : true,
						layout : "column",
						autoWidth : true,
						defaultType : "label",
						style : "padding-left:0px;",
						items : [{
									text : "合同附件:",
									width : 85,
									style : "padding-left:0px;padding-top:3px;"
								}, {
									xtype : "hidden",
									name : "bookAttachIDs",
									id : "bookAttachIDs"
								}, {
									xtype : "panel",
									id : "displayBookAttach",
									width : 320,
									height : 80,
									frame : true,
									autoScroll : true,
									style : "padding-left:0px;padding-top:0px;",
									html : ""
								}, {
									xtype : "button",
									iconCls : "btn-upload",
									text : "上传",
									handler : function() {
										var d = App.createUploadDialog({
													file_cat : "admin/book",
													callback : uploadbookAttach
												});
										d.show("queryBtn");
									}
								}]
						}]
	});
	// 验证是否是更新
// if (this.bookId == null || this.bookId == undefined || this.bookId == '') {
// Ext.getCmp("bookAmoutButton").setDisabled(true);
// Ext.getCmp("pcFile_edit_button").setDisabled(true);
// } else {
// Ext.getCmp("bookAmoutButton").setDisabled(false);
// Ext.getCmp("pcFile_edit_button").setDisabled(false);
// }
	if (this.bookId != null && this.bookId != 'undefined' && this.bookId != '') {
		formPanel.getForm().load({
			deferredRender : false,
			url : __ctxPath + '/admin/getBook.do?bookId=' + this.bookId,
			// method : 'post',
			waitMsg : '正在载入数据...',
			success : function(form, action) {

//				var typeId = Ext.getCmp('typeId');
				// 从服务端action取得图书类别的值
				var typeName = action.result.data.bookType.typeName;
				var bookTypeSelect = Ext.getCmp('bookTypeSelect');
				bookTypeSelect.setValue(typeName);
//				var bookId = action.result.data.bookId;
				
				var leftAmount = action.result.data.leftAmount;
				var leftAmountField = Ext.getCmp('leftAmount');
				leftAmountField.setValue(leftAmount);
				// 设置归档日期
				var fileDate = Ext.getCmp('fileDate');
				if (action.result.data.fileDate != null) {
					fileDate.setValue(new Date(action.result.data.fileDate));
				}
				// 设置保管日期
				var expirationDate = Ext.getCmp('expirationDate');
				if (action.result.data.expirationDate != null) {
					expirationDate.setValue(new Date(action.result.data.expirationDate));
				}
				// 设置制表日期
				var makeFileDate = Ext.getCmp('makeFileDate');
				if (action.result.data.makeFileDate != null) {
					makeFileDate.setValue(new Date(action.result.data.makeFileDate));
				}

				// 设置创建日期
				var createDate = Ext.getCmp('createDate');
				createDate.setValue(new Date(action.result.data.createDate).format("Y-m-d"));
				
				
				//附件
				var m = action.result.data.bookFiles;
				var l = Ext.getCmp("displayBookAttach");
				var o = Ext.getCmp("bookAttachIDs");
				for (var j = 0; j < m.length; j++) {
					if (o.getValue() != "") {
						o.setValue(o.getValue() + ",");
					}
					o.setValue(o.getValue() + m[j].fileId);
					Ext.DomHelper.append(l.body,
									'<span><a href="#" onclick="FileAttachDetail.show('
											+ m[j].fileId
											+ ')">'
											+ m[j].fileName
											+ '</a><img class="img-delete" src="'
											+ __ctxPath
											+ '/images/system/delete.gif" onclick="removebookAttach(this,'
											+ m[j].fileId
											+ ')"/>&nbsp;|&nbsp;</span>');
				}

				

			},
			failure : function(form, action) {
			}
		});
	} else {
		Ext.getCmp('createDate').setValue(new Date().format("Y-m-d"));
		// Ext.getCmp('operator').setValue(__currentUser);

	}
	return formPanel;
};
function removeBookSn(obj, bookSnId, bookId) {
	Ext.Msg.confirm('信息确认', '把借阅归还记录一起删除，您确认要删除该书吗？', function(btn) {
		if (btn == 'yes') {
			var el = Ext.get(obj.parentNode);
			el.remove();
			Ext.Ajax.request({
				url : __ctxPath + '/admin/multiDelBookSn.do',
				params : {
					ids : bookSnId
				},
				method : 'post',
				success : function() {
					Ext.ux.Toast.msg("信息提示", "成功删除所选记录！");
					// 删除图书标签成功后，将书的数量和未借出数量也同时修改
					Ext.Ajax.request({
						url : __ctxPath
								+ '/admin/updateAmountAndLeftAmountBook.do?bookId='
								+ bookId,
						method : 'post',
						success : function(response) {
							var result = Ext.util.JSON
									.decode(response.responseText);
							// 根据bookId修改书相应的数量和剩余数量
							Ext.getCmp('amount').setValue(result.data.amount);
							Ext.getCmp('leftAmount')
									.setValue(result.data.leftAmount);
						}
					});
				}
			});
		}
	});
};


function uploadbookAttach(c) {
	var a = Ext.getCmp("bookAttachIDs");
	var d = Ext.getCmp("displayBookAttach");
	for (var b = 0; b < c.length; b++) {
		if (a.getValue() != "") {
			a.setValue(a.getValue() + ",");
		}
		a.setValue(a.getValue() + c[b].fileId);
		Ext.DomHelper.append(d.body,
						'<span><a href="#" onclick="FileAttachDetail.show('
								+ c[b].fileId
								+ ')">'
								+ c[b].filename
								+ '</a><img class="img-delete" src="'
								+ __ctxPath
								+ '/images/system/delete.gif" onclick="removeContractAttach(this,'
								+ c[b].fileId + ')"/>&nbsp;|&nbsp;</span>');
	}
};
