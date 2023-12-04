let rec root3_2 a =

  let rec oblicz x =
    
    match x with
    | x when abs_float (x *. x *. x -. a) <= 1.0E-15 *. abs_float a -> x
    | _ -> oblicz (x +. (a /. (x ** 2.0) -. x) /. 3.0)
  in
  oblicz (if a > 1.0 then a /. 3.0 else a)
;;

root3_2 (-35.)