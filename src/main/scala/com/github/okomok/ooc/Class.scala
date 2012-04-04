

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


trait Class[A] { outer =>
    def contains(x: A): Boolean

    final def where(p: A => Boolean): Class[A] = new Class[A] {
        override def contains(x: A): Boolean = outer.contains(x) && p(x)
    }

    final def isSmall: Boolean = isInstanceOf[Set]
    final def isProper: Boolean = !isSmall
}


object Class {
    def any[A]: Class[A] = new Class[A] {
        override def contains(x: A): Boolean = true
    }
}
