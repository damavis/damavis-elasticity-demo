package com.damavis.elasticity.repository

import org.apache.spark.sql.DataFrame

trait Repository {
  def getData: DataFrame
  def writeElasticity(data: DataFrame): Unit
}
