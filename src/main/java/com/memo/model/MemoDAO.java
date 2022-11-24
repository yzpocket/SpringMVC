package com.memo.model;

import java.util.List;

public interface MemoDAO {
	int insertMemo(MemoVO memo);
	List<MemoVO> listMemo();
	int deleteMemo(int idx);
	int updateMemo(MemoVO memo);
	MemoVO getMemo(int idx);
	
	int getTotalCount();
}
