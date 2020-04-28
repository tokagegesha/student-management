package ge.gov.tsu.studentmanagement.rest.request;

import ge.gov.tsu.studentmanagement.apiutils.service.GeneralTools;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class RequestObject<T> implements Serializable{

    private ActionPerformer actionPerformer;

    private Long clientId;

    private MsdaPageRequest paging;

    private T data;

    public PageRequest getPaging() {

        return paging == null ? null : new PageRequest(paging.getPage(), paging.getSize(), paging.getSort());
    }

    public void setPaging(MsdaPageRequest paging) {
        this.paging = paging;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ActionPerformer getActionPerformer() {
        if(actionPerformer == null){
            actionPerformer = new ActionPerformer();
        }
        return actionPerformer;
    }

    public void setActionPerformer(ActionPerformer actionPerformer) {
        this.actionPerformer = actionPerformer;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void checkRequiredFields(String... on){
        if(data instanceof Collection){
            for (Object o : (Collection) data){
                GeneralTools.checkRequiredFields(o, on);
            }
        }else {
            GeneralTools.checkRequiredFields(data, on);
        }
    }

    public void checkRequiredFields(List<String> requiredFieldList){
        if(data instanceof Collection){
            for (Object o : (Collection) data){
                GeneralTools.checkRequiredFields(o, requiredFieldList);
            }
        }else {
            GeneralTools.checkRequiredFields(data, requiredFieldList);
        }
    }

    public void reqireActionPerformer(){
        GeneralTools.checkRequiredFields(actionPerformer, "require-action-performer");
    }

    public void reqireActionPerformerUser(){
        GeneralTools.checkRequiredFields(actionPerformer, "user");
    }

    public void reqireActionPerformerClient(){
        GeneralTools.checkRequiredFields(actionPerformer, "client");
    }

    public void reqireActionPerformerUserAndClient(){
        GeneralTools.checkRequiredFields(actionPerformer, "user", "client");
    }

    public void reqireActionPerformerSession(){
        GeneralTools.checkRequiredFields(actionPerformer, "session");
    }

    public void reqireActionPerformerSessionId(){
        GeneralTools.checkRequiredFields(actionPerformer, "sessionId");
    }

    public void reqireActionPerformer(String... fields){
        GeneralTools.checkRequiredFields(actionPerformer, fields);
    }
}