

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class ContravariantHomFunctor(_1: Category, _2: Object) extends Functor {
    override def dom: Category = _1.op
    override def cod: Category = _Set_
    override def at(a: Object): Object = _1(a, _2)
    override def apply(f: Morphism): Morphism = new Function {
        override val dom: Set = _1(_1.cod(f), _2)
        override val cod: Set = _1(_1.dom(f), _2)
        override def apply(g: Morphism): Morphism = _1.compose(g, f)
    }
}
