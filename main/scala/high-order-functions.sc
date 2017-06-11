def cube(x: Int): Int = x * x * x


def sumCubes(a: Int, b: Int): Int =
  if (a > b) 0 else cube(a) + sumCubes(a + 1, b)


sumCubes(1, 2)

def sum(f: Int => Int, a: Int, b: Int): Int =
  if (a > b) 0
  else f(a) + sum(f, a + 1, b)


def sumCube(a: Int, b: Int) = sum(cube, a, b)

sumCube(1, 2)



// anonymous function
(x: Int) => x * x


// Exercise:
// Write a tail-recursive version of sum

def sumTR(f: Int => Int, a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, f(a) + acc)
  }
  loop(a, 0)
}


sumTR(x => x * x * x, 1, 2)

def setFunc(x: Int): Boolean =
  return false

