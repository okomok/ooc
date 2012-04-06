

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class CompositeNaturalTransformation(_1: NaturalTransformation, _2: NaturalTransformation) extends NaturalTransformation {
    override val dom: Functor = _2.dom
    override val cod: Functor = _1.cod

    override def apply(a: Object): Morphism = _1.codF.compose(_1(a), _2(a))
}
