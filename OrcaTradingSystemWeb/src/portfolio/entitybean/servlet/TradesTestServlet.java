package portfolio.entitybean.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import orca.entitybeans.ejb.TracksSessionBeanLocal;

@WebServlet("/TradesTestServlet")
public class TradesTestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
    private TracksSessionBeanLocal tracksSessionBeanManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Object[] stocksOwnedWithPrice = tracksSessionBeanManager.getStocksInfo(1, "AAPL");
        displayStocksInfo("AAPL", (Integer) stocksOwnedWithPrice[0], (Double) stocksOwnedWithPrice[1]);
        
        out.println("<html>");
        out.println("<head><title>Hello!!!</title></head>");
        out.println("<body>" +
                    "<h1>Hello world :-)</h1>" +
                    "This page was generated at " + (Integer) stocksOwnedWithPrice[0]
                    		+ stocksOwnedWithPrice[0] + (Double)stocksOwnedWithPrice[1] +
                    "</body></html>");
        out.flush();
    }


    public void displayStocksInfo( String stocksCode, Integer shares, Double price ) {

        System.out.println("Got " + stocksCode + "--" + price + "--" + shares);
    }
    
}