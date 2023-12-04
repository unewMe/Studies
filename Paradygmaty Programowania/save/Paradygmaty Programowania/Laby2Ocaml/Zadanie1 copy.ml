let rec cutAndMend ( down, up ) list = 
  
  let rec cutAndMendHelper( list, index ) = 

    match ( list, index ) with
  
      |( h::t, x ) when down <= x && x <= up -> cutAndMendHelper( t, index+1 )
      |( h::t, _ ) -> h :: cutAndMendHelper( t, index+1 )  
      |( [], _ ) -> []

  in cutAndMendHelper( list, 0 )
;;


let cutAndMend15 = cutAndMend(1,5) ;;

cutAndMend15 [1;2;3;4;5;6;7;8;9;10] ;;

cutAndMend ( 3, 7 ) [1;2;3;4;5;6;7;8;9;10] ;;

cutAndMend ( 3, -7 ) [1;2;3;4;5;6;7;8;9;10] ;;
  
cutAndMend ( 3, 17 ) [1;2;3;4;5;6;7;8;9;10] ;;
  
cutAndMend ( -3, 7 ) [1;2;3;4;5;6;7;8;9;10] ;;

cutAndMend ( 3, 3 ) [1;2;3;4;5;6;7;8;9;10] ;;
     
cutAndMend ( -3, 10 ) [1;2;3;4;5;6;7;8;9;10] ;;  
    
cutAndMend ( 7, 10 )  [1] ;;   