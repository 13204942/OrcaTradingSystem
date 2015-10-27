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

import orca.entitybeans.ejb.AnalysisSessionBeanLocal;
import orca.entitybeans.jpa.Trades;

@WebServlet("/AnalysisTestServlet")
public class AnalysisTestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
    private AnalysisSessionBeanLocal analysisManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List<Trades> tradesList = analysisManager.getAllTrades(1);
        System.out.println("Got all trades!");
        for (Trades t : tradesList) {
        	displayPortfolio(t);
        }
        
        out.println("<html>");
        out.println("<head><title>Hello!!!</title></head>");
        out.println("<body>" +
                    "<h1>Hello world :-)</h1>" +
                    "This page was generated at " + tradesList.get(2).getStocksCode()+
                    "</body></html>");
        out.flush();
    }


    public void displayPortfolio(Trades trades) {

        System.out.println( trades.getStocksCode() + trades.getPrice() + trades.getBuy() + trades.getTimestamp());
    }
    
}