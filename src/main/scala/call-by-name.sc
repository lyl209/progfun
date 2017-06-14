def myFunction(): Int = {
  2 + 5
}

def myNothing(): Unit = {

}

def byValue(x: Int, y: Int) = x + y

def byName(x:Int, y: => Int) = x + y

def byAnonymousFunc(x: Int, y: () => Int) = x + y()

byValue(1, 2 + 5)

byName(1, 2 + 5)

// Both are OK, but notice the difference in implementation
// Notice byName accepts a function since function is first-class value
// Functional languages treat functions as first-class values.
// This means that, like any other value, a function can be passed as a
// parameter and returned as a result.
byName(1, myFunction)
//byName(1, myNothing) // Not allowed
byAnonymousFunc(1, myFunction)

// byAnonymousFunc(1, 3 + 6) // Not allowed!

// Which proves the following 2 are different!!
// 1. def func(y: => Int)
// 2. def func(y: () => Int)

// OK
byAnonymousFunc(1, () => 1+3+4)

byName(1, myFunction())

// The def form is “by-name”,
// its right hand side is evaluated on each use.

def byNameParam: Int = 1+2+3

byNameParam

// byNameParam is of type Int

// So y is of type Int from the following
// def byName(x:Int, y: => Int) = x

// The right-hand side of a val definition is evaluated
// at the point of the definition itself.
val byValueParam: Int = 1+2+3