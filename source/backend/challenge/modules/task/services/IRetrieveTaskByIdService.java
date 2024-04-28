package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.exceptions.RetriveTaskException;

public interface IRetrieveTaskByIdService {

	Task execute(Long taskId) throws RetriveTaskException;

}
