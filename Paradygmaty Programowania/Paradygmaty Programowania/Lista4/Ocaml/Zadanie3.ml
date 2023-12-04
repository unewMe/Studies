type 'a bt = Empty | Node of 'a * 'a bt * 'a bt

let fstOf(x,_,_) = x;;
let sndOf(_,y,_) = y;;
let thOf(_,_,z) = z;;

let visit tree = 
  let rec treesToVisit queue =

    match queue with
    |[] -> []
    |h::t -> 
      match h with 
      |Empty -> treesToVisit t
      |Node(v,left,right) -> v :: treesToVisit(t @ [left;right])

  in treesToVisit[tree] 
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

visit tt;;             