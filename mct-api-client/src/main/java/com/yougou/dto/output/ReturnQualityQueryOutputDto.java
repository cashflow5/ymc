package com.yougou.dto.output;

import java.util.Collections;
import java.util.List;


public class ReturnQualityQueryOutputDto extends PageableOutputDto {

	private static final long serialVersionUID = 4093477387725751250L;
	
	/** 页数据 **/
	private List<ReturnQA> items = Collections.emptyList();
	
	public ReturnQualityQueryOutputDto() {
		super();
	}

	public ReturnQualityQueryOutputDto(int page_index, int page_size, int total_count) {
		super(page_index, page_size, total_count);
	}
	
	public void setItems(List<ReturnQA> items) {
		this.items = items;
	}
	
	@Override
	public List<ReturnQA> getItems() {
		return this.items;
	}
	
}
