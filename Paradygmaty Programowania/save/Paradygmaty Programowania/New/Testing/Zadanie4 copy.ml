let insert (list,element,pos) = 

  let rec findPos (index,listPom) = 
    if listPom <> [] then
      if index = pos then 
        element :: findPos(index+1,listPom)
      else 
        List.hd listPom :: findPos(index+1,List.tl listPom)
    else 
        if index <= pos then 
           element::[] 
        else
            []
        
  in 
    if pos < 0 then element :: findPos (0,list)
    else findPos(0,list);;

insert([1;2;3;5],4,0);;