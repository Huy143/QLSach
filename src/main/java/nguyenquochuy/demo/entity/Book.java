package nguyenquochuy.demo.entity;

import nguyenquochuy.demo.validator.annotationn.ValidCategoryId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @Size(max = 50, min = 1, message = "Title must be less than 50 characters")
    @NotEmpty(message = "Title must not be empty")
    private String title;

    @Column(name = "author")
    private String author;

    @NotNull(message = "price must not be empty")
    @Column(name = "price")
    private  Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ValidCategoryId
    private  Category category;
}
