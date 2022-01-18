package vip.bluewater.oceanix.server.components

import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import org.ktorm.schema.Table
import org.ktorm.schema.date
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import org.ktorm.support.postgresql.PostgreSqlDialect
import vip.bluewater.oceanix.server.config.appConfig
import vip.bluewater.oceanix.server.entity.User


var database: Database? = null

fun initDatabaseConnection() {
  Class.forName("org.postgresql.Driver")
  database = Database.connect(appConfig.dbConnectStr, dialect = PostgreSqlDialect())

  for (row in database!!.from(Users).select()) {
    println(row[Users.username])
  }
}

object Users: Table<Nothing>("user") {
  val id = int("id").primaryKey()
  val username = varchar("username")
  val password = varchar("password")
  val createdOn = date("created_on")
}


