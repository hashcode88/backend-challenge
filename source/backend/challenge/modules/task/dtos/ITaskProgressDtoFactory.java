package backend.challenge.modules.task.dtos;

public interface ITaskProgressDtoFactory {

    public TaskProgressDTO build(Long taskId, int progress);
}
