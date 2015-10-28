package orca.entitybeans.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import orca.entitybeans.jpa.Portfolio;
import orca.entitybeans.jpa.Stocks;
import orca.entitybeans.jpa.Trade;

/**
 * Session Bean implementation class AnalysisSessionBean
 */
@Stateless
public class AnalysisSessionBean implements AnalysisSessionBeanLocal {

	@PersistenceContext(unitName = "PortfolioEntityBeansJPA-ejbPU")
	EntityManager em;
	
    public AnalysisSessionBean() { }
    
    public String getPortfolioName(int portfolioId) {
    	TypedQuery<String> query = em.createQuery("SELECT p.name FROM Portfolio p WHERE p.id = :i", String.class);
    	query.setParameter("i", portfolioId);
    	return query.getSingleResult();
    }
    
    public List<Double> getPrices(String code) {
    	TypedQuery<Double> query = em.createQuery("SELECT s.open_price FROM Stocks s WHERE s.stocks_code = :c", Double.class);
    	query.setParameter("c", code);
    	return (List<Double>) query.getResultList();
    }
    
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
    
    
    public List<Trade> getAllTrades(int portfolioId) {
    	TypedQuery<Trade> query = em.createQuery("SELECT t FROM Trade t WHERE t.portfolioID = :p", Trade.class);
    	query.setParameter("p", portfolioId);
    	return (List<Trade>) query.getResultList();
    }

}
