let rec count (toCount,list) =
  if  list = [] then 0
  else 
    if List.hd list = toCount then
      1 + count(toCount,List.tl list)
    else
      count(toCount,List.tl list);;

count (2, [1; 2; 2; 3]);;
