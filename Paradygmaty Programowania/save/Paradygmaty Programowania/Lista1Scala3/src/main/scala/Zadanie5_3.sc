def isPalindrome[A](list:List[A]) : Boolean =
  {
        def isPalindromeHelper[A](list1:List[A],list2:List[A]) : Boolean =
        {
          if list2 == List() then false
          else
            if list1 == list2 || list1 == list2.tail then true
            else isPalindromeHelper(list2.head::list1,list2.tail)
        }
        isPalindromeHelper(List(),list)
  }
isPalindrome(List(1,2,3,5,3,2,1,2))
