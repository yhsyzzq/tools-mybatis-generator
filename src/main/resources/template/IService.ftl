package ${packageName};

import org.apache.ibatis.annotations.Param;
import java.util.List;
${importStr}

/**
 *
 * ${description}服务类接口
 *
 * @version
 * <pre>
 * Created by ${author} on ${createDate}.
 * </pre>
 * @since 1.0
 */
public interface ${className} {
    /**
     * <p>
     * Description : 插入${description}<br />
     * </p>
     *
     * @param ${entityName}
     *
     * @return
     *
     * @date ${createDate}
     */
    void insert(${entityClassName} ${entityName});

    /**
     * <p>
     * Description : 更新${description}<br />
     * </p>
     *
     * @param ${entityName}
     *
     * @return
     *
     * @date ${createDate}
     */
    void updateByKey(${entityClassName} ${entityName});

    /**
     * <p>
     * Description : 根据id删除${description}记录<br />
     * </p>
     *
     * @param id
     *
     * @date ${createDate}
     */
    void deleteByKey(@Param(value = "id") String id);

    /**
     * <p>
     * Description : 根据ID获取记录<br />
     * </p>
     *
     * @param id
     *
     * @return
     *
     * @date ${createDate}
     */
    ${entityClassName} selectByKey(@Param(value = "id") String id);

    /**
     * <p>
     * Description : 模糊匹配，分页查询${description}记录列表<br />
     * </p>
     *
     * @param ${entityName}
     *
     * @param offset
     *
     * @param limit
     *
     * @return
     *
     * @date ${createDate}
     */
    List<${entityClassName}> likePage(${entityClassName} ${entityName}, int offset, int limit);

    /**
     * <p>
     * Description : 模糊匹配，不进行分页<br />
     * </p>
     *
     * @param ${entityName}
     *
     * @return
     *
     * @date ${createDate}
     */
    List<${entityClassName}> like(${entityClassName} ${entityName});


    /**
     * <p>
     * Description : 获取${description}记录总条数<br />
     * </p>
     *
     * @param ${entityName}
     *
     * @return
     *
     * @date ${createDate}
     */
    int rows(${entityClassName} ${entityName});
}