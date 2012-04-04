

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


trait Functor {
    def dom: Category
    def cod: Category

    def at(a: Object): Object
    def apply(f: Morphism): Morphism

    final def o(that: Functor) = Functor.compose(this, that)
}


object Functor {
    def id(C: Category): Functor = new Functor {
        override def dom: Category = C
        override def cod: Category = C
        override def at(a: Object): Object = a
        override def apply(f: Morphism): Morphism = f
    }

    def compose(G: Functor, F: Functor): Functor = new Functor {
        override val dom: Category = F.dom
        override val cod: Category = G.cod
        override def at(a: Object): Object = G.at(F.at(a))
        override def apply(f: Morphism): Morphism = G(F(f))
    }
}
