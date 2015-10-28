package orca.entitybeans.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="TRADES")
public class Trade implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
	private int id;
	
	@Column(name="portfolio_id")
	private int portfolioID;
	
	@Column(name="price")
	private double price;
	
	@Column(name="buy")
	private boolean buy;
	
	@Column(name="shares")
	private int shares;
	
	@Column(name="trade_timestamp")
	private Date timestamp;
	
	@Column(name="stock_code")
	private String stockCode;
	private static final long serialVersionUID = 1L;

	public Trade() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getPortfolioID() {
		return this.portfolioID;
	}

	public void setPortfolioID(int portfolioID) {
		this.portfolioID = portfolioID;
	}   
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}   
	public boolean getBuy() {
		return this.buy;
	}

	public void setBuy(boolean buy) {
		this.buy = buy;
	}   
	public int getShares() {
		return this.shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}   
	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getStockCode() {
		return this.stockCode;
	}
	
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	
	@Override
	public String toString() {
		String buyOrSell;
		if (buy)
			buyOrSell = "bought";
		else
			buyOrSell = "sold";
		String returnString = stockCode + " had " + shares + " shares " + buyOrSell + " at " + price + " per share at time " + timestamp;
		return returnString;
	}
   
}
