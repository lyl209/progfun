// immutable linked list

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

// Note the abbreviation (val head: Int, val tail: IntList)
// defines at the same time parameters and fields of a class

// class Cons(val head: Int, val tail: IntList) extends IntList {}

// It is equivalent to:
// class Cons(_head: Int, _tail: IntList) extends IntList {
//   val head = _head
//   val tail = _tail
// }

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false

  // We already implemented
  // def head: T
  // def tail: List[T]
  // by using val in the constructor.
}

class Nil[T] extends List[T] {
  def isEmpty = true
  def head = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
  // Nothing is a subtype of any other type, including T, List[T]
  // So this is OK
  // def tail: List[T]
}

val cons = new Cons(5, new Cons(3, null))

cons.head
cons.tail


// Generic function
def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])

singleton[Int](1)
singleton[Boolean](true)

// Scala can deduce the correct type parameters so type parameters
// can be left out!
singleton(1)
singleton(true)

// Type erasure:
// Type parameters do not affect evaluation in Scala.
// Only affect compiler.

// C++, C# keep type parameters at run time

// Polymorphism
// subtyping: instance of a subclass can be  passed to a base class
// generics: instances of a function or class are created
// by type parameterization

// Exercise
def nth[T](n: Int, xs: List[T]): T =
  if (xs.isEmpty) throw new IndexOutOfBoundsException("ERROR!")
  else if (n == 0) xs.head
  else nth(n - 1, xs.tail)

new Nil // OK
//new Cons // Not OK

// Notice type parameter is left out for new Nil and
val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))

nth(2, list)
nth(0, list)
nth(4, list)
nth(-1, list)

