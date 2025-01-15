package facade;

import entity.ProcesoConfeccionEtapa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Samuel P.
 */
@Stateless
public class ProcesoConfeccionEtapaFacade extends AbstractFacade<ProcesoConfeccionEtapa> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoConfeccionEtapaFacade() {
        super(ProcesoConfeccionEtapa.class);
    }
}
