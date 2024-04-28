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

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith( KikahaRunner.class )
public class UpdateTaskServiceTest {

	private IUpdateTaskService updateTaskService;

	@Mock
	private ITaskRepository taskRepository = new TaskRepository();
	private final Task retrivedTask = new Task();
	private final Task taskChanges = new Task();
	private final Task updatedTask = new Task();
	private final Long TASK_ID = 8567032865818388301L;
	private final int TASK_PROGRESS = 50;
	private final TaskStatus TASK_STATUS = TaskStatus.PROGRESS;
	private final Date CREATE_AT = new Date(2024, 4, 28);
	private final String OLD_TASk_TITLE = "Trabalho";
	private final String OLD_TASk_DESCRIPTION = "Capinar meu terreno";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		updateTaskService = new UpdateTaskService(taskRepository);

		retrivedTask.setId(TASK_ID);
        retrivedTask.setTitle(OLD_TASk_TITLE);
		retrivedTask.setDescription(OLD_TASk_DESCRIPTION);
		retrivedTask.setProgress(TASK_PROGRESS);
		retrivedTask.setStatus(TASK_STATUS);
		retrivedTask.setCreatedAt(CREATE_AT);

		taskChanges.setId(6714351139364770828L);
		taskChanges.setTitle("Compras");
		taskChanges.setDescription("Comprar presente pra minha esposa");
		taskChanges.setProgress(0);
		taskChanges.setStatus(TaskStatus.PROGRESS);
		taskChanges.setCreatedAt(CREATE_AT);

		updatedTask.setId(TASK_ID);
		updatedTask.setTitle("Compras");
		updatedTask.setDescription("Comprar presente pra minha esposa");
		updatedTask.setProgress(50);
		updatedTask.setStatus(TaskStatus.PROGRESS);
		updatedTask.setCreatedAt(CREATE_AT);
	}

	@Test
	public void shouldBeAbleToUpdateTask() {
		/*
			TODO:  Para que esse teste passe, sua aplicação deve permitir que sejam
		         alterados apenas os campos `title` e `observation`.
		*/
		when(taskRepository.index(anyLong())).thenReturn(retrivedTask);
		when(taskRepository.update(anyObject())).thenReturn(updatedTask);
		final DefaultResponse defaultResponse = updateTaskService.execute(TASK_ID, taskChanges);
		Assert.assertEquals(retrivedTask.getId(), TASK_ID);
		Assert.assertEquals(retrivedTask.getProgress(), TASK_PROGRESS);
		Assert.assertEquals(retrivedTask.getStatus(), TASK_STATUS);
		Assert.assertEquals(retrivedTask.getCreatedAt(), CREATE_AT);
		Assert.assertNotEquals(retrivedTask.getTitle(), OLD_TASk_TITLE);
		Assert.assertNotEquals(retrivedTask.getDescription(), OLD_TASk_DESCRIPTION);

	}

	@Test
	public void shouldNotBeAbleToUpdateATaskThatDoesNotExist() {
		/*
			TODO: Para que esse teste passe, você deve validar na sua rota de update se
			 			o id da tarefa enviada pela url existe ou não. Caso não exista, retornar um erro com status 400.
		*/
		final DefaultResponse defaultResponse = updateTaskService.execute(5101162744757410452L, taskChanges);
		DefaultResponse expectedResponse = DefaultResponse.notFound().statusCode(404);
		Assert.assertEquals(expectedResponse.statusCode(), defaultResponse.statusCode());
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskStatusManually() {
		/*
			TODO:  Para que esse teste passe, você não deve permitir que sua rota de update
						 altere diretamente o `status` dessa tarefa, mantendo o mesmo status que a tarefa
						 já possuía antes da atualização. Isso porque o único lugar que deve atualizar essa informação
						 é a rota responsável por alterar o progresso da tarefa.

		 */
	}


}