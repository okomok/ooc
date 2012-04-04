

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final class _Dual_(val dual: Category) extends CategoryProxy {
    override def selfCategory: Category = dual

    override def dom(f: Morphism): Object = dual.cod(f)
    override def cod(f: Morphism): Object = dual.dom(f)

    override def op: Category = dual
}
