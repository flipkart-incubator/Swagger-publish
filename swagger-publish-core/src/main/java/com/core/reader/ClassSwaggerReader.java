package com.core.reader;

import com.core.config.GenerateException;
import com.wordnik.swagger.models.Swagger;

import java.util.Set;

public interface ClassSwaggerReader {
    Swagger read(Set<Class<?>> classes) throws GenerateException;
}
