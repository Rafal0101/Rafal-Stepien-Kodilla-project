package com.crud.tasks.trello.mappers;
/*
Exercise 21.1.
Stosowanie wzorca fasady w projekcie
Zadanie: Napisz test
Twoim zadaniem będzie napisanie testu, sprawdzającego poprawne działanie mapperów. Sprawdź czy na pewno wszystkie
mappery działają poprawnie, aby nie zawiodły w trakcie działania aplikacji!
 */

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
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
public class TrelloMappersTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapBoardToBoardDto() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();

        trelloLists.add(new TrelloList("1", "FirstList", false));
        trelloLists.add(new TrelloList("2", "SecondList", false));
        trelloLists.add(new TrelloList("3", "ThirdList", true));
        trelloLists.add(new TrelloList("4", "FourthList", true));

        trelloBoards.add(new TrelloBoard("One", "FirstBoard", trelloLists));

        //When
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.mapToBoardDto(trelloBoards);

        //Then
        assertEquals(1, trelloBoardDto.size());
        assertEquals(4, trelloBoardDto.get(0).getLists().size());
        assertEquals("One", trelloBoardDto.get(0).getId());
        assertEquals("4", trelloBoardDto.get(0).getLists().get(3).getId());
    }

    @Test
    public void testMapBoardDtoToBoard() {
        //Given
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        List<TrelloListDto> trelloListsDto = new ArrayList<>();

        trelloListsDto.add(new TrelloListDto("1", "FirstList", false));
        trelloListsDto.add(new TrelloListDto("2", "SecondList", false));
        trelloListsDto.add(new TrelloListDto("3", "ThirdList", true));
        trelloListsDto.add(new TrelloListDto("4", "FourthList", true));

        trelloBoardsDto.add(new TrelloBoardDto("One", "FirstBoard", trelloListsDto));

        //When
        List<TrelloBoard> trelloBoard = trelloMapper.mapToBoard(trelloBoardsDto);

        //Then
        assertEquals(1, trelloBoard.size());
        assertEquals(4, trelloBoard.get(0).getLists().size());
        assertEquals("One", trelloBoard.get(0).getId());
        assertEquals("4", trelloBoard.get(0).getLists().get(3).getId());
    }

    @Test
    public void testMapCardToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("FirstCard", "First testing card", "1", "1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("FirstCard", trelloCardDto.getName());
    }

    @Test
    public void testMapCardDtoToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("FirstCard", "First testing card", "1", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("FirstCard", trelloCard.getName());
    }
}