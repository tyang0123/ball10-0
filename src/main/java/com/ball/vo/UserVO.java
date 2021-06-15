package com.ball.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class UserVO {
    private String user_id;
    private String user_password;
    private String user_nickname;
    private String user_email;
    private Date user_reg_date;
    private Date user_mod_date;
}