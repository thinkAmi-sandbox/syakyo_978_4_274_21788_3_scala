package com.thinkami.tdd


class Bank {
  // Mapに追加したりするので、パッケージ名を明示的に書く
  val rates: scala.collection.mutable.Map[Pair, Int] = scala.collection.mutable.Map.empty[Pair, Int]

  def reduce(source: Expression, to: String): Money = {
    source.reduce(this, to)
  }

  def addRate(from: String, to: String, rate: Int) = {
    this.rates.update(new Pair(from, to), rate)
  }

  def rate(from: String, to: String): Int = {
    if (from.equals(to)) {
      return 1
    }

    // Scalaの場合、get()だとOption[Int]が返るため、他の部分も修正が必要になる
    // そこで、Mapに存在しない場合も1を返す
    rates.getOrElse(new Pair(from, to), 1)
  }
}
