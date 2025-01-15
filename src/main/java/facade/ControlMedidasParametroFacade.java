package facade;


import entity.ControlMedidasParametro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class ControlMedidasParametroFacade extends AbstractFacade<ControlMedidasParametro> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ControlMedidasParametroFacade() {
        super(ControlMedidasParametro.class);
    }
}
