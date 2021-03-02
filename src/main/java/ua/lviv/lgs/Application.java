package ua.lviv.lgs;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.HashSet;

public class Application {
    public static void main(String[] args) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        Post post1 = new Post("post1");

        Comment comment1 = new Comment("smth1");
        comment1.setPost(post1);
        Comment comment2 = new Comment("smth2");
        comment2.setPost(post1);
        Comment comment3 = new Comment("smth3");
        comment3.setPost(post1);
        Comment comment4 = new Comment("smth4");
        comment4.setPost(post1);

        post1.setComments(new HashSet<>(Arrays.asList(comment1, comment2, comment3, comment4)));

        session.persist(post1);

        transaction.commit();

        Post postDB = (Post) session.get(Post.class, 1);
        System.out.println(postDB + " contains " + postDB.getComments());

        Comment commentDB = (Comment) session.get(Comment.class, 1);
        System.out.println(commentDB + " is referred to " + commentDB.getPost());

        session.close();
    }
}
