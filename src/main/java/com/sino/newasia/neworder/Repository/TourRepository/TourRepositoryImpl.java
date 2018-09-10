package com.sino.newasia.neworder.Repository.TourRepository;

import com.sino.newasia.neworder.Entity.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


public class TourRepositoryImpl {

//    <jpa:repositories base-package="com.sino.newasia.neworder.Repository.TourRepository.*"
//    repository-impl-postfix="Impl"
//    query-lookup-strategy = "create-if-not-found"
//    entity-manager-factory-ref="entityManagerFactory"
//    transaction-manager-ref="transactionManager" >
//    </jpa:repositories>


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

//    @Autowired
//    private TourRepository tourRepository;
//
//    public List<Tour> findAllByTourid(String tourid){return tourRepository.findAllByTourid(tourid);}
//    public Tour findByTourid(String tourid){return tourRepository.findByTourid(tourid);}
//    public List<Object[]>  findAllByRouteid(String routeid){return tourRepository.findAllByRouteid(routeid);}
//    public Tour save(Tour tour){return tourRepository.save(tour);}
//    public void delete(Tour tour){tourRepository.delete(tour);}


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

    @Transactional
    public int update(@Param("tour") Tour tour){
        //String sql = "update t_tour t set t.tourid = ? where t.tourid = ? ";
        String sql = "update Tour t set t.status=:status, t.departdate = :departdate, t.routeid=:routeid where t.tourid=:tourid";
        //em.getTransaction().begin();
        Query query = em.createQuery(sql);
        query.setParameter("tourid", tour.getTourid());
        query.setParameter("status", tour.getStatus());
        query.setParameter("departdate", tour.getDepartdate());
        query.setParameter("routeid", tour.getRouteid());
        query.executeUpdate();
        //em.getTransaction().commit();
        return 1;



        //Tour tour = em.find(Tour.class, 1);
        //tour.setRouteid("ROUTE2");
    }

//    @Transactional
//    public int insert(@Param("tour") Tour tour){
//        em.persist(tour);
//        return 1;
//    }
}
