package by.it.academy.testingTools.common.extensions.product;

import by.it.academy.entities.product.ModelProduct;
import by.it.academy.entities.product.Product;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InvalidProductParameterResolver implements ParameterResolver {
    public static List<ModelProduct> validProduct = Arrays.asList(
            new ModelProduct(null, "XR", 1212, 20, "IOS", "5,8",
                    "12 mp", "1200 mHr", "64 GB","default",
                    new Product(1, "iPhone")),
            new ModelProduct(null, "13", 1414, 20, "IOS", "6,2",
                    "12 mp", "1200 mHr", "256 GB","default",
                    new Product(2, "iPhone")),
            new ModelProduct(null, "11", 1313, 20, "IOS", "6,2",
                    "12 mp", "1200 mHr", "128 GB","default",
                    new Product(3, "iPhone"))
    );


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType()== ModelProduct.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return validProduct.get(new Random().nextInt(validProduct.size()));
    }
}
