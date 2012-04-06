

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


object _1_ extends CategoryImpl {
    override protected type _Object = Unit
    override protected type _Morphism = Unit

    override protected val _ob: Set = Set.any[_Object]
    override protected val _mor: Set = Set.any[_Morphism]

    override protected def _dom(f: _Morphism): _Object = ()
    override protected def _cod(f: _Morphism): _Object = ()

    override protected def _id(a: _Object): _Morphism = ()
    override protected def _compose(g: _Morphism, f: _Morphism): _Morphism = ()
}
