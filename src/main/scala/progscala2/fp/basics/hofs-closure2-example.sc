// src/main/scala/progscala2/fp/basics/hofs-closure2-example.sc

def m1 (multiplier: Int => Int) = {
  (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
}

def m2(factor: Int) : Int => Int = {
  val multiplier = (i: Int) => i * factor
  multiplier
}

m1(m2(2))
m1(m2(3))
