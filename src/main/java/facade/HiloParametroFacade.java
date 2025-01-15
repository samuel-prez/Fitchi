package facade;


import entity.HiloParametro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class HiloParametroFacade extends AbstractFacade<HiloParametro> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HiloParametroFacade() {
        super(HiloParametro.class);
    }
}
