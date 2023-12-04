let rec replaceNth(xs,n,x) = 

  let rec replaceNthHelper(list,index) =

    match (list,index) with
      |(h::t,l) when l = n -> x :: t (*:: replaceNthHelper(List.tl list,index+1)*)
      |(h::t,_) -> List.hd list :: replaceNthHelper(List.tl list,index+1) 
      |_-> [] 
  in replaceNthHelper(xs,0);;   
  
replaceNth(["o";"l";"a";"m";"a";"k";"o";"t";"a"],7,"s")  
