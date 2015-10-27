package orca.entitybeans.ejb;

import java.text.ParseException;

import javax.ejb.Local;

@Local
public interface TracksSessionBeanLocal {

	public Object[] getStocksInfo(Integer portId, String code);
	
	public boolean executeTrade (String code, Double price, boolean buy, int umberOfShares, Integer portId) 
			throws ParseException;
	
	public void updateStocksInTable(Integer portId, String stocksCode, boolean buy, Integer shares);
	
	public String getPortfolioName(int portfolioId);
}
