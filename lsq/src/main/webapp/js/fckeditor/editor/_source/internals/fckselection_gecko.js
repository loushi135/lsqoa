FCKSelection.GetType=function(){var b="Text";var c;try{c=this.GetSelection();}catch(d){}if(c&&c.rangeCount==1){var a=c.getRangeAt(0);if(a.startContainer==a.endContainer&&(a.endOffset-a.startOffset)==1&&a.startContainer.nodeType==1&&FCKListsLib.StyleObjectElements[a.startContainer.childNodes[a.startOffset].nodeName.toLowerCase()]){b="Control";}}return b;};FCKSelection.GetSelectedElement=function(){var b=!!FCK.EditorWindow&&this.GetSelection();if(!b||b.rangeCount<1){return null;}var a=b.getRangeAt(0);if(a.startContainer!=a.endContainer||a.startContainer.nodeType!=1||a.startOffset!=a.endOffset-1){return null;}var c=a.startContainer.childNodes[a.startOffset];if(c.nodeType!=1){return null;}return c;};FCKSelection.GetParentElement=function(){if(this.GetType()=="Control"){return FCKSelection.GetSelectedElement().parentNode;}else{var c=this.GetSelection();if(c){if(c.anchorNode&&c.anchorNode==c.focusNode){var h=c.getRangeAt(0);if(h.collapsed||h.startContainer.nodeType==3){return c.anchorNode.parentNode;}else{return c.anchorNode;}}var g=new FCKElementPath(c.anchorNode);var b=new FCKElementPath(c.focusNode);var d=null;var a=null;if(g.Elements.length>b.Elements.length){d=g.Elements;a=b.Elements;}else{d=b.Elements;a=g.Elements;}var f=d.length-a.length;for(var e=0;e<a.length;e++){if(d[f+e]==a[e]){return a[e];}}return null;}}return null;};FCKSelection.GetBoundaryParentElement=function(d){if(!FCK.EditorWindow){return null;}if(this.GetType()=="Control"){return FCKSelection.GetSelectedElement().parentNode;}else{var a=this.GetSelection();if(a&&a.rangeCount>0){var b=a.getRangeAt(d?0:(a.rangeCount-1));var c=d?b.startContainer:b.endContainer;return(c.nodeType==1?c:c.parentNode);}}return null;};FCKSelection.SelectNode=function(b){var c=FCK.EditorDocument.createRange();c.selectNode(b);var a=this.GetSelection();a.removeAllRanges();a.addRange(c);};FCKSelection.Collapse=function(b){var a=this.GetSelection();if(b==null||b===true){a.collapseToStart();}else{a.collapseToEnd();}};FCKSelection.HasAncestorNode=function(c){var a=this.GetSelectedElement();if(!a&&FCK.EditorWindow){try{a=this.GetSelection().getRangeAt(0).startContainer;}catch(b){}}while(a){if(a.nodeType==1&&a.nodeName.IEquals(c)){return true;}a=a.parentNode;}return false;};FCKSelection.MoveToAncestorNode=function(c){var b;var a=this.GetSelectedElement();if(!a){a=this.GetSelection().getRangeAt(0).startContainer;}while(a){if(a.nodeName.IEquals(c)){return a;}a=a.parentNode;}return null;};FCKSelection.Delete=function(){var a=this.GetSelection();for(var b=0;b<a.rangeCount;b++){a.getRangeAt(b).deleteContents();}return a;};FCKSelection.GetSelection=function(){return FCK.EditorWindow.getSelection();};FCKSelection.Save=function(){};FCKSelection.Restore=function(){};FCKSelection.Release=function(){};