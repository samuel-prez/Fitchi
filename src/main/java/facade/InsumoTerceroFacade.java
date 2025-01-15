package facade;


import entity.InsumoTercero;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class InsumoTerceroFacade extends AbstractFacade<InsumoTercero> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InsumoTerceroFacade() {
        super(InsumoTercero.class);
    }
}
