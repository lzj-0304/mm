package com.sys.query;


import lombok.Data;


@Data
public class ModuleQuery  extends BaseQuery {
    private String moduleName;
    private Integer pid;
    private Integer grade;
    private String optValue;


}
