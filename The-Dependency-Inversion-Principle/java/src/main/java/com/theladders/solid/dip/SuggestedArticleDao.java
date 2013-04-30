package com.theladders.solid.dip;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuggestedArticleDao {
	public void updateArticle(
			@SuppressWarnings("unused") SuggestedArticle article) {
	}

	public int addArticle(
			@SuppressWarnings("unused") SuggestedArticle suggestedArticle) {
		// Integer STATUS_UNREAD = 1;
		// Integer HTP_CONSULTANT = 1;
		// suggestedArticle.setSuggestedArticleStatusId(STATUS_UNREAD);
		// suggestedArticle.setSuggestedArticleSourceId(HTP_CONSULTANT);
		// suggestedArticle.setCreateTime(new Date()); // current date
		// suggestedArticle.setUpdateTime(new Date()); // current date
		return 0;
	}

	public List<SuggestedArticle> selectByExampleWithBlobs(
			@SuppressWarnings("unused") SuggestedArticleExample criteria) {
		return Collections.singletonList(new SuggestedArticle());
	}

	public void delete(SuggestedArticle article) {
		// Integer STATUS_DELETED = 4;
		// SuggestedArticle article = new SuggestedArticle();
		// article.setSuggestedArticleId(id);
		// article.setSuggestedArticleStatusId(STATUS_DELETED);
	}

	public SuggestedArticle getArticle(Integer id) {
		return new SuggestedArticle();
	}
}
