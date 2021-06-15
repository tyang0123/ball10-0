package com.ball.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVO {
    private Long notice_id;
    private String notice_content;
    private LocalDateTime notice_reg_date;
    private LocalDateTime notice_mod_date;

}
