package orca.entitybeans.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PortfolioHomeSessionBean implements PortfolioHomeSessionBeanLocal{
	

	@PersistenceContext(unitName = "OrcaEntityBeansJPA-ejbPU")
	EntityManager em;
	
	public PortfolioHomeSessionBean() {
		
	}
	
	@Override
	public List<Object[]> getPortfolioInfo(int id, Date mostRecentDate) {
		// TODO Auto-generated method stub
        Query query = em.createNativeQuery("SELECT s.Stocks_code, s.Name, s.Open, so.Shares_owned FROM stocks AS s JOIN "
        		+ "(SELECT Stocks_code, Shares_owned FROM stocksowned WHERE Portfolio_id = :id) AS so "
        		+ "ON s.Stocks_code = so.Stocks_code "
        		+ "WHERE s.Stocks_date = :date");
        query.setParameter("id", id);
        query.setParameter("date", mostRecentDate);
        
        List<Object[]> portfolioInfo = query.getResultList();

        for (Object[] objects: portfolioInfo) {
        	String stocksCode = (String)objects[0];
        	String stocksName = (String)objects[1];
        	Double openPrice = (Double)objects[2];
        	Integer numOfShares = (Integer)objects[3];
            doDiagnostics("Got portfolio info", stocksCode, stocksName, openPrice, numOfShares);
        }

        return portfolioInfo;
	}
	
    private void doDiagnostics(String message, String code, String name, Double price, Integer shares) {

        System.out.println(message);
        if (name == null) {
            System.out.print("The list: portfolio info is null");
        } else {
            System.out.println("Got " + code + "--" + name + "--" + price + "--" + shares);
        }
    }

	@Override
	public void exportPortfolioInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPortfolioName(int portfolioId) {
		// TODO Auto-generated method stub
		String portName = null;
		
		Query query = em.createQuery("SELECT p.name FROM Portfolio p WHERE p.id = ?");
		query.setParameter(1, portfolioId);
		
        List<String> portNames = query.getResultList();
        for(String name : portNames) {
        	portName = name;
        }
		return portName;
	}
	
	@Override
	public Date getMostRecentDate() {
        Query query = em.createQuery("SELECT MAX(s.stocksDate) FROM Stocks s ");
		return (Date)query.getSingleResult();
	}

}
