package facade;

import entity.ProcesoAcabadoEtapa;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Samuel P.
 */
@Stateless
public class ProcesoAcabadoEtapaFacade extends AbstractFacade<ProcesoAcabadoEtapa> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoAcabadoEtapaFacade() {
        super(ProcesoAcabadoEtapa.class);
    }
}
