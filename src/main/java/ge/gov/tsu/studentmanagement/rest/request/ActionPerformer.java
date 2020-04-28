package ge.gov.tsu.studentmanagement.rest.request;

import ge.gov.tsu.studentmanagement.apiutils.annotations.Required;

import java.util.Date;
import java.util.Map;

public class ActionPerformer {

    private Date date;

    @Required(on = {"require-action-performer", "user", "userId"})
    private Long userId;

    @Required(on = {"require-action-performer", "client", "clientId"})
    private Long clientId;

    @Required(on = {"require-action-performer", "session", "sessionToken"}, allowEmptyValue = false)
    private String sessionToken;

    @Required(on = {"sessionId"}, allowEmptyValue = false)
    private String sessionId;

    private Map meta;


    public Date getDate() {
        if (date == null) date = new Date();
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Map getMeta() {
        return meta;
    }

    public void setMeta(Map meta) {
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "" +
                (userId == null ? "" : ("userId=" + userId + " ")) +
                (clientId == null ? "" : ("clientId=" + clientId + " ")) +
                (sessionToken == null ? "" : ("sessionToken=" + sessionToken + " ")) +
                (sessionId == null ? "" : ("sessionId=" + sessionId));
    }
}
