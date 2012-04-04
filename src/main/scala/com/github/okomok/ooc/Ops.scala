

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final class Op_as(x: Any) {
    def as[T]: T = x.asInstanceOf[T]
}
