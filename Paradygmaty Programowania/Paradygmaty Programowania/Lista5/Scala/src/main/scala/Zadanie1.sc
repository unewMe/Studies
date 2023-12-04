def lrepeat[A](k:Int)(lazyList: Stream[A]) : Stream[A] =
{
  lazyList match
    case h #::t =>
      def helper(x: Int) : Stream[A] =
      {
          if x < k then
            h #:: helper(x+1)
          else
            lrepeat(k)(t)
      }
      helper(0)

}

val x = lrepeat(3)(Stream.from(1))
x.take(21).toList
