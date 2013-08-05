var CourseBoxSelector = {
	getView : function(callback,isSingle,selectedCourseIds,selectedCourseNoes,selectedCourseNames) {
	 var sm = null;
		if (isSingle) {
			var sm = new Ext.grid.CheckboxSelectionModel({
						singleSelect : true
					});
		} else {
			sm = new Ext.grid.CheckboxSelectionModel();
		}
		var cm = new Ext.grid.ColumnModel({
					columns : [sm, new Ext.grid.RowNumberer(), {
								header : 'courseId',
								dataIndex : 'courseId',
								hidden : true
							}, {
								header : '课程编号',
								dataIndex : 'courseNo'
							}, {
								header : '课程名称',
								dataIndex : 'courseName'
							}],
					defaults : {
						sortable : true,
						menuDisabled : false,
						width : 100
					}
				});
	    	var store = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : __ctxPath + '/hrm/listTrainCourse.do'
							}),
					reader : new Ext.data.JsonReader({
								root : 'result',
								totalProperty : 'totalCounts',
								id : 'id',
								fields : [{
								name : 'courseId',
								type : 'int'
							}, 'courseNo', 'courseName']
							}),
					remoteSort : true
				});
	    	 var gridPanel = new Ext.grid.GridPanel({
					id : 'TrainCourseGrid',
					region : 'center',
					stripeRows : true,
					store :store,
					trackMouseOver : true,
					disableSelection : false,
					loadMask : true,
					cm : cm,
					sm : sm,
					plugins : this.rowActions,
					viewConfig : {
						autoFill : true, // 自动填充
						forceFit : true
						// showPreview : false
					},
					bbar : new Ext.PagingToolbar({
								pageSize : 10,
								store : store,
								plugins : [new Ext.ux.PageSizePlugin()],
								displayInfo : true,
								displayMsg : '当前页记录索引{0}-{1}， 共{2}条记录',
								emptyMsg : "当前没有记录"
							})
				});
				store.load({
					params : {
						start : 0,
						limit : 10
					}
				});
			var searchPanel = new Ext.FormPanel({
					layout : 'column',
					region : 'north',
					height:50,
					id:'TrainForm',
					bodyStyle : 'padding:6px 10px 6px 10px',
					border : false,
					frame : true,
					defaults : {
						border : false,
						anchor : '98%,98%'
					},
					items : [{
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '查询条件:'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '课程编号:'
							}, {
								name : 'Q_courseNo_S_LK',
								width:100,
								xtype : 'textfield'
							}, {
								style : 'margin-top:5px;',
								xtype : 'label',
								text : '课程名称:'
							}, {
								name : 'Q_courseName_S_LK',
								width:100,
								xtype : 'textfield'
							},{
								xtype : 'button',
								text : '查询',
								iconCls : 'search',
								handler : function(){
									
							var searchPanel = Ext.getCmp('TrainForm');
							var grid = Ext.getCmp('TrainCourseGrid');
							if (searchPanel.getForm().isValid()) {
								searchPanel.getForm().submit({
									waitMsg : '正在提交查询',
									url : __ctxPath+ '/hrm/listTrainCourse.do',
									success : function(formPanel, action) {
										var result = Ext.util.JSON.decode(action.response.responseText);
										grid.getStore().loadData(result);
									}
								});
							}
								}
							}]
				});	
      var h = new Ext.grid.CheckboxSelectionModel();
	  var a = new Ext.grid.GridPanel({
					id : "selectedCourseGrid",
					region : "center",
					title : "已选课程",
					autoScroll : true,
					store : new Ext.data.ArrayStore({
								fields : ["courseId", "courseNo",'courseName']
							}),
					trackMouseOver : true,
					sm : h,
					columns : [h, new Ext.grid.RowNumberer(), {
								header : "课程名程",
								dataIndex : "courseName"
							}]
				});
		   var m = new Ext.Panel({
							width : 200,
							region : "east",
							layout : "border",
							border : false,
							items : [new Ext.Panel({
										frame : true,
										region : "west",
										width : 35,
										layout : {
											type : "vbox",
											pack : "center",
											align : "stretch"
										},
										defaults : {
											margins : "0 0 5 0"
										},
										items : [{
													xtype : "button",
													text : "&gt;&gt;",
													handler : function() {
														var t = Ext.getCmp("TrainCourseGrid");
														var n = Ext.getCmp("selectedCourseGrid");
														var u = n.getStore();
														var x = t.getSelectionModel()
																.getSelections();
														for (var p = 0; p < x.length; p++) {
															var q = x[p].data.courseId;
															var v = x[p].data.courseNo;
															var i = x[p].data.courseName;
															var s = false;
															for (var o = 0; o < u.getCount(); o++) {
																if (u.getAt(o).data.courseId == q) {
																	s = true;
																	break;
																}
															}
															if (!s) {
																var w = {
																	courseId : q,
																	courseNo : v,
																	courseName:i
																};
																var r = new u.recordType(w);
																n.stopEditing();
																u.add(r);
															}
														}
													}
												}, {
													xtype : "button",
													text : "&lt&lt;",
													handler : function() {
														var p = Ext.getCmp("selectedCourseGrid");
														var q = p.getSelectionModel()
																.getSelections();
														var n = p.getStore();
														for (var o = 0; o < q.length; o++) {
															p.stopEditing();
															n.remove(q[o]);
														}
													}
												}]
									}), a]
						});
	    var window = new Ext.Window({
			title : '课程选择器',
			iconCls:'menu-project',
			width : 530,
			height : 380,
			layout : 'border',
			border : false,
			items : [searchPanel, gridPanel],
			modal : true,
			buttonAlign : 'center',
			buttons : [{
						iconCls : 'btn-ok',
						text : '确定',
						handler : function() {
							var courseId = '';
							var courseName = '';
							var courseNo = ''; 
							if (isSingle) {
								var h = Ext.getCmp("TrainCourseGrid");
								var k = h.getSelectionModel().getSelections();
								for (var g = 0; g < k.length; g++) {
									if (i > 0) {
										courseId += ',';
										courseName += ',';
										courseNo +=','; 
									}
									courseId += k[g].data.courseId;
									courseName += k[g].data.courseName;
									courseNo += k[g].data.courseNo;
								}
							}else{
								var storeRecord = Ext.getCmp("selectedCourseGrid").getStore();
								for (var i = 0; i < storeRecord.getCount(); i++) {
									if (i > 0) {
										courseId += ',';
										courseName += ',';
										courseNo +=','; 
									}
									courseId += storeRecord.getAt(i).data.courseId;
									courseName += storeRecord.getAt(i).data.courseName;
									courseNo += storeRecord.getAt(i).data.courseNo;
								}
							}
							if (callback != null) {
								callback.call(this, courseId, courseNo,courseName);
							}
							window.close();
						}
					}, {
						text : '取消',
						iconCls : 'btn-cancel',
						handler : function() {
							window.close();
						}
					}]
		});
		if(!isSingle){
			window.add(m);
		    if(!(Ext.isEmpty(selectedCourseIds)) && !(Ext.isEmpty(selectedCourseNames)) && !(Ext.isEmpty(selectedCourseNoes))){
				var selectedCourseId = selectedCourseIds.split(",");
				var selectedCourseNo = selectedCourseNoes.split(",");
	 			var selectedCourseName = selectedCourseNames.split(",");
	 			var selectedCourseGrid = Ext.getCmp("selectedCourseGrid");
	 			var selectedCourseStore = selectedCourseGrid.getStore();
	 			for (var i = 0; i < selectedCourseId.length; i++) {
	 				var tempfield = {
	 					courseId :selectedCourseId[i],
	 					courseNo:selectedCourseNo[i],
						courseName : selectedCourseName[i]
	 				}
	 				var tempRecord = new selectedCourseStore.recordType(tempfield);
					selectedCourseGrid.stopEditing();
					selectedCourseStore.add(tempRecord);
	 			}
			}
		}
		return window;
	}
	
};