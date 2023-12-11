package bg.softuni.exam.entity;

import bg.softuni.exam.entity.enums.CategoryNameEnum;
import jakarta.persistence.*;



@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }

}