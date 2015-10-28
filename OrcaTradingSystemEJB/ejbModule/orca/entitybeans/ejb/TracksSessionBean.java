package orca.entitybeans.ejb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import orca.entitybeans.jpa.StocksOwned;
import orca.entitybeans.jpa.Trades;

@Stateless
public class TracksSessionBean implements TracksSessionBeanLocal {
	
	boolean newTradeOk = false;
	Integer portfolioIdToInsert = 0;
	String stockCode = null;
	Integer unmOfShares = 0;
	Double openPrice = 0.00;
	
	@PersistenceContext(unitName = "OrcaEntityBeansJPA-ejbPU")
	EntityManager em;
	
	public TracksSessionBean() {
		
	}

	@Override
	public Object[] getStocksInfo(Integer portId, String stocksCode) {
		
		Object[] stocksOwnedWithPrice = new Object[2];
		// TODO Auto-generated method stub
        Query query = em.createNativeQuery("SELECT so.Shares_owned, s.Open FROM stocksowned AS so "
        		+ "JOIN stocks AS s ON so.Stocks_code = s.Stocks_code "
        		+ "WHERE so.Portfolio_id = :id "
        		+ "AND so.Stocks_code = :code "
        		+ "AND s.Stocks_date = (SELECT MAX(Stocks_date) FROM stocks) ");
        query.setParameter("id", portId);
        query.setParameter("code", stocksCode);
        
        List<Object[]> stocksOwnedWithPriceList = query.getResultList();
        System.out.println(stocksOwnedWithPriceList);

        for (Object[] objects: stocksOwnedWithPriceList) {
        	unmOfShares = (Integer) objects[0]; 
        	openPrice = (Double) objects[1];
            doDiagnostics("Got stock info", stocksCode, openPrice, unmOfShares);
        }
        
        portfolioIdToInsert = portId;
        stockCode = stocksCode;

        return stocksOwnedWithPrice;
	}

	@Override
	public boolean executeTrade(String code, Double price, boolean buy, int umberOfShares, Integer portfolioId) throws ParseException {
		// TODO Auto-generated method stub
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date todayDate = new Date();
		String dateWithFormat = dateFormat.format(todayDate);
		
        Trades newTrade = new Trades();
        newTrade.setStocksCode(code);;
        newTrade.setPrice(price);
        newTrade.setBuy(buy);
        newTrade.setShares(umberOfShares);
        newTrade.setTimestamp(dateFormat.parse(dateWithFormat));
        newTrade.setPortfolioId(portfolioId);
        em.persist(newTrade);
        System.out.println("Just persisted " + newTrade.getStocksCode() + " with ID " + newTrade.getId());
		
        newTradeOk = true;
        
		return newTradeOk;
	}
	

	@Override
	public void updateStocksInTable(Integer portId, String stocksCode, boolean buy, Integer shares) {
		// TODO Auto-generated method stub
		if(newTradeOk) {
			
			StocksOwned stocksOwned = new StocksOwned();
			Integer newShares = 0;
			
	        Query getQuery = em.createQuery("SELECT so FROM StocksOwned so "
		    		+ "WHERE so.portfolioId = :id "
		    		+ "AND so.stocksCode = :code");
	        getQuery.setParameter("id", portId);
	        getQuery.setParameter("code", stocksCode);
	        
	        stocksOwned = (StocksOwned)getQuery.getSingleResult();
			
			if(buy) {
				newShares = stocksOwned.getSharesOwned() + shares;
			}
			else {
				newShares = stocksOwned.getSharesOwned() - shares;
			}
			
		    Query query = em.createQuery("UPDATE StocksOwned so SET so.sharesOwned = :newShares "
		    		+ "WHERE so.portfolioId = :id "
		    		+ "AND so.stocksCode = :code");
			query.setParameter("newShares", newShares);
			query.setParameter("id", portId);
			query.setParameter("code", stocksCode);

		    query.executeUpdate();
		    em.getTransaction().commit();
			
			System.out.println("Just persisted Stock ID: " + stockCode + " with " + unmOfShares);
		}
	}
	
    private void doDiagnostics(String message, String code, Double price, Integer shares) {

        System.out.println(message);
        if (code == null) {
            System.out.print("The stock is null");
        } else {
            System.out.println("Got " + code + "--" + price + "--" + shares);
        }
    }

	@Override
	public String getPortfolioName(int portfolioId) {
		// TODO Auto-generated method stub
		String portName = null;
		
		Query query = em.createQuery("SELECT p.name FROM Portfolio p WHERE p.portfolioId = ?");
		query.setParameter(1, portfolioId);
		
        List<String> portNames = query.getResultList();
        for(String name : portNames) {
        	portName = name;
        }
		return portName;
	}

}
