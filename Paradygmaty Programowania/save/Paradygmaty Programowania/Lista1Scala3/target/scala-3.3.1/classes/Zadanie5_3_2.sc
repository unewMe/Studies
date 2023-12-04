def isPalindrome[A](list: List[A]): Boolean =
{
  def isPalindromeHelper[A](list1: List[A], index: Int): (List[A], Int, Boolean) =
  {
    if (list1 == Nil) (Nil, index - 1, true)
    else
    {
      val helper = isPalindromeHelper(list1.tail, index + 1)
      if (helper._2/2 < index) then (list1.head :: helper._1, helper._2, helper._3)
      else
        if helper._2/2 > index || (helper._2/2 == index && index%2 == 1) then
          if (helper._1.head != list1.head) (helper._1.tail, helper._2, false)
          else (helper._1.tail, helper._2, helper._3)
        else
          helper
    }
  }

  isPalindromeHelper(list, 0)._3
}

println(isPalindrome(List(1, 2, 3, 5,6,5, 3, 3, 1)))

println(isPalindrome(List(1, 2, 3, 5, 3, 2, 1, 2)))
println(isPalindrome(List(2)))