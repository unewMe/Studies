def palindrome[A](xs: List[A]): Boolean =
{
    def reverse[A](list:List[A]) : List[A] =
      {
        def reverseHelper(listPom:List[A],listR:List[A]): List[A] =
           {
             if listPom == Nil then listR
             else reverseHelper(listPom.tail,listPom.head :: listR)
           }
        reverseHelper(list,List())
      }
    val listReversed = reverse(xs)
    xs == listReversed
}
palindrome(List(1,2,2,1,3))

