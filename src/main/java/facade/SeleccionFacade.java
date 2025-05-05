package facade;


import entity.Seleccion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class SeleccionFacade extends AbstractFacade<Seleccion> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeleccionFacade() {
        super(Seleccion.class);
    }
}
