

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


trait Functor {
    def dom: Category
    def cod: Category

    def at(a: Object): Object
    def apply(f: Morphism): Morphism

    final def o(that: Functor) = Functor.compose(this, that)
}


object Functor {
    def id(C: Category): Functor = new Functor {
        override def dom: Category = C
        override def cod: Category = C
        override def at(a: Object): Object = a
        override def apply(f: Morphism): Morphism = f
    }

    def compose(G: Functor, F: Functor): Functor = new Functor {
        override val dom: Category = F.dom
        override val cod: Category = G.cod
        override def at(a: Object): Object = G.at(F.at(a))
        override def apply(f: Morphism): Morphism = G(F(f))
    }

    def diagonal(C: Category): Functor = new Functor {
        override def dom: Category = C
        override val cod: Category = _Product_(C, C)
        override def at(a: Object): Object = (a, a)
        override def apply(f: Morphism): Morphism = (f, f)
    }

    def hom(C: Category, a: Object): Functor = new Functor {
        override def dom: Category = C
        override def cod: Category = _Set_
        override def at(b: Object): Object = C(a, b)
        override def apply(g: Morphism): Morphism = new Function {
            override val dom: Set = C(a, C.dom(g))
            override val cod: Set = C(a, C.cod(g))
            override def apply(g: Morphism): Morphism = Function((f: Morphism) => _Set_.compose(g, f))
        }
    }

    def chom(C: Category, b: Object): Functor = new Functor {
        override def dom: Category = C.op
        override def cod: Category = _Set_
        override def at(a: Object): Object = C(a, b)
        override def apply(f: Morphism): Morphism = new Function {
            override val dom: Set = C(C.cod(f), b)
            override val cod: Set = C(C.dom(f), b)
            override def apply(f: Morphism): Morphism = Function((g: Morphism) => _Set_.compose(g, f))
        }
    }

    def bihom(C: Category): Functor = new Functor {
        override def dom: Category = _Product_(C.op, C)
        override def cod: Category = _Set_
        override def at(a_b: Object): Object = a_b match {
            case (a, b) => C(a, b)
        }
        override def apply(f: Morphism): Morphism = new Function {
            override val dom: Set = C(C.cod(f), C.dom(f))
            override val cod: Set = C(C.dom(f), C.cod(f))
            override def apply(h_g: Morphism): Morphism = h_g match {
                case (h, g) => Function { (f: Morphism) =>
                    import _Set_.o
                    g o f o h
                }
            }
        }
    }
}
