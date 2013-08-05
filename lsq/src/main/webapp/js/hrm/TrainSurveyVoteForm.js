/**
 * @author
 * @createtime
 * @class TrainSurveyCourseUserForm
 * @extends Ext.Window
 * @description TrainSurveyCourseUser表单
 */
TrainSurveyVoteForm = Ext.extend(Ext.Window, {
	// 内嵌FormPanel
	gridPanel : null,
	// 构造函数
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 必须先初始化组件
		this.initUIComponents();
		TrainSurveyVoteForm.superclass.constructor.call(this, {
			id : 'TrainSurveyVoteFormWin',
			layout : 'fit',
			items : this.gridPanel,
			modal : true,
			height : 500,
			width : 400,
			maximizable : true,
			title : '详细信息',
			buttonAlign : 'center',
			buttons : this.buttons
		});
	},// end of the constructor
	// 初始化组件
	initUIComponents : function() {
		var thiz = this;
		var trainSurveyId = this.id;
		var cbsm = new Ext.grid.CheckboxSelectionModel({
			listeners : {
				beforerowselect : function(SelectionModel, rowIndex, keepExisting, record) {
					return true;
				}
			}
		});
		var cm = new Ext.grid.ColumnModel({
			columns : [cbsm, new Ext.grid.RowNumberer(), {
					header : 'id',
					dataIndex : 'courseId',
					hidden : true
				}, {
					header : '课程编号',
					dataIndex : 'courseNo'
				}, {
					header : '课程名称',
					dataIndex : 'courseName'
				}, {
					header : '投票人数',
					dataIndex : 'interestYes',
					hidden : true
				}, {
					header : '总人数',
					dataIndex : 'interestNo',
					renderer : function(value, metadata, record, rowIndex, colIndex, store) {
						return record.data.interestYes + "/" + (parseInt(record.data.interestYes) + parseInt(record.data.interestNo));
					}
				}]
		})
		this.gridPanel = new Ext.grid.GridPanel({
			id : "selectedUserGrid",
			region : "center",
			title : "投票结果",
			autoScroll : true,
			store : new Ext.data.ArrayStore({
				fields : ['courseId', 'courseNo', 'courseName', 'interestYes', 'interestNo']
			}),
			trackMouseOver : true,
			sm : cbsm,
			cm : cm,
			viewConfig : {
				autoFill : true, // 自动填充
				forceFit : true
			}
		});

		// 初始化功能按钮
		this.buttons = [{
				text : (!Ext.isEmpty(this.status) && this.status == '1') == true ? '已生成计划' : '生成培训计划',
				id : 'genPlanBtn',
				iconCls : 'btn-save',
				disabled : (!Ext.isEmpty(this.status) && this.status == '1'),
				hidden : !isGranted('_TrainPlanAdd'),
				handler : this.save.createCallback(this, trainSurveyId)
			}, {
				text : '取消',
				iconCls : 'btn-cancel',
				handler : this.cancel.createCallback(this)
			}];
		// 加载表单对应的数据
		if (this.id != null && this.id != 'undefined') {
			Ext.Ajax.request({
				url : __ctxPath + '/hrm/viewVoteResultTrainSurvey.do',
				params : {
					'trainSurveyId' : this.id
				},
				success : function(response, options) {
					var data = Ext.util.JSON.decode(response.responseText);
					if (!Ext.isEmpty(data.result)) {
						for (var i = 0; i < data.result.length; i++) {
							thiz.addComponent(data.result[i]);
						}
					}
				}
			})
		}
	},// end of the initcomponents

	/**
	 * 取消
	 * 
	 * @param {}
	 *            window
	 */
	cancel : function(window) {
		window.close();
	},
	/**
	 * 保存记录
	 */
	save : function(window, trainSurveyId) {
		var selectRecords = window.gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择记录！");
			return;
		}
		Ext.MessageBox.prompt("计划年份", "输入计划年份可生成计划编号反之不生成！", function(btn,txt) {
			var year = ""
			if(btn == "ok"){
				if(txt.length == 4){
					year = txt
				}
			}
			var courseIds = Array();
			for (var i = 0; i < selectRecords.length; i++) {
				courseIds.push(selectRecords[i].data.courseId);
			}
			Ext.MessageBox.show({  
                    msg : '保存中，请稍后...',  
                    // progressText : '保存中...',  
                    width : 300,  
                    wait : true,  
                    progress : true,  
                    closable : true,  
                    waitConfig : {  
                        interval : 200  
                    },  
                    icon : Ext.Msg.INFO  
                }); 
			Ext.Ajax.request({
				url : __ctxPath + '/hrm/batchGenTrainSurvey.do',
				params : {
					'courseIds' : courseIds,
					'trainSurveyId' : trainSurveyId,
					'year' :　year
				},
				method : 'POST',
				success : function(response, options) {
					Ext.MessageBox.hide();
					window.close();
					Ext.ux.Toast.msg('操作信息', '操作成功！');
				},
				failure : function(response, options) {
					Ext.MessageBox.hide();
					window.close();
					Ext.ux.Toast.msg('操作信息', '操作出错，请联系管理员！');
				}
			});
		}, window, false);
	},// end of save
	addComponent : function(result) {
		var store = this.gridPanel.getStore();
		var data = {
			'courseId' : result.courseId,
			'courseNo' : result.courseNo,
			'courseName' : result.courseName,
			'interestYes' : result.interestYes,
			'interestNo' : result.interestNo
		};
		var record = new store.recordType(data);
		this.gridPanel.stopEditing();
		store.add(record);
	}
});