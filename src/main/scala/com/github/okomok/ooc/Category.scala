

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
    //final def apply[a, b, R](a: a, b: b)(implicit _A: Category.Apply[a, b, R]): R = _A(this, a, b)

    def op: Category = new OppositeCategory(this)

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

    override def op: Category = selfCategory.op
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


object Category {
/*
    sealed trait Apply[a, b, R] {
        def apply(C: Category, a: a, b: b): R
    }

    sealed trait ApplyLowPriority { this: Apply.type =>
        implicit def _ofDefault[a, b]: Apply[a, b, Set] = new Apply[a, b, Set] {
            override def apply(C: Category, a: a, b: b): Set = C.mor.filter(f => (C.dom(f) == a) && (C.cod(f) == b))
        }
    }

    object Apply extends ApplyLowPriority {
        implicit def _ofHomFunctor[a]: Apply[a, X.type, Functor] = new Apply[a, X.type, Functor] {
            override def apply(C: Category, a: a, b: X.type): Functor = Functor.hom(C, a)
        }

        implicit def _ofCHomFunctor[b]: Apply[X.type, b, Functor] = new Apply[X.type, b, Functor] {
            override def apply(C: Category, a: X.type, b: b): Functor = Functor.chom(C, b)
        }

        implicit val _ofBiHomFunctor: Apply[X.type, X.type, Functor] = new Apply[X.type, X.type, Functor] {
            override def apply(C: Category, a: X.type, b: X.type): Functor = Functor.bihom(C)
        }
    }
*/
}
