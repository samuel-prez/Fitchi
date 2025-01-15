package facade;


import entity.ParametroPrenda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class ParametroPrendaFacade extends AbstractFacade<ParametroPrenda> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroPrendaFacade() {
        super(ParametroPrenda.class);
    }
}
