type 'a llist = LNil | LCons of 'a * (unit -> 'a llist);;

let rec lTake (k,lazyList) =
  match (k,lazyList) with
  |(0,_) ->[]
  |(_,LNil)-> []
  |(_,LCons(h,t)) -> h::lTake(k-1,t())
;; 

let fibList = 
  let rec fibHelper a acc = 
    LCons(a,fun() -> fibHelper(acc)(acc+a))
  in fibHelper 0 1  
;;

lTake(9,fibList)