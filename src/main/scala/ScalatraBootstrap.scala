import com.horiaconstantin.app._
import javax.servlet.ServletContext
import org.scalatra._

class ScalatraBootstrap extends LifeCycle with C3p0ClientInit {

  //  Routes are matched from the bottom up
  override def init(context: ServletContext): Unit = {
    val db = initDbConnection()
    context.mount(new MyScalatraServlet, "/*")
    context.mount(new APIServlet, "/api/*")
    context.mount(new ChatController, "/chat/*")
    context.mount(new SlickApp(db), "/slick/*")
    context.mount(new HTTPClientServlet, "/httpclient/*")
  }


  override def destroy(context: ServletContext): Unit = {
    super.destroy(context)
    closeDbConnection()
  }
}
