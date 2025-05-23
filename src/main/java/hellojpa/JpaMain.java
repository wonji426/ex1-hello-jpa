package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Team team2 = new Team();
            team.setName("TeamB");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.ChangeTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.ChangeTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();

//            Member m = em.find(Member.class, member1.getId());

            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();

            tx.commit(); //커밋시 SQL문 나감
        } catch (Exception e) {
            tx.rollback();//에러 시 롤백
            e.printStackTrace();
        } finally {
            em.close();//닫기
        }

        emf.close();
    }
}
