//In fact function values are treated as objects in Scala.
//The function type A => B is just an abbreviation for the class
//scala.Function1[A, B], which is defined as follows.
//package scala
trait Function1[A, B] {
  def apply(x: A): B
}

//An anonymous function such as
// (x: Int) => x * x
//is expanded to:
{
  class AnonFun extends Function1[Int, Int]
  {
    def apply(x: Int) = x * x
  }
  new AnonFun
}

//or, shorter, using anonymous class syntax:
new Function1[Int, Int] {
  def apply(x: Int) = x * x
}

//So the OO-translation of
// val f = (x: Int) => x * x
// f(7)
//would be
val f = new Function1[Int, Int] {
  def apply(x: Int) = x * x
}
f.apply(7)

//Note that a method such as
// def f(x: Int): Boolean = ...
//is not itself a function value.

// eta-expansion

// (x: Int) => f(x)
//or, expanded:
// new Function1[Int, Boolean] {
//   def apply(x: Int) = f(x)
// }


// Exercise
/*
In package week4, define an
object List {
...
}
with 3 functions in it so that users can create lists of lengths 0-2
using syntax
List() // the empty list
List(1) // the list with single element 1
List(2, 3) // the list with elements 2 and 3.
*/

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

class Nil[T] extends List[T] {
  def isEmpty = true
  def head = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

object List {
  // List(1, 2) = List.apply(1, 2)
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))
  def apply[T]() = new Nil

}

