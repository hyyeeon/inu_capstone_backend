package com.capstone.startmap.domain.user.events;

import com.capstone.startmap.domain.user.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserDeletedEvent extends ApplicationEvent {
    private final User user;

    public UserDeletedEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
