def myFunction(): Int = 2 + 5

def myNothing(): Unit = {}

def byValue(x: Int, y: Int) = x + y

def byName(x:Int, y: => Int) = x + y

def byAnonymousFunc(x: Int, y: () => Int) = x + y()

byValue(1, 2 + 5)

byName(1, 2 + 5)

// Notice byName & byValue both accept
// a function since function is first-class value
// Functional languages treat functions as first-class values.
// This means that, like any other value, a function can be passed as a
// parameter and returned as a result.

// The difference is when to evaluate
byName(1, myFunction)
//byName(1, myNothing) // Not allowed
byValue(1, myFunction)


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

def byNameParam: Int = 1 + 2 + 3

byNameParam

// byNameParam is of type Int

// So y is of type Int from the following
// def byName(x:Int, y: => Int) = x

// The right-hand side of a val definition is evaluated
// at the point of the definition itself.
val byValueParam: Int = 1+2+3


def anyFnunction(): Unit = {
}

def anyBody: Any = {1+2}

def test(name: String)(body: => Any): Int = {
  5
}

// The only way to not pass in () is passing a block

test("hello"){1+2+3} // passing a block

// Is {} similar to () in this case?
test("hello")(1+2+3)

test("hello")({1+2+3})

test("hola")(anyBody)

test("pass in func")(anyFnunction)

test("pass in func value")(anyFnunction())


// The only way to not pass in () is passing a block
//
// ▶ A block is delimited by braces { ... }.
// { val x = f(3)
//   x * x
// }
// ▶ It contains a sequence of definitions or expressions.
//
// ▶ The last element of a block is an expression that defines its value.
//
// ▶ This return expression can be preceded by auxiliary definitions.
//
// ▶ Blocks are themselves expressions; a block may appear
//   everywhere an expression can.