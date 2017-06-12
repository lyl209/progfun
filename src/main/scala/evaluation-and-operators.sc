class Rational(x: Int, y: Int) {
  require(y != 0, "divide by zero error!")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  val numer = x / gcd(x, y)
  val denom = y / gcd(x, y)

  def unary_- : Rational = new Rational(-numer, denom)

  def + (r: Rational) =
    new Rational(numer * r.denom + r.numer * denom,
      denom * r.denom)

  def - (that: Rational) = this + -that

  def < (that: Rational) =
    this.numer * that.denom < that.numer * this.denom

  def max(that: Rational) =
    if (this < that) that else this

  override def toString = numer + "/" + denom
}


val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)

x - y - z
y + y
x < y
x max y


// a + b ^? c ?^ d less a ==> b | c

// ((a + b) ^? (c ?^ d)) less ((a ==> b) | c)

