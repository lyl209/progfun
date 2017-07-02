def msort(xs: List[Int]): List[Int] = {
  val n = xs.length/2
  if (n == 0) xs
  else {
    def merge(xs: List[Int], ys: List[Int]): List[Int] =
      xs match {
        case Nil =>
          ys
        case x :: xs1 =>
          ys match {
            case Nil =>
              xs
            case y :: ys1 =>
              if (x < y) x :: merge(xs1, ys)
              else y :: merge(xs, ys1)
          }
      }
    val (fst, snd) = xs splitAt n
    merge(msort(fst), msort(snd))
  }
}


// splitAt

// Pair and Tuples

// Tuplen class
//https://github.com/scala/scala/blob/v2.11.8/src/library/scala/Tuple2.scala
//Plus means co-variant, minus means contra-variant, otherwise it's non-variant.

// For a declaration class M[+T](...) {...}, it means following subtyping rule is valid:
// S <: T
// -------
// M[S] <: M[T]

// In contrast, class M[-T](...) {...}, following subtyping rule holds:
// S <: T
// -------
// M[T] <: M[S]

// For class M[T](...) {...}, following subtyping rule holds:
// S = T
// -------
// M[S] <: M[T]


case class Tuple2[+T1, +T2](_1: T1, _2: T2) {
  override def toString = "(" + _1 + "," + _2 + ")"
}


// Rewrite merge using a pattern matching over pairs

def merge(xs: List[Int], ys: List[Int]): List[Int] =
  (xs, ys) match {
    case (Nil, ys) => ys
    case (xs, Nil) => xs
    case (x :: xs1, y :: ys1) =>
      if (x < y) x :: merge(xs1, ys)
      else y :: merge(xs, ys1)
  }

val nums = List(2, -4, 5, 7, 1)
msort(nums)

