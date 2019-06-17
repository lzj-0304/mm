package com.sys.query;


import lombok.Data;

@Data
public class UserQuery extends BaseQuery {
    private String userName;
    private String email;
    private String phone;

}
