package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  trait TestSets2 {
    val s1: Set = x => (x >= 5) && (x <= 10)
    val s2: Set = x => (x >= 9) && (x <= 13)
    val s: Set = diff(s1, s2)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s1, 2), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    val s1: Set = x => (x >= 5) && (x <= 10)
    val s2: Set = x => if ((x >= 9) && (x <= 13)) true else false

    val s = union(s1, s2)
    assert(contains(s, 9), "Union 1")
    assert(contains(s, 10), "Union 2")
    assert(!contains(s, 14), "Union 3")
  }

  test("intersect contains only intersecting elements of each set") {
    new TestSets {
      val s4 = union(s1, s2)
      val s5 = union(s2, s3)
      val s6 = intersect(s4, s5)
      assert(contains(s6,2), "Intersect 1")
      assert(!contains(s6,3), "Intersect 2")
    }
  }

  test("diff works") {
    new TestSets2 {
      assert(contains(s1, 6), "Diff 1")
      assert(!contains(s2, 15), "Diff 2")
      assert(contains(s, 8), "Diff 3")
      assert(!contains(s, 9), "Diff 4")
    }
  }

  test("filter works") {
    new TestSets2 {
      val f = filter(s1, x => x % 2 == 0)
      val f1 = filter(s1, x => x > 20)
      assert(contains(f, 6), "Filter 1")
      assert(!contains(f, 5), "Filter 2")
      assert(!contains(f, 4), "Filter 3")
      assert(!contains(f1,11), "Filter 4")
    }
  }

  test("forall works") {
    new TestSets2 {
      val p: Int => Boolean = x => x >= 0
      val p1: Set = x => x >= 0
      val p2: Set = x => x >= 1
      assert(!forall(p, s), "Forall 1")
      assert(forall(p, p1), "Forall 2")
      assert(forall(p2, p), "Forall 3")
    }
  }

  test("exists works") {
    val s1: Set = x => x >= 11
    val s2: Set = x => x >= 12
    val s3: Set = x => x <= 11
    val p1: Int => Boolean = x => x <= 11
    assert(exists(s1, p1), "Exists 1")
    assert(!exists(s2, p1), "Exists 2")
    assert(exists(s3, p1), "Exists 3")
  }

  test("map works") {
    val s1: Set = x => x >= 3 && x <= 9
    val m1 = map(s1, x => x - 3)
    assert(contains(m1, 4), "Map 1")
    assert(!contains(m1, 9), "Map 2")
    assert(!contains(m1, -1), "Map 3")
  }
}
