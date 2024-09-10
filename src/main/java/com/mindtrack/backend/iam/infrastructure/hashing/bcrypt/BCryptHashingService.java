package com.mindtrack.backend.iam.infrastructure.hashing.bcrypt;

import com.mindtrack.backend.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService  extends HashingService, PasswordEncoder {

}
