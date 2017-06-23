// Pure Bool
package idealized.scala

abstract class Bool {
  def ifThenElse[T](t: => T, e: => T): T
  // if (cond) te else ee => cond.ifThenElse(te, ee)
  // te = then expression; ee = else expression

  def && (x: => Bool): Bool = ifThenElse(x, False)
  // cond && x
  // if (cond) x else false
  // cond.ifThenElse(x, false)

  def || (x: => Bool): Bool = ifThenElse(True, x)
  // cond || x
  // if (cond) true else x
  // cond.ifThenElse(true, x)

  def unary_! : Bool = ifThenElse(False, True)
  // !cond
  // if (cond) false else true

  def == (x: Bool): Bool = ifThenElse(x, x.unary_!)
  // cond == x
  // if (cond) x else !x

  def != (x: Bool): Bool = ifThenElse(x.unary_!, x)
  // cond != x
  // if (cond) !x else x

  //...

  // Exercise
  def < (x: Bool): Bool = ifThenElse(False, x)
  // Assume false < true
  // cond < x
  // if (cond) false else x
}

object True extends Bool {
  def ifThenElse[T](t: => T, e: => T) = t
  // if (True) te else ee => te
}
object False extends Bool {
  def ifThenElse[T](t: => T, e: => T) = e
}


// Exercise
// Provide an implementation of the comparison operator < in class Bool
// Assume for this that
// false < true


abstract class Int {
  // overloading
  def + (that: Double): Double // 1 + 2.0
  def + (that: Float): Float
  def + (that: Long): Long
  def + (that: Int): Int // same for -, *, /, %

  def << (cnt: Int): Int // same for >>, >>> */

  def & (that: Long): Long
  def & (that: Int): Int // same for |, ^ */

  def == (that: Double): Bool
  def == (that: Float): Bool
  def == (that: Long): Bool // same for !=, <, >, <=, >=
  //  ...
}

// Can it be represented as a class from its first principles
// i.e. not using primitive ints?

// Exercise
// Provide an implementation of the abstract class Nat that represents
// non-negative integers

// Peano numbers
abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat = new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat
}

object Zero extends Nat {
  override def isZero = true

  override def predecessor: Nat = throw new Error("0.predecessor")

  override def +(that: Nat): Nat = that

  override def -(that: Nat): Nat =
    if (that.isZero) this else throw new Error("negative number")
}

class Succ(n: Nat) extends Nat {
  override def isZero = false

  override def predecessor: Nat = n

  override def +(that: Nat): Nat = new Succ(n + that)

  override def -(that: Nat): Nat = //new Succ(n - that)
    if (that.isZero) this else n - that.predecessor
}
