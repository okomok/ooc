

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


trait Set extends Class[Any]


object Set {
    def any[A](implicit _C: ClassManifest[A]): Set = new _Any(_C)

    private class _Any[A](val _C: ClassManifest[A]) extends Set {
        override def contains(x: Any): Boolean = _C.erasure.isInstance(x)
        override def equals(that: Any): Boolean = that match {
            case that: _Any[_] => _C == that._C
        }
    }
}
