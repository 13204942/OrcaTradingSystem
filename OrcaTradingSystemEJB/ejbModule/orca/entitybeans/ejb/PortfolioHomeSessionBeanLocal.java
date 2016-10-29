package orca.entitybeans.ejb;

import java.util.Date;
import java.util.List;

public interface PortfolioHomeSessionBeanLocal {

	public List<Object[]> getPortfolioInfo(int id, Date mostRecentDate);
	
	public void exportPortfolioInfo();
	
	public String getPortfolioName(int portfolioId);
	
	public Date getMostRecentDate();
}
