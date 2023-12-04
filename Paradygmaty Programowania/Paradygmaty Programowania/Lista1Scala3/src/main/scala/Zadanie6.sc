def listLength[A](xs: List[A]): Int =
{
  if xs == List() then 0
  else 1 + listLength(xs.tail)
}
listLength(List(1,2,2,1))