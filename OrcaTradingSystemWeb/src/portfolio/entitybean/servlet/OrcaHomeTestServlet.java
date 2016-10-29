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

import orca.entitybeans.ejb.OrcaHomeSessionBeanLocal;
import orca.entitybeans.jpa.Portfolio;

@WebServlet("/OrcaHomeTestServlet")
public class OrcaHomeTestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
    private OrcaHomeSessionBeanLocal portfolioManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

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
    	portfolioManager.createPortfolio("new portfolio here");
    }


    public void displayPortfolio(Portfolio portfolio) {

        System.out.println( portfolio.getName());
    }
    
}