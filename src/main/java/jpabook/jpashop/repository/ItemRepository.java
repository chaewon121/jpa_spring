package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { //아이디값이 없다는 것은 신규로 등록하는것
            em.persist(item);
        } else { //아이디값이 있다는것은 업데이트같은 의미
            em.merge(item);
        }
    }
    //아이템 하나 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }
    //아이템 리스트 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }

}
