package facade;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }
    
    public void detach(T entity) {
        getEntityManager().detach(entity);
    }
    
    public void clear() {
        getEntityManager().clear();
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> findEmpleadosProduccion() {
        try {
            Query consulta = getEntityManager().createNamedQuery("Empleado.findActivosProduccion");
            List<T> resultado = consulta.getResultList();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }
    
        public List<T> findEmpleadosStaff() {
        try {
            Query consulta = getEntityManager().createNamedQuery("Empleado.findActivosStaff");
            List<T> resultado = consulta.getResultList();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }
    
    public List<T> findEmpleadosMantenimiento() {
        try {
            Query consulta = getEntityManager().createNamedQuery("Empleado.findMantenimiento");
            List<T> resultado = consulta.getResultList();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }
    
    public List<T> findEmpleadosQF() {
        try {
            Query consulta = getEntityManager().createNamedQuery("Empleado.findQF");
            List<T> resultado = consulta.getResultList();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }
    
    public List<T> findEmpTrasladosMantenimiento() {
        try {
            Query consulta = getEntityManager().createNamedQuery("Empleado.findEmpTraslados");
            List<T> resultado = consulta.getResultList();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }
    
    public List<T> findEmpTrasladosMantenimientoFirma() {
        try {
            Query consulta = getEntityManager().createNamedQuery("Empleado.findEmpTrasladosFirma");
            List<T> resultado = consulta.getResultList();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }  
    
    public List<T> findRange(int first, int pageSize, String sortField, String sortOrder, Map<String, Object> filters) {
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        javax.persistence.criteria.Root<T> entityRoot = cq.from(entityClass);
        cq.select(entityRoot);
        List<javax.persistence.criteria.Predicate> predicates = getPredicates(cb, entityRoot, filters);
        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new javax.persistence.criteria.Predicate[]{}));
        }
        if (sortField != null && sortField.length() > 0) {
            if (entityRoot.get(sortField) != null) {
                if (sortOrder.startsWith("ASC")) {
                    cq.orderBy(cb.asc(entityRoot.get(sortField)));
                }
                if (sortOrder.startsWith("DESC")) {
                    cq.orderBy(cb.desc(entityRoot.get(sortField)));
                }
            }
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(pageSize);
        q.setFirstResult(first);
        return q.getResultList();
    }

    public List<T> findRange(int first, int pageSize, Map<String, String> sortFields, Map<String, Object> filters) {
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        javax.persistence.criteria.Root<T> entityRoot = cq.from(entityClass);
        cq.select(entityRoot);
        List<javax.persistence.criteria.Predicate> predicates = getPredicates(cb, entityRoot, filters);
        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new javax.persistence.criteria.Predicate[]{}));
        }
        if (sortFields != null && !sortFields.isEmpty()) {
            for (String sortField : sortFields.keySet()) {
                if (entityRoot.get(sortField) != null) {
                    String sortOrder = sortFields.get(sortField);
                    if (sortOrder.startsWith("ASC")) {
                        cq.orderBy(cb.asc(entityRoot.get(sortField)));
                    }
                    if (sortOrder.startsWith("DESC")) {
                        cq.orderBy(cb.desc(entityRoot.get(sortField)));
                    }
                }
            }
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(pageSize);
        q.setFirstResult(first);
        return q.getResultList();
    }

    public int count(Map<String, Object> filters) {
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        javax.persistence.criteria.Root<T> entityRoot = cq.from(entityClass);
        cq.select(cb.count(entityRoot));
        List<javax.persistence.criteria.Predicate> predicates = getPredicates(cb, entityRoot, filters);
        if (predicates.size() > 0) {
            cq.where(predicates.toArray(new javax.persistence.criteria.Predicate[]{}));
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    private List<Predicate> getPredicates(CriteriaBuilder cb, Root<T> entityRoot, Map<String, Object> filters) {
        javax.persistence.metamodel.Metamodel entityModel = this.getEntityManager().getMetamodel();
        javax.persistence.metamodel.ManagedType<T> entityType = entityModel.managedType(entityClass);
        java.util.Set<javax.persistence.metamodel.EmbeddableType<?>> embeddables = entityModel.getEmbeddables();
        String fieldTypeName = null;
        // Add predicates (WHERE clauses) based on filters map
        List<javax.persistence.criteria.Predicate> predicates = new java.util.ArrayList<>();
        for (String s : filters.keySet()) {
            javax.persistence.criteria.Path<Object> pkFieldPath = null;
            if (s.contains(".")) {
                String embeddedIdField = s.split("\\.")[0];
                String embeddedIdMember = s.split("\\.")[1];
                pkFieldPath = entityRoot.get(embeddedIdField).get(embeddedIdMember);
                javax.persistence.metamodel.EmbeddableType<?> embeddableType = entityModel.embeddable(entityType.getAttribute(embeddedIdField).getJavaType());
                fieldTypeName = embeddableType.getAttribute(embeddedIdMember).getJavaType().getName();
            } else {
                pkFieldPath = entityRoot.get(s);
                fieldTypeName = entityType.getAttribute(s).getJavaType().getName();
            }
            if (pkFieldPath != null && fieldTypeName != null) {
                if (fieldTypeName.contains("String")) {
                    predicates.add(cb.like((javax.persistence.criteria.Expression) pkFieldPath, filters.get(s) + "%"));
                } else {
                    javax.persistence.criteria.Expression<?> filterExpression = getCastExpression((String) filters.get(s), fieldTypeName, cb);
                    if (filterExpression != null) {
                        predicates.add(cb.equal((javax.persistence.criteria.Expression<?>) pkFieldPath, filterExpression));
                    } else {
                        predicates.add(cb.equal((javax.persistence.criteria.Expression<?>) pkFieldPath, filters.get(s)));
                    }
                }
            }
        }
        return predicates;
    }

    private Expression<?> getCastExpression(String searchValue, String typeName, CriteriaBuilder cb) {
        javax.persistence.criteria.Expression<?> expression = null;
        switch (typeName) {
            case "short":
                expression = cb.literal(Short.parseShort(searchValue));
                break;
            case "byte":
                expression = cb.literal(Byte.parseByte(searchValue));
                break;
            case "int":
                expression = cb.literal(Integer.parseInt(searchValue));
                break;
            case "long":
                expression = cb.literal(Long.parseLong(searchValue));
                break;
            case "float":
                expression = cb.literal(Float.parseFloat(searchValue));
                break;
            case "double":
                expression = cb.literal(Double.parseDouble(searchValue));
                break;
            case "boolean":
                expression = cb.literal(Boolean.parseBoolean(searchValue));
                break;
            default:
                break;
        }
        return expression;
    }

    public T getMergedEntity(T entity) {
        if (isEntityManaged(entity)) {
            return entity;
        } else {
            return getEntityManager().merge(entity);
        }
    }

    public boolean isEntityManaged(T entity) {
        return getEntityManager().contains(entity);
    }

    public T findWithParents(T entity) {
        return entity;
    }

    public List<T> findByNamedQuery(String namedQuery, Map<String, Object> parametros) {
        try {
            Query consulta = getEntityManager().createNamedQuery(namedQuery);
            for (String key : parametros.keySet()) {
                consulta.setParameter(key, parametros.get(key));
            }
            List<T> resultado = consulta.getResultList();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }

    public List<T> findWithoutParameters(String namedQuery) {
        try {
            Query consulta = getEntityManager().createNamedQuery(namedQuery);
            List<T> resultado = consulta.getResultList();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }

    public List<T> findByNamedQueryInteger(String namedQuery, Map<Integer, Object> parametros) {
        try {
            Query consulta = getEntityManager().createNamedQuery(namedQuery);
            for (Integer key : parametros.keySet()) {
                consulta.setParameter(key, parametros.get(key));
            }
            List<T> resultado = consulta.getResultList();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }

    public double findByNamedQuerySingleResult(String namedQuery, Map<String, Object> parametros) {
        try {
            Query consulta = getEntityManager().createNamedQuery(namedQuery);
            for (String key : parametros.keySet()) {
                consulta.setParameter(key, parametros.get(key));
            }
            return ((Double) consulta.getSingleResult());

        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return 0;
    }

    public long findByNamedQuerySingleResultLong(String namedQuery, Map<String, Object> parametros) {
        try {
            Query consulta = getEntityManager().createNamedQuery(namedQuery);
            for (String key : parametros.keySet()) {
                consulta.setParameter(key, parametros.get(key));
            }
            return ((long) consulta.getSingleResult());

        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return 0;
    }

    public Integer findByNamedQuerySingleResultInteger(String namedQuery, Map<String, Object> parametros) {
        try {
            Query consulta = getEntityManager().createNamedQuery(namedQuery);
            for (String key : parametros.keySet()) {
                consulta.setParameter(key, parametros.get(key));
            }
            return ((Integer) consulta.getSingleResult());

        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return 0;
    }

    public float findByNamedQuerySingleResultFloat(String namedQuery, Map<String, Object> parametros) {
        try {
            Query consulta = getEntityManager().createNamedQuery(namedQuery);
            for (String key : parametros.keySet()) {
                consulta.setParameter(key, parametros.get(key));
            }
            return ((float) consulta.getSingleResult());

        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return 0;
    }
    
    public Object findByNamedQueryObject(String namedQuery, Map<String, Object> parametros) {
        try {
            Query consulta = getEntityManager().createNamedQuery(namedQuery);
            for (String key : parametros.keySet()) {
                consulta.setParameter(key, parametros.get(key));
            }
            Object resultado = consulta.getResultList();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }
    
     public List<T> tresObjetos(String namedQuery, Map<String, Object> parametros) {
        try {
            Query consulta = getEntityManager().createNamedQuery(namedQuery);
            for (String key : parametros.keySet()) {
                consulta.setParameter(key, parametros.get(key));
            }
            consulta.setMaxResults(3);
            List<T> resultado = consulta.getResultList();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }
     
     public List<T> cincoObjetos(String namedQuery, Map<String, Object> parametros) {
        try {
            Query consulta = getEntityManager().createNamedQuery(namedQuery);
            for (String key : parametros.keySet()) {
                consulta.setParameter(key, parametros.get(key));
            }
            consulta.setMaxResults(5);
            List<T> resultado = consulta.getResultList();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }
    
     public List<Integer> listaEnteros(String namedQuery, Map<String, Object> parametros) {
        try {
            Query consulta = getEntityManager().createNamedQuery(namedQuery);
            for (String key : parametros.keySet()) {
                consulta.setParameter(key, parametros.get(key));
            }
            List<Integer> resultado = (List<Integer>) consulta.getResultList();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }

     public T getObject(String namedQuery, Map<String, Object> parametros) {
        try {
            Query consulta = getEntityManager().createNamedQuery(namedQuery);
            for (String key : parametros.keySet()) {
                consulta.setParameter(key, parametros.get(key));
            }
             T resultado = (T)consulta.getSingleResult();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }
     
     public List<String> listaString(String namedQuery, Map<String, Object> parametros) {
        try {
            Query consulta = getEntityManager().createNamedQuery(namedQuery);
            for (String key : parametros.keySet()) {
                consulta.setParameter(key, parametros.get(key));
            }
            List<String> resultado = (List<String>) consulta.getResultList();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }
     
     public String getString(String namedQuery, Map<String, Object> parametros) {
        try {
            Query consulta = getEntityManager().createNamedQuery(namedQuery);
            for (String key : parametros.keySet()) {
                consulta.setParameter(key, parametros.get(key));
            }
            String resultado = (String) consulta.getSingleResult();
            return resultado;
        } catch (Exception ebd) {
            System.out.println("ERROR: " + ebd.getMessage());
        }
        return null;
    }
     
}
