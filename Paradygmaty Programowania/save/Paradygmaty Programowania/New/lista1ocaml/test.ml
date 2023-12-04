let rec count (toCount, list) =
  if list = [] then 0
  else 
    if List.hd list = toCount then
      1 + count (toCount, List.tl list)
    else
      0 + count (toCount, List.tl list)


let result = count (2, [1; 2; 2; 3])

