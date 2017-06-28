trait Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr

def eval(e: Expr): Int = e match {
  case Number(n) => n
  case Sum(e1, e2) => eval(e1) + eval(e2)
}


def show(e: Expr): String = e match {
  case Number(x) => x.toString
  case Sum(l, r) => show(l) + " + " + show(r)
}


show(Sum(Number(1), Number(44)))

/*
Add case classes Var for variables x and Prod for products x * y as
discussed previously.

Change your show function so that it also deals with products.
  Pay attention you get operator precedence right but to use as few
  parentheses as possible.

Example

Sum(Prod(2, Var(”x”)), Var(”y”))
should print as “2 * x + y”. But
Prod(Sum(2, Var(”x”)), Var(”y”))
should print as “(2 + x) * y”.
*/

