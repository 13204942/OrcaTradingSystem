package orca.entitybeans.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import orca.entitybeans.jpa.Trade;

@Local
public interface AnalysisSessionBeanLocal {
	
	public List<Double> getPrices(String code);
	
	public Map<String, Double> getMostRecentPrices(int portfolioId);
	
	public List<Trade> getAllTrades(int portfolioId);

}
