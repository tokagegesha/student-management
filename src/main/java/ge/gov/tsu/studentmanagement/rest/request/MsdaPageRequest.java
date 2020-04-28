package ge.gov.tsu.studentmanagement.rest.request;

import java.util.ArrayList;
import java.util.List;

public class MsdaPageRequest{
    private int page = 0;
    private int size = 20;
    Sort sort;

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public org.springframework.data.domain.Sort getSort(){
        if(sort == null){
            return null;
        }else if(sort.getOrders() != null){
            List<org.springframework.data.domain.Sort.Order> oo = new ArrayList<>();
            for(Order item : sort.getOrders()){
                oo.add(new org.springframework.data.domain.Sort.Order(item.getDirection(), item.getProperty()));
            }
            return new org.springframework.data.domain.Sort(oo);
        }else if(sort.getProperties() != null) {
            return new org.springframework.data.domain.Sort(sort.getProperties());
        }
        return null;
    }
}
