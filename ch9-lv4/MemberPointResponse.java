package com.example.point.dto.response;

import com.example.point.entity.MemberEntity;

public class MemberPointResponse {

    private Integer id;
    private String memberCode;
    private String name;
    private String email;
    private Integer pointBalance;
    private String rank;

    public static MemberPointResponse from(MemberEntity entity) {
        MemberPointResponse response = new MemberPointResponse();
        response.id           = entity.getId();
        response.memberCode   = entity.getMemberCode();
        response.name         = entity.getName();
        response.email        = entity.getEmail();
        response.pointBalance = entity.getPointBalance();
        response.rank         = entity.getRank();
        return response;
    }

    public Integer getId() { return id; }
    public String getMemberCode() { return memberCode; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Integer getPointBalance() { return pointBalance; }
    public String getRank() { return rank; }
}
