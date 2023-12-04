let rec split2Rec list = 

  match list with

   |h1::h2::t -> let ( l1, l2 ) = split2Rec t in ( h1::l1,  h2::l2 )
   | _ -> ( [], [] )
;;

let rec split2Tail list = 

  let rec split2TailHelper( list, acc ) = 

    match list with

    |h1::h2::t -> split2TailHelper( t, ( h1::fst acc, h2::snd acc ) )
    | _ -> acc

  in split2TailHelper( list, ( [], [] ) )
;; 

split2Rec  [1;2;3;4;5;6;7;8;9;10;11] ;; 
split2Tail [1;2;3;4;5;6;7;8;9;10;11] ;; 


split2Rec  [1;2] ;; 
split2Tail [1;2] ;; 

split2Rec  [1] ;; 
split2Tail [1] ;; 

split2Rec  [] ;; 
split2Tail [] ;; 