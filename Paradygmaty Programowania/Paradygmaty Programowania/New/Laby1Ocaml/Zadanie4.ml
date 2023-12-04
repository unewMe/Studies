let insert (list,element,pos) = 

  let rec changeList (index,listPom) = 
    if listPom <> [] then
      if index = pos then 
        element :: changeList(index+1,listPom)
      else 
        List.hd listPom :: changeList(index+1,List.tl listPom)
    else 
        if index <= pos then 
           element::[] 
        else
            [] 
  in 
    if pos < 0 then 
      element :: changeList (0,list)
    else 
      changeList(0,list);;


insert([1;2;3;5],4,0);;
insert([1;2;3;5],4,4);;
insert([1;2;3;5],4,3);;

insert([1;2;3;5],4,-7);;
insert([1;2;3;5],4,7);;

insert([],4,0);;
insert([],4,1);;
insert([],4,-1);;

