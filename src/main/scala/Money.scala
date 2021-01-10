package com.thinkami.tdd

class Money(
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
}
