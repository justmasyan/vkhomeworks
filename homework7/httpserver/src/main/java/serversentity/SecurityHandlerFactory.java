package serversentity;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.util.security.Constraint;

import java.util.*;

public class SecurityHandlerFactory {

    private static final String ROLE_MANAGER = "manager";
    private static final String ROLE_GUEST = "guest";

    public static ConstraintSecurityHandler build(LoginService loginService) {
        ConstraintSecurityHandler security = new ConstraintSecurityHandler();
        security.setLoginService(loginService);

        String[] paths = new String[]{"/","/products"};
        String[] methodsRequest = new String[]{"GET","GET"};

        final List<ConstraintMapping> constraintMappings = new ArrayList<>();
        constraintMappings.addAll(ConstraintMappingFactory(buildConstraint(ROLE_GUEST),paths,methodsRequest));

        paths = new String[]{"/","/products","/products"};
        methodsRequest = new String[]{"GET","GET","POST"};

        constraintMappings.addAll(ConstraintMappingFactory(buildConstraint(ROLE_MANAGER),paths,methodsRequest));

        security.setConstraintMappings(constraintMappings);
        security.setAuthenticator(new BasicAuthenticator());
        security.setDenyUncoveredHttpMethods(true);
        return security;
    }

    private static Constraint buildConstraint(String... userRoles) {
        final Constraint starterConstraint = new Constraint();
        starterConstraint.setName(Constraint.__BASIC_AUTH);
        starterConstraint.setRoles(userRoles);
        starterConstraint.setAuthenticate(true);
        return starterConstraint;
    }

    private static List<ConstraintMapping> ConstraintMappingFactory(Constraint constraint, String[] paths,String[] methodsRequest){
        List<ConstraintMapping> list = new ArrayList<>();
        for (int i = 0; i < paths.length; i++) {
            ConstraintMapping mapping = new ConstraintMapping();
            mapping.setConstraint(constraint);
            mapping.setPathSpec(paths[i]);
            mapping.setMethod(methodsRequest[i]);
            list.add(mapping);
        }
        return list;
    }
}
