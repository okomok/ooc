

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class MixedvariantHomFunctor(_1: Category) extends Functor {
    override val dom: Category = _Product_(_1.op, _1)
    override def cod: Category = _Set_

    override def at(a_b: Object): Object = a_b match {
        case (a, b) => _1(a, b)
    }
    override def apply(f: Morphism): Morphism = new Function {
        override val dom: Set = _1(_1.cod(f), _1.dom(f))
        override val cod: Set = _1(_1.dom(f), _1.cod(f))
        override def apply(h_g: Morphism): Morphism = h_g match {
            case (h, g) => _1.compose(g, _1.compose(f, h))
        }
    }
}
