
// Two forms of polymorphism.
// One was subtyping, which was usually associated with object oriented programming.
// The other was generics, which came originally from functional programming.
// Once you combine subtyping and generics, the subtle interactions that
// we are going to explore in this session and the next one.

// Subtyping, where we can pass instances of a subtype where a base type was required.
// And, generics, where we can parameterize types with other types.


// Type Bounds

// 1. Upper bounds
// def assertAllPos[S <: IntSet](r: S): S = ...

// 2. Lower bounds
// def assertAllPos[S >: NonEmpty](r: S): S = ...

// 3. Mixed bounds
// [S >: NonEmpty <: IntSet]
//would restrict S any type on the interval between NonEmpty and
//IntSet.


// Covariance

// Array Typing Problem
//
// NonEmpty [] a = new NonEmpty []{ new NonEmpty (1 , Empty , Empty )}
// IntSet [] b = a
// b [0] = Empty
// NonEmpty s = a [0]

// runtime namely and array store exception
// What actually happens it that, to make up for the problems caused by coherence of arrays,
// Java needs to store in every array a type tag that reflects what,
// at what type this array was created. So when we create a non empty array,
// the type tag would say, well it's actually a non empty array.
// And now that we have signed something in to an element of the array,
// we run time type of the thing we have signed gets checked against the type tag.
// So in our case here it would be have an empty value but the type tag would
// read non empty and that would give you a run time error.

// Now, it seems that this is not a very good deal. We have traded a compile-time error
// for a run-time error, and we have also paid the price for a run-time check that
// we have to do. Every array store has to undergo this, this check against the array tag.


// it actually turned out that they wanted to do with they wanted to be able to write
// a method such as sort. That would work for any array. So the way they would express
// that in the first version of Java, it would say the sort method would take an object array.
// And then covariance of arrays was necessary so that an array of strings or
// an array of integers could all be passed to an object array.
// sort(Object[] a)

// This is before generic type

/*
The Liskov Substitution Principle
  The following principle, stated by Barbara Liskov, tells us when a
type can be a subtype of another.
  If A <: B, then everything one can to do with a value of
type B one should also be able to do with a value of type A.

[The actual definition Liskov used is a bit more formal. It says:
  Let q(x) be a property provable about objects x of type B.
Then q(y) should be provable for objects y of type A where
A <: B.]
*/


// Exercise
/*
val a: Array[NonEmpty] = Array(new NonEmpty(1, Empty, Empty))
val b: Array[IntSet] = a
b(0) = Empty
val s: NonEmpty = a(0)

When you try out this example, what do you observe?
=> A type error in line 2
*/

// In Scala, arrays are not covariant!