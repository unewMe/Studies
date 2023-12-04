sealed trait FilesAndFolders[+A, +B]
case class FolderType[+A,+B](folder: A) extends FilesAndFolders[A, B]
case class FileType[+A,+B](file: B) extends FilesAndFolders[A, B]


sealed trait FileT
case class File(name: String) extends FileT

sealed trait FolderT
case class Folder(name: String, filesAndFolders: List[FilesAndFolders[FolderT, FileT]]) extends FolderT

sealed trait DiskT
case class Disk(name: Char, filesAndFolders: List[FilesAndFolders[FolderT, FileT]]) extends DiskT


def path(disk: DiskT, toFind: String): Option[String] =
{

  def searchName(list: List[FilesAndFolders[FolderT, FileT]]): Option[String] =
  {
    list match
      case h :: t =>
        h match
          case FolderType(Folder(name, _)) if name == toFind => Some(name)
          case FileType(File(name)) if name == toFind => Some(name)
          case _ => searchName(t)
      case Nil => None
  }

  def search(folder: FolderT)(path: String)(depth: Int): Option[(String,Int)] =
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



val disk1 = Disk('C', List(FolderType(Folder("Program Files", List(FolderType(Folder("F1", List(FileType(File("P1.ml"))))), FileType(File("P12"))))),FolderType(Folder("Program Files2", List(FolderType(Folder("F1", List(FileType(File("P2.ml"))))), FileType(File("P12")))))))
val disk2 = Disk('B',List())
path(disk1, "P2.ml")
