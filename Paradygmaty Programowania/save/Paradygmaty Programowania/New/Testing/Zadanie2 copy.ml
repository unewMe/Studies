let sumProd x =
  let (s,e) = x in
  if s >= e then (0,0) 
  else 
    let rec count (n,(sum, il)) =
      if n = e then (sum, il) else count (n+1,(sum+n,il*n))
    in count (s,(0,1));; 
sumProd (6,7);;

    (* if(s+1>=e) then (s,s) 
    else*)