package facade;


import entity.PrefijadoObs;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class PrefijadoObsFacade extends AbstractFacade<PrefijadoObs> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrefijadoObsFacade() {
        super(PrefijadoObs.class);
    }
}
