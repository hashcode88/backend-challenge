package backend.challenge.modules.task.services;


import backend.challenge.modules.task.dtos.TaskProgressDtoFactory;
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
public class UpdateTaskProgressServiceTest {

	private IUpdateTaskProgressService updateTaskProgressService;

	@Mock
	private ITaskRepository taskRepository = new TaskRepository();

	private final Task retrivedTask = new Task();
	private final Task updatedTask = new Task();
	private final Long TASK_ID = 8567032865818388301L;
	private final int OLD_TASK_PROGRESS = 10;
	private final TaskStatus TASK_STATUS = TaskStatus.PROGRESS;
	private final Date CREATE_AT = new Date(2024, 4, 28);
	private final String TASk_TITLE = "Projeto";
	private final String TASk_DESCRIPTION = "Implementar chat com llama3";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		updateTaskProgressService = new UpdateTaskProgressService(taskRepository);
		retrivedTask.setId(TASK_ID);
		retrivedTask.setTitle(TASk_TITLE);
		retrivedTask.setDescription(TASk_DESCRIPTION);
		retrivedTask.setProgress(OLD_TASK_PROGRESS);
		retrivedTask.setStatus(TASK_STATUS);
		retrivedTask.setCreatedAt(CREATE_AT);

		updatedTask.setId(TASK_ID);
		updatedTask.setTitle(TASk_TITLE);
		updatedTask.setDescription(TASk_DESCRIPTION);
		updatedTask.setProgress(50);
		updatedTask.setStatus(TASK_STATUS);
		updatedTask.setCreatedAt(CREATE_AT);
	}

	@Test
	public void shouldBeAbleToUpdateTaskProgress() {
		/*
			TODO:  Para que esse teste passe, sua aplicação deve permitir que sejam
		         alterados apenas o campo `progress`.
		*/
		final int expectedProgress = 50;
		when(taskRepository.index(anyLong())).thenReturn(retrivedTask);
		when(taskRepository.updateProgress(anyObject())).thenReturn(updatedTask);
		DefaultResponse defaultResponse = updateTaskProgressService.execute(new TaskProgressDtoFactory().build(TASK_ID, expectedProgress));
		Task task = (Task) defaultResponse.entity();
		Assert.assertNotEquals(task.getProgress(), OLD_TASK_PROGRESS);
	}

	@Test
	public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() {
		/*
			TODO:  Para que esse teste passe, sua aplicação deve permitir que sejam
		         alterado apenas o campo `status`, quando o progresso for igual a 100.
		*/
		final Task completedTask = new Task();
		completedTask.setId(TASK_ID);
		completedTask.setTitle(TASk_TITLE);
		completedTask.setDescription(TASk_DESCRIPTION);
		completedTask.setProgress(100);
		completedTask.setStatus(TaskStatus.COMPLETE);
		completedTask.setCreatedAt(CREATE_AT);

		final int progress = 100;
		when(taskRepository.index(anyLong())).thenReturn(retrivedTask);
		when(taskRepository.updateProgress(anyObject())).thenReturn(completedTask);
		DefaultResponse defaultResponse = updateTaskProgressService.execute(new TaskProgressDtoFactory().build(TASK_ID, progress));
		Task task = (Task) defaultResponse.entity();
		Assert.assertEquals(task.getStatus(), TaskStatus.COMPLETE);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskProgressWhenProgressLessThanOneHundred() {
		/*
			TODO: Não deve ser capaz de atualizar o progresso da tarefa quando o progresso for inferior a cem.
		*/

		final int progress = 99;
		when(taskRepository.index(anyLong())).thenReturn(retrivedTask);
		when(taskRepository.updateProgress(anyObject())).thenReturn(updatedTask);
		DefaultResponse defaultResponse = updateTaskProgressService.execute(new TaskProgressDtoFactory().build(TASK_ID, progress));
		Task task = (Task) defaultResponse.entity();
		Assert.assertNotEquals(task.getStatus(), TaskStatus.COMPLETE);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskProgressWhenProgressLessThanZero() {
		/*
			TODO: Para que esse teste passe, você não deve permitir
			 que sua rota de update de progresso de tarefa altere o progresso para menor que 0.
		*/
		final Task updatedTaskWithZeroProgress = new Task();
		updatedTaskWithZeroProgress.setId(TASK_ID);
		updatedTaskWithZeroProgress.setTitle(TASk_TITLE);
		updatedTaskWithZeroProgress.setDescription(TASk_DESCRIPTION);
		updatedTaskWithZeroProgress.setProgress(0);
		updatedTaskWithZeroProgress.setStatus(TASK_STATUS);
		updatedTaskWithZeroProgress.setCreatedAt(CREATE_AT);

		when(taskRepository.index(anyLong())).thenReturn(retrivedTask);
		when(taskRepository.updateProgress(anyObject())).thenReturn(updatedTaskWithZeroProgress);
		final int progress = -3;
		DefaultResponse defaultResponse = updateTaskProgressService.execute(new TaskProgressDtoFactory().build(TASK_ID, progress));
		Task task = (Task) defaultResponse.entity();
		Assert.assertEquals(task.getProgress(), 0);
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskProgressWhenProgressGreaterThanOneHundred() {
		/*
			TODO: Para que esse teste passe, você não deve permitir
			 que sua rota de update de progresso de tarefa altere o progresso para maior que 100.
		*/
		final Task updatedTaskWithZeroProgress = new Task();
		updatedTaskWithZeroProgress.setId(TASK_ID);
		updatedTaskWithZeroProgress.setTitle(TASk_TITLE);
		updatedTaskWithZeroProgress.setDescription(TASk_DESCRIPTION);
		updatedTaskWithZeroProgress.setProgress(100);
		updatedTaskWithZeroProgress.setStatus(TASK_STATUS);
		updatedTaskWithZeroProgress.setCreatedAt(CREATE_AT);

		when(taskRepository.index(anyLong())).thenReturn(retrivedTask);
		when(taskRepository.updateProgress(anyObject())).thenReturn(updatedTaskWithZeroProgress);
		final int progress = 200;
		DefaultResponse defaultResponse = updateTaskProgressService.execute(new TaskProgressDtoFactory().build(TASK_ID, progress));
		Task task = (Task) defaultResponse.entity();
		Assert.assertEquals(task.getProgress(), 100);
	}

}
