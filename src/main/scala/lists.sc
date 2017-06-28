val fruit: List[String] = List("apples", "oranges", "pears")
val nums: List[Int] = List(1, 2, 3, 4)
val diag3: List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
val empty: List[Nothing] = List()


// Lists are immutable
// Lists are recursive, while arrays are flat
// Lists are homogeneous

// Constructors of Lists
// All lists are constructed from
// 1. the empty list Nil
// 2. :: (pronounced cons)
// x :: xs gives a new list with first element x,
// followed by elements xs


// fruit = "apples" :: ("ranges" :: ("pears" :: Nil))
// nums = 1 :: (2 :: (3 :: (4 :: Nil)))
// empty = Nil

// Right Associativity
// A :: B :: C  = A :: (B :: C)

// 1 :: 2 :: 3 :: 4 :: Nil
// The expression above is equivalent to
// Nil.::(4).::(3).::(2).::(1)

// List Patterns

// Nil
// p :: ps
// 1 :: 2 :: xs
// x :: Nil
// List(x)
// List() empty list same as Nil
// List(2 :: xs)

// Exercise
// Consider pattern
// x :: y :: List(xs, ys) :: zs
// What is the condition that describes most accurately the length L
// of the list it matches?

// Answer
// L >= 3 because zs could be Nil


// Insertion Sort
def isort(xs: List[Int]): List[Int] = xs match {
  case List() => List()
  case y :: ys => insert(y, isort(ys))
}

// O(N^2)
def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List() => List(x)
  case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
}




