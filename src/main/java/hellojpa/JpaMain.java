package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member1 = new Member();
            member1.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("B");

            Member member3 = new Member();
            member3.setUsername("C");

            System.out.println("====================");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            System.out.println("member1.Id = " + member1.getId());
            System.out.println("member2.Id = " + member2.getId());
            System.out.println("member3.Id = " + member3.getId());

            System.out.println("====================");

            tx.commit(); //커밋시 SQL문 나감
        } catch (Exception e) {
            tx.rollback();//에러 시 롤백
        } finally {
            em.close();//닫기
        }

        emf.close();
    }
}
