def abs(x: Double): Double = if (x < 0) -x else x

val tolerance = 0.0001

def isCloseEnough(x: Double, y: Double) =
  abs((x - y) / x) / x < tolerance

def fixedPoint(f: Double => Double)(firstGuess: Double) = {
  def iterate(guess: Double): Double = {
    //println("guess = " + guess)

    val next = f(guess)
    if (isCloseEnough(guess, next)) next
    else iterate(next)
  }
  iterate(firstGuess)
}


fixedPoint(x => 1 + x / 2)(1)

// IL = infinite loop
def sqrtIL(x: Double) = fixedPoint(y =>  x / y)(1.0)

//sqrtIL(2.0) // bouncing between 1 and 2, infinite loop


def sqrtAvg(x: Double) = fixedPoint(y => (y + x / y) / 2)(1.0)

sqrtAvg(2.0)

def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2

/*
Exercise
Write a square root function using fixedPoint and averageDamp.
*/

// Use averageDamp on the previously cannot converge function

def sqrt(x: Double) = fixedPoint(averageDamp(y => x/y))(1.0)

sqrt(2.0)




