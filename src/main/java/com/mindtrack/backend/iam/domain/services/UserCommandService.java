package com.mindtrack.backend.iam.domain.services;

import com.mindtrack.backend.iam.domain.model.aggregates.User;
import com.mindtrack.backend.iam.domain.model.commands.SignInCommand;
import com.mindtrack.backend.iam.domain.model.commands.SignUpCommand;
import com.mindtrack.backend.iam.domain.model.commands.SignUpPatientCommand;
import com.mindtrack.backend.iam.domain.model.commands.SignUpProfessionalCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    //Optional<User> handle(SignUpCommand command);
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
    Optional<User> handle(SignUpPatientCommand command);
    Optional<User> handle(SignUpProfessionalCommand command);
}
