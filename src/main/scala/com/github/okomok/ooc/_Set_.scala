

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


object _Set_ extends Category {
    override def dom(f: Morphism): Object = f.as[Function].dom
    override def cod(f: Morphism): Object = f.as[Function].cod

    override def id(a: Object): Morphism = Function.id(a.as[Set])
    override def compose(g: Morphism, f: Morphism): Morphism = Function.compose(g.as[Function], f.as[Function])

    override val ob: Class = Class.any[Set]
    override def mor(a: Object, b: Object): Class = Class.any[Function].where {
        f => (f.as[Function].dom == a) && (f.as[Function].cod == b)
    }
}
