let path disk toFind =
  let rec searchName list =
    match list with
    | h::t -> 
      begin
        match h with
        | FolderType(Folder(name, _)) | FileType(File(name)) when name = toFind -> Some name
        | _ -> searchName t
      end
    | [] -> None
  in
  let rec search (Folder(name, filesAndFolders)) depth =
    match searchName filesAndFolders with
    | Some res when depth = 0 -> Some (name ^ ":/" ^ res, depth)
    | Some res -> Some (name ^ "/" ^ res, depth)
    | None -> 
      List.fold_left (fun acc x ->
        match x with
        | FolderType subFolder -> 
            match search subFolder (depth + 1) with
            | Some (res, new_depth) ->
                match acc with
                | Some (name, acc_depth) when acc_depth = -1 -> Some (name^"/"^res, new_depth)
                | Some (_, acc_depth) when new_depth >= acc_depth -> acc
                | _ -> Some (res, new_depth)
            | None -> acc
        | _ -> acc
      ) Some(name,-1) filesAndFolders
  in
  match disk with
  | Disk (diskName, filesAndFolders) -> 
    match search (Folder (String.make 1 diskName, filesAndFolders)) 0 with
    | Some (res, _) -> Some res
    | None -> None
;;

let disc1 = Disk ('C', [FolderType(Folder ("Program Files", [FolderType(Folder ("F1", [FileType(File("P1"))]))]))]);;

path disc1 "P1";;
