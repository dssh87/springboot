package org.zerock.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
	
	public List<Board> findByBnoGreaterThan(Long bno, Pageable pageable);
	
	public Page<Board> findByTitleContains(String keyword, Pageable pageable);
	
	@Query("SELECT b FROM Board b WHERE b.bno > 0 ORDER BY b.bno DESC")
	public Page<Board> getList(Pageable pageable);
	
	@Query(value="select bno, title, content, writer from tbl_board where bno > 0 limit 0, 10", nativeQuery=true)
	public List<Object[]> getListNative();

}