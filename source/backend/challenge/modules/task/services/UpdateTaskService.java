package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.services.exceptions.RetriveTaskException;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UpdateTaskService implements IUpdateTaskService {

    private final ITaskRepository taskRepository;

    @Inject
    public UpdateTaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(Long taskId, Task task) throws RetriveTaskException {
        final Task retrivedTask = taskRepository.index(taskId);
        retrivedTask.setTitle(task.getTitle());
        retrivedTask.setDescription(task.getDescription());
        return taskRepository.update(retrivedTask);
    }
}
