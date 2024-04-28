package backend.challenge.modules.task.services;

import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

@RunWith( KikahaRunner.class )
public class DeleteTaskServiceTest {

	private IDeleteTaskService deleteTaskService;

	@Mock
	private ITaskRepository taskRepository = new TaskRepository();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		deleteTaskService = new DeleteTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToDeleteTaskById() {
		// TODO: Para que esse teste passe, sua aplicação deve permitir que tarefas sejam deletadas por id.
		final Long taskId = 8567032865818388301L;
		deleteTaskService.execute(taskId);
		Mockito.verify(taskRepository, times(1)).delete(taskId);
	}




}