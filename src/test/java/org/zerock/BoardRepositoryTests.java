package org.zerock;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Board;
import org.zerock.domain.QBoard;
import org.zerock.persistence.BoardRepository;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BoardRepositoryTests {
	@Autowired
	BoardRepository repo;
	
	@Test
	public void testInsert() {
		for(int i = 0; i < 100; i++) {
			Board vo = new Board();
			vo.setTitle("제목"+i);
			vo.setContent("내용"+i);
			vo.setWriter("안녕"+i);
			
			repo.save(vo);
		}
	}
	
	@Test
	public void testRead() {
		log.info(""+repo.getClass().getName());
		Optional<Board> result = repo.findById(10L);
		
		result.ifPresent(vo->log.info(""+vo));
		
	}
	
	@Test
	public void testList() {
		Pageable page = PageRequest.of(0, 10);
		
		List<Board> list = repo.findByBnoGreaterThan(0L,page);
		list.forEach(vo ->log.info(""+vo));
	}
	
	@Test
	public void testList2() {
		Pageable param = PageRequest.of(0, 20);		
		
		Page<Board> result = repo.findByTitleContains("1", param);
		log.info("result : " + result);
		log.info("total pages"+ result.getTotalPages());
	}
	
	@Test
	public void testList3(){
	
		Pageable param = PageRequest.of(0,  20);
		
		Page<Board> result = repo.getList(param);
		log.info(""+result);
	}
	
	@Test
	public void testQuerydsl() {
		
		String type = "t";
		String keyword = "1";
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard board = QBoard.board;
		
		if(type.equals("t")) {
			builder.and(board.title.like("%" + keyword + "%"));
		}
		
		builder.and(board.bno.gt(0L));
		
		/*repo.findAll(builder).iterator().forEachRemaining(vo->log.info(""+vo));*/
		Pageable page = PageRequest.of(0, 10, Direction.DESC, "bno");
		
		Page<Board> list = repo.findAll(builder, page);
		
		log.info("TOTAL: " +list.getTotalPages());
	
		list.forEach(v -> log.info(" " + v));
	
	}
}
