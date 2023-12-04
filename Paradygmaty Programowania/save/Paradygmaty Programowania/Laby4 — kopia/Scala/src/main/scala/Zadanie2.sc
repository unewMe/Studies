sealed trait FilesSystem[+A, +B]
case class FolderType[+A,+B](folder: A) extends FilesSystem[A, B]
case class FileType[+A,+B](file: B) extends FilesSystem[A, B]


sealed trait Files
case class File(name: String) extends Files

sealed trait Folders
case class Folder(name: String, filesAndFolders: List[FilesSystem[Folders, Files]]) extends Folders

sealed trait Disks
case class Disk(name: Char, filesAndFolders: List[FilesSystem[Folders, Files]]) extends Disks


def path(disk: Disks, toFind: String): Option[String] =
{

  def searchName(list: List[FilesSystem[Folders, Files]]): Option[String] =
  {
    list match
      case h :: t =>
        h match
          case FolderType(Folder(name, _)) if name == toFind => Some(name)
          case FileType(File(name)) if name == toFind => Some(name)
          case _ => searchName(t)
      case Nil => None
  }

  def search(folder: Folders)(path: String)(depth: Int): Option[(String,Int)] =
  {
    folder match
      case Folder(name,filesAndFolders) =>
        searchName(filesAndFolders) match
          case Some(res) => Some(path + name + "/" + res,depth)
          case None =>
            filesAndFolders.foldLeft[Option[(String,Int)]](None)((acc, x) =>
              x match
                case FolderType(subFolder) =>
                  acc match
                    case Some(_,d) if d<=depth => acc
                    case _ => search(subFolder)(path + name + "/")(depth + 1)
                case FileType(_) => acc
          )

  }

  disk match
    case Disk(name,filesAndFolders) =>
      val res = search(Folder(name.toString + ":",filesAndFolders))("")(0)
      res match
        case Some(path,_) => Some(path)
        case None => None
}



val disk1 = Disk('C',
  List(
    FolderType(Folder("Program Files",
      List(
        FolderType(Folder("F1",
          List(
            FileType(File("P1.ml"))
          )
         )),
        FileType(File("P12"))
      )
    )),
    FolderType(Folder("Program Files2",
      List(
        FolderType(Folder("F1",
          List(
            FileType(File("P2.ml"))
          ))),
        FileType(File("P12"))
      )))
  ))
val disk2 = Disk('B',List())
path(disk1, "P2.ml")
