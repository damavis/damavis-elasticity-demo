package com.damavis.elasticity.repository.resource

import org.apache.spark.sql.DataFrame

trait FileResource {
  def read(path: String): DataFrame
  def write(path: String, data: DataFrame): Unit
}
