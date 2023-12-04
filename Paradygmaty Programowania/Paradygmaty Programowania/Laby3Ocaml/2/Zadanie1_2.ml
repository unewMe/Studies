let pow x =
  x*x
;;

let rec (>>) f n = fun x ->

  if n <= 0 then
    []
  else
    let rec build (l, acc) =
      match acc with 
      |h::_ -> 
        if l = 0 then
          acc
        else
          build (l - 1, f (h) :: acc)
    in
    build (n-1, [x])
    
;;


let fun1 = pow >> 4;;
fun1 5;;

let fun2 = pow >> 0;;
fun2 5;;

let fun3 = pow >> -5;;
fun3 5;;