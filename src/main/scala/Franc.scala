package com.thinkami.tdd

class Franc(
             override val amount: Int
           ) extends Money(amount) {

  def times(multiplier: Int): Money = {
    new Franc(this.amount * multiplier)
  }
}
