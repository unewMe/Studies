def isPerfect(n:Int) : Boolean =
{
    def listaDzielnikow(pDzielnik:Int) : List[Int] =
      {
        if pDzielnik == 1 then Nil
        else
          if n % pDzielnik == 0 then pDzielnik :: listaDzielnikow(pDzielnik-1)
          else
            listaDzielnikow(pDzielnik-1)
      }
     def sumaDzielnikow(listaD:List[Int]) : Int =
       {
         if listaD == Nil then 1 else listaD.head + sumaDzielnikow(listaD.tail)
       }
    val czyDoskonala =(dzielnikiS:Int) => if dzielnikiS == n then true else false
    czyDoskonala(sumaDzielnikow(listaDzielnikow(n/2)))

}

isPerfect(28)