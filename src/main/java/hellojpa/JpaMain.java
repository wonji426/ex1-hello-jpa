package hellojpa;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUsername("kimseoun");
            em.persist(member1);

            List<Member> result = em.createQuery(
                    "SELECT m FROM  Member m WHERE  m.username LIKE '%kim%'", Member.class
            ).getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getUsername());
            }

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> cq = cb.createQuery(Member.class);
            Root<Member> m = cq.from(Member.class);
            CriteriaQuery<Member> query = cq.select(m).where(cb.equal(m.get("username"), "kimseoun"));
            List<Member> resultList = em.createQuery(query).getResultList();

            for (Member member : resultList) {
                System.out.println("member = " + member.getUsername());
            }

            List<Member> resultList1 = em.createNativeQuery("SELECT MEMBER_ID, ENDDATE, STARTDATE, CITY, STREET, ZIPCODE, USERNAME FROM MEMBER", Member.class).getResultList();
            for (Member member2 : resultList1) {
                System.out.println("member = " + member2);
            }

            tx.commit(); //커밋시 SQL문 나감
        } catch (Exception e) {
            tx.rollback();//에러 시 롤백
        } finally {
            em.close();//닫기
        }

        emf.close();
    }
}
