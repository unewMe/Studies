let rec replicate (element,counter) =
  if counter = 0 then []
  else element :: replicate(element,counter-1);;

replicate("la",3);;