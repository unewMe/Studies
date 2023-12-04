
type 'a llist = LNil | LCons of 'a * (unit -> 'a llist);;

let rec lazyList k = LCons(k,fun() -> lazyList(k+1));;

let rec lrepeat k lList = 
  match lList with
  | LNil -> LNil
  | LCons(a, b) -> 
    let rec helper x a =
      LCons(a, fun () -> if x < k - 1 then helper (x + 1) a else lrepeat k (b()))
    in helper 0 a
;;

let rec lTake (k,lazyList) =
  match (k,lazyList) with
  |(0,_) ->[]
  |(_,LNil)-> []
  |(_,LCons(h,t)) -> h::lTake(k-1,t())
;;  

let x = lrepeat 3 (lazyList 1);;
lTake(21,x);;
