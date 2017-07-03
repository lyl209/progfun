
// sum(List(x1, ..., xn)) = 0 + x1 + ... + xn
// product(List(x1, ..., xn)) = 1 * x1 * ... * xn

/*
def sum(xs: List[Int]): Int = xs match {
  case Nil => 0
  case y :: ys => y + sum(ys)
}
*/

// ReduceLeft
// List(x1, ..., xn) reduceLeft op = (...(x1 op x2) op ... ) op xn
/*
def sum(xs: List[Int]) = (0 :: xs) reduceLeft ((x, y) => x + y)
def product(xs: List[Int]) = (1 :: xs) reduceLeft ((x, y) => x * y)
*/

// Even shorter
// Every _ represents a new parameter, going from left to right
/*
def sum(xs: List[Int]) = (0 :: xs) reduceLeft (_ + _)
def product(xs: List[Int]) = (1 :: xs) reduceLeft (_ * _)
*/

// FoldLeft
// foldLeft is like reduceLEft but takes an accumulator z,
// as an additional parameter
// (List(x1, ..., xn) foldLeft z)(op) = (...(z op x1) op ... ) op xn

def sum(xs: List[Int]) = (xs foldLeft 0) (_ + _)
def product(xs: List[Int]) = (xs foldLeft 1) (_ * _)

/*
abstract class List[T] {
  ...
  def reduceLeft(op: (T, T) => T): T = this match {
    case Nil => throw new Error("Nil.reduceLeft")
    case x :: xs => (xs foldLeft x)(op)
  }
  def foldLeft[U](z: U)(op: (U, T) => U): U = this match {
    case Nil => z
    case x :: xs => (xs foldLeft op(z, x))(op)
  }
}
*/

// FoldRight and ReduceRight
// List(x1, ..., x{n-1}, xn) reduceRight op = x1 op ( ... (x{n-1} op xn) ... )
// (List(x1, ..., xn) foldRight acc)(op) = x1 op ( ... (xn op acc) ... )

/*
def reduceRight(op: (T, T) => T): T = this match {
  case Nil => throw new Error("Nil.reduceRight")
  case x :: Nil => x
  case x :: xs => op(x, xs.reduceRight(op))
}

def foldRight[U](z: U)(op: (T, U) => U): U = this match {
  case Nil => z
  case x :: xs => op(x, (xs foldRight z)(op))
}
*/


val data = List("a", "a", "a", "b", "c", "c", "a")

def concat[T](xs: List[T], ys: List[T]): List[T] =
  (xs foldRight ys) (_ :: _)

/*
def concat[T](xs: List[T], ys: List[T]): List[T] =
  (xs foldLeft ys) (_ :: _)
*/

/* Exercise
def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldRight List[U]())( ??? )

def lengthFun[T](xs: List[T]): Int =
  (xs foldRight 0)( ??? )
*/


def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldRight List[U]()) (f(_) :: _)

def lengthFun[T](xs: List[T]): Int =
  (xs foldRight 0)( (x, y) => 1 + y)


