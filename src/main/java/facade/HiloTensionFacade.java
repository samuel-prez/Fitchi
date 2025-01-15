package facade;


import entity.HiloTension;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class HiloTensionFacade extends AbstractFacade<HiloTension> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HiloTensionFacade() {
        super(HiloTension.class);
    }
}
