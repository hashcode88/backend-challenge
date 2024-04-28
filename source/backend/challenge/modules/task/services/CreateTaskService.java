package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import kikaha.urouting.api.DefaultResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CreateTaskService implements ICreateTaskService {

	private final ITaskRepository taskRepository;

	@Inject
	public CreateTaskService(final ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public DefaultResponse execute(TaskDTO taskDTO) {
		// TODO: Criar serviço responsável por criar uma tarefa
		Task task = taskRepository.create(taskDTO);
		if (task == null) {
			return DefaultResponse.notModified().entity("Titulo não pode ser vazio");
		}
		return DefaultResponse.ok().entity(task);
	}
}
