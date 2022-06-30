package terry.kong.naceservice.controller.dto;

import lombok.Builder;

@Builder
public class ErrorResponse {
    private String message;
    private String code;
}
