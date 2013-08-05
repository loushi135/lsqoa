/**
 * @author:
 * @class PhotoFolderView
 * @extends Ext.Panel
 * @description [PhotoFolder]管理
 * @company 
 * @createtime:2010-07-19
 */
PhotoFolderView=Ext.extend(Ext.Panel,{
	//条件搜索Panel
	searchPanel:null,
	treePanel:null,
	//GridPanel的数据Store
	store:null,
	//头部工具栏
	topbar:null,
	selectedNode:null,
	mainPanel:null,
	curNodeId:null,
	curNode:null,
	//构造函数
	constructor:function(_cfg){
			Ext.applyIf(this,_cfg);
			//初始化组件
			this.initUIComponents();
			//调用父类构造
			PhotoFolderView.superclass.constructor.call(this,{
				id:'PhotoFolderView',
				title:'公司相册',
				region:'center',
				layout:'border',
				items:[this.treePanel,this.mainPanel]
			});
	},//end of constructor

	//初始化组件
	initUIComponents:function(){
		//初始化treePanel
		var thiz = this;
		this.treePanel = new Ext.tree.TreePanel({
				region : 'west',
				id : 'photoTreePanel',
				title : '图片文件夹显示',
				collapsible : true,
				split : true,
				height : 800,
				width : 200,
				tbar : new Ext.Toolbar({
							items : [{
										xtype : 'button',
										iconCls : 'btn-refresh',
										text : '刷新',
										handler : function() {
											thiz.treePanel.root.reload();
										}
									}, {
										xtype : 'button',
										text : '展开',
										iconCls : 'btn-expand',
										handler : function() {
											thiz.treePanel.expandAll();
										}
									}, {
										xtype : 'button',
										text : '收起',
										iconCls : 'btn-collapse',
										handler : function() {
											thiz.treePanel.collapseAll();
										}
									}]
						}),
				loader : new Ext.tree.TreeLoader({
							url : __ctxPath + '/hrm/listPhotoFolder.do'
						}),
				root : new Ext.tree.AsyncTreeNode({
							expanded : true
						}),
				rootVisible : false,
				listeners : {
					'click' : function(node){
									var nodeId = node.id;
									var parentId ='';
									if(node.parentNode!=null){
										parentId = node.parentNode.id;
									}
									var photoFolderForm = Ext.getCmp('PhotoFolderFormWin');
									if (typeof(photoFolderForm)!='undefined'&&photoFolderForm != null) {
										if(thiz.curNodeId!=nodeId&&nodeId!='-1'){
											photoFolderForm.destroy();
											new PhotoFolderForm({id:nodeId,parentId:parentId}).show();
										}
									}
									
									if(thiz.curNodeId!=nodeId){
										if(nodeId!='-1'){
											thiz.store.reload({params : {
												folderId:nodeId
											}});
										}
									}
									thiz.curNodeId = nodeId;
									thiz.curNode = node;
					}
				}
			});
		// 树的右键菜单的
		this.treePanel.on('contextmenu', contextmenu, this.treePanel);
		// 创建右键菜单
		var treeMenu = new Ext.menu.Menu({
					id : 'PhotoTreeMenu',
					items : []
				});
		treeMenu.add({
					text : '新建目录',
					iconCls:'btn-add',
					scope : this,
					handler : this.createNode
				});
		treeMenu.add({
					text : '修改目录',
					iconCls:'btn-edit',
					id:'updateFolderBtn',
					scope : this,
					handler : this.editNode
				});
		treeMenu.add({
					text : '删除目录',
					iconCls:'btn-delete',
					id:'deleteFolderBtn',
					scope : this,
					handler : this.deteleNode
				});

		function contextmenu(node, e) {
			thiz.selectedNode = node;
			// if(selected.id>0){
			if(node.attributes.userId==__currentUserId||curUserInfo.rights.indexOf('__ALL')!=-1){//当前登录人为 上传者
				Ext.getCmp("updateFolderBtn").show();
				Ext.getCmp("deleteFolderBtn").show();
			}else{
				Ext.getCmp("updateFolderBtn").hide();
				Ext.getCmp("deleteFolderBtn").hide();
			}
			treeMenu.showAt(e.getXY());
			// }
		}
		this.store = new Ext.data.JsonStore({
							url : __ctxPath+"/hrm/listPhoto.do",
							root : 'result',
							totalProperty : 'totalCounts',
							remoteSort : true,
							fields : ['photoName','photoDesc',{name:'userId',mapping:'user.userId'},{name:'filePath',mapping:'file.filePath'},'id',{name:'note',mapping:'file.note'}]
		});
//		this.store.load();
		if(isGranted("_PhotoDel")){
			deleteStr = '<div><input type="button"  value="删除" onclick="javascript:Ext.getCmp(\'PhotoFolderView\').deleteViewImages({id},Ext.getCmp(\'PhotoFolderView\'))">&nbsp;<input type="button"  value="评论" onclick="javascript:Ext.getCmp(\'PhotoFolderView\').toPhotoDetail({id},Ext.getCmp(\'PhotoFolderView\'))"></div>';
		}else{
			deleteStr = '<div><input type="button"  value="评论" onclick="javascript:Ext.getCmp(\'PhotoFolderView\').toPhotoDetail({id},Ext.getCmp(\'PhotoFolderView\'))"></div>';
		}
		var tpl = new Ext.XTemplate(
		    '<tpl for=".">',
                '<div class="thumb-wrap1">',
                '<tpl if="this.isUploadUser(userId)"><div><input type="button"  value="删除" onclick="javascript:Ext.getCmp(\'PhotoFolderView\').deleteViewImages({id},Ext.getCmp(\'PhotoFolderView\'))">&nbsp;<input type="button"  value="评论" onclick="javascript:Ext.getCmp(\'PhotoFolderView\').toPhotoDetail({id},Ext.getCmp(\'PhotoFolderView\'))"></div></tpl>',
                '<tpl if="this.isOtherUser(userId)"><div><input type="button"  value="评论" onclick="javascript:Ext.getCmp(\'PhotoFolderView\').toPhotoDetail({id},Ext.getCmp(\'PhotoFolderView\'))"></div></tpl>',
		        '<div class="thumb"><a href="'+__ctxPath+'/attachFiles/{filePath}" class="highslide" onclick="return hs.expand(this)" ><img src="'+__ctxPath+'/attachFiles/{filePath}" alt="Highslide JS" title="Click to enlarge" /></a></div>',
		        '<span class="">{photoName:ellipsis(18)}&nbsp;</span>',
		        '<span class="">{photoDesc:ellipsis(18)}&nbsp;</span>',
		        '</div>', //x-editable可编辑  .thumb-wrap span
            '</tpl>',
            '<div class="x-clear"></div>',
            {
            	isUploadUser: function(userId){
            			if(userId==__currentUserId||curUserInfo.rights.indexOf('__ALL')!=-1){
            				return true;
            			}else{
            				return false;
            			}
            	},
            	isOtherUser : function(userId){
            		if(userId==__currentUserId||curUserInfo.rights.indexOf('__ALL')!=-1){
            				return false;
            			}else{
            				return true;
            			}
            	},
            	ellipsisName : function(name){
            		if(name==''){
            			name="名称为空";
            		}else {
            			name = Ext.util.Format.ellipsis(name,18);
            		}
            		return name;
            	}
            }
	    );
		this.dataview = new Ext.DataView({
            store: this.store,
            tpl: tpl,
//            height: 300,
            autoHeight:true,
//            simpleSelect: true,
            multiSelect: true,
//            singleSelect: true,
            overClass:'x-view-over',
            itemSelector:'div.thumb-wrap1',
            emptyText: 'No images to display',
            plugins: [
//                new Ext.DataView.DragSelector()
//                new Ext.DataView.LabelEditor({dataIndex: 'name'})
            ],
            prepareData: function(data){
                data.shortName = Ext.util.Format.ellipsis(data.name, 15);
                data.sizeString = Ext.util.Format.fileSize(data.size);
//                data.dateString = data.date.format("m/d/Y g:i a");
                return data;
            },
            listeners: {
        	    selectionchange: {
        		    fn: function(dv,nodes){
        			    var l = nodes.length;
        			    var s = '';//l != 1 ? 's' : '';
        			    thiz.mainPanel.setTitle('图片列表 ('+l+' 项'+s+' 被选中)');
        		    }
        	    }
            }
        })
        //初始化工具栏
		this.topbar=new Ext.Toolbar({
				height : 30,
				bodyStyle : 'text-align:left',
				items : [{
							iconCls : 'btn-add',
							text : '上传图片',
							xtype : 'button',
							handler:this.createRecord.createCallback(thiz)
						}]
			});
        this.mainPanel = new Ext.Panel({
            id:'photo-view',
            frame:true,
            width:535,
            tbar:this.topbar,
            region:'center',
            autoHeight:true,
            collapsible:true,
            layout:'fit',
            title:'图片列表(0 项 被选中)',
            butttonAlign: "left",
            tools: [{
                id:"refresh",
                qtip:"刷新图片列表",
                on:{click:function(){
    	                thiz.mainPanel.body.mask("加载中...", 'x-mask-loading');
    	                setTimeout(function(){
    	                	thiz.store.reload();
        	                thiz.mainPanel.body.unmask();
    	                }, 1000);
	                }
                }
            }],
            items: this.dataview
        });
			
	},//end of the initComponents()
	
	/**
	 * 添加记录
	 */
	createRecord:function(panel){
		var folderName = null;
		if(!Ext.isEmpty(panel.curNode)){
			folderName = panel.curNode.text;
		}
		new PhotoUploadWin({folderId:panel.curNodeId,folderName:folderName}).show();
//		var tabs = Ext.getCmp('centerTabPanel');
//		var tabItem = tabs.getItem('PhotoUpload');
//		if (tabItem == null) {
//			var photoUpload = new PhotoUpload();
//			tabItem = tabs.add(photoUpload);
//			tabs.activate(tabItem);
//		}else {
//			tabs.activate(tabItem);
//		}
	},
	/**
	 * 按IDS删除记录
	 * @param {} ids
	 */
	delByIds:function(ids){
		Ext.Msg.confirm('信息确认','您确认要删除所选记录吗？',function(btn){
			if(btn=='yes'){
				Ext.Ajax.request({
								url:__ctxPath+'/hrm/multiDelPhotoFolder.do',
								params:{ids:ids},
								method:'POST',
								success:function(response,options){
									Ext.ux.Toast.msg('操作信息','成功删除该[PhotoFolder]！');
									Ext.getCmp('PhotoFolderGrid').getStore().reload();
								},
								failure:function(response,options){
									Ext.ux.Toast.msg('操作信息','操作出错，请联系管理员！');
								}
							});
			}
		});//end of comfirm
	},
	/**
	 * 删除多条记录
	 */
	delRecords:function(){
		var gridPanel=Ext.getCmp('PhotoFolderGrid');
		var selectRecords = gridPanel.getSelectionModel().getSelections();
		if (selectRecords.length == 0) {
			Ext.ux.Toast.msg("信息", "请选择要删除的记录！");
			return;
		}
		var ids = Array();
		for (var i = 0; i < selectRecords.length; i++) {
			ids.push(selectRecords[i].data.id);
		}
		this.delByIds(ids);
	},
	
	/**
	 * 编辑记录
	 * @param {} record
	 */
	editRecord:function(record){
		new PhotoFolderForm({id:record.data.id}).show();
	},
	/**
	 * 管理列中的事件处理
	 * @param {} grid
	 * @param {} record
	 * @param {} action
	 * @param {} row
	 * @param {} col
	 */
	onRowAction:function(gridPanel, record, action, row, col) {
		switch(action) {
			case 'btn-del':
				this.delByIds(record.data.id);
				break;
			case 'btn-edit':
				this.editRecord(record);
				break;
			default:
				break;
		}
	},
	createNode:function(){
		var nodeId = this.selectedNode.id;
		var photoFolderForm = Ext.getCmp('photoFolderForm');
		if (typeof(photoFolderForm)=='undefined'||photoFolderForm == null) {
			if (nodeId > 0) {
				new PhotoFolderForm({parentId:nodeId}).show();
			} else {
				new PhotoFolderForm({parentId:0}).show();
			}
		}
	},
	editNode:function(){
		var nodeId = this.selectedNode.id;
		var parentId =  this.selectedNode.parentNode.id;
		var photoFolderForm = Ext.getCmp('photoFolderForm');
		if (typeof(photoFolderForm)=='undefined'||photoFolderForm == null) {
			new PhotoFolderForm({id:nodeId,parentId:parentId}).show();
		}
	},
	deteleNode:function(){
		var nodeId = this.selectedNode.id;
		var photoTreePanel = Ext.getCmp("photoTreePanel");
		 if(Ext.MessageBox.confirm("提示" , "删除此文件夹,会删除子文件夹及其图片，确定删除此文件夹吗?" , function(btn){
				if(btn=="yes"){
					Ext.Ajax.request({
						url:__ctxPath+'/hrm/singleDelPhotoFolder.do?id='+nodeId,
						success:function(response,options){
							var respText = Ext.util.JSON.decode(response.responseText);
							Ext.MessageBox.alert('成功' ,'删除成功!')
							photoTreePanel.root.reload();
							photoTreePanel.fireEvent('click',photoTreePanel.getRootNode());
						},
						failure: function(response,options) { 
		                     var respText = Ext.util.JSON.decode(response.responseText);    
		                     Ext.Msg.alert('错误', response.error);    
		                }
					})
				}
	   	}));
		
	},
	deleteViewImages:function(photoId,photoFolderView){
	   if(Ext.MessageBox.confirm("提示" , "确定删除此图片吗?" , function(btn){
				if(btn=="yes"){
					Ext.Ajax.request({
						url:__ctxPath+'/hrm/singleDelPhoto.do?id='+photoId,
						success:function(response,options){
							var respText = Ext.util.JSON.decode(response.responseText);
							Ext.MessageBox.alert('成功' ,'删除成功!')
							photoFolderView.store.reload();
						},
						failure: function(response,options) {    
		                     var respText = Ext.util.JSON.decode(response.responseText);    
		                     Ext.Msg.alert('错误', response.error);    
		                }
					})
				}
	   	}));
	},
	toPhotoDetail:function(photoId,photoFolderView){
		App.clickTopTab('PhotoDetail',photoId,function(){
			AppUtil.removeTab('PhotoDetail');
		});
	}
});
