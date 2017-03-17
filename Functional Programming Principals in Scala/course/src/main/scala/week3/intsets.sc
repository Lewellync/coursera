
abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

// Let's consider implementing sets as binary trees.
// There are two types of possible trees: a tree for the empty set, and a tree consisting of an integer
// and two sub-trees.

// This is a singleton object
object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def union(other: IntSet): IntSet = other


  override def toString: String = "."
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem

  override def toString: String = "{" + left + elem + right + "}"
}

// Persistent data structures

val t1 = new NonEmpty(3, Empty, Empty)
val t2 = t1 incl 4

val t3 = new NonEmpty(4, Empty, Empty)
val t4 = new NonEmpty(5, Empty, Empty)
t3 union t4
t4 union t3