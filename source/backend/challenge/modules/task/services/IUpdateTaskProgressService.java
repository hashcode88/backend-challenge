package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import kikaha.urouting.api.DefaultResponse;

public interface IUpdateTaskProgressService {

	DefaultResponse execute(TaskProgressDTO taskProgressDTO);

}
