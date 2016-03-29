package org.kwong.bishe.persistence.client;

import org.kwong.bishe.domain.MovieType;
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
public interface MovieTypeMapper {
    List<MovieType> queryOne(Map params);

    int insertOne(MovieType movieType);
}
