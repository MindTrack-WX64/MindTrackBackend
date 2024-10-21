package com.mindtrack.backend.iam.interfaces.acl;

import com.mindtrack.backend.iam.domain.model.queries.CheckUserByIdQuery;
import com.mindtrack.backend.iam.domain.services.UserQueryService;
import org.springframework.stereotype.Service;

@Service
public class UserContextFacade {
    private final UserQueryService userQueryService;

    public UserContextFacade(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    public boolean checkIfUserExists(Long userId) {
        var query = new CheckUserByIdQuery(userId);
        return userQueryService.handle(query);
    }
}