

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class _Morphism_(_1: Category) extends CategoryImpl {
    override protected type _Object = Morphism
    override protected type _Morphism = Square

    override protected val _ob: Set = _1.mor
    override protected val _mor: Set = Set.any[Square]

    override protected def _dom(f: _Morphism): _Object = f.left
    override protected def _cod(f: _Morphism): _Object = f.right

    override protected def _id(a: _Object): _Morphism = Square(a, _1.id(_1.dom(a)), a, _1.id(_1.cod(a)))
    override protected def _compose(g: _Morphism, f: _Morphism): _Morphism = Square(f.left, _1.compose(g.top, f.top), g.right, _1.compose(g.bottom, f.bottom))
}
