let rec replicate pairOfArr =
  if snd pairOfArr = 0 then []
  else fst pairOfArr :: replicate(fst pairOfArr,snd pairOfArr - 1);;

replicate("la",3);;