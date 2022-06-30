package com.kosta.myapp.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardVO is a Querydsl query type for BoardVO
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardVO extends EntityPathBase<BoardVO> {

    private static final long serialVersionUID = 398897378L;

    public static final QBoardVO boardVO = new QBoardVO("boardVO");

    public final NumberPath<Long> bno = createNumber("bno", Long.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.sql.Timestamp> regDate = createDateTime("regDate", java.sql.Timestamp.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.sql.Timestamp> updateDate = createDateTime("updateDate", java.sql.Timestamp.class);

    public final StringPath writer = createString("writer");

    public QBoardVO(String variable) {
        super(BoardVO.class, forVariable(variable));
    }

    public QBoardVO(Path<? extends BoardVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardVO(PathMetadata metadata) {
        super(BoardVO.class, metadata);
    }

}

