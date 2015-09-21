function setProperty(e, t, n, r) {
e.style.setProperty ? e.style.setProperty(t, n, r) : e.style.cssText && (e.style.cssText += t + ":" + n + "!" + r + ";");
}

function like_report(e) {
var tmpAttr = document.getElementById("like").getAttribute("like"), tmpHtml = document.getElementById("likeNum").innerHTML, isLike = parseInt(tmpAttr) ? parseInt(tmpAttr) : 0, like = isLike ? 0 : 1, likeNum = parseInt(tmpHtml) ? parseInt(tmpHtml) : 0;
ajax({
url: "/mp/appmsg_like?__biz=" + biz + "&mid=" + mid + "&idx=" + idx + "&uin=" + uin + "&key=" + key + "&like=" + like + "&f=json&appmsgid=" + appmsgid + "&itemidx=" + itemidx,
type: "GET",
timeout: 2e3,
success: function(res) {
var data = eval("(" + res + ")");
data.base_resp.ret == 0 && (isLike ? (removeClass(document.getElementById("like"), "praised"), document.getElementById("like").setAttribute("like", 0), likeNum > 0 && tmpHtml !== "100000+" && (document.getElementById("likeNum").innerHTML = likeNum - 1 == 0 ? "赞" : likeNum - 1)) : (document.getElementById("like").setAttribute("like", 1), addClass(document.getElementById("like"), "praised"), tmpHtml !== "100000+" && (document.getElementById("likeNum").innerHTML = likeNum + 1)));
},
async: !0
});
}

function hasClass(e, t) {
return e.className.match(new RegExp("(\\s|^)" + t + "(\\s|$)"));
}

function addClass(e, t) {
this.hasClass(e, t) || (e.className += " " + t);
}

function removeClass(e, t) {
if (hasClass(e, t)) {
var n = new RegExp("(\\s|^)" + t + "(\\s|$)");
e.className = e.className.replace(n, " ");
}
}

function toggleClass(e, t) {
hasClass(e, t) ? removeClass(e, t) : addClass(e, t);
}

function hash(e) {
var t = 5381;
for (var n = 0; n < e.length; n++) t = (t << 5) + t + e.charCodeAt(n), t &= 2147483647;
return t;
}

function trim(e) {
return e.replace(/^\s*|\s*$/g, "");
}

function ajax(e) {
var t = (e.type || "GET").toUpperCase(), n = e.url, r = typeof e.async == "undefined" ? !0 : e.async, i = typeof e.data == "string" ? e.data : null, s = new XMLHttpRequest, o = null;
s.open(t, n, r), s.onreadystatechange = function() {
s.readyState == 3 && e.received && e.received(s), s.readyState == 4 && (s.status >= 200 && s.status < 400 && (clearTimeout(o), e.success && e.success(s.responseText)), e.complete && e.complete(), e.complete = null);
}, t == "POST" && s.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"), s.setRequestHeader("X-Requested-With", "XMLHttpRequest"), s.send(i), typeof e.timeout != "undefined" && (o = setTimeout(function() {
s.abort("timeout"), e.complete && e.complete(), e.complete = null;
}, e.timeout));
}

function report_article() {
var e = sourceurl == "" ? location.href : sourceurl, t = [ nickname, location.href, title, e ].join("|WXM|");
location.href = "/mp/readtemplate?t=wxm-appmsg-inform&__biz=" + biz + "&info=" + encodeURIComponent(t) + "#wechat_redirect";
}

function viewSource() {
var redirectUrl = sourceurl.indexOf("://") < 0 ? "http://" + sourceurl : sourceurl;
redirectUrl = "http://" + location.host + "/mp/redirect?url=" + encodeURIComponent(sourceurl);
var opt = {
url: "/mp/advertisement_report" + location.search + "&report_type=3&action_type=0&url=" + encodeURIComponent(sourceurl) + "&uin=" + uin + "&key=" + key + "&__biz=" + biz + "&r=" + Math.random(),
type: "GET",
async: !1
};
return tid ? opt.success = function(res) {
try {
res = eval("(" + res + ")");
} catch (e) {
res = {};
}
res && res.ret == 0 ? location.href = redirectUrl : viewSource();
} : (opt.timeout = 2e3, opt.complete = function() {
location.href = redirectUrl;
}), ajax(opt), !1;
}

function parseParams(e) {
if (!e) return {};
var t = e.split("&"), n = {}, r = "";
for (var i = 0, s = t.length; i < s; i++) r = t[i].split("="), n[r[0]] = r[1];
return n;
}

function htmlDecode(e) {
return e.replace(/&#39;/g, "'").replace(/<br\s*(\/)?\s*>/g, "\n").replace(/&nbsp;/g, " ").replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&quot;/g, '"').replace(/&amp;/g, "&");
}

function report(e, t, n) {
var r = e.split("?").pop();
r = r.split("#").shift();
if (r == "") return;
var i = [ r, "action_type=" + n, "uin=" + t ].join("&");
ajax({
url: "/mp/appmsg/show",
type: "POST",
timeout: 2e3,
data: i
});
}

function reportTimeOnPage() {
var e = location.href, t = e.split("?").pop();
t = t.split("#").shift();
if (t == "") return;
var n = [ t, "start_time=" + _wxao.begin, "end_time=" + (new Date).getTime(), "uin=" + fakeid, "title=" + encodeURIComponent(title), "action=pagetime" ].join("&");
ajax({
url: "/mp/appmsg/show?" + n,
async: !1,
timeout: 2e3
});
}

function share_scene(e, t) {
var n = "";
tid != "" && (n = "tid=" + tid + "&aid=" + 54);
var r = e.split("?")[1] || "";
r = r.split("#")[0];
if (r == "") return;
var i = [ r, "scene=" + t ];
return n != "" && i.push(n), r = i.join("&"), e.split("?")[0] + "?" + r + "#" + (e.split("#")[1] || "");
}

function get_url(e, t) {
t = t || "";
var n = e.split("?")[1] || "";
n = n.split("#")[0];
if (n == "") return;
var r = [ n ];
return t != "" && r.push(t), n = r.join("&"), e.split("?")[0] + "?" + n + "#" + (e.split("#")[1] || "");
}

function viewProfile() {
typeof WeixinJSBridge != "undefined" && WeixinJSBridge.invoke && WeixinJSBridge.invoke("profile", {
username: user_name,
scene: "57"
});
}

function gdt_click(e, t, n, r, i, s, o, u, a) {
if (has_click[i]) return;
has_click[i] = !0;
var f = document.getElementById("loading_" + i);
f && (f.style.display = "inline");
var l = +(new Date);
ajax({
url: "/mp/advertisement_report?report_type=2&type=" + e + "&url=" + encodeURIComponent(t) + "&tid=" + i + "&rl=" + encodeURIComponent(n) + "&uin=" + uin + "&key=" + key + "&__biz=" + biz + "&pt=" + a + "&r=" + Math.random() + abtest_param,
type: "GET",
timeout: 1e3,
complete: function(n) {
has_click[i] = !1, f && (f.style.display = "none");
if (e == "5") location.href = "/mp/profile?source=from_ad&tousername=" + t + "&ticket=" + s + "&uin=" + uin + "&key=" + key + "&__biz=" + biz + "&mid=" + mid + "&idx=" + idx + "&tid=" + i; else {
var r = "source=4&tid=" + i + "&idx=" + idx + "&mid=" + mid + "&appuin=" + biz + "&pt=" + a + "&aid=" + u;
t.indexOf("mp.weixin.qq.com") == -1 && (r = ""), location.href = get_url(t, r);
}
},
async: !0
});
}

function addEvent(e, t, n) {
window.addEventListener ? e.addEventListener(t, n, !1) : window.attachEvent ? e.attachEvent("on" + t, function(e) {
return function(t) {
n.call(e, t);
};
}(e)) : e["on" + t] = n;
}

function log(e) {
var t = document.getElementById("log");
if (t) {
var n = t.innerHTML;
t.innerHTML = n + "<div>" + e + "</div>";
}
}

function initpicReport() {
function e(e) {
var t = [];
for (var n in e) t.push(n + "=" + encodeURIComponent(e[n] || ""));
return t.join("&");
}
if (!networkType) return;
var t = window.performance || window.msPerformance || window.webkitPerformance, n = null;
if (!t || typeof t.getEntries == "undefined") return;
var r, i = 100, s = document.getElementsByTagName("img"), o = s.length, u = navigator.userAgent, a, f = !1;
/micromessenger\/(\d+\.\d+)/i.test(u), a = RegExp.$1;
for (var l = 0, c = s.length; l < c; l++) {
r = parseInt(Math.random() * 100);
if (r > i) continue;
var h = s[l].getAttribute("src");
if (h.indexOf("mp.weixin.qq.com") >= 0) continue;
var p = t.getEntries(), d;
for (var v = 0; v < p.length; v++) {
d = p[v];
if (d.name == h) {
ajax({
type: "POST",
url: "/mp/appmsgpicreport?__biz=" + biz + "&uin=" + uin + "&key=" + key + "#wechat_redirect",
data: e({
rnd: Math.random(),
uin: uin,
version: version,
client_version: a,
device: navigator.userAgent,
time_stamp: parseInt(+(new Date) / 1e3),
url: h,
img_size: s[l].fileSize || 0,
user_agent: navigator.userAgent,
net_type: networkType,
appmsg_id: window.appmsgid || "",
sample: o > 100 ? 100 : o,
delay_time: parseInt(d.duration)
})
}), f = !0;
break;
}
}
if (f) break;
}
}

var ISWP = !!navigator.userAgent.match(/Windows\sPhone/i), sw = 0, imgs_info = {}, abtest_param = "&ab_test_id=color&ab_test_value=gray", outsize_link = 0;

(function() {
parseInt(readNum) > 1e5 ? readNum = "100000+" : "", parseInt(likeNum) > 1e5 ? likeNum = "100000+" : "", document.getElementById("readNum") && (document.getElementById("readNum").innerHTML = readNum), document.getElementById("likeNum") && (document.getElementById("likeNum").innerHTML = likeNum);
})(), function() {
var e = document.getElementById("js_content");
if (!e) return !1;
var t = e.getElementsByTagName("a") || [];
for (var n = 0, r = t.length; n < r; ++n) (function(e) {
var n = t[e], r = n.getAttribute("href");
if (!r) return !1;
r.indexOf("http://mp.weixin.qq.com") != 0 && r.indexOf("http://mp.weixin.qq.com") != 0 && outsize_link++;
var i = 0, s = n.innerHTML;
/^[^<>]+$/.test(s) ? i = 1 : /^<img[^>]*>$/.test(s) && (i = 2), r.indexOf("http://mp.weixin.qq.com/mp/redirect") != 0 && (r = "http://" + location.host + "/mp/redirect?url=" + encodeURIComponent(r) + "&action=appmsg_redirect" + "&uin=" + uin + "&biz=" + biz + "&mid=" + mid + "&idx=" + idx + "&type=" + i + "&scene=0"), n.addEventListener ? n.addEventListener("click", function(e) {
e.stopPropagation && e.stopPropagation(), e.preventDefault && e.preventDefault(), location.href = r;
}, !0) : n.attachEvent && n.attachEvent("click", function(e) {
e.stopPropagation && e.stopPropagation(), e.preventDefault && e.preventDefault(), location.href = r;
}, !0);
})(n);
}(), function() {
var e = document.getElementById("gdt_area");
if (abtest == 1 && !!e) {
var t = e.getElementsByTagName("a");
for (var n = 0, r = t.length; n < r; ++n) t[n].setAttribute("class", "random_empha");
}
}(), function() {
var e = document.getElementById("js_pc_qr_code_img");
if (!!e && navigator.userAgent.indexOf("MicroMessenger") == -1) {
var t = 10000004, n = document.referrer;
n.indexOf("http://weixin.sogou.com") == 0 ? t = 10000001 : n.indexOf("https://wx.qq.com") == 0 && (t = 10000003), e.setAttribute("src", "/mp/qrcode?scene=" + t + "&size=102&__biz=" + biz), document.getElementById("js_pc_qr_code").style.display = "block";
}
}(), function() {
var e = document.getElementById("gdt_area");
!!e && navigator.userAgent.indexOf("MicroMessenger") == -1 && (e.style.display = "none");
}(), function() {
function e(e) {
var t = 0;
e.contentDocument && e.contentDocument.body.offsetHeight ? t = e.contentDocument.body.offsetHeight : e.Document && e.Document.body && e.Document.body.scrollHeight ? t = e.Document.body.scrollHeight : e.document && e.document.body && e.document.body.scrollHeight && (t = e.document.body.scrollHeight);
var n = e.parentElement;
!n || (e.style.height = t + "px");
if (/MSIE\s(7|8)/.test(navigator.userAgent) && e.contentWindow && e.contentWindow.document) {
var r = e.contentWindow.document.getElementsByTagName("html");
r && r.length && (r[0].style.overflow = "hidden");
}
}
var t = document.getElementsByTagName("iframe"), n;
for (var r = 0, i = t.length; r < i; ++r) {
n = t[r];
var s = n.getAttribute("data-src"), o = n.className || "";
!!s && (s.indexOf("http://mp.weixin.qq.com/mp/appmsgvote") == 0 && o.indexOf("js_editor_vote_card") >= 0 || s.indexOf("http://mp.weixin.qq.com/bizmall/appmsgcard") == 0 && o.indexOf("card_iframe") >= 0) && (n.setAttribute("src", s.replace("#wechat_redirect", [ "&uin=", uin, "&key=", key ].join(""))), function(t) {
t.onload = function() {
e(t);
};
}(n), n.appmsg_idx = r);
}
window.iframe_reload = function(r) {
for (var i = 0, s = t.length; i < s; ++i) {
n = t[i];
var o = n.getAttribute("src");
!!o && o.indexOf("http://mp.weixin.qq.com/mp/appmsgvote") == 0 && e(n);
}
};
}();

if (ISWP) {
var profile = document.getElementById("post-user");
profile && profile.setAttribute("href", "weixin://profile/" + user_name);
}

var cookie = {
get: function(e) {
if (e == "") return "";
var t = new RegExp(e + "=([^;]*)"), n = document.cookie.match(t);
return n && n[1] || "";
},
set: function(e, t) {
var n = new Date;
n.setDate(n.getDate() + 1);
var r = n.toGMTString();
return document.cookie = e + "=" + t + ";expires=" + r, !0;
}
}, title = trim(htmlDecode(msg_title)), sourceurl = trim(htmlDecode(msg_source_url));

msg_link = htmlDecode(msg_link), function() {
function e() {
var e = "", t = msg_cdn_url, n = msg_link, r = htmlDecode(msg_title), i = htmlDecode(msg_desc);
i = i || n, WeixinJSBridge.call("hideToolbar"), "1" == is_limit_user && WeixinJSBridge.call("hideOptionMenu"), WeixinJSBridge.on("menu:share:appmessage", function(s) {
var o = 1;
s.scene == "favorite" && (o = 4), WeixinJSBridge.invoke("sendAppMessage", {
appid: e,
img_url: t,
img_width: "640",
img_height: "640",
link: share_scene(n, o),
desc: i,
title: r
}, function(e) {
report(n, fakeid, o);
});
}), WeixinJSBridge.on("menu:share:timeline", function(e) {
report(n, fakeid, 2), WeixinJSBridge.invoke("shareTimeline", {
img_url: t,
img_width: "640",
img_height: "640",
link: share_scene(n, 2),
desc: i,
title: r
}, function(e) {});
});
var s = "";
WeixinJSBridge.on("menu:share:weibo", function(e) {
WeixinJSBridge.invoke("shareWeibo", {
content: r + share_scene(n, 3),
url: share_scene(n, 3)
}, function(e) {
report(n, fakeid, 3);
});
}), WeixinJSBridge.on("menu:share:facebook", function(e) {
report(n, fakeid, 4), WeixinJSBridge.invoke("shareFB", {
img_url: t,
img_width: "640",
img_height: "640",
link: share_scene(n, 4),
desc: i,
title: r
}, function(e) {});
}), WeixinJSBridge.on("menu:general:share", function(s) {
var o = 0;
switch (s.shareTo) {
case "friend":
o = 1;
break;
case "timeline":
o = 2;
break;
case "weibo":
o = 3;
}
s.generalShare({
appid: e,
img_url: t,
img_width: "640",
img_height: "640",
link: share_scene(n, o),
desc: i,
title: r
}, function(e) {
report(n, fakeid, o);
});
});
var o = {
"network_type:fail": "fail",
"network_type:edge": "2g",
"network_type:wwan": "3g",
"network_type:wifi": "wifi"
};
typeof WeixinJSBridge != "undefined" && WeixinJSBridge.invoke && WeixinJSBridge.invoke("getNetworkType", {}, function(e) {
networkType = o[e.err_msg], initpicReport();
});
}
typeof WeixinJSBridge == "undefined" ? document.addEventListener ? document.addEventListener("WeixinJSBridgeReady", e, !1) : document.attachEvent && (document.attachEvent("WeixinJSBridgeReady", e), document.attachEvent("onWeixinJSBridgeReady", e)) : e();
}(), function() {
var e = null, t = 0, n = msg_link.split("?").pop(), r = hash(n);
window.addEventListener ? (window.addEventListener("load", function() {
t = cookie.get(r), window.scrollTo(0, t);
}, !1), window.addEventListener("unload", function() {
cookie.set(r, t), reportTimeOnPage();
}, !1), window.addEventListener("scroll", function() {
clearTimeout(e), e = setTimeout(function() {
t = window.pageYOffset;
}, 500);
}, !1), document.addEventListener("touchmove", function() {
clearTimeout(e), e = setTimeout(function() {
t = window.pageYOffset;
}, 500);
}, !1)) : window.attachEvent && (window.attachEvent("load", function() {
t = cookie.get(r), window.scrollTo(0, t);
}, !1), window.attachEvent("unload", function() {
cookie.set(r, t), reportTimeOnPage();
}, !1), window.attachEvent("scroll", function() {
clearTimeout(e), e = setTimeout(function() {
t = window.pageYOffset;
}, 500);
}, !1), document.attachEvent("touchmove", function() {
clearTimeout(e), e = setTimeout(function() {
t = window.pageYOffset;
}, 500);
}, !1));
}(), function() {
function e(e) {
typeof window.WeixinJSBridge != "undefined" && WeixinJSBridge.invoke("imagePreview", {
current: e,
urls: n
});
}
function t() {
var t = document.getElementById("img-content");
t = t ? t.getElementsByTagName("img") : [];
for (var r = 0, i = t.length; r < i; r++) {
var s = t.item(r), o = s.getAttribute("data-src") || s.getAttribute("src");
o && (!!s.dataset && !!s.dataset.s && o.indexOf("http://mmbiz.qpic.cn") == 0 && (o = o.replace(/\/640$/, "/0")), n.push(o), function(t) {
s.addEventListener ? s.addEventListener("click", function() {
e(t);
}, !1) : s.attachEvent && s.attachEvent("click", function() {
e(t);
}, !1);
}(o));
}
}
var n = [];
window.addEventListener ? window.addEventListener("load", t, !1) : window.attachEvent && (window.attachEvent("load", t), window.attachEvent("onload", t));
}();

var has_click = {};

(function() {
var e = document.getElementById("gdt_area");
if (!e) return !1;
if (!e.getElementsByClassName) return e.style.display = "none", !1;
var t = e.getElementsByClassName("js_ad_link") || [];
for (var n = 0, r = t.length; n < r; ++n) (function(e) {
var n = t[e], r = n.dataset, i = r.type, s = r.url, o = r.rl, u = r.apurl, a = r.tid, f = r.ticket, l = r.group_id, c = r.aid, h = r.pt;
n.addEventListener ? n.addEventListener("click", function(e) {
e.stopPropagation && e.stopPropagation(), e.preventDefault && e.preventDefault(), gdt_click(i, s, o, u, a, f, l, c, h);
}, !0) : n.attachEvent && n.attachEvent("click", function(e) {
e.stopPropagation && e.stopPropagation(), e.preventDefault && e.preventDefault(), gdt_click(i, s, o, u, a, f, l, c, h);
}, !0);
})(n);
})(), function() {
function detect() {
var e = window.pageYOffset || document.documentElement.scrollTop, t = 20, n = +(new Date);
for (var r = 0, i = images.length; r < i; r++) {
var s = images[r];
imgs_info[n] = imgs_info[n] || [];
var o = s.el.offsetTop;
if (!s.show && (networkType == "wifi" || e > o + s.height + t || e + height + t > o)) {
var u = s.src;
!!s.el.dataset && !!s.el.dataset.s && u.indexOf("http://mmbiz.qpic.cn") == 0 && (u = u.replace(/\/0$/, "/640")), s.el.onload = function() {
var e = this;
setProperty(e, "height", "auto", "important");
}, s.el.setAttribute("src", u), imgs_info[n].push(u), s.show = !0, setProperty(s.el, "visibility", "visible", "important");
}
ISWP && s.el.width * 1 > sw && (s.el.width = sw);
}
}
function onScroll() {
clearTimeout(timer), timer = setTimeout(detect, 300);
var performance = window.performance || window.msPerformance || window.webkitPerformance;
!has_report_img_time && !!performance && typeof performance.getEntries != "undefined" && function() {
var e = window.pageYOffset || document.documentElement.scrollTop, t = document.getElementById("js_toobar"), n = t.offsetTop;
if (e + innerHeight >= n) {
var r = performance.getEntries(), i = new Image, s = "http://isdspeed.qq.com/cgi-bin/r.cgi?flag1=7839&flag2=7&flag3=1", o = 0, u = 0, a = 0, f = 0, l = [], c = [], h = {};
for (var p = 0, d = r.length; p < d; ++p) {
var v = r[p], m = v.name;
!!m && v.initiatorType == "img" && (m.indexOf("http://mmbiz.qpic.cn") == 0 && (u++, f += v.duration), o++, a += v.duration, h[m] = {
startTime: v.startTime,
responseEnd: v.responseEnd
});
}
if (o > 0 || u > 0) {
o > 0 && (c.push("1=" + Math.round(a / o)), c.push("2=" + o * 1e3), c.push("3=" + Math.round(a))), u > 0 && (c.push("4=" + Math.round(f / u)), c.push("5=" + u * 1e3), c.push("6=" + Math.round(f)));
var g = 0, y = 0, b = 0;
for (var p in imgs_info) if (imgs_info.hasOwnProperty(p)) {
var w = imgs_info[p], E = 0, S = Infinity, x = 0, T = Infinity, N = !1, C = !1;
for (var k = 0, L = w.length; k < L; ++k) {
var m = w[k], A = h[m];
!A || (N = !0, E = Math.max(E, A.responseEnd), S = Math.min(S, A.startTime), m.indexOf("http://mmbiz.qpic.cn") == 0 && (x = Math.max(x, A.responseEnd), T = Math.min(T, A.startTime), C = !0));
}
N && (y += Math.round(E - S)), C && (b += Math.round(x - T));
}
c.push("7=" + y), c.push("8=" + b), i.src = s + "&" + c.join("&");
}
has_report_img_time = !0;
}
}();
var gdt_area = document.getElementById("gdt_area");
if (!!gdt_area && gdt_area.style.display != "none" && !ping_apurl && !!document.querySelectorAll) {
var scrollTop = window.pageYOffset || document.documentElement.scrollTop, offsetTop = gdt_area.offsetTop;
if (scrollTop + innerHeight > offsetTop) {
var gdt_a = document.querySelectorAll("#gdt_area a");
if (gdt_a.length) {
gdt_a = gdt_a[0];
if (gdt_a.dataset && gdt_a.dataset.apurl) {
ping_apurl = !0;
var gid = gdt_a.dataset.gid, tid = gdt_a.dataset.tid;
ajax({
url: "/mp/advertisement_report?report_type=1&tid=" + tid + "&adver_group_id=" + gid + "&apurl=" + encodeURIComponent(gdt_a.dataset.apurl) + "&uin=" + uin + "&key=" + key + "&__biz=" + biz + "&r=" + Math.random() + abtest_param,
success: function(res) {
try {
res = eval("(" + res + ")");
} catch (e) {
res = {};
}
res && res.ret != 0 && (ping_apurl = !1);
},
async: !0
});
}
}
}
}
}
function onLoad() {
var e = document.getElementsByTagName("img"), t = document.getElementById("page-content");
t.currentStyle ? sw = t.currentStyle.width : typeof getComputedStyle != "undefined" && (sw = getComputedStyle(t).width), sw = 1 * sw.replace("px", "");
for (var n = 0, r = e.length; n < r; n++) {
var i = e.item(n);
if (!i.getAttribute("data-src")) continue;
images.push({
el: i,
src: i.getAttribute("data-src"),
height: i.offsetHeight,
show: !1
});
var s = 100;
if (!!i.dataset && !!i.dataset.ratio) {
var o = i.dataset.ratio * 1, u = i.dataset.w * 1 || imgMaxW;
typeof o == "number" && o > 0 && (u = i.offsetWidth || (u <= imgMaxW ? u : imgMaxW), s = u * o);
}
setProperty(i, "height", s + "px", "important"), setProperty(i, "visibility", "hidden", "important");
}
detect(), initpicReport();
}
var timer = null, innerHeight = window.innerHeight || document.documentElement.clientHeight, imgMaxW = document.getElementById("page-content").offsetWidth, height = innerHeight + 40, images = [];
window.detect = detect;
var ping_apurl = !1, has_report_img_time = !1;
window.addEventListener ? (window.addEventListener("scroll", onScroll, !1), window.addEventListener("load", onLoad, !1), document.addEventListener("touchmove", onScroll, !1)) : (window.attachEvent("onscroll", onScroll), window.attachEvent("onload", onLoad));
}(), function() {
function e() {
var e = document.getElementsByTagName("a"), t = !1;
if (!!e) {
var r = e.length;
for (var i = 0; i < r; ++i) {
var s = e[i], o = s.getAttribute("href"), u = /^(http|https):\/\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)(\/)?/g, a = u.exec(o);
a && a[2] && a[2] != "mp.weixin.qq.com" && (t = t || [], t.push(o), n.push(encodeURIComponent(o)));
}
}
return t.length == outsize_link ? !1 : t;
}
function t() {
var e = document.getElementsByTagName("form");
if (e) for (var t = 0; t < e.length; ++t) {
var n = e[t];
if (n) {
var i = n.outerHTML || "";
r.push(encodeURIComponent(n.getAttribute("action") + i.substr(0, 400)));
}
}
return e ? e.length : 0;
}
var n = [], r = [], i = [];
e() && i.push(1), t() && i.push(2), i.length > 0 && (n = "&url=" + n.join("||"), r = "&furl=" + r.join("||"), ajax({
url: "/mp/hijack?type=" + i.join(",") + "&r=" + Math.random() + n + r,
type: "POST",
timeout: 2e3,
data: ""
}));
}(), function() {
function e() {
if (typeof WeixinJSBridge == "undefined") return;
if (has_click[r]) return;
has_click[r] = !0, ajax({
url: "/mp/advertisement_report?report_type=2&type=" + i + "&url=" + encodeURIComponent(s) + "&tid=" + r + "&rl=" + encodeURIComponent(o) + "&uin=" + uin + "&key=" + key + "&__biz=" + biz + "&pt=100&r=" + Math.random(),
type: "GET",
timeout: 1e3,
complete: function(e) {
has_click[r] = !1;
var i = new Image(1, 1);
i.src = "http://mp.weixin.qq.com/mp/ad_report?action=follow&__biz=" + biz + "&uin=" + uin + "&key=" + key + "&type=10&source=" + source + "&scene=" + scene + "&tid=" + r + "&aid=" + ad_biz_profile_aid + "&appuin=" + biz, WeixinJSBridge.invoke("addContact", {
scene: scene,
webtype: "1",
username: ad_biz_profile_usename
}, function(e) {
var s = e.err_msg, o = 6;
s == "add_contact:added" && (o = 7), s == "add_contact:cancel" ? o = 8 : s != "add_contact:fail" && (n.style.display = "inline-block", t.style.display = "none", o = 9), i.src = "http://mp.weixin.qq.com/mp/ad_report?action=follow&__biz=" + biz + "&uin=" + uin + "&key=" + key + "&type=" + o + "&source=" + source + "&scene=" + scene + "&tid=" + r + "&aid=" + ad_biz_profile_aid + "&appuin=" + biz;
});
},
async: !0
});
}
var t = document.getElementById("addContact"), n = document.getElementById("viewProfile");
if (!t || !t.dataset) return;
var r = t.dataset.tid, i = t.dataset.type, s = t.dataset.url, o = t.dataset.rl;
t.addEventListener ? t.addEventListener("click", e, !1) : t.attachEvent("onclick", e);
}(), function() {
function e() {
if (typeof WeixinJSBridge == "undefined") return;
WeixinJSBridge.invoke("profile", {
username: ad_biz_profile_usename
});
}
var t = document.getElementById("viewProfile");
if (!t) return;
t.addEventListener ? t.addEventListener("click", e, !1) : t.attachEvent("onclick", e);
}();