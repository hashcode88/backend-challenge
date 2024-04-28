package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskDtoFactory;
import backend.challenge.modules.task.infra.http.views.TaskView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.*;
import backend.challenge.modules.task.services.exceptions.CreateTaskException;
import backend.challenge.modules.task.services.exceptions.RetriveTaskException;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Path("tasks")
public class TaskController {

	private final ICreateTaskService createTaskService;
	private final IDeleteTaskService deleteTaskService;
	private final IRetrieveAllTasksService retrieveAllTasksService;
	private final IRetrieveTaskByIdService retrieveTaskByIdService;
	private final IUpdateTaskService updateTaskService;

	@Inject
	public TaskController(
		final ICreateTaskService createTaskService,
		final IDeleteTaskService deleteTaskService,
		final IRetrieveAllTasksService retrieveAllTasksService,
		final IRetrieveTaskByIdService retrieveTaskByIdService,
		final IUpdateTaskService updateTaskService
	) {
		this.createTaskService = createTaskService;
		this.deleteTaskService = deleteTaskService;
		this.retrieveAllTasksService = retrieveAllTasksService;
		this.retrieveTaskByIdService = retrieveTaskByIdService;
		this.updateTaskService = updateTaskService;
	}

	@GET
	public Response show() {
		// TODO: Rota que lista todas as tarefas
		return DefaultResponse.ok().entity(retrieveAllTasksService.execute());
	}

	@GET
	@Path("single/{taskId}")
	public Response index(@PathParam("taskId") Long taskId) {
		// TODO: A rota deve retornar somente a tarefa a qual o id corresponder
		Task retrivedTask = retrieveTaskByIdService.execute(taskId);
		if(retrivedTask == null) {
			return DefaultResponse.notFound().statusCode(404);
		}
        return DefaultResponse.ok().entity(retrivedTask);
	}

	@POST
	public Response create(TaskView task) {
		// TODO: A rota deve receber title e description, sendo o `title` o título da tarefa e `description` uma descrição da tarefa.
        Task createdTask = null;
		try {
			createdTask = createTaskService.execute(new TaskDtoFactory().build(task.getTitle(), task.getDescription()));
        } catch (CreateTaskException e) {
			DefaultResponse.badRequest().entity(e.getMessage());
            throw new RuntimeException(e);
        }
		return DefaultResponse.ok().entity(createdTask);
	}

	@PUT
	@Path("single/{taskId}")
	public Response update(@PathParam("taskId") Long taskId, Task task) {
		/*
			TODO:  A rota deve alterar apenas o title e description da tarefa
			 			 que possua o id igual ao id correspondente nos parâmetros da rota.
		 */
		return updateTaskService.execute(taskId, task);
    }

	@DELETE
	@Path("single/{taskId}")
	public Response delete(@PathParam("taskId") Long taskId) {
		// TODO: A rota deve deletar a tarefa com o id correspondente nos parâmetros da rota

		return DefaultResponse.ok().entity("Hello world");
	}

}
