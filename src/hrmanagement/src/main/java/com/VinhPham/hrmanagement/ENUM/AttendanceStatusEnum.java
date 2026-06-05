package com.VinhPham.hrmanagement.ENUM;

public enum AttendanceStatusEnum {
    VANG_MAT("VẮNG MẶT"),
    DUNG_GIO("ĐÚNG GIỜ"),
    MUON("MUỘN"),
    LAM_THEM("LÀM THÊM");

    private final String displayName;

    AttendanceStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
