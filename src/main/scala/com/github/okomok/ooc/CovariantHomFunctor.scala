

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class CovariantHomFunctor(_1: Category, _2: Object) extends Functor {
    override def dom: Category = _1
    override def cod: Category = _Set_

    override def at(b: Object): Object = _1(_2, b)
    override def apply(g: Morphism): Morphism = new Function {
        override val dom: Set = _1(_2, _1.dom(g))
        override val cod: Set = _1(_2, _1.cod(g))
        override def apply(f: Morphism): Morphism = _1.compose(g, f)
    }
}
