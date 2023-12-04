type ('a,'b) filesAndFolders = FolderType of 'a| FileType of 'b
type file = File of string;;
type folder = Folder of string * filesAndFolders list;; 
type disk = Disk of char * filesAndFolders list;;


let disc1 = Disk ('C',[ FolderType(Folder ("Program Files", [FolderType(Folder ("F1",[FileType(File("P1"))]))]))]);;         

(* let path disk toFind : Option[string] = 
  let rec pathHelper folderList acc : Option[string] = 
    match folderList with 
      h::t -> let res = search h if res = Some(name) then acc^name else  *)



  
(* let disc1 = Disk ('C', [Folder ("Program Files", [Folder ("F1", [], [File "P1"])], [])], []);; *)
