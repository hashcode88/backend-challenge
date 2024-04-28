package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.exceptions.CreateTaskException;

import java.util.List;

public interface ITaskRepository {

	Task index(Long taskId);
	List<Task> show();
	Task create(TaskDTO taskDTO) throws CreateTaskException;
	Task update(Task task);
	Task updateProgress(Task task);
	void delete(Long taskId);

}
