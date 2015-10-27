package orca.entitybeans.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import orca.entitybeans.jpa.Portfolio;

@Stateless
public class OrcaHomeSessionBean implements OrcaHomeSessionBeanLocal {
	
	@PersistenceContext(unitName = "OrcaEntityBeansJPA-ejbPU")
	EntityManager em;
	
	public OrcaHomeSessionBean() {
		
	}

	@Override 
	public List<Portfolio> getAllPortfolios() {
		// TODO Auto-generated method stub
        TypedQuery<Portfolio> query = em.createQuery("SELECT p FROM Portfolio AS p", Portfolio.class);

        // Execute the query, and get a collection of beans back.
        List<Portfolio> portfolios = query.getResultList();

        for (Portfolio portfolio: portfolios) {
            doDiagnostics("Got portfolio in getAllPortfolios()", portfolio);
        }

        return portfolios;
	}
	
    private void doDiagnostics(String message, Portfolio portfolio) {

        System.out.println(message);
        if (portfolio == null) {
            System.out.print("Portfolio is null");
        } else {
            System.out.println("Got " + portfolio.getName() );
        }
    }

	@Override
	public boolean createPortfolio(String portfolioName) {
		// TODO Auto-generated method stub
        Portfolio newPortfolio = new Portfolio();
        newPortfolio.setName(portfolioName);
        em.persist(newPortfolio);
        System.out.println("Just persisted " + newPortfolio.getName() + " with ID " + newPortfolio.getId());

		return false;
	}

}
