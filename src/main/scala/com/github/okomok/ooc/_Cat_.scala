

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


object _Cat_ extends Category {
    override def dom(f: Morphism): Object = f.as[Functor].dom
    override def cod(f: Morphism): Object = f.as[Functor].cod

    override def id(a: Object): Morphism = Functor.id(a.as[Category])
    override def compose(g: Morphism, f: Morphism): Morphism = Functor.compose(g.as[Functor], f.as[Functor])

    override val ob: Class = Class.any[Category]
    override def mor(a: Object, b: Object): Class = Class.any[Functor].where {
        f => (f.as[Functor].dom == a) && (f.as[Functor].cod == b)
    }
}
