package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.services.exceptions.RetriveTaskException;
import kikaha.urouting.api.DefaultResponse;

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
    public DefaultResponse execute(Long taskId) {
        final Task retrivedTask = taskRepository.index(taskId);
        if(retrivedTask == null) {
            return DefaultResponse.notFound().statusCode(404);
        }
        return DefaultResponse.ok().entity(retrivedTask);
    }
}
