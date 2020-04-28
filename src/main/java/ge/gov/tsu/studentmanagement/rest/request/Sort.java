package ge.gov.tsu.studentmanagement.rest.request;

public class Sort {
    private Order[] orders;
    private String[] properties;

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public String[] getProperties() {
        return properties;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }

}
