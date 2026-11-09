package com.example.esercitazione1.exception;

public record ApiErrorResponse(int status, String error, String message) {
}
