(* let insertSort precedes list =
  match list with
    |[] -> []
    |h::t -> 
        let rec insertSortHelper(list1,list2) =
          match list2 with
            |[] ->list1
            |h2::t2-> 
              let rec insert(element,listPom) =
                match listPom with
                  | h::t as ys -> 
                      if precedes element h then 
                        element::ys
                      else 
                        h::(insert(element,t))
            
              in insertSortHelper(insert(h2,list1),t2)
      in insertSortHelper([h],t) 
;;              


insertSort(<=)([9;6;4;5;3]);; *)

let insertSort precedes list =

  let rec insert(element,listPom) =
    match listPom with
      | h::t as ys -> 
          if precedes element h then 
            element::ys
          else 
            h::(insert(element,t))
   and insertSortHelper(list1,list2) =
    match list2 with
      |[] ->list1
      |h2::t2-> insertSortHelper(insert(h2,list1),t2)
  in
  match list with
    |[] -> []
    |h::t -> insertSortHelper([h],t) 
;;              


let mergeSort precedes list =

  let rec merge(list1,list2) = 

    match (list1, list2) with
    | ([], l) -> l
    | (l, []) -> l
    | (h1::t1, h2::t2) ->
        if precedes h1 h2 then
          h1::merge (t1,h2::t2)
        else
          h2::merge(h1::t1,t2)

  and scalHelper lists =
    match lists with
      |[] -> []
      |h1::h2::t -> merge(h1,h2) :: scalHelper t 
      |h::t -> h :: scalHelper(t)
          

  and scal lists =
    match lists with
      |[] -> []
      |h1::h2::t -> scal(scalHelper lists)
      |[h] -> h 
      

  in scal (List.map (fun x -> [x]) list)
;;
insertSort(<=)([9;6;4;5;3]);;
mergeSort(<=)([9;6;4;5;3]);;
