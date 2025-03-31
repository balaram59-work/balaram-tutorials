package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class App {


    public static void main(String[] args) {
        try (EntityManagerFactory emf = createEntityManagerFactory("com.example")) {
            EntityManager em = emf.createEntityManager();
            Session session = em.unwrap(Session.class);
            em.getTransaction().begin();

            // Unidirectional example:
            Author author1 = new Author();
            author1.setName("J.K. Rowling");

            Book book1 = new Book();
            book1.setTitle("Harry Potter and the Philosopher's Stone");
            author1.addBook(book1);
            Book book2 = new Book();
            book2.setTitle("Harry Potter and the Chamber of Secrets");
            author1.addBook(book2);
            em.persist(author1);
            // persitance state
            author1.setName("Joanne Rowling");
            Book book5 = new Book();
            book5.setTitle("Harry Potter and the Order of Phoenix");
            author1.addBook(book5);
            //em.detach(author1);
            // author is in detached state
            Book book7 = new Book();
            book7.setTitle("Harry Potter and Deathly hallows");
            author1.addBook(book7);
            //em.flush();
            em.getTransaction().commit();


            Book book6 = new Book();
            book6.setTitle("Harry Potter and the Half blood prince");
            author1.addBook(book6);

            //em.merge(author1);
           // session.save(author1);
            System.out.println("***************");
            Author author = em.find(Author.class, 1L);
            System.out.println(author.getName());
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(book1));
            System.out.println("***************************************************************************");
            author.getBooks().forEach(b->System.out.println(b.getTitle()));
            /*TypedQuery<Author> authorsInDB = em.createQuery("SELECT a FROM Author a", Author.class);
            List<Author> resultList = authorsInDB.getResultList();
            for (Author author : resultList) {
                System.out.println(author);
            }*/
            System.out.println("***************");
//            System.out.println(emf.getPersistenceUnitUtil().getIdentifier(author1));
  //          System.out.println(emf.getPersistenceUnitUtil().isLoaded(author1));
            session.close();
            em.close();


            emf.close();
        /*    System.out.println(emf.getPersistenceUnitUtil().getIdentifier(author1));
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(author1));*/


        }


        System.out.println("Data saved successfully!");
    }
}
