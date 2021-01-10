package com.thinkami.tdd

class Franc(
             override val amount: Int,
             override val cur: String
           ) extends Money(amount, cur) {

  def times(multiplier: Int): Money = {
    Money.franc(this.amount * multiplier)
  }
}
