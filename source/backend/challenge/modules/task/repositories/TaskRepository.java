package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.models.TaskFactory;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class TaskRepository implements ITaskRepository {

	List<Task> taskList = new ArrayList<>();

	@Override
	public Task index(final Long taskId){
		// TODO: Criar método responsável por retornar tarefa por id
		List<Task> retrivedtask = taskList.stream().filter(task -> task.getId().equals(taskId)).collect(Collectors.toList());
		Task task = null;
		if (!retrivedtask.isEmpty()) {
			task = retrivedtask.get(0);
		}
		return task;
	}

	@Override
	public List<Task> show() {
		// TODO: Criar método responsável por retornar todas as tarefas
		return taskList;
	}

	@Override
	public Task create(final TaskDTO taskDTO) {
		// TODO: Criar método responsável por criar uma tarefa
		Task task = null;
		if (taskDTO.getTitle().trim().isEmpty()) {
			return task;
		}
		task = new TaskFactory().build(taskDTO);
		taskList.add(task);
		return task;
	}

	@Override
	public Task update(final Task task) {
		// TODO: Criar método responsável por atualizar progresso e status da tarefa
		Task retrivedTask = this.index(task.getId());
		retrivedTask.setTitle(task.getTitle());
		retrivedTask.setDescription(task.getDescription());
		return retrivedTask;
	}

	@Override
	public Task updateProgress(Task task) {
		Task retrivedTask = this.index(task.getId());
		retrivedTask.setProgress(task.getProgress());
		retrivedTask.setStatus(task.getStatus());
		return retrivedTask;
	}

	@Override
	public void delete(final Long taskId) {
 		// TODO: Criar método responsável por deletar tarefa por id
		Task task = this.index(taskId);
		taskList.remove(task);
	}
}
