package campfut;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        // Inicializa a fábrica de EntityManager para a unidade de persistência "lab02"
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab02");
        EntityManager em = emf.createEntityManager();

        // Inicia uma transação (necessária para operações no banco)
        em.getTransaction().begin();

        // Aqui você pode realizar operações, como persistir uma entidade
        // Exemplo: Campeonato campeonato = new Campeonato(1, 2025, "Copa", new ArrayList<>(), new ArrayList<>());
        // em.persist(campeonato);

        // Comita a transação
        em.getTransaction().commit();

        // Fecha os recursos
        em.close();
        emf.close();
    }
}