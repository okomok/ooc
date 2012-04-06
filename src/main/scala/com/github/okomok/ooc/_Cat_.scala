

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


object _Cat_ extends CategoryImpl {
    override protected type _Object = Category
    override protected type _Morphism = Functor

    override protected val _ob: Set = Set.any[Category]
    override protected val _mor: Set = Set.any[Functor]

    override protected def _dom(f: _Morphism): _Object = f.dom
    override protected def _cod(f: _Morphism): _Object = f.cod

    override protected def _id(a: _Object): _Morphism = Functor.id(a)
    override protected def _compose(g: _Morphism, f: _Morphism): _Morphism = Functor.compose(g, f)
}
