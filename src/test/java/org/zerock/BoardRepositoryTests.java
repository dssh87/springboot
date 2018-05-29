package org.zerock;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Board;
import org.zerock.persistence.BoardRepository;

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
		
		List<Board> list = repo.findByBnoGreaterThanOrderByBnoDesc(0L,page);
		list.forEach(vo ->log.info(""+vo));
	}

}
