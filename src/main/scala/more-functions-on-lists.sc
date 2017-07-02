def last[T](xs: List[T]): T = xs match {
  case List() => throw new Error("last of empty list")
  case List(x) => x
  case y :: ys => last(ys)
}


def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("init of empty list")
  case List(x) => List()
  case y :: ys => y :: init(ys)
}

val l1 = List(1, 2, 3)
val l2 = List(5) ++ l1

val l3 = l1 :: l2
val l4 = l1 ::: l2



def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case List() => ys
  case z :: zs => z :: concat(zs, ys)
}


def reverse[T](xs: List[T]): List[T] = xs match {
  case List() => List()
  case y :: ys => reverse(ys) ++  List(y)

}

// :: prepends a single item whereas ::: prepends a complete list
// if you put a List in front of :: it is taken as one item,
// which results in a nested structure.

// Exercise
// Remove the n'th element of a list xs.
// If n is out of bounds, return xs itself
// removeAt(1, List('a', 'b', 'c', 'd')) // List(a, c, d)

def removeAt[T](n: Int, xs: List[T]) =
  (xs take n) ::: (xs drop n + 1)

