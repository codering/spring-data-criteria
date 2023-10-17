package io.github.holmofy.data.relational;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.sql.*;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

@Slf4j
public class MoreCondition {

    private final SqlParameterSource sqlParameterSource;

    public MoreCondition(SqlParameterSource sqlParameterSource) {
        this.sqlParameterSource = sqlParameterSource;
    }

    public Condition eq(Column column, String parameterName) {
        if (isParameterNameInvalid(parameterName)) {
            return TrueCondition.INSTANCE;
        }
        return Conditions.isEqual(column, SQL.bindMarker(":" + parameterName));
    }

    public Condition lt(Column column, String parameterName) {
        if (isParameterNameInvalid(parameterName)) {
            return TrueCondition.INSTANCE;
        }
        return Conditions.isLess(column, SQL.bindMarker(":" + parameterName));
    }

    public Condition lte(Column column, String parameterName) {
        if (isParameterNameInvalid(parameterName)) {
            return TrueCondition.INSTANCE;
        }
        return Conditions.isLessOrEqualTo(column, SQL.bindMarker(":" + parameterName));
    }

    public Condition gt(Column column, String parameterName) {
        if (isParameterNameInvalid(parameterName)) {
            return TrueCondition.INSTANCE;
        }
        return Conditions.isGreater(column, SQL.bindMarker(":" + parameterName));
    }

    public Condition gte(Column column, String parameterName) {
        if (isParameterNameInvalid(parameterName)) {
            return TrueCondition.INSTANCE;
        }
        return Conditions.isGreaterOrEqualTo(column, SQL.bindMarker(":" + parameterName));
    }

    public Condition in(Column column, String parameterName) {
        if (isParameterNameInvalid(parameterName)) {
            return TrueCondition.INSTANCE;
        }
        return Conditions.in(column, SQL.bindMarker(":" + parameterName));
    }

    public Condition notIn(Column column, String parameterName) {
        if (isParameterNameInvalid(parameterName)) {
            return TrueCondition.INSTANCE;
        }
        return Conditions.notIn(column, SQL.bindMarker(":" + parameterName));
    }

    public Condition like(Column column, String parameterName) {
        if (isParameterNameInvalid(parameterName)) {
            return TrueCondition.INSTANCE;
        }
        return Conditions.like(column, SQL.bindMarker(":" + parameterName));
    }

    private boolean isParameterNameInvalid(String parameterName) {
        if (!sqlParameterSource.hasValue(parameterName)) {
            log.warn("No parameter named \"{}\" exists.", parameterName);
            return true;
        }
        return sqlParameterSource.getValue(parameterName) == null;
    }
}
