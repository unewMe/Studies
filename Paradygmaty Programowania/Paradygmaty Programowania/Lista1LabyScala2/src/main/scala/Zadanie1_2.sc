def reverse4[A, B, C, D] (krotka:(A,B,C,D)) : (D,C,B,A)  =
{
//  val nowaKrotka = (krotka._4,krotka._3,krotka._2,krotka._1)
//  nowaKrotka
    val (a,b,c,d) = krotka
    (d,c,b,a)
}

reverse4("Bartek", "To", "Ykhm", "Lewak")