package com.rallibau.acl.user.application.create;

import com.rallibau.acl.user.domain.User;
import com.rallibau.acl.user.domain.UserId;
import com.rallibau.acl.user.domain.UserName;
import com.rallibau.acl.user.domain.UserPassword;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.command.CommandHandler;
import com.rallibau.shared.domain.spring.security.PasswordEncoder;

@Service
public class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {

    private final UserCreator userCreator;
    private final PasswordEncoder passwordEncoder;

    public CreateUserCommandHandler(UserCreator userCreator, PasswordEncoder passwordEncoder) {
        this.userCreator = userCreator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void handle(CreateUserCommand command) {
        userCreator.create(
                User.create(
                        UserId.create(command.id()),
                        UserName.create(command.userName()),
                        UserPassword.create(passwordEncoder, command.password())
                )
        );
    }
}
