package backend.challenge.modules.task.services;


import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import kikaha.urouting.api.DefaultResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.mockito.Mockito.when;

@RunWith( KikahaRunner.class )
public class RetrieveTaskByIdServiceTest {

	private IRetrieveTaskByIdService retrieveTaskByIdService;
	@Mock
	final ITaskRepository taskRepository = new TaskRepository();
	private final Task task = new Task();
	private final Long TASK_ID = 6679377763735439322L;

	@Before
	public void Iinit() {
		MockitoAnnotations.initMocks(this);
		retrieveTaskByIdService = new RetriveTaskByIdService(taskRepository);
		task.setId(TASK_ID);
		task.setTitle("Compras");
		task.setDescription("Comprar presente para minha esposa");
		task.setProgress(0);
		task.setStatus(TaskStatus.PROGRESS);
		task.setCreatedAt(new Date());
	}

	@Test
	public void shouldBeAbleToListTheTaskById(){
		/*
			TODO: Para que esse teste passe, sua aplicação deve permitir que seja
			 			retornado uma tarefa com o mesmo id informado.
		*/
		when(taskRepository.index(TASK_ID)).thenReturn(task);
		final DefaultResponse defaultResponse = retrieveTaskByIdService.execute(TASK_ID);
		Task retrivedTask = (Task) defaultResponse.entity();
		Assert.assertEquals(task.getId(), retrivedTask.getId());
	}

}
