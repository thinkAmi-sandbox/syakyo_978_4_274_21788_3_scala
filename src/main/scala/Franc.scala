package com.thinkami.tdd

class Franc(
             private[Franc] val amount: Int
           ) {

  def times(multiplier: Int): Franc = {
    new Franc(this.amount * multiplier)
  }

  override def equals(obj: Any): Boolean = {
    val franc = obj.asInstanceOf[Franc]
    this.amount == franc.amount
  }
}
