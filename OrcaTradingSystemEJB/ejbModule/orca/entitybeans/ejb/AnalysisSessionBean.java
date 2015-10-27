package orca.entitybeans.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import orca.entitybeans.jpa.Trades;

@Stateless
public class AnalysisSessionBean implements AnalysisSessionBeanLocal {
	
	@PersistenceContext(unitName = "OrcaEntityBeansJPA-ejbPU")
	EntityManager em;

	public AnalysisSessionBean() {
		
	}
	    
	@Override
	public List<Double> getPrices(String code) {
		TypedQuery<Double> query = em.createQuery("SELECT s.open_price FROM Stocks s WHERE s.stocks_code = :c", Double.class);
		query.setParameter("c", code);
		return (List<Double>) query.getResultList();
	}
	    
	@Override
	public Map<String, Double> getMostRecentPrices(int portfolioId) {
		Map<String, Double> resultMap = new HashMap();
	    	
		Query query = em.createNativeQuery(
					"SELECT s.stocks_code, s.open_price " +
	    			"FROM (" +
	    				"SELECT stock_code " + 
	    			    "FROM stocksowned " +
	    			    "WHERE portfolio_id = " + portfolioId +
	    			") AS so " +
	    			"INNER JOIN " +
	    			"(" +
	    				"SELECT t2.stocks_code, t2.stocks_date, t2.stock_name, t2.open_price, t2.high_price, t2.low_price, t2.close_price " +
	    			    "FROM (" +
	    					"SELECT stocks_code, open_price, MAX(stocks_date) AS recent " +
	    			        "FROM stocks GROUP BY stocks_code" +
	    				") AS t1 INNER JOIN stocks AS t2 ON t2.stocks_code = t1.stocks_code AND t2.stocks_date = t1.recent" +
	    			") AS s " +
	    			"ON so.stock_code = s.stocks_code;");
	    	
		List<Object[]> result = query.getResultList();
		for (Object[] element : result) {
			resultMap.put((String) element[0], (Double) element[1]);
		}
	    	
		return resultMap;
	}
	    
	@Override
	public List<Trades> getAllTrades(int id) {
		// TODO Auto-generated method stub
		
        Query query = em.createQuery("SELECT t FROM Trades AS t WHERE t.portfolioId = ?", Trades.class);
        query.setParameter(1, id);

        List<Trades> trades = query.getResultList();

        for (Trades trade: trades) {
            doDiagnostics("Got portfolio in getAllPortfolios()", trade);
        }
		return trades;
	}
	
    private void doDiagnostics(String message, Trades trade) {

        System.out.println(message);
        if (trade == null) {
            System.out.print("The list: Stocks is null");
        } else {
            System.out.println("Got " + trade.getPortfolioId() +" " + trade.getStocksCode() + "--" + trade.getPrice() + 
            		trade.getBuy() + "--" + trade.getTimestamp());
        }
    }
    
	@Override
    public String getPortfolioName(int portfolioId) {
    	TypedQuery<String> query = em.createQuery("SELECT p.name FROM Portfolio p WHERE p.id = :i", String.class);
    	query.setParameter("i", portfolioId);
    	return query.getSingleResult();
    }

}
