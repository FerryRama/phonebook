package co.id.ferry.phonebook.repository;

import co.id.ferry.phonebook.model.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface phonebookRepository extends JpaRepository<ContactEntity, String> {

    @Query(value = "SELECT * from LIST_PHONEBOOK t " +
            "where t.ID = :ID", nativeQuery = true)
    public List<ContactEntity> findAllContact(
            @Param("ID") int ID);
}
