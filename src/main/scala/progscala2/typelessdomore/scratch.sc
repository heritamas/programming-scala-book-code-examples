
val f2: Function2[Int,String,String] = (i, s) => s+i

case class MyTuple2[A, B](fst: A, snd: B) {
  def _1 = { fst }
  def _2 = { snd }
}

var t2 = MyTuple2("Foo", 3L)
println(t2._1)

case class MyTuple(elts: Any*) {
  def __(i: Int) = { elts(i-1) }
}

var tn = MyTuple("Foo", 3.14, 3L)
println (tn __ 3)

class A {}
class B extends A {}

var ListOfA : List[A]  = List(new A(), new A())
println(ListOfA.getClass.getName)

ListOfA = List(new B(), new B())
println(ListOfA.getClass.getName)


def double(i: Int) { println("I'm the side effect: " + 2 * i) }
println(double(4))