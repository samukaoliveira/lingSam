package com.samukaoliveira.lingSam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardDTO {

    private long novas;
    private long familiares;
    private long aprendendo;
    private long dominadas;

}