

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok.ooc


final case class _Product_(_1: Category, _2: Category) extends CategoryImpl {
    override protected type _Object = (Object, Object)
    override protected type _Morphism = (Morphism, Morphism)

    override protected val _ob: Set = Set.any[_Object]
    override protected val _mor: Set = Set.any[_Morphism]

    override protected def _dom(f: _Morphism): _Object = (_1.dom(f._1), _2.dom(f._2))
    override protected def _cod(f: _Morphism): _Object = (_1.cod(f._1), _2.cod(f._2))

    override protected def _id(a: _Object): _Morphism = (_1.id(a._1), _2.id(a._2))
    override protected def _compose(g: _Morphism, f: _Morphism): _Morphism = (_1.compose(g._1, f._1), _2.compose(g._2, f._2))
}
