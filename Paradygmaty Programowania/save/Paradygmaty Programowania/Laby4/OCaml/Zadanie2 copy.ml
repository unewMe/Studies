type file = File of string;;
type folder = Folder of string * folder list * file list;; 
type disk = Disk of char * folder list * file list;;

let path disk toFind : Option[string] = 
  let searchFile fileList : Option[string] =
    match fileList with
    |File(name):: t -> if name = toFind then Some(name) else searchFile t
    |_ -> None
  and searchFolder folderList : Option[string] =
    match folderList with
    |Folder(name,_,_):: t -> if name = toFind then Some(name) else searchFolder t
    |_ -> None
  let pathHelper folderList depth : Option[string] = 
    if depth = 0 then 
      let res = searchFile()



let disc1 = Disk ('C', [Folder ("Program Files", [Folder ("F1", [], [File "P1"])], [])], []);;
