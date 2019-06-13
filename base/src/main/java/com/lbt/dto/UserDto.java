package com.lbt.dto;

import com.jfinal.plugin.activerecord.Model;
import lombok.Data;

@Data
public class UserDto extends Model<UserDto> {
    private  Integer uid;
    private String uname;
}
