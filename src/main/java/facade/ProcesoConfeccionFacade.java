package facade;

import entity.ProcesoConfeccion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Samuel P.
 */
@Stateless
public class ProcesoConfeccionFacade extends AbstractFacade<ProcesoConfeccion> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcesoConfeccionFacade() {
        super(ProcesoConfeccion.class);
    }
}
