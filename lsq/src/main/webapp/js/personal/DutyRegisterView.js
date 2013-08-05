Ext.ns("DutyRegisterView");var DutyRegisterView=function(){return new Ext.Panel({id:"DutyRegisterView",iconCls:"menu-dutyRegister",title:"考勤管理",autoScroll:true,items:[new Ext.FormPanel({id:"DutyRegisterSearchForm",height:40,frame:false,border:false,layout:"column",bodyStyle:"padding:6px 2px 0 6px",layoutConfig:{padding:"5",align:"middle"},defaults:{xtype:"label",style:"padding-top:2px"},items:[{text:"查询条件:"},{text:"上下班"},{hiddenName:"Q_inOffFlag_SN_EQ",xtype:"combo",width:80,mode:"local",editable:true,triggerAction:"all",store:[["1","上班"],["2","下班"]]},{text:"所属用户"},{xtype:"textfield",readOnly:true,name:"DR_fullname",id:"DR_fullname",width:80},{xtype:"hidden",name:"Q_appUser.userId_L_EQ",id:"Q_appUser.userId_L_EQ"},{xtype:"button",text:"选择",iconCls:"btn-select",width:50,handler:function(){UserSelector.getView(function(c,d){var b=Ext.getCmp("Q_appUser.userId_L_EQ");var a=Ext.getCmp("DR_fullname").setValue(d);b.setValue(c);},true).show();}},{text:"考勤选项"},{hiddenName:"Q_regFlag_SN_EQ",xtype:"combo",width:100,mode:"local",editable:true,triggerAction:"all",store:[["1","√"],["2","迟到"],["3","早退"]]},{xtype:"button",text:"查询",iconCls:"search",handler:function(){var a=Ext.getCmp("DutyRegisterSearchForm");var b=Ext.getCmp("DutyRegisterGrid");if(a.getForm().isValid()){a.getForm().submit({waitMsg:"正在提交查询",url:__ctxPath+"/personal/listDutyRegister.do",success:function(d,e){var c=Ext.util.JSON.decode(e.response.responseText);b.getStore().loadData(c);}});}}}]}),this.setup()]});};DutyRegisterView.prototype.setup=function(){return this.grid();};DutyRegisterView.prototype.grid=function(){var b=["周日","周一","周二","周三","周四","周五","周六"];var e=new Ext.grid.CheckboxSelectionModel();var a=new Ext.grid.ColumnModel({columns:[e,new Ext.grid.RowNumberer(),{header:"registerId",dataIndex:"registerId",hidden:true},{header:"登记时间",dataIndex:"registerDate"},{header:"登记人",dataIndex:"fullname"},{header:"登记标识",dataIndex:"regFlag",renderer:function(f){if(f==1){return'<font color="green">√</font>';}else{if(f==2){return'<font color="red">迟到</font>';}else{if(f==3){return'<font color="red">早退</font>';}}}}},{header:"周几",dataIndex:"dayOfWeek",renderer:function(f){return b[f-1];}},{header:"上下班标识",dataIndex:"inOffFlag",renderer:function(f){if(f==1){return"上班";}else{return"下班";}}},{header:"迟到或早退分钟",dataIndex:"regMins"},{header:"迟到或早退原因",dataIndex:"reason"},{header:"管理",dataIndex:"registerId",width:50,sortable:false,renderer:function(i,h,f,l,g){var k=f.data.registerId;var j="";if(isGranted("_DutyRegisterDel")){j='<button title="删除" value=" " class="btn-del" onclick="DutyRegisterView.remove('+k+')">&nbsp;&nbsp;</button>';}return j;}}],defaults:{sortable:true,menuDisabled:false,width:100}});var c=this.store();c.load({params:{start:0,limit:25}});var d=new Ext.grid.GridPanel({id:"DutyRegisterGrid",tbar:this.topbar(),store:c,trackMouseOver:true,disableSelection:false,loadMask:true,autoHeight:true,cm:a,sm:e,viewConfig:{forceFit:true,enableRowBody:false,showPreview:false},bbar:new Ext.PagingToolbar({pageSize:25,store:c,displayInfo:true,displayMsg:"当前显示从{0}至{1}， 共{2}条记录",emptyMsg:"当前没有记录"})});AppUtil.addPrintExport(d);return d;};DutyRegisterView.prototype.store=function(){var a=new Ext.data.Store({proxy:new Ext.data.HttpProxy({url:__ctxPath+"/personal/listDutyRegister.do"}),reader:new Ext.data.JsonReader({root:"result",totalProperty:"totalCounts",id:"id",fields:[{name:"registerId",type:"int"},"registerDate","fullname","regFlag","regMins","reason","dayOfWeek","inOffFlag"]}),remoteSort:true});a.setDefaultSort("registerDate","desc");return a;};DutyRegisterView.prototype.topbar=function(){var a=new Ext.Toolbar({id:"DutyRegisterFootBar",height:30,bodyStyle:"text-align:left",items:[]});if(isGranted("_DutyRegisterAdd")){a.add(new Ext.Button({iconCls:"btn-add",text:"补签",handler:function(){new DutyRegisterForm();}}));}if(isGranted("_DutyRegisterDel")){a.add(new Ext.Button({iconCls:"btn-del",text:"删除考勤",handler:function(){var c=Ext.getCmp("DutyRegisterGrid");var b=c.getSelectionModel().getSelections();if(b.length==0){Ext.ux.Toast.msg("信息","请选择要删除的记录！");return;}Ext.MessageBox.confirm("操作信息","你确定要删除该记录吗？",function(f){if(f=="yes"){var e=Array();for(var d=0;d<b.length;d++){e.push(b[d].data.registerId);}DutyRegisterView.remove(e);}});}}));}return a;};DutyRegisterView.remove=function(b){var a=Ext.getCmp("DutyRegisterGrid");Ext.Msg.confirm("信息确认","您确认要删除该记录吗？",function(c){if(c=="yes"){Ext.Ajax.request({url:__ctxPath+"/personal/multiDelDutyRegister.do",params:{ids:b},method:"post",success:function(){Ext.ux.Toast.msg("信息提示","成功删除所选记录！");a.getStore().reload({params:{start:0,limit:25}});}});}});};