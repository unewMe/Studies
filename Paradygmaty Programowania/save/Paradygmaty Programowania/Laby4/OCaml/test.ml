let path disk toFind =
  let rec searchName list =
    match list with
    | h :: t -> 
      (match h with
      | FolderType(Folder(name, _)) when name = toFind -> Some(name)
      | FileType(File(name)) when name = toFind -> Some(name)
      | _ -> searchName t)
    | _ -> None
  in
  let rec search fld path depth =
    match fld with
    | Folder(name, filesAndFolders) -> 
      match searchName filesAndFolders with
      | Some(res) -> Some((path ^ name ^ "/" ^ res), depth)
      | None -> 
        List.fold_left (fun acc x ->
          match x with 
          | FolderType(subFolder) -> 
            (match acc with
            | Some(_, d) when d <= depth -> acc
            | _ -> search subFolder (path ^ name ^ "/") (depth + 1))
          | FileType(_) -> acc
        ) None filesAndFolders
  in
  match disk with
  | Disk(diskName, filesAndFolders) -> 
    let result = search (Folder((String.make 1 diskName)^":", filesAndFolders)) "" 0 in
    match result with
    | Some(path, _) -> Some(path)
    | None -> None
;;

let disk1 = Disk ('C', [FolderType(Folder ("Program Files", [FolderType(Folder ("F1", [FileType(File("P1.ml"))]));FileType(File("P12"))]))]);;

path disk1 "P1.ml";;