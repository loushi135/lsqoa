/**
 * @author:
 * @class DruidMonitorView
 * @extends Ext.Panel
 * @description [DruidMonitorView]管理
 * @company
 * @createtime:2010-07-19
 */
DruidMonitorView = Ext.extend(Ext.Panel, {
	mainPanel : null,
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		// 初始化组件
		this.initUIComponents();
		// 调用父类构造
		DruidMonitorView.superclass.constructor.call(this, {
					id : 'DruidMonitorView',
					title : '[数据监控]管理',
					region : 'center',
					layout : 'border',
					items : [this.mainPanel]
				});
	},// end of constructor

	// 初始化组件
	initUIComponents : function() {
		// 初始化搜索条件Panel
		this.mainPanel = new Ext.Panel({
					layout : 'column',
					region : 'center',
					border : false,
					height:'100%',
					width:'100%',
					defaults : {
						border : false,
						anchor : '100%,100%'
					},
					html:'<iframe src="'+__ctxPath+'/pages/druid/monitor/index.jsp" width="100%" height="100%"></iframe>'
				});// end of the searchPanel

	}// end of the initComponents()
});
