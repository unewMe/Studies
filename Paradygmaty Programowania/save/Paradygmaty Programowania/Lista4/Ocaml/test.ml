type 'a bt = Empty | Node of 'a * 'a bt * 'a bt

let visit tree = 
  let rec treesToVisit queue =
    match queue with
    | [] -> []
    | Empty :: t -> treesToVisit t
    | Node (x, left, right) :: t -> x :: treesToVisit (t @ [left; right])
  in treesToVisit [tree]

let tt = Node(1,
  Node(2,
    Node(4, Empty, Empty),
    Empty
  ),
  Node(3,
    Node(5, Empty, Node(6, Empty, Empty)),
    Empty
  )
);;

visit tt;;
