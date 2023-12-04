let rec listLength list =
  if list = [] then 0 
  else 1 + listLength(List.tl list);;

listLength[1;2;2;3];;