type 'a graph = Graph of ('a -> 'a list);;

let dfs (Graph graph)(start) =
  let rec dfsHelper visited queue =
    match queue with
    |[] -> []
    |h::t ->
    if List.mem h visited then
      dfsHelper visited t
    else 
      h :: dfsHelper(h::visited)(graph h @ t)  
  in dfsHelper[][start]      
;;

let g = Graph
(function
0 -> [3]
| 1 -> [0;2;4]
| 2 -> [1]
| 3 -> []
| 4 -> [0;2]
| n -> failwith ("Graph g: node "^string_of_int n^" doesn't exist")
);;

dfs(g)(4);;
