def sqrList(xs: List[Int]): List[Int] = 
{
  if xs == List() then Nil 
  else xs.head * xs.head :: sqrList(xs.tail)
}

val sqrt: List[Int] => List[Int] = xs =>
  if xs == Nil then Nil
  else xs.head * xs.head :: sqrt(xs.tail)

sqrList(List(1,2,2,1))
sqrt(List(1,2,2,1))
