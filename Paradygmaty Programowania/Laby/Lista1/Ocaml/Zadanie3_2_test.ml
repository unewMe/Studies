let isPerfect n =

  if n > 1 then

    let rec root(value) = 

      let e = 0.000001 in
      let rec rootHelper acc =

        let temp = value /. acc in
        if abs_float(acc -. temp) >= e then
          rootHelper((acc +. temp) /. 2.0)
        else
          (acc +. temp) /. 2.0
      in rootHelper(1.0) 
    in

    let rec divList pDiv =
      if pDiv = 1 then
        pDiv :: []
      else
        if n mod pDiv = 0 then
          let temp = n / pDiv in
          pDiv :: temp :: divList (pDiv - 1)
        else
          divList (pDiv - 1)

    and divSum listOfDiv =
      if listOfDiv = [] then
        0
      else
        List.hd listOfDiv + divSum (List.tl listOfDiv)

    in
    divSum (divList (int_of_float (root (float_of_int n)))) = n
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