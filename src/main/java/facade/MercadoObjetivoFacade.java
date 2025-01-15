package facade;


import entity.MercadoObjetivo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class MercadoObjetivoFacade extends AbstractFacade<MercadoObjetivo> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MercadoObjetivoFacade() {
        super(MercadoObjetivo.class);
    }
}
