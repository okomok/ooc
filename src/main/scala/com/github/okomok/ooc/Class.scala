

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


trait Class { outer =>
    def contains(x: Any): Boolean

    final def where(p: Any => Boolean): Class = new Class {
        override def contains(x: Any): Boolean = outer.contains(x) && p(x)
    }

    final def isSmall: Boolean = isInstanceOf[Set]
    final def isProper: Boolean = !isSmall
}


trait ClassProxy extends Class {
    def selfClass: Class
    override def contains(x: Any): Boolean = selfClass.contains(x)
}


object Class {
    def any[A](implicit _C: ClassManifest[A]): Class = new Class {
        override def contains(x: Any): Boolean = _C.erasure.isInstance(x)
    }
}
