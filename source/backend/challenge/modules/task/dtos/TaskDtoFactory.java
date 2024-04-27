package backend.challenge.modules.task.dtos;

public class TaskDtoFactory implements ITaskDtoFactory{

    @Override
    public TaskDTO build(String title, String description) {
        TaskDTO taskDTO = TaskDTO.create();
        taskDTO.setTitle(title);
        taskDTO.setDescription(description);
        return taskDTO;
    }
}
