package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    println()
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if(c == 0 || c == r) 1 else pascal(c-1, r-1) + pascal(c, r-1)
    }

  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def balanced(chars: List[Char], level: Int): Boolean = (chars, level) match {
        case (Nil, x)         => if (x == 0) true else false
        case (_, x) if x < 0  => false
        case ('(' :: xs, _)   => balanced(xs, level + 1)
        case (')' :: xs, _)   => balanced(xs, level - 1)
        case (_ :: xs, _)     => balanced(xs, level)
      }
      balanced(chars, 0)
    }

  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      def count(remainder: Int, coins: List[Int]): Int = {
        if(remainder < 0 || coins.isEmpty) 0
        else if (remainder == 0) 1
        else count(remainder, coins.tail) + count(remainder - coins.head, coins)
      }
      count(money, coins)
    }
  }
