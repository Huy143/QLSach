package nguyenquochuy.demo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nguyenquochuy.demo.entity.Category;
import nguyenquochuy.demo.validator.annotationn.ValidCategoryId;


public class ValidCategoryIdValidator  implements ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public  boolean isValid(Category category, ConstraintValidatorContext context){
        return category != null && category.getId() != null;
    }
}