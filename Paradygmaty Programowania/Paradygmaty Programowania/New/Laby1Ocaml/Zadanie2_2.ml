let sumProd (s,e) =
  
  let addT ((x1,y1),(x2,y2)) = 
    (x1+x2,y1*y2) 
  in 

  if s >= e then 
    (0,0) 
  else 
    let rec count n =
      if n = e then 
        (0, 1) 
      else 
        addT((n,n),count(n+1))
    in count (s);; 

sumProd (-6,-2);;       (*Test ujemnych liczb*)
sumProd (-6,-6);;       
sumProd (-4,-6);;
sumProd (-6,-5);;

sumProd (-1,7);;
sumProd (0,7);;         (*Test dodatnich liczb*)

sumProd (3,6);;
sumProd (3,3);;
sumProd (6,3);;
sumProd (3,4);;
