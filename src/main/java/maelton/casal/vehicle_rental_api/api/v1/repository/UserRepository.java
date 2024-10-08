package maelton.casal.vehicle_rental_api.api.v1.repository;

import maelton.casal.vehicle_rental_api.api.v1.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(UUID id);
    Optional<User> findUserByEmail(String email);
    void deleteById(UUID id);
    void deleteByEmail(String email);

    Boolean existsUserById(UUID id);
    Boolean existsUserByEmail(String email);
}
