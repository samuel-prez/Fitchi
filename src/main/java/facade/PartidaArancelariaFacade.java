package facade;


import entity.PartidaArancelaria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class PartidaArancelariaFacade extends AbstractFacade<PartidaArancelaria> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PartidaArancelariaFacade() {
        super(PartidaArancelaria.class);
    }
}
