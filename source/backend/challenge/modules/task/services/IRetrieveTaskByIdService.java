package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.exceptions.RetriveTaskException;
import kikaha.urouting.api.DefaultResponse;

public interface IRetrieveTaskByIdService {

	DefaultResponse execute(Long taskId);

}
