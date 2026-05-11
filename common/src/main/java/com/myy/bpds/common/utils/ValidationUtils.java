package com.myy.bpds.common.utils;

import com.myy.bpds.common.exception.BpdsException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 参数校验工具类 基于 spring-boot-starter-validation (Hibernate Validator) 提供编程式参数校验和 Bean 校验功能
 */
public class ValidationUtils {

    private static final Validator VALIDATOR;

    static {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            VALIDATOR = factory.getValidator();
        }
    }

    /**
     * 校验 Bean 对象，返回所有违反约束的信息
     *
     * @param obj 待校验的 Bean 对象
     * @return 违反约束的错误消息集合
     */
    public static Set<String> validate(Object obj) {
        Set<ConstraintViolation<Object>> violations = VALIDATOR.validate(obj);
        return violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());
    }

    /**
     * 校验 Bean 对象，如果有违反约束则抛出异常
     *
     * @param obj 待校验的 Bean 对象
     * @throws BpdsException 如果存在违反约束的情况
     */
    public static void validateAndThrow(Object obj) {
        Set<String> errors = validate(obj);
        if (!errors.isEmpty()) {
            String message = String.join(", ", errors);
            throw new BpdsException(message);
        }
    }
}
