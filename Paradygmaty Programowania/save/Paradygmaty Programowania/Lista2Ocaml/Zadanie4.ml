(* let matching list = 
  match list with
  | _ ::_ :: x :: _ -> x
;;

let matching2 list = 
  match list with
  | _ :: (x,_) :: _ -> x
;;  *)

let matching ( _ ::_ :: x :: _) = x;;
let matching2 (_ :: (x,_) :: _) = x;;
matching  [-2; -1; 0; 1; 2] ;;
matching2 [ (1,2); (0,1) ];;