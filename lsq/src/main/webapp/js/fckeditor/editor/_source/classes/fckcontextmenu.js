var FCKContextMenu=function(d,c){this.CtrlDisable=false;var b=this._Panel=new FCKPanel(d);b.AppendStyleSheet(FCKConfig.SkinEditorCSS);b.IsContextMenu=true;if(FCKBrowserInfo.IsGecko){b.Document.addEventListener("draggesture",function(f){f.preventDefault();return false;},true);}var a=this._MenuBlock=new FCKMenuBlock();a.Panel=b;a.OnClick=FCKTools.CreateEventListener(FCKContextMenu_MenuBlock_OnClick,this);this._Redraw=true;};FCKContextMenu.prototype.SetMouseClickWindow=function(a){if(!FCKBrowserInfo.IsIE){this._Document=a.document;if(FCKBrowserInfo.IsOpera&&!("oncontextmenu" in document.createElement("foo"))){this._Document.addEventListener("mousedown",FCKContextMenu_Document_OnMouseDown,false);this._Document.addEventListener("mouseup",FCKContextMenu_Document_OnMouseUp,false);}this._Document.addEventListener("contextmenu",FCKContextMenu_Document_OnContextMenu,false);}};FCKContextMenu.prototype.AddItem=function(c,b,e,a,f){var d=this._MenuBlock.AddItem(c,b,e,a,f);this._Redraw=true;return d;};FCKContextMenu.prototype.AddSeparator=function(){this._MenuBlock.AddSeparator();this._Redraw=true;};FCKContextMenu.prototype.RemoveAllItems=function(){this._MenuBlock.RemoveAllItems();this._Redraw=true;};FCKContextMenu.prototype.AttachToElement=function(a){if(FCKBrowserInfo.IsIE){FCKTools.AddEventListenerEx(a,"contextmenu",FCKContextMenu_AttachedElement_OnContextMenu,this);}else{a._FCKContextMenu=this;}};function FCKContextMenu_Document_OnContextMenu(b){if(FCKConfig.BrowserContextMenu){return true;}var a=b.target;while(a){if(a._FCKContextMenu){if(a._FCKContextMenu.CtrlDisable&&(b.ctrlKey||b.metaKey)){return true;}FCKTools.CancelEvent(b);FCKContextMenu_AttachedElement_OnContextMenu(b,a._FCKContextMenu,a);return false;}a=a.parentNode;}return true;}var FCKContextMenu_OverrideButton;function FCKContextMenu_Document_OnMouseDown(f){if(!f||f.button!=2){return false;}if(FCKConfig.BrowserContextMenu){return true;}var c=f.target;while(c){if(c._FCKContextMenu){if(c._FCKContextMenu.CtrlDisable&&(f.ctrlKey||f.metaKey)){return true;}var b=FCKContextMenu_OverrideButton;if(!b){var d=FCKTools.GetElementDocument(f.target);b=FCKContextMenu_OverrideButton=d.createElement("input");b.type="button";var a=d.createElement("p");d.body.appendChild(a);a.appendChild(b);}b.style.cssText="position:absolute;top:"+(f.clientY-2)+"px;left:"+(f.clientX-2)+"px;width:5px;height:5px;opacity:0.01";}c=c.parentNode;}return false;}function FCKContextMenu_Document_OnMouseUp(c){if(FCKConfig.BrowserContextMenu){return true;}var a=FCKContextMenu_OverrideButton;if(a){var b=a.parentNode;b.parentNode.removeChild(b);FCKContextMenu_OverrideButton=undefined;if(c&&c.button==2){FCKContextMenu_Document_OnContextMenu(c);return false;}}return true;}function FCKContextMenu_AttachedElement_OnContextMenu(e,b,d){if((b.CtrlDisable&&(e.ctrlKey||e.metaKey))||FCKConfig.BrowserContextMenu){return true;}var c=d||this;if(b.OnBeforeOpen){b.OnBeforeOpen.call(b,c);}if(b._MenuBlock.Count()==0){return false;}if(b._Redraw){b._MenuBlock.Create(b._Panel.MainNode);b._Redraw=false;}FCKTools.DisableSelection(b._Panel.Document.body);var a=0;var f=0;if(FCKBrowserInfo.IsIE){a=e.screenX;f=e.screenY;}else{if(FCKBrowserInfo.IsSafari){a=e.clientX;f=e.clientY;}else{a=e.pageX;f=e.pageY;}}b._Panel.Show(a,f,e.currentTarget||null);return false;}function FCKContextMenu_MenuBlock_OnClick(b,a){a._Panel.Hide();FCKTools.RunFunction(a.OnItemClick,a,b);}