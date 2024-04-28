package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import kikaha.urouting.api.DefaultResponse;

public interface ICreateTaskService {

	DefaultResponse execute(TaskDTO taskDTO);

}
