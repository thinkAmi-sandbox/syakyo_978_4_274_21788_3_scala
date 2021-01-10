package com.thinkami.tdd

// 公式ドキュメントだとimportを認識しなかったので、以下の2つの方法を元に対応(importを替えて、ideaディレクトリを消してload)
// https://dev.classmethod.jp/articles/write-unittest-by-scalatest/
// https://stackoverflow.com/questions/31416656/cannot-resolve-symbol-scalatest
import org.scalatest.funsuite.AnyFunSuite

class MoneyTest extends AnyFunSuite {
  test("掛け算") {
    // Dollarは同じパッケージなのでimportしなくても使える
    val five = new Dollar(5)

    // `===` はScalaにないため、使用しているものによって定義されている
    // https://stackoverflow.com/questions/39490236/difference-between-and-in-scala-spark
    // ScalaTestの場合、 `===` を使うほうが、エラーになったときに詳しい情報が出てくる
    // https://www.scalatest.org/getting_started_with_fun_suite
    // assert(1 == 2)
    // => [info]   org.scalatest.exceptions.TestFailedException was thrown. (MoneyTest.scala:28)
    // assert(1 === 2)
    // =>  [info]   1 did not equal 2 (MoneyTest.scala:33)

    assert(new Dollar(10) === five.times(2))

    assert(new Dollar(15) === five.times(3))
  }

  test("等価性") {
    // Scalaの場合、equals()を実装していないクラスは、JavaのObject.equals()を呼び出すので、コンパイルエラーにはならない
    // https://qiita.com/hysdsk/items/b37c4cf5ee21bbf1c494
    assert(new Dollar(5) === (new Dollar(5)))

    // 三角測量用のコード
    assert(new Dollar(5) !== (new Dollar(6)))
  }

  test("Francのテスト") {
    val five = new Franc(5)
    assert(new Franc(10) === five.times(2))
    assert(new Franc(15) === five.times(3))
  }
}
