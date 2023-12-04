object Main extends App {

  // Użycie val do zdefiniowania funkcji rekurencyjnej
  val factorial: List[Int] => List[Int] = n => 
  {
    if (n == Nil) then Nil
    else (n.head*n.head) :: factorial(n.tail)
  }

  val result = factorial(List(1,2,2,-4))
  print(result)
  
}
