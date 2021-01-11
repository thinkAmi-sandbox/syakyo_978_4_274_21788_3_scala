package com.thinkami.tdd

class Sum(
         val augend: Money,
         val addend: Money,
         ) extends Expression {

  def reduce(to: String) = {
    val amount = augend.amount + addend.amount
    new Money(amount, to)
  }
}
