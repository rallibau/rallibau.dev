package com.rallibau.apps.commons;

import com.rallibau.shared.domain.DomainError;
import com.rallibau.shared.domain.Utils;
import com.rallibau.shared.domain.bus.command.CommandHandlerExecutionError;
import com.rallibau.shared.domain.bus.query.QueryHandlerExecutionError;
import com.rallibau.shared.infraestructure.spring.api.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.NestedServletException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public final class ApiExceptionMiddleware implements Filter {
    private RequestMappingHandlerMapping mapping;

    public ApiExceptionMiddleware(RequestMappingHandlerMapping mapping) {
        this.mapping = mapping;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws ServletException {
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        HttpServletResponse httpResponse = ((HttpServletResponse) response);

        try {
            Optional<Object> possibleController = Optional.empty();
            if (Objects.requireNonNull(
                    mapping.getHandler(httpRequest)).getHandler() instanceof HandlerMethod) {
                possibleController = Optional.of((
                        (HandlerMethod) Objects.requireNonNull(
                                mapping.getHandler(httpRequest)).getHandler()
                ).getBean());
            }


            try {
                chain.doFilter(request, response);
            } catch (NestedServletException exception) {
                if (possibleController.isPresent() && possibleController.get() instanceof ApiController) {
                    handleCustomError(response,
                            httpResponse, (ApiController) possibleController.get(), exception);
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void handleCustomError(
            ServletResponse response,
            HttpServletResponse httpResponse,
            ApiController possibleController,
            NestedServletException exception
    ) throws IOException {
        exception.printStackTrace();
        HashMap<Class<? extends RuntimeException>, HttpStatus> errorMapping = possibleController
                .errorMapping();
        errorMapping.put(BadCredentialsException.class, HttpStatus.UNAUTHORIZED);
        Throwable error = (
                exception.getCause() instanceof CommandHandlerExecutionError ||
                        exception.getCause() instanceof QueryHandlerExecutionError
        )
                ? exception.getCause().getCause() : exception.getCause();

        int statusCode = statusFor(errorMapping, error);
        String errorCode = errorCodeFor(error);
        String errorMessage = error.getMessage();

        httpResponse.reset();
        httpResponse.setHeader("Content-Type", "application/json");
        httpResponse.setStatus(statusCode);
        PrintWriter writer = response.getWriter();
        writer.write(String.format(
                "{\"error_code\": \"%s\", \"message\": \"%s\"}",
                errorCode,
                errorMessage
        ));
        writer.close();
    }

    private String errorCodeFor(Throwable error) {
        if (error instanceof DomainError) {
            return ((DomainError) error).errorCode();
        }

        if (error instanceof BadCredentialsException) {
            return "bad_credentials";
        }

        return Utils.toSnake(error.getClass().toString());
    }

    private int statusFor(HashMap<Class<? extends RuntimeException>, HttpStatus> errorMapping, Throwable error) {
        return errorMapping.getOrDefault(error.getClass(), HttpStatus.INTERNAL_SERVER_ERROR).value();
    }
}