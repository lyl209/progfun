// Return type Nothing
def error(msg: String) = throw new Error(msg)

//java.lang.Error: test
//at #worksheet#.error(how-classes-are-organized.sc:2)
//at #worksheet#.get$$instance$$res0(how-classes-are-organized.sc:4)
//at #worksheet#.#worksheet#(how-classes-are-organized.sc:24)
//error("test")


// Every reference class type also has null as a value.
// The type of null is Null.
// Null is a subtype of every class that inherits from Object; it is
// incompatible with subtypes of AnyVal.

val x = null // x: Null
val y: String = null // y: String
//val z: Int = null // error: type mismatch


// Exercise
// What is the type of
// if (true) 1 else false

if (true) 1 else false // res0: AnyVal = 1

