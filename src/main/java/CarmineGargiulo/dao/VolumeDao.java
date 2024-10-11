package CarmineGargiulo.dao;

import CarmineGargiulo.entities.Volume;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class VolumeDao {
    private final EntityManager entityManager;

    public VolumeDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveVolume(Volume volume){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(volume);
        transaction.commit();
        System.out.println("Volume " + volume.getTitle() + " has been successfully saved");
    }

    public List<Volume> getAllVolums(){
        TypedQuery<Volume> query = entityManager.createNamedQuery("allVolums", Volume.class);
        return query.getResultList();
    }
}
