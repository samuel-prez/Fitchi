package facade;


import entity.GuiaTallas;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;


/**
 *
 * @author Samuel P.
 */
@Stateless
public class GuiaTallasFacade extends AbstractFacade<GuiaTallas> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GuiaTallasFacade() {
        super(GuiaTallas.class);
    }
    
}
