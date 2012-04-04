

// Copyright Shunsuke Sogame 2012.
// Distributed under the New BSD license.


package com.github.okomok


package object ooc {
    type Object = Any
    type Morphism = Any

    implicit def as(x: Any): Op_as = new Op_as(x)
}
