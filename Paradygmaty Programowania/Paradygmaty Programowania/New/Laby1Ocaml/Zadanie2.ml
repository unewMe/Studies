let sumProd (s,e) =
  
  if s >= e then 
    (0,0) 
  else 
    let rec count (n,(sum, prod)) =
      if n = e then 
        (sum, prod) 
      else 
        count (n+1,(sum+n,prod*n))
    in count (s,(0,1));; 

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
