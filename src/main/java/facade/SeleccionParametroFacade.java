package facade;


import entity.SeleccionParametro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class SeleccionParametroFacade extends AbstractFacade<SeleccionParametro> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeleccionParametroFacade() {
        super(SeleccionParametro.class);
    }
}
