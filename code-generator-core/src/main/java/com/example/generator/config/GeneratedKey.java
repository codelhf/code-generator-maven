package com.example.generator.config;

import com.example.generator.db.DatabaseDialects;
import com.example.generator.db.DatabaseDialects;
import com.example.generator.util.StringUtil;
import com.example.generator.util.StringUtil;

import java.util.List;

public class GeneratedKey {

    private String column;

    private String runtimeSqlStatement;

    private boolean isIdentity;

    private String type;

    public GeneratedKey(String column, String configuredSqlStatement,
                        boolean isIdentity, String type) {
        this.column = column;
        this.type = type;
        this.isIdentity = isIdentity;

        DatabaseDialects dialect = DatabaseDialects.getDatabaseDialect(configuredSqlStatement);
        if (dialect == null) {
            this.runtimeSqlStatement = configuredSqlStatement;
        } else {
            this.runtimeSqlStatement = dialect.getIdentityRetrievalStatement();
        }
    }

    public void validate(List<String> errors, String tableName) {
        if (StringUtil.isBlank(runtimeSqlStatement)) {
            errors.add("SQL Statement is required if a generated key is specified in table configuration for table " + tableName);
        }

        if (StringUtil.isNotBlank(type) && !"pre".equals(type) && !"post".equals(type)) {
            errors.add("Generated key type must be either \"pre\" or \"post\" if the type is specified for a generated key for table {tableName}");
        }

        if ("pre".equals(type) && isIdentity) {
            errors.add("Generated key in table {"+ tableName +"} cannot be both \"pre\" and identity");
        }

        if ("post".equals(type) && !isIdentity) {
            errors.add("Generated key in table {"+ tableName +"} cannot be both \"post\" and not identity");
        }
    }

    public String getMyBatis3Order() {
        return isIdentity ? "AFTER" : "BEFORE";
    }

    public boolean isJdbcStandard() {
        return "JDBC".equals(runtimeSqlStatement);
    }


    public String getColumn() {
        return column;
    }

    public boolean isIdentity() {
        return isIdentity;
    }

    public String getRuntimeSqlStatement() {
        return runtimeSqlStatement;
    }

    public String getType() {
        return type;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setRuntimeSqlStatement(String runtimeSqlStatement) {
        this.runtimeSqlStatement = runtimeSqlStatement;
    }

    public void setIdentity(boolean identity) {
        isIdentity = identity;
    }

    public void setType(String type) {
        this.type = type;
    }
}
