

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class MorMor(left: Morphism, top: Morphism, right: Morphism, bottom: Morphism)


final class _Mor_(C: Category) extends CategoryImpl {
    override protected type _Object = Morphism
    override protected type _Morphism = MorMor

    override val _ob: Set = C.mor
    override val _mor: Set = Set.any[MorMor]

    override protected def _dom(f: _Morphism): _Object = f.left
    override protected def _cod(f: _Morphism): _Object = f.right

    override protected def _id(a: _Object): _Morphism = MorMor(a, C.id(C.dom(a)), a, C.id(C.cod(a)))
    override protected def _compose(g: _Morphism, f: _Morphism): _Morphism = MorMor(f.left, C.compose(g.top, f.top), g.right, C.compose(g.bottom, f.bottom))
}
