def imply1(liczba:Int) : Int =
  liczba match
  {
    case 0 => 0
    case 1 => 1
    case _ => imply1(liczba-2) + imply1(liczba-1)
  }

def imply1Tail(liczba:Int) : Int =
  {
    def implyHelp(n: Int, liczbaO2: Int, liczbaO1: Int): Int =
    {
      n match
      {
        case 0 => liczbaO2
        case 1 => liczbaO1
        case _ => implyHelp(n-1,liczbaO1,liczbaO2+liczbaO1)
      }
    }
    implyHelp(liczba,0,1)
  }

//imply1(42)
//imply1Tail(42)


