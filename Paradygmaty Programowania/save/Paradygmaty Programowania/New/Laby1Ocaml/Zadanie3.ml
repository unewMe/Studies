let isPerfect n = 

  let rec divList pDiv =
    if pDiv = 1 then 
      pDiv :: []
    else 
      if n mod pDiv = 0 then 
        pDiv :: divList (pDiv - 1)   
      else 
        divList (pDiv - 1)

  and sumDiv listOfDiv = 
        if listOfDiv = [] then 
          0 
        else 
          List.hd listOfDiv + sumDiv (List.tl listOfDiv)

  in sumDiv (divList (n / 2)) = n;;

isPerfect 496;;