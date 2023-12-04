val imply1: (Int) => BigInt = (liczba: Int) =>
{
  liczba match
  {
    case 0 => 0
    case 1 => 1
    case _ => imply1(liczba - 2) + imply1(liczba - 1)
  }
}

//val imply1Tail: (Int) => BigInt = (liczba: Int) =>
//{
//  def implyHelp(n: Int, lb: Int, liczbaO2: BigInt, liczbaO1: BigInt): BigInt =
//  {
//    n match
//    {
//      case `lb` if n == lb => liczbaO2 + liczbaO1
//      case _ => implyHelp(n + 1, lb, liczbaO1, liczbaO2 + liczbaO1)
//    }
//  }
//
//  liczba match
//  {
//    case 0 => 0
//    case 1 => 1
//    case _ => implyHelp(2, liczba, 0, 1)
//  }
//}

val imply1Tail: (Int) => BigInt = (liczba: Int) =>
{
  def implyHelp(n: Int, liczbaO2: BigInt, liczbaO1: BigInt): BigInt =
  {
    n match
    {
      case 0 => liczbaO2
      case 1 => liczbaO1
      case _ => implyHelp(n - 1, liczbaO1, liczbaO2 + liczbaO1)
    }
  }

  implyHelp(liczba, 0, 1)
}


//println(imply1(82))
imply1Tail(82)