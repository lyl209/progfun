// def sumCubes(a: Int, b: Int) = sum(cube, a, b)
// Note that a and b get passed unchanged from sumInts and sumCubes into sum.

// LF = long form
def sumLF(f: Int => Int): (Int, Int) => Int = {
  def sumF(a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sumF(a + 1, b)
  sumF
}

def sumCubes = sumLF(x => x * x * x)

sumCubes(1, 2)

sumLF(x => x * x * x)(1, 2)

sumLF(x => x)


// Prettier
def sum(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 0 else f(a) + sum(f)(a + 1, b)


// What is the type of sum?
// (Int => Int) => (Int, Int) => Int


/*
Exercise
1. Write a product function that calculates the product of the
values of a function for the points on a given interval.
2. Write factorial in terms of product.
3. Can you write a more general function, which generalizes both
sum and product?
*/

// RC = recursive
def productRC(f: Int => Int)(a: Int, b:Int): Int =
  if (a > b) 1
  else f(a) * productRC(f)(a + 1, b)

productRC(x => x * x)(3, 4)

def factorial(n: Int) = productRC(x => x)(1, n)

factorial(5)

def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b:Int): Int = {
  if (a > b) return zero
  else combine(f(a), mapReduce(f, combine, zero) (a + 1, b))
}

// define product function using map reduce

def product(f: Int => Int)(a: Int, b:Int): Int =
  mapReduce(f, (x, y) => x * y, 1)(a, b)

product(x => x * x)(3, 4)


