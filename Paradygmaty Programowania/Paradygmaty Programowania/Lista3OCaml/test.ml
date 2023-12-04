let mergeSort precedes list =

  let rec merge(list1,list2) =

    match (list1, list2) with
    | ([], l) -> l
    | (l, []) -> l
    | (h1::t1, h2::t2) ->
        if precedes h1 h2 then
          h1 :: merge(t1,h2::t2)
        else
          h2 :: merge (h1::t1,t2)

  and scalHelper lists =
    match lists with
      | [] -> []
      | h1 :: h2 :: t -> merge(h1,h2) :: scalHelper t
      | h::t -> h :: scalHelper t


  and scal lists =
    match lists with
      | [] -> []
      | [h] -> h
      | h1 :: h2 :: t -> scal (scalHelper lists)

  in scal (List.map (fun x -> [x]) list)
;;

(* Example usage *)
let precedes a b = a < b in
let result = mergeSort precedes [4; 2; 7; 1; 9; 5] in
print_endline (String.concat "; " (List.map string_of_int result));
