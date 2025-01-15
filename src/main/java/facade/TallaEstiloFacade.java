package facade;


import entity.TallaEstilo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class TallaEstiloFacade extends AbstractFacade<TallaEstilo> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TallaEstiloFacade() {
        super(TallaEstilo.class);
    }
}
