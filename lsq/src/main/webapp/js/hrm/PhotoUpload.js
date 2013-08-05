Ext.ns("PhotoUpload");
var outStore;
PhotoUpload = Ext.extend(Ext.Panel,{
	dataView:null,
	store:null,
	topbar:null,
	idArr:new Array(),
	mainPanel:null,
	constructor:function(_cfg){
			Ext.applyIf(this,_cfg);
			//初始化组件
			this.initUIComponents();
			//调用父类构造
			PhotoUpload.superclass.constructor.call(this,{
				id:'PhotoUpload',
				title:'上传图片',
				region:'center',
				layout:'border',
				items:[this.mainPanel],
				listeners:{
					'close' :function(){
							this.idArr.splice(0,this.idArr.length);
					}
				}
			});
	},
	initUIComponents:function(){
		//加载数据至store
		var thiz = this;
		this.store = new Ext.data.JsonStore({
							url : __ctxPath+"/system/fileListFileAttach.do",
							root : 'result',
							totalProperty : 'totalCounts',
							remoteSort : true,
							fields : ['fileName','filePath','fileId','note']
		});
		var deleteStr = '<div><input type="button"  value="删除" onclick="Ext.getCmp(\'PhotoUpload\').deleteImages(Ext.getCmp(\'PhotoUpload\'),{fileId});"></div>';
		var tpl = new Ext.XTemplate(
		    '<tpl for=".">',
                '<div class="thumb-wrap1" id="attachFiles/{filePath}|{fileName}|{fileId}">',
                deleteStr,
		        '<div class="thumb"><a href="'+__ctxPath+'/attachFiles/{filePath}" class="highslide" id="{fileId}" onclick="return hs.expand(this)" ><img src="'+__ctxPath+'/attachFiles/{filePath}" alt="Highslide JS" title="Click to enlarge" /></a></div>',
		        '<span class="">{fileName}</span>',
		        '<span>{note}</span>',
		        '</div>', //x-editable可编辑  .thumb-wrap span
            '</tpl>',
            '<div class="x-clear"></div>'
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
        
       		var url = __ctxPath + "/hrm/listPhotoFolder.do";
			var photoTreeSelector = new TreeSelector("photoTreeSelector", url, "文件夹", "photo.photoFolder.id");
			var toolbar = new Ext.Toolbar({
					height : 30,
					bodyStyle : 'text-align:left',
					items : [{iconCls : 'btn-add',text: "添加图片", handler: this.insertImages.createCallback(this)}, "-",
							{xtype:'label',text:'请选择上传文件夹:'},photoTreeSelector,"-",
	                		{iconCls : 'btn-save',text: "保存图片", handler: this.saveImages.createCallback(this)},"-",
	                		{xtype:'label',style:'color:red',text:'使用说明：先添加图片，然后选择上传文件夹，再保存图片'}
	                		]
				});
         this.mainPanel = new Ext.Panel({
            id:'images-view',
            frame:true,
            width:535,
            region:'center',
            autoHeight:true,
            collapsible:true,
            layout:'fit',
            title:'图片列表(0 项 被选中)',
            tbar:toolbar,
            butttonAlign: "left",
            tools: [{
                id:"refresh",
                qtip:"刷新相片列表",
                on:{click:function(){
    	                thiz.mainPanel.body.mask("加载中...", 'x-mask-loading');
    	                setTimeout(function(){
	                		var params1 = "";
							for(var i=0;i<thiz.idArr.length;i++){
									if(i==0){
										params1 += thiz.idArr[i]
									}else
									params1+=","+thiz.idArr[i];
								}
							if(params1!=""){
								thiz.store.reload({params:{"fileIds":params1}});
							}
							outStore = thiz.store;
        	                thiz.mainPanel.body.unmask();
    	                }, 1000);
	                }
                }
            }],
            items: this.dataview
        });
	},
	insertImages:function(panel){
		var dialog = App.createUploadDialog({
							file_cat : 'hrm/photo',
							callback : function(data) {
								var fileIds = Ext.getCmp('baseAttachIDs');
								var display = Ext.getCmp('displayBaseAttach');
								for (var i = 0; i < data.length; i++) {
									panel.idArr.push(data[i].fileId);
								}
								var params1 = "";
								for(var i=0;i<panel.idArr.length;i++){
										if(i==0){
											params1 += panel.idArr[i]
										}else
										params1+=","+panel.idArr[i];
									}
								if(params1!=""){
									panel.store.load({params:{"fileIds":params1}});
								}
								outStore = panel.store;
								
			},
			permitted_extensions : ['jpg','JPG','jpeg','JPEG','BMP','bmp','PNG','png','GIF','gif'],
			permitted_max_size : 1024*1024*800
		});
		dialog.show(this);
		
	},
	saveImages:function(panel){
		var folderId = Ext.getCmp("photoTreeSelector").id;
		if(folderId=='photoTreeSelector'){
			Ext.Msg.alert('提示', '请选择保存的文件夹');  
			return;
		}
		if(panel.idArr!=null&&panel.idArr.length>0){
			var fileIds = "";
			var idArr = panel.idArr;
			Ext.each(idArr,function(item,i){
				if(i==0){
					fileIds +=item;
				}else{
					fileIds +=","+item;
				}
			})
			Ext.Ajax.request({
				url:__ctxPath+'/hrm/savePhoto.do?fileIds='+fileIds,
				params:{'folderId':folderId},
				success:function(response,options){
					var respText = Ext.util.JSON.decode(response.responseText);
					outStore.removeAll();
					panel.idArr.splice(0,panel.idArr.length);
					Ext.MessageBox.alert('成功' ,'保存成功!')
				},
				failure: function(response,options) {    
                     var respText = Ext.util.JSON.decode(response.responseText);    
                     Ext.Msg.alert('错误', response.error);    
                }
			})
		}
	},
	deleteImages:function(photoUpload,fileId){
		var idArr = photoUpload.idArr;
	    if(Ext.MessageBox.confirm("提示" , "确定删除此图片吗?" , function(btn){
				if(btn=="yes"){
					var delIndex=0;
					for(var i = 0;i<idArr.length;i++){
						if(idArr[i]==fileId){
							delIndex = i;
						}
					}
					idArr.splice(delIndex,1);
					var params1 = "";
						for(var i=0;i<idArr.length;i++){
								if(i==0){
									params1 += idArr[i]
								}else
								params1+=","+idArr[i];
						}
					if(params1!=""){
						outStore.reload({params:{"fileIds":params1}});
					}else{
						outStore.removeAll();
					}
				}
	   	}));
	
	}
})
