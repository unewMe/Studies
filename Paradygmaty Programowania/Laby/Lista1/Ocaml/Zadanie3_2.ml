let isPerfect n = 

  if n > 1 then   
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

    in sumDiv (divList (n / 2)) = n
  else
    false
  ;;

isPerfect 496;;  
isPerfect 6;;
isPerfect 28;;
isPerfect 0;;
isPerfect 1;;
isPerfect 2;;
isPerfect (-5);;
isPerfect 188;;
isPerfect 235;;