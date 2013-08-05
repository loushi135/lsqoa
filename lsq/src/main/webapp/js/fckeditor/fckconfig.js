FCKConfig.CustomConfigurationsPath = "";
FCKConfig.EditorAreaCSS = FCKConfig.BasePath + "css/fck_editorarea.css";
FCKConfig.EditorAreaStyles = "";
FCKConfig.ToolbarComboPreviewCSS = "";
FCKConfig.DocType = "";
FCKConfig.BaseHref = "";
FCKConfig.FullPage = false;
FCKConfig.StartupShowBlocks = false;
FCKConfig.Debug = false;
FCKConfig.AllowQueryStringDebug = true;
FCKConfig.SkinPath = FCKConfig.BasePath + "skins/office2003/";
FCKConfig.SkinEditorCSS = "";
FCKConfig.SkinDialogCSS = "";
FCKConfig.PreloadImages = [FCKConfig.SkinPath + "images/toolbar.start.gif",
		FCKConfig.SkinPath + "images/toolbar.buttonarrow.gif"];
FCKConfig.PluginsPath = FCKConfig.BasePath + "plugins/";
FCKConfig.AutoGrowMax = 400;
FCKConfig.AutoDetectLanguage = true;
FCKConfig.DefaultLanguage = "zh_cn";
FCKConfig.ContentLangDirection = "ltr";
FCKConfig.ProcessHTMLEntities = true;
FCKConfig.IncludeLatinEntities = true;
FCKConfig.IncludeGreekEntities = true;
FCKConfig.ProcessNumericEntities = false;
FCKConfig.AdditionalNumericEntities = "";
FCKConfig.FillEmptyBlocks = true;
FCKConfig.FormatSource = true;
FCKConfig.FormatOutput = true;
FCKConfig.FormatIndentator = "    ";
FCKConfig.EMailProtection = "none";
FCKConfig.EMailProtectionFunction = "mt(NAME,DOMAIN,SUBJECT,BODY)";
FCKConfig.StartupFocus = false;
FCKConfig.ForcePasteAsPlainText = false;
FCKConfig.AutoDetectPasteFromWord = true;
FCKConfig.ShowDropDialog = true;
FCKConfig.ForceSimpleAmpersand = false;
FCKConfig.TabSpaces = 0;
FCKConfig.ShowBorders = true;
FCKConfig.SourcePopup = false;
FCKConfig.ToolbarStartExpanded = true;
FCKConfig.ToolbarCanCollapse = true;
FCKConfig.IgnoreEmptyParagraphValue = true;
FCKConfig.FloatingPanelsZIndex = 10000;
FCKConfig.HtmlEncodeOutput = false;
FCKConfig.TemplateReplaceAll = true;
FCKConfig.TemplateReplaceCheckbox = true;
FCKConfig.ToolbarLocation = "In";
FCKConfig.ToolbarSets["Default"] = [
		["Source", "DocProps", "-", "Save", "NewPage", "Preview", "-",
				"Templates"],
		["Cut", "Copy", "Paste", "PasteText", "PasteWord", "-", "Print",
				"SpellCheck"],
		["Undo", "Redo", "-", "Find", "Replace", "-", "SelectAll",
				"RemoveFormat"],
		["Form", "Checkbox", "Radio", "TextField", "Textarea", "Select",
				"Button", "ImageButton", "HiddenField"],
		"/",
		["Bold", "Italic", "Underline", "StrikeThrough", "-", "Subscript",
				"Superscript"],
		["OrderedList", "UnorderedList", "-", "Outdent", "Indent",
				"Blockquote", "CreateDiv"],
		["JustifyLeft", "JustifyCenter", "JustifyRight", "JustifyFull"],
		["Link", "Unlink", "Anchor"],
		["Image", "Flash", "Table", "Rule", "Smiley", "SpecialChar",
				"PageBreak"], "/",
		["Style", "FontFormat", "FontName", "FontSize"],
		["TextColor", "BGColor"], ["FitWindow", "ShowBlocks", "-", "About"]];
FCKConfig.ToolbarSets["Basic"] = [["Bold", "Italic", "-", "OrderedList",
		"UnorderedList", "-", "Link", "Unlink", "-", "About"]];
FCKConfig.ToolbarSets["JDefault"] = [
		["Source", "DocProps", "-", "Save", "NewPage", "Preview", "-",
				"Templates"],
		["Cut", "Copy", "Paste", "PasteText", "PasteWord", "-", "Print",
				"SpellCheck"],
		["Undo", "Redo", "-", "Find", "Replace", "-", "SelectAll",
				"RemoveFormat"],
		"/",
		["Bold", "Italic", "Underline", "StrikeThrough", "-", "Subscript",
				"Superscript"],
		["OrderedList", "UnorderedList", "-", "Outdent", "Indent",
				"Blockquote", "CreateDiv"],
		["TextColor", "BGColor"],
		"/",
		["JustifyLeft", "JustifyCenter", "JustifyRight", "JustifyFull"],
		["Link", "Unlink", "Anchor"],
		["Image", "Flash", "Table", "Rule", "Smiley", "SpecialChar",
				"PageBreak"], ["FitWindow", "ShowBlocks", "-", "About"], "/",
		["Style", "FontFormat", "FontName", "FontSize"]];
FCKConfig.ToolbarSets["Custom"] = [
     ['FontFormat','FontName','FontSize'],
     ['Bold','Italic','Underline','StrikeThrough','-','Subscript','Superscript'],
     ['OrderedList','UnorderedList','-','Outdent','Indent','Blockquote'],
     ['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'],
     ['Image','Flash','Table','Rule','Smiley','SpecialChar','PageBreak'],
     ['Link','Unlink','Anchor'],
     ['TextColor','BGColor'],'/'
     ['Cut','Copy','Paste','PasteText','PasteWord'],
    ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
    ['FitWindow','ShowBlocks','-','Source']        // No comma for the last row.
] ;
FCKConfig.EnterMode = "p";
FCKConfig.ShiftEnterMode = "br";
FCKConfig.Keystrokes = [[CTRL + 65, true], [CTRL + 67, true],
		[CTRL + 70, true], [CTRL + 83, true], [CTRL + 84, true],
		[CTRL + 88, true], [CTRL + 86, "Paste"], [CTRL + 45, true],
		[SHIFT + 45, "Paste"], [CTRL + 88, "Cut"], [SHIFT + 46, "Cut"],
		[CTRL + 90, "Undo"], [CTRL + 89, "Redo"], [CTRL + SHIFT + 90, "Redo"],
		[CTRL + 76, "Link"], [CTRL + 66, "Bold"], [CTRL + 73, "Italic"],
		[CTRL + 85, "Underline"], [CTRL + SHIFT + 83, "Save"],
		[CTRL + ALT + 13, "FitWindow"], [SHIFT + 32, "Nbsp"]];
FCKConfig.ContextMenu = ["Generic", "Link", "Anchor", "Image", "Flash",
		"Select", "Textarea", "Checkbox", "Radio", "TextField", "HiddenField",
		"ImageButton", "Button", "BulletedList", "NumberedList", "Table",
		"Form", "DivContainer"];
FCKConfig.BrowserContextMenuOnCtrl = false;
FCKConfig.BrowserContextMenu = false;
FCKConfig.EnableMoreFontColors = true;
FCKConfig.FontColors = "000000,993300,333300,003300,003366,000080,333399,333333,800000,FF6600,808000,808080,008080,0000FF,666699,808080,FF0000,FF9900,99CC00,339966,33CCCC,3366FF,800080,999999,FF00FF,FFCC00,FFFF00,00FF00,00FFFF,00CCFF,993366,C0C0C0,FF99CC,FFCC99,FFFF99,CCFFCC,CCFFFF,99CCFF,CC99FF,FFFFFF";
FCKConfig.FontFormats = "p;h1;h2;h3;h4;h5;h6;pre;address;div";
FCKConfig.FontNames = "宋体;楷体;黑体;隶书;行楷;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana";
FCKConfig.FontSizes = "12px;14px;16px;18px;24px;26px;28px;32px;48px;smaller;larger;xx-small;x-small;small;medium;large;x-large;xx-large";
FCKConfig.StylesXmlPath = FCKConfig.EditorPath + "fckstyles.xml";
FCKConfig.TemplatesXmlPath = FCKConfig.EditorPath + "fcktemplates.xml";
FCKConfig.SpellChecker = "WSC";
FCKConfig.IeSpellDownloadUrl = "http://www.iespell.com/download.php";
FCKConfig.SpellerPagesServerScript = "server-scripts/spellchecker.php";
FCKConfig.FirefoxSpellChecker = false;
FCKConfig.MaxUndoLevels = 15;
FCKConfig.DisableObjectResizing = false;
FCKConfig.DisableFFTableHandles = true;
FCKConfig.LinkDlgHideTarget = false;
FCKConfig.LinkDlgHideAdvanced = false;
FCKConfig.ImageDlgHideLink = false;
FCKConfig.ImageDlgHideAdvanced = false;
FCKConfig.FlashDlgHideAdvanced = false;
FCKConfig.ProtectedTags = "";
FCKConfig.BodyId = "";
FCKConfig.BodyClass = "";
FCKConfig.DefaultStyleLabel = "";
FCKConfig.DefaultFontFormatLabel = "";
FCKConfig.DefaultFontLabel = "";
FCKConfig.DefaultFontSizeLabel = "";
FCKConfig.DefaultLinkTarget = "";
FCKConfig.CleanWordKeepsStructure = false;
FCKConfig.RemoveFormatTags = "b,big,code,del,dfn,em,font,i,ins,kbd,q,samp,small,span,strike,strong,sub,sup,tt,u,var";
FCKConfig.RemoveAttributes = "class,style,lang,width,height,align,hspace,valign";
FCKConfig.CustomStyles = {
	"Red Title" : {
		Element : "h3",
		Styles : {
			"color" : "Red"
		}
	}
};
FCKConfig.CoreStyles = {
	"Bold" : {
		Element : "strong",
		Overrides : "b"
	},
	"Italic" : {
		Element : "em",
		Overrides : "i"
	},
	"Underline" : {
		Element : "u"
	},
	"StrikeThrough" : {
		Element : "strike"
	},
	"Subscript" : {
		Element : "sub"
	},
	"Superscript" : {
		Element : "sup"
	},
	"p" : {
		Element : "p"
	},
	"div" : {
		Element : "div"
	},
	"pre" : {
		Element : "pre"
	},
	"address" : {
		Element : "address"
	},
	"h1" : {
		Element : "h1"
	},
	"h2" : {
		Element : "h2"
	},
	"h3" : {
		Element : "h3"
	},
	"h4" : {
		Element : "h4"
	},
	"h5" : {
		Element : "h5"
	},
	"h6" : {
		Element : "h6"
	},
	"FontFace" : {
		Element : "span",
		Styles : {
			"font-family" : '#("Font")'
		},
		Overrides : [{
					Element : "font",
					Attributes : {
						"face" : null
					}
				}]
	},
	"Size" : {
		Element : "span",
		Styles : {
			"font-size" : '#("Size","fontSize")'
		},
		Overrides : [{
					Element : "font",
					Attributes : {
						"size" : null
					}
				}]
	},
	"Color" : {
		Element : "span",
		Styles : {
			"color" : '#("Color","color")'
		},
		Overrides : [{
					Element : "font",
					Attributes : {
						"color" : null
					}
				}]
	},
	"BackColor" : {
		Element : "span",
		Styles : {
			"background-color" : '#("Color","color")'
		}
	},
	"SelectionHighlight" : {
		Element : "span",
		Styles : {
			"background-color" : "navy",
			"color" : "white"
		}
	}
};
FCKConfig.IndentLength = 40;
FCKConfig.IndentUnit = "px";
FCKConfig.IndentClasses = [];
FCKConfig.JustifyClasses = [];
var _FileBrowserLanguage = "jsp";
var _QuickUploadLanguage = "jsp";
var _FileBrowserExtension = _FileBrowserLanguage == "perl"
		? "cgi"
		: _FileBrowserLanguage;
var _QuickUploadExtension = _QuickUploadLanguage == "perl"
		? "cgi"
		: _QuickUploadLanguage;
FCKConfig.LinkBrowser = true;
FCKConfig.LinkBrowserURL = FCKConfig.BasePath
		+ "filemanager/browser/default/browser.html?Connector=connectors/jsp/connector";
FCKConfig.LinkBrowserWindowWidth = FCKConfig.ScreenWidth * 0.7;
FCKConfig.LinkBrowserWindowHeight = FCKConfig.ScreenHeight * 0.7;
FCKConfig.ImageBrowser = true;
FCKConfig.ImageBrowserURL = FCKConfig.BasePath
		+ "filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector";
FCKConfig.ImageBrowserWindowWidth = FCKConfig.ScreenWidth * 0.7;
FCKConfig.ImageBrowserWindowHeight = FCKConfig.ScreenHeight * 0.7;
FCKConfig.FlashBrowser = true;
FCKConfig.FlashBrowserURL = FCKConfig.BasePath
		+ "filemanager/browser/default/browser.html?Type=Flash&Connector=connectors/jsp/connector";
FCKConfig.FlashBrowserWindowWidth = FCKConfig.ScreenWidth * 0.7;
FCKConfig.FlashBrowserWindowHeight = FCKConfig.ScreenHeight * 0.7;
FCKConfig.LinkUpload = true;
FCKConfig.LinkUploadURL = FCKConfig.BasePath
		+ "filemanager/browser/default/connectors/jsp/connector?Command=FileUpload&Type=File&CurrentFolder=%2F";
FCKConfig.LinkUploadAllowedExtensions = ".(7z|aiff|asf|avi|bmp|csv|doc|fla|flv|gif|gz|gzip|jpeg|jpg|mid|mov|mp3|mp4|mpc|mpeg|mpg|ods|odt|pdf|png|ppt|pxd|qt|ram|rar|rm|rmi|rmvb|rtf|sdc|sitd|swf|sxc|sxw|tar|tgz|tif|tiff|txt|vsd|wav|wma|wmv|xls|xml|zip)$";
FCKConfig.LinkUploadDeniedExtensions = "";
FCKConfig.ImageUpload = true;
FCKConfig.FlashUploadURL = FCKConfig.BasePath
		+ "filemanager/browser/default/connectors/jsp/connector?Command=FileUpload&Type=File&CurrentFolder=%2F";
FCKConfig.ImageUploadAllowedExtensions = ".(jpg|gif|jpeg|png|bmp|JPG|GIF|JPEG|PNG|BPM)$";
FCKConfig.ImageUploadDeniedExtensions = "";
FCKConfig.FlashUpload = true;
FCKConfig.ImageUploadURL = FCKConfig.BasePath
		+ "filemanager/browser/default/connectors/jsp/connector?Command=FileUpload&Type=File&CurrentFolder=%2F";
FCKConfig.FlashUploadAllowedExtensions = ".(swf|flv)$";
FCKConfig.FlashUploadDeniedExtensions = "";
FCKConfig.SmileyPath = FCKConfig.BasePath + "images/smiley/msn/";
FCKConfig.SmileyImages = ["regular_smile.gif", "sad_smile.gif",
		"wink_smile.gif", "teeth_smile.gif", "confused_smile.gif",
		"tounge_smile.gif", "embaressed_smile.gif", "omg_smile.gif",
		"whatchutalkingabout_smile.gif", "angry_smile.gif", "angel_smile.gif",
		"shades_smile.gif", "devil_smile.gif", "cry_smile.gif",
		"lightbulb.gif", "thumbs_down.gif", "thumbs_up.gif", "heart.gif",
		"broken_heart.gif", "kiss.gif", "envelope.gif"];
FCKConfig.SmileyColumns = 8;
FCKConfig.SmileyWindowWidth = 320;
FCKConfig.SmileyWindowHeight = 210;
FCKConfig.BackgroundBlockerColor = "#ffffff";
FCKConfig.BackgroundBlockerOpacity = 0.5;
FCKConfig.MsWebBrowserControlCompat = false;
FCKConfig.PreventSubmitHandler = false;