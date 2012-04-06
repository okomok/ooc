

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


trait Function extends (Any => Any) {
    def dom: Set
    def cod: Set

    final def o(that: Function): Function = Function.Composite(this, that)
}


object Function {
    implicit def apply[A, B](f: A => B)(implicit _CA: ClassManifest[A], _CB: ClassManifest[B]): Function = new Function {
        override val dom: Set = Set.any(_CA)
        override val cod: Set = Set.any(_CB)
        override def apply(x: Any): Any = f(x.asInstanceOf[A])
    }

    final case class Identity(_1: Set) extends Function {
        override val dom: Set = _1
        override val cod: Set = _1
        override def apply(x: Any): Any = x
    }

    final case class Composite(_1: Function, _2: Function) extends Function {
        override val dom: Set = _2.dom
        override val cod: Set = _1.cod
        override def apply(x: Any): Any = _1(_2(x))
    }
}
