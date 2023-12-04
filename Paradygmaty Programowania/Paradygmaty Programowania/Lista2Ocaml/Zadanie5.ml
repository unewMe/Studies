let rec initSegment (xs,ys) =
  match (xs,ys) with
  |([],_) -> true
  |(h1::t1,h2::t2) when h1 = h2 -> initSegment(t1,t2)
  |_ -> false
;;

initSegment([1;2;3;3],[1;2;3;3;5])