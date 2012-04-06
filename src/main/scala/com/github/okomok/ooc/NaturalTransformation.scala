

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


trait NaturalTransformation {
    def dom: Functor
    def cod: Functor

    def apply(a: Object): Morphism

    final def domF: Category = dom.dom
    final def codF: Category = dom.cod

    final def o(that: NaturalTransformation) = NaturalTransformation.Composite(this, that)
}


object NaturalTransformation {
    final case class Identity(_1: Functor) extends NaturalTransformation {
        override def dom: Functor = _1
        override def cod: Functor = _1

        override def apply(a: Object): Morphism = codF.id(_1.at(a))
    }

    final case class Composite(_1: NaturalTransformation, _2: NaturalTransformation) extends NaturalTransformation {
        override val dom: Functor = _2.dom
        override val cod: Functor = _1.cod

        override def apply(a: Object): Morphism = _1.codF.compose(_1(a), _2(a))
    }
}
