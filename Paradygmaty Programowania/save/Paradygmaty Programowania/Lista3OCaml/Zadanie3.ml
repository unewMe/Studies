let rec sumProd list =

  match list with
  |_::_ ->  List.fold_left(fun (sum,prod) element  -> (element+sum,element*prod)) (0,1)  list 
  |[] -> (0,0)

;;


sumProd [];;