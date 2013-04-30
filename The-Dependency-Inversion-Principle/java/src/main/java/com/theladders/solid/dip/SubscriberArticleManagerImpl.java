package com.theladders.solid.dip;

import java.util.Arrays;

import java.util.List;


public class SubscriberArticleManagerImpl implements SubscriberArticleManager
{

  private SuggestedArticleDao              suggestedArticleDao;
  private RepositoryManager                repositoryManager;

  public SubscriberArticleManagerImpl(SuggestedArticleDao suggestedArticleDao,
                                      RepositoryManager repositoryManager)
  {
    this.suggestedArticleDao = suggestedArticleDao;
    this.repositoryManager = repositoryManager;
  }

  public List<SuggestedArticle> getArticlesbySubscriber(Integer subscriberId)
  {
    SuggestedArticleExample criteria = new SuggestedArticleExample();
    criteria.createCriteria()
            .andSubscriberIdEqualTo(subscriberId)
            .andSuggestedArticleStatusIdIn(Arrays.asList(1, 2))  // must be New or Viewed
            .andSuggestedArticleSourceIdEqualTo(1);

    criteria.setOrderByClause("create_time desc");
    List<SuggestedArticle> dbSuggestions = suggestedArticleDao.selectByExampleWithBlobs(criteria);

    // Fetch content associated with SuggestedArticle (based on externalArticleId)
    SubscriberArticleProcessor articleProcessor = new SubscriberArticleProcessor(repositoryManager);
    articleProcessor.resolveArticles(dbSuggestions);

    return dbSuggestions;
  }

  public int addSuggestedArticle(SuggestedArticle suggestedArticle)
  {
    return suggestedArticleDao.addArticle(suggestedArticle);
  }

  public void updateNote(Integer id, String note)
  {
    SuggestedArticle article = suggestedArticleDao.getArticle(id);
    article.setNote(note);
    suggestedArticleDao.updateArticle(article);
  }

  public void markRecomDeleted(Integer id)
  {
    suggestedArticleDao.delete(suggestedArticleDao.getArticle(id));
  }
  
}