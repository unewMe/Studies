type 'a tree3 = TNil | TNode0 of 'a | TNode1 of 'a * 'a tree3| TNode2 of 'a * 'a tree3 * 'a tree3| TNode3 of 'a * 'a tree3 * 'a tree3 * 'a tree3;;

let rec mapTree3 f tr3 =
  match tr3 with
  |TNil -> TNil
  |TNode0(v) -> TNode0(f v)
  |TNode1(v,ch1) -> TNode1(f v,mapTree3 f ch1)
  |TNode2(v,ch1,ch2) -> TNode2(f v,mapTree3 f ch1,mapTree3 f ch2)
  |TNode3(v,ch1,ch2,ch3) -> TNode3(f v,mapTree3 f ch1,mapTree3 f ch2,mapTree3 f ch3)
;;

  
let pow x = x*x;;

let x1 = TNode3(2,TNode3(3,TNil,TNil,TNil),TNode3(4,TNil,TNil,TNil),TNode3(5,TNil,TNil,TNil));;
let x2 = TNode2(2,TNode3(3,TNil,TNil,TNil),TNode0(4));;
mapTree3 pow x1;;
mapTree3((+) 2) x1;;

mapTree3 pow x2;;
mapTree3((+) 2) x2;;
