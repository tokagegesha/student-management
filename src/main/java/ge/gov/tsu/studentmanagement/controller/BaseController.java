package ge.gov.tsu.studentmanagement.controller;

import ge.gov.tsu.studentmanagement.exception.TsuException;
import ge.gov.tsu.studentmanagement.exception.TsuRuntimeException;
import ge.gov.tsu.studentmanagement.rest.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@ControllerAdvice
public class BaseController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> handleException(Exception e) throws IOException {
        // TO DO ErrorService & Language Service
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e);
    }

    @ExceptionHandler(TsuException.class)
    public ResponseEntity<TsuException> handleException(TsuException e) throws Exception {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e);
    }

    @ExceptionHandler(TsuRuntimeException.class)
    public ResponseEntity<TsuRuntimeException> handleException(TsuRuntimeException e) throws Exception {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e);
    }

    private ResponseObject findCause(Exception e) {
        if (e.getClass() == ResponseObject.class) {
            return (ResponseObject) e;
        }
        if (e.getCause() != null) {
            return this.findCause((Exception) e.getCause());
        }
        e.printStackTrace();
        return null;
    }
}