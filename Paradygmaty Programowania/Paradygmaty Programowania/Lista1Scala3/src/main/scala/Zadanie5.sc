def palindrome[A](xs: List[A]): Boolean = 
{
  xs == xs.reverse
}
palindrome(List(1,2,2,1))