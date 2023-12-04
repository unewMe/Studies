let insert (list,element,pos) = 

  let rec findPos (index,pos,listPom) = 
    if pos < 0 then element :: findPos(index+1,0,listPom)
    else
      if listPom <> [] then
        if index = pos then 
          element :: findPos(index+1,pos,listPom)
        else 
          List.hd listPom :: findPos(index+1,pos,List.tl listPom)
      else 
          if index <= pos then 
            element::[] 
          else
              []
        
  in findPos(0,pos,list);;
insert([1;2;3;5],4,30);;