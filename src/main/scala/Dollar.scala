package com.thinkami.tdd


class Dollar(
              var amount: Int // timesで更新できるよう、読み取り専用のvalでなく、書き込みもできるvarを使う
            ) {
  def times(multiplier: Int): Dollar = {
    // Scalaなのでreturnを省略
    new Dollar(this.amount * multiplier)
  }

  override def equals(obj: Any): Boolean = {
    // Scalaのキャスト
    // https://qiita.com/cupper/items/9028a5a108deb8706717
    val dollar = obj.asInstanceOf[Dollar]
    this.amount == dollar.amount
  }
}
