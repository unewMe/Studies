type 'a llist = LNil | LCons of 'a * (unit -> 'a llist);;

let rec lazyList k = LCons(k,fun() -> lazyList(k+1));;

let rec lrepeat k lList = 
  match lList with
  |LNil -> LNil
  |LCons(a,b) -> 
    let rec helper x a =
      LCons(a,fun() -> if x < k-1 then helper (x+1) a else lrepeat k (b()))
    in helper 0 a
;;
  