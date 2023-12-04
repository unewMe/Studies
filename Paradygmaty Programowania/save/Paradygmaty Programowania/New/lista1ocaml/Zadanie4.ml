let rec sqrtList list =
  if list = [] then []
  else (List.hd list * List.hd list) :: sqrtList (List.tl list);;

sqrtList [1; 2; 2; 3];;