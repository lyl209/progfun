/*

Collection Hierarchy

   Iterable
  /   |    \
Seq   Set   Map

*/

// Sets for n-queens problem


val fruit = Set("apple", "banana", "pear")
val s = (1 to 6).toSet

s map (_ + 2)
fruit filter (_.startsWith("app"))
s.nonEmpty

// Eliminate duplicate elements
s map (_ / 2)

// The fundamental operation on sets
s contains 5


// Example: N-Queens
// The eight queens problem is to place eight queens on a chessboard
//  so that no queen is threatened by another.
// ▶ In other words, there can’t be two queens in the same row,
//    column, or diagonal.

// Algorithm
/*
We can solve this problem with a recursive algorithm:

▶ Suppose that we have already generated all the solutions
  consisting of placing k-1 queens on a board of size n.
▶ Each solution is represented by a list (of length k-1) containing
  the numbers of columns (between 0 and n-1).
▶ The column number of the queen in the k-1th row comes first
  in the list, followed by the column number of the queen in row
  k-2, etc.
▶ The solution set is thus represented as a set of lists, with one
  element for each solution.
▶ Now, to place the kth queen, we generate all possible
  extensions of each solution preceded by a new queen:
*/

// If we have queens/solution = List(0, 3, 1)
// we want List((2, 0), (1, 3), (0, 1)) to indicate
// row, col
def isSafe(col: Int, queens: List[Int]): Boolean = {
 // !(queens contains col)
  val row = queens.length
  val queensWithRow = (row - 1 to 0 by -1) zip queens
  queensWithRow forall {
    // diagonal check
    case (r, c) => col != c && math.abs(col - c) != row - r
  }
}

def queens(n: Int) = {
  def placeQueens(k: Int): Set[List[Int]] = {
    if (k == 0) Set(List())
    else
      for {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if isSafe(col, queens)
      } yield col :: queens
  }
  placeQueens(n)
}

queens(4)
//Set[List[Int]] = Set(List(2, 0, 3, 1), List(1, 3, 0, 2))

def show(queens: List[Int]) = {
  val lines =
    for (col <- queens.reverse)
      yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
  "\n" + (lines mkString "\n")
}

queens(4) map show

(queens(5) map show) mkString "\n"

(queens(8) take 3 map show) mkString "\n"

/*
scala.collection.immutable.Set[String] = Set(
  * * X *
  X * * *
  * * * X
  * X * * ,

  * X * *
  * * * X
  X * * *
  * * X * )
*/
