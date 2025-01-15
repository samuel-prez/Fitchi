package facade;


import entity.PlanchaTintoreria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class PlanchaTintoreriaFacade extends AbstractFacade<PlanchaTintoreria> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanchaTintoreriaFacade() {
        super(PlanchaTintoreria.class);
    }
}
