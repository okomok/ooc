

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


trait NaturalTransformation {
    def dom: Functor
    def cod: Functor

    def apply(a: Object): Morphism

    final def domF: Category = dom.dom
    final def codF: Category = dom.cod

    final def o(that: NaturalTransformation) = NaturalTransformation.compose(this, that)
}


object NaturalTransformation {
    def id(F: Functor): NaturalTransformation = new NaturalTransformation {
        override def dom: Functor = F
        override def cod: Functor = F
        override def apply(a: Object): Morphism = codF.id(F.at(a))
    }

    def compose(t: NaturalTransformation, s: NaturalTransformation): NaturalTransformation = new NaturalTransformation {
        override val dom: Functor = s.dom
        override val cod: Functor = t.cod
        override def apply(a: Object): Morphism = t.codF.compose(t(a), s(a))
    }
}
