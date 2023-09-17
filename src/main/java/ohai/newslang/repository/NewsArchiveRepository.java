package ohai.newslang.repository;

import lombok.RequiredArgsConstructor;
import ohai.newslang.domain.NewsArchive;
import ohai.newslang.domain.subscribe.item.SubscribeItem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NewsArchiveRepository {

    private final EntityManager em;

    public void save(NewsArchive item){
        em.persist(item);
    }

    public NewsArchive findOne(Long id){
        return em.find(NewsArchive.class, id);
    }

    public NewsArchive findByUrl(String url){
        return em.createQuery(
                        "select na from NewsArchive na" +
                                " where na.news.url = :url", NewsArchive.class)
                .setParameter("url", url)
                .getSingleResult();
    }

    public List<NewsArchive> findAllIdWithName(List<String> urlList){
        return em.createQuery(
                        "select na from NewsArchive na" +
                                " where na.news.url in :urlList", NewsArchive.class)
                .setParameter("urlList", urlList)
                .getResultList();
    }
}