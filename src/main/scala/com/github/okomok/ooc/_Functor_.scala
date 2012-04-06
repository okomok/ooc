

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


object _Functor_ extends CategoryImpl {
    override protected type _Object = Functor
    override protected type _Morphism = NaturalTransformation

    override protected val _ob: Set = Set.any[_Object]
    override protected val _mor: Set = Set.any[_Morphism]

    override protected def _dom(f: _Morphism): _Object = f.dom
    override protected def _cod(f: _Morphism): _Object = f.cod

    override protected def _id(a: _Object): _Morphism = IdentityNaturalTransformation(a)
    override protected def _compose(g: _Morphism, f: _Morphism): _Morphism = CompositeNaturalTransformation(g, f)
}
