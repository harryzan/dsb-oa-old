// Title: COOLjsOutlookBar
// URL: http://javascript.cooldev.com/scripts/outlook/
// Version: 1.2.3
// Last Modify: 11 Apr 2007

// Options: STANDARD, COMPRESSED

function _1v(_r) {
    return typeof(_r) == 'undefined'
}
;
function _1R(_r) {
    return typeof(_r) == 'number'
}
;
function _1A(_r) {
    return typeof(_r) == 'object'
}
;
var _O = {visited:8,current:4,rollovered:1,clicked:2};
var _t = -1,_b = -1,_14 = false;
var _15 = {};
var _1h = {4:null};
function COOLjsOutlookBar(_3) {
    bw = new _1B();
    window.$bar = this;
    this._3 = _3;
    this._A = false;
    this._o = 0;
    this._N = 0;
    this._C = '';
    window.onload = function() {
        window.$bar._g()
    };
    window.onunload = function() {
        window.$bar.__()
    };
    window.onresize = function() {
        if (bw.ns4)document.location.reload();
        else window.$bar._l()
    };
    if (bw.ie)setInterval(window.onresize, 1000);
    document.onselect = function() {
        return false
    };
    if (bw.operaOld) {
        $iw = innerWidth;
        $ih = innerHeight;
        $tiw = top.innerWidth;
        $tih = top.innerHeight;
        window.setInterval('if($iw!=innerWidth||$ih!=innerHeight||$tiw!=top.innerWidth||$tih!=top.innerHeight)document.location.reload();', 300)
    }
    ;
    this.$panels = this._c = [];
    this._4 = [];
    this._1O = null;
    for (var i = 0; i < this._3.panels.length; i++)this._c[i] = new _1i(this, i);
    document.write('<div id="dummyNN4Layer" style="left: 0; top: 0;"></div>' + this._C)
}
;
$ = COOLjsOutlookBar.prototype;
$._1P = function(_F, _K) {
    window.open(_F, _K || this._3.format.target)
};
$._g = function() {
    for (var i = 0; i < this._c.length; i++)this._c[i]._g();
    var _1a = this._3.format.defaultPanel || 0;
    if (_1a != -1)this._c[_1a]._1b(0);
    else this._l()
};
$.__ = function() {
    for (var i = 0; i < this._c.length; i++)this._c[i].__()
};
$._18 = function(_1G) {
    this._C += _1G
};
$._16 = function(_8) {
    document.body.appendChild(_8, 'beforeEnd')
};
$._19 = function() {
    if (this._U) {
        window.clearTimeout(this._U);
        this._A = false;
        this._l()
    }
    ;
    this._o = 0;
    this._A = true
};
$.$update = $._l = function(_n) {
    for (var i = this._c.length - 1; i >= 0; i--)this._c[i]._l(this._A && _n);
    if (this._A)if (this._o < 1000) {
        this._o = Math.round(Math.min(this._o + 1000 / this._3.format.animationSteps, 1000));
        this._U = window.setTimeout('window.$bar.$update(' + _n + ')', this._3.format.animationDelay)
    }
    else {
        this._o = 0;
        this._A = false;
        this._U = null;
        this._l();
        if (!_n && _b != -1 && this._N) {
            if (!_14)this._c[_b]._V(this._N);
            _14 = false
        }
    }
};
$._1j = function() {
    return(bw.ie &&
           (document.body.offsetHeight || document.body.parentElement && document.body.parentElement.offsetHeight) ||
           ((window.innerHeight || 0) + (bw.ns4 ? 4 : 0)))
};
$._e = function() {
    try {
        var _m = this._1j();
        for (var i = 0; i < this._c.length; i++)_m -= this._c[i]._1.h;
        return _m
    }
    catch(E) {
    }

};
$._1u = function(_r) {
    return Math.round(_r * (1000 - this._o) / 1000)
};
$._1s = function(_8) {
    this._4[this._4.length] = _8;
    _8._H = this._4[this._4.length - 2] || null;
    _8._J = null;
    if (_8._H)_8._H._J = _8
};
function _G(_R, _S, _w, _L, _u, _s, _I, _B, _A) {
    this._7 = 0;
    this._z = false;
    this._u = _u;
    this._I = _I || '';
    this._B = _B || '';
    this._j = {};
    this._T = null;
    this._D = {};
    for (var i in _w)if (i == 'normal') {
        var _7 = 0;
        var _W = i.split(/_/);
        for (var j in _W)if (_O[_W[j]])_7 |= _O[_W[j]];
        var _1 = new _v(false, _s);
        this._j[_7] = _1;
        if (_A) {
            _1._Z(_R, 0, _S * 2 + 2, this._1q(_w[i], _L, _w.common));
        }
        else {
            _1._Z(_R, 0, _S * 2, this._1q(_w[i], _L, _w.common));
        }
        if (!this._T)this._T = _1;
        this._D[_7] = _7
    }
    this._1p(8 | 4 | 2 | 1, 0);
    this._i = new _v(true, _s);
    this._i._Z(_R, 10, _S * 2 + 1, bw.realDom ? '<img src="' + window.$bar._3.format.blankImage +
                                                '" width="100%" height="100%" alt="' + this._I + '" title="' + this._I +
                                                '" />' : '')
}
;
$ = _G.prototype;
$._1p = function(_7, _H) {
    for (var i in _O) {
        var _J = _7 & ~_O[i];
        if (_7 != _J)this._1p(_J, _7)
    }
    ;
    if (_1v(this._D[_H]))this._D[_H] = this._D[_7]
};
$._E = function() {
    return this._j[this._D[this._7]]
};
$._g = function() {
    for (var i in this._j)this._j[i]._g();
    this._i._g();
    with (this._T) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h
    }
    ;
    this._i.el._8 = this;
    this._i.el.onmousedown = function() {
        with (this._8)_1k(2),_11()
    };
    this._i.el.onmouseup = function() {
        if (bw.ns4)this.onclick();
        with (this._8)_1o(2),_10()
    };
    this._i.el.onclick = function() {
        with (this._8)_1k(4 | 8),_X()
    };
    if (bw.realDom)for (var i in this._j) {
        this._j[i]._k();
        this._j[i]._q()
    }
};
$.__ = function() {
    for (var i in this._j)this._j[i].__();
    this._i.__()
};
$._1Q = function() {
    if (this._B)top.defaultStatus = this._B
};
$._1l = function() {
    if (this._B)top.defaultStatus = ''
};
$._11 = $._10 = $._X = function() {
};
$._1m = function(_7) {
    if (this._7 != _7) {
        if (this._z)this._E()._q();
        this._7 = _7;
        if (this._z)this._E()._k()
    }
};
$._k = function() {
    if (!this._z) {
        this._E()._k();
        this._i._k();
        this._z = true
    }
    ;
    this._i._1r(this.h = this._E()._$())
};
$._q = function() {
    if (this._z) {
        this._E()._q();
        this._i._q();
        this._z = false
    }
};
$._p = function(_z, _d) {
    for (var i in this._j)this._j[i]._p(_z, _d);
    this._i._p(_z, _d);
    this.x = _z;
    this.y = _d
};
$._1k = function(_Q) {
    for (var i in this._u)if (_Q & i && !_1v(this._u[i])) {
        if (this._u[i])this._u[i]._1o(i);
        this._u[i] = this
    }
    ;
    this._1m(this._7 | _Q)
};
$._1o = function(_Q) {
    this._1m(this._7 & ~_Q)
};
$._1q = function(_y, _L, _1K) {
    if (_1A(_y))_y = this._1q(_1K, _y);
    for (var _1w in _L)_y = _y.replace(new RegExp('\\{' + _1w + '\\}', 'g'), _L[_1w]);
    return _y
};
function _1i(_2, _5) {
    try {
        this._2 = _2;
        this._5 = _5;
        this._a = 0;
        var _3 = this._2._3.panels[this._5];
        var _w = this._2._3.format.templates;
        this._F = _3.url;
        this._K = _3.target;
        this._1 = new _G(bw.ns4 ? null : '100%', 1, _w.panel, _3, _1h, null, _3.alt, _3.status);
        this._2._1s(this._1);
        this._1._6 = this;
        this._1._X = function() {
            this._6._1x(this);
            if (this._6._1L()) {
                this._6._2._19();
                this._6._1b(0)
            }
            ;
            _1t(this._6)
        };
        this._9 = new _v(true);
        this._4 = [];
        for (var i = 0; i < this._2._3.panels[this._5].items.length; i++) {
            try {
                var _3 = this._2._3.panels[this._5].items[i];
                this._4[i] = new _G(bw.ns4 ? null : '100%', 10, _w.item, _3, _1h, this._9, _3.alt, _3.status, true);
                this._2._1s(this._4[i]);
                if (_3.url) {
                    this._4[i]._5 = i;
                    this._4[i]._F = _3.url;
                    this._4[i]._K = _3.target;
                    this._4[i]._6 = this
                }
                ;
                this._4[i]._X = function() {
                    if (this._6) {
                        this._6._1x(this);
                        if (this._5 < this._6._a) {
                            this._6._a = this._5;
                            this._6._l()
                        }
                        else while (this._5 != this._6._a && !this._6._1I(this, true, this._6._9.y)) {
                            this._6._a++;
                            this._6._l()
                        }
                        _1t(this)
                    }
                    ;
                }
            }
            catch(E) {
            }
        }
        ;
        this.arr_up = new _G('auto', 20, this._2._3.format.templates.upArrow, {}, _15, this._9);
        this.arr_up._6 = this;
        this.arr_up._11 = function() {
            this._6._V(-1)
        };
        this.arr_dn = new _G('auto', 20, this._2._3.format.templates.downArrow, {}, _15, this._9);
        this.arr_dn._6 = this;
        this.arr_dn._11 = function() {
            this._6._V(+1)
        };
        this.arr_up._1l = this.arr_up._10 = this.arr_dn._1l = this.arr_dn._10 = function() {
            this._6._2._N = 0
        };
        this._9._Z('100%', 0, 1);
        this._1n = true
    }
    catch(E) {
    }
}
;
$ = _1i.prototype;
$._g = function() {
    try {
        this._1._g();
        this._9._g();
        for (var i = 0; i < this._4.length; i++)this._4[i]._g();
        this.arr_up._g();
        this.arr_dn._g()
    }
    catch(E) {
    }
};
$.__ = function() {
    this._1.__();
    for (var i = 0; i < this._4.length; i++)this._4[i].__();
    this.arr_up.__();
    this.arr_dn.__();
    this._9.__()
};
$._1x = function(_1H) {
    this._2._1O = _1H
};
$._13 = function() {
    return bw.ie &&
           (document.body.offsetWidth || document.body.parentElement && document.body.parentElement.offsetWidth) ||
           document.body.innerWidth
};
$._1I = function(_1, _1M, _f) {
    if (!_f)_f = 0;
    if (_1M)return _1.y + _f >= this._1.y + this._1.h && _1.y + _f + _1.h <= this._1.y + this._1.h + this._2._e();
    else return(_1.y + _f >= this._1.y + this._1.h && _1.y + _f < this._1.y + this._1.h + this._2._e()) ||
               (_1.y + _f + _1.h >= this._1.y + this._1.h && _1.y + _f + _1.h < this._1.y + this._1.h + this._2._e())
};
$._17 = function(_1g) {
    var _m = 0;
    for (var i = 0; i < this._5; i++)_m += this._2._c[i]._1.h;
    if (_1g >= 0 && this._5 > _1g)_m += this._2._e();
    return _m
};
$._e = function() {
    return(this._5 == this._2._c.length - 1 ? this._2._1j() : this._2._c[this._5 + 1]._1.y) - this._1.y - this._1.h
};
$.$update = $._l = function(_n) {
    try {
        var _d = this._17(_b);
        this._1._p(0, _d + (_n ? this._2._1u(this._17(_t) - _d) : 0));
        this._1._k();
        if (_b == this._5 || (_n && _t == this._5)) {
            this._9._k();
            if (this._P() < this._2._e())while (this._a > 0) {
                this._a--;
                if (this._P() > this._2._e()) {
                    this._a++;
                    break
                } else if (this._P() >= this._2._e())break
            }
            ;
            _d += this._1.h;
            if (this._2._o == 0) {
                this._f = 0;
                if (_n) {
                    if (this._5 == _b) {
                        if (_b > _t && _t != -1)this._f = this._2._e()
                    }
                    else {
                        if (_t < _b || _b == -1)return;
                        else this._f = -this._2._e()
                    }
                } else if (this._2._A)this._f = this._9.y + this._4[this._a].y - _d
            }
            ;
            for (var i = 0; i < this._a; i++)_d -= this._4[i].h;
            _d += this._2._1u(this._f);
            if (this._1n) {
                this._1n = false;
                var _Y = 0;
                for (var i = 0; i < this._4.length; i++) {
                    this._4[i]._k();
                    this._4[i]._p(0, _Y);
                    _Y += this._4[i].h
                }
                ;
                this._9._1r(_Y)
            }
            ;
            this._9._p(0, _d);
            this._9._1N(this._1.y + this._1.h - this._9.y, this._13(), this._1.y + this._1.h - this._9.y + this._e(), 0)
        }
        else this._9._q();
        this.arr_up._p(this._13() - this.arr_up.w, this._1.y + this._1.h - this._9.y);
        this.arr_dn._p(this._13() - this.arr_dn.w, this._1.y + this._1.h - this._9.y + this._2._e() - this.arr_dn.h);
        if (this._1f())this.arr_up._k(); else if (!_n || this._2._o == 1000)this.arr_up._q();
        if (this._1e())this.arr_dn._k(); else if (!_n || this._2._o == 1000)this.arr_dn._q()
    }
    catch(E){}
};
$._V = function(_12) {
    if (_12 < 0 ? this._1f() : this._1e()) {
        this._2._N = _12;
        this._2._19();
        this._a += _12;
        this._2._l()
    }
};
$._P = function() {
    var _m = 0;
    for (var i = this._a; i < this._4.length; i++)_m += this._4[i].h;
    return _m
};
$._1e = function() {
    return this._a < this._4.length - 1 && _b == this._5 && this._P() > this._2._e()
};
$._1f = function() {
    return this._a > 0 && _b == this._5
};
$._1L = function() {
    return _b != this._5 || this._2._3.format.rollback
};
$._1b = function(_a, _1J) {
    if (_b == this._5) {
        if (!_1J && this._2._3.format.rollback) {
            _t = this._5;
            _b = -1
        }
        else return
    }
    else {
        _t = _b;
        _b = this._5
    }
    ;
    this._a = _a;
    this._2._l(true)
};
function _1B() {
    this.ver = navigator.appVersion;
    this.agent = navigator.userAgent;
    this.dom = document.getElementById ? 1 : 0;
    this.opera5 = this.agent.indexOf("Opera 5") > -1;
    this.ie5 = this.ver.indexOf("MSIE 5") > -1 && this.dom && !this.opera5;
    this.ie6 = this.ver.indexOf("MSIE 6") > -1 && this.dom && !this.opera5;
    this.ie4 = (document.all && !this.dom && !this.opera5) ? 1 : 0;
    this.operaNew = this.agent.match(/opera.[789]/i);
    this.opera = window.opera;
    this.operaOld = this.opera && !this.operaNew;
    this.realDom = this.dom && !this.operaOld;
    this.ns4 = document.layers && !this.dom && !this.operaOld;
    this.ie = this.ver.indexOf("MSIE") && !this.opera
}
;
function _v(_M, _s) {
    this.id = 'do_' + (_v._5++);
    this._M = _M;
    this._s = _s || window.$bar;
    this._1c = ''
}
;
_v._5 = 0;
$ = _v.prototype;
$._g = function() {
    this.el =
    bw.dom ? document.getElementById(this.id) : bw.ie4 ? document.all[this.id] : bw.ns4 ? document.layers[this.id] : 0;
    this.css = bw.dom || bw.ie4 ? this.el.style : this.el;
    this.doc = bw.dom || bw.ie4 ? document : this.css.document;
    this.x = parseInt(this.css.left) || this.css.pixelLeft || this.el.offsetLeft || 0;
    this.y = parseInt(this.css.top) || this.css.pixelTop || this.el.offsetTop || 0;
    this.w = this._1d();
    this.h = this._$()
};
$.__ = function() {
    this.el = null;
    this.css = null;
    this.doc = null
};
$._1d = function() {
    try {
        return this.el.offsetWidth || this.css.clip.width || this.doc.width || this.css.pixelWidth || 0
    }
    catch(E) {
    }
};
$._$ = function() {
    try {
        return this.el.offsetHeight || this.css.clip.height || this.doc.height || this.css.pixelHeight || 0
    }
    catch(E) {
    }
};
$._p = function(_z, _d) {
    this.x = _z;
    this.y = _d;
    if (this.el) {
        var px = bw.ns4 || bw.operaOld ? 0 : 'px';
        try {
            this.css.left = _z + px;
            this.css.top = _d + px
        }
        catch(E) {
        }
    }
};
$._1r = function(__) {
    try {
        this.h = __;
        if (this.el) {
            if (bw.ns4)this.el.resize(this.w, __);
            else {
                var px = bw.operaOld ? 0 : 'px';
                this.css.height = __ + px
            }
        }
    }
    catch(E) {
    }
};
$._1N = function(_1z, _1F, _1C, _1D) {
    this.el.style.clip = 'rect(' + _1z + 'px ' + _1F + 'px ' + _1C + 'px ' + _1D + 'px)'
};
$._k = function() {
    try {
        if (bw.realDom && !this.el && !this._M) {
            this.el = document.createElement('DIV');
            this.el.innerHTML = this._C;
            this.el.style.position = 'absolute';
            this.el.style.width = this._R || (this.w + 'px');
            this.el.style.left = this.x + 'px';
            this.el.style.top = this.y + 'px';
            this.el.style.zIndex = this._S;
            this._s._16(this.el, 'beforeEnd');
            this.css = this.el.style;
            this.w = this._1d();
            this.h = this._$()
        }
        this.css.visibility = bw.ns4 ? 'show' : "inherit";
    }
    catch(E) {
    }
};
$._q = function() {
    try {
        this.css.visibility = bw.ns4 ? 'hide' : "hidden";
        if (bw.realDom && this.el && !this._M) {
            this.el.parentNode.removeChild(this.el);
            this.el.innerHTML = '';
            this.css = null;
            this.el = null
        }
    }
    catch(E) {
    }
};
$._Z = function(w, h, z, _1E) {
    this._C = (_1E || '') + this._1c;
    this._S = z;
    this._R = w;
    if (z == 3) {
        this._s._18('<div ondrag="return false" id="' + this.id + '" style="cursor:pointer;position:absolute;z-index:' +
                    z + ';left:0;top:0;' + (w ? ' width:' + w + ';' : '') +
                    'height:auto;visibility:hidden;overflow:hidden;">' +
                    this._C + '</div>')
    }
    else{
        this._s._18('<div ondrag="return false" id="' + this.id + '" style="position:absolute;z-index:' + z +
                ';left:0;top:0;' + (w ? ' width:' + w + ';' : '') + 'height:auto;visibility:hidden;overflow:hidden;">' +
                this._C + '</div>')
    }

};
$._18 = function(_1y) {
    this._1c += _1y
};
$._16 = function(_8) {
    this.el.appendChild(_8, 'beforeEnd')
};
function _1t(_8) {
    if (_8._F)window.$bar._1P(_8._F, _8._K)
}
