package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;
import kikaha.urouting.api.DefaultResponse;

public interface IUpdateTaskService {

	DefaultResponse execute(Long taskId, Task task);

}
