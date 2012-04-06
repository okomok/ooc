

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


trait Functor {
    def dom: Category
    def cod: Category

    def at(a: Object): Object
    def apply(f: Morphism): Morphism

    final def o(that: Functor): Functor = Functor.Composite(this, that)

    def op: Functor = Functor.Opposite(this)
}


object Functor {
    final case class Identity(_1: Category) extends Functor {
        override def dom: Category = _1
        override def cod: Category = _1

        override def at(a: Object): Object = a
        override def apply(f: Morphism): Morphism = f
    }

    final case class Composite(_1: Functor, _2: Functor) extends Functor {
        override val dom: Category = _2.dom
        override val cod: Category = _1.cod

        override def at(a: Object): Object = _1.at(_2.at(a))
        override def apply(f: Morphism): Morphism = _1(_2(f))
    }

    final case class Diagonal(_1: Category) extends Functor {
        override def dom: Category = _1
        override val cod: Category = _1 * _1

        override def at(a: Object): Object = (a, a)
        override def apply(f: Morphism): Morphism = (f, f)
    }

    final case class Opposite(_1: Functor) extends Functor {
        override val dom: Category = _1.dom.op
        override val cod: Category = _1.cod.op

        override def at(a: Object): Object = _1.at(a)
        override def apply(f: Morphism): Morphism = _1(f)

        override def op: Functor = _1
    }

    final case class Hom0(_1: Category) extends Functor {
        override val dom: Category = _1.op * _1
        override def cod: Category = Set

        override def at(a_b: Object): Object = a_b match {
            case (a, b) => _1(a, b)
        }
        override def apply(f: Morphism): Morphism = new Function {
            override val dom: Set = _1(_1.cod(f), _1.dom(f))
            override val cod: Set = _1(_1.dom(f), _1.cod(f))
            override def apply(h_g: Morphism): Morphism = h_g match {
                case (h, g) => _1.compose(g, _1.compose(f, h))
            }
        }
    }

    final case class Hom1(_1: Category, _2: Object) extends Functor {
        override def dom: Category = _1
        override def cod: Category = Set

        override def at(b: Object): Object = _1(_2, b)
        override def apply(g: Morphism): Morphism = new Function {
            override val dom: Set = _1(_2, _1.dom(g))
            override val cod: Set = _1(_2, _1.cod(g))
            override def apply(f: Morphism): Morphism = _1.compose(g, f)
        }
    }

    final case class Hom2(_1: Category, _2: Object) extends Functor {
        override def dom: Category = _1.op
        override def cod: Category = Set

        override def at(a: Object): Object = _1(a, _2)
        override def apply(f: Morphism): Morphism = new Function {
            override val dom: Set = _1(_1.cod(f), _2)
            override val cod: Set = _1(_1.dom(f), _2)
            override def apply(g: Morphism): Morphism = _1.compose(g, f)
        }
    }
}
