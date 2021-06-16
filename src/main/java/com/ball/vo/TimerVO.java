package com.ball.vo;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TimerVO {
    private Long timer_id;
    private String user_id;
    private LocalDateTime timer_date;
    private LocalTime timer_accumulated_day;
    private LocalDateTime timer_reg_date;
    private LocalDateTime timer_mod_date;
}
