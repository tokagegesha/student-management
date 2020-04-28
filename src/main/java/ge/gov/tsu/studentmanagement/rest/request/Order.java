package ge.gov.tsu.studentmanagement.rest.request;

import org.springframework.data.domain.Sort;

public class Order{
    private org.springframework.data.domain.Sort.Direction direction = org.springframework.data.domain.Sort.Direction.ASC;
    private String property = "id";

    public org.springframework.data.domain.Sort.Direction getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        if (direction.equalsIgnoreCase("ASC")){
            this.direction = Sort.Direction.ASC;
        }else  if (direction.equalsIgnoreCase("DESC")){
            this.direction = Sort.Direction.DESC;
        }
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}