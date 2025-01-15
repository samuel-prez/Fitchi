package facade;


import entity.ParametroTallajeAlista;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author Samuel P.
 */
@Stateless
public class ParametroTallajeAlistaFacade extends AbstractFacade<ParametroTallajeAlista> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroTallajeAlistaFacade() {
        super(ParametroTallajeAlista.class);
    }
}
