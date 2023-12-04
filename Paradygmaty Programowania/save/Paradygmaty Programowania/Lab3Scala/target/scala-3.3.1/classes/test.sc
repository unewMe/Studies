def primes(n: Int) =
{
  for {
    y <- List.range(2, n)
    x <- List.range(2, Math.sqrt(y).toInt + 1) if (y % x != 0)
    if (y == x || y % x != 0)
  } yield y
}

primes(20)