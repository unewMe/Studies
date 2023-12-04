let rec flatten1 listOfLists =
  if listOfLists = [] then [] 
  else 
    List.hd listOfLists @ flatten1 (List.tl listOfLists);;

flatten1 [[1; 2]; [2; 3]];;