package com.damavis.elasticity

import scopt.OptionParser

case class Arguments(environment: String = "")

case object Arguments {
  def getArguments(args: Array[String]): Option[Arguments] = {
    val parser: OptionParser[Arguments] =
      new OptionParser[Arguments]("test") {
        opt[String]("environment")
          .text("Environment. Either 'pre' or 'live'")
          .required()
          .action((in, self) => self.copy(environment = in))
      }
    parser.parse(args, Arguments())
  }
}
