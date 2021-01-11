package com.thinkami.tdd

class Money(
             // 自クラスと継承クラスで見えるようにする
             // Javaのprotectedと同じようにするため、パッケージの末尾を指定
             // http://www.ne.jp/asahi/hishidama/home/tech/scala/class.html#h_access_modifiers
             protected[tdd] val amount: Int,
             // メソッドとメンバ変数で同じ名前を使えないので、別の名前にしている
             protected[Money] val cur: String,
           ) extends Expression {

  override def equals(obj: Any): Boolean = {
    // Scalaのキャスト
    // https://qiita.com/cupper/items/9028a5a108deb8706717
    val money = obj.asInstanceOf[Money]
    (
      this.amount == money.amount
        // クラスが同じなのかを確認
        // https://qiita.com/mtoyoshi/items/6ebcce6e2abdaf41a05e
      && this.currency() == money.currency()
    )
  }

  // 抽象メソッドとして用意 (=~ がないため)
  // http://www.ne.jp/asahi/hishidama/home/tech/scala/class.html#h_abstract_member
  def times(multiplier: Int): Money = {
    new Money(amount * multiplier, this.cur)
  }

  def currency(): String = this.cur

  def plus(addend: Money): Expression = {
    new Sum(this, addend)
  }

  def reduce(to: String) = this
}

// Scalaには静的(static)メソッドがない
// そのため、コンパニオンオブジェクトを使ってファクトリーを用意する
// コンパニオンオブジェクト = クラスの共通の処理・操作
// https://www.ne.jp/asahi/hishidama/home/tech/scala/object.html#h_companion_object
object Money {
  def dollar(amount: Int): Money = {
    new Money(amount, "USD")
  }

  def franc(amount: Int): Money = {
    new Money(amount, "CHF")
  }
}
