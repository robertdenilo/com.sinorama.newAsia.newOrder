package com.sino.newasia.neworder.Repository.TestJPARepository;

import com.sino.newasia.neworder.Entity.Test_JPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class JPARepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public List<Test_JPA> callStore(String name) {
        StoredProcedureQuery store = this.em.createNamedStoredProcedureQuery("proc1");
        store.setParameter("_name", name);
        store.execute();
        return store.getResultList();
    }
}
