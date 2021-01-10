package com.thinkami.tdd

// 公式ドキュメントだとimportを認識しなかったので、以下の2つの方法を元に対応(importを替えて、ideaディレクトリを消してload)
// https://dev.classmethod.jp/articles/write-unittest-by-scalatest/
// https://stackoverflow.com/questions/31416656/cannot-resolve-symbol-scalatest
import org.scalatest.funsuite.AnyFunSuite

class MoneyTest extends AnyFunSuite {
  test("掛け算") {
    // Dollarは同じパッケージなのでimportしなくても使える
    val five = new Dollar(5)

    // 再代入できるように、varを使う
    var product = five.times(2)

    // `===` はScalaにないため、使用しているものによって定義されている
    // https://stackoverflow.com/questions/39490236/difference-between-and-in-scala-spark
    assert(product.amount === 10)

    product = five.times(3)
    assert(product.amount === 15)
  }
}
