package portfolio.entitybean.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import orca.entitybeans.ejb.OrcaHomeSessionBeanLocal;
import orca.entitybeans.ejb.PortfolioHomeSessionBeanLocal;
import orca.entitybeans.ejb.TracksSessionBeanLocal;
import orca.entitybeans.jpa.Portfolio;
import orca.entitybeans.jpa.Stocks;
import orca.entitybeans.jpa.StocksOwned;
import orca.entitybeans.jpa.StocksOwnedWithFullName;

@WebServlet("/TradesTestServlet")
public class TradesTestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
    private TracksSessionBeanLocal tracksSessionBeanManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Object[] stocksOwnedWithPrice = tracksSessionBeanManager.getStocksInfo(1, "AAPL");
        displayStocksInfo((StocksOwned) stocksOwnedWithPrice[0], (Double) stocksOwnedWithPrice[1]);
        
        out.println("<html>");
        out.println("<head><title>Hello!!!</title></head>");
        out.println("<body>" +
                    "<h1>Hello world :-)</h1>" +
                    "This page was generated at " + stocksOwnedWithPrice[0] + (Double)stocksOwnedWithPrice[1] 
                    		+ (String)stocksOwnedWithPrice[2] + 
                    "</body></html>");
        out.flush();
    }


    public void displayStocksInfo( StocksOwned stocksOwned, Double price ) {

        System.out.println("Got " + stocksOwned.getStocksCode() + "--" + price +
        		"--" + stocksOwned.getSharesOwned());
    }
    
}