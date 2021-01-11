package com.thinkami.tdd

class Sum(
         val augend: Money,
         val addend: Money,
         ) extends Expression {

  def reduce(bank: Bank, to: String) = {
    val amount = augend.amount + addend.amount
    new Money(amount, to)
  }
}
