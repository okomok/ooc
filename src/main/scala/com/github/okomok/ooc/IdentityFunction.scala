

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class IdentityFunction(_1: Set) extends Function {
    override val dom: Set = _1
    override val cod: Set = _1

    override def apply(x: Any): Any = x
}
