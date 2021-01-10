package com.thinkami.tdd


class Dollar(
              var amount: Int // timesで更新できるよう、読み取り専用のvalでなく、書き込みもできるvarを使う
            ) {
  def times(multiplier: Int): Dollar = {
    // Scalaなのでreturnを省略
    new Dollar(this.amount * multiplier)
  }
}
