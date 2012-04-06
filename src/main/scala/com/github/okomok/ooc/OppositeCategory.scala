

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class OppositeCategory(_1: Category) extends CategoryProxy {
    override def selfCategory: Category = _1

    override def dom(f: Morphism): Object = _1.cod(f)
    override def cod(f: Morphism): Object = _1.dom(f)

    override def op: Category = _1
}
