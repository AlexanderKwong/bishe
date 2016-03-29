package org.kwong.bishe.persistence.client;

import org.kwong.bishe.domain.Movie;
import org.kwong.bishe.domain.Type;

import java.util.List;
import java.util.Map;

/**
 * @author ������
 * @version V1.0
 * @Description
 * @Company �㶫ȫͨ�����ɷݹ�˾
 * @date 2016/3/25
 */
public interface MovieMapper {
    List<Movie> queryOne(Map params);

    int insertOne(Movie movie);
}
