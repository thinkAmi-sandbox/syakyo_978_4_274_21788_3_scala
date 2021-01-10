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
              //
              // Moneyの子クラスにするため、override val で基本コンストラクタ(primary constructor)も定義
              // [Q]この方法以外で、Scalaっぽい書き方はできるのか？
              // https://stackoverflow.com/questions/6497059/scala-inherit-parameterized-constructor
              override val amount: Int
            ) extends Money(amount) {
  def times(multiplier: Int): Money = {
    // Scalaなのでreturnを省略
    new Dollar(this.amount * multiplier)
  }
}
