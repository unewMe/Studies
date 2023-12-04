def insert[A](precedes: (A, A) => Boolean, list: List[A]): List[A] =
{

  def insertPom(element: A, listPom: List[A]): List[A] = listPom match
  {
    case h :: _ if precedes(element, h) => element :: listPom
    case h :: t => h :: insertPom(element, t)
    case Nil => List(element)
  }

  def insertSortHelper(acc: List[A], list2: List[A]): List[A] = list2 match
  {
    case h2 :: t2 => insertSortHelper(insertPom(h2, acc), t2)
    case Nil => acc
  }

  list match
  {
    case Nil => Nil
    case h :: t => insertSortHelper(List(h), t)
  }
}

def mergeSort[A](precedes: (A, A) => Boolean, list: List[A]): List[A] =
{

  def merge(list1: List[A], list2: List[A]): List[A] =
    (list1, list2) match
      case (Nil, l) => l
      case (l, Nil) => l
      case (h1 :: t1, h2 :: t2) =>
        if (precedes(h1, h2))
          h1 :: merge(t1, h2 :: t2)
        else
          h2 :: merge(h1 :: t1, t2)

  def scalHelper(lists: List[List[A]]): List[List[A]] =
    lists match
      case Nil => Nil
      case h1 :: h2 :: t => merge(h1, h2) :: scalHelper(t)
      case h :: t => h :: scalHelper(t)

  def scal(lists: List[List[A]]): List[A] =
    lists match
      case Nil => Nil
      case h1 :: h2 :: t => scal(scalHelper(lists))
      case h :: Nil => h

  scal(list.map(x => List(x)))
}

insert((a:Int,b:Int) => a<=b ,List(9,6,4,5,3))
mergeSort((a:Int,b:Int) => a<=b ,List(9,6,4,5,3))

insert((a:Int,b:Int) => a<=b ,List(9))
mergeSort((a:Int,b:Int) => a<=b ,List(9))

insert((a:Int,b:Int) => a<=b ,List())
mergeSort((a:Int,b:Int) => a<=b ,List())