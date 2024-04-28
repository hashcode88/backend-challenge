package backend.challenge.modules.task.services;

import kikaha.urouting.api.DefaultResponse;

public interface IRetrieveTaskByIdService {

	DefaultResponse execute(Long taskId);

}
