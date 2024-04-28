package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.models.TaskFactory;
import backend.challenge.modules.task.services.exceptions.CreateTaskException;
import backend.challenge.modules.task.services.exceptions.RetriveTaskException;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class TaskRepository implements ITaskRepository {

	List<Task> taskList = new ArrayList<>();

	@Override
	public Task index(final Long taskId) throws RetriveTaskException {
		// TODO: Criar método responsável por retornar tarefa por id
		List<Task> retrivedtask = taskList.stream().filter(task -> task.getId().equals(taskId)).collect(Collectors.toList());
		if (retrivedtask.isEmpty()) {
			throw new RetriveTaskException("Tarefa não encontrada");
		}
		return retrivedtask.get(0);
	}

	@Override
	public List<Task> show() {
		// TODO: Criar método responsável por retornar todas as tarefas
		return taskList;
	}

	@Override
	public Task create(final TaskDTO taskDTO) throws CreateTaskException {
		// TODO: Criar método responsável por criar uma tarefa
		validate(taskDTO);
		Task task = new TaskFactory().build(taskDTO);
		taskList.add(task);
		return task;
	}

	@Override
	public Task update(final Task task) {
		// TODO: Criar método responsável por atualizar uma tarefa
		taskList.forEach(rawTask -> {
			if (rawTask.getId().equals(task.getId())){
				rawTask.setTitle(task.getTitle());
				rawTask.setDescription(task.getDescription());
			}
		});
		return task;
	}

	@Override
	public void delete(final Long taskId) {
 		// TODO: Criar método responsável por deletar tarefa por id

	}

	private static void validate(TaskDTO taskDTO) throws CreateTaskException {
		if (taskDTO.getTitle().trim().isEmpty()) {
			throw new CreateTaskException("Titulo não pode ser vazio");
		}
	}
}
