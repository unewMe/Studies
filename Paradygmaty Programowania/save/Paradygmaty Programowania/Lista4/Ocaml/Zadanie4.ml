
type 'a bt = Empty | Node of 'a * 'a bt * 'a bt

let insideLength tree =
  let rec insideLengthHelper (node,depth) =
    match node with
      |Empty -> 0
      |Node(v,left,right) -> depth + insideLengthHelper(left,depth+1) + insideLengthHelper(right,depth+1)

  in insideLengthHelper (tree,0)    
;;

let outsideLength tree =
  let rec outsideLengthHelper (node,depth) =
    match node with
      |Empty -> depth
      |Node(v,left,right) -> outsideLengthHelper(left,depth+1) + outsideLengthHelper(right,depth+1)

  in outsideLengthHelper (tree,0)    
;;
      
      let tt = Node(1,
      Node(2,
        Node(4,
          Empty,
          Empty
            ),
         Empty
          ),
      Node(3,
        Node(5,
          Empty,
          Node(6,
            Empty,
            Empty
              )
            ),
        Empty
          )
         );;

insideLength tt;; 
outsideLength tt;;       