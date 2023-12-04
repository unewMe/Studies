let isPerfect n = 
  let rec czyDzielnik x =
    if x = 1 then []
    else if n mod x <> 0 then x :: czyDzielnik (x - 1)   
    else czyDzielnik (x - 1)
  in czyDzielnik (n / 2);;

let list = isPerfect(30);;