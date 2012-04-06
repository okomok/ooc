

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class IdentityFunctor(_1: Category) extends Functor {
    override def dom: Category = _1
    override def cod: Category = _1

    override def at(a: Object): Object = a
    override def apply(f: Morphism): Morphism = f
}
