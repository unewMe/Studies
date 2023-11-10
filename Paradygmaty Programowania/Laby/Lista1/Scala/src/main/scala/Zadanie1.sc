def reverse4[A, B, C, D] (tuple:(A,B,C,D)) : (D,C,B,A)  =
{
    (tuple._4,tuple._3,tuple._2,tuple._1)
}

reverse4("Bartek", "To", "Ykhm", "Lewak")
reverse4("Bartek",2,5.0,true)