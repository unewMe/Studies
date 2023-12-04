let pow x =
  x*x
;;

let rec (>>) f n =

  if n < 0 then
    fun x -> []
  else
    let rec build (n, acc) =
      match acc with 
      |h::_ -> 
        if n = 0 then
          acc
        else
          build (n - 1, f (h) :: acc)
    in
    fun x -> build (n, [x])
    
;;


let fun1 = pow >> 3;;
fun1 5;;

let fun2 = pow >> 0;;
fun2 5;;

let fun3 = pow >> -5;;
fun3 5;;