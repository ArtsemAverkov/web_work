package by.it.academy.testingTools.common.junit;


import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;



import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testcontainers.shaded.org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AcceptedMediaTypesTest {
    private static final MediaType[] SUPPORTED_MEDIA_TYPES = new MediaType[]{MediaType.APPLICATION_JSON,
    MediaType.APPLICATION_XML};

    @TestFactory
    Collection<DynamicTest> createControllerTests(){
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(new DefaultListableBeanFactory(),
                false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(RestController.class));
        Set<BeanDefinition> definitions = scanner.findCandidateComponents("by.it.academy");
        Set<Object> controllers = new HashSet<>();
        Set<Class<?>>controllersClasses = new HashSet<>();
        addController(definitions, controllers, controllersClasses);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controllers.toArray()).build();
        List<DynamicTest> generationTest = new ArrayList<>();
        acceptedXMLANDJSONMediaTypeInResController(controllersClasses, mockMvc, generationTest);
        return generationTest;
    }

    private void acceptedXMLANDJSONMediaTypeInResController(Set<Class<?>> controllerClasses, MockMvc mockMvc,
                                                            List<DynamicTest> generatedTests){
        for (Class<?> controllerClazz : controllerClasses){
            RequestMapping mapping = controllerClazz.getAnnotationsByType(RequestMapping.class)[0];
            for (Method method : controllerClazz.getMethods()){
                if (method.isAnnotationPresent(PostMapping.class)){
                    PostMapping postMapping = method.getAnnotationsByType(PostMapping.class)[0];
                    String methodMapping = postMapping.value().length > 0 ? postMapping.value()[0] : StringUtils.EMPTY;
                    for (MediaType mediaType : SUPPORTED_MEDIA_TYPES){
                        generatedTests.add(DynamicTest.dynamicTest(getDisplayName(mapping.value()[0], methodMapping, mediaType),
                                () -> mockMvc.perform(post(mapping.value()[0]).contentType(mediaType))
                                        .andExpect(status().is(IsNot.not(415)))));
                    }
                }
            }
        }
    }

    private String getDisplayName(String builder, String methodMapping, MediaType mediaType){
        return builder + methodMapping + ": " + mediaType;
    }


    private void addController(Set<BeanDefinition> beanDefinitions, Set<Object> controllers,
                               Set<Class<?>> controllersClasses){
        beanDefinitions
                .forEach(beanDefinition -> {
                    String className = beanDefinition.getBeanClassName();
                    Class<?> controllerClazz;
                    try{
                        controllerClazz = ClassLoader.getSystemClassLoader().loadClass(className);
                        controllersClasses.add(controllerClazz);
                        Constructor<?> constructor = controllerClazz.getDeclaredConstructors()[0];
                        Object[] arguments= new Object[constructor.getParameterTypes().length];
                        int i = 0;
                        for (Class<?> parameterType : constructor.getParameterTypes()){
                            arguments[i] = mock(parameterType);
                            i++;
                        }
                        controllers.add(constructor.newInstance(arguments));
                    }catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e){
                        e.printStackTrace();
                    }
                }
                );
    }
}

