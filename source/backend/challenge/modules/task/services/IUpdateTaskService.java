package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.exceptions.RetriveTaskException;

public interface IUpdateTaskService {

	Task execute(Long taskId, Task task) throws RetriveTaskException;

}
