package facade;


import entity.HiloTubular;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class HiloTubularFacade extends AbstractFacade<HiloTubular> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HiloTubularFacade() {
        super(HiloTubular.class);
    }
}
