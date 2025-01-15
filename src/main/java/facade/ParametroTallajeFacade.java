package facade;


import entity.ParametroTallaje;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class ParametroTallajeFacade extends AbstractFacade<ParametroTallaje> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroTallajeFacade() {
        super(ParametroTallaje.class);
    }
}
