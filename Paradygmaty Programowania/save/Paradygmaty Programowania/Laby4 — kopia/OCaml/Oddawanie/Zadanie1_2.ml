type 'a tree3 = TNil | TNode of 'a * 'a tree3 option * 'a tree3 option * 'a tree3 option

let rec mapTree3 f tr3 =
  let mapTree3Helper node =
    match node with
    | None -> None
    | Some x -> Some (mapTree3 f x)
  in
  match tr3 with
  |TNil -> TNil
  |TNode(v,ch1,ch2,ch3) -> TNode(f v, mapTree3Helper ch1 ,mapTree3Helper ch2,mapTree3Helper ch3)
;;

  
let pow x = x*x;;

let x = 
  TNode(2, 
    Some (TNode(3, Some(TNil), None, None)), 
    Some (TNode(4, None, Some(TNil), None)), 
    Some (TNode(5, None, None, Some(TNil)))
  );;
mapTree3 pow x;;
mapTree3((+) 2) x
