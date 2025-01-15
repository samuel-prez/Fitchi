package facade;


import entity.TubularDoblez;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class TubularDoblezFacade extends AbstractFacade<TubularDoblez> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TubularDoblezFacade() {
        super(TubularDoblez.class);
    }
}
