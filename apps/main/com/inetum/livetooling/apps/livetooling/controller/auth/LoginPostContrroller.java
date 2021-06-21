package com.inetum.livetooling.apps.livetooling.controller.auth;

import com.inetum.livetooling.shared.domain.DomainError;
import com.inetum.livetooling.shared.domain.bus.command.CommandBus;
import com.inetum.livetooling.shared.domain.bus.query.QueryBus;
import com.inetum.livetooling.shared.infraestructure.spring.ApiController;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@RestController
public class LoginPostContrroller extends ApiController {
    public LoginPostContrroller(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PostMapping(value = "/auth/login")
    public @ResponseBody Response Login(@RequestBody Request request) {
        if (request.email().equals("rallibau@gmail.com") && request.password().equals("test")) {
            return new Response(new Response.Data(createJWT("1", request.email(), "", 0)));
        }else {
            return new Response(new Response.Data(""));
        }

    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }


    //Sample method to construct a JWT
    private String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("SECRETOSECRETOSECRETOSECRETOSECRETOSECRETOSECRETOSECRETOSECRETOSECRETO");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .claim("name","Ramon Llinares")
                .claim("email","rallibau@gmail.com")
                .signWith(signingKey);



        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string

        return builder.compact();
    }

    static final class Request {


        private String email;
        private String password;

        public String email() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String password() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


    }

    static final class Response {

        private Data data;

        public Response(Data data) {
            this.data = data;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }


        static final class Data {


            private String token;

            public Data(String token) {
                this.token = token;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }

    }
}
