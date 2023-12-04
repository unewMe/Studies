def isPerfect(n:Int) : Boolean =
{
    def listaDzielnikow(pDzielnik:Int) : List[Int] =
      {
        if pDzielnik == 1 then pDzielnik::Nil
        else
          if n % pDzielnik == 0 then pDzielnik :: listaDzielnikow(pDzielnik-1)
          else
            listaDzielnikow(pDzielnik-1)
      }
     def sumaDzielnikow(listaD:List[Int]) : Int =
       {
         if listaD == Nil then 0 else listaD.head + sumaDzielnikow(listaD.tail)
       }
    sumaDzielnikow(listaDzielnikow(n/2)) == n

}

isPerfect(498)