package com.crud.tasks.tasks.mappers;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testTaskToTaskDto() {
        //Given
        Task task = new Task(1L, "One", "First Task");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(1L, taskDto.getId(), 0);
        assertEquals("One", taskDto.getName());
        assertEquals("First Task", taskDto.getDescription());
    }

    @Test
    public void testTaskDtoToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "One", "First Task");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(1L, task.getId(), 0);
        assertEquals("One", task.getName());
        assertEquals("First Task", task.getDescription());
    }

    @Test
    public void testTaskListToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "One", "First Task"));

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskListDto(taskList);

        //Then
        assertEquals(1, taskDtoList.size());
        taskDtoList.forEach(taskDto -> {
            assertEquals(1L, taskDto.getId(), 0);
            assertEquals("One", taskDto.getName());
            assertEquals("First Task", taskDto.getDescription());
        });
    }
}
