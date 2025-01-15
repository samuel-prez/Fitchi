package facade;


import entity.TejidoCirculares;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class TejidoCircularesFacade extends AbstractFacade<TejidoCirculares> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TejidoCircularesFacade() {
        super(TejidoCirculares.class);
    }
}
