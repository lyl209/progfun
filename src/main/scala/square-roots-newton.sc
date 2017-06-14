def abs(x: Double): Double = if (x < 0) -x else x

def improve(guess: Double, x: Double) =
  (guess + x / guess) / 2
def isGoodEnough(guess: Double, x: Double) =
  abs(guess * guess - x) < 0.001

def sqrtIter(guess: Double, x: Double): Double =
  if (isGoodEnough(guess, x)) guess
  else sqrtIter(improve(guess, x), x)


def sqrt(x: Double) = sqrtIter(1.0, x)

// 

def abs(x:Double) = if (x < 0) -x else x

abs(-6)

def sqrt(x: Double) = {
  def improve(guess: Double, x: Double) = (guess + x / guess) / 2

  def isGoodEnough(guess: Double, x: Double) = abs((guess * guess - x) / x) < 0.01

  def sqrtIter(guess: Double, x: Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)

  sqrtIter(1.0, x)
}

sqrt(8)


def sum(f: Int => Int, a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, f(a) + acc)
  }

  loop(a, 0)
}

sum(x => x, 1, 5)


def product(f: Int => Int)(a: Int, b: Int): Int = {
  if (a > b) 1
  else f(a) * product(f)(a + 1, b)
}

product(x => x * x)(3, 4)

def fact(n: Int) = product(x => x)(1, n)

fact(5)
fact(7)

/*
1. The isGoodEnough test is not very precise for small numbers and
can lead to non-termination for very large numbers. Explain why.
2. Design a different version of isGoodEnough that does not have
these problems.
3. Test your version with some very very small and large numbers,
e.g.
 */



