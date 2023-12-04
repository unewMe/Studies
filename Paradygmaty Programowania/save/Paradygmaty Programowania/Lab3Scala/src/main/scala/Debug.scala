object Debug
{
  def main(args: Array[String]): Unit =
  {
    primes(20);
  }

  def primes(n: Int) = {
    //    def ifPrime(value:Int) =
    //    {
    //        for(x<-List.range(2,Math.sqrt(value).toInt+1);if(value%x==0)) yield x
    //    }
    //    for(x<-List.range(2,n);if ifPrime(x) == Nil) yield x
    for (x <- List.range(2, Math.sqrt(n).toInt + 1); y <- List.range(2, n); if (y == x || y % x != 0)) yield y
  }
}