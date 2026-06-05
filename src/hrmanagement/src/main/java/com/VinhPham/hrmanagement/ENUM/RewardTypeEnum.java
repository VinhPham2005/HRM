package com.VinhPham.hrmanagement.ENUM;

public enum RewardTypeEnum {
    KHEN_THUONG("KHEN THƯỞNG"),
    KY_LUAT("KỶ LUẬT");

    private final String displayName;

    RewardTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

