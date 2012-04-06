

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


object _Set_ extends CategoryImpl {
    override protected type _Object = Set
    override protected type _Morphism = Function

    override protected val _ob: Set = Set.any[Set]
    override protected val _mor: Set = Set.any[Function]

    override protected def _dom(f: _Morphism): _Object = f.dom
    override protected def _cod(f: _Morphism): _Object = f.cod

    override protected def _id(a: _Object): _Morphism = Function.id(a)
    override protected def _compose(g: _Morphism, f: _Morphism): _Morphism = Function.compose(g, f)
}
