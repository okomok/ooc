

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class OppositeFunctor(_1: Functor) extends Functor {
    override val dom: Category = _1.dom.op
    override val cod: Category = _1.cod.op

    override def at(a: Object): Object = _1.at(a)
    override def apply(f: Morphism): Morphism = _1(f)

    override def op: Functor = _1
}
