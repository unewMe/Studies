object Debug 
{
    def main(args: Array[String]): Unit = 
    {
        val x = ifSegment((List(1,2,3,4), List(1,2,3,3,5)))
        print(x)
    }

    // def ifSegment[A](pair: (List[A], List[A])): Boolean = 
    // {
    //     pair match 
    //     {
    //         case (List(), _) => true
    //         case (h1 :: t1, h2 :: t2) =>
    //             (h1, h2) match 
    //             {
    //                 case (h1Value, h2Value) => ifSegment(t1, t2)
    //                 case _ => false
    //             }
    //         case _ => false // Dodaj obsługę innych przypadków
    //     }
    // }

def ifSegement[A](pair:(List[A],List[A])) : Boolean =
{
    pair match
    {
       case (List(),_) => true
       case (h1::t1,h2::t2) => if h1 == h2 then ifSegement((t1,t2)) else false
    }

}


}

