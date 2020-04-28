package ge.gov.tsu.studentmanagement.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(value = {"cause", "stackTrace", "total", "data", "localizedMessage", "message", "suppressed"}, ignoreUnknown = true)
public class ResponseObject extends RuntimeException {

    private ResponseResult result;

    // TODO - yep "error" because of fucking actionhero :(
    @JsonProperty("error")
    private List<ResponseError> errors;

    private ResponseObject() {
    }

    public boolean isSuccess() {
        return errors == null;
    }

    public static ResponseObject createSuccessfulResponse() {
        return createSuccessfulResponse((Object) null);
    }

    public static ResponseObject createSuccessfulResponse(Object data) {
        ResponseObject r = new ResponseObject();
        r.result = new ResponseResult(data);
        return r;
    }

    public static ResponseObject createSuccessfulResponse(List data) {
        ResponseObject r = new ResponseObject();
        r.result = new ResponseResult(data);
        return r;
    }

    public static ResponseObject createSuccessfulResponse(Page data) {
        ResponseObject r = new ResponseObject();
        r.result = new ResponseResult(data);
        return r;
    }

    public static ResponseObject createFailedResponse(String errorCode, String errorKeyword, String errorMessage) {
        ResponseObject r = new ResponseObject();

        ResponseError re = new ResponseError();
        re.setCode(errorCode);
        re.setKeyword(errorKeyword);
        re.setMessage(errorMessage);
        if(r.errors == null){
            r.errors = new ArrayList<>();
        }
        r.errors.add(re);
        return r;
    }

    public static ResponseObject createFailedResponse(List<ResponseError> errors) {
        ResponseObject r = new ResponseObject();
        r.errors = errors;
        return r;
    }

    public static ResponseObject createFailedResponse(ResponseError... errors) {
        ResponseObject r = new ResponseObject();
        if(errors.length > 0){
            r.setErrors(Arrays.asList(errors));
        }
        return r;
    }

    /**
     * create ResponseObject from JSON string
     */
    public static ResponseObject getInstance(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, ResponseObject.class);
    }

    /**
     * generates json from current instance.
     */
    public String generateJsonString() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    @Deprecated
    public String generateJsonStringWithNoExceptions() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            return "{ \"errors\" : [{ code : \"99009\", \"keyword\" : \"generateJsonString\", message : \"error while generating json of another response object.\"}] }";
        }
    }

    public Object getData() {
        return result == null ? null : result.getContent();
    }

    public Long getTotal() {
        return result == null ? null : result.getTotalElements();
    }

    public ResponseObject setData(Object data) {
        this.result = new ResponseResult(data);
        return this;
    }

    public ResponseObject setTotalCount(Long totalCount) {
        this.result = new ResponseResult();
        return this;
    }

    public List<ResponseError> getErrors() {
        return this.errors;
    }

    public void setErrors(List<ResponseError> errors) {
        this.errors = errors;
    }

    public <T> ResponseResult<T> getResult() {
        return result;
    }

    public void setResult(ResponseResult result) {
        this.result = result;
    }

    public void addError(ResponseError err){
        if(this.errors == null){
            this.errors = new ArrayList<>();
        }
        this.errors.add(err);
    }

}