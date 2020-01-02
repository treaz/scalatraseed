import com.horiaconstantin.app._
import javax.servlet.ServletContext
import org.scalatra._

class ScalatraBootstrap extends LifeCycle {
  //  Routes are matched from the bottom up
  override def init(context: ServletContext) {
    context.mount(new MyScalatraServlet, "/*")
    context.mount(new APIServlet, "/api/*")
  }
}
