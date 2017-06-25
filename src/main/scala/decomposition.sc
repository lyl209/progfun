trait Expr {
  def isNumber: Boolean
  def isSum: Boolean
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr
}


class Number(n: Int) extends Expr {
  def isNumber: Boolean = true
  def isSum: Boolean = false
  def numValue: Int = n
  def leftOp: Expr = throw new Error("Number.leftOp")
  def rightOp: Expr = throw new Error("Number.rightOp")
}


class Sum(e1: Expr, e2: Expr) extends Expr {
  def isNumber: Boolean = false
  def isSum: Boolean = true
  def numValue: Int = throw new Error("Sum.numValue")
  def leftOp: Expr = e1
  def rightOp: Expr = e2
}


//You can now write an evaluation function as follows.
def eval(e: Expr): Int = {
  if (e.isNumber) e.numValue
  else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
  else throw new Error("Unknown expression " + e)
}

//Problem: Writing all these classification and accessor functions
//quickly becomes tedious!

eval(new Sum(new Number(1), new Number(2)))


// class Prod(e1: Expr, e2: Expr) extends Expr // e1 * e2
// class Var(x: String) extends Expr // Variable ‘x’


// Exercise
/*
To integrate Prod and Var into the hierarchy, how many new method
definitions do you need?
(including method definitions in Prod and Var themselves, but not
  counting methods that were already given on the slides)
*/

// 3 new methods: isVar, isProd, name
// trait Expr now has 5 + 3 = 8 methods
// 8 for Number, Sum, Prod, Var
// Total 40 methods
// 40 - 15 = 25 new methods

// Number of new methods tend to grow qudratically

// Type Tests and Type Casts
// def isInstanceOf[T]: Boolean // checks whether this object’s type conforms to ‘T‘
// def asInstanceOf[T]: T // treats this object as an instance of type ‘T‘
// throws ‘ClassCastException‘ if it isn’t.

// Here’s a formulation of the eval method using type tests and casts:
/*
def eval(e: Expr): Int =
  if (e.isInstanceOf[Number])
    e.asInstanceOf[Number].numValue
  else if (e.isInstanceOf[Sum])
    eval(e.asInstanceOf[Sum].leftOp) +
      eval(e.asInstanceOf[Sum].rightOp)
  else throw new Error("Unknown expression " + e)

Assessment of this solution:

+ no need for classification methods, access methods only for
  classes where the value is defined.
- low-level and potentially unsafe.
*/

// Solution 1: Object-Oriented Decomposition

// Limitations of OO Decomposition

