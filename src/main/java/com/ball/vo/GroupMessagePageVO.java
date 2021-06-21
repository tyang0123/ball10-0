package com.ball.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
public class GroupMessagePageVO {
    private int MessageCnt;
    private List<GroupMessageVO> list;
}
