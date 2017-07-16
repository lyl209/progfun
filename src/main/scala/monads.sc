// Design pattern

// Monads
/*
Data structures with map and flatMap seem to be quite common.
In fact there’s a name that describes this class of a data structures
together with some algebraic laws that they should have.

They are called monads
*/


// What is a Monad?
// A monad M is a parametric type M[T] with two operations, flatMap and
// unit, that have to satisfy some laws.
trait M[T] {
  def flatMap[U](f: T => M[U]): M[U]
}
def unit[T](x: T): M[T]
// In the literature, flatMap is more commonly called bind.


// Examples of Monads
/*

▶ List is a monad with unit(x) = List(x)
▶ Set is monad with unit(x) = Set(x)
▶ Option is a monad with unit(x) = Some(x)
▶ Generator is a monad with unit(x) = single(x)

flatMap is an operation on each of these types, whereas unit in Scala is
different for each monad.

*/

// Monads and map
/*
map can be defined for every monad as a combination of flatMap and unit:

  m map f == m flatMap (x => unit(f(x)))
          == m flatMap (f andThen unit)
*/


// Monad Laws -> Moniod
/*
To qualify as a monad, a type has to satisfy three laws:

Associativity
  (m flatMap f) flatMap g == m flatMap (x => f(x) flatMap g)

  integers are moniod
  (x+y) + z = x + (y+z)

Left unit
  unit(x) flatMap f == f(x)

Right unit
  m flatMap unit == m

*/

// Checking Monad Laws
/*

abstract class Option[+T] {
  def flatMap[U](f: T => Option[U]): Option[U] = this match {
    case Some(x) => f(x)
    case None => None
  }
}

// Checking the Left Unit Law
Need to show: Some(x) flatMap f == f(x)

   Some(x) flatMap f

== Some(x) match {
      case Some(x) => f(x)
      case None => None
   }

== f(x)

// Checking the Right Unit Law
Need to show: opt flatMap Some == opt

   opt flatMap Some

== opt match {
      case Some(x) => Some(x)
      case None => None
   }

// both cases become itself
== opt


// Checking the Associative Law

Need to show:
(opt flatMap f) flatMap g == opt flatMap (x => f(x) flatMap g)

   (opt flatMap f) flatMap g

// expand flatMap by Option's implementation definition
== opt match { case Some(x) => f(x) case None => None }
       match { case Some(y) => g(y) case None => None }

// put 2nd match inside first match
== opt match {
      case Some(x) =>
          f(x) match { case Some(y) => g(y) case None => None }
      case None =>
          None match { case Some(y) => g(y) case None => None }
   }

// reduce 2nd match None
== opt match {
      case Some(x) =>
          f(x) match { case Some(y) => g(y) case None => None }
      case None => None
   }

// Reduce 1st match by flatMap definition
== opt match {
  case Some(x) => f(x) flatMap g
  case None => None
}
== opt flatMap (x => f(x) flatMap g)
*/

// Significance of the Laws for For-Expressions
/*
We have seen that monad-typed expressions are typically written as for
expressions.

What is the significance of the laws with respect to this?

1. Associativity says essentially that one can “inline” nested for
   expressions:

   for (y <- for (x <- m; y <- f(x)) yield y
        z <- g(y)) yield z

== for (x <- m;
        y <- f(x)
        z <- g(y)) yield z

2. Right unit says:

   for (x <- m) yield x
== m

3. Left unit does not have an analogue for for-expressions.

*/


