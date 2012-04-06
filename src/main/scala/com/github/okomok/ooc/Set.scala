

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


trait Set { outer =>
    def contains(x: Any): Boolean

    final def filter(p: Any => Any): Set = new Set {
        override def contains(x: Any): Boolean = outer.contains(x) && p(x).as[Boolean]
    }

    final def map(f: Any => Any): Set = new Set {
        override def contains(x: Any): Boolean = outer.contains(f(x))
    }

    final def withFilter(p: Any => Any): Set = filter(p)
}


trait SetProxy extends Set {
    def selfSet: Set
    override def contains(x: Any): Boolean = selfSet.contains(x)
}


object Set {
    def any[A](implicit _C: ClassManifest[A]): Set = new _Any(_C)

    private class _Any[A](val _C: ClassManifest[A]) extends Set {
        override def contains(x: Any): Boolean = _C.erasure.isInstance(x)
        override def equals(that: Any): Boolean = that match {
            case that: _Any[_] => _C == that._C
            case _ => super.equals(that)
        }
    }
}
