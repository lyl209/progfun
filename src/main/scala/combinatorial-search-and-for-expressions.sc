import scala.collection.immutable
// Example:
// Given a positive integer n, find all pairs of positive integers i and j
// with 1 <= j < i < n such that i + j is prime.

val n = 7
(1 until n) map (i =>
  (1 until i) map (j => (i, j)))

// scala.collection.immutable.IndexedSeq
// [scala.collection.immutable.IndexedSeq[(Int, Int)]] =
// Vector(Vector(), Vector((2,1)), Vector((3,1), (3,2)),
// Vector((4,1), (4,2), (4,3)),
// Vector((5,1), (5,2), (5,3), (5,4)),
// Vector((6,1), (6,2), (6,3), (6,4), (6,5)))

/*

Collection Hierarchy

          Iterable
        /      |  \
      Seq      Set, Map
     /  | ..
String, Array, List, Vector, Range

*/

// Range, IndexedSeq, Vector...

// We can combine all the sub-sequences using foldRight with ++
(xss foldRight Seq[(Int, Int)]())(_ ++ _)

// Or,
xss.flatten

((1 until n) map (i =>
  (1 until i) map (j => (i, j)))).flatten

//scala.collection.immutable.IndexedSeq[(Int, Int)] =
// Vector((2,1), (3,1), (3,2), (4,1), (4,2), (4,3), (5,1), (5,2), (5,3), (5,4), (6,1), (6,2), (6,3), (6,4), (6,5))


// xs flatMap f = (xs map f).flatten

// Simplified
(1 until n) flatMap (i => (1 until i) map (j => (i, j)))


def isPrime(n: Int): Boolean = (2 until n) forall (d => n % d != 0)

(1 until n) flatMap (i =>
  (1 until i) map (j => (i, j))) filter ( pair => isPrime(pair._1 + pair._2))

// For-Expression

case class Person(name: String, age: Int)

val persons = Array(new Person("A", 20), new Person("B", 15), new Person("C", 33))

// To obtain the names of persons over 20 years old

for ( p <- persons if p.age > 20 ) yield p.name

// is the same as

persons filter (p => p.age > 20) map (p => p.name)

// The for-expression is similar to loops in imperative languages, except that is builds a list
// of the results of all iterations

// A for-expression is of the form

// for ( s ) yield e

// where s is a sequence of generators and filters, and e is an expression whose value
// is returned by an iteration
//
// > a generator is of the form p <- e, where p is a pattern and e an expression whose value is a collection.
// > a filter is of the form if f where f is a boolean expression.
// > The sequence must start with a generator.
// > If there ar several generators in the sequence, the last generators vary faster than the first.


// for { s } is OK too with multiple lines
for {
  i <- 1 until n
  j <- 1 until i
  if isPrime(i + j)
} yield (i, j)


// Exercise
// Write a version of scalarProduct that makes use of a for:
/*def scalarProduct(xs: List[Double], ys: List[Double]) : Double = {
  val p =
  for {
    x <- xs
    y <- ys
  } yield (x * y)
  p.sum
}
*/
def scalarProduct(xs: List[Double], ys: List[Double]) : Double =
  (for ((x, y) <- xs zip ys) yield x * y).sum



=======
>>>>>>> Stashed changes

