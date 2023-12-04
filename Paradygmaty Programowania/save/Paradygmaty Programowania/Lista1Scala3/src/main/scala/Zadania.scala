import scala.List
object Zadania
{

    def main(args: Array[String]): Unit =
    {
        val result = 2 + 3;
        val list = flatten1(List(List(1, 2), List(2, 1)));
        print(list);
        print("\n")
        val counted = count(3, list);
        print(counted);
        print("\n")
        val replicated = replicate("la", 3);
        print(replicated);
        print("\n")
        val sqrted = sqrList(list);
        print(sqrted);
        print("\n")
        print(palindrome(list))
        print("\n")
        val listLengthValue = listLength(list);
        print(listLengthValue);
        val zaa = isPalindrome(List(1, 2, 3, 5, 3, 2, 1, 2))
        println(zaa)
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

    def isPalindrome[A](list: List[A]): Boolean =
    {
        def isPalindromeHelper[A](list1: List[A], index: Int): (List[A], Int, Boolean) =
        {
            if (list1 == Nil) (Nil, index - 1, true)
            else
            {
                val helper = isPalindromeHelper(list1.tail, index + 1)
                if (helper._2/2 < index) (list1.head::helper._1, helper._2, helper._3)
                else
                {
                    if (helper._1.head != list1.head) (helper._1.tail, helper._2, false)
                    else (helper._1.tail, helper._2, helper._3)
                }
            }
        }

        isPalindromeHelper(list, 0)._3
    }


}
