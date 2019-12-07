#!/bin/sh
exec scala "$0" "$@"
!#
import sys.process._

import scala.language.postfixOps

val mountData = "mount" !!

println(mountData)

println("foo")
