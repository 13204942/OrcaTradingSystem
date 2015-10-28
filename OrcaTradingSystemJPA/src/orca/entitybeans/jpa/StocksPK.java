package orca.entitybeans.jpa;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class StocksPK implements java.io.Serializable {
    private String stocksCode;
    private Date stocksDate;


    public StocksPK() {
	}

	public StocksPK(String code, Date stocksDate) {
		this.stocksCode = code;
		this.stocksDate = stocksDate;
	}

	public void setStocksCode(String stocksCode) {
		this.stocksCode = stocksCode;
	}

	public String getStocksCode() {
		return stocksCode;
	}

	public int hashCode() {
		return stocksCode.hashCode() + stocksDate.hashCode();
	}

	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof StocksPK)) return false;
		if (obj == null) return false;
		StocksPK pk = (StocksPK) obj;
		return pk.stocksCode.equals(stocksCode) && pk.stocksDate.equals(stocksDate);
	}
}

