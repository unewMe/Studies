type 'a llist = LNil | LCons of 'a * (unit -> 'a llist);;

type 'a lBT = LEmpty | LNode of 'a * (unit ->'a lBT) * (unit -> 'a lBT);;


(* let rec lTree n = LNode (n, fun() -> lTree (2*n) , fun() -> lTree (2*n + 1));; *)
(* let rec lTree n = LNode (n, fun () -> lTree (2 * n), fun () -> lTree (2 * n + 1));; *)
let rec lTree n = LNode ((n),(fun () -> lTree (2 * n)),(fun () -> lTree (2 * n + 1)));;

let  takeAll lazyTree = 
  let rec takeHelper queue =
    match queue with
    |h::t ->
      match h with
      |LNode(v,left,right) -> LCons(v,fun() -> takeHelper(t @ [left();right()]))
  in takeHelper [lazyTree];;   

let rec lTake (k,lazyList) =
  match (k,lazyList) with
  |(0,_) ->[]
  |(_,LNil)-> []
  |(_,LCons(h,t)) -> h::lTake(k-1,t())
;; 

let tree = lTree(2);;
let x = takeAll(tree);;
lTake(21,x);;