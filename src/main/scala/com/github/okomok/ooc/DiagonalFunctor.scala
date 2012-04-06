

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class DiagonalFunctor(_1: Category) extends Functor {
    override def dom: Category = _1
    override val cod: Category = _Product_(_1, _1)

    override def at(a: Object): Object = (a, a)
    override def apply(f: Morphism): Morphism = (f, f)
}
