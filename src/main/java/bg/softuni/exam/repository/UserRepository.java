package bg.softuni.exam.repository;

import bg.softuni.exam.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsernameAndPassword(String username,String password);

    String findByUsername(String username);
}
