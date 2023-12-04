def primes(n:Int) =
{
    def ifPrime(value:Int) =
    {
        for(x<-List.range(2,Math.sqrt(value).toInt+1);if(value%x==0)) yield x
    }
    for(x<-List.range(2,n+1);if ifPrime(x) == Nil) yield x
//    for(y<-List.range(2,n);x<-List.range(2,Math.sqrt(y).toInt+1) if(y%x!=0))yield y
}


primes(100)
primes(500)
primes(2)
primes(-10)