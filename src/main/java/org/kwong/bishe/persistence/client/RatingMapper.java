package org.kwong.bishe.persistence.client;

import org.kwong.bishe.domain.Movie;
import org.kwong.bishe.domain.Rating;

import java.util.List;
import java.util.Map;

/**
 * @author ������
 * @version V1.0
 * @Description
 * @Company �㶫ȫͨ�����ɷݹ�˾
 * @date 2016/3/25
 */
public interface RatingMapper {
    List<Rating> queryOne(Map params);

    int insertOne(Rating rating);
}
