package com.thinkami.tdd

class Bank {
  def reduce(source: Expression, to: String): Money = {
    source.reduce(to)
  }
}
