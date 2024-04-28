package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import backend.challenge.modules.task.services.exceptions.CreateTaskException;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThrows;

@RunWith( KikahaRunner.class )
public class CreateTaskServiceTest {

	private ICreateTaskService createTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToCreateANewTask() throws CreateTaskException {
		// TODO:  Para que esse teste passe, sua aplicação deve permitir que
		//  uma tarefa seja criado, e retorne um json com a tarefa criada.
		final String expectedTitle = "Compras";
		final String expectedDescription = "Comprar presente para minha esposa";
		TaskDTO taskDTO = TaskDTO.create();
		taskDTO.setTitle(expectedTitle);
		taskDTO.setDescription(expectedDescription);
		Task task = createTaskService.execute(taskDTO);
		Assert.assertNotNull(task.getId());
		Assert.assertEquals(task.getTitle(), expectedTitle);
		Assert.assertEquals(task.getDescription(), expectedDescription);
	}

	@Test
	public void ShouldNotBeAbleToCreateNewTaskWithEmptyTitle() throws CreateTaskException {
		final String expectedTitle = "";
		final String expectedDescription = "Comprar presente para minha esposa";
		TaskDTO taskDTO = TaskDTO.create();
		taskDTO.setTitle(expectedTitle);
		taskDTO.setDescription(expectedDescription);
		Exception exception = assertThrows(CreateTaskException.class, () ->{
			createTaskService.execute(taskDTO);
		});
		final String expectedMessage = "Titulo não pode ser vazio";
		Assert.assertTrue(exception.getMessage().contains(expectedMessage));
	}
}