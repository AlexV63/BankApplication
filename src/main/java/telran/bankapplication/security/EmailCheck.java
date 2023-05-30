package telran.bankapplication.security;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailCheck implements Predicate<String> {
    @Override
    public boolean test(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }
}