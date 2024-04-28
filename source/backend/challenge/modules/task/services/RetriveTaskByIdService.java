package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.services.exceptions.RetriveTaskException;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RetriveTaskByIdService implements IRetrieveTaskByIdService {

    private final ITaskRepository taskRepository;

    @Inject
    public RetriveTaskByIdService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(Long taskId) throws RetriveTaskException {
        return taskRepository.index(taskId);
    }
}
