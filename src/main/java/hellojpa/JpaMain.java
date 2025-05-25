package hellojpa;

import jakarta.persistence.*;
public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("Jack");
            member.setHomeAddress(new Address("city", "street", "12345"));
            member.setWorkPeriod(new Period());

            em.persist(member);

            tx.commit(); //커밋시 SQL문 나감
        } catch (Exception e) {
            tx.rollback();//에러 시 롤백
        } finally {
            em.close();//닫기
        }

        emf.close();
    }
}
