package com.thinkami.tdd

// 公式ドキュメントだとimportを認識しなかったので、以下の2つの方法を元に対応(importを替えて、ideaディレクトリを消してload)
// https://dev.classmethod.jp/articles/write-unittest-by-scalatest/
// https://stackoverflow.com/questions/31416656/cannot-resolve-symbol-scalatest
import org.scalatest.funsuite.AnyFunSuite

class MoneyTest extends AnyFunSuite {
  test("Multiplication") {
    // Dollarは同じパッケージなのでimportしなくても使える
    val five = Money.dollar(5)

    // `===` はScalaにないため、使用しているものによって定義されている
    // https://stackoverflow.com/questions/39490236/difference-between-and-in-scala-spark
    // ScalaTestの場合、 `===` を使うほうが、エラーになったときに詳しい情報が出てくる
    // https://www.scalatest.org/getting_started_with_fun_suite
    // assert(1 == 2)
    // => [info]   org.scalatest.exceptions.TestFailedException was thrown. (MoneyTest.scala:28)
    // assert(1 === 2)
    // =>  [info]   1 did not equal 2 (MoneyTest.scala:33)

    assert(Money.dollar((10)) === five.times(2))

    assert(Money.dollar(15) === five.times(3))
  }

  test("Equality") {
    // Scalaの場合、equals()を実装していないクラスは、JavaのObject.equals()を呼び出すので、コンパイルエラーにはならない
    // https://qiita.com/hysdsk/items/b37c4cf5ee21bbf1c494
    assert(Money.dollar(5) === (Money.dollar(5)))

    // 三角測量用のコード
    assert(Money.dollar(5) !== (Money.dollar(6)))

    assert(Money.franc(5) !== Money.dollar(5))
  }

  test("Currency") {
    assert(Money.dollar(1).currency() === "USD")
    assert(Money.franc(1).currency() === "CHF")
  }

  test("SimpleAddition") {
    val five = Money.dollar(5)
    val sum = five.plus(five)

    val bank = new Bank()
    val reduced = bank.reduce(sum, "USD")
    assert(Money.dollar(10) === reduced)
  }

  test("PlustReturnsSum") {
    val five = Money.dollar(5)
    val result = five.plus(five)
    val sum = result.asInstanceOf[Sum]

    assert(five === sum.augend)  // 被加算数(augend)
    assert(five === sum.addend)
  }

  test("ReduceSum") {
    val sum = new Sum(Money.dollar(3), Money.dollar(4))
    val bank = new Bank()
    val result = bank.reduce(sum, "USD")
    assert(Money.dollar(7) === result)
  }

  test("ReduceMoney") {
    val bank = new Bank()
    val result = bank.reduce(Money.dollar(1), "USD")
    assert(Money.dollar(1) === result)
  }

  test("ReduceMoneyDifferentCurrency") {
    val bank = new Bank()
    bank.addRate("CHF", "USD", 2)
    val result = bank.reduce(Money.franc(2), "USD")
    assert(result === Money.dollar(1))
  }

  test("IdentityRate") {
    assert(new Bank().rate("USD", "USD") === 1)
  }

  test("MixedAddition") {
    val fiveBucks = Money.dollar(5)
    val tenFrancs = Money.franc(10)

    val bank = new Bank()
    bank.addRate("CHF", "USD", 2)
    val result = bank.reduce(fiveBucks.plus(tenFrancs), "USD")

    assert(result === Money.dollar(10))
  }

  test("SumPlusMoney") {
    val fiveBucks = Money.dollar(5)
    val tenFrancs = Money.franc(10)

    val bank = new Bank()
    bank.addRate("CHF", "USD", 2)
    val sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks)
    val result = bank.reduce(sum, "USD")

    assert(result === Money.dollar(15))
  }

  test("SumTimes") {
    val fiveBucks = Money.dollar(5)
    val tenFrancs = Money.franc(10)

    val bank = new Bank()
    bank.addRate("CHF", "USD", 2)
    val sum = new Sum(fiveBucks, tenFrancs).times(2)
    val result = bank.reduce(sum, "USD")

    assert(result === Money.dollar(20))
  }
}
