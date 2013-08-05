Ext.ns("ProvinceTreePanel")

ProvinceTreePanel = function(){};
ProvinceTreePanel.prototype.getTree = function(){
		var thiz = this;
		var rootNode=new Ext.tree.AsyncTreeNode({
        			text:thiz.type=='1'?'供应商':'客户',
        			provinceId:'0',
        			expanded : true
                }); 
        var treeLoader= new Ext.tree.TreeLoader({
							url : __ctxPath + '/system/getTreeProvince.do'
						});
		var treePanel = new Ext.tree.TreePanel({
				region : 'west',
				title : '地区显示',
				collapsible : true,
				autoScroll:true,
				useArrows: true,
			    animate: true,
//			    enableDD: true,
//			    dropConfig: {appendOnly:true},//appendOnlY 只向后加
			    containerScroll: true,
			    border: false,
				split : true,
				height : 800,
				width : 200,
				tbar : new Ext.Toolbar({
							items : [{
										xtype : 'button',
										iconCls : 'btn-refresh',
										text : '刷新',
										handler : function() {
											treePanel.root.reload();
										}
									}, {
										xtype : 'button',
										text : '展开',
										iconCls : 'btn-expand',
										handler : function() {
											treePanel.expandAll();
										}
									}, {
										xtype : 'button',
										text : '收起',
										iconCls : 'btn-collapse',
										handler : function() {
											treePanel.collapseAll();
										}
									}]
						}),
				loader : treeLoader,
				rootVisible : true,
				listeners : {
					'click' : function(node){
									var provinceId = node.attributes.provinceId;
									var cityId = node.attributes.cityId;
									if(typeof(provinceId)!='undefined'){//当前节点为 省
										if(provinceId=='0'){//点击供应商
											if(thiz.type=='1'){
												var params = Ext.getCmp("SuppliersAssessView").store.baseParams;
									            Ext.apply(params,{"Q_province.provinceId_L_EQ":'',"Q_city.cityId_L_EQ":''});
									            Ext.getCmp("SuppliersAssessView").store.load(params);
											}else if(thiz.type=='2'){
												var params = Ext.getCmp("CustomerNewView").store.baseParams;
									            Ext.apply(params,{"Q_province.provinceId_L_EQ":'',"Q_city.cityId_L_EQ":''});
									            Ext.getCmp("CustomerNewView").store.load(params);
											}
											
										}else{
											var provinceForm = Ext.getCmp('ProvinceFormWin');
											if (typeof(provinceForm)!='undefined'&&provinceForm != null) {
												if(thiz.curNodeId!=provinceId){
													provinceForm.destroy();
													new ProvinceForm({provinceTreePanel:thiz,provinceId:provinceId}).show();
												}
											}
											if(thiz.curNodeId!=provinceId){
												if(thiz.type=='1'){
													var params = Ext.getCmp("SuppliersAssessView").store.baseParams; 
										            Ext.apply(params,{"Q_province.provinceId_L_EQ":provinceId,"Q_city.cityId_L_EQ":''});
										            Ext.getCmp("SuppliersAssessView").store.load(params);
												}else if(thiz.type=='2'){
													var params = Ext.getCmp("CustomerNewView").store.baseParams; 
										            Ext.apply(params,{"Q_province.provinceId_L_EQ":provinceId,"Q_city.cityId_L_EQ":''});
										            Ext.getCmp("CustomerNewView").store.load(params);
												}
											}
										}
										thiz.curNodeId = provinceId;
									}else if(typeof(cityId)!='undefined'){//当前节点为市
										var provinceId =  node.parentNode.attributes.provinceId;
										var cityForm = Ext.getCmp('CityFormWin');
										if (typeof(cityForm)!='undefined'&&cityForm != null) {
											if(thiz.curNodeId!=cityId){
												cityForm.destroy();
												new CityForm({provinceTreePanel:thiz,cityId:cityId,provinceId:provinceId}).show();
											}
										}
										if(thiz.curNodeId!=cityId){
											if(thiz.type=='1'){
												var params = Ext.getCmp("SuppliersAssessView").store.baseParams; 
									            Ext.apply(params,{"Q_province.provinceId_L_EQ":provinceId,"Q_city.cityId_L_EQ":cityId});
									            Ext.getCmp("SuppliersAssessView").store.load(params);
											}else if(thiz.type=='2'){
												var params = Ext.getCmp("CustomerNewView").store.baseParams; 
									            Ext.apply(params,{"Q_province.provinceId_L_EQ":provinceId,"Q_city.cityId_L_EQ":cityId});
									            Ext.getCmp("CustomerNewView").store.load(params);
											}
										}
										thiz.curNodeId = cityId;
									}
					},
					nodedrop:function(dropEvent){
						var tree = dropEvent.tree;
						var target = dropEvent.target;
						var data = dropEvent.data;
						var point = dropEvent.point;
						var source = dropEvent.source;
						var rawEvent = dropEvent.rawEvent;
						var dropNode = dropEvent.dropNode;
//						alert(point);//append,above,below
//						alert(target.text);
//						alert(dropNode.parentNode.attributes.provinceId);
					},
					nodedragover:function(dropOverEvent){
						if(dropOverEvent.dropNode.text=='北京市'){
							dropOverEvent.cancel = true;
						}
					}
				}
			});
        treePanel.setRootNode(rootNode);
        treePanel.on('beforeload',
                function(node){
                  	treePanel.loader.dataUrl = __ctxPath+'/system/getTreeProvince.do?provinceId='+node.attributes.provinceId;    //定义子节点的Loader   
                }); 
		// 树的右键菜单的
		treePanel.on('contextmenu', contextmenu, treePanel);
		var treeMenu = new Ext.menu.Menu({
						id : 'DistrictTreeMenu',
						items : []
					});
		function contextmenu(node, e) {
			thiz.selectedNode = node;
			// 创建右键菜单
			treeMenu.removeAll();
			if(node==treePanel.getRootNode()){
				treeMenu.add({
						text : '新建省',
						iconCls:'btn-add',
						scope : this,
						handler : thiz.createProvince.createCallback(thiz)
				});
			}else if(typeof(node.attributes.provinceId)!='undefined'){
				treeMenu.add({
						text : '新建市',
						iconCls:'btn-add',
						scope : this,
						handler : thiz.createCity.createCallback(thiz)
					});
				treeMenu.add({
							text : '修改省',
							iconCls:'btn-edit',
							scope : this,
							handler : thiz.editProvince.createCallback(thiz)
						});
				treeMenu.add({
							text : '删除省',
							iconCls:'btn-delete',
							scope : this,
							handler : thiz.deleteProvince.createCallback(thiz)
						})
			}else{
				treeMenu.add({
							text : '修改市',
							iconCls:'btn-edit',
							scope : this,
							handler : thiz.editCity.createCallback(thiz)
						});
				treeMenu.add({
							text : '删除市',
							iconCls:'btn-delete',
							scope : this,
							handler : thiz.deleteCity.createCallback(thiz)
						})
			}
			treeMenu.showAt(e.getXY());
		}
		this.treePanel = treePanel;
		return this.treePanel;
	}
ProvinceTreePanel.prototype.selectedNode = null;//当前选中节点

ProvinceTreePanel.prototype.curNodeId = null; //上次点击的节点 id，可能为provinceId或cityId

ProvinceTreePanel.prototype.treePanel = null;

ProvinceTreePanel.prototype.type = null;

ProvinceTreePanel.prototype.createProvince = function(obj){
	var provinceForm = Ext.getCmp('ProvinceFormWin');
	if (typeof(provinceForm)=='undefined'||provinceForm == null) {
		 new ProvinceForm({provinceTreePanel:obj}).show();
	}
}

ProvinceTreePanel.prototype.editProvince = function(obj){
	var provinceId = obj.selectedNode.attributes.provinceId;
	var provinceForm = Ext.getCmp('ProvinceFormWin');
	if (typeof(provinceForm)=='undefined'||provinceForm == null) {
		new ProvinceForm({provinceTreePanel:obj,provinceId:provinceId}).show();
	}
}

ProvinceTreePanel.prototype.deleteProvince = function(obj){
	var provinceId = obj.selectedNode.attributes.provinceId;
	var ids = new Array();
	ids.push(provinceId);
	Ext.Msg.confirm('信息确认','您确认要删除此省份吗？',function(btn){
			if(btn=='yes'){
				Ext.Ajax.request({
								url:__ctxPath+'/system/multiDelProvince.do',
								params:{ids:ids},
								method:'POST',
								success:function(response,options){
									Ext.ux.Toast.msg('操作信息','成功删除该省份！');
									obj.treePanel.root.reload();
								},
								failure:function(response,options){
									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
								}
							});
			}
		});//end of comfirm
}

ProvinceTreePanel.prototype.createCity = function(obj){
	var provinceId = obj.selectedNode.attributes.provinceId;
	var cityForm = Ext.getCmp('CityFormWin');
	if (typeof(cityForm)=='undefined'||cityForm == null) {
		new CityForm({provinceTreePanel:obj,provinceId:provinceId}).show();
	}
}
ProvinceTreePanel.prototype.editCity = function(obj){
	var cityId = obj.selectedNode.attributes.cityId;
	var provinceId =  obj.selectedNode.parentNode.attributes.provinceId;
	var cityForm = Ext.getCmp('CityFormWin');
	if (typeof(cityForm)=='undefined'||cityForm == null) {
		new CityForm({provinceTreePanel:obj,cityId:cityId,provinceId:provinceId}).show();
	}
}
ProvinceTreePanel.prototype.deleteCity = function(obj){
	var cityId = obj.selectedNode.attributes.cityId;
	var ids = new Array();
	ids.push(cityId);
	Ext.Msg.confirm('信息确认','您确认要删除此城市吗？',function(btn){
			if(btn=='yes'){
				Ext.Ajax.request({
								url:__ctxPath+'/system/multiDelCity.do',
								params:{ids:ids},
								method:'POST',
								success:function(response,options){
									Ext.ux.Toast.msg('操作信息','成功删除该城市！');
									obj.treePanel.root.reload();
								},
								failure:function(response,options){
									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
								}
							});
			}
		});//end of comfirm
}

