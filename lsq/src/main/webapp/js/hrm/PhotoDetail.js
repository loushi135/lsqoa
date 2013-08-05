/**
 * 图片详情
 */
var PhotoDetail = function(id) {
	return new Ext.Panel({
				title : '照片详情',
				iconCls : 'menu-notice',
				id : 'PhotoDetail',
				autoScroll : true,
				autoWidth : true,
				tbar : new Ext.Toolbar({
							height : 30,
							bodyStyle : 'text-align:left',
							defaultType:'button',
							items : [{
									text : '关闭',
									iconCls:'btn-mail_remove',
									handler : function() {
										var centerTabPanel = Ext
												.getCmp('centerTabPanel');
										var oldDetailPanel = centerTabPanel
												.getItem('PhotoDetail');
										centerTabPanel.remove(oldDetailPanel);
									}
								},{
									text : '上一条',
									iconCls:'btn-up',
									handler : function(){
										var haveNextEmpcareFlag = document.getElementById('__haveNextPhotoFlag');
										if(haveNextEmpcareFlag !=null && haveNextEmpcareFlag.value !='endPre'){
											var curId = document.getElementById('__curPhotoId').value;
											Ext.getCmp('HomePhotoDetailPanel').load({
												url : __ctxPath+ '/pages/hrm/photoDetail.jsp?opt=_pre&id='+ curId,
												callback: function(){
														var newCurId = document.getElementById('__curPhotoId').value;
														var __curPhotoName = document.getElementById('__curPhotoName').value;
														var __curPhotoDesc = document.getElementById('__curPhotoDesc').value;
														var __curPhotoUserId = document.getElementById('__curPhotoUserId').value;
														if(__curPhotoUserId==__currentUserId||curUserInfo.rights.indexOf('__ALL')!=-1){//当前登录人为 上传者
															Ext.getCmp("curUpdateForm").show();
															Ext.getCmp("otherDescContainer").hide();
														}else{
															Ext.getCmp("curUpdateForm").hide();
															Ext.getCmp("photoNameLabel").setText(__curPhotoName);
															Ext.getCmp("photoDescLabel").setText(__curPhotoDesc);
															Ext.getCmp("otherDescContainer").show();
														}
														Ext.getCmp("photo.photoName").setValue(__curPhotoName);
														Ext.getCmp("photo.photoDesc").setValue(__curPhotoDesc);
														var photoCommentContainer = Ext.getCmp('photoCommentContainer');
														photoCommentContainer.removeAll();
														photoCommentContainer.add(photoCommentDisplayGrid(newCurId) );
														photoCommentContainer.doLayout(true);
													}
											});
										}else{
											Ext.ux.Toast.msg('提示信息','这里已经是第一条了.');
										}
									}
								},{
									text : '下一条',
									iconCls:'btn-last',
									handler : function(){
										var haveNextEmpcareFlag = document.getElementById('__haveNextPhotoFlag');
										if(haveNextEmpcareFlag !=null && haveNextEmpcareFlag.value !='endNext'){
											var curId = document.getElementById('__curPhotoId').value;
											Ext.getCmp('HomePhotoDetailPanel').load({
												url : __ctxPath+ '/pages/hrm/photoDetail.jsp?opt=_next&id='+ curId,
												callback: function(){
														var newCurId = document.getElementById('__curPhotoId').value;
														var __curPhotoName = document.getElementById('__curPhotoName').value;
														var __curPhotoDesc = document.getElementById('__curPhotoDesc').value;
														var __curPhotoUserId = document.getElementById('__curPhotoUserId').value;
														if(__curPhotoUserId==__currentUserId||curUserInfo.rights.indexOf('__ALL')!=-1){//当前登录人为 上传者
															Ext.getCmp("curUpdateForm").show();
															Ext.getCmp("otherDescContainer").hide();
														}else{
															Ext.getCmp("curUpdateForm").hide();
															Ext.getCmp("photoNameLabel").setText(__curPhotoName);
															Ext.getCmp("photoDescLabel").setText(__curPhotoDesc);
															Ext.getCmp("otherDescContainer").show();
														}
														Ext.getCmp("photo.photoName").setValue(__curPhotoName);
														Ext.getCmp("photo.photoDesc").setValue(__curPhotoDesc);
														var photoCommentContainer = Ext.getCmp('photoCommentContainer');
														photoCommentContainer.removeAll();
														photoCommentContainer.add(photoCommentDisplayGrid(newCurId));
														photoCommentContainer.doLayout(true);
													}
											});
										}else{
											Ext.ux.Toast.msg('提示信息','这里已经是最后一条了.');
										}
									}
								}]
						}),
				defaults:{
					width:'80%'
				},
				items : [new Ext.Panel({
							id : 'HomePhotoDetailPanel',
							autoScroll : true,
							style : 'padding-left:10%;padding-top:10px;',
							autoHeight : true,
							border : false,
							script:true,
							autoLoad : {
								url : __ctxPath
										+ '/pages/hrm/photoDetail.jsp?id='
										+ id,
								scripts:true,
								callback: function(){
											var __curPhotoName = document.getElementById('__curPhotoName').value;
											var __curPhotoDesc = document.getElementById('__curPhotoDesc').value;
											Ext.getCmp("photo.photoName").setValue(__curPhotoName);
											Ext.getCmp("photo.photoDesc").setValue(__curPhotoDesc);
											var __curPhotoUserId = document.getElementById('__curPhotoUserId').value;
											if(__curPhotoUserId==__currentUserId||curUserInfo.rights.indexOf('__ALL')!=-1){//当前登录人为 上传者
												Ext.getCmp("curUpdateForm").show();
												Ext.getCmp("otherDescContainer").hide();
											}else{
												Ext.getCmp("curUpdateForm").hide();
												Ext.getCmp("photoNameLabel").setText(__curPhotoName);
												Ext.getCmp("photoDescLabel").setText(__curPhotoDesc);
												Ext.getCmp("otherDescContainer").show();
											}
									}
							}
						}),new Ext.FormPanel({
							url : __ctxPath + '/hrm/updatePhoto.do',
							id : 'PhotoUpdateForm',
							autoHeight :true,
							layout:'form',
							frame:true,
							style : 'padding-left:10%;padding-top:5px;',
							items:[	
									{
											xtype:'container',
											layout:'column',
											id:"curUpdateForm",
											items:[
												{
													xtype:'container',
													layout:'form',
													defaults:{xtype:'textfield',width:700},
													items:[
														{name:'photo.id',xtype:'hidden',id:'photoUpdateId'},
														{name:'photo.photoName',id:'photo.photoName',fieldLabel:'名称'},
														{xtype:'textarea',name:'photo.photoDesc',id:'photo.photoDesc',height:55,fieldLabel:'描述'}
													]
												},{
													xtype:'button',
													text:'保存',
													height:80,
													width:80,
													iconCls : 'btn-save',
													handler:function(){
														Ext.getCmp('photoUpdateId').setValue(document.getElementById('__curPhotoId').value);
														var fp = Ext.getCmp('PhotoUpdateForm');
														if (fp.getForm().isValid()) {
															fp.getForm().submit({
																method : 'post',
																waitMsg : '正在提交数据...',
																success : function(fp, action) {
																	Ext.ux.Toast.msg('操作信息', '成功保存信息！');
																},
																failure : function(fp, action) {
																	Ext.MessageBox.show({
																				title : '操作信息',
																				msg : '信息保存出错，请联系管理员！',
																				buttons : Ext.MessageBox.OK,
																				icon : Ext.MessageBox.ERROR
																			});
																}
															});
														}
													},
													listeners:{
																	afterrender:function(component){
																		Ext.getCmp("curUpdateForm").hide();
																	}
													}
												}
											]
									},{
											xtype:'container',
											layout:'column',
											id:"otherDescContainer",
											items:[
												{
													xtype:'container',
													layout:'form',
													items:[
														{
															xtype:'container',
															layout:'column',
															defaults:{
																xtype:'label'
															},
															width:700,
															items:[
																{text:'名称:',width:60},
																{id:'photoNameLabel',width:600}
															]
														},
														{
															xtype:'container',
															layout:'column',
															defaults:{
																xtype:'label'
															},
															autoHeight :true,
															width:700,
															items:[
																{text:'描述:',width:60},
																{id:'photoDescLabel',width:600,
																	 listeners:{
																					afterrender:function(component){
																						Ext.getCmp("otherDescContainer").hide();
																					}
																	}
																}
															]
														}
													]
												}
											]
									}
									
							]
						}),{
							xtype:'panel',
							border:false,
							id:'photoCommentContainer',
							style : 'padding-left:10%;padding-top:10px;',
							items:[new photoCommentDisplayGrid(id)]
						},new Ext.FormPanel({
								url : __ctxPath + '/hrm/savePhotoComment.do',
								id : 'PhotoCommentForm',
								iconCls : 'menu-info',
								title : '我要评论',
								autoScroll : true,
								style : 'padding-left:10%;padding-top:10px;padding-bottom:25px;',
								autoHeight : true,
								defaultType : 'textfield',
								labelWidth : 55,
								defaults : {
									width : 550
								},
								layout : 'form',
								items : [
										{
											name : 'photoComment.id',
											xtype : 'hidden'
										},{
											name : 'photoComment.photo.id',
											xtype : 'hidden',
											id : 'photoId'
										},{
											name : 'photoComment.user.userId',
											xtype : 'hidden',
											value : curUserInfo.userId
										}, {
											fieldLabel : '用户',
											name : 'photoComment.fullname',
											readOnly : true,
											value : curUserInfo.fullname
										}, {
											fieldLabel : '内容',
											xtype : 'textarea',
											blankText : '评论内容为必填!',
											allowBlank : false,
											name : 'photoComment.content',
											id : 'photoCommentContent'
										}
										],
								buttonAlign : 'center',
								buttons : [{
									text : '提交',
									iconCls : 'btn-save',
									handler : function() {
										Ext.getCmp('photoId').setValue(document.getElementById('__curPhotoId').value);
										var fp = Ext.getCmp('PhotoCommentForm');
										if (fp.getForm().isValid()) {
											fp.getForm().submit({
												method : 'post',
												waitMsg : '正在提交数据...',
												success : function(fp, action) {
													Ext.ux.Toast.msg('操作信息', '成功保存信息！');
													Ext.getCmp('photoCommentContent').setValue('');
													Ext.getCmp('photoCommentDisplayGrid').getStore().reload();
												},
												failure : function(fp, action) {
													Ext.MessageBox.show({
																title : '操作信息',
																msg : '信息保存出错，请联系管理员！',
																buttons : Ext.MessageBox.OK,
																icon : Ext.MessageBox.ERROR
															});
												}
											});
										}
									}
								}, {
									text : '重置',
									iconCls : 'reset',
									handler : function() {
										Ext.getCmp('photoCommentContent').setValue('');
									}
								}]
							})
						]
			});
	
}

var photoCommentDisplayGrid = function(id) {

	var cm = new Ext.grid.ColumnModel({
		columns : [{
					width : 40,
					dataIndex : 'start',
					renderer : function(value, metadata, record, rowIndex,
							colIndex) {
						return rowIndex + 1 + '楼';
					}
				}, {
					dataIndex : 'id',
					hidden : true
				}, {
					width : 400,
					dataIndex : 'content',
					renderer : function(value, metadata, record, rowIndex,
							colIndex) {
						html = '<table width="100%"><tr>' +
								'<td width="80%"><font color="gray">评论人:'
								+ record.data.fullname
								+ '</font></td><td align="right"><font color="gray">'
								+ record.data.createDate
								+ '</font></td></tr>' +
								'<tr><td rowspan="2" style="white-space:normal;"><font style="font:13px 宋体;color: black;">'
								+ value + '</font></td></tr></table>'
						return html;
					}
				}
		],
		defaults : {
			sortable : true,
			menuDisabled : false,
			width : 100
		}
	});
	var store = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : __ctxPath + '/hrm/listPhotoComment.do?Q_photo.id_L_EQ='+id
						}),
				reader : new Ext.data.JsonReader({
							root : 'result',
							totalProperty : 'totalCounts',
							id : 'id',
							fields : ['id',
									'content',
									'createDate',
									'fullname'
									]
						}),
				remoteSort : false
			});
	store.setDefaultSort('createDate', 'asc');
	store.load({
				params : {
					start : 0,
					limit : 10
				}
			});
	var grid = new Ext.grid.GridPanel({
				store : store,
				hideHeaders:true,
				trackMouseOver : true,
				disableSelection : false,
				loadMask : true,
				autoHeight : true,
				id : 'photoCommentDisplayGrid',
				autoWidth : true,
				title : '查看评论',
				iconCls : 'menu-info',
				collapsible : true,
				collapsed : false,
				cm : cm,
				viewConfig : {
					forceFit : true,
					enableRowBody : false,
					showPreview : false
				},
				bbar : new Ext.PagingToolbar({
							pageSize : 10,
							store : store,
							plugins : [new Ext.ux.PageSizePlugin()], 
							displayInfo : true,
							displayMsg : '当前显示{0}至{1}， 共{2}条记录',
							emptyMsg : "当前没有记录"
						})
			});
	return grid;
}
