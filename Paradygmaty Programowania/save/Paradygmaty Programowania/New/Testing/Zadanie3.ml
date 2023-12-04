let isPerfect n = 
  let rec czyDzielnik x =
    if x = 1 then []
    else if n mod x = 0 then x :: czyDzielnik (x - 1)   
    else czyDzielnik (x - 1)
  in 
  let listaDzielnikow = czyDzielnik (n / 2) 
  in
  let rec sumaDzielnikow x = 
    if x = [] then 1 else List.hd x + sumaDzielnikow (List.tl x)
  in 
  let sumaOstateczna = sumaDzielnikow listaDzielnikow
  in
  let boolCzyIdealna = if sumaOstateczna = n then true else false
  in boolCzyIdealna;;

let x = isPerfect 6;;