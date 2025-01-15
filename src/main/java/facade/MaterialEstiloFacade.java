package facade;


import entity.MaterialEstilo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class MaterialEstiloFacade extends AbstractFacade<MaterialEstilo> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaterialEstiloFacade() {
        super(MaterialEstilo.class);
    }
}
