package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskDtoFactory;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.models.TaskFactory;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith( KikahaRunner.class )
public class RetrieveAllTasksServiceTest {

	private IRetrieveAllTasksService retrieveAllTasksService;

	@Mock
	final ITaskRepository taskRepository = new TaskRepository();
	List<Task> taskList = new ArrayList<>();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
		for (int i = 0; i < 5; i++) {
			TaskDTO taskDTO = new TaskDtoFactory().build("tarefa"+i, "");
			taskList.add(new TaskFactory().build(taskDTO));
		}
	}

	@Test
	public void shouldBeAbleToListTheTasks() {
		/*
			TODO: Para que esse teste passe, sua aplicação deve permitir que seja
					  retornado um array com todas as tarefas que foram criadas até o momento.
		*/
		when(taskRepository.show()).thenReturn(taskList);
		Assert.assertEquals(retrieveAllTasksService.execute().size(), 5);
	}

}