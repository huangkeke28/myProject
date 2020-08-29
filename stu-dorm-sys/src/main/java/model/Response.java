package model;
//统一的数据格式

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Response {
    private boolean success;
    private String code;
    private String message;
    private Integer total;//做分页用的
    private Object data;
    private String stackTrace;
}
