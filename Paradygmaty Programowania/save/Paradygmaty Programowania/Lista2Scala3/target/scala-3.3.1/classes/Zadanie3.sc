def root3 (a:Double) =
  {
        def oblicz(x:Double) : Double =
          {
            if math.abs(x*x*x-a) <= 1.0E-15* math.abs(a) then x
            else
              oblicz(x + (a / math.pow(x, 2) - x) / 3)
          }
        oblicz(if a > 1.0 then a / 3 else a)
  }

def root3_2 (a: Double): Double =
{
  def oblicz(x: Double): Double =
    x match
    {
      case x if math.abs(x * x * x - a) <= 1.0E-15 * math.abs(a) => x
      case _ => oblicz(x + (a / math.pow(x, 2) - x) / 3)
    }

  oblicz(if (a > 1.0) a / 3 else a)
}

val root3_3: (Double) => Double = (a:Double) =>
{
  def oblicz(x: Double): Double =
    x match
    {
      case x if math.abs(x * x * x - a) <= 1.0E-15 * math.abs(a) => x
      case _ => oblicz(x + (a / math.pow(x, 2) - x) / 3)
    }

  oblicz(if (a > 1.0) a / 3 else a)
}

root3_2(27)
root3_3(27)

