package facade;


import entity.ParametroTallajeDimension;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class ParametroTallajeDimensionFacade extends AbstractFacade<ParametroTallajeDimension> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroTallajeDimensionFacade() {
        super(ParametroTallajeDimension.class);
    }
}
