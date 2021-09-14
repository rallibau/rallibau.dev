package com.rallibau.schedule.story.application.create;

import com.rallibau.schedule.story.domain.Story;
import com.rallibau.schedule.story.domain.StoryId;
import com.rallibau.schedule.story.domain.StoryName;
import com.rallibau.schedule.story.domain.StoryProcessId;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.command.CommandHandler;

@Service
public class CreateStoryCommandHandler implements CommandHandler<CreateStoryCommand> {
    private final StoryCreator storyCreator;

    public CreateStoryCommandHandler(StoryCreator storyCreator) {
        this.storyCreator = storyCreator;
    }

    public void handle(CreateStoryCommand createStoryCommand) {
        storyCreator.create(Story.create(new StoryId(createStoryCommand.id()),
                new StoryName(createStoryCommand.name()),
                new StoryProcessId(createStoryCommand.processId())));
    }
}
