let sumProd x =
  let s = fst x and e = snd x in
  if s >= e then (0,0)
  else
    let rec count (n,(sum, il)) =
      if n = e then (sum, il) else count (n+1,(sum+n,il*n))
    in count (s,(0,1));; 
sumProd (2,3);;