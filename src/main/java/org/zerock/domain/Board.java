package org.zerock.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Table(name="tbl_board")
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bno;

	private String title, content, writer;
	@CreationTimestamp //레코드가 생성될때 시간 기록해줌
	private Timestamp regdate;	
	@UpdateTimestamp //update시간 자동기록
	private Timestamp updateDate;


}
