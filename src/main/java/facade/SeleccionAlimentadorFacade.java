package facade;


import entity.SeleccionAlimentador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class SeleccionAlimentadorFacade extends AbstractFacade<SeleccionAlimentador> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeleccionAlimentadorFacade() {
        super(SeleccionAlimentador.class);
    }
}
