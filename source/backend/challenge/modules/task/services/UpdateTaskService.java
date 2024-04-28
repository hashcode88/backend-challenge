package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import kikaha.urouting.api.DefaultResponse;

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
    public DefaultResponse execute(Long taskId, Task task) {
        final Task retrivedTask = taskRepository.index(taskId);
        if(retrivedTask == null) {
            return DefaultResponse.notFound().statusCode(404);
        }
        return DefaultResponse.ok().entity(taskRepository.update(retrivedTask));
    }
}
