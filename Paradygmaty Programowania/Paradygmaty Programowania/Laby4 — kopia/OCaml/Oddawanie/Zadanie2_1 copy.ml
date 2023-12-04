type filesSystem  = File of string | Folder of string * filesSystem list;;
type disk = Disk of char * filesSystem list;;

let pathF dsk toFind =
  let rec searchName list =
    match list with
    | h :: t -> 
      (match h with
      | Folder(name, _) when name = toFind -> Some(name)
      | File(name) when name = toFind -> Some(name)
      | _ -> searchName t)
    | _ -> None
  and searchHelper filesAndFolders path name depth =
      match searchName filesAndFolders with
      | Some(res) -> Some((path ^ name ^ "/"), depth)
      | None -> 
        List.fold_left (fun acc x ->
          match x with 
          | Folder(_) -> 
            (match acc with
            | Some(_, acc_depth) -> let result = searchFolder x (path ^ name ^ "/") (depth + 1) acc_depth in
              (match result with
              | Some(_, d) when d < acc_depth -> result
              | _ -> acc)  
            | _ -> searchFolder x (path ^ name ^ "/") (depth + 1) (depth + 2))
          | File(_) -> acc
        ) None filesAndFolders
  and searchFolder fld path depth res_depth =
    if res_depth <= depth then None else
      match fld with
      | Folder(name,filesAndFolders) -> searchHelper filesAndFolders path name depth 
  and searchDisk dsk path depth =
    match dsk with  
    | Disk(name, filesAndFolders) -> searchHelper filesAndFolders path ((String.make 1 name)^":") depth

        
  in
  let result = searchDisk (dsk) "" 0 in
    match result with
    | Some(path, _) -> Some(path)
    | None -> None
;;

let disk2 = Disk('B',[]);;
(* let disk1 = Disk ('C', [FolderType(Folder ("Program Files", [FolderType(Folder ("F1", [FolderType(Folder("F29",[]));FileType(File("P2.ml"))]));FolderType(Folder("F2",[]));FileType(File("P3.ml"))]));FolderType(Folder ("Program Files2", [FolderType(Folder ("F1", [FileType(File("P3.ml"))]));FileType(File("P211.ml"));FolderType(Folder("F3",[FileType(File("P6.ml"))]))]));FileType(File("P1.ml"))]);; ;FolderType(Folder ("Program Files3", [FolderType(Folder ("F1", [FileType(File("P31.ml"))]));FolderType(Folder("F2",[]));FileType(File("P1.ml"))])) *)
let disk1 = Disk ('C', 
[
  Folder ("Program Files", 
  [
    Folder ("F1", 
    [
      Folder("F29",[]);
      File("P2.ml")
    ]
    );
    Folder("F2",[]);
    File("P3.ml")
  ]
   );

  Folder ("Program Files2", 
  [
    Folder ("F1", 
    [
      File("P3.ml")
    ]
    );
    File("P2.ml");
    Folder("F3",
    [
      File("P6.ml")
    ]
    )
  ]
  );
  File("P1.ml")
]
);; 
pathF  disk1 "F29";;
pathF  disk1 "P2.ml";;
pathF  disk1 "P132.ml";;
pathF  disk1 "";;
pathF  disk2 "";;

(* let disk2 = Disk('B',[]);;
let disk3 = Disk ('C', [FolderType(Folder ("Program Files", [FolderType(Folder ("F1", [FileType(File("P2"))]))]));FolderType(Folder ("Program Files", [FolderType(Folder ("F1", [FileType(File("P1"))]))]))]);;
path disk3 "P2";; *)