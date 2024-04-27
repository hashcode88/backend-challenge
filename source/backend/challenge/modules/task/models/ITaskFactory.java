package backend.challenge.modules.task.models;

import backend.challenge.modules.task.dtos.TaskDTO;

public interface ITaskFactory {

    public Task build(TaskDTO taskDTO);
}
