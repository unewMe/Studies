let rec cutAndMend ( list, down, up ) = 
  
  let rec cutAndMendHelper( list, index ) = 

    match list with
  
      |h::t -> 
        (
          match index with 

          |x when down <= x && x <= up -> cutAndMendHelper( t, index+1 )
          |_ -> h :: cutAndMendHelper( t, index+1 )
        )

      |[] -> []

  in cutAndMendHelper( list, 0 );;

cutAndMend ( [1;2;3;4;5;6;7;8;9;10], 3, 7 );;

cutAndMend ( [1;2;3;4;5;6;7;8;9;10], 3, -7 );;
    
cutAndMend ( [1;2;3;4;5;6;7;8;9;10], 3, 17 );;
    
cutAndMend ( [1;2;3;4;5;6;7;8;9;10], 3, 3 );;
    
cutAndMend ( [1;2;3;4;5;6;7;8;9;10], -3, 7 );;
      
cutAndMend ( [1;2;3;4;5;6;7;8;9;10], -3, 10 );;  
      
cutAndMend ( [1], 7, 10 );;    