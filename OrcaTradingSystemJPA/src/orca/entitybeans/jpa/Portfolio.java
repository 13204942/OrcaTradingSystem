package orca.entitybeans.jpa;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PORTFOLIO")
public class Portfolio implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Collection<StocksOwned> stocksOwned;
    private Collection<Trades> tradesList;
    
    public Portfolio() {
    	
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

    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="Name")
    public String getName() {
        return name;
    }
    
    public void setStocksOwned(Collection<StocksOwned> stocksOwned) {
    	this.stocksOwned = stocksOwned;
    }
    
    @OneToMany(mappedBy="portfolio", cascade=CascadeType.ALL)
    public Collection<StocksOwned> getStocksOwned() {
    	return stocksOwned;
    }
    
    public void setTradesList(Collection<Trades> tradesList) {
    	this.tradesList = tradesList;
    }
    
    @OneToMany(mappedBy="portfolio", cascade=CascadeType.ALL)
    public Collection<Trades> getTradesList() {
    	return tradesList;
    }
}
