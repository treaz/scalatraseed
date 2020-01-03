import com.mchange.v2.c3p0.ComboPooledDataSource
import org.slf4j.{Logger, LoggerFactory}
import slick.jdbc.H2Profile.api._

trait C3p0ClientInit {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  val cpds = new ComboPooledDataSource
  logger.info("Created c3p0 connection pool")

  def initDbConnection(): Database = {
    Database.forDataSource(cpds, None) // create the Database object
  }

  def closeDbConnection() {
    logger.info("Closing c3po connection pool")
    cpds.close()
  }
}
