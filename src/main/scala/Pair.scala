package com.thinkami.tdd

class Pair(val from: String, val to: String) {
  override def equals(obj: Any): Boolean = {
    val pair = obj.asInstanceOf[Pair]
    from.equals(pair.from) && to.equals(pair.to)
  }

  override def hashCode(): Int = 0
}
