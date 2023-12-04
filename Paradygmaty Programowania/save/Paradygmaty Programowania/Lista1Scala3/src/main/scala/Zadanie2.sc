def count[A](x: A, xs: List[A]): Int = 
{
  if xs == List() then 0
  else 
    if xs.head == x then 1 + count(x, xs.tail)
    else 
      0 + count(x, xs.tail)
}
count(3, List(1,2,3,3,4,5,5,3,3))