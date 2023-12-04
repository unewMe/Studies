type file = File of string;;
type folder = Folder of string * folder list * file list;; 
type disk = Disk of char * folder list * file list;;

(* let path disk toFind : Option[string] =
  let search folder : Option[string] =
    match folder with
    |Folder(_,folist,filist) ->
      let folderContains folist = 
        match folist with
        |Folder(name,_,_) :: t -> if name = toFind then Some(name) else folderContains t
        |_ -> None
      and fileContains filist = 
        match filist with
        |Folder(name,_,_) :: t -> if name = toFind then Some(name) else folderContains t
        |_ -> None
      in 
      let res = folderContains folist
      if res = None then fileContains filist else res   
  let rec pathHelper folderList acc : Option[string] = 
    match folderList with 
      h::t -> let res = search h if res = Some(name) then acc^name else  *)

let rec find_path_in_folders name folders path = 
  match folders with
  | [] -> None
  | Folder(folder_name, subfolders, files)::rest ->
    if folder_name = name then Some (path ^ folder_name)
    else match find_path_in_folders name subfolders (path ^ folder_name ^ "\\") with
         | Some p -> Some p
         | None -> find_path_in_folders name rest path

let find_path_in_files name files path =
  List.find_all (fun (File file_name) -> file_name = name) files
  |> Option.map (fun _ -> path ^ name)

let find_path (Disk (disk_letter, folders, files)) name =
  let path = Char.escaped disk_letter ^ ":\\" in
  match find_path_in_files name files path with
  | Some p -> Some p
  | None -> find_path_in_folders name folders path
;;     


let disc1 = Disk ('C', [Folder ("Program Files", [Folder ("F1", [], [File "P1"])], [])], []);;
