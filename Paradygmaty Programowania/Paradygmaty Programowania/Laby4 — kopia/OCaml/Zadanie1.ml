type 'a tree3 = TNil | TNode of 'a * 'a tree3 * 'a tree3 * 'a tree3;;

let rec mapTree3 f tr3 =
  match tr3 with
  |TNil -> TNil
  |TNode(v,ch1,ch2,ch3) -> TNode(f v,mapTree3 f ch1,mapTree3 f ch2,mapTree3 f ch3)
;;

  
let pow x = x*x;;

let x = TNode(2,TNode(3,TNil,TNil,TNil),TNode(4,TNil,TNil,TNil),TNode(5,TNil,TNil,TNil));;
mapTree3 pow x;;
mapTree3((+) 2) x