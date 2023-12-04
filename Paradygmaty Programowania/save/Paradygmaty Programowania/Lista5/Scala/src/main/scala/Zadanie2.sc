def lFib() : Stream[Int] =
{
  def helper(a:Int,acc:Int) : Stream[Int] =
    {
      a #::helper(acc,a+acc)
    }
  helper(0,1)
}

lFib().take(10).toList