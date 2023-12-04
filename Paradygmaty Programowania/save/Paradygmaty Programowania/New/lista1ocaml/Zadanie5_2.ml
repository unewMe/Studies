let rec isPalindrome list  =

  let rec isPalindromeHelper(list1,index) =
  
    if (list1 = []) then 
      (true,([], index - 1))
    else
    
      let helper = isPalindromeHelper(List.tl list1, index + 1) in
      if (snd(snd helper) < index) then (fst helper , (List.hd list1 :: fst(snd helper), snd(snd helper)))
      else
        if snd(snd helper) > index || (snd(snd helper) = index && index mod 2 = 1) then
          if (List.hd (fst(snd helper)) <> List.hd list1) then 
            (false,(List.tl (fst(snd helper)),snd(snd helper)))
          else (fst helper,(fst(snd helper), snd(snd helper)))
        else
          helper
    
  

in fst(isPalindromeHelper(list, 0));;
