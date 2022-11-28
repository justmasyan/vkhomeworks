package repositoryies.funcinterfaces;

import org.jooq.DSLContext;

@FunctionalInterface
public interface DSLContextExecutor {

    void addValues(DSLContext context);
}
