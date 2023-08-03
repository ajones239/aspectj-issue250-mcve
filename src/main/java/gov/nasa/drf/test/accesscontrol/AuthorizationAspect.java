package gov.nasa.drf.test.accesscontrol;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Aspect
@Component
@Order(2)
public class AuthorizationAspect {

    @Around("execution(* gov.nasa.drf.test.controller.HealthController.getDatabaseHealth(..))")
    public ResponseEntity<?> checkIfAllowedToAddRemoveRoleForUser(ProceedingJoinPoint joinPoint) throws Throwable {
        String did = "test";
        String targetDid = "test";
        String targetRole = "test";

        isValidOrgAdmin(did, targetDid, List.of(new String[]{}));
        isValidParent(did, targetDid, List.of(new String[]{targetRole}));

        Jwt.extractSubjectRoles((String) joinPoint.getArgs()[0]).contains(targetRole);

        return null;
    }

    @Around("execution(* gov.nasa.drf.test.controller.HealthController.getDatabaseHealth(..))")
    public ResponseEntity<?> checkIfAllowedToDeactivateUser(ProceedingJoinPoint joinPoint) throws Throwable {
        String did = Jwt.extractIss(((String) joinPoint.getArgs()[0]).split(" ")[1]);
        List<String> roles = Jwt.extractRoles((String) joinPoint.getArgs()[0]);
        String targetDid = (String) joinPoint.getArgs()[1];

        boolean authorized = false;
        isValidOrgAdmin(did, targetDid, roles);
        isValidParent(did, targetDid);

        return authorized ? (ResponseEntity<?>) joinPoint.proceed() : new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    private boolean isValidParent(String requesterDid, String targetDid, List<String> roles) throws IOException {
        return false;
    }

    private boolean isValidParent(String requesterDid, String targetDid) throws IOException {
        return true;
    }

    private boolean isValidOrgAdmin(String requesterDid, String targetDid, List<String> roles) throws IOException {
        return false;
    }
}
