

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


object _Set_ extends Category {
    override type Object = Set
    override type Morphism = Function

    override def dom(f: Morphism): Object = f.dom
    override def cod(f: Morphism): Object = f.cod

    override def id(a: Object): Morphism = Function.id(a)
    override def compose(g: Morphism, f: Morphism): Morphism = g o f

    override val ob: Class[Object] = Class.any[Object]
    override def apply(a: Object, b: Object): Class[Morphism] = Class.any[Morphism].where {
        f => (f.dom == a) && (f.cod == b)
    }
}
