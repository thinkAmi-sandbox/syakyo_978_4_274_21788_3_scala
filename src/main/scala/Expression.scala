package com.thinkami.tdd

trait Expression {
  def reduce(bank: Bank, to: String): Money
}
