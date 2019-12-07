// #!/bin/sh
// exec scala "$0" "$@"
// !#
import scala.sys.process._
import scala.language.postfixOps
import java.io.{File, FileInputStream, InputStream}

case class Job(device:String, mountPoint:String) {
  lazy val prettyName = mountPoint.split("/").last.replaceAll(" +", "_")
  override def toString = s"""Job(device = "${device}", mountPoint = "${mountPoint}", prettyName = "${prettyName}")"""

  val volumeListFile = new File(prettyName + ".flist")
  val clamFile = new File(prettyName + ".clam")

  lazy val totalFiles = genFileList

  def genFileList = {
    def count(in:InputStream):Int = {
      var ch = ' '
      var count = 0
      do {
        ch = in.read()
        if (ch == '\n') {
          val ch2 = in.read()
          if (ch2 == '/') count = count + 1
          if (ch2 < 0) return count
        }
      }
      while (ch > 0) ;
      return count
    }
    // Seq("find", mountPoint, "-type", "f") #> volumeListFile !
    val t = count(new FileInputStream(volumeListFile))
    println(t)
    // proc.!!.trim.toInt
    12
  }

// sfill
  // start clam with future
}

val jobs = {Process("mount") !!}
            .split("\n")
            .toList
            .filter(_.matches("/dev/sd[^a][0-9]+.*"))
            .map(_.split(" +"))
            .map(line => Job(line(0), line(2)))

jobs.foreach(_.totalFiles)

// println(jobs)
