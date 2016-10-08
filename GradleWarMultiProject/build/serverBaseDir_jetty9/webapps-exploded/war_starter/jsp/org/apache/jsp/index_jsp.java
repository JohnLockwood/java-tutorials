package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.example.Message;
import com.example2.LocalMessage;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
 String title = "It worked!"; 
      out.write("\n");
      out.write("<html>\n");
      out.write("\t<head>\n");
      out.write("\t\t<title>");
      out.print( title);
      out.write("</title>\n");
      out.write("\t</head>\n");
      out.write("\t<body>\n");
      out.write("\t\t<h1>");
      out.print( title);
      out.write("</h1>\n");
      out.write("\t\t<p>This sample jsp file is located in src/main/webapp/index.jsp.</p>\n");
      out.write("\t\t<p>Test a relative link to a file located in <a href=\"jsp/testrelative.jsp\">src/main/webapp/index.jsp</a></p>\n");
      out.write("\t\t<p>Test a message coming from the jar_dependency project.  Sure, calling a service class directly\n");
      out.write("\t\tfrom a JSP is not the coolest design, but we all know this is just a sample app, right?</p>\n");
      out.write("\t\t<p>The Message class in the dependency jar (src/main/webapp/index.jsp) says this:</p>\n");
      out.write("\t\t<p><strong>");
      out.print( new Message().getMessage() );
      out.write(".</strong></p>\n");
      out.write("\t\t<p>The message class in our local war (src/main/java/com/example2/LocalMessage.java) says:</p>\n");
      out.write("\t\t<p><strong>");
      out.print( new LocalMessage().getMessage() );
      out.write(".</strong></p>\n");
      out.write("\t\t<h2>Things to Try</h2>\n");
      out.write("\t\t<p>You should already be running this using:  <span style=\"font-style:italic;\"> gradle -t -q jettyRunWar</span> \n");
      out.write("\t\t\tusing Gradle 3.0 or later.</p>\n");
      out.write("\t\t<p>Assuming all that's true, while the server is running (yeah, that's right, don't\n");
      out.write("\t\t\trestart it) go ahead and edit the JSP or any of the files mentioned on this page.  A second or two after you\n");
      out.write("\t\tsave the file you should see your changes reflected on the page.</p>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
