package com.thinkami.tdd

abstract class Money(
             // 自クラスと継承クラスで見えるようにする
             protected[Money] val amount: Int
           ) {
  override def equals(obj: Any): Boolean = {
    // Scalaのキャスト
    // https://qiita.com/cupper/items/9028a5a108deb8706717
    val money = obj.asInstanceOf[Money]
    (
      this.amount == money.amount
        // クラスが同じなのかを確認
        // https://qiita.com/mtoyoshi/items/6ebcce6e2abdaf41a05e
      && getClass == obj.getClass
    )
  }

  // 抽象メソッドとして用意 (=~ がないため)
  // http://www.ne.jp/asahi/hishidama/home/tech/scala/class.html#h_abstract_member
  def times(multiplier: Int): Money
}

// Scalaには静的(static)メソッドがない
// そのため、コンパニオンオブジェクトを使ってファクトリーを用意する
// コンパニオンオブジェクト = クラスの共通の処理・操作
// https://www.ne.jp/asahi/hishidama/home/tech/scala/object.html#h_companion_object
object Money {
  def dollar(amount: Int): Dollar = {
    new Dollar(amount)
  }

  def franc(amount: Int): Franc = {
    new Franc(amount)
  }
}
