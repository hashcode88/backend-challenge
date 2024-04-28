package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.exceptions.CreateTaskException;
import backend.challenge.modules.task.services.exceptions.RetriveTaskException;

import java.util.List;

public interface ITaskRepository {

	Task index(Long taskId) throws RetriveTaskException;
	List<Task> show();
	Task create(TaskDTO taskDTO) throws CreateTaskException;
	Task update(Task task);
	void delete(Long taskId);

}
