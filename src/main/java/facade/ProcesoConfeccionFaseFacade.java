package facade;

import entity.ProcesoConfeccionFase;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Samuel P.
 */
@Stateless
public class ProcesoConfeccionFaseFacade extends AbstractFacade<ProcesoConfeccionFase> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoConfeccionFaseFacade() {
        super(ProcesoConfeccionFase.class);
    }
}
