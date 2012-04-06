

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


object _0_ extends CategoryImpl {
    override protected type _Object = Nothing
    override protected type _Morphism = Nothing

    override protected val _ob: Set = Set.any[_Object]
    override protected val _mor: Set = Set.any[_Morphism]

    override protected def _dom(f: _Morphism): _Object = throw new Error("unreachable")
    override protected def _cod(f: _Morphism): _Object = throw new Error("unreachable")

    override protected def _id(a: _Object): _Morphism = throw new Error("unreachable")
    override protected def _compose(g: _Morphism, f: _Morphism): _Morphism = throw new Error("unreachable")
}
