let pow x =
  x*x
;;

let rec (>>) f n x  = 

  if n < 0 then
    []  
  else  
    let rec build (n,acc) =
      if n = 0 then
        acc
      else
        build(n-1,(f(List.hd acc)) :: acc)

    in build(n,[x])
;;      
