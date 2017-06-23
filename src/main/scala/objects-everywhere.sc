// Pure Boolean
package idealized.scala

abstract class Boolean {
  def ifThenElse[T](t: => T, e: => T): T
  // if (cond) te else ee => cond.ifThenElse(te, ee)
  // te = then expression; ee = else expression

  def && (x: => Boolean): Boolean = ifThenElse(x, False)
  // cond && x
  // if (cond) x else false
  // cond.ifThenElse(x, false)

  def || (x: => Boolean): Boolean = ifThenElse(True, x)
  // cond || x
  // if (cond) true else x
  // cond.ifThenElse(true, x)

  def unary_! : Boolean = ifThenElse(False, True)
  // !cond
  // if (cond) false else true

  def == (x: Boolean): Boolean = ifThenElse(x, x.unary_!)
  // cond == x
  // if (cond) x else !x

  def != (x: Boolean): Boolean = ifThenElse(x.unary_!, x)
  // cond != x
  // if (cond) !x else x

  //...

  // Exercise
  def < (x: Boolean): Boolean = ifThenElse(False, x)
  // Assume false < true
  // cond < x
  // if (cond) false else x
}

object True extends Boolean {
  def ifThenElse[T](t: => T, e: => T) = t
  // if (True) te else ee => te
}
object False extends Boolean {
  def ifThenElse[T](t: => T, e: => T) = e
}


// Exercise
// Provide an implementation of the comparison operator < in class Boolean
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

  def == (that: Double): Boolean
  def == (that: Float): Boolean
  def == (that: Long): Boolean // same for !=, <, >, <=, >=
  //  ...
}

// Can it be represented as a class from its first principles
// i.e. not using primitive ints?

// Exercise
// Provide an implementation of the abstract class Nat that represents
// non-negative integers

abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat
  def + (that: Nat): Nat
  def - (that: Nat): Nat
}

object Zero extends Nat {
  override def isZero: Boolean = ???

  override def predecessor: Nat = ???

  override def successor: Nat = ???

  override def +(that: Nat): Nat = ???

  override def -(that: Nat): Nat = ???
}

class Succ(n: Nat) extends Nat {
  override def isZero: Boolean = ???

  override def predecessor: Nat = ???

  override def successor: Nat = ???

  override def +(that: Nat): Nat = ???

  override def -(that: Nat): Nat = ???
}
