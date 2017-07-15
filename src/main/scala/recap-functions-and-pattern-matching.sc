// Case Classes
abstract class JSON
case class JSeq (elems: List[JSON]) extends JSON
case class JObj (bindings: Map[String, JSON]) extends JSON
case class JNum (num: Double) extends JSON
case class JStr (str: String) extends JSON
case class JBool(b: Boolean) extends JSON
case object JNull extends JSON

val data = JObj(Map(
  "firstName" -> JStr("John"),
  "lastName" -> JStr("Smith"),
  "address" -> JObj(Map(
  "streetAddress" -> JStr("21 2nd Street"),
  "state" -> JStr("NY"),
  "postalCode" -> JNum(10021)
  )),
  "phoneNumbers" -> JSeq(List(
    JObj(Map(
      "type" -> JStr("home"), "number" -> JStr("212 555-1234")
    )),
    JObj(Map(
      "type" -> JStr("fax"), "number" -> JStr("646 555-4567")
    ))
  ))
))

def show(json: JSON): String = json match {
  case JSeq(elems) =>
    "[" + (elems map show mkString ", ") + "]"
  case JObj(bindings) =>
    val assocs = bindings map {
      case (key, value) => "\"" + key + "\": " + show(value)
    }
    "{" + (assocs mkString ", ") + "}"
  case JNum(num) => num.toString
  case JStr(str) => '\"' + str + '\"'
  case JBool(b) => b.toString
  case JNull => "null"
}

// Question: What's the type of:
// { case (key, value) => key + ": " + value }

// Error: missing parameter type for expanded functuon
// The argument types of an anonymous function must be fully known.
// val what = {case (key, value) => key + ": " + value}

/*
Taken by itself, the expression is not typable.
We need to prescribe an expected type.

The type expected by map on the last slide is

JBinding => String,

the type of functions from pairs of strings and JSON data to String. where
JBinding is

type JBinding = (String, JSON)

 */

type JBinding = (String, JSON)

// OK
val that: scala.Function1[JBinding, String] =
  { case (key, value) => key + ": " + value }


// The Function1 Trait

// Here’s an outline of trait Function1:
trait Function1[-A, +R] {
  def apply(x: A): R
}

// The pattern matching block
//  { case (key, value) => key + ": " + value }
// expands to the Function1 instance

new Function1[JBinding, String] {
  def apply(x: JBinding) = x match {
    case (key, value) => key + ": " + show(value)
  }
}


// Subclassing Functions

// One nice aspect of functions being traits is that we can subclass the
// function type.
// For instance, maps are functions from keys to values:
// trait Map[Key, Value] extends (Key => Value)
trait MyMap[Key, Value] extends (Key => Value)

// Sequences are functions from Int indices to values:
// trait Seq[Elem] extends (Int => Elem)
trait MySeq[Elem] extends (Int => Elem)

// That’s why we can write
//    elems(i)
// for sequence (and array) indexing.

// Compare to Java: elems[i] because arrays are not functions in Java.

// Partial Matches
val f: String => String = { case "ping" => "pong" }

f("ping")

/*
scala.MatchError: pong (of class java.lang.String)
	at #worksheet#.$anonfun$f$1(recap-functions-and-pattern-matching.sc:93)
	at #worksheet#.get$$instance$$res2(recap-functions-and-pattern-matching.sc:97)
	at #worksheet#.#worksheet#(recap-functions-and-pattern-matching.sc:208)

f("pong")
*/

// Is there a way to find out whether the function can be applied to a given
// argument before running it?


// Partial Functions
val g: PartialFunction[String, String] = { case "ping" => "pong" }

g.isDefinedAt("ping") // true
g.isDefinedAt("pong") // false

/*
trait PartialFunction[-A, +R] extends Function1[-A, +R] {
  def apply(x: A): R
  def isDefinedAt(x: A): Boolean
}
*/


// If the expected type is a PartialFunction, the Scala compiler will expand
// { case "ping" => "pong" }
// as follows:

new PartialFunction[String, String] {
  def apply(x: String) = x match {
    case "ping" => "pong"
  }
  def isDefinedAt(x: String) = x match {
    case "ping" => true
    case _ => false
  }
}

// Exercise 1

// Given the function
val h: PartialFunction[List[Int], String] = {
  case Nil => "one"
  case x :: y :: rest => "two"
}
// What do you expect is the result of
//  f.isDefinedAt(List(1, 2, 3)) ?
// Answer: true

h.isDefinedAt(List(1, 2, 3))

// Exercise 2
// How about the following variation:
val l: PartialFunction[List[Int], String] = {
  case Nil => "one"
  case x :: rest =>
    rest match {
      case Nil => "two"
    }
}

// l.isDefinedAt(List(1, 2, 3)) gives:
// true!!

// But at run time it will give an run time error

l.isDefinedAt(List(1, 2, 3))

/*
scala.MatchError: List(2, 3) (of class scala.collection.immutable.$colon$colon)
	at #worksheet#.$anonfun$l$lzycompute$1.applyOrElse(recap-functions-and-pattern-matching.sc:140)
	at #worksheet#.$anonfun$l$lzycompute$1.applyOrElse(recap-functions-and-pattern-matching.sc:136)
	at scala.runtime.AbstractPartialFunction.apply(recap-functions-and-pattern-matching.sc:30)
	at #worksheet#.get$$instance$$res7(recap-functions-and-pattern-matching.sc:150)
	at #worksheet#.#worksheet#(recap-functions-and-pattern-matching.sc:321)

l(List(1,2,3))
*/


// Conclusion
// isDefined only apply for outer most matching block
