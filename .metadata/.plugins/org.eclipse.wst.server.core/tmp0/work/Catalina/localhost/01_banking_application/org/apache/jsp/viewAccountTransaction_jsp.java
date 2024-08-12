/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.80
 * Generated at: 2024-08-12 12:35:00 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.Connection;
import java.util.List;
import com.aurionpro.model.TransactionView;
import com.aurionpro.operation.TransactionViewOperation;
import com.aurionpro.database.DbConnection;

public final class viewAccountTransaction_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("java.sql.Connection");
    _jspx_imports_classes.add("com.aurionpro.model.TransactionView");
    _jspx_imports_classes.add("com.aurionpro.operation.TransactionViewOperation");
    _jspx_imports_classes.add("com.aurionpro.database.DbConnection");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>View Transactions</title>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"static/css/styles.css\">\r\n");
      out.write("    <style>\r\n");
      out.write(" a {\r\n");
      out.write("            display: block;\r\n");
      out.write("            width: 97%;\r\n");
      out.write("            padding: 10px;\r\n");
      out.write("            background-color: #007bff;\r\n");
      out.write("            color: white;\r\n");
      out.write("            text-align: center;\r\n");
      out.write("            border-radius: 4px;\r\n");
      out.write("            text-decoration: none;\r\n");
      out.write("            font-size: 16px;\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        a:hover {\r\n");
      out.write("            background-color: #0056b3;\r\n");
      out.write("        }\r\n");
      out.write("    .transaction-view-container{\r\n");
      out.write("    padding: 13px;\r\n");
      out.write("  \r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <div class=\"transaction-view-container\">\r\n");
      out.write("        <h2>View Transactions</h2>\r\n");
      out.write("        \r\n");
      out.write("        <table>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th>Transaction ID</th>\r\n");
      out.write("                <th>Receiver Account</th>\r\n");
      out.write("                <th>Type of Transaction</th>\r\n");
      out.write("                <th>Amount</th>\r\n");
      out.write("                <th>Transaction Date</th>\r\n");
      out.write("            </tr>\r\n");
      out.write("         \r\n");
      out.write("           ");

    Long selectedAccount = (Long) session.getAttribute("selectedAccount");
    try {
        Connection connection = DbConnection.connectToDb();
        TransactionViewOperation transactionViewOperation = new TransactionViewOperation(connection);
        List<TransactionView> transactionViews = transactionViewOperation.fetchTransactionsByAccountNumber(selectedAccount);
        int count=1;
        for (TransactionView transaction : transactionViews) {
            out.println("<tr>");
            out.println("<td>" + count + "</td>");
            // Use ternary operator to check for null
            String receiverAccount = transaction.getReceiverAccount() == 0 ? "self" :  Long.toString(transaction.getReceiverAccount());
            out.println("<td>" + receiverAccount + "</td>");
            out.println("<td>" + transaction.getTypeOfTransaction() + "</td>");
            out.println("<td>" + transaction.getAmount() + "</td>");
            out.println("<td>" + transaction.getTransactionDate() + "</td>");
            out.println("</tr>");
            
            count++;
        }
        
        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

      out.write("\r\n");
      out.write("\r\n");
      out.write("        </table>\r\n");
      out.write("        <a href=\"customerOperation.jsp\">Back to Dashboard</a>\r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
