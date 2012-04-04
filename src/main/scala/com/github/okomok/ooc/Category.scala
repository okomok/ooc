

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


trait Category extends ClassProxy {
    def dom(f: Morphism): Object
    def cod(f: Morphism): Object

    def id(a: Object): Morphism
    def compose(g: Morphism, f: Morphism): Morphism

    def ob: Class
    def mor(a: Object, b: Object): Class

    override def selfClass: Class = ob
    final def apply(a: Object, b: Object): Class = mor(a, b)

    object -> {
        def unapply(f: Morphism): Option[(Object, Object)] = Some(dom(f), cod(f))
    }

    final class Op_o(g: Morphism) {
        def o(f: Morphism): Morphism = compose(g, f)
    }
    implicit def o(g: Morphism): Op_o = new Op_o(g)

    def op: Category = new _Dual_(this)
}


trait CategoryProxy extends Category with ClassProxy {
    def selfCategory: Category

    override def dom(f: Morphism): Object = selfCategory.dom(f)
    override def cod(f: Morphism): Object = selfCategory.cod(f)

    override def id(a: Object): Morphism = selfCategory.id(a)
    override def compose(g: Morphism, f: Morphism): Morphism = selfCategory.compose(g, f)

    override def ob: Class = selfCategory.ob
    override def mor(a: Object, b: Object): Class = selfCategory.mor(a, b)
}
