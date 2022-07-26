package com.toursix.turnaround.service.auth;

import com.toursix.turnaround.service.auth.dto.request.LoginDto;

public interface AuthService {

    Long login(LoginDto request);
}
