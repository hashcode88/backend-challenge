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
    private int INITIAL_PROGRESS = 0;

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
        this.changeProgress(retrivedTask, taskProgressDTO.getProgress());
        this.changeStatus(retrivedTask, taskProgressDTO.getProgress());
        Task updatedTask = taskRepository.updateProgress(retrivedTask);
        return DefaultResponse.ok().entity(updatedTask);
    }

    private void changeProgress(Task task, int progress) {
        int taskProgress = 0;
        if (progress > INITIAL_PROGRESS) {
            taskProgress = progress;
        }
        if (progress > LIMIT_PROGRESS) {
            taskProgress = LIMIT_PROGRESS;
        }
        task.setProgress(taskProgress);
    }

    private void changeStatus(Task task, int progress) {
        if(progress == LIMIT_PROGRESS) {
            task.setStatus(TaskStatus.COMPLETE);
        }else {
            task.setStatus(TaskStatus.PROGRESS);
        }
    }
}
