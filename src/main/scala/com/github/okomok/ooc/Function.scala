

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


trait Function {
    def dom: Set
    def cod: Set

    def apply(x: Any): Any

    final def o(that: Function): Function = Function.compose(this, that)
}


object Function {
    implicit def apply[A, B](f: A => B)(implicit _CA: ClassManifest[A], _CB: ClassManifest[B]): Function = new Function {
        override val dom: Set = Set.any(_CA)
        override val cod: Set = Set.any(_CB)
        override def apply(x: Any): Any = f(x.asInstanceOf[A])
    }

    def id(a: Set): Function = new Function {
        override val dom: Set = a
        override val cod: Set = a
        override def apply(x: Any): Any = x
    }

    def compose(g: Function, f: Function): Function = new Function {
        override val dom: Set = f.dom
        override val cod: Set = g.cod
        override def apply(x: Any): Any = g(f(x))
    }
}
