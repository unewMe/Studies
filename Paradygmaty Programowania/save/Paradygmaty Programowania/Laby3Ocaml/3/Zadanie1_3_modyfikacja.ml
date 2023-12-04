let pow x =
  x*x
;;

let rec (>>) f n = fun x ->

  let rec build (l,element, acc) =
      if l <= 0 then
        acc
      else
        build (l - 1, f (element-1),element :: acc)
  in
  build (n,x,[])
    
;;


let fun1 = pow >> 4;;
fun1 5;;

let fun2 = pow >> 0;;
fun2 5;;

let fun2 = pow >> 1;;
fun2 5;;

let fun3 = pow >> -5;;
fun3 5;;