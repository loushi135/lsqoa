EmpProfileForm = Ext.extend(Ext.Panel, {
    formPanel: null,
    constructor: function(a) {
        if (a == null) {
            a = {};
        }
        Ext.apply(this, a);
        this.initComponents();
        EmpProfileForm.superclass.constructor.call(this, {
            id: "EmpProfileForm",
            iconCls: "menu-profile-create",
            layout: "fit",
            items: this.formPanel,
            modal: true,
            height: 200,
            tbar: this.topbar,
            width: 400,
            maximizable: true,
            title: "档案详细信息",
            buttonAlign: "center",
            buttons: this.buttons
        });
    },
    topbar: null,
    initComponents: function() {
        this.formPanel = new Ext.FormPanel({
            layout: "form",
            autoScroll: true,
            trackResetOnLoad:true,
            tbar: this.topbar,
            labelWidth : 120,
            bodyStyle: "padding:10px 20px 10px 10px",
            border: false,
            url: __ctxPath + "/hrm/saveEmpProfile.do",
            defaults: {
                anchor: "98%,98%"
            },
            defaultType: "textfield",
            items: [{
                name: "empProfile.profileId",
                id: "profileId",
                xtype: "hidden",
                value: this.profileId == null ? "": this.profileId
            },
            {
                fieldLabel: "建档人",
                name: "empProfile.creator",
                xtype: "hidden",
                id: "creator"
            },
            {
                fieldLabel: "建档时间",
                name: "empProfile.createtime",
                xtype: "hidden",
                id: "createtime"
            },
            {
                fieldLabel: "审核人",
                name: "empProfile.checkName",
                xtype: "hidden",
                id: "checkName"
            },
            {
                fieldLabel: "审核时间",
                name: "empProfile.checktime",
                xtype: "hidden",
                id: "checktime"
            },
            {
                fieldLabel: "核审状态",
                name: "empProfile.approvalStatus",
                xtype: "hidden",
                id: "approvalStatus"
            },
            {
                fieldLabel: "所属部门Id",
                name: "empProfile.dept.depId",
                xtype: "hidden",
                id: "empProfile.dept.depId"
            },{
                fieldLabel: "所属部门Id",
                name: "empProfile.depName",
                xtype: "hidden",
                id: "depName"
            },
            {
                fieldLabel: "删除状态",
                name: "empProfile.delFlag",
                xtype: "hidden",
                id: "delFlag"
            },
            {
                fieldLabel: "所属职位",
                name: "empProfile.job.jobId",
                xtype: "hidden",
                id: "empProfile.job.jobId"
            },
            {
                fieldLabel: "照片",
                name: "empProfile.photo",
                xtype: "hidden",
                id: "photo"
            },
            {
                fieldLabel: "所属员工ID",
                name: "empProfile.appUser.userId",
                xtype: "hidden",
                id: "empProfile.appUser.userId"
            },
//            ,
//            {
//                xtype: "container",
//                layout: "column",
//                height: 26,
//                anchor: "100%",
//                items: [{
//                    xtype: "label",
//                    style: "padding:3px 5px 0px 17px;",
//                    text: "档案编号:"
//                },
//                {
//                    name: "empProfile.profileNo",
//                    width: 200,
//                    xtype: "textfield",
//                    id: "profileNo",
//                    readOnly: true,
//                    allowBlank: false,
//                    blankText: "档案编号不能为空!"
//                }
//                ,
//                {
//                    xtype: "button",
//                    autoWidth: true,
//                    id: "EmpProfileSystemSetting",
//                    text: "系统生成",
//                    iconCls: "btn-system-setting",
//                    handler: function() {
//                        Ext.Ajax.request({
//                            url: __ctxPath + "/hrm/numberEmpProfile.do",
//                            success: function(d) {
//                                var c = Ext.util.JSON.decode(d.responseText);
//                                Ext.getCmp("profileNo").setValue(c.profileNo);
//                            }
//                        });
//                    }
//                }
//                ]
//            },
            {
                xtype: "container",
                height: 26,
                layout: "column",
                anchor: "100%",
                items: [{
                    xtype: "label",
                    style: "padding:3px 5px 0px 17px;",
                    text: "员工姓名:"
                },
                {
                    width: 200,
                    xtype: "textfield",
                    name: "empProfile.fullname",
                    id: "fullname",
                    allowBlank: false,
                    blankText: "姓名不能为空！",
                    readOnly: true
                },
                {
                    xtype: "button",
                    id: "EmpProfileSelectEmp",
                    text: "选择员工",
                    iconCls: "btn-mail_recipient",
                    handler: function() {
                        UserSelector.getView(function(d, c) {
                            Ext.getCmp("fullname").setValue(c);
                            Ext.getCmp("empProfile.appUser.userId").setValue(d);
                            Ext.Ajax.request({
                                url: __ctxPath + "/system/getAppUser.do",
                                params: {
                                    userId: d
                                },
                                method: "post",
                                success: function(f) {
                                    var e = Ext.util.JSON.decode(f.responseText).data[0];
                                    Ext.getCmp("empProfile.dept.depId").setValue(e.department.depId);
                                    Ext.getCmp("depName").setValue(e.department.depName);
                                }
                            });
                        },
                        true).show();
                    }
                }]
            },
            {
                xtype: "fieldset",
                title: "基本资料",
                layout: "column",
                items: [{
                    xtype: "container",
                    columnWidth: 0.37,
                    defaultType: "textfield",
                    layout: "form",
                    defaults: {
                        anchor: "100%,100%"
                    },
                    items: [{
                        fieldLabel: "身份证号",
                        name: "empProfile.idCard",
                        id: "idCard",
                        allowBlank:false,
                        maxLength:64
                    },
                    {
                        fieldLabel: "出生日期",
                        name: "empProfile.birthday",
                        id: "birthday",
                        xtype: "datefield",
                        format: "Y-m-d"
                    },
                    {
                        fieldLabel: "性别",
                        name: "empProfile.sex",
                        id: "sex",
                        xtype: "combo",
                        editable: false,
                        mode: "local",
                        triggerAction: "all",
                        store: ["男", "女"]
                    },
                    {
                        fieldLabel: "婚姻状况",
                        hiddenName: "empProfile.dicMarriage.dicId",
                        id: "dicMarriage",
                        xtype: "combo",
                        mode : 'local',
						valueField : "dicId",
						displayField : "itemValue",
						triggerAction : "all",
						editable : false,
						allowBlank : false,
						store : new Ext.data.JsonStore({
							baseParams : {
								"Q_itemName_S_EQ" : '金螳螂-婚姻状况'
							},
							autoLoad : true,
							root : "result",
							totalProperty : "totalCounts",
							url : __ctxPath + "/system/listDictionary.do",
							fields : ["dicId", "itemName", "itemValue"]
						})
                    },{
                        fieldLabel: "编制",
                        hiddenName: "empProfile.dicPlait.dicId",
                        id: "dicPlait",
                        xtype: "combo",
                        mode : 'local',
						valueField : "dicId",
						displayField : "itemValue",
						triggerAction : "all",
						editable : false,
						allowBlank : false,
						store : new Ext.data.JsonStore({
							baseParams : {
								"Q_itemName_S_EQ" : '金螳螂-编制'
							},
							autoLoad : true,
							root : "result",
							totalProperty : "totalCounts",
							url : __ctxPath + "/system/listDictionary.do",
							fields : ["dicId", "itemName", "itemValue"]
						})
                    },{
                        fieldLabel: "职类",
                        hiddenName: "empProfile.dicProfessionType.dicId",
                        id: "dicProfessionType",
                        xtype: "combo",
                        mode : 'local',
						valueField : "dicId",
						displayField : "itemValue",
						triggerAction : "all",
						editable : false,
						allowBlank : false,
						store : new Ext.data.JsonStore({
							baseParams : {
								"Q_itemName_S_EQ" : '金螳螂-职类'
							},
							autoLoad : true,
							root : "result",
							totalProperty : "totalCounts",
							url : __ctxPath + "/system/listDictionary.do",
							fields : ["dicId", "itemName", "itemValue"]
						})
                    }
                    ]
                },
                {
                    xtype: "container",
                    columnWidth: 0.37,
                    defaultType: "textfield",
                    layout: "form",
                    defaults: {
                        anchor: "100%,100%"
                    },
                    items: [
                    {
                        fieldLabel: "政治面貌",
                        hiddenName: "empProfile.dicParty.dicId",
                        id: "dicParty",
                        maxHeight: 200,
                        xtype: "combo",
                        mode : 'local',
						valueField : "dicId",
						displayField : "itemValue",
						triggerAction : "all",
						editable : false,
						allowBlank : false,
						store : new Ext.data.JsonStore({
							baseParams : {
								"Q_itemName_S_EQ" : '金螳螂-政治身份'
							},
							autoLoad : true,
							root : "result",
							totalProperty : "totalCounts",
							url : __ctxPath + "/system/listDictionary.do",
							fields : ["dicId", "itemName", "itemValue"]
						})
                    },
                    {
                        fieldLabel: "国籍",
                        hiddenName: "empProfile.dicNationality.dicId",
                        id: "dicNationality",
                        maxHeight: 200,
                        xtype: "combo",
                        mode : 'local',
						valueField : "dicId",
						displayField : "itemValue",
						triggerAction : "all",
						editable : false,
						allowBlank : false,
						store : new Ext.data.JsonStore({
							baseParams : {
								"Q_itemName_S_EQ" : '金螳螂-国籍'
							},
							autoLoad : true,
							root : "result",
							totalProperty : "totalCounts",
							url : __ctxPath + "/system/listDictionary.do",
							fields : ["dicId", "itemName", "itemValue"]
						})
                    },
                    {
                        fieldLabel: "民族",
                        hiddenName: "empProfile.dicRace.dicId",
                        id: "dicRace",
                        maxHeight: 200,
                        xtype: "combo",
                        mode : 'local',
						valueField : "dicId",
						displayField : "itemValue",
						triggerAction : "all",
						editable : false,
						allowBlank : false,
						store : new Ext.data.JsonStore({
							baseParams : {
								"Q_itemName_S_EQ" : '金螳螂-民族'
							},
							autoLoad : true,
							root : "result",
							totalProperty : "totalCounts",
							url : __ctxPath + "/system/listDictionary.do",
							fields : ["dicId", "itemName", "itemValue"]
						})
                    },
                    {
                        fieldLabel: "家庭地址",
                        name: "empProfile.address",
                        id: "address"
                    },{
                        fieldLabel: "职位",
                        name: "empProfile.position",
                        id: "position",
                        xtype: "combo",
                        mode: "local",
                        allowBlank: false,
                        editable: false,
                        valueField: "jobName",
                        displayField: "jobName",
                        triggerAction: "all",
                        store: new Ext.data.SimpleStore({
                            url: __ctxPath + "/hrm/comboJob.do",
                            fields: ["jobId", "jobName"]
                        }),
                        listeners: {
                            focus: function(d) {
                                var c = Ext.getCmp("empProfile.dept.depId").getValue();
                                if (c != null && c != "" && c != "undefined") {
                                    Ext.getCmp("position").getStore().reload({
                                        params: {
                                            depId: c
                                        }
                                    });
                                } else {
                                    Ext.ux.Toast.msg("操作信息", "请先选择员工！");
                                }
                            },
                            select: function(field, record, d) {
                                Ext.getCmp("empProfile.job.jobId").setValue(record.data.jobId);
                            }
                        }
                    },{
                        fieldLabel: "职级",
                        hiddenName: "empProfile.dicProfessionLevel.dicId",
                        id: "dicProfessionLevel",
                        xtype: "combo",
                        mode : 'local',
						valueField : "dicId",
						displayField : "itemValue",
						triggerAction : "all",
						editable : false,
						allowBlank : false,
						store : new Ext.data.JsonStore({
							baseParams : {
								"Q_itemName_S_EQ" : '金螳螂-职级'
							},
							autoLoad : true,
							root : "result",
							totalProperty : "totalCounts",
							url : __ctxPath + "/system/listDictionary.do",
							fields : ["dicId", "itemName", "itemValue"]
						})
                    }]},
                {
                    xtype: "container",
                    columnWidth: 0.26,
                    layout: "column",
                    items: [{
                        xtype: "label",
                        text: "个人相片:",
                        style: "padding-left:0px;"
                    },
                    {
                        xtype: "container",
                        layout: "form",
                        width: 100,
                        items: [{
                            id: "ProfilePhotoPanel",
                            height: 120,
                            width: 88,
                            xtype: "panel",
                            border: true,
                            html: '<img src="' + __ctxPath + '/images/default_person.gif" width="88" height="120"/>'
                        },
                        {
                            xtype: "button",
                            style: "padding-top:3px;",
                            iconCls: "btn-upload",
                            text: "上传照片",
                            handler: function() {
                                var d = Ext.getCmp("photo");
                                var e = App.createUploadDialog({
                                    file_cat: "hrm/profile",
                                    callback: function c(h) {
                                        var g = Ext.getCmp("photo");
                                        var i = Ext.getCmp("ProfilePhotoPanel");
                                        g.setValue(h[0].filepath);
                                        i.body.update('<img src="' + __ctxPath + "/attachFiles/" + h[0].filepath + '"  width="88" height="120"/>');
                                    },
                                    permitted_extensions: ["jpg", "png"]
                                });
                                if (d.getValue() != "" && d.getValue() != null && d.getValue() != "undefined") {
                                    var f = "再次上传需要先删除原有图片,";
                                    Ext.Msg.confirm("信息确认", f + "是否删除？",
                                    function(g) {
                                        if (g == "yes") {
                                            var h = Ext.getCmp("profileId").getValue();
                                            Ext.Ajax.request({
                                                url: __ctxPath + "/hrm/delphotoEmpProfile.do",
                                                method: "post",
                                                params: {
                                                    profileId: h
                                                },
                                                success: function() {
                                                    var i = d.value;
                                                    Ext.getCmp("photo").setValue("");
                                                    Ext.getCmp("ProfilePhotoPanel").body.update('<img src="' + __ctxPath + '/images/default_person.gif" width="88" height="120"/>');
                                                    Ext.Ajax.request({
                                                        url: __ctxPath + "/system/deleteFileAttach.do",
                                                        method: "post",
                                                        params: {
                                                            filePath: i
                                                        },
                                                        success: function() {
                                                            e.show("queryBtn");
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    });
                                } else {
                                    e.show("queryBtn");
                                }
                            }
                        }]
                    }]
                }]
            },
            {
                xtype: "fieldset",
                title: "其他情况",
                defaultType: "textfield",
                layout: "column",
                items: [{
                    xtype: "container",
                    columnWidth: 0.5,
                    defaultType: "textfield",
                    layout: "form",
                    defaults: {
                        anchor: "100%,100%"
                    },
                    items: [{
                        fieldLabel: "家乡",
                        name: "empProfile.homeAddr",
                        id: "homeAddr"
                    },{
                        fieldLabel: "花名册岗位",
                        name: "empProfile.rosterJobName",
                        id: "rosterJobName",
                        allowBlank:false,
                        maxLength:64
                    },{
                        fieldLabel: "学历",
                        hiddenName: "empProfile.dicEduDegree.dicId",
                        id: "dicEduDegree",
                        xtype: "combo",
                        mode : 'local',
						valueField : "dicId",
						displayField : "itemValue",
						triggerAction : "all",
						editable : false,
						allowBlank : false,
						store : new Ext.data.JsonStore({
							baseParams : {
								"Q_itemName_S_EQ" : '金螳螂-学历'
							},
							autoLoad : true,
							root : "result",
							totalProperty : "totalCounts",
							url : __ctxPath + "/system/listDictionary.do",
							fields : ["dicId", "itemName", "itemValue"]
						})
                    },
                    {
                        fieldLabel: "专业",
                        name: "empProfile.eduMajor",
                        id: "eduMajor",
                        maxLength:64
                    },
                    {
                        fieldLabel: "毕业时间",
                        name: "empProfile.graduateDate",
                        id: "graduateDate",
                        xtype: "datefield",
                        format: "Y-m-d"
                    },
                    {
                        fieldLabel: "补发工资时间",
                        name: "empProfile.rePayWageTime",
                        id: "rePayWageTime",
                        xtype: "datefield",
                        format: "Y-m-d"
                    },{
                        fieldLabel: "人事职称",
                        hiddenName: "empProfile.dicHrmTitle.dicId",
                        id: "dicHrmTitle",
                        xtype: "combo",
                        mode : 'local',
						valueField : "dicId",
						displayField : "itemValue",
						triggerAction : "all",
						editable : false,
						allowBlank : false,
						store : new Ext.data.JsonStore({
							baseParams : {
								"Q_itemName_S_EQ" : '金螳螂-人事职称'
							},
							autoLoad : true,
							root : "result",
							totalProperty : "totalCounts",
							url : __ctxPath + "/system/listDictionary.do",
							fields : ["dicId", "itemName", "itemValue"]
						})
                    },{
                        fieldLabel: "健康状况",
                        name: "empProfile.healthStatus",
                        id: "healthStatus",
                        maxLength:64
                    },{
                        fieldLabel: "宅电",
                        name: "empProfile.homeTel",
                        id: "homeTel",
                        maxLength:64
                    },{
                        fieldLabel: "新员工培训批次",
                        name: "empProfile.newStaffTrain",
                        id: "newStaffTrain",
                        allowBlank : false,
                        maxLength:64
                    },{
                        fieldLabel: "统计批次",
                        name: "empProfile.statisticsTime",
                        id: "statisticsTime",
                        regex:/^(1[89]\d\d|2[01][01]\d)-(1[0-2]|0\d)$/,
						emptyText:'格式：2013-04',
						regexText:'格式：2013-04',
                        maxLength:64
                    },{
                        fieldLabel: "新版承诺",
                        name: "empProfile.dicNewTypeCommitment.dicId",
                        id: "dicNewTypeCommitment",
                        xtype:'radiogroup',
                        columns :8,
						items:[
								{
								  name:'empProfile.dicNewTypeCommitment.dicId',
								  boxLabel:'有',
								  inputValue:'925'
								},{
								  name:'empProfile.dicNewTypeCommitment.dicId',
								  boxLabel:'无',
								  inputValue:'926',
								  checked:true
								}
						]
                    },{
                        fieldLabel: "社保缴纳地",
                        hiddenName: "empProfile.dicSocialSecurityPlace.dicId",
                        id: "dicSocialSecurityPlace",
                        xtype: "combo",
                        mode : 'local',
						valueField : "dicId",
						displayField : "itemValue",
						triggerAction : "all",
						editable : false,
						allowBlank : false,
						store : new Ext.data.JsonStore({
							baseParams : {
								"Q_itemName_S_EQ" : '金螳螂-社保缴纳地'
							},
							autoLoad : true,
							root : "result",
							totalProperty : "totalCounts",
							url : __ctxPath + "/system/listDictionary.do",
							fields : ["dicId", "itemName", "itemValue"]
						})
                    }]
                },
                {
                    xtype: "container",
                    columnWidth: 0.5,
                    defaultType: "textfield",
                    layout: "form",
                    defaults: {
                        anchor: "100%,100%"
                    },
                    items: [{
                        fieldLabel: "劳动合同类型",
                        hiddenName: "empProfile.dicWorkContractType.dicId",
                        id: "dicWorkContractType",
                        xtype: "combo",
                        mode : 'local',
						valueField : "dicId",
						displayField : "itemValue",
						triggerAction : "all",
						editable : false,
						allowBlank : false,
						store : new Ext.data.JsonStore({
							baseParams : {
								"Q_itemName_S_EQ" : '金螳螂-劳动合同类型'
							},
							autoLoad : true,
							root : "result",
							totalProperty : "totalCounts",
							url : __ctxPath + "/system/listDictionary.do",
							fields : ["dicId", "itemName", "itemValue"]
						})
                    },
                    {
                        fieldLabel: "劳动合同到期时间",
                        name: "empProfile.workContractEndDate",
                        id: "workContractEndDate",
                        xtype: "datefield",
                        format: "Y-m-d"
                    },{
                        fieldLabel: "毕业院校",
                        name: "empProfile.eduCollege",
                        id: "eduCollege",
                        maxLength:64
                    },
                    {
                        fieldLabel: "参加工作时间",
                        name: "empProfile.startWorkDate",
                        id: "startWorkDate",
                        xtype: "datefield",
                        format: "Y-m-d"
                    },{
                        fieldLabel: "档案所在机构",
                        name: "empProfile.profilePlace",
                        id: "profilePlace",
                        maxLength:64
                    },{
                        fieldLabel: "人事职称描述",
                        name: "empProfile.hrmTitleDesc",
                        id: "hrmTitleDesc",
                        maxLength:64
                    },{
                        fieldLabel: "职称级别",
                        hiddenName: "empProfile.dicHrmTitleLevel.dicId",
                        id: "dicHrmTitleLevel",
                        xtype: "combo",
                        mode : 'local',
						valueField : "dicId",
						displayField : "itemValue",
						triggerAction : "all",
						editable : false,
						allowBlank : false,
						store : new Ext.data.JsonStore({
							baseParams : {
								"Q_itemName_S_EQ" : '金螳螂-职称级别'
							},
							autoLoad : true,
							root : "result",
							totalProperty : "totalCounts",
							url : __ctxPath + "/system/listDictionary.do",
							fields : ["dicId", "itemName", "itemValue"]
						})
                    },{
                        fieldLabel: "血型",
                        hiddenName: "empProfile.dicBloodType.dicId",
                        id: "dicBloodType",
                        xtype: "combo",
                        mode : 'local',
						valueField : "dicId",
						displayField : "itemValue",
						triggerAction : "all",
						editable : false,
						allowBlank : false,
						store : new Ext.data.JsonStore({
							baseParams : {
								"Q_itemName_S_EQ" : '金螳螂-血型'
							},
							autoLoad : true,
							root : "result",
							totalProperty : "totalCounts",
							url : __ctxPath + "/system/listDictionary.do",
							fields : ["dicId", "itemName", "itemValue"]
						})
                    },{
                        fieldLabel: "其他电话",
                        name: "empProfile.otherTel",
                        id: "otherTel",
                        maxLength:64
                    },{
                        fieldLabel: "审批录入年月",
                        name: "empProfile.approvalTime",
                        id: "approvalTime",
                        regex:/^(1[89]\d\d|2[01][01]\d)(1[0-2]|0\d)$/,
						emptyText:'格式：201304',
						regexText:'格式：201304'
                    },
                    {
                        fieldLabel: "内评职称/其他证书",
                        name: "empProfile.innerCommentTitle",
                        id: "innerCommentTitle"
                    },{
                        fieldLabel: "项目经理等级",
                        hiddenName: "empProfile.dicProManagerLevel.dicId",
                        id: "dicProManagerLevel",
                        xtype: "combo",
                        mode : 'local',
						valueField : "dicId",
						displayField : "itemValue",
						triggerAction : "all",
						editable : false,
						allowBlank : false,
						store : new Ext.data.JsonStore({
							baseParams : {
								"Q_itemName_S_EQ" : '金螳螂-项目经理等级'
							},
							autoLoad : true,
							root : "result",
							totalProperty : "totalCounts",
							url : __ctxPath + "/system/listDictionary.do",
							fields : ["dicId", "itemName", "itemValue"]
						})
                    },{
                        fieldLabel: "拜师仪式",
                        name: "empProfile.masterCeremony",
                        id: "masterCeremony",
                        maxLength:64
                    }
                    ]
                }]
            },
            {
                xtype: "fieldset",
                title: "备注",
                layout: "anchor",
                items: [{
                    fieldLabel: "备注",
                    name: "empProfile.memo",
                    anchor: "100%",
                    id: "memo",
                    xtype: "textarea"
                }]
            }]
        });
        this.topbar = new Ext.Toolbar({
            height: 30,
            bodyStyle: "text-align:left",
            defaultType: "button",
            items: [{
                text: "保存",
                iconCls: "btn-save",
                handler: this.save.createCallback(this.formPanel, this)
            },
            {
                text: "重置",
                iconCls: "btn-reset",
                handler: this.reset.createCallback(this.formPanel)
            },
            {
                text: "取消",
                iconCls: "btn-cancel",
                handler: this.cancel.createCallback(this)
            }]
        });
        if (this.profileId != null && this.profileId != "undefined") {
            this.formPanel.getForm().load({
                deferredRender: false,
                url: __ctxPath + "/hrm/getEmpProfile.do?profileId=" + this.profileId,
                waitMsg: "正在载入数据...",
                success: function(form, action) {
                    var res = action.result.data;
                    var photo = res.photo;
                    if (photo != null && photo != "") {
                        Ext.getCmp("ProfilePhotoPanel").body.update('<img src="' + __ctxPath + "/attachFiles/" + photo + '" width="88" height="120"/>');
                    }
                    if(!Ext.isEmpty(res.dicEduDegree)){
						Ext.getCmp("dicEduDegree").setValue(res.dicEduDegree.dicId);
						Ext.getCmp("dicEduDegree").originalValue=res.dicEduDegree.dicId;
					}
					if(!Ext.isEmpty(res.dicWorkContractType)){
						Ext.getCmp("dicWorkContractType").setValue(res.dicWorkContractType.dicId);
						Ext.getCmp("dicWorkContractType").originalValue=res.dicWorkContractType.dicId;
					}
					if(!Ext.isEmpty(res.dicMarriage)){
						Ext.getCmp("dicMarriage").setValue(res.dicMarriage.dicId);
						Ext.getCmp("dicMarriage").originalValue=res.dicMarriage.dicId;
					}
					if(!Ext.isEmpty(res.dicParty)){
						Ext.getCmp("dicParty").setValue(res.dicParty.dicId);
						Ext.getCmp("dicParty").originalValue=res.dicParty.dicId;
					}
					if(!Ext.isEmpty(res.dicNationality)){
						Ext.getCmp("dicNationality").setValue(res.dicNationality.dicId);
						Ext.getCmp("dicNationality").originalValue=res.dicNationality.dicId;
					}
					if(!Ext.isEmpty(res.dicRace)){
						Ext.getCmp("dicRace").setValue(res.dicRace.dicId);
						Ext.getCmp("dicRace").originalValue=res.dicRace.dicId;
					}
					if(!Ext.isEmpty(res.dicBloodType)){
						Ext.getCmp("dicBloodType").setValue(res.dicBloodType.dicId);
						Ext.getCmp("dicBloodType").originalValue=res.dicBloodType.dicId;
					}
					if(!Ext.isEmpty(res.dicPlait)){
						Ext.getCmp("dicPlait").setValue(res.dicPlait.dicId);
						Ext.getCmp("dicPlait").originalValue=res.dicPlait.dicId;
					}
					if(!Ext.isEmpty(res.dicProfessionType)){
						Ext.getCmp("dicProfessionType").setValue(res.dicProfessionType.dicId);
						Ext.getCmp("dicProfessionType").originalValue=res.dicProfessionType.dicId;
					}
					if(!Ext.isEmpty(res.dicProfessionLevel)){
						Ext.getCmp("dicProfessionLevel").setValue(res.dicProfessionLevel.dicId);
						Ext.getCmp("dicProfessionLevel").originalValue=res.dicProfessionLevel.dicId;
					}
					if(!Ext.isEmpty(res.dicHrmTitle)){
						Ext.getCmp("dicHrmTitle").setValue(res.dicHrmTitle.dicId);
						Ext.getCmp("dicHrmTitle").originalValue=res.dicHrmTitle.dicId;
					}
					if(!Ext.isEmpty(res.dicHrmTitleLevel)){
						Ext.getCmp("dicHrmTitleLevel").setValue(res.dicHrmTitleLevel.dicId);
						Ext.getCmp("dicHrmTitleLevel").originalValue=res.dicHrmTitleLevel.dicId;
					}
					if(!Ext.isEmpty(res.dicNewTypeCommitment)){
						Ext.getCmp("dicNewTypeCommitment").setValue(res.dicNewTypeCommitment.dicId);
						Ext.getCmp("dicNewTypeCommitment").originalValue=res.dicNewTypeCommitment.dicId;
					}
					if(!Ext.isEmpty(res.dicProManagerLevel)){
						Ext.getCmp("dicProManagerLevel").setValue(res.dicProManagerLevel.dicId);
						Ext.getCmp("dicProManagerLevel").originalValue=res.dicProManagerLevel.dicId;
					}
					if(!Ext.isEmpty(res.dicSocialSecurityPlace)){
						Ext.getCmp("dicSocialSecurityPlace").setValue(res.dicSocialSecurityPlace.dicId);
						Ext.getCmp("dicSocialSecurityPlace").originalValue=res.dicSocialSecurityPlace.dicId;
					}
					if(!Ext.isEmpty(res.appUser)){
						Ext.getCmp("empProfile.appUser.userId").setValue(res.appUser.userId);
						Ext.getCmp("empProfile.appUser.userId").originalValue=res.appUser.userId;
					}
					if(!Ext.isEmpty(res.dept)){
						Ext.getCmp("empProfile.dept.depId").setValue(res.dept.depId);
						Ext.getCmp("empProfile.dept.depId").originalValue=res.dept.depId;
					}
					if(!Ext.isEmpty(res.job)){
						Ext.getCmp("empProfile.job.jobId").setValue(res.job.jobId);
						Ext.getCmp("empProfile.job.jobId").originalValue=res.job.jobId;
					}
                    Ext.getCmp("EmpProfileSystemSetting").hide();
//                    Ext.getCmp("empProfileForm.profileNo").getEl().dom.readOnly = true;
//                    Ext.getCmp("EmpProfileSelectEmp").hide();
                },
                failure: function(c, d) {}
            });
        }
    },
    reset: function(a) {
        a.getForm().reset();
    },
    cancel: function(a) {
        var b = Ext.getCmp("centerTabPanel");
        if (a != null) {
            b.remove("EmpProfileForm");
        }
    },
    save: function(a, b) {
        if (a.getForm().isValid()) {
            a.getForm().submit({
                method: "POST",
                waitMsg: "正在提交数据...",
                success: function(c, e) {
                    Ext.ux.Toast.msg("操作信息", "成功保存信息！");
                    var d = Ext.getCmp("EmpProfileGrid");
                    if (d != null) {
                        d.getStore().reload();
	                    AppUtil.removeTab("EmpProfileForm");
                    }
                },
                failure: function(c, d) {
                    Ext.MessageBox.show({
                        title: "操作信息",
                        msg: d.result.msg,
                        buttons: Ext.MessageBox.OK,
                        icon: Ext.MessageBox.ERROR
                    });
//                    Ext.getCmp("empProfileForm.profileNo").setValue("");
                }
            });
        }
    }
});