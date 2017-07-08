import scala.collection.immutable
import scala.collection.immutable.NumericRange
// Vector implementations is shallow tree

// 32 elements
// 32 pointers to each 32 elements
// continue ...

// 32 = 2 ^ 5
// 32 * 32 = 2 ^ 10
// 2 ^ 15
// 2 ^ 20
// 2 ^ 25
// 2 ^ 30 this is the limit

// log32(N)

// bulk operation (i.e. map) is good because can do cache in chunk
// locality is better than list
// but list is still best if you only need head/tail stuff


// Cons
//
// Instead of x :: xs, there is
//   x +: xs Create a new vector with leading element x, followed
// by all elements of xs.
//   xs :+ x Create a new vector with trailing element x, preceded
// by all elements of xs.
//
// (Note that the : always points to the sequence.)

// Append an element to a vector
// log32(N) for object creations
// Get 2 copies of vectors


/*

Collection Hierarchy

    Iterable
  /   |    \
Seq   Set   Map

*/

// Arrays and Strings support the same operations as Seq and can
// implicitly be converted to sequences where needed.
// (They cannot be subclasses of Seq because they come from Java)

val xs = Array(1, 2, 3, 44)
xs map (x => x * 2)

val s = "Hello World"
s filter (c => c.isUpper)

// Shorter
s filter (_.isUpper)


// Ranges
// to (inclusive), until (exclusive), by (to determine step value):
/*
val r: Range = 1 until 5
val s: Range = 1 to 5
1 to 10 by 3
6 to 1 by -2
*/

val lambdas: NumericRange[Double] = (0.1 to 0.9 by 0.2)
lambdas.foreach(l => print(l + ","))

lambdas.toArray

(2 to 5).foreach(x => println(x))
(-5 to -2).foreach(x => println(x))

val alphas: immutable.IndexedSeq[Double] = (2 to 5) map (x => math.pow(10, -x))

alphas.toArray

/*
xs exists p true if there is an element x of xs such that p(x) holds,
false otherwise.

xs forall p true if p(x) holds for all elements x of xs, false otherwise.

xs zip ys A sequence of pairs drawn from corresponding elements
of sequences xs and ys.

xs.unzip Splits a sequence of pairs xs into two sequences consisting
of the first, respectively second halves of all pairs.

xs.flatMap f Applies collection-valued function f to all elements of
xs and concatenates the results

xs.sum The sum of all elements of this numeric collection.
xs.product The product of all elements of this numeric collection
xs.max The maximum of all elements of this collection (an
Ordering must exist)
xs.min The minimum of all elements of this collection
*/

s exists (_.isUpper)

s forall (_.isUpper)

val pairs = List(1, 2, 3) zip s
//pairs: List[(Int, Char)] = List((1,H), (2,e), (3,l))

pairs.unzip
//(List[Int], List[Char]) = (List(1, 2, 3),List(H, e, l))

s flatMap (c => List('.', c))
//String = .H.e.l.l.o. .W.o.r.l.d

xs.sum
xs.max

// Example: Combinations
//To list all combinations of numbers x and y where x is drawn from
//  1..M and y is drawn from 1..N:
val M = 5
val N = 7

(1 to M) flatMap (x => (1 to N) map (y => (x, y)))


// Example: Scalar Product
//  To compute the scalar product of two vectors:

def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
  (xs zip ys).map(xy => xy._1 * xy._2).sum

/*
An alternative way to write this is with a pattern matching function
value.

def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
  (xs zip ys).map{ case (x, y) => x * y }.sum

Generally, the function value
  { case p1 => e1 ... case pn => en }

is equivalent to
  x => x match { case p1 => e1 ... case pn => en }

*/


// Exercise
// A number n is prime if the only divisors of n are 1 and n itself.
// What is a high-level way to write a test for primality of numbers?
//   For once, value conciseness over efficiency.
// def isPrime(n: Int): Boolean = ???

//def isPrime(n: Int): Boolean =
//  (1 to n - 1) forall (m => n % m != 0)

def isPrime(n: Int): Boolean =
  (2 until n) forall (d => n % d != 0)


