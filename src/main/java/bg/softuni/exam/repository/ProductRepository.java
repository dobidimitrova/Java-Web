package bg.softuni.exam.repository;

import bg.softuni.exam.entity.Product;
import bg.softuni.exam.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryName(CategoryNameEnum categoryName);
}
