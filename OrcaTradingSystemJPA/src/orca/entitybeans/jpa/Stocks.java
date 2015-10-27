package orca.entitybeans.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(StocksId.class)
@Table(name="STOCKS")
public class Stocks implements Serializable {
	
	@Id
	@Column(name="stocks_code")
	private String stocks_code;
	
	@Id
	@Column(name="stocks_date")
	private Date stocks_date;
	
	@Column(name="stock_name")
	private String stock_name;
	
	@Column(name="open_price")
	private double open_price;
	
	@Column(name="high_price")
	private double high_price;
	
	@Column(name="low_price")
	private double low_price;
	
	@Column(name="close_price")
	private double close_price;
	
	public Stocks() { }
	
	public Stocks(String stocks_code, Date stocks_date, String stock_name, double open_price, double high_price,
			double low_price, double close_price) {
		this.stocks_code = stocks_code;
		this.stocks_date = stocks_date;
		this.stock_name = stock_name;
		this.open_price = open_price;
		this.high_price = high_price;
		this.low_price = low_price;
		this.close_price = close_price;
	}
	
	public String getStocksCode() {
		return this.stocks_code;
	}
	
	public void setStocksCode(String stocks_code) {
		this.stocks_code = stocks_code;
	}
	
	public Date getStocksDate() {
		return this.stocks_date;
	}
	
	public void setStocksDate(Date stocks_date) {
		this.stocks_date = stocks_date;
	}
	
	public String getStockName() {
		return this.stock_name;
	}
	
	public void setStockName(String stock_name) {
		this.stock_name = stock_name;
	}
	
	public double getOpenPrice() {
		return this.open_price;
	}
	
	public void setOpenPrice(double open_price) {
		this.open_price = open_price;
	}
	
	public double getHighPrice() {
		return this.high_price;
	}
	
	public void setHighPrice(double high_price) {
		this.high_price = high_price;
	}
	
	public double getLowPrice() {
		return this.low_price;
	}
	
	public void setLowPrice(double low_price) {
		this.low_price = low_price;
	}
	
	public double getClosePrice() {
		return this.close_price;
	}
	
	public void setClosePrice(double close_price) {
		this.close_price = close_price;
	}

}
