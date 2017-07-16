// Other Uses of For-Expressions
/*
Operations of sets, or databases, or options.

Question: Are for-expressions tied to collections?
Answer: No! All that is required is some interpretation of map, flatMap
and withFilter.

There are many domains outside collections that afford such an
interpretation.

Example: random value generators.
*/

import java.util.Random
val rand = new Random
rand.nextInt()

/*
Question: What is a systematic way to get random values for other
domains, such as
  ▶ booleans, strings, pairs and tuples, lists, sets, trees
*/

// Generators
// Let’s define a trait Generator[T] that generates random values of type T:
/*
trait Generator[+T] {
  def generate: T
}
*/

// Some instances:

val integers = new Generator[Int] {
  val rand = new java.util.Random
  def generate = rand.nextInt()
}

// val booleans = new Generator[Boolean] {
//   def generate = integers.generate > 0
// }

// val pairs = new Generator[(Int, Int)] {
//   def generate = (integers.generate, integers.generate)
// }


//   Ideally, we would like to write:
/*
val booleans = for (x <- integers) yield x > 0
def pairs[T, U](t: Generator[T], u: Generator[U]) = for {
  x <- t
  y <- u
} yield (x, y)
*/
// What does this expand to?
/*
def pairs[T, U](t: Generator[T], u: Generator[U]) =
t flatMap (x => u map (y => (x, y)))
*/


// Generator with map and flatMap

trait Generator[+T] {
  self => // an alias for "this".
  def generate: T
  def map[S](f: T => S): Generator[S] = new Generator[S] {
    def generate = f(self.generate)
  }
  def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
    def generate = f(self.generate).generate
  }
}

// The booleans Generator

// What does this definition resolve to?

val booleans = for (x <- integers) yield x > 0
/*
val booleans = integers map { x => x > 0 }

val booleans = new Generator[Boolean] {
  def generate = (x: Int => x > 0)(integers.generate)
}

val booleans = new Generator[Boolean] {
  def generate = integers.generate > 0
}
*/

// The pairs Generator

def pairs[T, U](t: Generator[T], u: Generator[U]) = t flatMap {
  x => u map { y => (x, y) } }

/*
def pairs[T, U](t: Generator[T], u: Generator[U]) = t flatMap {
  x => new Generator[(T, U)] { def generate = (x, u.generate) } }

def pairs[T, U](t: Generator[T], u: Generator[U]) = new Generator[(T, U)] {
  def generate = (new Generator[(T, U)] {
    def generate = (t.generate, u.generate)
  }).generate }

def pairs[T, U](t: Generator[T], u: Generator[U]) = new Generator[(T, U)] {
  def generate = (t.generate, u.generate)
}
*/


// Generator Examples

def single[T](x: T): Generator[T] = new Generator[T] {
  def generate = x
}

def choose(lo: Int, hi: Int): Generator[Int] =
  for (x <- integers) yield lo + x % (hi - lo)

def oneOf[T](xs: T*): Generator[T] =
  for (idx <- choose(0, xs.length)) yield xs(idx)


// A List Generator

// A list is either an empty list or a non-empty list.
def lists: Generator[List[Int]] = for {
  isEmpty <- booleans
  list <- if (isEmpty) emptyLists else nonEmptyLists
} yield list

def emptyLists = single(Nil)
def nonEmptyLists = for {
  head <- integers
  tail <- lists
} yield head :: tail


// A Tree Generator
// Can you implement a generator that creates random Tree objects?
trait Tree
case class Inner(left: Tree, right: Tree) extends Tree
case class Leaf(x: Int) extends Tree
// Hint: a tree is either a leaf or an inner node.


def leafs: Generator[Leaf] = for {
  x <- integers
} yield Leaf(x)

def inners: Generator[Inner] = for {
  l <- trees
  r <- trees
} yield Inner(l, r)

def trees: Generator[Tree] = for {
  isLeaf <- booleans
  tree <- if (isLeaf) leafs else inners
} yield tree

trees.generate


// Application: Random Testing
/*
You know about units tests:
  ▶ Come up with some some test inputs to program functions and a
postcondition.
  ▶ The postcondition is a property of the expected result.
  ▶ Verify that the program satisfies the postcondition.

Question: Can we do without the test inputs?
Yes, by generating random test inputs.
*/


// Random Test Function
// Using generators, we can write a random test function:

// (test: T => Boolean) is a function
// that takes a generator T and return a Boolean
def test[T](g: Generator[T], numTimes: Int = 100)
           (testFunc: T => Boolean): Unit = {

  for (i <- 0 until numTimes) {
    val value: T = g.generate
    assert(testFunc(value), "test failed for "+value)
  }
  println("passed " + numTimes + " tests)")
}

val g: Generator[(List[Int], List[Int])]
  = pairs(lists, lists)


// Example usage:
/*
The assert will fail

test(pairs(lists, lists)) {
  case (xs, ys) => (xs ++ ys).length > xs.length
}
*/

// Extra note
// Different with the following FunSuite_test
// call-by-name v.s. function
// (test: => Boolean) is a call-by-name Boolean type
def FunSuite_test[T](name: String)
           (test: => Boolean): Unit = {
  assert(test, name + " test failed")
  println(name + " ran")
}

FunSuite_test("FunSuite_test") {
  println("test implementation")
  true
}

// ScalaCheck
/*
Shift in viewpoint: Instead of writing tests, write properties that are
assumed to hold.
This idea is implemented in the ScalaCheck tool.

forAll { (l1: List[Int], l2: List[Int]) =>
  l1.size + l2.size == (l1 ++ l2).size
}

It can be used either stand-alone or as part of ScalaTest.
See ScalaCheck tutorial on the course page.
*/