package com.core.config;
import com.wordnik.swagger.annotations.Api;
import org.reflections.Reflections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shubham.tyagi on 08/05/15.
 */
public class GetValidClasses {
    Set<Class <?> > getValidClasses(String locations) {
        Set<Class <?> > validClasses = new HashSet<Class <?> >();
        if (locations == null) {
            Set<Class<?>> c = new Reflections("").getTypesAnnotatedWith(Api.class);
            validClasses.addAll(c);
        } else {
            if (locations.contains(";")) {
                String[] sources = locations.split(";");
                for (String source : sources) {
                    Set<Class<?>> c = new Reflections(source).getTypesAnnotatedWith(Api.class);
                    validClasses.addAll(c);
                }
            } else {
                validClasses.addAll(new Reflections(locations).getTypesAnnotatedWith(Api.class));
            }
        }
        return validClasses;
    }
}
