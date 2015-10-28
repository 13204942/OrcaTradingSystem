package portfolio.entitybean.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import orca.entitybeans.jpa.Portfolio;
import orca.entitybeans.jpa.Stocks;
import orca.entitybeans.jpa.StocksOwned;
import orca.entitybeans.jpa.StocksOwnedWithFullName;

@WebServlet("/PortfoliInfo")
public class PortfolioInfoTestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
    private PortfolioHomeSessionBeanLocal portfolioHomeManager;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        Date mostRecentDate  = portfolioHomeManager.getMostRecentDate();
        System.out.println(mostRecentDate);

        List<Object[]> portfolioInfo = portfolioHomeManager.getPortfolioInfo(1,mostRecentDate);
        System.out.println("Got portfolio 1 information!");
        for (Object[] objects: portfolioInfo) {
        	String stocksCode = (String)objects[0];
        	String stocksName = (String)objects[1];
        	Double openPrice = (Double)objects[2];
        	Integer numOfShares = (Integer)objects[3];
        	displayPortfolio(stocksCode, stocksName, openPrice, numOfShares);
        }
        System.out.println(portfolioHomeManager.getPortfolioName(1));
        
        out.println("<html>");
        out.println("<head><title>Hello!!!</title></head>");
        out.println("<body>" +
                    "<h1>Hello world :-)</h1>" +
                    "This page was generated at " + "stocksOwnedWithName" +
                    "</body></html>");
        out.flush();
    }


    public void displayPortfolio(String code, String name, Double price, Integer shares) {

        System.out.println("Got " + code + "--" + name + "--" + price + "--" + shares);
    }
    
}