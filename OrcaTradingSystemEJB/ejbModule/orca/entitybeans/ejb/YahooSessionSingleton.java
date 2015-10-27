package orca.entitybeans.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Singleton
@Startup
public class YahooSessionSingleton {

	@PersistenceContext(unitName = "PortfolioEntityBeansJPA-ejbPU")
	EntityManager em;
	
	@Resource
    TimerService timerService;
	
    public YahooSessionSingleton() { }
    
    @Schedule(minute = "*", second = "*", dayOfMonth = "*", month = "*", year = "*", hour = "*", dayOfWeek = "*", persistent=false)
    public void scheduledLoad() {
    	//System.out.println("BLUUUUH");
    	//Poll Yahoo
    }

}
