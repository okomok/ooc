

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class IdentityNaturalTransformation(_1: Functor) extends NaturalTransformation {
    override def dom: Functor = _1
    override def cod: Functor = _1

    override def apply(a: Object): Morphism = codF.id(_1.at(a))
}
