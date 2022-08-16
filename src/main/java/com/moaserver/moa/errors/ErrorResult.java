package com.moaserver.moa.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * written by dahae
 * date: 22.05.27
 */

@Data
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;
}
