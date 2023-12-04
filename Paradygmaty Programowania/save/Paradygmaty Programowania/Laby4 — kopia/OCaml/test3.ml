type ('a, 'b) filesAndFolders = FolderType of 'a | FileType of 'b;;

type file = File of string;;
type folder = Folder of string * (folder, file) filesAndFolders list;;
type disk = Disk of char * (folder, file) filesAndFolders list;;

let path disk toFind =
  let rec search fld path depth =
    match fld with
    | Folder(name, filesAndFolders) -> 
      let newPath = path ^ name ^ "/" in
      List.fold_left (fun acc x ->
        match x with 
        | FileType(File(fname)) when fname = toFind -> Some(newPath ^ fname, depth)
        | FolderType(subFolder) -> 
          (match acc with
          | Some(_, d) when d <= depth -> acc
          | _ -> search subFolder newPath (depth + 1))
        | _ -> acc
      ) None filesAndFolders
  in
  match disk with
  | Disk(diskName, filesAndFolders) -> 
    search (Folder(String.make 1 diskName ^ ":", filesAndFolders)) "" 0
;;

let disk1 = Disk ('C', [FolderType(Folder ("Program Files", [FolderType(Folder ("F1", [FileType(File("P1.ml"))])); FileType(File("P2.ml"))])); FolderType(Folder ("Program Files2", [FolderType(Folder ("F1", [FileType(File("P3.ml"))])); FileType(File("P1.ml"))]))]);;
path disk1 "P2.ml";;
