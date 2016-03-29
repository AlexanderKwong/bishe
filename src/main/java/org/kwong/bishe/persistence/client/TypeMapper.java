package org.kwong.bishe.persistence.client;

import org.kwong.bishe.domain.Type;
import org.kwong.bishe.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author ������
 * @version V1.0
 * @Description
 * @Company �㶫ȫͨ�����ɷݹ�˾
 * @date 2016/3/25
 */
public interface TypeMapper {
    List<Type> queryOne(Map params);
}
