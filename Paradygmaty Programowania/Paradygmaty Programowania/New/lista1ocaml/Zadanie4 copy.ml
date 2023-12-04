let pow x = x * x;;
let rec sqrtList list = if list = [] then [] else pow(List.hd list) :: sqrtList(List.tl list);;

sqrtList [1; 2; 2; 3];;