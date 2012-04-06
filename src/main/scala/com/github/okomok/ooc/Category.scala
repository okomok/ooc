

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


trait Category extends SetProxy {
    def ob: Set
    def mor: Set

    def dom(f: Morphism): Object
    def cod(f: Morphism): Object

    def id(a: Object): Morphism
    def compose(g: Morphism, f: Morphism): Morphism

    /*final*/ override def selfSet: Set = ob
    final def apply(a: Object, b: Object): Set = mor.filter(f => (dom(f) == a) && (cod(f) == b))

    /*final*/ def op: Category = new _Dual_(this)

    object -> {
        def unapply(f: Morphism): Option[(Object, Object)] = Some(dom(f), cod(f))
    }

    final class Op_o(g: Morphism) {
        def o(f: Morphism): Morphism = compose(g, f)
    }
    final implicit def o(g: Morphism): Op_o = new Op_o(g)
}


trait CategoryProxy extends Category {
    def selfCategory: Category

    override def ob: Set = selfCategory.ob
    override def mor: Set = selfCategory.mor

    override def dom(f: Morphism): Object = selfCategory.dom(f)
    override def cod(f: Morphism): Object = selfCategory.cod(f)

    override def id(a: Object): Morphism = selfCategory.id(a)
    override def compose(g: Morphism, f: Morphism): Morphism = selfCategory.compose(g, f)
}


trait CategoryImpl extends Category {
    protected type _Object
    protected type _Morphism

    protected def _ob: Set
    protected def _mor: Set

    protected def _dom(f: _Morphism): _Object
    protected def _cod(f: _Morphism): _Object

    protected def _id(a: _Object): _Morphism
    protected def _compose(g: _Morphism, f: _Morphism): _Morphism

    final override def ob: Set = _ob
    final override def mor: Set = _mor

    final override def dom(f: Morphism): Object = _dom(f.as[_Morphism])
    final override def cod(f: Morphism): Object = _cod(f.as[_Morphism])

    final override def id(a: Object): Morphism = _id(a.as[_Object])
    final override def compose(g: Morphism, f: Morphism): Morphism = _compose(g.as[_Morphism], f.as[_Morphism])
}
