def abs(x: Double): Double = if (x < 0) -x else x

def improve(guess: Double, x: Double) =
  (guess + x / guess) / 2
def isGoodEnough(guess: Double, x: Double) =
  abs(guess * guess - x) < 0.001

def sqrtIter(guess: Double, x: Double): Double =
  if (isGoodEnough(guess, x)) guess
  else sqrtIter(improve(guess, x), x)


def sqrt(x: Double) = sqrtIter(1.0, x)

/*
1. The isGoodEnough test is not very precise for small numbers and
can lead to non-termination for very large numbers. Explain why.
2. Design a different version of isGoodEnough that does not have
these problems.
3. Test your version with some very very small and large numbers,
e.g.
 */



