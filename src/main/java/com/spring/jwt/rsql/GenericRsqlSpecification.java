package com.spring.jwt.rsql;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.*;

import org.springframework.data.jpa.domain.Specification;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;

public class GenericRsqlSpecification<T> implements Specification<T> {

    private String property;
    private ComparisonOperator operator;
    private List<String> arguments;

    public GenericRsqlSpecification(final String property, final ComparisonOperator operator, final List<String> arguments) {
        super();
        this.property = property;
        this.operator = operator;
        this.arguments = arguments;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        Path<String> propertyExpression = parseProperty(root);
        List<Object> args = castArguments(propertyExpression);
        Object argument = args.get(0);
        switch (RsqlSearchOperation.getSimpleOperator(operator)) {
            case EQUAL:
                if (argument instanceof String)
                    return builder.like(propertyExpression,
                            argument.toString().replace('*', '%'));
                else if (argument == null)
                    return builder.isNull(propertyExpression);
                else return builder.equal(propertyExpression, argument);

            case NOT_EQUAL:
                if (argument instanceof String)
                    return builder.notLike(propertyExpression,
                            argument.toString().replace('*', '%'));
                else if (argument == null)
                    return builder.isNotNull(propertyExpression);
                else return builder.notEqual(propertyExpression, argument);

            case GREATER_THAN:
                return builder.greaterThan(propertyExpression,
                        argument.toString());

            case GREATER_THAN_OR_EQUAL:
                return builder.greaterThanOrEqualTo(propertyExpression,
                        argument.toString());

            case LESS_THAN:
                return builder.lessThan(propertyExpression,
                        argument.toString());

            case LESS_THAN_OR_EQUAL:
                return builder.lessThanOrEqualTo(propertyExpression,
                        argument.toString());
            case IN:
                return propertyExpression.in(args);
            case NOT_IN:
                return builder.not(propertyExpression.in(args));
        }

        return null;
    }

    private Path<String> parseProperty(Root<T> root) {
        Path<String> path;
        if (property.contains(".")) {
            // Nested properties
            String[] pathSteps = property.split("\\.");
            String step = pathSteps[0];
            path = root.get(step);

            for (int i = 1; i <= pathSteps.length - 1; i++) {
                path = path.get(pathSteps[i]);
            }
        } else {
            path = root.get(property);
        }
        return path;
    }

    // === private

    private List<Object> castArguments(Path<?> propertyExpression) {
        Class<?> type = propertyExpression.getJavaType();

        return arguments.stream().map(arg -> {
            if (type.equals(Integer.class)) return Integer.parseInt(arg);
            else if (type.equals(Long.class)) return Long.parseLong(arg);
            else if (type.equals(Byte.class)) return Byte.parseByte(arg);
            else return arg;
        }).collect(Collectors.toList());
    }

}