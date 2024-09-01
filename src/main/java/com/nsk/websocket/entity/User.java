package com.nsk.websocket.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter
public class User {
    @Id
    private String nickName;
    private String fullName;
    private Status status;
}
