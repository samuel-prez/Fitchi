package facade;


import entity.TallaEtiqueta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class TallaEtiquetaFacade extends AbstractFacade<TallaEtiqueta> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TallaEtiquetaFacade() {
        super(TallaEtiqueta.class);
    }
}
