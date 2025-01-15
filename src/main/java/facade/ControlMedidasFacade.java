package facade;


import entity.ControlMedidas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class ControlMedidasFacade extends AbstractFacade<ControlMedidas> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ControlMedidasFacade() {
        super(ControlMedidas.class);
    }
}
