Ext.ns("App");
Ext.ns("AppUtil");
var jsCache = new Array();
function strToDom(a) {
	if (window.ActiveXObject) {
		var b = new ActiveXObject("Microsoft.XMLDOM");
		b.async = "false";
		b.loadXML(a);
		return b;
	} else {
		if (document.implementation && document.implementation.createDocument) {
			var c = new DOMParser();
			var b = c.parseFromString(a, "text/xml");
			return b;
		}
	}
}
function newView(viewName, params) {
	var str = "new " + viewName;
	if (params != null) {
		str += "(params);";
	} else {
		str += "();";
	}
	return eval(str);
}

/**

 * 数字格式转换成千分位

 *@param{Object}num

 */

function commafy(num){

   if((num+"").trim()==""){

      return"";

   }

   if(isNaN(num)){

      return"";

   }

   num = num+"";

   if(/^.*\..*$/.test(num)){

      var pointIndex =num.lastIndexOf(".");

      var intPart = num.substring(0,pointIndex);

      var pointPart =num.substring(pointIndex+1,num.length);

      intPart = intPart +"";

       var re =/(-?\d+)(\d{3})/

       while(re.test(intPart)){

          intPart =intPart.replace(re,"$1,$2")

       }

      num = intPart+"."+pointPart;

   }else{

      num = num +"";

       var re =/(-?\d+)(\d{3})/

       while(re.test(num)){

          num =num.replace(re,"$1,$2")

       }

   }

    return num;

}


/**

 * 去除千分位

 *@param{Object}num

 */



function delcommafy(num){

   if((num+"").trim()==""){

      return"";

   }

   num=num.replace(/,/gi,'');

   returnnum;

}

function $ImportJs(viewName, callback, params) {
	var b = jsCache[viewName];
	if (b != null) {
		var view = newView(viewName, params);
		callback.call(this, view);
	} else {
		var jsArr = eval("App.importJs." + viewName);
		if (jsArr == undefined || jsArr.length == 0) {
			try {
				var view = newView(viewName, params);
				callback.call(this, view);
			} catch (e) {
//				alert(e.message);
			}
			return;
		}
		ScriptMgr.load({
					scripts : jsArr,
					callback : function() {
						jsCache[viewName] = 0;
						var view = newView(viewName, params);
						callback.call(this, view);
					}
				});
	}
}
function $ImportSimpleJs(a, b) {
	ScriptMgr.load({
				scripts : a,
				callback : function() {
					if (b) {
						b.call(this);
					}
				}
			});
}
App.getContentPanel = function() {
	var a = Ext.getCmp("centerTabPanel");
	return a;
};
App.createUploadDialog = function(b) {
	var a = {
		file_cat : "others",
		url : __ctxPath + "/file-upload",
		reset_on_hide : false,
		upload_autostart : false,
		modal : true
	};
	Ext.apply(a, b);
	var c = new Ext.ux.UploadDialog.Dialog(a);
	return c;
};
function uniqueArray(e) {
	e = e || [];
	var b = {};
	for (var d = 0; d < e.length; d++) {
		var c = e[d];
		if (typeof(b[c]) == "undefined") {
			b[c] = 1;
		}
	}
	e.length = 0;
	for (var d in b) {
		e[e.length] = d;
	}
	return e;
}
function setCookie(b, d, a, f, c, e) {
	document.cookie = b + "=" + escape(d)
			+ ((a) ? "; expires=" + a.toGMTString() : "")
			+ ((f) ? "; path=" + f : "") + ((c) ? "; domain=" + c : "")
			+ ((e) ? "; secure" : "");
}
function getCookie(b) {
	var d = b + "=";
	var e = document.cookie.indexOf(d);
	if (e == -1) {
		return null;
	}
	var a = document.cookie.indexOf(";", e + d.length);
	if (a == -1) {
		a = document.cookie.length;
	}
	var c = document.cookie.substring(e + d.length, a);
	return unescape(c);
}
function deleteCookie(a, c, b) {
	if (getCookie(a)) {
		document.cookie = a + "=" + ((c) ? "; path=" + c : "")
				+ ((b) ? "; domain=" + b : "")
				+ "; expires=Thu, 01-Jan-70 00:00:01 GMT";
	}
}
String.prototype.trim = function() {
	return (this.replace(/^[\s\xA0]+/, "").replace(/[\s\xA0]+$/, ""));
};
function $request(a) {
	Ext.Ajax.request({
				url : a.url,
				params : a.params,
				method : a.method == null ? "POST" : a.method,
				success : function(b, c) {
					if (a.success != null) {
						a.success.call(this, b, c);
					}
				},
				failure : function(b, c) {
					Ext.ux.Toast.msg("操作信息", "操作出错，请联系管理员！");
					if (a.success != null) {
						a.failure.call(this, b, c);
					}
				}
			});
}
function asynReq() {
	var a = Ext.Ajax.getConnectionObject().conn;
	a.open("GET", url, false);
	a.send(null);
}
/**
 * 为GridPanel添加打印及导出功能
 * @param {} grid GridPanel
 */
AppUtil.addPrintExport=function(grid,text,fileName,sampleOvierr){
		var exportButton = new Ext.ux.Exporter.Button({
          component: grid,
          iconCls: '',
          text : text==null?'导出':text,
          fileName : fileName==null?'导出':fileName,
          sampleOvierr: sampleOvierr==null?false:sampleOvierr
        });
        
       // grid.getTopToolbar().add('->');
        grid.getTopToolbar().add(exportButton);
//		grid.getTopToolbar().add(
//				new Ext.Button({
//					text:'打印',
//					iconCls:'btn-print',
//					handler:function(){
//						Ext.ux.Printer.print(grid);
//					}
//				})
//		);
}
AppUtil.removeTab = function(a) {
	var b = App.getContentPanel();
	var c = b.getItem(a);
	if (c != null) {
		b.remove(c, true);
	}
};
AppUtil.activateTab = function(a) {
	var b = App.getContentPanel();
	b.activate(a);
};
AppUtil.addTab = function(view,params) {
	var contentPanel = App.getContentPanel();
	var item = contentPanel.getItem(view);
	if (item != null) {
		contentPanel.remove(item);
	}
	var newPanel = new newView(view,params);
	contentPanel.add(newPanel);
	contentPanel.activate(newPanel);
};
AppUtil.selectProcessForm=function(runId){
		var window = new Ext.Window({
			id : 'flowForm',
			title : '审批表单',
			width : 700,
			height:500,
			y:100,
			autoScroll:true,
			shadow : false,
			modal : true,
			layout : 'anchor',
			iconCls: "btn-showDetail",
			plain : true,
			bodyStyle : 'padding:5px;',
			buttonAlign : 'center',
			autoLoad: {
	            url: __ctxPath + "/flow/processRunDetail.do?runId=" + runId
	        }
		});
		
		window.show();
}
/**
 * 
 * @param {} obj
 * @param {} fileId
 * @param {} attachIDs
 */
function removeFileById(obj, fileId,attachIDs,fileHtml,attachFile){
	var fileIds = Ext.getCmp(attachIDs);
	var value = fileIds.getValue();
	if (value.indexOf(',') < 0) {// 仅有一个附件
		fileIds.setValue('');
	} else {
		value = value.replace(',' + fileId, '').replace(fileId + ',', '');
		fileIds.setValue(value);
	}
	
	var fileHtmls = Ext.getCmp(attachFile);
	
	if(fileHtmls!=null && fileHtmls!='undefined'){
	    var value = fileHtmls.getValue();
		if (value.indexOf(',') < 0) {// 仅有一个附件
			fileHtmls.setValue('');
		} else {
			value = value.replace(',' + fileHtml, '').replace(fileHtml + ',', '');
			fileHtmls.setValue(value);
		}
	}
	var el = Ext.get(obj.parentNode);
	el.remove();
}
	/**  
	 * 同步ajax调用 返回json Object  
	 *   
	 * @param {}  
	 *            urlStr  
	 * @param {}  
	 *            paramsStr 为字符串键值对形式“key=value&key2=value2”  
	 * @return {} 返回json Object  
	 */  
	function ajaxSyncCall(urlStr, paramsStr) {   
	    var obj;   
	    var value;   
	    if (window.ActiveXObject) {   
	        obj = new ActiveXObject('Microsoft.XMLHTTP');   
	    } else if (window.XMLHttpRequest) {   
	        obj = new XMLHttpRequest();   
	    }   
	    obj.open('POST', urlStr, false);   
	    obj.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');   
	    obj.send(paramsStr);   
	    var result = Ext.util.JSON.decode(obj.responseText);   
	    return result;   
	} 
	
	function days_between(date1, date2) {

	    // The number of milliseconds in one day
	    var ONE_DAY = 1000 * 60 * 60 * 24;
	
	    // Convert both dates to milliseconds
	    var date1_ms = date1.getTime();
	    var date2_ms = date2.getTime();
	
	    // Calculate the difference in milliseconds
	    var difference_ms = Math.abs(date1_ms - date2_ms);
	    
	    // Convert back to days and return
	    return Math.round(difference_ms/ONE_DAY);
	}

	function months_between(date1, date2) {

	    // The number of milliseconds in one month
	    var ONE_Month = 1000 * 60 * 60 * 24 * 30;
	
	    // Convert both dates to milliseconds
	    var date1_ms = date1.getTime();
	    var date2_ms = date2.getTime();
	
	    // Calculate the difference in milliseconds
	    var difference_ms = Math.abs(date1_ms - date2_ms)
	    
	    // Convert back to days and return
	    return Math.round(difference_ms/ONE_Month);
	}
/**
 * 图片百分比缩放
 * @param {} maxWidth 最大宽度
 * @param {} maxHeight 最大高度
 * @param {} objImg
 * 使用 如:<img  alt=""  src="<%=basePath%>/attachFiles/${photo.file.filePath}" onload="AutoResizeImage(900,700,this)">
 */
function AutoResizeImage(maxWidth, maxHeight, objImg) {
	var img = new Image();
	img.src = objImg.src;
	var hRatio;
	var wRatio;
	var Ratio = 1;
	var w = img.width;
	var h = img.height;
	wRatio = maxWidth / w;
	hRatio = maxHeight / h;
	if (maxWidth == 0 && maxHeight == 0) {
		Ratio = 1;
	} else if (maxWidth == 0) {//
		if (hRatio < 1)
			Ratio = hRatio;
	} else if (maxHeight == 0) {
		if (wRatio < 1)
			Ratio = wRatio;
	} else if (wRatio < 1 || hRatio < 1) {
		Ratio = (wRatio <= hRatio ? wRatio : hRatio);
	}
	if (Ratio < 1) {
		w = w * Ratio;
		h = h * Ratio;
	}
	objImg.height = h;
	objImg.width = w;
}

Ext.override(Ext.layout.ContainerLayout, {
			setContainer : function(a) {
				this.container = a;
			}
		});
Ext.override(Ext.BoxComponent, {
			setSize : function(b, d) {
				if (typeof b == "object") {
					d = b.height, b = b.width;
				}
				if (Ext.isDefined(b) && Ext.isDefined(this.minWidth)
						&& (b < this.minWidth)) {
					b = this.minWidth;
				}
				if (Ext.isDefined(d) && Ext.isDefined(this.minHeight)
						&& (d < this.minHeight)) {
					d = this.minHeight;
				}
				if (Ext.isDefined(b) && Ext.isDefined(this.maxWidth)
						&& (b > this.maxWidth)) {
					b = this.maxWidth;
				}
				if (Ext.isDefined(d) && Ext.isDefined(this.maxHeight)
						&& (d > this.maxHeight)) {
					d = this.maxHeight;
				}
				if (!this.boxReady) {
					this.width = b, this.height = d;
					return this;
				}
				if (this.cacheSizes !== false && this.lastSize
						&& this.lastSize.width == b
						&& this.lastSize.height == d) {
					return this;
				}
				this.lastSize = {
					width : b,
					height : d
				};
				var c = this.adjustSize(b, d), f = c.width, a = c.height, e;
				if (f !== undefined || a !== undefined) {
					e = this.getResizeEl();
					if (!this.deferHeight && f !== undefined && a !== undefined) {
						e.setSize(f, a);
					} else {
						if (!this.deferHeight && a !== undefined) {
							e.setHeight(a);
						} else {
							if (f !== undefined) {
								e.setWidth(f);
							}
						}
					}
					this.onResize(f, a, b, d);
				}
				return this;
			},
			onResize : function(d, b, a, c) {
				this.fireEvent("resize", this, d, b, a, c);
			}
		});
Ext.override(Ext.Container, {
			onResize : function(d, b, a, c) {
				Ext.Container.superclass.onResize.apply(this, arguments);
				if ((this.rendered && this.layout && this.layout.monitorResize)
						&& !this.suspendLayoutResize) {
					this.layout.onResize();
				}
			},
			canLayout : function() {
				var a = this.getVisibilityEl();
				return a && !a.isStyle("display", "none");
			},
			doLayout : function(f, e) {
				var j = this.rendered, h = e || this.forceLayout, d, b, a, g;
				if (!this.canLayout() || this.collapsed) {
					this.deferLayout = this.deferLayout || !f;
					if (!h) {
						return;
					}
					f = f && !this.deferLayout;
				} else {
					delete this.deferLayout;
				}
				d = (f !== true && this.items) ? this.items.items : [];
				for (b = 0, a = d.length; b < a; b++) {
					if ((g = d[b]).layout) {
						g.suspendLayoutResize = true;
					}
				}
				if (j && this.layout) {
					this.layout.layout();
				}
				for (b = 0; b < a; b++) {
					if ((g = d[b]).doLayout) {
						g.doLayout(false, h);
					}
				}
				if (j) {
					this.onLayout(f, h);
				}
				this.hasLayout = true;
				delete this.forceLayout;
				for (b = 0; b < a; b++) {
					if ((g = d[b]).layout) {
						delete g.suspendLayoutResize;
					}
				}
			}
		});
Ext.override(Ext.Panel, {
			onResize : Ext.Panel.prototype.onResize
					.createSequence(Ext.Container.prototype.onResize)
		});
Ext.override(Ext.Viewport, {
			fireResize : function(a, b) {
				this.onResize(a, b, a, b);
			}
		});