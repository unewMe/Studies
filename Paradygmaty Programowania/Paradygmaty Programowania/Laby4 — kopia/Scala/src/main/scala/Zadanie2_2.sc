sealed trait FilesSystem
case class File(name: String) extends FilesSystem
case class Folder(name: String, filesAndFolders: List[FilesSystem]) extends FilesSystem

sealed trait Disks
case class Disk(name: Char, filesAndFolders: List[FilesSystem]) extends Disks

val disk1 = Disk('C',
  List(
    Folder("Program Files",
      List(
        Folder("F1",
          List(
            File("P1.ml")
          )
         ),
        File("P12")
      )
    ),
    Folder("Program Files2",
      List(
        Folder("F1",
          List(
            File("P2.ml")
          )),
        File("P12")
      ))
  ))
val disk2 = Disk('B',List())
