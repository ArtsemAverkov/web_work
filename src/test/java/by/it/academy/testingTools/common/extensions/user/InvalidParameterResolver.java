package by.it.academy.testingTools.common.extensions.user;

import by.it.academy.entities.user.User;
import by.it.academy.entities.user.UserType;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class InvalidParameterResolver implements ParameterResolver {
    public static List<User> validUser = Arrays.asList(
            new User(null, "qwerty", "123", "by", "academy", "@mail.ru",
                    Collections.singleton(new UserType(1, "USER"))),
            new User(null, "artsem", "345", "by", "academy", "@mail.ru",
                    Collections.singleton(new UserType(2, "USER"))),
            new User(null, "anton", "678", "by", "academy", "@mail.ru",
                    Collections.singleton(new UserType(3, "USER")))
    );
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == User.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return validUser.get(new Random().nextInt(validUser.size()));
    }
}
