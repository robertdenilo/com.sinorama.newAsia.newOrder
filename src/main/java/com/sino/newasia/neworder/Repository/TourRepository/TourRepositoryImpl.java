package com.sino.newasia.neworder.Repository.TourRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class TourRepositoryImpl {

//    @PersistenceContext
//    private EntityManager em;
//    public Page<Object[]> getByCondition(UserQueryModel u){
//        String hql = "select o.uuid,o.name from UserModel o where 1=1 and o.uuid=:uuid";
//        Query q = em.createQuery(hql);
//        q.setParameter("uuid", u.getUuid());
//        q.setFirstResult(0);
//        q.setMaxResults(1);
//        Page<Object[]> page = new PageImpl<Object[]>(q.getResultList(),new PageRequest(0,1),3);
//        return page;
//    }



    @PersistenceContext
    private EntityManager em;
    //@Test
    public List<Object[]> doTest() {
        String sql = "select p.file_no from t_passenger p where p.file_no = ? ";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1,"F0827-2018-CHSB-15A-ACF-M9");

        //QueryImpl impl = query.unwrap(QueryImpl.class);
        //impl.setResultClass(Map.class);
        List<Object[]> list = query.getResultList();
        return list;
    }
}
