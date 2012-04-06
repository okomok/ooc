

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class CompositeFunctor(_1: Functor, _2: Functor) extends Functor {
    override val dom: Category = _2.dom
    override val cod: Category = _1.cod

    override def at(a: Object): Object = _1.at(_2.at(a))
    override def apply(f: Morphism): Morphism = _1(_2(f))
}
