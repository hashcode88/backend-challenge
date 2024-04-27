package backend.challenge.modules.task.dtos;

public interface ITaskDtoFactory {
    public TaskDTO build(String title, String description);
}
