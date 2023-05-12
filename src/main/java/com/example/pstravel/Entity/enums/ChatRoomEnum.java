package com.example.pstravel.Entity.enums;

public enum ChatRoomEnum {
    SHOW,
    DELETE,
    OUT;

    private String displayName;

    ChatRoomEnum(String displayName){
        this.displayName = displayName;
    }

    ChatRoomEnum() {
    }

    public String getDisplayName(){
        return displayName;
    }

}
