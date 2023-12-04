let rec fib number = 
  
  match number with
  | 0 -> 0
  | 1 -> 1
  | _ -> fib (number-1) + fib (number-2)
;;

let rec fibTail number =

  let rec fibTailHelper(current,liczba1,liczba2) = 
    
    match current with
    | 0 -> liczba1
    | 1 -> liczba2
    | _ -> fibTailHelper(current-1,liczba2,liczba1+liczba2)

  in fibTailHelper(number,0,1);;  
    

fibTail 6

  