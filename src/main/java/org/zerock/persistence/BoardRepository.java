package org.zerock.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {
	
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable pageable);

}