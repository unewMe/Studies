type ('a, 'b) filesAndFolders = FolderType of 'a| FileType of 'b;;

type file = File of string;;
type folder = Folder of string *(folder,file) filesAndFolders list;; 
type disk = Disk of char * (folder, file) filesAndFolders list;;

let pathF (dsk,toFind) =
  let rec searchName list =
    match list with
    | h :: t -> 
      (match h with
      | FolderType(Folder(name, _)) when name = toFind -> Some(name)
      | FileType(File(name)) when name = toFind -> Some(name)
      | _ -> searchName t)
    | _ -> None
  and search (fld,path,depth,res_depth) =
    if res_depth <= depth then None else
      match fld with
      | Folder(name, filesAndFolders) -> 
        match searchName filesAndFolders with
        | Some(res) -> Some((path ^ name ^ "/"), depth)
        | None -> 
          List.fold_left (fun acc x ->
            match x with 
            | FolderType(subFolder) -> 
              (match acc with
              | Some(_, acc_depth) -> let result = search (subFolder,(path ^ name ^ "/"),(depth + 1),acc_depth) in
                (match result with
                | Some(_, d) when d < acc_depth -> result
                | _ -> acc)  
              | _ -> search (subFolder,(path ^ name ^ "/"),(depth + 1),(depth + 2)))
            | FileType(_) -> acc
          ) None filesAndFolders
      | _ -> None   
  in
  match dsk with
  | Disk(name, filesAndFolders) -> 
    let result = search ((Folder((String.make 1 name)^":", filesAndFolders)),"",0,1) in
    match result with
    | Some(path, _) -> Some(path)
    | None -> None
;;


let disk1 = Disk ('C', 
[FolderType(Folder ("Program Files", 
  [FolderType(Folder ("F1", 
    [FolderType(Folder("F29",[]));FileType(File("P2.ml"))]
   ));
   FolderType(Folder("F2",[]));
   FileType(File("P3.ml"))]
   ));

   FolderType(Folder ("Program Files2", 
    [FolderType(Folder ("F1", [FileType(File("P3.ml"))]));
     FileType(File("P2.ml"));
     FolderType(Folder("F3",
      [FileType(File("P6.ml"))]
     ))]
   ));
   FileType(File("P1.ml"))]
);; 

let disk2 = Disk('B',[]);;

pathF (disk1,"F29");;
pathF (disk1,"P2.ml");;
pathF (disk1,"P132.ml");;
pathF (disk1,"");;
pathF (disk2,"");;



(* let disk2 = Disk('B',[]);;
let disk1 = Disk ('C', [FolderType(Folder ("Program Files", [FolderType(Folder ("F1", [FileType(File("P2.ml"))]));FolderType(Folder("F2",[]));FileType(File("P3.ml"))]));FolderType(Folder ("Program Files2", [FolderType(Folder ("F1", [FileType(File("P2.ml"))]));FileType(File("P2.ml"));FolderType(Folder("F3",[FileType(File("P3.ml"))]))]));FileType(File("P1.ml"))]);; (*;FolderType(Folder ("Program Files3", [FolderType(Folder ("F1", [FileType(File("P31.ml"))]));FolderType(Folder("F2",[]));FileType(File("P1.ml"))])) *)
pathF (disk1,"P1.ml");; *)

(* let disk2 = Disk('B',[]);;
let disk3 = Disk ('C', [FolderType(Folder ("Program Files", [FolderType(Folder ("F1", [FileType(File("P2"))]))]));FolderType(Folder ("Program Files", [FolderType(Folder ("F1", [FileType(File("P1"))]))]))]);;
path disk3 "P2";; *)