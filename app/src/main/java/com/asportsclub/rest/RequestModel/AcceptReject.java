package com.asportsclub.rest.RequestModel;

/**
 * Created by Narendra on 2/20/2017.
 */
public class AcceptReject {

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    int boardId;
    int id;
    int userId;
    String action;
    public AcceptReject(int boardId, int userId , int id, String action) {
        this.boardId = boardId;
        this.userId = userId;
        this.id=id;
        this.action=action;
    }


}
