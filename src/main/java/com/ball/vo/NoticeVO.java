package com.ball.vo;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeVO {
    private Long notice_id;
    private String notice_content;
    private Date notice_reg_date;
    private Date notice_mod_date;
}
