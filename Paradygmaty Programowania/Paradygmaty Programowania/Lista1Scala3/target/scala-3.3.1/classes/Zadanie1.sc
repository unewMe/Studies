def flatten1[A](xss: List[List[A]]): List[A] =
{
  if xss == List() then List() else xss.head ++ flatten1(xss.tail)
}

flatten1(List(List(1, 2), List(2, 1)))