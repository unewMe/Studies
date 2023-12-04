def sumProd (krotka:(Int,Int)): (Int,Int) =
{
  val (s,e) = krotka
  if s >= e then (0,0)
  else
      def count(n:Int,sumAndIl:(Int,Int)) : (Int,Int) =
      {
          if n == e then sumAndIl else count(n+1,(sumAndIl._1+n,sumAndIl._2*n))
      }
      count(s,(0,1))
}

sumProd(6,7)
//if s + 1 >= e then (s, s)
//    else
