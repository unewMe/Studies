let rec count pairOfArr =
  if snd pairOfArr = [] then 0
  else if List.hd (snd pairOfArr) = fst pairOfArr then
    1 + count (fst pairOfArr, List.tl (snd pairOfArr))
  else
    0 + count (fst pairOfArr, List.tl (snd pairOfArr));;

count(2,[1;2;2;3]);;