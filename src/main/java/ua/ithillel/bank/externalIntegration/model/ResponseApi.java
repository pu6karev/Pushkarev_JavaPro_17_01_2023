package ua.ithillel.bank.externalIntegration.model;

import lombok.Data;

import java.util.Map;

@Data
public class ResponseApi {
    private Map<String, ResponseData> data;
}
