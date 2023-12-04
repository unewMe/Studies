type file = {name : string};;
type folder = {name : string; folders : folder list; files : file list};; 
type disc = {name : char; folders : folder list; files : file list};;


let path disc =
  

let disc1 = {name = 'C'; folders = [{name = "Program Files"; folders = [{name = "F1"; folders = []; files = [{name = "P1"}]}]; files = []}]; files = []};;