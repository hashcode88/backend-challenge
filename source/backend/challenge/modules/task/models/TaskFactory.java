package backend.challenge.modules.task.models;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;

import java.util.Date;
import java.util.UUID;

public class TaskFactory implements ITaskFactory{

    @Override
    public Task build(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(Math.abs(UUID.randomUUID().getLeastSignificantBits()));
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setProgress(0);
        task.setStatus(TaskStatus.PROGRESS);
        task.setCreatedAt(new Date());
        return task;
    }
}
