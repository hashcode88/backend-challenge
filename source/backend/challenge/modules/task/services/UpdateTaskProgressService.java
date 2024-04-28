package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import kikaha.urouting.api.DefaultResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UpdateTaskProgressService implements IUpdateTaskProgressService{

    private final ITaskRepository taskRepository;
    private int LIMIT_PROGRESS = 100;

    @Inject
    public UpdateTaskProgressService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public DefaultResponse execute(TaskProgressDTO taskProgressDTO) {
        final Task retrivedTask = taskRepository.index(taskProgressDTO.getId());
        if(retrivedTask == null) {
            return DefaultResponse.notFound().statusCode(404);
        }
        if(taskProgressDTO.getProgress() == LIMIT_PROGRESS) {
            retrivedTask.setStatus(TaskStatus.COMPLETE);
        }
        retrivedTask.setProgress(taskProgressDTO.getProgress());
        Task updatedTask = taskRepository.updateProgress(retrivedTask);
        return DefaultResponse.ok().entity(updatedTask);
    }
}