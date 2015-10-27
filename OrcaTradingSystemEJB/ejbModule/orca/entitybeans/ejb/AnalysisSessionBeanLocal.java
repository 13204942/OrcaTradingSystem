package orca.entitybeans.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import orca.entitybeans.jpa.Trades;

@Local
public interface AnalysisSessionBeanLocal {
	
	public List<Trades> getAllTrades(int portfolioId);
	
	public String getPortfolioName(int portfolioId);
	
	public List<Double> getPrices(String code);
	
	public Map<String, Double> getMostRecentPrices(int portfolioId);
}
