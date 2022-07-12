package com.kosta.myapp.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kosta.myapp.vo.relation.QWebBoard;
import com.kosta.myapp.vo.relation.WebBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface WebBoardRepository
		extends PagingAndSortingRepository<WebBoard, Long>, QuerydslPredicateExecutor<WebBoard> {

	// interface를 구현한 모든 클래스 공유해서 사용하는 메소드이다 . 재정의가능하다.
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QWebBoard board = QWebBoard.webBoard;
		builder.and(board.bno.gt(0));
		// 검색조건처리
		if (type == null)
			return builder;
		switch (type) {
		case "title":
			builder.and(board.title.like("%" + keyword + "%"));
			break;
		case "content":
			builder.and(board.content.like("%" + keyword + "%"));
			break;
		case "writer":
			builder.and(board.writer.like("%" + keyword + "%"));
			break;
		default:
			break;
		}
		return builder;
	}
}
