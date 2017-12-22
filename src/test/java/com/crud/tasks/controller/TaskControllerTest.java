package com.crud.tasks.controller;

/*
Exercise 21.3
Zadanie: Przetestuj TaskController
Wykonaj po jednym teście na każdą metodę controllera TaskController. Wykorzystaj zdobytą wiedzę w tym submodule,
aby poprawnie przeprowadzić powyższe testy.
 */

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    public void shouldGetTasks() throws Exception {
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "Task1", "Test task 1"));

        when(taskMapper.mapToTaskListDto(any())).thenReturn(taskDtoList);

        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Task1")))
                .andExpect(jsonPath("$[0].description", is("Test task 1")));

    }

    @Test
    public void shouldGetTask() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "Task1", "Test task 1");
        Task task = new Task(1L, "Task1", "Test task 1");

        when(dbService.getTask(any())).thenReturn(ofNullable(task));
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        mockMvc.perform(get("/v1/task/getTask")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Task1")))
                .andExpect(jsonPath("$.description", is("Test task 1")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        Task task = new Task(1L, "Task1", "Test task 1");

        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);


        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        Task task = new Task(1L, "Task1", "Test task 1");
        TaskDto taskDto = new TaskDto(1L, "Updated Task", "Updated first task");

        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);


        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated Task")))
                .andExpect(jsonPath("$.description", is("Updated first task")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        mockMvc.perform(delete("/v1/task/deleteTask?id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
