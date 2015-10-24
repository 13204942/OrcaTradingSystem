package orca.entitybeans.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import orca.entitybeans.jpa.Portfolio;

@Stateless
public class OrcaSessionBean implements OrcaSessionBeanLocal {
	
	@PersistenceContext(unitName = "PortfolioEntityBeansJPA-ejbPU")
	EntityManager em;
	
	public OrcaSessionBean() {
		
	}

	@Override
	public List<Portfolio> getAllPortfolios() {
		// TODO Auto-generated method stub
        TypedQuery<Portfolio> query = em.createQuery("SELECT t FROM Portfolio AS t", Portfolio.class);

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
		Portfolio portfolio = new Portfolio();
		portfolio.setName("Super Swans");
        em.persist(portfolio);
        System.out.println("Just persisted " + portfolio.getName() + " with ID " + portfolio.getId());

		return false;
	}

}
