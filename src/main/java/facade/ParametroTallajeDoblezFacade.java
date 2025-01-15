package facade;


import entity.ParametroTallajeDoblez;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class ParametroTallajeDoblezFacade extends AbstractFacade<ParametroTallajeDoblez> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroTallajeDoblezFacade() {
        super(ParametroTallajeDoblez.class);
    }
}
