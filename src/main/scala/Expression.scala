package com.thinkami.tdd

trait Expression {
  def plus(addend: Expression): Expression

  def reduce(bank: Bank, to: String): Money
}
