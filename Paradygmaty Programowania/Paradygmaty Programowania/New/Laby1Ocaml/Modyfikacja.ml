let rec findMaxIndex list =
  if list <> [] then
    let rec findMaxIndexPom(list,maxValue,index) = 

      if list <> [] then
        if List.hd(list) = maxValue then

          let pair = findMaxIndexPom(List.tl(list),maxValue,index+1) in
          if(fst pair = maxValue) then
            (fst pair,index::snd(pair))
          else
            (fst pair,snd pair)
        else
          if List.hd(list) > maxValue then
            findMaxIndexPom(list,List.hd(list),index)
          else
            findMaxIndexPom(List.tl(list),maxValue,index+1)
      else 
        (maxValue,[])
      in snd ( findMaxIndexPom (list,List.hd(list),0) ) 
  else
    [];;
findMaxIndex [-1;-5;-7;-1];;
findMaxIndex [];;
findMaxIndex [1];;
findMaxIndex [1;5;3;8;2;7;8;1;8;5;5;4;3;8;7;6];;