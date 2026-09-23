package com.example.point.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PointUseRequest {

    @NotNull(message = "ポイント数は必須です")
    @Min(value = 1, message = "ポイント数は1以上で入力してください")
    private Integer points;

    @NotBlank(message = "利用理由は必須です")
    private String reason;

    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
