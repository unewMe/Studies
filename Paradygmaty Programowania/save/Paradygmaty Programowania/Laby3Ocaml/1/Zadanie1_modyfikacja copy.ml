let pow x =
  x*x
;;

let (>>) f n =

  if n < 0 then
    fun x -> []
  else
    let rec build (l, acc) =
      match acc with 
      |h::_ ->
        if l = 0 then
          acc
        else if l = n then
          build (l - 1, (f (h)) :: acc)
        else
          build (l - 1, (f (h-1)) :: acc)
    in
    fun x -> build (n, [x-1])
    
;;


let fun1 = pow >> 3;;
fun1 5;;

let fun2 = pow >> 0;;
fun2 5;;

let fun3 = pow >> -5;;
fun3 5;;