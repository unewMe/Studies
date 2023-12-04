object Main
{

    def main(args: Array[String]): Unit = 
      {
      val result = 2 + 3;
      val list = flatten1(List(List(1, 2), List(2, 1)));
      print(list);
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

    def flatten1[A](xss: List[List[A]]): List[A] = {
      if (xss == List()) {
        List()
      } else {
        xss.head ++ flatten1(xss.tail)
      }
    }

    def count[A](x: A, xs: List[A]): Int = {
      if (xs == List()) {
        0
      }
      else {
        if (xs.head == x) {
          1 + count(x, xs.tail);
        }
        else {
          0 + count(x, xs.tail);
        }
      }
    }

    def replicate[A](x: A, n: Int): List[A] = {
      if (n == 0) {
        List();
      }
      else {
        List(x) ++ replicate(x, n - 1)
      }
    }

    def sqrList(xs: List[Int]): List[Int] = {
      if (xs == List()) then Nil else xs.head * xs.head :: sqrList(xs.tail);

    }

    def palindrome[A](xs: List[A]): Boolean =
    {
      if (xs == xs.reverse) then true else false;

    }
    def listLength[A](xs: List[A]): Int =
      {
        if(xs== List()) then 0 else 1 + listLength(xs.tail)
      }

}
