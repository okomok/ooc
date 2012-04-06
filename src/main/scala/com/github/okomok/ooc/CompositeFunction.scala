

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class CompositeFunction(_1: Function, _2: Function) extends Function {
    override val dom: Set = _2.dom
    override val cod: Set = _1.cod
    override def apply(x: Any): Any = _1(_2(x))
}
