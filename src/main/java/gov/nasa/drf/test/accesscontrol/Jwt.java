package gov.nasa.drf.test.accesscontrol;

import com.auth0.jwt.exceptions.JWTDecodeException;

import java.util.*;

public class Jwt {

    public static String extractIss(String token) {
        return "";
    }

    public static List<String> extractRoles(String tokenStr) {
        return new ArrayList<>();

    }

    public static Set<String> extractSubjectRoles(String tokenStr) throws JWTDecodeException {
        return new HashSet<>();
    }

}

