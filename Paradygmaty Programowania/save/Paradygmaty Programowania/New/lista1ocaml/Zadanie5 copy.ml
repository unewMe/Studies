(* let palindrome list =
   let rec reverse listP =
      let rec reverseHelper(listPom,listR) =
         if listPom = [] then listR
         else reverseHelper((List.tl listPom), (List.hd :: listR))
      in reverseHelper(listP,[])
   in 
   let listReversed = reverse list
   in
   list = listReversed;;


palindrome[1;2;2;1;3];; *)
let palindrome list =
   let rec reverse listP =
      let rec reverseHelper (listPom, listR) =
         if listPom = [] then listR
         else reverseHelper (List.tl listPom, List.hd listPom :: listR)
      in reverseHelper (listP, [])
   in 
   let listReversed = reverse list in
   list = listReversed;;

palindrome [1;2;2;1;3];;