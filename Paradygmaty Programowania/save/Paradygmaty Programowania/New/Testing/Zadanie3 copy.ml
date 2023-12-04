let isPerfect n = 
  let rec czyDzielnik x =
    if x = 1 then x :: []
    else if n mod x = 0 then x :: czyDzielnik (x - 1)   
    else czyDzielnik (x - 1)
  and sumaDzielnikow x = 
        if x = [] then 0 else List.hd x + sumaDzielnikow (List.tl x)
  in let listaDzielnikow = czyDzielnik (n / 2) 
     in sumaDzielnikow listaDzielnikow = n;;

isPerfect 496;;