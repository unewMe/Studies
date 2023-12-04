let rec select list indexList =
  let rec selectPom list indexList =
    if list <> [] && indexList <> [] then
      if List.hd indexList >= 0 && List.hd list <> [] then
        selectHelper (List.hd list) (List.hd indexList) 0 :: selectPom (List.tl list) (List.tl indexList)
      else
        selectPom (List.tl list) (List.tl indexList)
    else
      []
  and selectHelper list pos index =
    if list <> [] then
      if index = pos then [List.hd list]
      else
        if List.tl list <> [] then
          selectHelper (List.tl list) pos (index + 1)
        else
          []
    else
      []
  in
  let rec removeEmpty list =
    if list <> [] then
      if List.hd list <> [] then List.hd (List.hd list) :: removeEmpty (List.tl list)
      else removeEmpty (List.tl list)
    else
      []
  in
  removeEmpty (selectPom list indexList)
;;

let result = select [[1;5;3;8]; [2;7;4;1]] [4; 2];;