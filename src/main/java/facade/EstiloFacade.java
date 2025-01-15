package facade;


import entity.Estilo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.ejb.Stateless;

import javax.persistence.Query;


/**
 *
 * @author Samuel P.
 */
@Stateless
public class EstiloFacade extends AbstractFacade<Estilo> {
    
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstiloFacade() {
        super(Estilo.class);
    }
    
    public List<Estilo> findActivos() {
        Query q = em.createNativeQuery("SELECT * FROM `estilo`  WHERE `ACTIVA` = 1 ORDER BY creado DESC;", Estilo.class);
        List<Estilo> listado = q.getResultList();
        return listado;
    }
}
