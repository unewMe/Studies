import scala.language.postfixOps

//val plus = (x:Int,y:Int,z:Int) => x+y+z
//val curry3 = (fun:((Int,Int,Int)=>Int)) => (x:Int) =>(y:Int)=>(z:Int) => (fun curried x)(y)(z)
//plus(4,6,7)
//val x =curry3(plus)
//x(4)(6)(7)

val plus = (x:Int,y:Int,z:Int) => x+y+z
val add = (x:Int) => (y:Int) => (z:Int) => x+y+z
def curry3[A,B,C,D](fun:(A,B,C)=>D) =
  {
    (x:A) => (y:B) => (z:C) => fun(x,y,z)
  }
def uncurry3[A,B,C,D](fun:(A)=>B=>C=>D) =
{
  (x:A,y:B,z:C) => fun(x)(y)(z)
}

def curry3_2[A,B,C,D](fun:(A,B,C)=>D) =
{
  x => y => z => fun(x,y,z)
}
def uncurry3_2[A,B,C,D](fun:(A)=>B=>C=>D) =
{
  (x,y,z) => fun(x)(y)(z)
}
plus(4,6,7)
add(4)(6)(7)
val x =curry3(plus)
val y = uncurry3(add)
x(4)(6)(7)
y(4,6,7)

//val curry3 = (fun:((Int,Int,Int)=>Int)) => (fun curried)
//val uncurry3 =(fun:(Int=>Int=>Int)) Function.uncurried(add)
//plus(4,6,7)
//add(4)(6)(7)
//val x =curry3(plus)
//val y = uncurry3(add)
//x(4)(6)(7)
//y(4,6,7)

