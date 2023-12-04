file:///C:/Users/chudz/Desktop/Dawid/Paradygmaty%20Programowania/Lista1Scala3/src/main/scala/Zadania.scala
### dotty.tools.dotc.core.TypeError$$anon$1: Toplevel definition flatten1 is defined in
  C:/Users/chudz/Desktop/Dawid/Paradygmaty Programowania/Lista1Scala3/src/main/scala/Zadanie1.sc
and also in
  C:/Users/chudz/Desktop/Dawid/Paradygmaty Programowania/Lista1Scala3/src/main/scala/Zadania.scala
One of these files should be removed from the classpath.

occurred in the presentation compiler.

action parameters:
offset: 209
uri: file:///C:/Users/chudz/Desktop/Dawid/Paradygmaty%20Programowania/Lista1Scala3/src/main/scala/Zadania.scala
text:
```scala
import scala.List
object Zadania
{

    def main(args: Array[String]): Unit =
    {
        val result = 2 + 3;
        val list = flatten1(List(List(1, 2), List(2, 1)));
        print(list);
        @@print("\n")
        val counted = count(3, list);
        print(counted);
        val replicated = replicate("la", 3);
        print(replicated);
        val sqrted = sqrList(list);
        print(sqrted);
        print(palindrome(list))
        val listLengthValue = listLength(list);
        print(listLengthValue);
    }

    def flatten1[A](xss: List[List[A]]): List[A] =
    {
      if xss == List() then List()
      else xss.head ::: flatten1(xss.tail)
    }

    def count[A](x: A, xs: List[A]): Int =
    {
      if xs == List() then 0
      else
        if xs.head == x then 1 + count(x, xs.tail)
        else
          0 + count(x, xs.tail)
    }

    def replicate[A](x: A, n: Int): List[A] =
    {
      if n == 0 then Nil
      else
        x :: replicate(x, n - 1)
    }


    def sqrList(xs: List[Int]): List[Int] =
    {
      if xs == List() then Nil else xs.head * xs.head :: sqrList(xs.tail)
    }

    def palindrome[A](xs: List[A]): Boolean =
    {
       xs == xs.reverse
    }
    def listLength[A](xs: List[A]): Int =
    {
      if xs== List() then 0 else 1 + listLength(xs.tail)
    }


}

```



#### Error stacktrace:

```

```
#### Short summary: 

dotty.tools.dotc.core.TypeError$$anon$1: Toplevel definition flatten1 is defined in
  C:/Users/chudz/Desktop/Dawid/Paradygmaty Programowania/Lista1Scala3/src/main/scala/Zadanie1.sc
and also in
  C:/Users/chudz/Desktop/Dawid/Paradygmaty Programowania/Lista1Scala3/src/main/scala/Zadania.scala
One of these files should be removed from the classpath.