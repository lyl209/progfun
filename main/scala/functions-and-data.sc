/*
1. In your worksheet, add a method neg to class Rational that is
used like this:
x.neg // evaluates to -x
2. Add a method sub to subtract two rational numbers.
3. With the values of x, y, z as given in the previous slide, what is
the result of
x - y - z
?
*/

class Rational(x: Int, y: Int) {
  require(y != 0, "divide by zero error!")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  val numer = x / gcd(x, y)
  val denom = y / gcd(x, y)

  def neg: Rational = new Rational(-numer, denom)

  def add(r: Rational) =
    new Rational(numer * r.denom + r.numer * denom,
      denom * r.denom)

  def sub(r: Rational) = add(r.neg)

  def less(that: Rational) =
    this.numer * that.denom < that.numer * this.denom

  def max(that: Rational) =
    if (this.less(that)) that else this

  override def toString = numer + "/" + denom
}

val r = new Rational(1, 2)

r.numer
r.denom
r.neg

val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)

x.sub(y).sub(z)
y.add(y)
x.less(y)
x.max(y)

// java.lang.IllegalArgumentException: requirement failed: divide by zero error!
//val strange = new Rational(1, 0)
// java.lang.ArithmeticException: / by zero
//strange.add(strange)

new Rational(2)

/*
Modify the Rational class so that rational numbers are kept
unsimplified internally, but the simplification is applied when
numbers are converted to strings.
Do clients observe the same behavior when interacting with the
rational class?
Ans: yes for small sizes of denominators and nominators
and small numbers of operations.
*/


class RationalNS(x: Int, y: Int) {
  require(y != 0, "divide by zero error!")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  val numer = x
  val denom = y

  def neg: Rational = new Rational(-numer, denom)

  def add(r: Rational) =
    new Rational(numer * r.denom + r.numer * denom,
      denom * r.denom)

  def sub(r: Rational) = add(r.neg)

  def less(that: Rational) =
    this.numer * that.denom < that.numer * this.denom

  def max(that: Rational) =
    if (this.less(that)) that else this

  override def toString = {
    val g = gcd(numer, denom)
    numer / g + "/" + denom / g
  }
}


val x1 = new Rational(1, 3)
val y1 = new Rational(5, 7)
val z1 = new Rational(3, 2)

x1.sub(y1).sub(z1)
y1.add(y1)
x1.less(y1)
x1.max(y1)


type Set = Int => Boolean






