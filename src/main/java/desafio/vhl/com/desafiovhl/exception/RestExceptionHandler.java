package desafio.vhl.com.desafiovhl.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



    @ControllerAdvice
    @Slf4j
    public class RestExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(RegistroExistenteException.class)
        public ResponseEntity<Object> handleRegistroExistenteException(RegistroExistenteException e) {
            var retorno = new ErroResponse("Registro já cadastrado!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(retorno);
        }

        @ExceptionHandler(RegistroNaoEncontradoException.class)
        public ResponseEntity<Object> handleRegistroNaoEncontradoException(RegistroNaoEncontradoException e) {
            var retorno = new ErroResponse("Registro não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retorno);
        }

        @ExceptionHandler(LivroIndisponivelException.class)
        public ResponseEntity<Object> handleLivroIndisponivelException(LivroIndisponivelException e) {
            var retorno = new ErroResponse("Livro não disponível para empréstimo!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);
        }

        @ExceptionHandler(LeitorComEmprestimosException.class)
        public ResponseEntity<Object> handleLeitorComEmprestimosException(LeitorComEmprestimosException e) {
            var retorno = new ErroResponse("Leitor com Empréstimos ativos não podem ser excluídos!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
            Map<String, String> fieldErrors = new HashMap<>();
            ex.getConstraintViolations().forEach(e -> {
                Iterator<Path.Node> iterator = e.getPropertyPath().iterator();
                String fieldName = null;
                while (iterator.hasNext()) {
                    fieldName = iterator.next().getName();
                }
                String errorMessage = e.getMessage();
                fieldErrors.put(fieldName, errorMessage);
            });
            log.error("Erros de validacao: {}", fieldErrors);
            List<String> mensagens = fieldErrors.keySet().stream().map(k -> k + ": " + fieldErrors.get(k)).toList();
            var retorno = new ErroResponse(mensagens);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);
        }

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
            Map<String, String> fieldErrors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                fieldErrors.put(fieldName, errorMessage);
            });
            List<String> mensagens = fieldErrors.keySet().stream().map(k -> k + ": " + fieldErrors.get(k)).toList();
            var retorno = new ErroResponse(mensagens);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);
        }

        @Override   // catch any other exception for standard error message handling
        protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
            Map<String, String> retorno = new HashMap<>();
            retorno.put("erro", "Erro no servidor! Contate o administrador do sistema!");
            return new ResponseEntity<>(retorno, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

