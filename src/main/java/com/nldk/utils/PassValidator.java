package com.nldk.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PassValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        // Có thể dùng để khởi tạo nếu cần
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.length() < 8) {
            return false; // Mật khẩu phải có ít nhất 8 ký tự
        }
        return password.matches("^(?=.*[A-Z])(?=.*\\d).+$"); // Yêu cầu có ít nhất 1 chữ hoa và 1 số
    }
}
