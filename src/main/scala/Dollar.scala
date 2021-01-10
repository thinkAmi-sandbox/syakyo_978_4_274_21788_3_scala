package com.thinkami.tdd


class Dollar(
              // timesで更新できるよう、読み取り専用のvalでなく、書き込みもできるvarを使う
              // timesでの更新が不要になったので、varからvalへと切り替える
              // さらに、val/varをつけなくても、暗黙的にprivateなインスタンス変数として作成される
              // http://www.ne.jp/asahi/hishidama/home/tech/scala/class.html#h_member
              // ただ、equals()で比較するときにprivateフィールドだとアクセスできない
              // (Javaの場合、同一クラスであれば、別インスタンスのprivateにアクセスできる模様)
              // そのため、アクセス修飾子のprivate[T]を使用して、同一クラスならアクセスできるようにする
              // http://www.ne.jp/asahi/hishidama/home/tech/scala/class.html#h_access_modifiers
              private[Dollar] val amount: Int
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
