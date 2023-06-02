package nguyenquochuy.demo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nguyenquochuy.demo.repository.IUserRepository;
import nguyenquochuy.demo.validator.annotationn.ValidUsername;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidUsernameValidator implements ConstraintValidator <ValidUsername,String> {
    @Autowired
    private IUserRepository userReponsitory;

    @Override
    public boolean isValid (String username, ConstraintValidatorContext context){
        if (userReponsitory == null)
            return true;
        return userReponsitory.findByUsername(username) == null;
    }

}


