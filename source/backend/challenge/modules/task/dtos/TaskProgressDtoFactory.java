package backend.challenge.modules.task.dtos;

public class TaskProgressDtoFactory implements ITaskProgressDtoFactory{

    @Override
    public TaskProgressDTO build(Long taskId, int progress) {
        TaskProgressDTO taskProgressDTO = TaskProgressDTO.create();
        taskProgressDTO.setId(taskId);
        taskProgressDTO.setProgress(progress);
        return taskProgressDTO;
    }
}
