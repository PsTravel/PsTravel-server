package com.example.pstravel.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
public class ChatRoom  extends TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomIdx;


}
