

object Functions {

  def main(args: Array[String]) {
    // Run Functions
    println("Hello, world!")

    var result = sumInts(2,5)
    println("result: " + result)

    result = sumSquares(2,5)
    println("result: " + result)

    result = sumPowersOfTwo(2,5)
    println("result: " + result)
  }

  def id(x: Int, message: String)  = {
    println(message)
    x
  }

  def square(x: Int, message: String): Int = {
    println(message + "square")
	x * x
  }

  def powerOfTwo(x: Int, message: String) : Int = {
    println(message + "Power of Two: " + x)
    if (x == 0) 1 else 2 * powerOfTwo(x - 1, message + (x - 1))
  }

  def sum(f: (Int, String) => Int, a: Int, b: Int): Int = {
    if (a > b) 0 else f(a, "Sum ") + sum(f, a + 1, b)
  }

  def suma(f: Int => Int): (Int, Int) => Int = {
    def sumFunc(a: Int, b: Int): Int =
      if (a > b) 0 else f(a) + sumFunc(a + 1, b)
  sumFunc
  }

  def sumInts(a: Int, b: Int): Int = sum(id, a, b)
  def sumSquares(a: Int, b: Int) : Int = sum(square, a, b)
  def sumPowersOfTwo(a: Int, b: Int): Int = sum(powerOfTwo, a, b)
}
