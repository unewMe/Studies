def sumProd (s:Int,e:Int) : (Int,Int) =
{
    if s >= e then
      (0,0)
    else
      def count(n:Int, sumAndPr:(Int,Int)) : (Int,Int) =
      {
         if n == e then
           sumAndPr
         else
           count(n+1,(sumAndPr._1+n,sumAndPr._2*n))
      }
      count(s,(0,1))
}

sumProd (-6,-2)
sumProd (-6,-6)
sumProd (-4,-6)
sumProd (-6,-5)

sumProd (-1,7)
sumProd (0,7)

sumProd (3,6)
sumProd (3,3)
sumProd (6,3)
sumProd (3,4)
