file:///C:/Users/chudz/Desktop/Dawid/Paradygmaty%20Programowania/Lista2Scala3/src/main/scala/Debug.scala
### java.lang.IllegalArgumentException: Comparison method violates its general contract!

occurred in the presentation compiler.

action parameters:
offset: 145
uri: file:///C:/Users/chudz/Desktop/Dawid/Paradygmaty%20Programowania/Lista2Scala3/src/main/scala/Debug.scala
text:
```scala
object Debug 
{
    def main(args: Array[String]): Unit = 
    {
        val x = ifSegment((List(1,2,3,4), List(1,2,3,3,5)))
        print(x@@)
    }

    def ifSegment[A](pair: (List[A], List[A])): Boolean = 
    {
        pair match 
        {
            case (List(), _) => true
            case (h1 :: t1, h2 :: t2) =>
                (h1, h2) match 
                {
                    case (h1Value, h2Value) => ifSegment(t1, t2)
                    case _ => false
                }
            case _ => false // Dodaj obsługę innych przypadków
        }
    }
}


```



#### Error stacktrace:

```
java.base/java.util.TimSort.mergeLo(TimSort.java:781)
	java.base/java.util.TimSort.mergeAt(TimSort.java:518)
	java.base/java.util.TimSort.mergeForceCollapse(TimSort.java:461)
	java.base/java.util.TimSort.sort(TimSort.java:254)
	java.base/java.util.Arrays.sort(Arrays.java:1441)
	scala.collection.SeqOps.sorted(Seq.scala:727)
	scala.collection.SeqOps.sorted$(Seq.scala:719)
	scala.collection.immutable.List.scala$collection$immutable$StrictOptimizedSeqOps$$super$sorted(List.scala:79)
	scala.collection.immutable.StrictOptimizedSeqOps.sorted(StrictOptimizedSeqOps.scala:78)
	scala.collection.immutable.StrictOptimizedSeqOps.sorted$(StrictOptimizedSeqOps.scala:78)
	scala.collection.immutable.List.sorted(List.scala:79)
	scala.meta.internal.pc.completions.Completions.completions(Completions.scala:210)
	scala.meta.internal.pc.completions.CompletionProvider.completions(CompletionProvider.scala:86)
	scala.meta.internal.pc.ScalaPresentationCompiler.complete$$anonfun$1(ScalaPresentationCompiler.scala:123)
```
#### Short summary: 

java.lang.IllegalArgumentException: Comparison method violates its general contract!