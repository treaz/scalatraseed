import com.horiaconstantin.app._
import javax.servlet.ServletContext
import org.scalatra._
import org.scalatra.metrics.MetricsBootstrap
import org.scalatra.metrics.MetricsSupportExtensions._

class ScalatraBootstrap extends LifeCycle with C3p0ClientInit with MetricsBootstrap {

  //  Routes are matched from the bottom up
  override def init(context: ServletContext): Unit = {
    val db = initDbConnection()
    context.mount(new MyScalatraServlet, "/*")
    context.mount(new APIServlet, "/api/*")
    context.mount(new ChatController, "/chat/*")
    context.mount(new DatabaseController(db), "/db/*")
    context.mount(new HTTPClientServlet, "/httpclient/*")

    context.mountMetricsAdminServlet("/metrics-admin")
    context.mountHealthCheckServlet("/health")
    context.mountMetricsServlet("/metrics")
    context.mountThreadDumpServlet("/thread-dump")
    context.installInstrumentedFilter("/test/*")
  }


  override def destroy(context: ServletContext): Unit = {
    super.destroy(context)
    closeDbConnection()
  }
}
