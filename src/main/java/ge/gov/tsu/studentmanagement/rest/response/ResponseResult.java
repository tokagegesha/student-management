package ge.gov.tsu.studentmanagement.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.*;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@JsonIgnoreProperties({"isLast", "last", "isFirst", "first"})
public class ResponseResult<T> implements Page<T>, Serializable {

    private final List<T> content;

    private long total;

    private Pageable pageable;

    public ResponseResult() {
        this(null, 0, null);
    }


    public ResponseResult(Page<T> page) {
        this.content = page.getContent();
        this.total = page.getTotalElements();
        if(page.getSize() > 0){
            this.pageable = new PageRequest(page.getNumber(), page.getSize(), page.getSort());
        }
    }

    public ResponseResult(Page<T> page, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        List<T> list = new ArrayList<>();
        for(Object o : page.getContent()){
            list.add(mapper.convertValue(o, clazz));
        }
        this.content = list;
        this.total = page.getTotalElements();
        if(page.getSize() > 0){
            this.pageable = new PageRequest(page.getNumber(), page.getSize(), page.getSort());
        }
    }

    public ResponseResult(List<T> content) {
        this(content, null);
    }

    public ResponseResult(T content) {
        this.content = new ArrayList<T>();
        if(content != null){
            this.content.add(content);
        }
        this.total = 0;
        this.pageable = new PageRequest(0, 1, null);
    }

    public ResponseResult(List<T> content, Sort sort) {
        this(content, content.size(), 0, content.size() > 0 ? content.size() : 1, sort);
    }

    private ResponseResult(List<T> content, int totalCount, int pageNumber, int pageSize, Sort sort){
        this.content = content;
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, sort);
        this.total = totalCount;
        this.pageable = pageRequest;
    }
    private ResponseResult(List<T> content, int totalCount, Pageable pageable){
        this.content = content;
        this.total = totalCount;
        this.pageable = pageable;
    }


    @JsonIgnore
    public Pageable getPageRequest(){
        return pageable;
    }

    @JsonIgnore
    public T getSingleInstance() {
        switch (content.size()){
            case 1: return content.get(0);
            case 0: throw ResponseObject.createFailedResponse();//FIXME
            default: throw ResponseObject.createFailedResponse();//FIXME
        }
    }


    /*Implementing Page*/

    @JsonProperty("pageNumber")
    public int getNumber() {
        return this.pageable == null?0:this.pageable.getPageNumber();
    }

    @JsonProperty("pageSize")
    public int getSize() {
        return this.pageable == null?0:this.pageable.getPageSize();
    }

    @JsonProperty("totalPages")
    public int getTotalPages() {
        return this.getSize() == 0?0:(int)Math.ceil((double)this.total / (double)this.getSize());
    }

    @JsonIgnore
    public int getNumberOfElements() {
        return this.content.size();
    }

    @JsonProperty("totalCount")
    public long getTotalElements() {
        return this.total;
    }

    @JsonIgnore
    public boolean hasPrevious() {
        return this.getNumber() > 0;
    }

    @JsonIgnore
    public boolean isFirst() {
        return !this.hasPrevious();
    }

    @JsonIgnore
    public boolean hasNext() {
        return (long)((this.getNumber() + 1) * this.getSize()) < this.total;
    }

    @JsonIgnore
    public boolean isLast() {
        return !this.hasNext();
    }

    @JsonIgnore
    public Iterator<T> iterator() {
        return this.content.iterator();
    }

    @JsonProperty("data")
    public List<T> getContent() {
        return /*(List<T>) */this.content;
    }

    @JsonIgnore
    public boolean hasContent() {
        return !this.content.isEmpty();
    }

    public Sort getSort() {
        return this.pageable == null?null:this.pageable.getSort();
    }


    public Pageable nextPageable() {
        return hasNext() ? pageable.next() : null;
    }

    public Pageable previousPageable() {
        if (hasPrevious()) {
            return pageable.previousOrFirst();
        }
        return null;
    }

    public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
        return new PageImpl<S>(getConvertedContent(converter), pageable, total);
    }

    protected <S> List<S> getConvertedContent(Converter<? super T, ? extends S> converter) {

        Assert.notNull(converter, "Converter must not be null!");

        List<S> result = new ArrayList<S>(content.size());

        for (T element : this) {
            result.add(converter.convert(element));
        }

        return result;
    }

    public String toString() {
        String contentType = "UNKNOWN";
        if(this.content.size() > 0) {
            contentType = this.content.get(0).getClass().getName();
        }

        return String.format("Page %s of %d containing %s instances", new Object[]{Integer.valueOf(this.getNumber()), Integer.valueOf(this.getTotalPages()), contentType});
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(!(obj instanceof ResponseResult)) {
            return false;
        } else {
            ResponseResult that = (ResponseResult) obj;
            boolean totalEqual = this.total == that.total;
            boolean contentEqual = this.content.equals(that.content);
            boolean pageableEqual = this.pageable == null?that.pageable == null:this.pageable.equals(that.pageable);
            return totalEqual && contentEqual && pageableEqual;
        }
    }

    public int hashCode() {
        byte result = 17;
        int result1 = 31 * result + (int)(this.total ^ this.total >>> 32);
        result1 = 31 * result1 + (this.pageable == null?0:this.pageable.hashCode());
        result1 = 31 * result1 + this.content.hashCode();
        return result1;
    }

    /*END OF Implementing Page*/
}