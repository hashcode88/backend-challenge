package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskDtoFactory;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import kikaha.urouting.api.DefaultResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class CreateTaskServiceTest {

	private ICreateTaskService createTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();
		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToCreateANewTask() {
		// TODO:  Para que esse teste passe, sua aplicação deve permitir que
		//  uma tarefa seja criado, e retorne um json com a tarefa criada.
		final String expectedTitle = "Compras";
		final String expectedDescription = "Comprar presente para minha esposa";
		TaskDTO taskDTO = new TaskDtoFactory().build(expectedTitle, expectedDescription);
		DefaultResponse defaultResponse = createTaskService.execute(taskDTO);
		Task task = (Task) defaultResponse.entity();
		Assert.assertNotNull(task.getId());
		Assert.assertEquals(task.getTitle(), expectedTitle);
		Assert.assertEquals(task.getDescription(), expectedDescription);
	}

	@Test
	public void ShouldNotBeAbleToCreateNewTaskWithEmptyTitle() {
		final String expectedTitle = "";
		final String expectedDescription = "Comprar presente para minha esposa";
		TaskDTO taskDTO = new TaskDtoFactory().build(expectedTitle, expectedDescription);
		DefaultResponse defaultResponse = createTaskService.execute(taskDTO);
		final String expectedMessage = "Titulo não pode ser vazio";
		Assert.assertTrue(defaultResponse.entity().toString().contains(expectedMessage));
	}
}