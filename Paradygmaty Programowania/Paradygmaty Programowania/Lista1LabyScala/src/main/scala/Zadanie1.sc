def reverse4[A, B, C, D] (a:A, b:B, c:C, d:D) : (D,C,B,A)  =
{
  val nowaKrotka= (d,c,b,a)
  nowaKrotka
}

val x = reverse4("Bartek", "To", "Ykhm", "Lewak")
println(x)