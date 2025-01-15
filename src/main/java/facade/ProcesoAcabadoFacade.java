package facade;

import entity.ProcesoAcabado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Samuel P.
 */
@Stateless
public class ProcesoAcabadoFacade extends AbstractFacade<ProcesoAcabado> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoAcabadoFacade() {
        super(ProcesoAcabado.class);
    }
}
