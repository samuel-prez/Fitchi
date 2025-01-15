package facade;


import entity.ParametroPrendaEstilo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class ParametroPrendaEstiloFacade extends AbstractFacade<ParametroPrendaEstilo> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroPrendaEstiloFacade() {
        super(ParametroPrendaEstilo.class);
    }
}
