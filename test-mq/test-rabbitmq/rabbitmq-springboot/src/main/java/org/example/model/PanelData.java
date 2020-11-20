package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PanelData {
    private String uuid;
    private String GunNumber;
    private Long holdDeptId;
    private Integer reason;
    private Long timestamp;
}
