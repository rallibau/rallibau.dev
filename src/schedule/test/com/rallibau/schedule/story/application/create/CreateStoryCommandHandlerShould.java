package com.rallibau.schedule.story.application.create;

import com.rallibau.schedule.story.domain.*;
import com.rallibau.shared.domain.bus.command.Command;
import com.rallibau.shared.domain.bus.event.EventBus;
import com.rallibau.shared.domain.events.scheduler.StoryCreatedDomainEvent;
import com.rallibau.shared.infraestructure.bus.command.CommandHandlersInformation;
import com.rallibau.shared.infraestructure.bus.command.CommandJsonDeserializer;
import com.rallibau.shared.infraestructure.bus.command.CommandJsonSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class CreateStoryCommandHandlerShould {
    private CreateStoryCommandHandler createStoryCommandHandler;
    private StoryCreator storyCreator;
    private StoryRepository storyRepository;
    protected EventBus eventBus;
    private CommandHandlersInformation information;
    private CommandJsonDeserializer commandJsonDeserializer;

    @BeforeEach
    protected void setUp() {
        eventBus = mock(EventBus.class);
        storyRepository = mock(StoryRepository.class);
        storyCreator = new StoryCreator(storyRepository, eventBus);

        createStoryCommandHandler = new CreateStoryCommandHandler(storyCreator);
        information = mock(CommandHandlersInformation.class);
        commandJsonDeserializer = new CommandJsonDeserializer(information);
    }

    @Test
    public void create_story_dont_failure() {
        Story story = StoryMother.random();

        StoryCreatedDomainEvent storyCreatedDomainEvent = new StoryCreatedDomainEvent(story.id().value(),
                story.name().value(),
                story.processId().value());

        CreateStoryCommand createStoryCommand = new CreateStoryCommand(story.id().value(),
                story.name().value(),
                story.processId().value());

        createStoryCommandHandler.handle(createStoryCommand);
        verify(storyRepository, atLeastOnce()).save(story);
        verify(eventBus, atLeastOnce()).publish(Collections.singletonList(storyCreatedDomainEvent));
    }

    @Test
    public void serialize_command_is_ok() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        CreateStoryCommand createStoryCommand = new CreateStoryCommand(StoryIdMother.random().value(),
                StoryNameMother.random().value(),
                StoryProcessIdMother.random().value());


        String commandSerialized = CommandJsonSerializer.serialize(createStoryCommand);

        when(information.forName("com.rallibau.schedule.story.application.create.CreateStoryCommand")).thenReturn(
          Optional.of(CreateStoryCommand.class)
        );


        System.out.println(commandSerialized);

        Optional<Command> command =  commandJsonDeserializer.deserialize(commandSerialized);
        assertThat("command isn't present ", command.isPresent());
        if(command.isPresent()){
            assertThat("command id isn't equal ", command.get().id().equals(createStoryCommand.id()));
            assertThat("command name isn't equal ", command.get().toPrimitives().get("name").equals(createStoryCommand.name()));
            assertThat("command processId isn't equal ", command.get().toPrimitives().get("processId").equals(createStoryCommand.processId()));
        }

    }

}
