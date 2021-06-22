package com.example.spring02.model.memo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.spring02.model.memo.dto.MemoDTO;

public interface MemoDAO {
	String list_memo="select * from memo order by idx desc";
	String insert_memo="insert into memo (idx,writer,memo) values "
			+ "((select nvl(max(idx)+1,1) from memo),#{writer},#{memo})";
	
	@Select(list_memo)
	public List<MemoDTO> list();
	
	@Insert(insert_memo)
	public void insert(@Param("writer") String writer, @Param("memo") String memo);
	
	@Select("select * from memo where idx=#{idx}")
	public MemoDTO memo_view(@Param("idx") int idx);
	
	@Update("update memo set writer=#{writer},memo=#{memo} where idx=#{idx}")
	public void memo_update(MemoDTO dto);
	
	@Delete("delete from memo where idx=#{idx}")
	public void memo_delete(@Param("idx") int idx);
			
}
