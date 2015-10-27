package orca.entitybeans.ejb;

import java.util.List;

import javax.ejb.Local;

import orca.entitybeans.jpa.Portfolio;

@Local
public interface OrcaSessionBeanLocal {
	
	public List<Portfolio> getAllPortfolios();
	
	public boolean createPortfolio(String portfolioName);
	
}
