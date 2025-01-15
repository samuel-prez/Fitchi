package facade;


import entity.HiloTejidoCirculares;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class HiloTejidoCircularesFacade extends AbstractFacade<HiloTejidoCirculares> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HiloTejidoCircularesFacade() {
        super(HiloTejidoCirculares.class);
    }
}
