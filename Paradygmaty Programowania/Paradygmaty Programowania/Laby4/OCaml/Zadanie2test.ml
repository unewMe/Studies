type ('a, 'b) filesAndFolders = FolderType of 'a| FileType of 'b;;

type file = File of string;;
type folder = Folder of string *(folder,file) filesAndFolders list;; 
type disk = Disk of char * (folder, file) filesAndFolders list;;


let path disk toFind =
  let rec searchName list =
    match list with
    |h::t -> 
      match h with
        |FolderType(Folder(name,filesAndFolders)) when name = toFind -> Some(name)
        |FileType(File(name)) when name = toFind -> Some(name)
        |_ -> searchName t
    |_ -> None
  and search fld depth =
    match fld with
      Folder(name,filesAndFolders) -> 
        let x = searchName filesAndFolders 
        in
        match x with
          |Some(res) when depth = 0 -> Some((name ^ ":/" ^ res),depth)
          |Some(res) -> Some((name ^ "/" ^ res),depth)
          |None ->  (List.fold_left (fun acc x -> (
            match x with 
              |FolderType(Folder(name,filesAndFolders)) -> 
                match search (Folder(name,filesAndFolders)) (depth + 1) with
                  |Some(res,depth) when acc = None -> Some(res,depth)
                  |Some(res,depth) when depth < snd acc -> Some(res,depth)
                  |_ -> acc
          ) None filesAndFolders))
    in
    match disk with
      Disk(name,filesAndFolders) -> 
        match search (Folder(name,filesAndFolders)) 0 with
          (res,depth) -> res      


let disc1 = Disk ('C', [FolderType(Folder ("Program Files", [FolderType(Folder ("F1", [FileType(File("P1"))]))]))]);;
