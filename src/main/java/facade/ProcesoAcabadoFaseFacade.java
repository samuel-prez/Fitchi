package facade;

import entity.ProcesoAcabadoFase;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Samuel P.
 */
@Stateless
public class ProcesoAcabadoFaseFacade extends AbstractFacade<ProcesoAcabadoFase> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoAcabadoFaseFacade() {
        super(ProcesoAcabadoFase.class);
    }
}
