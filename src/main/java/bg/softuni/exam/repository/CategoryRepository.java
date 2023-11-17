package bg.softuni.exam.repository;

import bg.softuni.exam.entity.Category;
import bg.softuni.exam.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByName(CategoryNameEnum name);
}
