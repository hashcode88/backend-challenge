package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.exceptions.CreateTaskException;

public interface ICreateTaskService {

	Task execute(TaskDTO taskDTO) throws CreateTaskException;

}
