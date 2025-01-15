package facade;


import entity.MaquinaConfeccion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class MaquinaConfeccionFacade extends AbstractFacade<MaquinaConfeccion> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaquinaConfeccionFacade() {
        super(MaquinaConfeccion.class);
    }
}
