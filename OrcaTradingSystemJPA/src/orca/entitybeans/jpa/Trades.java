package orca.entitybeans.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TRADES")
public class Trades implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String stocksCode;
    private Double price;
    private boolean buy;
    private Integer shares;
    private Date timestamp;
    private Integer portfolioId;
    private Portfolio portfolio;
    
    public Trades() {
    	
    }
    
    public void setId(Integer id) {
    	this.id = id;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    public Integer getId() {
        return id;
    }
    
    public void setStocksCode(String stocksCode) {
    	this.stocksCode = stocksCode;
    }
    
    @Column(name="Stocks_code")
    public String getStocksCode() {
    	return stocksCode;
    }
    
    public void setPrice(Double price) {
    	this.price = price;
    }
    
    @Column(name="Price")
    public Double getPrice() {
    	return price;
    }
    
    public void setBuy(boolean buy) {
    	this.buy = buy;
    }
    
    @Column(name="Buy")
    public boolean getBuy() {
    	return buy;
    }
    
    public void setShares(Integer shares) {
    	this.shares = shares;
    }
    
    @Column(name="Shares")
    public Integer getShares() {
    	return shares;
    }
    
    public void setTimestamp(Date timestamp) {
    	this.timestamp = timestamp;
    }
    
    @Column(name="Timestamp")
    public Date getTimestamp() {
    	return timestamp;
    }
    
    public void setPortfolioId(Integer portfolioId) {
    	this.portfolioId = portfolioId;
    }
    
    @Column(name="Portfolio_id")
    public Integer getPortfolioId() {
    	return portfolioId;
    }
    
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
    
    @ManyToOne
    public Portfolio getPortfolio() {
        return portfolio;
    }
}
