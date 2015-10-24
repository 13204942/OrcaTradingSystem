package portfolio.entitybean.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import orca.entitybeans.ejb.OrcaSessionBeanLocal;
import orca.entitybeans.jpa.Portfolio;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
    private OrcaSessionBeanLocal portfolioManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get all teams.
        List<Portfolio> portfolios = portfolioManager.getAllPortfolios();
        System.out.println("Got all portfolios!");
        for (Portfolio t: portfolios) {
        	displayPortfolio(t);
        }
        
        out.println("<html>");
        out.println("<head><title>Hello!!!</title></head>");
        out.println("<body>" +
                    "<h1>Hello world :-)</h1>" +
                    "This page was generated at " + portfolios.get(2).getName()+
                    "</body></html>");
        out.flush();
    }


    public void displayPortfolio(Portfolio portfolio) {

        System.out.println( portfolio.getName());
    }
    
}