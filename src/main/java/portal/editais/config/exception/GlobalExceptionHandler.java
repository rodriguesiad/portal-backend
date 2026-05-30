package portal.editais.config.exception;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.security.sasl.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404() {
        logger.error("Erro: 404 Not Found.");
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        var errosResponse = errors.stream().map(DataErrorValidation::new).toList();
        var pacote = exception.getParameter().getParameterType().getPackageName();

        logger.error("Erro em " + pacote + ": " + errosResponse);

        return ResponseEntity.badRequest().body(errosResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleError400(HttpMessageNotReadableException ex) {
        logger.error("Erro: " + ex);
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleErrorBadCredentials() {
        logger.error("Erro: Unauthorized. Credenciais inválidas.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handleErrorAuthentication() {
        logger.error("Erro: Unauthorized. Falha na autenticação.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleErrorAccessDenied() {
        logger.error("Erro: Forbidden. Acesso negado.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleError500(Exception exception) {
        logger.error("Erro: " + exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + exception.getLocalizedMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleBusinessRuleError(ApiException exception) {
        logger.error("Erro: " + exception);
        return ResponseEntity.badRequest().contentType(MediaType.TEXT_PLAIN).body(new String(exception.getMessage().getBytes(), StandardCharsets.UTF_8));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleBusinessRuleError(IOException exception) {
        logger.error("Erro: " + exception);
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity handleTokenExpiredException(JwtException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity handleMultipartException(MultipartException exception) {
        logger.error("Erro: " + exception);
        return ResponseEntity.badRequest().body(exception.getCause().getMessage());
    }

    public record DataErrorValidation(String name, String message) {
        public DataErrorValidation(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
