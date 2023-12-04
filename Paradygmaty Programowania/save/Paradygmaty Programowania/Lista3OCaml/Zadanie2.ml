let plus x y z = x + y + z;;
let add(x,y,z) = x+y+z;;


let uncurry3 f (x,y,z) = f x y z;; 
let curry3 f x y z = f(x,y,z);;

let uncurry3_2 = function f -> function (x,y,z) -> f x y z;; 
let curry3_2 = function f ->function x -> function y -> function z -> f(x,y,z);;

let uncurry3_3 = function f -> 
  fun tup ->
    let (x, y, z) = tup in
    f x y z;;

uncurry3 plus(1,2,3);;
curry3 add 1 2 3;;
uncurry3_2 plus(1,2,3);;
let x = curry3_2 add;;
x 1 2 3