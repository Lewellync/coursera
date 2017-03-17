package week3

/**
  * Created by lewel_000 on 3/16/2017.
  */
class Rational(x: Int, y: Int) {
  require(y != 0, "Denominator must be nonzero")

  private def gcd(a: Int, b: Int): Int = if (a == 0) b else gcd(b % a, a)
  private def g: Int = gcd(x, y)
  def numer: Int = x / g
  def denom: Int = y / g

  def this(x: Int) = this(x, 1)

  def add(that: Rational) = new Rational(
    numer * that.denom + that.numer * denom,
    denom * that.denom
  )

  def sub(that: Rational): Rational = add(that.neg)

  def neg = new Rational(-numer, denom)


  override def toString: String = numer + "/" + denom
}
