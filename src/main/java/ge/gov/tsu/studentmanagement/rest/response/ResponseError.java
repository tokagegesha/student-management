package ge.gov.tsu.studentmanagement.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseError {

    /**
     * Unique id of error
     */
    private String code;

    /**
     * Unique keyword inside application context.
     */
    private String keyword;

    /**
     * Human readable message of error.
     */
    private String message;

    private String description;

    /**
     * Target Exception object
     */
    private Object error;

    public ResponseError() {
    }

    public ResponseError(Map errorAsMap) {
        this(errorAsMap, null);
    }

    public ResponseError(Map errorAsMap, Object error) {
        this(errorAsMap.get("code").toString(), errorAsMap.get("keyword").toString(), errorAsMap.get("message").toString(), error);
    }

    public ResponseError(String code, String keyword, String message) {
        this(code, keyword, message, null);
    }

    public ResponseError(String code, String keyword, String message, Object exception) {
        this.code = code;
        this.keyword = keyword;
        this.message = message;
        this.error = exception;
    }

    public ResponseError(String keyword, Object... exceptions) {
        this.setKeyword(keyword);
        List<Object> errorList = new ArrayList<Object>();
        for (Object error : exceptions) {
            errorList.add(error);
        }
        this.setError(errorList);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getMessage() {
        if (message == null) return description;
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.message = description;
        this.description = description;
    }
}
