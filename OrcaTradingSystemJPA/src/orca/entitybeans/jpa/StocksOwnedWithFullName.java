package orca.entitybeans.jpa;

public class StocksOwnedWithFullName {

    public String fullName;
    public StocksOwned stocksOwned;
 
    public StocksOwnedWithFullName(String fullName, StocksOwned stocksOwned) {
        this.fullName = fullName;
        this.stocksOwned = stocksOwned;
    }
    
    public void setFullName(String fullName) {
    	this.fullName = fullName;
    }
    
    public String getFullName() {
    	return fullName;
    }
    
    public void setStocksOwned(StocksOwned stocksOwned) {
    	this.stocksOwned = stocksOwned;
    }
    
    public StocksOwned getStocksOwned() {
    	return stocksOwned;
    }
}
