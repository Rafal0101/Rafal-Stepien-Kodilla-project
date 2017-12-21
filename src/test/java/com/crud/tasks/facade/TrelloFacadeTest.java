package com.crud.tasks.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {
    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloMapper trelloMapper;

    @Mock
    private TrelloValidator trelloValidator;

    @Test
    public void shouldFetchEmptyList() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "Test list", false));

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "test", trelloListDtos));

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", trelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);
        when(trelloMapper.mapToBoard(trelloBoardDtos)). thenReturn(trelloBoards);
//        when(trelloMapper.mapToBoardDto(trelloBoards)).thenReturn(trelloBoardDtos);
        when(trelloValidator.validateTrelloBoards(trelloBoards)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtos1 = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos1);
        assertEquals(0, trelloBoardDtos1.size());
    }

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "my_list", false));

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "my_task", trelloListDtos));

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "my_list", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "my_task", trelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);
        when(trelloMapper.mapToBoard(trelloBoardDtos)). thenReturn(trelloBoards);
        when(trelloMapper.mapToBoardDto(trelloBoards)).thenReturn(trelloBoardDtos);
        when(trelloValidator.validateTrelloBoards(trelloBoards)).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> trelloBoardDtos1 = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos1);
        assertEquals(1, trelloBoardDtos1.size());

        trelloBoardDtos1.forEach( trelloBoardDto -> {
            assertEquals("1", trelloBoardDto.getId());
            assertEquals("my_task", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("1", trelloListDto.getId());
                assertEquals("my_list", trelloListDto.getName());
                assertFalse(trelloListDto.isClosed());
            });
        });

    }
}
