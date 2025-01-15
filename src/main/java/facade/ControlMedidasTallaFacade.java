package facade;


import entity.ControlMedidasTalla;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class ControlMedidasTallaFacade extends AbstractFacade<ControlMedidasTalla> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ControlMedidasTallaFacade() {
        super(ControlMedidasTalla.class);
    }
}
