

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

    object -> extends Category.Morphisms(this) {
        def unapply(f: Morphism): Option[(Object, Object)] = Some(dom(f), cod(f))
    }

    final class Op_o(g: Morphism) {
        def o(f: Morphism): Morphism = compose(g, f)
    }
    final implicit def o(g: Morphism): Op_o = new Op_o(g)

    def op: Category = Category.Opposite(this)
    def *(that: Category): Category = Category.Product(this, that)
    def ^(that: Category): Category = Category.Functors(that, this)
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
    override def *(that: Category): Category = selfCategory.*(that)
    override def ^(that: Category): Category = selfCategory.^(that)
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


object Category extends CategoryImpl {
    override protected type _Object = Category
    override protected type _Morphism = Functor

    override protected val _ob: Set = Set.any[_Object]
    override protected val _mor: Set = Set.any[_Morphism]

    override protected def _dom(f: _Morphism): _Object = f.dom
    override protected def _cod(f: _Morphism): _Object = f.cod

    override protected def _id(a: _Object): _Morphism = Functor.Identity(a)
    override protected def _compose(g: _Morphism, f: _Morphism): _Morphism = Functor.Composite(g, f)

    final case class Opposite(_1: Category) extends CategoryProxy {
        override def selfCategory: Category = _1

        override def dom(f: Morphism): Object = _1.cod(f)
        override def cod(f: Morphism): Object = _1.dom(f)

        override def op: Category = _1
    }

    class Morphisms(_1: Category) extends CategoryImpl {
        override protected type _Object = Morphism
        override protected type _Morphism = Square

        override protected val _ob: Set = _1.mor
        override protected val _mor: Set = Set.any[Square]

        override protected def _dom(f: _Morphism): _Object = f.left
        override protected def _cod(f: _Morphism): _Object = f.right

        override protected def _id(a: _Object): _Morphism = Square(a, _1.id(_1.dom(a)), a, _1.id(_1.cod(a)))
        override protected def _compose(g: _Morphism, f: _Morphism): _Morphism = Square(f.left, _1.compose(g.top, f.top), g.right, _1.compose(g.bottom, f.bottom))
    }

    final case class Product(_1: Category, _2: Category) extends CategoryImpl {
        override protected type _Object = (Object, Object)
        override protected type _Morphism = (Morphism, Morphism)

        override protected val _ob: Set = Set.any[_Object]
        override protected val _mor: Set = Set.any[_Morphism]

        override protected def _dom(f: _Morphism): _Object = (_1.dom(f._1), _2.dom(f._2))
        override protected def _cod(f: _Morphism): _Object = (_1.cod(f._1), _2.cod(f._2))

        override protected def _id(a: _Object): _Morphism = (_1.id(a._1), _2.id(a._2))
        override protected def _compose(g: _Morphism, f: _Morphism): _Morphism = (_1.compose(g._1, f._1), _2.compose(g._2, f._2))
    }

    final case class Functors(_1: Category, _2: Category) extends CategoryImpl {
        override protected type _Object = Functor
        override protected type _Morphism = NaturalTransformation

        override protected val _ob: Set = Set.any[_Object].filter { F => (F.as[Functor].dom == _1) && (F.as[Functor].cod == _2) }
        override protected val _mor: Set = Set.any[_Morphism]

        override protected def _dom(f: _Morphism): _Object = f.dom
        override protected def _cod(f: _Morphism): _Object = f.cod

        override protected def _id(a: _Object): _Morphism = NaturalTransformation.Identity(a)
        override protected def _compose(g: _Morphism, f: _Morphism): _Morphism = NaturalTransformation.Composite(g, f)
    }

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
