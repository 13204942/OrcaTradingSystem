package orca.entitybeans.jpa;

import java.io.Serializable;
import java.util.Date;

public class StocksId implements Serializable {
	private String stocks_code;
	private Date stocks_date;
	
	public StocksId() { }
	
	public StocksId(String stocks_code, Date stocks_date) {
		this.stocks_code = stocks_code;
		this.stocks_date = stocks_date;
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
	
	public boolean equals(Object o) {
		if(o == null)
            return false;

        if(!(o instanceof StocksId))
            return false;

        StocksId id = (StocksId) o;
        if(!(getStocksCode().equals(id.getStocksCode())))
            return false;

        if(!(getStocksDate().equals(id.getStocksDate())))
            return false;

        return true;
	}
	
	public int hashCode(){
    	return super.hashCode();
    }
}
