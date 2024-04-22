package br.com.gabriel.todolistchallenge;

import br.com.gabriel.todolistchallenge.entity.ToDo;
import org.glassfish.jaxb.core.v2.TODO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static br.com.gabriel.todolistchallenge.TestConstants.TODOS;
import static br.com.gabriel.todolistchallenge.TestConstants.TODO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/remove.sql")
class TodolistChallengeApplicationTests {
	@Autowired
	private WebTestClient webTestClient;
	@Test
	void testCreateToDoSuccess() {
		var todo = new ToDo("name", "descripition", false, 1);

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isCreated()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].name").isEqualTo(todo.getName())
				.jsonPath("$[0].description").isEqualTo(todo.getDescription())
				.jsonPath("$[0].done").isEqualTo(todo.isDone())
				.jsonPath("$[0].priority").isEqualTo(todo.getPriority());
	}

	@Test
	void testCreateToDoFailure() {
		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(
						new ToDo("","",false,0))
				.exchange()
				.expectStatus().isBadRequest();

	}

	@Sql("/import.sql")
	@Test
	public void testUpdateToDOSuccess(){
		var toDo = new ToDo(TODO.getId(),TODO.getName()+" Up", TODO.getDescription()
				+ "Up",!TODO.isDone(),TODO.getPriority() +1);
		webTestClient
				.put()
				.uri("/todos/" + TODO.getId())
				.bodyValue(toDo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(5)
				.jsonPath("$[0].name").isEqualTo(toDo.getName())
				.jsonPath("$[0].description").isEqualTo(toDo.getDescription())
				.jsonPath("$[0].done").isEqualTo(toDo.isDone())
				.jsonPath("$[0].priority").isEqualTo(toDo.getPriority());
	}

	@Test
	public void testUpdateToDoFailure() {
		var unexinstingId = 1L;

		webTestClient
				.put()
				.uri("/todos/" + unexinstingId)
				.bodyValue(
						new ToDo(unexinstingId, "", "", false, 0))
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Sql("/import.sql")
	@Test
	public void testDeleteTodoSuccess() {
		webTestClient
				.delete()
				.uri("/todos/" + TODOS.get(0).getId())
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(4)
				.jsonPath("$[0].name").isEqualTo(TODOS.get(1).getName())
				.jsonPath("$[0].description").isEqualTo(TODOS.get(1).getDescription())
				.jsonPath("$[0].done").isEqualTo(TODOS.get(1).isDone())
				.jsonPath("$[0].priority").isEqualTo(TODOS.get(1).getPriority());
	}

	@Test
	public void testDeleteTodoFailure() {
		var unexinstingId = 1L;

		webTestClient
				.delete()
				.uri("/todos/" + unexinstingId)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Sql("/import.sql")
	@Test
	public void testListToDos() throws Exception {
		webTestClient
				.get()
				.uri("/todos")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(5)
				.jsonPath("$[0]").isEqualTo(TODOS.get(0))
				.jsonPath("$[1]").isEqualTo(TODOS.get(1))
				.jsonPath("$[2]").isEqualTo(TODOS.get(2))
				.jsonPath("$[3]").isEqualTo(TODOS.get(3))
				.jsonPath("$[4]").isEqualTo(TODOS.get(4));
	}

}
