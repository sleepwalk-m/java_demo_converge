// "synct" "syncd"
function s(n) {
    var n = new RegExp("(^| )" + n + "=([^;]*)(;|$)");
    return (n = document.cookie.match(n)) ? unescape(n[2]) : F
}

// t为接口参数 脱敏处理
var t = {
    "url": "脱敏处理",
    "params": {
    },
    "baseURL": "脱敏处理",
};

var a2 = 1000;
var p = "analysis";

function o(n) {
    t = "",
        ['66', '72', '6f', '6d', '43', '68', '61', '72', '43', '6f', '64', '65'].forEach(function (n) {
            t += unescape("%u00" + n)
        });
    var t, e = t;
    return String.fromCharCode(n)
}

function v(t) {
    t = encodeURIComponent(t).replace(/%([0-9A-F]{2})/g, function (n, t) {
        return o("0x" + t)
    });
    try {
        return btoa(t)
    } catch (n) {
        return Buffer.from(t).toString("base64")
    }
}

function h(n, t) {
    t = t || u();
    for (var e = (n = n.split("")).length, r = t.length, a = "charCodeAt", i = 0; i < e; i++)
        n[i] = o(n[i].charCodeAt(0) ^ t[(i + 10) % r].charCodeAt(0));
    return n.join("")
}

var d = "xyz517cda96efgh";

function getSign() {
    try {
        var n;
        // f || F != s || (n = s(m),
        //     s = c[x][k][Pt] = -s("syncd") || +new Date() - a2 * n);

        var e, r = +new Date() - 1661224081041, a = [];
        // return void 0 === t.params && (t.params = {}),

        Object.keys(t.params).forEach(function (n) {
            if (n == p)
                return !1;
            t.params.hasOwnProperty(n) && a.push(t.params[n])
        }),

            a = a.sort().join(""),
            a = v(a),
            a = (a += "@#" + t.url.replace(t.baseURL, "")) + ("@#" + r) + ("@#" + 3),

            e = v(h(a, d)),

        -1 == t.url.indexOf(p) && (t.url += (-1 != t.url.indexOf("?") ? "&" : "?") + p + "=" + encodeURIComponent(e)),
            t
        console.log(e)
    } catch (t) {
    }
}

getSign();