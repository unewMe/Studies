def sumProd (s:Int,e:Int) : (Int,Int) =
{
  if s >= e then (0,0)
  else
      def count(n:Int,sumAndIl:(Int,Int)) : (Int,Int) =
      {
            if n == e then sumAndIl else count(n+1,(sumAndIl._1+n,sumAndIl._2*n))
      }
      count(s,(0,1))
}

sumProd(3,3)

//    if s+1 == e then (s,s)
//    else