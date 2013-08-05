var MONTH_NAMES = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
var DAY_NAMES = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
function LZ(a) {
    return (a < 0 || a > 9 ? "": "0") + a;
}
function isDate(c, b) {
    var a = getDateFromFormat(c, b);
    if (a == 0) {
        return false;
    }
    return true;
}
function compareDates(e, f, c, d) {
    var b = getDateFromFormat(e, f);
    var a = getDateFromFormat(c, d);
    if (b == 0 || a == 0) {
        return - 1;
    } else {
        if (b > a) {
            return 1;
        }
    }
    return 0;
}
function formatDate(I, D) {
    D = D + "";
    var l = "";
    var v = 0;
    var G = "";
    var f = "";
    var j = I.getYear() + "";
    var g = I.getMonth() + 1;
    var F = I.getDate();
    var o = I.getDay();
    var n = I.getHours();
    var x = I.getMinutes();
    var q = I.getSeconds();
    var t, u, b, r, J, e, C, B, z, p, N, n, L, i, a, A;
    var w = new Object();
    if (j.length < 4) {
        j = "" + (j - 0 + 1900);
    }
    w["y"] = "" + j;
    w["yyyy"] = j;
    w["yy"] = j.substring(2, 4);
    w["M"] = g;
    w["MM"] = LZ(g);
    w["MMM"] = MONTH_NAMES[g - 1];
    w["NNN"] = MONTH_NAMES[g + 11];
    w["d"] = F;
    w["dd"] = LZ(F);
    w["E"] = DAY_NAMES[o + 7];
    w["EE"] = DAY_NAMES[o];
    w["H"] = n;
    w["HH"] = LZ(n);
    if (n == 0) {
        w["h"] = 12;
    } else {
        if (n > 12) {
            w["h"] = n - 12;
        } else {
            w["h"] = n;
        }
    }
    w["hh"] = LZ(w["h"]);
    if (n > 11) {
        w["K"] = n - 12;
    } else {
        w["K"] = n;
    }
    w["k"] = n + 1;
    w["KK"] = LZ(w["K"]);
    w["kk"] = LZ(w["k"]);
    if (n > 11) {
        w["a"] = "PM";
    } else {
        w["a"] = "AM";
    }
    w["m"] = x;
    w["mm"] = LZ(x);
    w["s"] = q;
    w["ss"] = LZ(q);
    while (v < D.length) {
        G = D.charAt(v);
        f = "";
        while ((D.charAt(v) == G) && (v < D.length)) {
            f += D.charAt(v++);
        }
        if (w[f] != null) {
            l = l + w[f];
        } else {
            l = l + f;
        }
    }
    return l;
}
function _isInteger(c) {
    var b = "1234567890";
    for (var a = 0; a < c.length; a++) {
        if (b.indexOf(c.charAt(a)) == -1) {
            return false;
        }
    }
    return true;
}
function _getInt(f, d, e, c) {
    for (var a = c; a >= e; a--) {
        var b = f.substring(d, d + a);
        if (b.length < e) {
            return null;
        }
        if (_isInteger(b)) {
            return b;
        }
    }
    return null;
}
function getDateFromFormat(w, p) {
    w = w + "";
    p = p + "";
    var v = 0;
    var l = 0;
    var r = "";
    var f = "";
    var u = "";
    var h, g;
    var b = new Date();
    var j = b.getYear();
    var t = b.getMonth() + 1;
    var s = 1;
    var d = b.getHours();
    var q = b.getMinutes();
    var n = b.getSeconds();
    var k = "";
    while (l < p.length) {
        r = p.charAt(l);
        f = "";
        while ((p.charAt(l) == r) && (l < p.length)) {
            f += p.charAt(l++);
        }
        if (f == "yyyy" || f == "yy" || f == "y") {
            if (f == "yyyy") {
                h = 4;
                g = 4;
            }
            if (f == "yy") {
                h = 2;
                g = 2;
            }
            if (f == "y") {
                h = 2;
                g = 4;
            }
            j = _getInt(w, v, h, g);
            if (j == null) {
                return 0;
            }
            v += j.length;
            if (j.length == 2) {
                if (j > 70) {
                    j = 1900 + (j - 0);
                } else {
                    j = 2000 + (j - 0);
                }
            }
        } else {
            if (f == "MMM" || f == "NNN") {
                t = 0;
                for (var o = 0; o < MONTH_NAMES.length; o++) {
                    var e = MONTH_NAMES[o];
                    if (w.substring(v, v + e.length).toLowerCase() == e.toLowerCase()) {
                        if (f == "MMM" || (f == "NNN" && o > 11)) {
                            t = o + 1;
                            if (t > 12) {
                                t -= 12;
                            }
                            v += e.length;
                            break;
                        }
                    }
                }
                if ((t < 1) || (t > 12)) {
                    return 0;
                }
            } else {
                if (f == "EE" || f == "E") {
                    for (var o = 0; o < DAY_NAMES.length; o++) {
                        var m = DAY_NAMES[o];
                        if (w.substring(v, v + m.length).toLowerCase() == m.toLowerCase()) {
                            v += m.length;
                            break;
                        }
                    }
                } else {
                    if (f == "MM" || f == "M") {
                        t = _getInt(w, v, f.length, 2);
                        if (t == null || (t < 1) || (t > 12)) {
                            return 0;
                        }
                        v += t.length;
                    } else {
                        if (f == "dd" || f == "d") {
                            s = _getInt(w, v, f.length, 2);
                            if (s == null || (s < 1) || (s > 31)) {
                                return 0;
                            }
                            v += s.length;
                        } else {
                            if (f == "hh" || f == "h") {
                                d = _getInt(w, v, f.length, 2);
                                if (d == null || (d < 1) || (d > 12)) {
                                    return 0;
                                }
                                v += d.length;
                            } else {
                                if (f == "HH" || f == "H") {
                                    d = _getInt(w, v, f.length, 2);
                                    if (d == null || (d < 0) || (d > 23)) {
                                        return 0;
                                    }
                                    v += d.length;
                                } else {
                                    if (f == "KK" || f == "K") {
                                        d = _getInt(w, v, f.length, 2);
                                        if (d == null || (d < 0) || (d > 11)) {
                                            return 0;
                                        }
                                        v += d.length;
                                    } else {
                                        if (f == "kk" || f == "k") {
                                            d = _getInt(w, v, f.length, 2);
                                            if (d == null || (d < 1) || (d > 24)) {
                                                return 0;
                                            }
                                            v += d.length;
                                            d--;
                                        } else {
                                            if (f == "mm" || f == "m") {
                                                q = _getInt(w, v, f.length, 2);
                                                if (q == null || (q < 0) || (q > 59)) {
                                                    return 0;
                                                }
                                                v += q.length;
                                            } else {
                                                if (f == "ss" || f == "s") {
                                                    n = _getInt(w, v, f.length, 2);
                                                    if (n == null || (n < 0) || (n > 59)) {
                                                        return 0;
                                                    }
                                                    v += n.length;
                                                } else {
                                                    if (f == "a") {
                                                        if (w.substring(v, v + 2).toLowerCase() == "am") {
                                                            k = "AM";
                                                        } else {
                                                            if (w.substring(v, v + 2).toLowerCase() == "pm") {
                                                                k = "PM";
                                                            } else {
                                                                return 0;
                                                            }
                                                        }
                                                        v += 2;
                                                    } else {
                                                        if (w.substring(v, v + f.length) != f) {
                                                            return 0;
                                                        } else {
                                                            v += f.length;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    if (v != w.length) {
        return 0;
    }
    if (t == 2) {
        if (((j % 4 == 0) && (j % 100 != 0)) || (j % 400 == 0)) {
            if (s > 29) {
                return 0;
            }
        } else {
            if (s > 28) {
                return 0;
            }
        }
    }
    if ((t == 4) || (t == 6) || (t == 9) || (t == 11)) {
        if (s > 30) {
            return 0;
        }
    }
    if (d < 12 && k == "PM") {
        d = d - 0 + 12;
    } else {
        if (d > 11 && k == "AM") {
            d -= 12;
        }
    }
    var a = new Date(j, t - 1, s, d, q, n);
    return a.getTime();
}
function parseDate(h) {
    var f = (arguments.length == 2) ? arguments[1] : false;
    generalFormats = new Array("y-M-d", "MMM d, y", "MMM d,y", "y-MMM-d", "d-MMM-y", "MMM d");
    monthFirst = new Array("M/d/y", "M-d-y", "M.d.y", "MMM-d", "M/d", "M-d");
    dateFirst = new Array("d/M/y", "d-M-y", "d.M.y", "d-MMM", "d/M", "d-M");
    var b = new Array("generalFormats", f ? "dateFirst": "monthFirst", f ? "monthFirst": "dateFirst");
    var g = null;
    for (var e = 0; e < b.length; e++) {
        var a = window[b[e]];
        for (var c = 0; c < a.length; c++) {
            g = getDateFromFormat(h, a[c]);
            if (g != 0) {
                return new Date(g);
            }
        }
    }
    return null;
}



/**  
*功能:格式化时间  
*示例:DateUtil.Format("yyyy/MM/dd","Thu Nov 9 20:30:37 UTC+0800 2006 ");  
*返回:2006/11/09  
*/  
function FormatGMTtoLocal(date,fmtCode){  
	var result,d,arr_d;  
	
	var patrn_now_1=/^y{4}-M{2}-d{2}\sh{2}:m{2}:s{2}$/;  
	var patrn_now_11=/^y{4}-M{1,2}-d{1,2}\sh{1,2}:m{1,2}:s{1,2}$/;  
	
	var patrn_now_2=/^y{4}\/M{2}\/d{2}\sh{2}:m{2}:s{2}$/;  
	var patrn_now_22=/^y{4}\/M{1,2}\/d{1,2}\sh{1,2}:m{1,2}:s{1,2}$/;  
	
	var patrn_now_3=/^y{4}年M{2}月d{2}日\sh{2}时m{2}分s{2}秒$/;  
	var patrn_now_33=/^y{4}年M{1,2}月d{1,2}日\sh{1,2}时m{1,2}分s{1,2}秒$/;  
	
	var patrn_date_1=/^y{4}-M{2}-d{2}$/;  
	var patrn_date_11=/^y{4}-M{1,2}-d{1,2}$/;  
	
	var patrn_date_2=/^y{4}\/M{2}\/d{2}$/;  
	var patrn_date_22=/^y{4}\/M{1,2}\/d{1,2}$/;  
	
	var patrn_date_3=/^y{4}年M{2}月d{2}日$/;  
	var patrn_date_33=/^y{4}年M{1,2}月d{1,2}日$/;  
	
	var patrn_time_1=/^h{2}:m{2}:s{2}$/;  
	var patrn_time_11=/^h{1,2}:m{1,2}:s{1,2}$/;  
	var patrn_time_2=/^h{2}时m{2}分s{2}秒$/;  
	var patrn_time_22=/^h{1,2}时m{1,2}分s{1,2}秒$/;  
	
	if(!fmtCode){fmtCode="yyyy/MM/dd hh:mm:ss";}  
	if(date){  
	d=new Date(date);  
	if(isNaN(d)){  
	msgBox("时间参数非法\n正确的时间示例:\nThu Nov 9 20:30:37 UTC+0800 2006\n或\n2006/      10/17");  
	return;}  
	}else{  
	d=new Date();  
	}  
	
	if(patrn_now_1.test(fmtCode))  
	{  
	arr_d=splitDate(d,true);  
	result=arr_d.yyyy+"-"+arr_d.MM+"-"+arr_d.dd+" "+arr_d.hh+":"+arr_d.mm+":"+arr_d.ss;  
	}  
	else if(patrn_now_11.test(fmtCode))  
	{  
	arr_d=splitDate(d);  
	result=arr_d.yyyy+"-"+arr_d.MM+"-"+arr_d.dd+" "+arr_d.hh+":"+arr_d.mm+":"+arr_d.ss;  
	}  
	else if(patrn_now_2.test(fmtCode))  
	{  
	arr_d=splitDate(d,true);  
	result=arr_d.yyyy+"/"+arr_d.MM+"/"+arr_d.dd+" "+arr_d.hh+":"+arr_d.mm+":"+arr_d.ss;  
	}  
	else if(patrn_now_22.test(fmtCode))  
	{  
	arr_d=splitDate(d);  
	result=arr_d.yyyy+"/"+arr_d.MM+"/"+arr_d.dd+" "+arr_d.hh+":"+arr_d.mm+":"+arr_d.ss;  
	}  
	else if(patrn_now_3.test(fmtCode))  
	{  
	arr_d=splitDate(d,true);  
	result=arr_d.yyyy+"年"+arr_d.MM+"月"+arr_d.dd+"日"+" "+arr_d.hh+"时"+arr_d.mm+"分"+arr_d.ss+"秒";  
	}  
	else if(patrn_now_33.test(fmtCode))  
	{  
	arr_d=splitDate(d);  
	result=arr_d.yyyy+"年"+arr_d.MM+"月"+arr_d.dd+"日"+" "+arr_d.hh+"时"+arr_d.mm+"分"+arr_d.ss+"秒";  
	}  
	
	else if(patrn_date_1.test(fmtCode))  
	{  
	arr_d=splitDate(d,true); 
	result=arr_d.yyyy+"-"+arr_d.MM+"-"+arr_d.dd;  
	}  
	else if(patrn_date_11.test(fmtCode))  
	{  
	arr_d=splitDate(d);  
	result=arr_d.yyyy+"-"+arr_d.MM+"-"+arr_d.dd;  
	}  
	else if(patrn_date_2.test(fmtCode))  
	{  
	arr_d=splitDate(d,true);  
	result=arr_d.yyyy+"/"+arr_d.MM+"/"+arr_d.dd;  
	}  
	else if(patrn_date_22.test(fmtCode))  
	{  
	arr_d=splitDate(d);  
	result=arr_d.yyyy+"/"+arr_d.MM+"/"+arr_d.dd;  
	}  
	else if(patrn_date_3.test(fmtCode))  
	{  
	arr_d=splitDate(d,true);  
	result=arr_d.yyyy+"年"+arr_d.MM+"月"+arr_d.dd+"日";  
	}  
	else if(patrn_date_33.test(fmtCode))  
	{  
	arr_d=splitDate(d);  
	result=arr_d.yyyy+"年"+arr_d.MM+"月"+arr_d.dd+"日";  
	}  
	else if(patrn_time_1.test(fmtCode)){  
	arr_d=splitDate(d,true);  
	result=arr_d.hh+":"+arr_d.mm+":"+arr_d.ss;  
	}  
	else if(patrn_time_11.test(fmtCode)){  
	arr_d=splitDate(d);  
	result=arr_d.hh+":"+arr_d.mm+":"+arr_d.ss;  
	}  
	else if(patrn_time_2.test(fmtCode)){  
	arr_d=splitDate(d,true);  
	result=arr_d.hh+"时"+arr_d.mm+"分"+arr_d.ss+"秒";  
	}  
	else if(patrn_time_22.test(fmtCode)){  
	arr_d=splitDate(d);  
	result=arr_d.hh+"时"+arr_d.mm+"分"+arr_d.ss+"秒";  
	}  
	else{  
	msgBox("没有匹配的时间格式!");  
	return;  
	}  
	
	return result;  
};  
function splitDate(d,isZero){  
	var yyyy,MM,dd,hh,mm,ss;  
	if(isZero){  
	yyyy=d.getFullYear(); 
	MM=(d.getMonth()+1)<10?"0"+(d.getMonth()+1):d.getMonth()+1;  
	dd=d.getDate()<10?"0"+d.getDate():d.getDate();  
	hh=d.getHours()<10?"0"+d.getHours():d.getHours();  
	mm=d.getMinutes()<10?"0"+d.getMinutes():d.getMinutes();  
	ss=d.getSeconds()<10?"0"+d.getSeconds():d.getSeconds();  
	}else{  
	yyyy=d.getYear();  
	MM=d.getMonth()+1;  
	dd=d.getDate();  
	hh=d.getHours();  
	mm=d.getMinutes();  
	ss=d.getSeconds();    
	}  
	return {"yyyy":yyyy,"MM":MM,"dd":dd,"hh":hh,"mm":mm,"ss":ss};    
}  