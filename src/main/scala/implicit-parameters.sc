// Making sort more general
// def msort[T](xs: List[T]): List[T] = ???
// does not work, because the comparison < in merge is not defined for
// arbitrary types T.

// Idea: Parameterize merge with the necessary comparison function.

def msort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
  val n = xs.length/2
  if (n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

    val (fst, snd) = xs splitAt n
    merge(msort(fst)(lt), msort(snd)(lt))
  }
}

val nums = List(2, -4, 5, 7, 1)
msort(nums)((x: Int, y: Int) => x < y)
// Simplify
msort(nums)((x, y) => x < y)

val fruites = List("apple", "pineapple", "orange", "banana")
msort(fruites)((x: String, y: String) => x.compareTo(y) < 0)

// Parametrization with Ordered
// There is already a class in the standard library that represents
// orderings.
// scala.math.Ordering[T]

/*
def msort[T](xs: List[T])(ord: Ordering) =
def merge(xs: List[T], ys: List[T]) =
... if (ord.lt(x, y)) ...
... merge(msort(fst)(ord), msort(snd)(ord)) ...


import math.Ordering
msort(nums)(Ordering.Int)
msort(fruits)(Ordering.String)
*/

// Problem: Passing around lt or ord values is cumbersome.
// We can avoid this by making ord an implicit parameter.

/*
def msort[T](xs: List[T])(implicit ord: Ordering) =
def merge(xs: List[T], ys: List[T]) =
... if (ord.lt(x, y)) ...
... merge(msort(fst), msort(snd)) ...
*/

// Then calls to msort can avoid the ordering parameters:
// msort(nums)
// msort(fruits)
// The compiler will figure out the right implicit to pass based on the
// demanded type.

/*
Rules for Implicit Parameters
Say, a function takes an implicit parameter of type T.
The compiler will search an implicit definition that
  ▶ is marked implicit
  ▶ has a type compatible with T
  ▶ is visible at the point of the function call, or is defined in a
    companion object associated with T.

If there is a single (most specific) definition, it will be taken as
actual argument for the implicit parameter.
Otherwise it’s an error.
*/

// Exercise

/*
Consider the following line of the definition of msort:
... merge(msort(fst), msort(snd)) ...

Which implicit argument is inserted?
1. Ordering.Int
2. Ordering.String
v 3. the ”ord” parameter of ”msort”
*/