package facade;


import entity.ParametroTallajeTalla;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class ParametroTallajeTallaFacade extends AbstractFacade<ParametroTallajeTalla> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroTallajeTallaFacade() {
        super(ParametroTallajeTalla.class);
    }
}
