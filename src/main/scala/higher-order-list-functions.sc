// Recurring Patterns for computations on Lists

// 1. transforming each element in a list in a certain way
// -> map
// 2. retrieving a list of all elements satisfying a criterion
// -> filter
// 3. combining the elements of a list using an operator
// ->

def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
  case Nil => xs
  case y :: ys => y * factor :: scaleList(ys, factor)
}

/*
abstract class List[T] {
...
  def map[U](f: T => U): List[U] = this match {
    case Nil => this
    case x :: xs => f(x) :: xs.map(f)
  }
}
*/

// Rewrite scaleList
def scaleLst(xs: List[Double], factor: Double) =
  xs map (x => x * factor)

// Exercise
/*
def squareList(xs: List[Int]): List[Int] = xs match {
  case Nil => Nil
  case y :: ys => y * y :: squareList(ys)
}
*/

def squareList(xs: List[Int]): List[Int] =
  xs map (x => x * x)


// Filtering

// positive elementss
def posElems(xs: List[Int]): List[Int] = xs match {
  case Nil => xs
  case y :: ys => if (y > 0) y :: posElems(ys) else posElems(ys)
}

/*
abstract class List[T] {
...
  def filter(p: T => Boolean): List[T] = this match {
    case Nil => this
    case x :: xs => if (p(x)) x :: xs.filter(p) else xs.filter(p)
  }
}
*/

def posElem(xs: List[Int]): List[Int] =
  xs filter (x => x > 0)

val nums = List(2, -4, 5, 7, 1)
nums filter (x => x > 0) //res0: List[Int] = List(2, 5, 7, 1)
nums filterNot (x => x > 0) //res1: List[Int] = List(-4)

nums partition (x => x > 0)//res2: (List[Int], List[Int]) = (List(2, 5, 7, 1),List(-4))

nums takeWhile (x => x > 0)//res3: List[Int] = List(2)
nums dropWhile (x => x > 0)//res4: List[Int] = List(-4, 5, 7, 1)

nums span (x => x > 0)//res5: (List[Int], List[Int]) = (List(2),List(-4, 5, 7, 1))


/*
xs filterNot p

xs partition p
// return a pair, same as (xs filter p , xs filterNot p)

xs takeWhile p
// longest prefix of list xs while p is true

xs dropWhile p
// remainder of the list xs after any leading elements
// satisfying p have been removed

xs span p
// same as (xs takeWhile p, xs dropWhile p)

*/

// Exercise 1
// pack(List("a", "a", "a", "b", "c", "c", "a"))
// should give
// List(List("a", "a", "a"), List("b"), List("c", "c"), List("a")).

val data = List("a", "a", "a", "b", "c", "c", "a")

def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    val (first, rest) = xs span (y => y == x)
    first :: pack(rest)
}

pack(data)

// Exercise 2
// encode(List("a", "a", "a", "b", "c", "c", "a"))
// should give
// List(("a", 3), ("b", 1), ("c", 2), ("a", 1)).

def encode[T](xs: List[T]): List[(T, Int)] = {
  //val packed = pack(xs)
  //packed.map(x => (x.head, x.size))

  pack(xs) map (ys => (ys.head, ys.size))
}

encode(data)

