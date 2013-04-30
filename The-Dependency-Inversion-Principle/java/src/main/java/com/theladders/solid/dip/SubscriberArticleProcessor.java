package com.theladders.solid.dip;

import java.util.List;

public class SubscriberArticleProcessor {

	private RepositoryManager repositoryManager;
	
	public SubscriberArticleProcessor(RepositoryManager repositoryManager){
		this.repositoryManager = repositoryManager;
	}

	public void resolveArticles(List<SuggestedArticle> dbArticles)
	{
		for (SuggestedArticle article : dbArticles)
		{

			// Attempt to fetch the actual content;
			ContentNode content = this.repositoryManager.getNodeByUuid(article.getArticleExternalIdentifier());
			if (content != null && ContentUtils.isPublishedAndEnabled(content))
			{
				// Override miniImagePath
				overrideMiniImagePath(content);
				article.setContent(content);
			}
		}
	}

	public void overrideMiniImagePath(ContentNode node)
	{
		String path = (String) node.getProperty(PropertiesDao.getImagePathPropertyName());

		if (path == null || path.length() == 0)
		{
			String category = (String) node.getProperty("primaryTopic");
			node.addProperty(PropertiesDao.getImagePathPropertyName(), PropertiesDao.getImagePrefix() + PropertiesDao.getcategoryImageMap().get(category));
		}
	}
}
