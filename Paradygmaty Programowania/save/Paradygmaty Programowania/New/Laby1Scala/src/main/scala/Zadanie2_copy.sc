def sumProd (s:Int,e:Int) : (Int,Int) =
{
    def addT(tuple1: (Int, Int), tuple2: (Int, Int)): (Int, Int) =
    {
        (tuple1._1 + tuple2._1, tuple1._2 * tuple2._2)
    }

    if s >= e then
      (0,0)
    else
      def count(n:Int) : (Int,Int) =
      {
         if n == e then
           (0,1)
         else
           addT((n,n),count(n+1))
      }
      count(s)
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
