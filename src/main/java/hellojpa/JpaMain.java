package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            /* INSERT
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            em.persist(member);
             */

            /* FIND
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
             */


            /* UPDATE
            findMember.setName("HelloJPA");
             */


            /* DELETE
            em.remove(findMember);
             */

            /* SELECT 쿼리문 조회
            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
             */

            /* INSERT
            // 비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            // 영속(DB에 바로 쿼리가 날라가지 않음)
            // 1차 캐시에 저장됨
            System.out.println("=== BEFORE ===");
            em.persist(member);
            System.out.println("=== AFTER ===");
             */

            /* 1차 캐시에서 꺼내온 다는 것을 알 수 있다.
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);
            System.out.println("findMember.id = " + findMember1.getId());
            System.out.println("findMember.name = " + findMember1.getName());

            System.out.println("result = " + (findMember1 == findMember2));
             */

            Member findMember = em.find(Member.class, 150L);
            findMember.setName("Z");

            System.out.println(" ======================= ");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
