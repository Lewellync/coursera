
// Currying
// Can we remove all the repetition?

// Sum is now a function that returns another function
def sum(f: Int => Int): (Int, Int) => Int = {
  def sumF(a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sumF(a + 1, b)
  }
  sumF
}

// Now that sum returns a function that handles the two variables, we no longer need to care
// about a and b, and that repetition is removed from the sum definitions.
def sumInts = sum(x => x)
def sumCubes = sum(x => x * x * x)
def sumFactorials = sum(fact)

def fact(x: Int): Int = if (x == 0) 1 else x * fact(x - 1)

// But can we make it even smaller?! Always

sum(x => x * x * x)(1, 10) // Equivalent to sumCubes(1, 10)
val temp = sum(fact)

temp(3,4)