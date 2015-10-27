package orca.entitybeans.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@IdClass(StocksPK.class)
@Table(name="STOCKS")
public class Stocks implements Serializable {

    private static final long serialVersionUID = 1L;

    private String stocksCode;
    private String name;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Date stocksDate;
    
    public Stocks() {
    	
    }
    
    public void setStocksCode(String stocksCode) {
    	this.stocksCode = stocksCode;
    }
    
    @Id
    @Column(name="Stocks_code")
    public String getStocksCode() {
    	return stocksCode;
    }
    
    public void setName(String name) {
    	this.name =  name;
    }
    
    @Column(name="Name")
    public String getName() {
    	return name;
    }
    
    public void setOpen(Double open) {
    	this.open = open;
    }
    
    @Column(name="Open")
    public Double getOpen() {
    	return open;
    }
    
    public void setHigh(Double high) {
    	this.high = high;
    }
    
    @Column(name="High")
    public Double getHigh() {
    	return high;
    }
    
    public void setLow(Double low) {
    	this.low = low;
    }
    
    @Column(name="Low")
    public Double getLow() {
    	return low;
    }
    
    public void setClose(Double close) {
    	this.close = close;
    }
    
    @Column(name="Close")
    public Double getClose() {
    	return close;
    }
    
    public void setStocksDate(Date stocksDate) {
    	this.stocksDate = stocksDate;
    }
    
    @Id
    @Column(name="Stocks_date")
    public Date getStocksDate() {
    	return stocksDate;
    }

}
