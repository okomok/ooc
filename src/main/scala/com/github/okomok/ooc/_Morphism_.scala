

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final class _Mor_(C: Category) extends CategoryImpl {
    override protected type _Object = Morphism
    override protected type _Morphism = Square

    override protected val _ob: Set = C.mor
    override protected val _mor: Set = Set.any[Square]

    override protected def _dom(f: _Morphism): _Object = f.left
    override protected def _cod(f: _Morphism): _Object = f.right

    override protected def _id(a: _Object): _Morphism = Square(a, C.id(C.dom(a)), a, C.id(C.cod(a)))
    override protected def _compose(g: _Morphism, f: _Morphism): _Morphism = Square(f.left, C.compose(g.top, f.top), g.right, C.compose(g.bottom, f.bottom))
}
