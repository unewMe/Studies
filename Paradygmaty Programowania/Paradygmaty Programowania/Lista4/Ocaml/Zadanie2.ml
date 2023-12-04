let rec f x = f x
;;


type 'param para_i_x = int * 'param;;

let conv (y: 'a para_i_x) : 'b =
  snd y