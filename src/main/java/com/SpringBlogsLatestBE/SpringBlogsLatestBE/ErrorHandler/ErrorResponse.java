package com.SpringBlogsLatestBE.SpringBlogsLatestBE.ErrorHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorResponse {

    private String errorCode;
    private String message;
    private String cause;
    private HttpStatus status;
}
