package main

import com.damavis.elasticity.Arguments
import main.pipeline.PipelineInstance

object Main extends App {
  val arguments: Arguments = Arguments.getArguments(args).get
  new PipelineInstance(arguments).run()
}
