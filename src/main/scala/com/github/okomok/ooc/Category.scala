

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


trait Category {
    type Object
    type Morphism

    def dom(f: Morphism): Object
    def cod(f: Morphism): Object

    def id(a: Object): Morphism
    def compose(g: Morphism, f: Morphism): Morphism

    def ob: Class[Object]
    def apply(a: Object, b: Object): Class[Morphism]

    object -> {
        def unapply(f: Morphism): Option[(Object, Object)] = Some(dom(f), cod(f))
    }

    final class Op_o(g: Morphism) {
        def o(f: Morphism): Morphism = compose(g, f)
    }
    implicit def o(g: Morphism): Op_o = new Op_o(g)
}
